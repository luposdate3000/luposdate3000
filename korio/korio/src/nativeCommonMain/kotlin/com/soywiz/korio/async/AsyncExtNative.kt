package com.soywiz.korio.async
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.coroutines.*
import kotlinx.coroutines.*

actual fun asyncEntryPoint(callback: suspend () -> Unit) = runBlocking { callback() }
