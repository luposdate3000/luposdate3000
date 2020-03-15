package com.soywiz.korio
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import kotlinx.cinterop.*
import platform.posix.*

fun nativeCwd(): String = kotlinx.cinterop.autoreleasepool { platform.Foundation.NSBundle.mainBundle.resourcePath ?: realpath(".") ?: "." }
