package com.soywiz.korio.lang

inline fun assert(cond: Boolean) {
    if (!cond) throw AssertionError()
}
