package lupos.s01io.buffer

import kotlin.jvm.JvmField


typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = String(chars)
