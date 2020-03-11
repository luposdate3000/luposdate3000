package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

val Process.isAliveJre7: Boolean
	get() = try {
		exitValue()
		false
	} catch (e: IllegalThreadStateException) {
		true
	}
