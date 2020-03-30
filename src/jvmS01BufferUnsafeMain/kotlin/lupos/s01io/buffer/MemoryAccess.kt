package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s01io.buffer.UnsafePage
import lupos.s04logicalOperators.Query

typealias Page = UnsafePage

inline fun createString(chars: CharArray): String = String(chars)
