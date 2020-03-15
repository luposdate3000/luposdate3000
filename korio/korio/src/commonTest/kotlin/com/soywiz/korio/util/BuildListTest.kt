package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.*

class BuildListTest {
	@Test
	fun test() {
		assertEquals(listOf("a", "b"), buildList { add("a"); add("b") })
	}
}