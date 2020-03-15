package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

expect object Environment {
	// Uses querystring on JS/Browser, and proper env vars in the rest
	operator fun get(key: String): String?

	fun getAll(): Map<String, String>
}
