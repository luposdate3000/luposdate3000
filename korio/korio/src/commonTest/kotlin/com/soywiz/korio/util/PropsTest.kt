package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.*
import com.soywiz.korio.file.std.*
import kotlin.test.*

class PropsTest {
	@Test
	fun test() = suspendTest {
		val vfs = MemoryVfsMix(
			"hello.properties" to """
    			hello=world
			""".trimIndent()
		)
		val props = vfs["hello.properties"].loadProperties()
		assertEquals("world", props["hello"])
		props["demo"] = "test"
		assertEquals("hello=world\ndemo=test", props.serialize())
		vfs["world.properties"].saveProperties(props)
		assertEquals("hello=world\ndemo=test", vfs["world.properties"].readString())
	}
}