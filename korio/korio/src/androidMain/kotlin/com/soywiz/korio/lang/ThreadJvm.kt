package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

actual val currentThreadId: Long get() = Thread.currentThread().id

actual fun Thread_sleep(time: Long) = Thread.sleep(time)
