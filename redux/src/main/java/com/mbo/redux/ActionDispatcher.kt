package com.mbo.redux

interface ActionDispatcher<A : Action> {
    fun dispatchAction(action: A)
}