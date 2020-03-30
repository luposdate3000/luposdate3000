package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query

typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = String(chars)
