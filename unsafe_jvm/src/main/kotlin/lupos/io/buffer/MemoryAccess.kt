package lupos.io.buffer

actual typealias Page = UnsafePage

actual inline fun createString(chars: CharArray): String = String(chars)
