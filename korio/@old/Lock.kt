package com.soywiz.korio.concurrent
import lupos.s04logicalOperators.ResultIterator

expect class Lock() {
	inline operator fun <T> invoke(callback: () -> T): T
}

// NATIVE:
// In JS and Native this is not required
//actual class Lock actual constructor() {
//	actual inline operator fun <T> invoke(callback: () -> T): T = callback()
//}

// JS
// In JS and Native this is not required
//actual class Lock actual constructor() {
//	actual inline operator fun <T> invoke(callback: () -> T): T = callback()
//}

// JVM
//actual class Lock actual constructor() {
//	actual inline operator fun <T> invoke(callback: () -> T): T = synchronized(this) { callback() }
//}
