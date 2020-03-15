package com.soywiz.korio.net.ws
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

actual suspend fun WebSocketClient(
	url: String,
	protocols: List<String>?,
	origin: String?,
	wskey: String?,
	debug: Boolean
): WebSocketClient = RawSocketWebSocketClient(url, protocols, origin, wskey, debug)
