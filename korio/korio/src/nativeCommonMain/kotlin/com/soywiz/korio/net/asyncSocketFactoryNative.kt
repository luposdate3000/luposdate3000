package com.soywiz.korio.net
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

internal actual val asyncSocketFactory: AsyncSocketFactory = NativeAsyncSocketFactory
