package com.soywiz.korio.stream
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.*

class MemorySyncStreamTest {
	@Test
	fun name() {
		val v = MemorySyncStream(byteArrayOf(0, 0, 1, 0))
		assertEquals(0x100, v.readS32BE())
	}
}