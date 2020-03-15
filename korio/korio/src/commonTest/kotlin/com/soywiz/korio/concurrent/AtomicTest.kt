package com.soywiz.korio.concurrent
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.concurrent.atomic.*
import kotlin.test.*

class AtomicTest {
	@Test
	fun test() {
		val value = KorAtomicInt(0)
		assertEquals(1, value.incrementAndGet())
		assertEquals(1, value.value++)
		assertEquals(2, value.value)
		assertEquals(3, ++value.value)
	}
}