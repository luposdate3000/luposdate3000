package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

internal actual val rawPlatformName: String = "jvm"
internal actual val rawOsName: String by lazy { System.getProperty("os.name") }
