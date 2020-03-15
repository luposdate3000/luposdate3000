package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.klock.*

actual val currentThreadId: Long get() = 1L

actual fun Thread_sleep(time: Long) {
	val start = PerformanceCounter.milliseconds
	while (PerformanceCounter.milliseconds - start < time) {
		// Spinlock
	}
}
