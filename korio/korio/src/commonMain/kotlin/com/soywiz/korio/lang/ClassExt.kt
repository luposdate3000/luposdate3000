package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

import kotlin.reflect.*

expect val <T : Any> KClass<T>.portableSimpleName: String
