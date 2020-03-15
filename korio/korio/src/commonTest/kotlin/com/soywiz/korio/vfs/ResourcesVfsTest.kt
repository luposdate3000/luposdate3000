package com.soywiz.korio.vfs
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.*
import com.soywiz.korio.file.*
import com.soywiz.korio.file.std.*
import kotlinx.coroutines.channels.*
import kotlin.test.*

class ResourcesVfsTest {
	@Test
	@Ignore
	fun name() = suspendTest {
		println("[A]")
		val listing = resourcesVfs["tresfolder"].list()
		println("[B]")

		for (v in resourcesVfs["tresfolder"].list().filter { it.extensionLC == "txt" }.toList()) {
			println(v)
		}

		assertEquals(
			"[a.txt, b.txt]",
			resourcesVfs["tresfolder"].list().filter { it.extensionLC == "txt" }.toList().map { it.baseName }.sorted().toString()
		)
	}
}
