package com.soywiz.korio.compression.deflate
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.compression.*

actual fun Deflate(windowBits: Int): CompressionMethod = DeflatePortable(windowBits)
