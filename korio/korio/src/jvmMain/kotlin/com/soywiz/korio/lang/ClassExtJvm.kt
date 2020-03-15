package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.reflect.*

actual val <T : Any> KClass<T>.portableSimpleName: String get() = java.simpleName
