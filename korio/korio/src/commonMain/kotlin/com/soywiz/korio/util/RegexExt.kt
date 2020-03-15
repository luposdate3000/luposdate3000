package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.lang.*

//fun Regex.Companion.quote(str: String): String = str.replace(Regex("[.?*+^\$\\[\\]\\\\(){}|\\-]")) { "\\${it.value}" }

fun Regex.Companion.quote(str: String): String = str.eachBuilder { c ->
	when (c) {
		'.', '?', '*', '+', '^', '\\', '$', '[', ']', '(', ')', '{', '}', '|', '-' -> append('\\')
	}
	append(c)
}
