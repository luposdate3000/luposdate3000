package com.soywiz.korio.concurrent.atomic
import lupos.s04logicalOperators.ResultIterator

actual fun <T> korAtomic(initial: T): KorAtomicRef<T> =
	KorAtomicRef(initial)
actual fun korAtomic(initial: Boolean): KorAtomicBoolean =
	KorAtomicBoolean(initial)
actual fun korAtomic(initial: Int): KorAtomicInt =
	KorAtomicInt(initial)
actual fun korAtomic(initial: Long): KorAtomicLong =
	KorAtomicLong(initial)
