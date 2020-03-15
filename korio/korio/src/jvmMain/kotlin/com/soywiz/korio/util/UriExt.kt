package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import java.net.*

fun URI.portWithDefault(default: Int) = if (this.port < 0) default else this.port