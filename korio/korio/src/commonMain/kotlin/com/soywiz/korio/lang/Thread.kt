package com.soywiz.korio.lang
import lupos.s04logicalOperators.ResultIterator

import com.soywiz.korio.*

expect val currentThreadId: Long

expect fun Thread_sleep(time: Long): Unit
