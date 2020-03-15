package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

inline fun <T, R : Any> Iterable<T>.computeRle(callback: (T) -> R): List<Pair<R, IntRange>> {
	var first = true
	var pos = 0
	var startpos = 0
	lateinit var lastRes: R
	val out = arrayListOf<Pair<R, IntRange>>()
	for (it in this) {
		val current = callback(it)
		if (!first) {
			if (current != lastRes) {
				out += lastRes to (startpos until pos)
				startpos = pos
			}
		}
		lastRes = current
		first = false
		pos++
	}
	if (startpos != pos) {
		out += lastRes to (startpos until pos)
	}
	return out
}
