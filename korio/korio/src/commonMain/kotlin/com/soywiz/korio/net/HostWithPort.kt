package com.soywiz.korio.net
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

data class HostWithPort(val host: String, val port: Int) {
	companion object {
		fun parse(str: String, defaultPort: Int): HostWithPort {
			val parts = str.split(':', limit = 2)
			return HostWithPort(parts[0], parts.getOrElse(1) { "$defaultPort" }.toInt())
		}
	}
}