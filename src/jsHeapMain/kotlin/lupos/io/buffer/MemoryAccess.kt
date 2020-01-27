package lupos.io.buffer
import lupos.s01io.buffer.PageHelper
import lupos.s01io.buffer.ByteArrayPage
import lupos.s01io.buffer.Benchmark

typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = chars.joinToString("")
// todo: more efficient way?
