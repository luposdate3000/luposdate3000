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
package lupos.shared.xmlParser

import lupos.s00misc.IMyInputStream
import lupos.s00misc.Luposdate3000Exception
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

internal inline fun parse_element_start(
    context: ParserContext,
    crossinline onELEMENT_START: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_element_start_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onELEMENT_START()
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
internal inline fun parse_element_start_helper_0(c: Int): Int {
    if (c == 0x3c) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_element_tag_or_immediate_close_char(
    context: ParserContext,
    crossinline onELEMENT_END_PART: () -> Unit,
    crossinline onTAG: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_element_tag_or_immediate_close_char_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onELEMENT_END_PART()
                return
            }
            1 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onTAG()
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
internal inline fun parse_element_tag_or_immediate_close_char_helper_0(c: Int): Int {
    if (c < 0x2f) {
        return 2
    } else if (c <= 0x2f) {
        return 0
    } else if (c < 0x41) {
        return 2
    } else if (c <= 0x5a) {
        return 1
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 1
    } else {
        return 2
    }
}

internal inline fun parse_element_tag(
    context: ParserContext,
    crossinline onTAG: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_element_tag_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onTAG()
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
internal inline fun parse_element_tag_helper_0(c: Int): Int {
    if (c < 0x41) {
        return 1
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x61) {
        return 1
    } else if (c <= 0x7a) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_element_close(
    context: ParserContext,
    crossinline onELEMENT_CLOSE_LATER: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_element_close_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onELEMENT_CLOSE_LATER()
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
internal inline fun parse_element_close_helper_0(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_attribute_or_close_tag(
    context: ParserContext,
    crossinline onATTRIBUTE_NAME: () -> Unit,
    crossinline onELEMENT_CLOSE_IMMEDIATELY: () -> Unit,
    crossinline onELEMENT_CLOSE_LATER: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_attribute_or_close_tag_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onATTRIBUTE_NAME()
                return
            }
            1 -> {
                context.append()
                val localswitch3 = parse_attribute_or_close_tag_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onELEMENT_CLOSE_IMMEDIATELY()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                onELEMENT_CLOSE_LATER()
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
internal inline fun parse_attribute_or_close_tag_helper_0(c: Int): Int {
    if (c < 0x2f) {
        return 3
    } else if (c <= 0x2f) {
        return 1
    } else if (c < 0x3e) {
        return 3
    } else if (c <= 0x3e) {
        return 2
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 0
    } else {
        return 3
    }
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_attribute_or_close_tag_helper_1(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_attribute_assinment(
    context: ParserContext,
    crossinline onATTRIBUTE_ASSIGNMENT: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_attribute_assinment_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onATTRIBUTE_ASSIGNMENT()
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
internal inline fun parse_attribute_assinment_helper_0(c: Int): Int {
    if (c == 0x3d) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_attribute_value(
    context: ParserContext,
    crossinline onATTRIBUTE_VALUE: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_attribute_value_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x21), in (0x23..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_attribute_value_helper_0(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onATTRIBUTE_VALUE()
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
internal inline fun parse_attribute_value_helper_0(c: Int): Int {
    if (c == 0x22) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_content_or_child(
    context: ParserContext,
    crossinline onELEMENT_CONTENT: () -> Unit,
    crossinline onELEMENT_START: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_content_or_child_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        in (0x0..0x3b), in (0x3d..0x1fffff) -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                onELEMENT_CONTENT()
                return
            }
            1 -> {
                context.append()
                onELEMENT_START()
                return
            }
            else -> {
                onELEMENT_CONTENT()
                return
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

@Suppress("NOTHING_TO_INLINE")
internal inline fun parse_content_or_child_helper_0(c: Int): Int {
    if (c <= 0x3b) {
        return 0
    } else if (c < 0x3c) {
        return 2
    } else if (c <= 0x3c) {
        return 1
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}
