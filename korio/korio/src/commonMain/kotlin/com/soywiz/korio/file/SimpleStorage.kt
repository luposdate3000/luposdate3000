package com.soywiz.korio.file
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

interface SimpleStorage {
	suspend fun get(key: String): String?
	suspend fun set(key: String, value: String)
	suspend fun remove(key: String)
}
