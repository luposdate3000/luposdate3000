package lupos.s00misc

import kotlin.experimental.and
import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query

inline fun Byte.bit0(): Boolean = (this and 1) > 0
inline fun Byte.bit1(): Boolean = (this and 2) > 0
inline fun Byte.bit2(): Boolean = (this and 4) > 0
inline fun Byte.bit3(): Boolean = (this and 8) > 0
inline fun Byte.bit4(): Boolean = (this and 16) > 0
inline fun Byte.bit5(): Boolean = (this and 32) > 0
inline fun Byte.bit6(): Boolean = (this and 64) > 0
inline fun Byte.bit7(): Boolean = (this and 128.toByte()) > 0
