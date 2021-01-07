package com.soywiz.korio

import android.content.*
import com.soywiz.korio.android.withAndroidContext
import com.soywiz.korio.async.*
import kotlinx.coroutines.*

fun Korio(context: Context, entry: suspend CoroutineScope.() -> Unit) = asyncEntryPoint { withAndroidContext(context) { entry(CoroutineScope(coroutineContext)) } }
