package com.soywiz.korio.async
import lupos.s04logicalOperators.ResultIterator

import kotlinx.coroutines.*

actual fun asyncEntryPoint(callback: suspend () -> Unit) {
	CoroutineScope(Dispatchers.Main).launch {
		callback()
	}
}

