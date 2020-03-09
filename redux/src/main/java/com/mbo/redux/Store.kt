package com.mbo.redux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

typealias Reducer<STATE> = (state: STATE, action: Action) -> STATE

class Store<STATE> constructor(
    initialState: STATE,
    reducer: Reducer<STATE>,
    sideEffects: Set<SideEffect<STATE>>,
    private val scope: CoroutineScope
) : ActionDispatcher<Action>, NavigationActionObserver<NavigationAction> {

    private val singleDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    private val actionChannel = BroadcastChannel<Action>(1)
    private val stateChannel = ConflatedBroadcastChannel(initialState)

    init {
        actionChannel.asFlow()
            .scan(initialState) { state, action -> reducer.invoke(state, action) }
            .flowOn(singleDispatcher)
            .onEach(stateChannel::send)
            .launchIn(scope)

        stateChannel.asFlow()
            .zip(actionChannel.asFlow()) { state, action -> state to action }
            .flatMapMerge { (state, action) ->
                sideEffects.asFlow()
                    .mapNotNull { it.invoke(state, action) }
                    .flowOn(Dispatchers.IO)
            }
            .onEach(actionChannel::send)
            .launchIn(scope)
    }

    override fun dispatchAction(action: Action) {
        scope.launch {
            actionChannel.send(action)
        }
    }

    fun observeState() = stateChannel.asFlow()
    override fun observeNavigation() = actionChannel.asFlow().ofType<NavigationAction>()

}

inline fun <reified T> Flow<*>.ofType(): Flow<T> {
    return filter { it is T }.map { it as T }
}
