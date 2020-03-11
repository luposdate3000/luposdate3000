package com.soywiz.korio.vfs
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.*
import kotlin.test.*

class CommonResourcesVfsTest {
	@Test
	fun testCanReadResourceProperly() = suspendTest {
		//assertEquals("HELLO", resourcesVfs["resource.txt"].readString())
	}
}