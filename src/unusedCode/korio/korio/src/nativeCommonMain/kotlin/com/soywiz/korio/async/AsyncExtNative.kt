package com.soywiz.korio.async

import kotlinx.coroutines.*
import kotlin.coroutines.*

actual fun asyncEntryPoint(callback: suspend () -> Unit) = runBlocking { callback() }
