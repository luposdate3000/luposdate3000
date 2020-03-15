package com.soywiz.korio
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlinx.cinterop.*
import platform.posix.*

fun doMkdir(path: String, attr: Int): Int {
	return platform.posix.mkdir(path, attr.convert())
}

fun realpath(path: String): String = memScoped {
	val temp = allocArray<ByteVar>(PATH_MAX)
	realpath(path, temp)
	temp.toKString()
}

fun readlink(path: String): String? = memScoped {
	val addr = allocArray<ByteVar>(PATH_MAX)
	val finalSize = readlink(path, addr, PATH_MAX).toInt()
	if (finalSize < 0) null else addr.toKString()
}