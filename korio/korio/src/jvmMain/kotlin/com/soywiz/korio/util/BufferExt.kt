package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import java.nio.*

fun ByteBuffer.toByteArray(): ByteArray {
	val out = ByteArray(this.limit())
	this.get(out, 0, out.size)
	return out
}