package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

inline fun <T> buildList(callback: ArrayList<T>.() -> Unit): List<T> = arrayListOf<T>().apply(callback)
