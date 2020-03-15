package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

val Process.isAliveJre7: Boolean
	get() = try {
		exitValue()
		false
	} catch (e: IllegalThreadStateException) {
		true
	}
