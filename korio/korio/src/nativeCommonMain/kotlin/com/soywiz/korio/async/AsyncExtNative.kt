package com.soywiz.korio.async
import lupos.s04logicalOperators.ResultIterator

import kotlin.coroutines.*
import kotlinx.coroutines.*

actual fun asyncEntryPoint(callback: suspend () -> Unit) = runBlocking { callback() }
