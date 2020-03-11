package com.soywiz.korio.file.std
import lupos.s04logicalOperators.ResultIterator

import java.io.*

suspend fun ByteArray.writeToFile(file: File) = localVfs(file).write(this)
