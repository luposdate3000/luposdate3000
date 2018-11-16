package lupos.io.buffer

actual typealias Page = JVMByteArrayPage

actual inline fun createString(chars: CharArray):String = String(chars)
