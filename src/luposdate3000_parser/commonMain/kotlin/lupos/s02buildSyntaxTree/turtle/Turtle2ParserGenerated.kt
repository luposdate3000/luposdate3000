package lupos.s02buildSyntaxTree.turtle

import kotlin.jvm.JvmField
import lupos.s00misc.IMyInputStream
import lupos.s00misc.Luposdate3000Exception

open class ParserException(msg: String) : Luposdate3000Exception("ParserContext", msg)
internal class ParserExceptionEOF() : ParserException("EOF")
internal class ParserExceptionUnexpectedChar(context: ParserContext) : ParserException("unexpected char 0x${context.c.toString(16)} at ${context.line}:${context.column}")
internal class ParserContext(@JvmField val input: IMyInputStream) {
    internal companion object {
        const val EOF = 0x7fffffff.toInt()
    }

    @JvmField
    var c: Int = 0

    @JvmField
    var line = 1

    @JvmField
    var column = 0

    @JvmField
    val outBuffer = StringBuilder()

    @JvmField
    val inBuf = ByteArray(8192)

    @JvmField
    var inBufPosition = 0

    @JvmField
    var inBufSize = 0

    @JvmField
    var flagR_N = false
    inline fun clear() {
        outBuffer.clear()
    }

    inline fun getValue(): String {
        return outBuffer.toString()
    }

