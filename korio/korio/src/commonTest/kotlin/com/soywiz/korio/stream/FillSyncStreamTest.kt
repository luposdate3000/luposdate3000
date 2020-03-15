package com.soywiz.korio.stream
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.*

class FillSyncStreamTest {
	@Test
	fun name() {
		assertEquals(0, FillSyncStream(0).readS8())
		assertEquals(-1, FillSyncStream(0xFF).readS8())
		assertEquals(-1, FillSyncStream(0xFF).readS16LE())
		assertEquals(-1, FillSyncStream(0xFF).readS16BE())
	}
}