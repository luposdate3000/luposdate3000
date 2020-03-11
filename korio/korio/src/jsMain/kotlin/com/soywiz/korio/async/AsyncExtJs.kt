package com.soywiz.korio.async
import lupos.s04logicalOperators.ResultIterator

import kotlinx.coroutines.*
import kotlin.coroutines.*

actual fun asyncEntryPoint(callback: suspend () -> Unit): dynamic = kotlin.js.Promise<dynamic> { resolve, reject ->
	callback.startCoroutine(object : Continuation<Unit> {
		override val context: CoroutineContext = Dispatchers.Default
		override fun resumeWith(result: Result<Unit>) {
			val exception = result.exceptionOrNull()
			if (exception != null) {
				reject(exception)
			} else {
				//resolve(undefined)
				resolve(Unit)
			}
		}
	})
}
