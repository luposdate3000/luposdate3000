package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.Test

class EnvironmentTest {
	@Test
	fun test() {
		println(Environment["path"])
		println(Environment["PATH"])
		println(Environment.getAll())
	}
}