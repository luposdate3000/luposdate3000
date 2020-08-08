package lupos.s01io.buffer

import lupos.s01io.buffer.ByteArrayPage

typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = chars.joinToString("")
// todo: more efficient way?
