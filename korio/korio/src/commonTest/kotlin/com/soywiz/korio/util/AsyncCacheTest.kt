package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.klock.*
import com.soywiz.korio.async.*
import kotlin.test.*

class AsyncCacheTest {
	@Test
	fun test() = suspendTest {
		val cache = AsyncCache()
		assertEquals("a", cache("key1") { "a" })
		assertEquals("a", cache("key1") { "b" })
	}

	@Test
	fun test2() = suspendTest {
		var count = 0
		val cache = AsyncCacheGen { delay(1.milliseconds); "$it${count++}" }
		assertEquals("a0", cache("a"))
		assertEquals("a0", cache("a"))
		assertEquals("b1", cache("b"))
		assertEquals("b1", cache("b"))
	}
}