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
package lupos.launch.test_dictionary_encoding

import lupos.buffermanager.BufferManagerExt
import lupos.dictionary.DictionaryHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.Parallel
import lupos.test.AflCore
import kotlin.math.abs

private val verbose = false

@OptIn(ExperimentalStdlibApi::class, kotlin.time.ExperimentalTime::class)
internal fun mainFunc(arg: String): Unit = Parallel.runBlocking {
    AflCore("dictionary_encoding.${BufferManagerExt.isInMemoryOnly}", 1.0, ::executeTest)(arg)
}

private object AssertionFunctions {
    fun <T> assumeEQ(a: () -> T, b: () -> T) {
        val a1 = a()
        val b1 = b()
        if (a1 != b1) {
            throw Exception("$a1 != $b1")
        }
    }

    fun <T> assumeNEQ(a: () -> T, b: () -> T) {
        val a1 = a()
        val b1 = b()
        if (a1 == b1) {
            throw Exception("$a1 != $b1")
        }
    }

    fun <T> assumeException(a: () -> Unit) {
        var flag = true
        try {
            a()
        } catch (e: Throwable) {
            flag = false
        }
        if (flag) {
            throw Exception("no Exception")
        }
    }

    fun randomRangePositive(rng: Int, limit: Int): Int = abs(rng % limit)
    fun randomPrintableChar(rng): Char = printableChars[randomRangePositive(rng, printableChars.size)]
    fun randomPrintableNumber(rng): Char = "${randomRangePositive(rng, 10)}"[0]
    val printableChars = """ !"#\$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~"""
}

