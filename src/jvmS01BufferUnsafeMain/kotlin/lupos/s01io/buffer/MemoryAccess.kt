package lupos.s01io.buffer
import lupos.s00misc.Coverage
import lupos.s01io.buffer.UnsafePage
typealias Page = UnsafePage
inline fun createString(chars: CharArray): String = String(chars)
