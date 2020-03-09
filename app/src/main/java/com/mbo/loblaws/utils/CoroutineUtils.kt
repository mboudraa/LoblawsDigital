package com.mbo.loblaws.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

suspend inline fun <A, B, C> zip(
    crossinline f: suspend () -> A,
    crossinline g: suspend () -> B,
    crossinline block: (A, B) -> C
): C {
    return coroutineScope {
        val a = async { f() }
        val b = async { g() }
        block(a.await(), b.await())
    }
}

fun <T> Flow<T>.launchWhenResumedIn(scope: LifecycleCoroutineScope): Job = scope.launchWhenResumed {
    collect() // tail-call
}

inline fun <T> Flow<T>.onEach(crossinline action: (T) -> Unit): Flow<T> = onEach { action(it) }