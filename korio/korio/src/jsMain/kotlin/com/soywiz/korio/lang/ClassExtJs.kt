package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

import kotlin.reflect.*

actual val <T : Any> KClass<T>.portableSimpleName: String get() = simpleName ?: "unknown"
