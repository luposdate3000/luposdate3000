package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

import kotlin.test.*

class StringEscapeTest {
	@Test
	fun test() {
		assertEquals("\"hello\\nworld!\"", "hello\nworld!".quote())
		assertEquals("\\x1e", "\u001e".escape())
		assertEquals("\\u001e", "\u001e".uescape())
	}
}