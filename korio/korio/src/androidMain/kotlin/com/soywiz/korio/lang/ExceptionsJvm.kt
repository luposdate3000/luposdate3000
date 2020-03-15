package com.soywiz.korio.lang
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

actual typealias IOException = java.io.IOException
actual typealias EOFException = java.io.EOFException
actual typealias FileNotFoundException = java.io.FileNotFoundException

actual fun Throwable.printStackTrace() {
	this.printStackTrace()
}

actual fun enterDebugger(): Unit {
	println("enterDebugger")
}
