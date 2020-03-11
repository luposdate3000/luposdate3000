package com.soywiz.korio.concurrent.lock
import lupos.s04logicalOperators.ResultIterator

internal actual class Lock actual constructor() {
	actual inline operator fun <T> invoke(callback: () -> T): T = callback()
}
