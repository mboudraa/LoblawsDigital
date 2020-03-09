package com.mbo.redux

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

interface NavigationActionObserver<A : NavigationAction> {
    fun observeNavigation(): Flow<A>
}

inline fun <reified T : NavigationAction> NavigationActionObserver<out NavigationAction>.filterNavigation(): Flow<T> {
    return observeNavigation()
        .filter { action -> T::class.java.isAssignableFrom(action::class.java) }
        .map { it as T }
}
