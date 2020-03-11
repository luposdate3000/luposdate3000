package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

inline fun assert(cond: Boolean): Unit {
	if (!cond) throw AssertionError()
}