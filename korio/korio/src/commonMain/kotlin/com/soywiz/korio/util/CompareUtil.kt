package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

inline fun Int.compareToChain(callback: () -> Int): Int = if (this != 0) this else callback()
