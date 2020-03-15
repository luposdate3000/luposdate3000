package com.soywiz.korio.net.http
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

internal actual val httpFactory: HttpFactory by lazy {
	object : HttpFactory {
		init {
			System.setProperty("http.keepAlive", "false")
		}

		//override fun createClient(): HttpClient = HttpClientJvm()
		override fun createClient(): HttpClient = HttpPortable.createClient()

		override fun createServer(): HttpServer = HttpPortable.createServer()
	}
}

