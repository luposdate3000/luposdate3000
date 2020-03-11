package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.file.*
import java.net.*

val URL.basename: String get() = PathInfo(this.file).baseName