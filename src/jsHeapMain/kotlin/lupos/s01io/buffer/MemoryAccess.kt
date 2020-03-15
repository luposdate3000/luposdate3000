package lupos.s01io.buffer

import kotlin.jvm.JvmField
import lupos.s01io.buffer.ByteArrayPage
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


typealias Page = ByteArrayPage

inline fun createString(chars: CharArray): String = chars.joinToString("")
// todo: more efficient way?