    fun append() {
        if (c <= 0xd7ff || (c >= 0xe000 && c <= 0xffff)) {
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
            //1byte
            c = t
            if ((c == '\r'.toInt()) || (c == '\n'.toInt())) {
                if (!flagR_N) {
                    flagR_N = true
                    line++
                    column = 1
                }
            } else {
                column++
                flagR_N = false
            }
        } else if ((t and 0x20) == 0) {
            //2byte
            flagR_N = false
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
            //3byte
            flagR_N = false
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
            //4byte
            flagR_N = false
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

internal inline fun parse_dot(context: ParserContext,
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

internal inline fun parse_dot_helper_0(c: Int): Int {
    if (c == 0x2e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_ws(context: ParserContext,
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

internal inline fun parse_ws_forced(context: ParserContext,
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

internal inline fun parse_statement(context: ParserContext,
                                    crossinline onBASE: () -> Unit,
                                    crossinline onPREFIX: () -> Unit,
                                    crossinline onBASE2: () -> Unit,
                                    crossinline onPREFIX2: () -> Unit,
                                    crossinline onIRIREF: () -> Unit,
                                    crossinline onPNAME_NS: () -> Unit,
                                    crossinline onBLANK_NODE_LABEL: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_statement_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                val localswitch3 = parse_statement_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_statement_helper_2(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_statement_helper_3(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        onBASE()
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
            1 -> {
                context.append()
                val localswitch3 = parse_statement_helper_4(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_statement_helper_3(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_statement_helper_5(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_statement_helper_6(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_statement_helper_7(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        onPREFIX()
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
                val localswitch3 = parse_statement_helper_8(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_statement_helper_9(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_statement_helper_10(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_statement_helper_11(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                onBASE2()
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
                    1 -> {
                        context.append()
                        val localswitch5 = parse_statement_helper_12(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_statement_helper_11(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_statement_helper_13(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_statement_helper_14(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_statement_helper_15(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                onPREFIX2()
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
            3 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_statement_helper_16(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_statement_helper_17(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_statement_helper_18(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_statement_helper_18(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_statement_helper_18(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_statement_helper_18(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_statement_helper_18(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_statement_helper_18(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_statement_helper_18(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_statement_helper_18(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_statement_helper_18(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_statement_helper_18(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_statement_helper_18(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_statement_helper_18(context.c)
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
                val localswitch3 = parse_statement_helper_19(context.c)
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
            4 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_statement_helper_20(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_statement_helper_21(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onPNAME_NS()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            5 -> {
                context.append()
                onPNAME_NS()
                return
            }
            6 -> {
                context.append()
                val localswitch3 = parse_statement_helper_21(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_statement_helper_22(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_statement_helper_20(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
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

fun parse_statement_helper_0(c: Int): Int {
    if (c < 0x3a) {
        return 7
    } else if (c <= 0x3a) {
        return 5
    } else if (c < 0x3c) {
        return 7
    } else if (c <= 0x3c) {
        return 3
    } else if (c < 0x40) {
        return 7
    } else if (c <= 0x40) {
        return 2
    } else if (c < 0x41) {
        return 7
    } else if (c <= 0x5a) {
        return 4
    } else if (c <= 0x42) {
        return 0
    } else if (c < 0x50) {
        return 7
    } else if (c <= 0x50) {
        return 1
    } else if (c < 0x5f) {
        return 7
    } else if (c <= 0x5f) {
        return 6
    } else if (c < 0x61) {
        return 7
    } else if (c <= 0x7a) {
        return 4
    } else if (c < 0xc0) {
        return 7
    } else if (c <= 0xd6) {
        return 4
    } else if (c < 0xd8) {
        return 7
    } else if (c <= 0xf6) {
        return 4
    } else if (c < 0xf8) {
        return 7
    } else if (c <= 0x2ff) {
        return 4
    } else if (c < 0x370) {
        return 7
    } else if (c <= 0x37d) {
        return 4
    } else if (c < 0x37f) {
        return 7
    } else if (c <= 0x1fff) {
        return 4
    } else if (c < 0x200c) {
        return 7
    } else if (c <= 0x200d) {
        return 4
    } else if (c < 0x2070) {
        return 7
    } else if (c <= 0x218f) {
        return 4
    } else if (c < 0x2c00) {
        return 7
    } else if (c <= 0x2fef) {
        return 4
    } else if (c < 0x3001) {
        return 7
    } else if (c <= 0xd7ff) {
        return 4
    } else if (c < 0xf900) {
        return 7
    } else if (c <= 0xfdcf) {
        return 4
    } else if (c < 0xfdf0) {
        return 7
    } else if (c <= 0xfffd) {
        return 4
    } else if (c < 0x10000) {
        return 7
    } else if (c <= 0x1fffff) {
        return 4
    } else {
        return 7
    }
}

internal inline fun parse_statement_helper_1(c: Int): Int {
    if (c == 0x41) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_2(c: Int): Int {
    if (c == 0x53) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_3(c: Int): Int {
    if (c == 0x45) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_4(c: Int): Int {
    if (c == 0x52) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_5(c: Int): Int {
    if (c == 0x46) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_6(c: Int): Int {
    if (c == 0x49) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_7(c: Int): Int {
    if (c == 0x58) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_8(c: Int): Int {
    if (c < 0x62) {
        return 2
    } else if (c <= 0x62) {
        return 0
    } else if (c < 0x70) {
        return 2
    } else if (c <= 0x70) {
        return 1
    } else {
        return 2
    }
}

internal inline fun parse_statement_helper_9(c: Int): Int {
    if (c == 0x61) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_10(c: Int): Int {
    if (c == 0x73) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_11(c: Int): Int {
    if (c == 0x65) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_12(c: Int): Int {
    if (c == 0x72) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_13(c: Int): Int {
    if (c == 0x66) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_14(c: Int): Int {
    if (c == 0x69) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_15(c: Int): Int {
    if (c == 0x78) {
        return 0
    } else {
        return 1
    }
}

fun parse_statement_helper_16(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_statement_helper_17(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_statement_helper_18(c: Int): Int {
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

internal inline fun parse_statement_helper_19(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

fun parse_statement_helper_20(c: Int): Int {
    if (c < 0x2d) {
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
    } else if (c < 0xb7) {
        return 1
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 1
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_statement_helper_21(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

fun parse_statement_helper_22(c: Int): Int {
    if (c < 0x30) {
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
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_base(context: ParserContext,
                               crossinline onIRIREF: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_base_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_base_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_base_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_base_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_base_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_base_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_base_helper_3(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_base_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_base_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_base_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_base_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_base_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_base_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_base_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_base_helper_3(context.c)
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
                val localswitch3 = parse_base_helper_4(context.c)
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

internal inline fun parse_base_helper_0(c: Int): Int {
    if (c == 0x3c) {
        return 0
    } else {
        return 1
    }
}

fun parse_base_helper_1(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_base_helper_2(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_base_helper_3(c: Int): Int {
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

internal inline fun parse_base_helper_4(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_prefix(context: ParserContext,
                                 crossinline onPNAME_NS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_prefix_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_prefix_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_prefix_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onPNAME_NS()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            1 -> {
                context.append()
                onPNAME_NS()
                return
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

fun parse_prefix_helper_0(c: Int): Int {
    if (c < 0x3a) {
        return 2
    } else if (c <= 0x3a) {
        return 1
    } else if (c < 0x41) {
        return 2
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xc0) {
        return 2
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 2
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 2
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 2
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 2
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 2
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 2
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 2
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 2
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 2
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 2
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

fun parse_prefix_helper_1(c: Int): Int {
    if (c < 0x2d) {
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
    } else if (c < 0xb7) {
        return 1
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 1
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_prefix_helper_2(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_prefix2(context: ParserContext,
                                  crossinline onIRIREF: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_prefix2_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_prefix2_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_prefix2_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_prefix2_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_prefix2_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_prefix2_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_prefix2_helper_3(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_prefix2_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_prefix2_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_prefix2_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_prefix2_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_prefix2_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_prefix2_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_prefix2_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_prefix2_helper_3(context.c)
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
                val localswitch3 = parse_prefix2_helper_4(context.c)
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

internal inline fun parse_prefix2_helper_0(c: Int): Int {
    if (c == 0x3c) {
        return 0
    } else {
        return 1
    }
}

fun parse_prefix2_helper_1(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_prefix2_helper_2(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_prefix2_helper_3(c: Int): Int {
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

internal inline fun parse_prefix2_helper_4(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_predicate(context: ParserContext,
                                    crossinline onVERB1: () -> Unit,
                                    crossinline onIRIREF: () -> Unit,
                                    crossinline onPNAME_NS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_predicate_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onVERB1()
                return
            }
            1 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_predicate_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_predicate_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_predicate_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_predicate_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_predicate_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_predicate_helper_3(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_predicate_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_predicate_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_predicate_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_predicate_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_predicate_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_predicate_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_predicate_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_predicate_helper_3(context.c)
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
                val localswitch3 = parse_predicate_helper_4(context.c)
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
            2 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_predicate_helper_5(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_predicate_helper_6(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onPNAME_NS()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            3 -> {
                context.append()
                onPNAME_NS()
                return
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

fun parse_predicate_helper_0(c: Int): Int {
    if (c < 0x3a) {
        return 4
    } else if (c <= 0x3a) {
        return 3
    } else if (c < 0x3c) {
        return 4
    } else if (c <= 0x3c) {
        return 1
    } else if (c < 0x41) {
        return 4
    } else if (c <= 0x5a) {
        return 2
    } else if (c < 0x61) {
        return 4
    } else if (c <= 0x61) {
        return 0
    } else if (c <= 0x7a) {
        return 2
    } else if (c < 0xc0) {
        return 4
    } else if (c <= 0xd6) {
        return 2
    } else if (c < 0xd8) {
        return 4
    } else if (c <= 0xf6) {
        return 2
    } else if (c < 0xf8) {
        return 4
    } else if (c <= 0x2ff) {
        return 2
    } else if (c < 0x370) {
        return 4
    } else if (c <= 0x37d) {
        return 2
    } else if (c < 0x37f) {
        return 4
    } else if (c <= 0x1fff) {
        return 2
    } else if (c < 0x200c) {
        return 4
    } else if (c <= 0x200d) {
        return 2
    } else if (c < 0x2070) {
        return 4
    } else if (c <= 0x218f) {
        return 2
    } else if (c < 0x2c00) {
        return 4
    } else if (c <= 0x2fef) {
        return 2
    } else if (c < 0x3001) {
        return 4
    } else if (c <= 0xd7ff) {
        return 2
    } else if (c < 0xf900) {
        return 4
    } else if (c <= 0xfdcf) {
        return 2
    } else if (c < 0xfdf0) {
        return 4
    } else if (c <= 0xfffd) {
        return 2
    } else if (c < 0x10000) {
        return 4
    } else if (c <= 0x1fffff) {
        return 2
    } else {
        return 4
    }
}

fun parse_predicate_helper_1(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_predicate_helper_2(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_predicate_helper_3(c: Int): Int {
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

internal inline fun parse_predicate_helper_4(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

fun parse_predicate_helper_5(c: Int): Int {
    if (c < 0x2d) {
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
    } else if (c < 0xb7) {
        return 1
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 1
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_predicate_helper_6(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj(context: ParserContext,
                              crossinline onIRIREF: () -> Unit,
                              crossinline onPNAME_NS: () -> Unit,
                              crossinline onBLANK_NODE_LABEL: () -> Unit,
                              crossinline onSTRING_LITERAL_QUOTE: () -> Unit,
                              crossinline onSTRING_LITERAL_SINGLE_QUOTE: () -> Unit,
                              crossinline onSTRING_LITERAL_LONG_SINGLE_QUOTE: () -> Unit,
                              crossinline onSTRING_LITERAL_LONG_QUOTE: () -> Unit,
                              crossinline onINTEGER: () -> Unit,
                              crossinline onDECIMAL: () -> Unit,
                              crossinline onDOUBLE: () -> Unit,
                              crossinline onBOOLEAN: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_obj_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_obj_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_obj_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_obj_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_obj_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
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
                val localswitch3 = parse_obj_helper_4(context.c)
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
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_obj_helper_5(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_obj_helper_6(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onPNAME_NS()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                onPNAME_NS()
                return
            }
            3 -> {
                context.append()
                val localswitch3 = parse_obj_helper_6(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_7(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_obj_helper_5(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
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
            4 -> {
                context.append()
                val localswitch3 = parse_obj_helper_8(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            val localswitch6 = parse_obj_helper_9(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_obj_helper_10(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            continue@loop5
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
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            continue@loop5
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
                                    break@loop5
                                }
                            }
                        }
                        val localswitch5 = parse_obj_helper_11(context.c)
                        when (localswitch5) {
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
                    1 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_10(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    val localswitch8 = parse_obj_helper_9(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_10(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop7
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
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop7
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
                                            break@loop7
                                        }
                                    }
                                }
                                val localswitch7 = parse_obj_helper_11(context.c)
                                when (localswitch7) {
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
                            1 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_3(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_3(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_3(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_3(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                loop15@ while (true) {
                                                                    val localswitch16 = parse_obj_helper_9(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            continue@loop15
                                                                        }
                                                                        1 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_10(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop15
                                                                                }
                                                                                1 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop15
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
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    continue@loop15
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
                                                                            break@loop15
                                                                        }
                                                                    }
                                                                }
                                                                val localswitch15 = parse_obj_helper_11(context.c)
                                                                when (localswitch15) {
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
                                val localswitch7 = parse_obj_helper_3(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_3(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_3(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_3(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                val localswitch15 = parse_obj_helper_3(context.c)
                                                                when (localswitch15) {
                                                                    0 -> {
                                                                        context.append()
                                                                        val localswitch17 = parse_obj_helper_3(context.c)
                                                                        when (localswitch17) {
                                                                            0 -> {
                                                                                context.append()
                                                                                val localswitch19 = parse_obj_helper_3(context.c)
                                                                                when (localswitch19) {
                                                                                    0 -> {
                                                                                        context.append()
                                                                                        val localswitch21 = parse_obj_helper_3(context.c)
                                                                                        when (localswitch21) {
                                                                                            0 -> {
                                                                                                context.append()
                                                                                                loop23@ while (true) {
                                                                                                    val localswitch24 = parse_obj_helper_9(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            continue@loop23
                                                                                                        }
                                                                                                        1 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_10(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop23
                                                                                                                }
                                                                                                                1 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    continue@loop23
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
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    val localswitch36 = parse_obj_helper_3(context.c)
                                                                                                                                                    when (localswitch36) {
                                                                                                                                                        0 -> {
                                                                                                                                                            context.append()
                                                                                                                                                            val localswitch38 = parse_obj_helper_3(context.c)
                                                                                                                                                            when (localswitch38) {
                                                                                                                                                                0 -> {
                                                                                                                                                                    context.append()
                                                                                                                                                                    val localswitch40 = parse_obj_helper_3(context.c)
                                                                                                                                                                    when (localswitch40) {
                                                                                                                                                                        0 -> {
                                                                                                                                                                            context.append()
                                                                                                                                                                            val localswitch42 = parse_obj_helper_3(context.c)
                                                                                                                                                                            when (localswitch42) {
                                                                                                                                                                                0 -> {
                                                                                                                                                                                    context.append()
                                                                                                                                                                                    continue@loop23
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
                                                                                                            break@loop23
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                val localswitch23 = parse_obj_helper_11(context.c)
                                                                                                when (localswitch23) {
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
                    2 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_11(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    val localswitch8 = parse_obj_helper_12(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_10(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop7
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
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop7
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
                                        2 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_12(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_10(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
                                                        }
                                                        1 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            continue@loop7
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
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            continue@loop7
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
                                                2 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_12(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
                                                        }
                                                        1 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_10(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    continue@loop7
                                                                }
                                                                1 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    continue@loop7
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
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    continue@loop7
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
                                                        2 -> {
                                                            context.append()
                                                            onSTRING_LITERAL_LONG_QUOTE()
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
                                            break@loop7
                                        }
                                    }
                                }
                                break@error
                            }
                            else -> {
                                onSTRING_LITERAL_QUOTE()
                                return
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            5 -> {
                context.append()
                val localswitch3 = parse_obj_helper_13(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            val localswitch6 = parse_obj_helper_14(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_obj_helper_10(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            continue@loop5
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
                                            val localswitch10 = parse_obj_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            continue@loop5
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
                                    break@loop5
                                }
                            }
                        }
                        val localswitch5 = parse_obj_helper_15(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                onSTRING_LITERAL_SINGLE_QUOTE()
                                return
                            }
                            else -> {
                                break@error
                            }
                        }
                    }
                    1 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_10(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    val localswitch8 = parse_obj_helper_14(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_10(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop7
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
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop7
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
                                            break@loop7
                                        }
                                    }
                                }
                                val localswitch7 = parse_obj_helper_15(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        onSTRING_LITERAL_SINGLE_QUOTE()
                                        return
                                    }
                                    else -> {
                                        break@error
                                    }
                                }
                            }
                            1 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_3(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_3(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_3(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_3(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                loop15@ while (true) {
                                                                    val localswitch16 = parse_obj_helper_14(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            continue@loop15
                                                                        }
                                                                        1 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_10(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop15
                                                                                }
                                                                                1 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop15
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
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    continue@loop15
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
                                                                            break@loop15
                                                                        }
                                                                    }
                                                                }
                                                                val localswitch15 = parse_obj_helper_15(context.c)
                                                                when (localswitch15) {
                                                                    0 -> {
                                                                        context.append()
                                                                        onSTRING_LITERAL_SINGLE_QUOTE()
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
                                val localswitch7 = parse_obj_helper_3(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_3(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_3(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_3(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                val localswitch15 = parse_obj_helper_3(context.c)
                                                                when (localswitch15) {
                                                                    0 -> {
                                                                        context.append()
                                                                        val localswitch17 = parse_obj_helper_3(context.c)
                                                                        when (localswitch17) {
                                                                            0 -> {
                                                                                context.append()
                                                                                val localswitch19 = parse_obj_helper_3(context.c)
                                                                                when (localswitch19) {
                                                                                    0 -> {
                                                                                        context.append()
                                                                                        val localswitch21 = parse_obj_helper_3(context.c)
                                                                                        when (localswitch21) {
                                                                                            0 -> {
                                                                                                context.append()
                                                                                                loop23@ while (true) {
                                                                                                    val localswitch24 = parse_obj_helper_14(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            continue@loop23
                                                                                                        }
                                                                                                        1 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_10(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop23
                                                                                                                }
                                                                                                                1 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    continue@loop23
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
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    val localswitch32 = parse_obj_helper_3(context.c)
                                                                                                                                    when (localswitch32) {
                                                                                                                                        0 -> {
                                                                                                                                            context.append()
                                                                                                                                            val localswitch34 = parse_obj_helper_3(context.c)
                                                                                                                                            when (localswitch34) {
                                                                                                                                                0 -> {
                                                                                                                                                    context.append()
                                                                                                                                                    val localswitch36 = parse_obj_helper_3(context.c)
                                                                                                                                                    when (localswitch36) {
                                                                                                                                                        0 -> {
                                                                                                                                                            context.append()
                                                                                                                                                            val localswitch38 = parse_obj_helper_3(context.c)
                                                                                                                                                            when (localswitch38) {
                                                                                                                                                                0 -> {
                                                                                                                                                                    context.append()
                                                                                                                                                                    val localswitch40 = parse_obj_helper_3(context.c)
                                                                                                                                                                    when (localswitch40) {
                                                                                                                                                                        0 -> {
                                                                                                                                                                            context.append()
                                                                                                                                                                            val localswitch42 = parse_obj_helper_3(context.c)
                                                                                                                                                                            when (localswitch42) {
                                                                                                                                                                                0 -> {
                                                                                                                                                                                    context.append()
                                                                                                                                                                                    continue@loop23
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
                                                                                                            break@loop23
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                val localswitch23 = parse_obj_helper_15(context.c)
                                                                                                when (localswitch23) {
                                                                                                    0 -> {
                                                                                                        context.append()
                                                                                                        onSTRING_LITERAL_SINGLE_QUOTE()
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
                    2 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_15(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    val localswitch8 = parse_obj_helper_16(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_10(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    continue@loop7
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
                                                    val localswitch12 = parse_obj_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    continue@loop7
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
                                        2 -> {
                                            context.append()
                                            val localswitch10 = parse_obj_helper_16(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                1 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_10(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
                                                        }
                                                        1 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            continue@loop7
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
                                                            val localswitch14 = parse_obj_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            continue@loop7
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
                                                2 -> {
                                                    context.append()
                                                    val localswitch12 = parse_obj_helper_16(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
                                                        }
                                                        1 -> {
                                                            context.append()
                                                            val localswitch14 = parse_obj_helper_10(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    continue@loop7
                                                                }
                                                                1 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    continue@loop7
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
                                                                    val localswitch16 = parse_obj_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_obj_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_obj_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_obj_helper_3(context.c)
                                                                                            when (localswitch22) {
                                                                                                0 -> {
                                                                                                    context.append()
                                                                                                    val localswitch24 = parse_obj_helper_3(context.c)
                                                                                                    when (localswitch24) {
                                                                                                        0 -> {
                                                                                                            context.append()
                                                                                                            val localswitch26 = parse_obj_helper_3(context.c)
                                                                                                            when (localswitch26) {
                                                                                                                0 -> {
                                                                                                                    context.append()
                                                                                                                    val localswitch28 = parse_obj_helper_3(context.c)
                                                                                                                    when (localswitch28) {
                                                                                                                        0 -> {
                                                                                                                            context.append()
                                                                                                                            val localswitch30 = parse_obj_helper_3(context.c)
                                                                                                                            when (localswitch30) {
                                                                                                                                0 -> {
                                                                                                                                    context.append()
                                                                                                                                    continue@loop7
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
                                                        2 -> {
                                                            context.append()
                                                            onSTRING_LITERAL_LONG_SINGLE_QUOTE()
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
                                            break@loop7
                                        }
                                    }
                                }
                                break@error
                            }
                            else -> {
                                onSTRING_LITERAL_SINGLE_QUOTE()
                                return
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            6 -> {
                context.append()
                loop3@ while (true) {
                    when (context.c) {
                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                            context.append()
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_obj_helper_17(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_18(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                val localswitch7 = parse_obj_helper_19(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_20(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
                                                return
                                            }
                                            1 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_21(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        loop13@ while (true) {
                                                            when (context.c) {
                                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                                    context.append()
                                                                }
                                                                else -> {
                                                                    break@loop13
                                                                }
                                                            }
                                                        }
                                                        onDOUBLE()
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
                                        onDECIMAL()
                                        return
                                    }
                                }
                            }
                            1 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_20(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        loop9@ while (true) {
                                            when (context.c) {
                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                    context.append()
                                                }
                                                else -> {
                                                    break@loop9
                                                }
                                            }
                                        }
                                        onDOUBLE()
                                        return
                                    }
                                    1 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_21(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
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
                    1 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_20(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onDOUBLE()
                                return
                            }
                            1 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_21(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        loop9@ while (true) {
                                            when (context.c) {
                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                    context.append()
                                                }
                                                else -> {
                                                    break@loop9
                                                }
                                            }
                                        }
                                        onDOUBLE()
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
                        onINTEGER()
                        return
                    }
                }
            }
            7 -> {
                context.append()
                val localswitch3 = parse_obj_helper_22(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            when (context.c) {
                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                    context.append()
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        val localswitch5 = parse_obj_helper_17(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_18(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        loop9@ while (true) {
                                            when (context.c) {
                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                    context.append()
                                                }
                                                else -> {
                                                    break@loop9
                                                }
                                            }
                                        }
                                        val localswitch9 = parse_obj_helper_19(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_20(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        loop13@ while (true) {
                                                            when (context.c) {
                                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                                    context.append()
                                                                }
                                                                else -> {
                                                                    break@loop13
                                                                }
                                                            }
                                                        }
                                                        onDOUBLE()
                                                        return
                                                    }
                                                    1 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_21(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                loop15@ while (true) {
                                                                    when (context.c) {
                                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                                            context.append()
                                                                        }
                                                                        else -> {
                                                                            break@loop15
                                                                        }
                                                                    }
                                                                }
                                                                onDOUBLE()
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
                                                onDECIMAL()
                                                return
                                            }
                                        }
                                    }
                                    1 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_20(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
                                                return
                                            }
                                            1 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_21(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        loop13@ while (true) {
                                                            when (context.c) {
                                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                                    context.append()
                                                                }
                                                                else -> {
                                                                    break@loop13
                                                                }
                                                            }
                                                        }
                                                        onDOUBLE()
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
                            1 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_20(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        loop9@ while (true) {
                                            when (context.c) {
                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                    context.append()
                                                }
                                                else -> {
                                                    break@loop9
                                                }
                                            }
                                        }
                                        onDOUBLE()
                                        return
                                    }
                                    1 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_21(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
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
                                onINTEGER()
                                return
                            }
                        }
                    }
                    1 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_21(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    when (context.c) {
                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                            context.append()
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                val localswitch7 = parse_obj_helper_19(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_20(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
                                                return
                                            }
                                            1 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_21(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        loop13@ while (true) {
                                                            when (context.c) {
                                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                                    context.append()
                                                                }
                                                                else -> {
                                                                    break@loop13
                                                                }
                                                            }
                                                        }
                                                        onDOUBLE()
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
                                        onDECIMAL()
                                        return
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
            8 -> {
                context.append()
                val localswitch3 = parse_obj_helper_21(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            when (context.c) {
                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                    context.append()
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        val localswitch5 = parse_obj_helper_19(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_20(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        loop9@ while (true) {
                                            when (context.c) {
                                                0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                    context.append()
                                                }
                                                else -> {
                                                    break@loop9
                                                }
                                            }
                                        }
                                        onDOUBLE()
                                        return
                                    }
                                    1 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_21(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                loop11@ while (true) {
                                                    when (context.c) {
                                                        0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39 -> {
                                                            context.append()
                                                        }
                                                        else -> {
                                                            break@loop11
                                                        }
                                                    }
                                                }
                                                onDOUBLE()
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
                                onDECIMAL()
                                return
                            }
                        }
                    }
                    else -> {
                        break@error
                    }
                }
            }
            9 -> {
                context.append()
                val localswitch3 = parse_obj_helper_23(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_obj_helper_24(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                val localswitch7 = parse_obj_helper_25(context.c)
                                when (localswitch7) {
                                    0 -> {
                                        context.append()
                                        val localswitch9 = parse_obj_helper_26(context.c)
                                        when (localswitch9) {
                                            0 -> {
                                                context.append()
                                                val localswitch11 = parse_obj_helper_27(context.c)
                                                when (localswitch11) {
                                                    0 -> {
                                                        context.append()
                                                        val localswitch13 = parse_obj_helper_28(context.c)
                                                        when (localswitch13) {
                                                            0 -> {
                                                                context.append()
                                                                val localswitch15 = parse_obj_helper_29(context.c)
                                                                when (localswitch15) {
                                                                    0 -> {
                                                                        context.append()
                                                                        val localswitch17 = parse_obj_helper_25(context.c)
                                                                        when (localswitch17) {
                                                                            0 -> {
                                                                                context.append()
                                                                                onBOOLEAN()
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
    throw ParserExceptionUnexpectedChar(context)
}

fun parse_obj_helper_0(c: Int): Int {
    if (c < 0x22) {
        return 10
    } else if (c <= 0x22) {
        return 4
    } else if (c < 0x27) {
        return 10
    } else if (c <= 0x27) {
        return 5
    } else if (c < 0x2b) {
        return 10
    } else if (c <= 0x2b) {
        return 7
    } else if (c < 0x2d) {
        return 10
    } else if (c <= 0x2d) {
        return 7
    } else if (c < 0x2e) {
        return 10
    } else if (c <= 0x2e) {
        return 8
    } else if (c < 0x30) {
        return 10
    } else if (c <= 0x39) {
        return 6
    } else if (c < 0x3a) {
        return 10
    } else if (c <= 0x3a) {
        return 2
    } else if (c < 0x3c) {
        return 10
    } else if (c <= 0x3c) {
        return 0
    } else if (c < 0x41) {
        return 10
    } else if (c <= 0x5a) {
        return 1
    } else if (c < 0x5f) {
        return 10
    } else if (c <= 0x5f) {
        return 3
    } else if (c < 0x61) {
        return 10
    } else if (c <= 0x7a) {
        return 1
    } else if (c <= 0x74) {
        return 9
    } else if (c < 0xc0) {
        return 10
    } else if (c <= 0xd6) {
        return 1
    } else if (c < 0xd8) {
        return 10
    } else if (c <= 0xf6) {
        return 1
    } else if (c < 0xf8) {
        return 10
    } else if (c <= 0x2ff) {
        return 1
    } else if (c < 0x370) {
        return 10
    } else if (c <= 0x37d) {
        return 1
    } else if (c < 0x37f) {
        return 10
    } else if (c <= 0x1fff) {
        return 1
    } else if (c < 0x200c) {
        return 10
    } else if (c <= 0x200d) {
        return 1
    } else if (c < 0x2070) {
        return 10
    } else if (c <= 0x218f) {
        return 1
    } else if (c < 0x2c00) {
        return 10
    } else if (c <= 0x2fef) {
        return 1
    } else if (c < 0x3001) {
        return 10
    } else if (c <= 0xd7ff) {
        return 1
    } else if (c < 0xf900) {
        return 10
    } else if (c <= 0xfdcf) {
        return 1
    } else if (c < 0xfdf0) {
        return 10
    } else if (c <= 0xfffd) {
        return 1
    } else if (c < 0x10000) {
        return 10
    } else if (c <= 0x1fffff) {
        return 1
    } else {
        return 10
    }
}

fun parse_obj_helper_1(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_2(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_3(c: Int): Int {
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

internal inline fun parse_obj_helper_4(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

fun parse_obj_helper_5(c: Int): Int {
    if (c < 0x2d) {
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
    } else if (c < 0xb7) {
        return 1
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 1
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_6(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

fun parse_obj_helper_7(c: Int): Int {
    if (c < 0x30) {
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
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

fun parse_obj_helper_8(c: Int): Int {
    if (c <= 0x9) {
        return 0
    } else if (c < 0xb) {
        return 3
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 3
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x22) {
        return 3
    } else if (c <= 0x22) {
        return 2
    } else if (c < 0x23) {
        return 3
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

fun parse_obj_helper_9(c: Int): Int {
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

fun parse_obj_helper_10(c: Int): Int {
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

internal inline fun parse_obj_helper_11(c: Int): Int {
    if (c == 0x22) {
        return 0
    } else {
        return 1
    }
}

fun parse_obj_helper_12(c: Int): Int {
    if (c <= 0x21) {
        return 0
    } else if (c < 0x22) {
        return 3
    } else if (c <= 0x22) {
        return 2
    } else if (c < 0x23) {
        return 3
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

fun parse_obj_helper_13(c: Int): Int {
    if (c <= 0x9) {
        return 0
    } else if (c < 0xb) {
        return 3
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 3
    } else if (c <= 0x26) {
        return 0
    } else if (c < 0x27) {
        return 3
    } else if (c <= 0x27) {
        return 2
    } else if (c < 0x28) {
        return 3
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

fun parse_obj_helper_14(c: Int): Int {
    if (c <= 0x9) {
        return 0
    } else if (c < 0xb) {
        return 2
    } else if (c <= 0xc) {
        return 0
    } else if (c < 0xe) {
        return 2
    } else if (c <= 0x26) {
        return 0
    } else if (c < 0x28) {
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

internal inline fun parse_obj_helper_15(c: Int): Int {
    if (c == 0x27) {
        return 0
    } else {
        return 1
    }
}

fun parse_obj_helper_16(c: Int): Int {
    if (c <= 0x26) {
        return 0
    } else if (c < 0x27) {
        return 3
    } else if (c <= 0x27) {
        return 2
    } else if (c < 0x28) {
        return 3
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_obj_helper_17(c: Int): Int {
    if (c < 0x2e) {
        return 2
    } else if (c <= 0x2e) {
        return 0
    } else if (c < 0x45) {
        return 2
    } else if (c <= 0x45) {
        return 1
    } else if (c < 0x65) {
        return 2
    } else if (c <= 0x65) {
        return 1
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_18(c: Int): Int {
    if (c < 0x30) {
        return 2
    } else if (c <= 0x39) {
        return 0
    } else if (c < 0x45) {
        return 2
    } else if (c <= 0x45) {
        return 1
    } else if (c < 0x65) {
        return 2
    } else if (c <= 0x65) {
        return 1
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_19(c: Int): Int {
    if (c < 0x45) {
        return 1
    } else if (c <= 0x45) {
        return 0
    } else if (c < 0x65) {
        return 1
    } else if (c <= 0x65) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_20(c: Int): Int {
    if (c < 0x2b) {
        return 2
    } else if (c <= 0x2b) {
        return 1
    } else if (c < 0x2d) {
        return 2
    } else if (c <= 0x2d) {
        return 1
    } else if (c < 0x30) {
        return 2
    } else if (c <= 0x39) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_21(c: Int): Int {
    if (c < 0x30) {
        return 1
    } else if (c <= 0x39) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_22(c: Int): Int {
    if (c < 0x2e) {
        return 2
    } else if (c <= 0x2e) {
        return 1
    } else if (c < 0x30) {
        return 2
    } else if (c <= 0x39) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_obj_helper_23(c: Int): Int {
    if (c == 0x72) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_24(c: Int): Int {
    if (c == 0x75) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_25(c: Int): Int {
    if (c == 0x65) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_26(c: Int): Int {
    if (c == 0x66) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_27(c: Int): Int {
    if (c == 0x61) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_28(c: Int): Int {
    if (c == 0x6c) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_obj_helper_29(c: Int): Int {
    if (c == 0x73) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end(context: ParserContext,
                                     crossinline onPREDICATE_LIST1: () -> Unit,
                                     crossinline onOBJECT_LIST1: () -> Unit,
                                     crossinline onDOT: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_triple_end_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                onPREDICATE_LIST1()
                return
            }
            1 -> {
                context.append()
                onOBJECT_LIST1()
                return
            }
            2 -> {
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

internal inline fun parse_triple_end_helper_0(c: Int): Int {
    if (c < 0x2c) {
        return 3
    } else if (c <= 0x2c) {
        return 1
    } else if (c < 0x2e) {
        return 3
    } else if (c <= 0x2e) {
        return 2
    } else if (c < 0x3b) {
        return 3
    } else if (c <= 0x3b) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_triple_end_or_object_iri(context: ParserContext,
                                                   crossinline onPN_LOCAL: () -> Unit,
                                                   crossinline onPREDICATE_LIST1: () -> Unit,
                                                   crossinline onOBJECT_LIST1: () -> Unit,
                                                   crossinline onDOT: () -> Unit,
                                                   crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_triple_end_or_object_iri_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_triple_end_or_object_iri_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_triple_end_or_object_iri_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_iri_helper_2(context.c)
                                    when (localswitch8) {
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
                        2 -> {
                            context.append()
                            val localswitch6 = parse_triple_end_or_object_iri_helper_3(context.c)
                            when (localswitch6) {
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
                            break@loop3
                        }
                    }
                }
                onPN_LOCAL()
                return
            }
            1 -> {
                context.append()
                val localswitch3 = parse_triple_end_or_object_iri_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_triple_end_or_object_iri_helper_2(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_triple_end_or_object_iri_helper_1(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_iri_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_triple_end_or_object_iri_helper_2(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
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
                                            val localswitch10 = parse_triple_end_or_object_iri_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onPN_LOCAL()
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
                val localswitch3 = parse_triple_end_or_object_iri_helper_3(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            loop6@ while (true) {
                                when (context.c) {
                                    0x2e -> {
                                        context.append()
                                    }
                                    else -> {
                                        break@loop6
                                    }
                                }
                            }
                            val localswitch6 = parse_triple_end_or_object_iri_helper_1(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_iri_helper_2(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_iri_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop5
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
                                    val localswitch8 = parse_triple_end_or_object_iri_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        onPN_LOCAL()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            3 -> {
                context.append()
                onPREDICATE_LIST1()
                return
            }
            4 -> {
                context.append()
                onOBJECT_LIST1()
                return
            }
            5 -> {
                context.append()
                onDOT()
                return
            }
            6 -> {
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

fun parse_triple_end_or_object_iri_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 7
    } else if (c <= 0xa) {
        return 6
    } else if (c < 0xd) {
        return 7
    } else if (c <= 0xd) {
        return 6
    } else if (c < 0x20) {
        return 7
    } else if (c <= 0x20) {
        return 6
    } else if (c < 0x25) {
        return 7
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2c) {
        return 7
    } else if (c <= 0x2c) {
        return 4
    } else if (c < 0x2e) {
        return 7
    } else if (c <= 0x2e) {
        return 5
    } else if (c < 0x30) {
        return 7
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x3b) {
        return 7
    } else if (c <= 0x3b) {
        return 3
    } else if (c < 0x41) {
        return 7
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 7
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 7
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 7
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xc0) {
        return 7
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 7
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 7
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 7
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 7
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 7
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 7
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 7
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 7
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 7
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 7
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 7
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 7
    }
}

fun parse_triple_end_or_object_iri_helper_1(c: Int): Int {
    if (c < 0x25) {
        return 3
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2d) {
        return 3
    } else if (c <= 0x2d) {
        return 0
    } else if (c < 0x30) {
        return 3
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xb7) {
        return 3
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 3
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 3
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 3
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 3
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 3
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 3
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 3
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 3
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 3
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 3
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 3
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_triple_end_or_object_iri_helper_2(c: Int): Int {
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

fun parse_triple_end_or_object_iri_helper_3(c: Int): Int {
    if (c < 0x21) {
        return 1
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 1
    } else if (c <= 0x2f) {
        return 0
    } else if (c < 0x3b) {
        return 1
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 1
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 1
    } else if (c <= 0x40) {
        return 0
    } else if (c < 0x5f) {
        return 1
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x7e) {
        return 1
    } else if (c <= 0x7e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end_or_object_string(context: ParserContext,
                                                      crossinline onLANGTAG: () -> Unit,
                                                      crossinline onIRI1: () -> Unit,
                                                      crossinline onPREDICATE_LIST1: () -> Unit,
                                                      crossinline onOBJECT_LIST1: () -> Unit,
                                                      crossinline onDOT: () -> Unit,
                                                      crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_triple_end_or_object_string_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                val localswitch3 = parse_triple_end_or_object_string_helper_1(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            when (context.c) {
                                0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                                    context.append()
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        loop5@ while (true) {
                            val localswitch6 = parse_triple_end_or_object_string_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_string_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            loop10@ while (true) {
                                                when (context.c) {
                                                    0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f, 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f, 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a -> {
                                                        context.append()
                                                    }
                                                    else -> {
                                                        break@loop10
                                                    }
                                                }
                                            }
                                            continue@loop5
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
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
            1 -> {
                context.append()
                val localswitch3 = parse_triple_end_or_object_string_helper_4(context.c)
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
            2 -> {
                context.append()
                onPREDICATE_LIST1()
                return
            }
            3 -> {
                context.append()
                onOBJECT_LIST1()
                return
            }
            4 -> {
                context.append()
                onDOT()
                return
            }
            5 -> {
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

fun parse_triple_end_or_object_string_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 6
    } else if (c <= 0xa) {
        return 5
    } else if (c < 0xd) {
        return 6
    } else if (c <= 0xd) {
        return 5
    } else if (c < 0x20) {
        return 6
    } else if (c <= 0x20) {
        return 5
    } else if (c < 0x2c) {
        return 6
    } else if (c <= 0x2c) {
        return 3
    } else if (c < 0x2e) {
        return 6
    } else if (c <= 0x2e) {
        return 4
    } else if (c < 0x3b) {
        return 6
    } else if (c <= 0x3b) {
        return 2
    } else if (c < 0x40) {
        return 6
    } else if (c <= 0x40) {
        return 0
    } else if (c < 0x5e) {
        return 6
    } else if (c <= 0x5e) {
        return 1
    } else {
        return 6
    }
}

internal inline fun parse_triple_end_or_object_string_helper_1(c: Int): Int {
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

internal inline fun parse_triple_end_or_object_string_helper_2(c: Int): Int {
    if (c == 0x2d) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end_or_object_string_helper_3(c: Int): Int {
    if (c < 0x30) {
        return 1
    } else if (c <= 0x39) {
        return 0
    } else if (c < 0x41) {
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

internal inline fun parse_triple_end_or_object_string_helper_4(c: Int): Int {
    if (c == 0x5e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end_or_object_string_typed(context: ParserContext,
                                                            crossinline onIRIREF: () -> Unit,
                                                            crossinline onPNAME_NS: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_triple_end_or_object_string_typed_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    val localswitch4 = parse_triple_end_or_object_string_typed_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_triple_end_or_object_string_typed_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_triple_end_or_object_string_typed_helper_3(context.c)
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
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            val localswitch14 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                            when (localswitch14) {
                                                                0 -> {
                                                                    context.append()
                                                                    val localswitch16 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                                    when (localswitch16) {
                                                                        0 -> {
                                                                            context.append()
                                                                            val localswitch18 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                                            when (localswitch18) {
                                                                                0 -> {
                                                                                    context.append()
                                                                                    val localswitch20 = parse_triple_end_or_object_string_typed_helper_3(context.c)
                                                                                    when (localswitch20) {
                                                                                        0 -> {
                                                                                            context.append()
                                                                                            val localswitch22 = parse_triple_end_or_object_string_typed_helper_3(context.c)
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
                val localswitch3 = parse_triple_end_or_object_string_typed_helper_4(context.c)
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
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_triple_end_or_object_string_typed_helper_5(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        else -> {
                            break@loop3
                        }
                    }
                }
                val localswitch3 = parse_triple_end_or_object_string_typed_helper_6(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        onPNAME_NS()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            2 -> {
                context.append()
                onPNAME_NS()
                return
            }
            else -> {
                break@error
            }
        }
    }
    throw ParserExceptionUnexpectedChar(context)
}

fun parse_triple_end_or_object_string_typed_helper_0(c: Int): Int {
    if (c < 0x3a) {
        return 3
    } else if (c <= 0x3a) {
        return 2
    } else if (c < 0x3c) {
        return 3
    } else if (c <= 0x3c) {
        return 0
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 1
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 1
    } else if (c < 0xc0) {
        return 3
    } else if (c <= 0xd6) {
        return 1
    } else if (c < 0xd8) {
        return 3
    } else if (c <= 0xf6) {
        return 1
    } else if (c < 0xf8) {
        return 3
    } else if (c <= 0x2ff) {
        return 1
    } else if (c < 0x370) {
        return 3
    } else if (c <= 0x37d) {
        return 1
    } else if (c < 0x37f) {
        return 3
    } else if (c <= 0x1fff) {
        return 1
    } else if (c < 0x200c) {
        return 3
    } else if (c <= 0x200d) {
        return 1
    } else if (c < 0x2070) {
        return 3
    } else if (c <= 0x218f) {
        return 1
    } else if (c < 0x2c00) {
        return 3
    } else if (c <= 0x2fef) {
        return 1
    } else if (c < 0x3001) {
        return 3
    } else if (c <= 0xd7ff) {
        return 1
    } else if (c < 0xf900) {
        return 3
    } else if (c <= 0xfdcf) {
        return 1
    } else if (c < 0xfdf0) {
        return 3
    } else if (c <= 0xfffd) {
        return 1
    } else if (c < 0x10000) {
        return 3
    } else if (c <= 0x1fffff) {
        return 1
    } else {
        return 3
    }
}

fun parse_triple_end_or_object_string_typed_helper_1(c: Int): Int {
    if (c < 0x21) {
        return 2
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 2
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 2
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 2
    } else if (c <= 0x5b) {
        return 0
    } else if (c < 0x5c) {
        return 2
    } else if (c <= 0x5c) {
        return 1
    } else if (c < 0x5d) {
        return 2
    } else if (c <= 0x5d) {
        return 0
    } else if (c < 0x5f) {
        return 2
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 2
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0x7e) {
        return 2
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_triple_end_or_object_string_typed_helper_2(c: Int): Int {
    if (c < 0x55) {
        return 2
    } else if (c <= 0x55) {
        return 1
    } else if (c < 0x75) {
        return 2
    } else if (c <= 0x75) {
        return 0
    } else {
        return 2
    }
}

internal inline fun parse_triple_end_or_object_string_typed_helper_3(c: Int): Int {
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

internal inline fun parse_triple_end_or_object_string_typed_helper_4(c: Int): Int {
    if (c == 0x3e) {
        return 0
    } else {
        return 1
    }
}

fun parse_triple_end_or_object_string_typed_helper_5(c: Int): Int {
    if (c < 0x2d) {
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
    } else if (c < 0xb7) {
        return 1
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 1
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 1
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 1
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 1
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 1
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 1
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 1
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 1
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 1
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 1
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 1
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 1
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end_or_object_string_typed_helper_6(c: Int): Int {
    if (c == 0x3a) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_triple_end_or_object_string_typed_iri(context: ParserContext,
                                                                crossinline onPN_LOCAL: () -> Unit,
                                                                crossinline onPREDICATE_LIST1: () -> Unit,
                                                                crossinline onOBJECT_LIST1: () -> Unit,
                                                                crossinline onDOT: () -> Unit,
                                                                crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_triple_end_or_object_string_typed_iri_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                                    when (localswitch8) {
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
                        2 -> {
                            context.append()
                            val localswitch6 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c)
                            when (localswitch6) {
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
                            break@loop3
                        }
                    }
                }
                onPN_LOCAL()
                return
            }
            1 -> {
                context.append()
                val localswitch3 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
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
                                            val localswitch10 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onPN_LOCAL()
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
                val localswitch3 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            loop6@ while (true) {
                                when (context.c) {
                                    0x2e -> {
                                        context.append()
                                    }
                                    else -> {
                                        break@loop6
                                    }
                                }
                            }
                            val localswitch6 = parse_triple_end_or_object_string_typed_iri_helper_1(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_triple_end_or_object_string_typed_iri_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop5
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
                                    val localswitch8 = parse_triple_end_or_object_string_typed_iri_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        onPN_LOCAL()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            3 -> {
                context.append()
                onPREDICATE_LIST1()
                return
            }
            4 -> {
                context.append()
                onOBJECT_LIST1()
                return
            }
            5 -> {
                context.append()
                onDOT()
                return
            }
            6 -> {
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

fun parse_triple_end_or_object_string_typed_iri_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 7
    } else if (c <= 0xa) {
        return 6
    } else if (c < 0xd) {
        return 7
    } else if (c <= 0xd) {
        return 6
    } else if (c < 0x20) {
        return 7
    } else if (c <= 0x20) {
        return 6
    } else if (c < 0x25) {
        return 7
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2c) {
        return 7
    } else if (c <= 0x2c) {
        return 4
    } else if (c < 0x2e) {
        return 7
    } else if (c <= 0x2e) {
        return 5
    } else if (c < 0x30) {
        return 7
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x3b) {
        return 7
    } else if (c <= 0x3b) {
        return 3
    } else if (c < 0x41) {
        return 7
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 7
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 7
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 7
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xc0) {
        return 7
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 7
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 7
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 7
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 7
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 7
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 7
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 7
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 7
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 7
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 7
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 7
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 7
    }
}

fun parse_triple_end_or_object_string_typed_iri_helper_1(c: Int): Int {
    if (c < 0x25) {
        return 3
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2d) {
        return 3
    } else if (c <= 0x2d) {
        return 0
    } else if (c < 0x30) {
        return 3
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xb7) {
        return 3
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 3
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 3
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 3
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 3
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 3
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 3
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 3
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 3
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 3
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 3
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 3
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_triple_end_or_object_string_typed_iri_helper_2(c: Int): Int {
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

fun parse_triple_end_or_object_string_typed_iri_helper_3(c: Int): Int {
    if (c < 0x21) {
        return 1
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 1
    } else if (c <= 0x2f) {
        return 0
    } else if (c < 0x3b) {
        return 1
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 1
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 1
    } else if (c <= 0x40) {
        return 0
    } else if (c < 0x5f) {
        return 1
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x7e) {
        return 1
    } else if (c <= 0x7e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_subject_iri_or_ws(context: ParserContext,
                                            crossinline onPN_LOCAL: () -> Unit,
                                            crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_subject_iri_or_ws_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_subject_iri_or_ws_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_subject_iri_or_ws_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_subject_iri_or_ws_helper_2(context.c)
                                    when (localswitch8) {
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
                        2 -> {
                            context.append()
                            val localswitch6 = parse_subject_iri_or_ws_helper_3(context.c)
                            when (localswitch6) {
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
                            break@loop3
                        }
                    }
                }
                onPN_LOCAL()
                return
            }
            1 -> {
                context.append()
                val localswitch3 = parse_subject_iri_or_ws_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_subject_iri_or_ws_helper_2(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_subject_iri_or_ws_helper_1(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_subject_iri_or_ws_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_subject_iri_or_ws_helper_2(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
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
                                            val localswitch10 = parse_subject_iri_or_ws_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onPN_LOCAL()
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
                val localswitch3 = parse_subject_iri_or_ws_helper_3(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            loop6@ while (true) {
                                when (context.c) {
                                    0x2e -> {
                                        context.append()
                                    }
                                    else -> {
                                        break@loop6
                                    }
                                }
                            }
                            val localswitch6 = parse_subject_iri_or_ws_helper_1(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_subject_iri_or_ws_helper_2(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_subject_iri_or_ws_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop5
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
                                    val localswitch8 = parse_subject_iri_or_ws_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        onPN_LOCAL()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            3 -> {
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

fun parse_subject_iri_or_ws_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 4
    } else if (c <= 0xa) {
        return 3
    } else if (c < 0xd) {
        return 4
    } else if (c <= 0xd) {
        return 3
    } else if (c < 0x20) {
        return 4
    } else if (c <= 0x20) {
        return 3
    } else if (c < 0x25) {
        return 4
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x30) {
        return 4
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 4
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 4
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 4
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 4
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xc0) {
        return 4
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 4
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 4
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 4
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 4
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 4
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 4
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 4
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 4
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 4
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 4
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 4
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 4
    }
}

fun parse_subject_iri_or_ws_helper_1(c: Int): Int {
    if (c < 0x25) {
        return 3
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2d) {
        return 3
    } else if (c <= 0x2d) {
        return 0
    } else if (c < 0x30) {
        return 3
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xb7) {
        return 3
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 3
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 3
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 3
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 3
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 3
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 3
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 3
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 3
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 3
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 3
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 3
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_subject_iri_or_ws_helper_2(c: Int): Int {
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

fun parse_subject_iri_or_ws_helper_3(c: Int): Int {
    if (c < 0x21) {
        return 1
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 1
    } else if (c <= 0x2f) {
        return 0
    } else if (c < 0x3b) {
        return 1
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 1
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 1
    } else if (c <= 0x40) {
        return 0
    } else if (c < 0x5f) {
        return 1
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x7e) {
        return 1
    } else if (c <= 0x7e) {
        return 0
    } else {
        return 1
    }
}

internal inline fun parse_predicate_iri_or_ws(context: ParserContext,
                                              crossinline onPN_LOCAL: () -> Unit,
                                              crossinline onSKIP_WS_FORCED: () -> Unit
) {
    context.clear()
    error@ while (true) {
        val localswitch1 = parse_predicate_iri_or_ws_helper_0(context.c)
        when (localswitch1) {
            0 -> {
                context.append()
                loop3@ while (true) {
                    loop4@ while (true) {
                        when (context.c) {
                            0x2e -> {
                                context.append()
                            }
                            else -> {
                                break@loop4
                            }
                        }
                    }
                    val localswitch4 = parse_predicate_iri_or_ws_helper_1(context.c)
                    when (localswitch4) {
                        0 -> {
                            context.append()
                            continue@loop3
                        }
                        1 -> {
                            context.append()
                            val localswitch6 = parse_predicate_iri_or_ws_helper_2(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    val localswitch8 = parse_predicate_iri_or_ws_helper_2(context.c)
                                    when (localswitch8) {
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
                        2 -> {
                            context.append()
                            val localswitch6 = parse_predicate_iri_or_ws_helper_3(context.c)
                            when (localswitch6) {
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
                            break@loop3
                        }
                    }
                }
                onPN_LOCAL()
                return
            }
            1 -> {
                context.append()
                val localswitch3 = parse_predicate_iri_or_ws_helper_2(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        val localswitch5 = parse_predicate_iri_or_ws_helper_2(context.c)
                        when (localswitch5) {
                            0 -> {
                                context.append()
                                loop7@ while (true) {
                                    loop8@ while (true) {
                                        when (context.c) {
                                            0x2e -> {
                                                context.append()
                                            }
                                            else -> {
                                                break@loop8
                                            }
                                        }
                                    }
                                    val localswitch8 = parse_predicate_iri_or_ws_helper_1(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop7
                                        }
                                        1 -> {
                                            context.append()
                                            val localswitch10 = parse_predicate_iri_or_ws_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    val localswitch12 = parse_predicate_iri_or_ws_helper_2(context.c)
                                                    when (localswitch12) {
                                                        0 -> {
                                                            context.append()
                                                            continue@loop7
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
                                            val localswitch10 = parse_predicate_iri_or_ws_helper_3(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop7
                                                }
                                                else -> {
                                                    break@error
                                                }
                                            }
                                        }
                                        else -> {
                                            break@loop7
                                        }
                                    }
                                }
                                onPN_LOCAL()
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
                val localswitch3 = parse_predicate_iri_or_ws_helper_3(context.c)
                when (localswitch3) {
                    0 -> {
                        context.append()
                        loop5@ while (true) {
                            loop6@ while (true) {
                                when (context.c) {
                                    0x2e -> {
                                        context.append()
                                    }
                                    else -> {
                                        break@loop6
                                    }
                                }
                            }
                            val localswitch6 = parse_predicate_iri_or_ws_helper_1(context.c)
                            when (localswitch6) {
                                0 -> {
                                    context.append()
                                    continue@loop5
                                }
                                1 -> {
                                    context.append()
                                    val localswitch8 = parse_predicate_iri_or_ws_helper_2(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            val localswitch10 = parse_predicate_iri_or_ws_helper_2(context.c)
                                            when (localswitch10) {
                                                0 -> {
                                                    context.append()
                                                    continue@loop5
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
                                    val localswitch8 = parse_predicate_iri_or_ws_helper_3(context.c)
                                    when (localswitch8) {
                                        0 -> {
                                            context.append()
                                            continue@loop5
                                        }
                                        else -> {
                                            break@error
                                        }
                                    }
                                }
                                else -> {
                                    break@loop5
                                }
                            }
                        }
                        onPN_LOCAL()
                        return
                    }
                    else -> {
                        break@error
                    }
                }
            }
            3 -> {
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

fun parse_predicate_iri_or_ws_helper_0(c: Int): Int {
    if (c < 0x9) {
        return 4
    } else if (c <= 0xa) {
        return 3
    } else if (c < 0xd) {
        return 4
    } else if (c <= 0xd) {
        return 3
    } else if (c < 0x20) {
        return 4
    } else if (c <= 0x20) {
        return 3
    } else if (c < 0x25) {
        return 4
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x30) {
        return 4
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 4
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 4
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 4
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 4
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xc0) {
        return 4
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 4
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 4
    } else if (c <= 0x2ff) {
        return 0
    } else if (c < 0x370) {
        return 4
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 4
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 4
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x2070) {
        return 4
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 4
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 4
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 4
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 4
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 4
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 4
    }
}

fun parse_predicate_iri_or_ws_helper_1(c: Int): Int {
    if (c < 0x25) {
        return 3
    } else if (c <= 0x25) {
        return 1
    } else if (c < 0x2d) {
        return 3
    } else if (c <= 0x2d) {
        return 0
    } else if (c < 0x30) {
        return 3
    } else if (c <= 0x3a) {
        return 0
    } else if (c < 0x41) {
        return 3
    } else if (c <= 0x5a) {
        return 0
    } else if (c < 0x5c) {
        return 3
    } else if (c <= 0x5c) {
        return 2
    } else if (c < 0x5f) {
        return 3
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x61) {
        return 3
    } else if (c <= 0x7a) {
        return 0
    } else if (c < 0xb7) {
        return 3
    } else if (c <= 0xb7) {
        return 0
    } else if (c < 0xc0) {
        return 3
    } else if (c <= 0xd6) {
        return 0
    } else if (c < 0xd8) {
        return 3
    } else if (c <= 0xf6) {
        return 0
    } else if (c < 0xf8) {
        return 3
    } else if (c <= 0x37d) {
        return 0
    } else if (c < 0x37f) {
        return 3
    } else if (c <= 0x1fff) {
        return 0
    } else if (c < 0x200c) {
        return 3
    } else if (c <= 0x200d) {
        return 0
    } else if (c < 0x203f) {
        return 3
    } else if (c <= 0x2040) {
        return 0
    } else if (c < 0x2070) {
        return 3
    } else if (c <= 0x218f) {
        return 0
    } else if (c < 0x2c00) {
        return 3
    } else if (c <= 0x2fef) {
        return 0
    } else if (c < 0x3001) {
        return 3
    } else if (c <= 0xd7ff) {
        return 0
    } else if (c < 0xf900) {
        return 3
    } else if (c <= 0xfdcf) {
        return 0
    } else if (c < 0xfdf0) {
        return 3
    } else if (c <= 0xfffd) {
        return 0
    } else if (c < 0x10000) {
        return 3
    } else if (c <= 0x1fffff) {
        return 0
    } else {
        return 3
    }
}

internal inline fun parse_predicate_iri_or_ws_helper_2(c: Int): Int {
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

fun parse_predicate_iri_or_ws_helper_3(c: Int): Int {
    if (c < 0x21) {
        return 1
    } else if (c <= 0x21) {
        return 0
    } else if (c < 0x23) {
        return 1
    } else if (c <= 0x2f) {
        return 0
    } else if (c < 0x3b) {
        return 1
    } else if (c <= 0x3b) {
        return 0
    } else if (c < 0x3d) {
        return 1
    } else if (c <= 0x3d) {
        return 0
    } else if (c < 0x3f) {
        return 1
    } else if (c <= 0x40) {
        return 0
    } else if (c < 0x5f) {
        return 1
    } else if (c <= 0x5f) {
        return 0
    } else if (c < 0x7e) {
        return 1
    } else if (c <= 0x7e) {
        return 0
    } else {
        return 1
    }
}
