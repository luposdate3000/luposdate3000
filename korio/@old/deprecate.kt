package com.soywiz.korio.async
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.coroutines.*

@Deprecated("", ReplaceWith("coroutineContext", "kotlin.coroutines.coroutineContext"), level = DeprecationLevel.ERROR)
suspend fun getCoroutineContext() = coroutineContext