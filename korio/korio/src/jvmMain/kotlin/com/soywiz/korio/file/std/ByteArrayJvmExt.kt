package com.soywiz.korio.file.std
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import java.io.*

suspend fun ByteArray.writeToFile(file: File) = localVfs(file).write(this)
