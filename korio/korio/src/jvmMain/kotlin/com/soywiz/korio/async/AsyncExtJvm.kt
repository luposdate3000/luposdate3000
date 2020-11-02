package com.soywiz.korio.async

import java.util.concurrent.*
import kotlin.coroutines.*
import kotlinx.coroutines.*

fun <T> Deferred<T>.jvmSyncAwait(): T = runBlocking { await() }
operator fun ExecutorService.invoke(callback: () -> Unit) {
    this.execute(callback)
}

private val mainDispatcher by lazy { newSingleThreadContext("mainDispatcher") }
internal val workerContext by lazy { newSingleThreadContext("worker") }
actual fun asyncEntryPoint(callback: suspend () -> Unit) =
    runBlocking(mainDispatcher) { callback() }

suspend fun <T> executeInWorkerJVM(callback: suspend () -> T): T = withContext(workerContext) { callback() }
