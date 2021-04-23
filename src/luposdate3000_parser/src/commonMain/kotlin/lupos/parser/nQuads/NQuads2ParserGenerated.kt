/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.s02buildSyntaxTree.nQuads

import lupos.shared.IMyInputStream
import lupos.shared.Luposdate3000Exception
import kotlin.jvm.JvmField

internal open class ParserException(msg: String) : Luposdate3000Exception("ParserContext", msg)
internal class ParserExceptionEOF : ParserException("EOF")
internal class ParserExceptionUnexpectedChar(context: ParserContext) : ParserException("unexpected char 0x${context.c.toString(16)} at ${context.line}:${context.column}")
internal class ParserContext(@JvmField internal val input: IMyInputStream) {
    internal companion object {
        const val EOF = 0x7fffffff
    }

    @JvmField
    internal var c: Int = 0

    @JvmField
    internal var line = 1

    @JvmField
    internal var column = 0

    @JvmField
    internal val outBuffer = StringBuilder()

    @JvmField
    internal val inBuf = ByteArray(8192)

    @JvmField
    internal var inBufPosition = 0

    @JvmField
    internal var inBufSize = 0

    @JvmField
    internal var flagrN = false
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun clear() {
        outBuffer.clear()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun getValue(): String {
        return outBuffer.toString()
    }

    fun append() {
        if (c <= 0xd7ff || (c in 0xe000..0xffff)) {
            outBuffer.append(c.toChar())
            next()
        } else {
            c -= 0x100000
            outBuffer.append((0xd800 + ((c shr 10) and 0x03ff)).toChar())
            outBuffer.append((0xdc00 + (c and 0x03ff)).toChar())
            next()
        }
    }

    fun next() {
        if (inBufPosition >= inBufSize) {
            if (c == EOF) {
                throw ParserExceptionEOF()
            } else {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
        }
        val t: Int = inBuf[inBufPosition++].toInt() and 0xff
        if ((t and 0x80) == 0) {
            // 1byte
            c = t
            if ((c == '\r'.toInt()) || (c == '\n'.toInt())) {
                if (!flagrN) {
                    flagrN = true
                    line++
                    column = 1
                }
            } else {
                column++
                flagrN = false
            }
        } else if ((t and 0x20) == 0) {
            // 2byte
            flagrN = false
            c = (t and 0x1f) shl 6
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)
            column++
        } else if ((t and 0x10) == 0) {
            // 3byte
            flagrN = false
            c = (t and 0x0f) shl 12
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 6)
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)
            column++
        } else {
            // 4byte
            flagrN = false
            c = (t and 0x07) shl 18
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 12)
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or ((inBuf[inBufPosition++].toInt() and 0x3f) shl 6)
            if (inBufPosition >= inBufSize) {
                inBufSize = input.read(inBuf)
                inBufPosition = 0
                if (inBufSize <= 0) {
                    c = EOF
                    return
                }
            }
            c = c or (inBuf[inBufPosition++].toInt() and 0x3f)
            column++
        }
    }

    init {
        next()
    }
}

