package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

actual val currentThreadId: Long get() = Thread.currentThread().id

actual fun Thread_sleep(time: Long) = Thread.sleep(time)
