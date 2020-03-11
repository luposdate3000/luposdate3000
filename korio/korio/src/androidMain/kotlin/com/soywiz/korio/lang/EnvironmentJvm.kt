package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

actual object Environment {
	// Uses querystring on JS/Browser, and proper env vars in the rest
	actual operator fun get(key: String): String? = System.getenv(key)
	actual fun getAll(): Map<String, String> = System.getenv()
}
