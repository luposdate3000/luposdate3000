package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

val LONG_ZERO_TO_MAX_RANGE = 0L..Long.MAX_VALUE
fun IntRange.toLongRange() = this.start.toLong()..this.endInclusive.toLong()