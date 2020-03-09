package com.mbo.loblaws.feed

import com.mbo.loblaws.AppState
import com.mbo.loblaws.domain.toError
import com.mbo.redux.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FeedStore @Inject constructor(
    private val store: Store<AppState>
) : ActionDispatcher<FeedAction>, NavigationActionObserver<FeedAction.Navigation> {

    fun observeState(): Flow<FeedState> {
        return store.observeState().map { state -> state.feedState }
    }

    override fun observeNavigation(): Flow<FeedAction.Navigation> {
        return store.observeNavigation().ofType()
    }

    override fun dispatchAction(action: FeedAction) {
        store.dispatchAction(action)
    }
}
