package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

actual abstract class NativeThreadLocal<T> {
	actual abstract fun initialValue(): T

	val jthreadLocal = object : java.lang.ThreadLocal<T>() {
		override fun initialValue(): T = this@NativeThreadLocal.initialValue()
	}

	actual fun get(): T = jthreadLocal.get()
	actual fun set(value: T) = jthreadLocal.set(value)
}
