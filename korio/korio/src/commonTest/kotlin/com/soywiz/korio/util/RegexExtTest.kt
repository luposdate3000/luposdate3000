package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.*

class RegexExtTest {
	@Test
	fun test() {
		assertEquals("""\*\+""", Regex.quote("*+"))
		assertEquals("""\.\?\*\+\^\\\[\]\(\)\{\}\|\-\${'$'}""", Regex.quote(""".?*+^\[](){}|-${'$'}"""))

		assertEquals("""abc\[a\]""", Regex.quote("abc[a]"))


		// @TODO: Inconsistent among targets! JS/JVM
		//assertEquals("""\Q*+\E""", Regex.escape("*+"))
		//assertEquals("""*+""", Regex.escapeReplacement("*+"))
		//assertEquals("""*+""", Regex.fromLiteral("*+").pattern)
	}
}