package lupos.s01io.buffer
typealias Page = ByteArrayPage
inline fun createString(chars: CharArray): String = chars.joinToString("")
// todo: more efficient way?
