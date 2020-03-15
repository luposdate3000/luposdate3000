package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlinx.cinterop.*

actual val currentThreadId: Long get() = 1L

actual fun Thread_sleep(time: Long): Unit {
	platform.posix.usleep((time * 1000L).convert())
}
