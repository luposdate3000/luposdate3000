package com.soywiz.korio
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlinx.cinterop.*
import platform.posix.*

fun getCurrentExe(): String? = readlink("/proc/self/exe")
	?: readlink("/proc/curproc/file")
	?: readlink("/proc/self/path/a.out")

fun getCurrentExeFolder() = getCurrentExe()?.substringBeforeLast('/')

fun nativeCwd(): String = getCurrentExeFolder() ?: realpath(".") ?: "."
