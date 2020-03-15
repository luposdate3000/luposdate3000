package com.soywiz.korio.net.ws
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.async.*
import kotlin.test.*

class WebSocketTest {
	@Test
	@Ignore
	fun testWebSocket() = suspendTest {
		val ws = WebSocketClient("ws://echo.websocket.org")
		ws.send("hello")
		ws.send("world")
		assertEquals("hello", ws.readString())
		assertEquals("world", ws.readString())
		ws.close()
	}
}