package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.serialization.xml.*

fun String.htmlspecialchars() = Xml.Entities.encode(this)
