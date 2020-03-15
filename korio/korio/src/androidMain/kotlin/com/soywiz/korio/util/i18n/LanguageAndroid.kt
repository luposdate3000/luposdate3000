package com.soywiz.korio.util.i18n
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import java.util.*

internal actual val systemLanguageStrings get() = listOf(Locale.getDefault().isO3Language)
