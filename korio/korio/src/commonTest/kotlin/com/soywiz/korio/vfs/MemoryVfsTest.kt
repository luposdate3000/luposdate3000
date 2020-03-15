package com.soywiz.korio.vfs
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.*
import com.soywiz.korio.file.std.*
import kotlinx.coroutines.*
import kotlin.test.*

class MemoryVfsTest {
	@Test
	fun name() = suspendTest {
		val log = ArrayList<String>()
		val mem = MemoryVfs()

		mem.watch {
			log += it.toString()
		}

		mem["item.txt"].writeString("test")
		mem["test"].mkdir()
		mem["test"].delete()
		delay(100)
		assertEquals(
			"[MODIFIED(NodeVfs[/item.txt]), CREATED(NodeVfs[/test]), DELETED(NodeVfs[/test])]",
			log.toString()
		)
	}
}