internal inline fun parse_dot(
    context: ParserContext,
    crossinline onDOT: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_dot_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onDOT()
                return
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_dot_helper_0(c: Int): Int {
    if (c == 0x2e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_ws(
    context: ParserContext,
    crossinline onSKIP_WS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        loop1@ while (true) {
            when (context.c) {
                0x9, 0xa, 0xd, 0x20 -> {
                    context.append()
                }
                else -> {
                    break@loop1
                }
            }
        }
        onSKIP_WS()
        return
    }
    throw ParserExceptionUnexpectedChar(context)
}

internal inline fun parse_ws_forced(
    context: ParserContext,
    crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_ws_forced_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x9, 0xa, 0xd, 0x20 -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onSKIP_WS_FORCED()
                return
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_ws_forced_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 1
    } else if (c <= 0xa) {
        return 0
    } else if (c < 0xd) {
        return 1
    } else if (c <= 0xd) {
        return 0
    } else if (c < 0x20) {
        return 1
    } else if (c <= 0x20) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_subject(
    context: ParserContext,
    crossinline onIRIREF: () -> Unit,
    crossinline onBLANK_NODE_LABEL: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_subject_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3d), in (0x3f..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_subject_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRIREF()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            1 -> {
                context.append()
                val localswitch3 = parse_subject_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_subject_helper_3(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        in (0x0..0x8), in (0xb..0xc), in (0xe..0x1f), in (0x21..0x1fffff) -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onBLANK_NODE_LABEL()
                                return
                            }
                            else -> {
                                break@error
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_subject_helper_0(c: Int): Int {
    if (c < 0x3c) {
        return 2
    } else if (c <= 0x3c) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 1
    } else {
        return 2
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_subject_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_subject_helper_2(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_subject_helper_3(c: Int): Int {
    if (c <= 0x8) {
        return 0
    } else if (c < 0xb) {
        return 1
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 1
    } else if (c <= 0x1f) {
        return 0
    } else if (c < 0x21) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_predicate(
    context: ParserContext,
    crossinline onIRIREF: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_predicate_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3d), in (0x3f..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_predicate_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRIREF()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_predicate_helper_0(c: Int): Int {
    if (c == 0x3c) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_predicate_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_object(
    context: ParserContext,
    crossinline onIRIREF: () -> Unit,
    crossinline onBLANK_NODE_LABEL: () -> Unit,
    crossinline onSTRING_LITERAL_QUOTE: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_object_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3d), in (0x3f..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_object_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRIREF()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            1 -> {
                context.append()
                val localswitch3 = parse_object_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_object_helper_3(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        in (0x0..0x8), in (0xb..0xc), in (0xe..0x1f), in (0x21..0x1fffff) -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onBLANK_NODE_LABEL()
                                return
                            }
                            else -> {
                                break@error
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_object_helper_4(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_object_helper_5(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop3
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_object_helper_6(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_object_helper_6(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_object_helper_6(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_object_helper_6(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    continue@loop3
                                                                }
                                                                else -> {
                                                                    break@error
                                                                }
                                                            }
                                                        }
                                                        else -> {
                                                            break@error
                                                        }
                                                    }
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                2 -> {
                                    context.append()
                                    val localswitch8 = parse_object_helper_6(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_object_helper_6(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_object_helper_6(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_object_helper_6(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_object_helper_6(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_object_helper_6(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_object_helper_6(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_object_helper_6(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    continue@loop3
                                                                                                }
                                                                                                else -> {
                                                                                                    break@error
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                        else -> {
                                                                                            break@error
                                                                                        }
                                                                                    }
                                                                                }
                                                                                else -> {
                                                                                    break@error
                                                                                }
                                                                            }
                                                                        }
                                                                        else -> {
                                                                            break@error
                                                                        }
                                                                    }
                                                                }
                                                                else -> {
                                                                    break@error
                                                                }
                                                            }
                                                        }
                                                        else -> {
                                                            break@error
                                                        }
                                                    }
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                else -> {
                                    break@error
                                }
                            }
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_object_helper_7(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onSTRING_LITERAL_QUOTE()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_0(c: Int): Int {
    if (c < 0x22) {
        return 3
    } else if (c <= 0x22) {
        return 2
    } else if (c < 0x3c) {
        return 3
    } else if (c <= 0x3c) {
        return 0
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 1
    } else {
        return 3
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_2(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_3(c: Int): Int {
    if (c <= 0x8) {
        return 0
    } else if (c < 0xb) {
        return 1
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 1
    } else if (c <= 0x1f) {
        return 0
    } else if (c < 0x21) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal fun parse_object_helper_4(c: Int): Int {
    if (c <= 0x9) {
        return 0
    } else if (c < 0xb) {
        return 2
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal fun parse_object_helper_5(c: Int): Int {
    if (c < 0x22) {
        return 3
    } else if (c <= 0x22) {
        return 0
    } else if (c < 0x27) {
        return 3
    } else if (c <= 0x27) {
        return 0
    } else if (c < 0x55) {
        return 3
    } else if (c <= 0x55) {
        return 2
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 0
    } else if (c < 0x62) {
        return 3
    } else if (c <= 0x62) {
        return 0
    } else if (c < 0x66) {
        return 3
    } else if (c <= 0x66) {
        return 0
    } else if (c < 0x6e) {
        return 3
    } else if (c <= 0x6e) {
        return 0
    } else if (c < 0x72) {
        return 3
    } else if (c <= 0x72) {
        return 0
    } else if (c < 0x74) {
        return 3
    } else if (c <= 0x74) {
        return 0
    } else if (c < 0x75) {
        return 3
    } else if (c <= 0x75) {
        return 1
    } else {
        return 3
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_6(c: Int): Int {
    if (c < 0x30) {
        return 1
    } else if (c <= 0x39) {
        return 0
    } else if (c < 0x41) {
        return 1
    } else if (c <= 0x46) {
        return 0
    } else if (c < 0x61) {
        return 1
    } else if (c <= 0x66) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_helper_7(c: Int): Int {
    if (c == 0x22) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_object_string(
    context: ParserContext,
    crossinline onIRI1: () -> Unit,
    crossinline onLANGTAG: () -> Unit,
    crossinline onSKIP_WS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_object_string_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                val localswitch3 = parse_object_string_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRI1()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            1 -> {
                context.append()
                val localswitch3 = parse_object_string_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            when (context.c) {
                                0x2c, 0x2d, 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x5f, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                                    context.append()
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        onLANGTAG()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x9, 0xa, 0xd, 0x20 -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onSKIP_WS()
                return
            }
            else -> {
                onSKIP_WS()
                return
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

internal fun parse_object_string_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 3
    } else if (c <= 0xa) {
        return 2
    } else if (c < 0xd) {
        return 3
    } else if (c <= 0xd) {
        return 2
    } else if (c < 0x20) {
        return 3
    } else if (c <= 0x20) {
        return 2
    } else if (c < 0x40) {
        return 3
    } else if (c <= 0x40) {
        return 1
    } else if (c < 0x5e) {
        return 3
    } else if (c <= 0x5e) {
        return 0
    } else {
        return 3
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_string_helper_1(c: Int): Int {
    if (c == 0x5e) {
        return 0
    } else {
        return 1
    }
}

internal fun parse_object_string_helper_2(c: Int): Int {
    if (c < 0x2c) {
        return 1
    } else if (c <= 0x2d) {
        return 0
    } else if (c < 0x30) {
        return 1
    } else if (c <= 0x39) {
        return 0
    } else if (c < 0x41) {
        return 1
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5f) {
        return 1
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 1
    } else if (c <= 0x7a) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_object_typed(
    context: ParserContext,
    crossinline onIRIREF: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_object_typed_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3d), in (0x3f..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_object_typed_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRIREF()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_typed_helper_0(c: Int): Int {
    if (c == 0x3c) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_object_typed_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_graph(
    context: ParserContext,
    crossinline onIRIREF: () -> Unit,
    crossinline onBLANK_NODE_LABEL: () -> Unit,
    crossinline onSKIP_WS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_graph_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3d), in (0x3f..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_graph_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onIRIREF()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            1 -> {
                context.append()
                val localswitch3 = parse_graph_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_graph_helper_3(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        in (0x0..0x8), in (0xb..0xc), in (0xe..0x1f), in (0x21..0x1fffff) -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onBLANK_NODE_LABEL()
                                return
                            }
                            else -> {
                                break@error
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x9, 0xa, 0xd, 0x20 -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onSKIP_WS()
                return
            }
            else -> {
                onSKIP_WS()
                return
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

internal fun parse_graph_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 3
    } else if (c <= 0xa) {
        return 2
    } else if (c < 0xd) {
        return 3
    } else if (c <= 0xd) {
        return 2
    } else if (c < 0x20) {
        return 3
    } else if (c <= 0x20) {
        return 2
    } else if (c < 0x3c) {
        return 3
    } else if (c <= 0x3c) {
        return 0
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 1
    } else {
        return 3
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_graph_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_graph_helper_2(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_graph_helper_3(c: Int): Int {
    if (c <= 0x8) {
        return 0
    } else if (c < 0xb) {
        return 1
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 1
    } else if (c <= 0x1f) {
        return 0
    } else if (c < 0x21) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}
