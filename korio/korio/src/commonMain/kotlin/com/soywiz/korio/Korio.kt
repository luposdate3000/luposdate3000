package com.soywiz.korio

import com.soywiz.korio.async.*
import com.soywiz.korio.internal.*
import kotlin.coroutines.*
import kotlinx.coroutines.*

fun Korio(entry: suspend CoroutineScope.() -> Unit) = asyncEntryPoint { entry(CoroutineScope(coroutineContext)) }

object Korio {
    val VERSION = KORIO_VERSION
}
