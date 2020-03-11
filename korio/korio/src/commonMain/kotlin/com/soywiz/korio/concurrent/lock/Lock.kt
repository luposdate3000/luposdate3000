package com.soywiz.korio.concurrent.lock
import lupos.s04logicalOperators.ResultIterator

internal expect class Lock() {
	inline operator fun <T> invoke(callback: () -> T): T
}
