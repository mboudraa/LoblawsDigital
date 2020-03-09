package com.mbo.redux

interface SideEffect<STATE> {
    suspend operator fun invoke(state: STATE, action: Action): Action?
}

fun <STATE> sideEffect(f: suspend (state: STATE, action: Action) -> Unit): SideEffect<STATE> {
    return object : SideEffect<STATE> {
        override suspend fun invoke(state: STATE, action: Action): Action? {
            f(state, action)
            return null
        }
    }
}

fun <STATE> actionSideEffect(f: suspend (state: STATE, action: Action) -> Action?): SideEffect<STATE> {
    return object : SideEffect<STATE> {
        override suspend fun invoke(state: STATE, action: Action): Action? {
            return f(state, action)
        }
    }
}