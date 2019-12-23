package lupos.io.buffer

actual typealias Page = ByteArrayPage

actual inline fun createString(chars: CharArray):String = String(chars)
