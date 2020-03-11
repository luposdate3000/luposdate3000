package com.soywiz.korio.util
import lupos.s04logicalOperators.ResultIterator

inline fun <T> buildList(callback: ArrayList<T>.() -> Unit): List<T> = arrayListOf<T>().apply(callback)