private fun executeTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    val buffer = ByteArrayWrapper()
    val buffer2 = ByteArrayWrapper()
    fun generateDoubleNumber(len: Int): String {
        var remaining = len
        var v = ""
        when (remaining) {
            1 -> {
                remaining--
                v = nextRandom().toString()
            }
            2, 3, 4 -> {
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                }
                remaining--
                v += abs(nextRandom()).toString()
            }
            else -> {
                remaining--
                val l1 = nextRandom()
                remaining--
                val l2 = nextRandom()
                val v2 = ""
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 6)) {
                    0 -> v2 += "e-"
                    1 -> v2 += "e+"
                    2 -> v2 += "e"
                    3 -> v2 += "E+"
                    4 -> v2 += "E-"
                    5 -> v2 += "E"
                }
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                }
                for (i in 0 until remaining) {
                    remaining--
                    v += randomPrintableNumber(nextRandom())
                }
                val dot = abs(l1 % v.length)
                when (dot) {
                    0 -> "." + v
                    v.length -> v + ".0"
                    else -> v = v.substring(0, dot) + "." + v.substring(dot, v.length)
                }
                if (dot + 2 < v.length) {
                    val pos = 1 + dot + abs(l2 % (v.length - dot - 2))
                    v = v.substring(0, pos) + "." + v.substring(pos, v.length)
                }
            }
        }
        return v
    }

    fun generateDecimalNumber(len: Int) {
        var remaining = len
        var v = ""
        when (remaining) {
            1 -> {
                remaining--
                v = nextRandom().toString()
            }
            2 -> {
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                }
                remaining--
                v += abs(nextRandom()).toString()
            }
            else -> {
                remaining--
                val l = nextRandom()
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                }
                for (i in 0 until remaining) {
                    remaining--
                    v += randomPrintableNumber(nextRandom())
                }
                val l2 = abs(l % v.length - 1)
                when (l2) {
                    0 -> "." + v
                    v.length -> v + ".0"
                    else -> v = v.substring(0, l2) + "." + v.substring(l2, v.length)
                }
            }
        }
        return v
    }

    fun generateIntegerNumber(len: Int) {
        var remaining = len
        var v = ""
        if (remaining == 1) {
            remaining--
            v = nextRandom().toString()
        } else {
            remaining--
            when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                0 -> v += "+"
                1 -> v += "-"
            }
            for (i in 0 until remaining) {
                remaining--
                v += randomPrintableNumber(nextRandom())
            }
        }
        return v
    }

    fun bnodeToByteArray_1() {
        resetRandom()
        if (hasNextRandom() > 0) {
            val v = nextRandom()
            DictionaryHelper.bnodeToByteArray(buffer, v)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BLANK_NODE })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_I(buffer) }, { v })
            AssertionFunctions.assumeException({ DictionaryHelper.byteArrayToBnode_S(buffer) })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_A(buffer) }, { "$v" })
        }
    }

    fun bnodeToByteArray_2() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += randomPrintableChar(nextRandom())
        }
        DictionaryHelper.bnodeToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BLANK_NODE })
        AssertionFunctions.assumeException({ DictionaryHelper.byteArrayToBnode_I(buffer) })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_A(buffer) }, { v })
    }

    fun booleanToByteArray() {
        resetRandom()
        DictionaryHelper.booleanToByteArray(buffer, true)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BOOLEAN })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBoolean(buffer) }, { true })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.booleanToByteArray(buffer, false)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BOOLEAN })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBoolean(buffer) }, { false })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun iriToByteArray() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += randomPrintableChar(nextRandom())
        }
        DictionaryHelper.iriToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.IRI })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToIri(buffer) }, { v })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun stringToByteArray() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += randomPrintableChar(nextRandom())
        }
        DictionaryHelper.stringToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToString(buffer) }, { v })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun typedToByteArray() {
        resetRandom()
        var v = ""
        if (hasNextRandom() > 1) {
            val l = nextRandom()
            for (i in 0 until hasNextRandom()) {
                v += randomPrintableChar(nextRandom())
            }
            val l2 = AssertionFunctions.randomRangePositive(l, v.length - 1)
            val a = v.substring(0, l2)
            val b = v.substring(l2, v.length)
            DictionaryHelper.typedToByteArray(buffer, a, b)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING_TYPED })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToTyped_Content(buffer) }, { a })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToTyped_Type(buffer) }, { b })
            DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
    }

    fun langToByteArray() {
        resetRandom()
        var v = ""
        if (hasNextRandom() > 1) {
            val l = nextRandom()
            for (i in 0 until hasNextRandom()) {
                v += randomPrintableChar(nextRandom())
            }
            val l2 = AssertionFunctions.randomRangePositive(l, v.length - 1)
            val a = v.substring(0, l2)
            val b = v.substring(l2, v.length)
            DictionaryHelper.langToByteArray(buffer, a, b)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING_TYPED })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToLang_Content(buffer) }, { a })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToLang_Lang(buffer) }, { b })
            DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
    }

    fun integerToByteArray() {
        resetRandom()
        var v = generateIntegerNumber(hasNextRandom())
        DictionaryHelper.integerToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.INTEGER })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToInteger_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToInteger_I(buffer).toString() }, { v })
        DictionaryHelper.integerToByteArray(buffer2, DictionaryHelper.byteArrayToInteger_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun decimalToByteArray() {
        resetRandom()
        var v = generateDecimalNumber(hasNextRandom())
        DictionaryHelper.decimalToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DECIMAL })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDecimal_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDecimal_I(buffer).toString() }, { v })
        DictionaryHelper.decimalToByteArray(buffer2, DictionaryHelper.byteArrayToDecimal_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun floatToByteArray() {
        resetRandom()
        var v = generateDoubleNumber(hasNextRandom()).toDouble.toString()
        println("floatToByteArray '$v'")
        DictionaryHelper.floatToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.FLOAT })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToFloat_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToFloat_I(buffer).toString() }, { v })
        DictionaryHelper.floatToByteArray(buffer2, DictionaryHelper.byteArrayToFloat_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun doubleToByteArray() {
        resetRandom()
        var v = generateDoubleNumber(hasNextRandom()).toDouble.toString()
        println("doubleToByteArray '$v'")
        DictionaryHelper.doubleToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DOUBLE })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDouble_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDouble_I(buffer).toString() }, { v })
        DictionaryHelper.doubleToByteArray(buffer2, DictionaryHelper.byteArrayToDouble_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun errorToByteArray() {
        resetRandom()
        DictionaryHelper.errorToByteArray(buffer)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.ERROR })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun undefToByteArray() {
        resetRandom()
        DictionaryHelper.undefToByteArray(buffer)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.ERROR })
        DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun dateTimeToByteArray() {
        resetRandom()
        if (hasNextRandom() > 9) {
            val month: Int = nextRandom()
            val day: Int = nextRandom()
            val hours: Int = nextRandom()
            val minutes: Int = nextRandom()
            val timezoneHours: Int = nextRandom()
            val timezoneMinutes: Int = nextRandom()
            var year: String = "0"
            var seconds: String = "0.0"
            if (hasNextRandom() < 3) {
                year = generateIntegerNumber(1)
                seconds = generateDecimalNumber(1)
            } else {
                val rng = nextRandom()
                val avail = hasNextRandom()
                val l = AssertionFunctions.randomRangePositive(rng, avail - 2)
                year = generateIntegerNumber(1 + l)
                seconds = generateDecimalNumber(hasNextRandom())
            }
            DictionaryHelper.dateTimeToByteArray(buffer, year, month, day, hours, minutes, seconds, timezoneHours, timezoneMinutes)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DATE_TIME })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Month(buffer).toString().toInt() }, { month })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Day(buffer).toString().toInt() }, { day })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Hours(buffer).toString().toInt() }, { hours })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Minutes(buffer).toString().toInt() }, { minutes })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Seconds(buffer).toString() }, { seconds })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Year(buffer).toString() }, { year })
            val tmp = byteArrayToDateTimeAsTyped_Content(buffer)
            DictionaryHelper.DictionaryHelper.dateTimeToByteArray(buffer2, tmp)
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
            DictionaryHelper.sparqlToByteArray(buffer2, byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
    }
    bnodeToByteArray_1()
    bnodeToByteArray_2()
    booleanToByteArray()
    iriToByteArray()
    stringToByteArray()
    typedToByteArray()
    langToByteArray()
    integerToByteArray()
    decimalToByteArray()
    floatToByteArray()
    doubleToByteArray()
    errorToByteArray()
    undefToByteArray()
    dateTimeToByteArray()
}
