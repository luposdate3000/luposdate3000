package com.soywiz.korio.util
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04logicalOperators.ResultIterator

import java.lang.reflect.*

val Class<*>.allDeclaredFields: List<Field>
	get() = this.declaredFields.toList() + (this.superclass?.allDeclaredFields?.toList() ?: listOf<Field>())

fun Class<*>.isSubtypeOf(that: Class<*>) = that.isAssignableFrom(this)

val Class<*>.allDeclaredMethods: List<Method>
	get() = this.declaredMethods.toList() + (this.superclass?.allDeclaredMethods?.toList() ?: listOf<Method>())
