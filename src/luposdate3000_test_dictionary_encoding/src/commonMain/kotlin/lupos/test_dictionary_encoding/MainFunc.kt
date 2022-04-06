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
package lupos.test_dictionary_encoding
import lupos.shared.myPrintStackTrace

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.shared.DictionaryValueHelper
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import kotlin.math.abs

private object AssertionFunctions {
    fun <T> assumeEQ(a: () -> T, b: () -> T) {
        val a1 = a()
        val b1 = b()
        if (a1 != b1) {
            TODO("'$a1' == '$b1' -- ${a1.toString().length} ${b1.toString().length}")
        }
    }

    fun <T> assumeNEQ(a: () -> T, b: () -> T) {
        val a1 = a()
        val b1 = b()
        if (a1 == b1) {
            TODO("'$a1' != '$b1' -- ${a1.toString().length} ${b1.toString().length}")
        }
    }

    fun assumeException(a: () -> Unit) {
        var flag = true
        try {
            a()
        } catch (e: Throwable) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_test_dictionary_encoding/src/commonMain/kotlin/lupos/test_dictionary_encoding/MainFunc.kt:50"/*SOURCE_FILE_END*/ ) this is handled correctly
            flag = false
        }
        if (flag) {
            TODO("expected an Exception")
        }
    }

    fun randomRangePositive(rng: Int, limit: Int): Int = abs(rng % limit)
    fun randomPrintableChar(rng: Int): Char = printableChars[randomRangePositive(rng, printableChars.length)]
    fun randomPrintableLetter(rng: Int): Char = letters[randomRangePositive(rng, letters.length)]
    fun randomPrintableNumber(rng: Int): Char = "${randomRangePositive(rng, 10)}"[0]
    const val printableChars = """ !#\$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~""" // excluding """""""
    const val letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
}

public fun executeDictionaryEncodingTest(nextRandom: () -> Int, hasNextRandom: () -> Int, resetRandom: () -> Unit) {
    if (!SanityCheck.enabled) {
        return
    }
    val verbose = false
    val buffer = ByteArrayWrapper()
    val buffer2 = ByteArrayWrapper()
    fun generateDoubleNumber(len: Int): String {
        var remaining = len
        var v = ""
        when (remaining) {
            0 -> v = "1.0e1"
            1 -> {
                v = nextRandom().toString()
            }
            2, 3, 4 -> {
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                }
                v += abs(nextRandom()).toString()
            }
            else -> {
                remaining--
                val l1 = nextRandom()
                remaining--
                val l2 = nextRandom()
                var v2 = ""
                remaining--
                when (AssertionFunctions.randomRangePositive(nextRandom(), 7)) {
                    0 -> v2 += "e-"
                    1 -> v2 += "e+"
                    2 -> v2 += "e"
                    3 -> v2 += "E+"
                    4 -> v2 += "E-"
                    5 -> v2 += "E"
                }
                remaining--
                var x = 1
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                    else -> x = 0
                }
                for (i in 0 until remaining) {
                    remaining--
                    v += AssertionFunctions.randomPrintableNumber(nextRandom())
                }
                if (v.length - x > 0) {
                    val dot = abs(l1 % (v.length - x)) + x
                    v = when (dot) {
                        0 -> ".$v"
                        v.length -> ".0"
                        else -> v.substring(0, dot) + "." + v.substring(dot, v.length)
                    }
                    if (dot + 3 < v.length) {
                        val pos = 2 + dot + abs(l2 % (v.length - dot - 3))
                        v = v.substring(0, pos) + v2 + v.substring(pos, v.length)
                    }
                } else {
                    v += ".0e1"
                }
            }
        }
        if (v.startsWith("+.")) {
            return "+0." + v.substring(2, v.length)
        } else if (v.startsWith("-.")) {
            return "-0." + v.substring(2, v.length)
        } else if (v.startsWith(".")) {
            return "0." + v.substring(1, v.length)
        }
        return v
    }

    fun generateDecimalNumber(len: Int): String {
        var remaining = len
        var v = ""
        when (remaining) {
            0 -> v = "0.0"
            1, 2 -> {
                v = nextRandom().toString() + ".0"
            }
            else -> {
                remaining--
                val l = nextRandom()
                remaining--
                var x = 1
                when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                    0 -> v += "+"
                    1 -> v += "-"
                    else -> x = 0
                }
                for (i in 0 until remaining) {
                    remaining--
                    v += AssertionFunctions.randomPrintableNumber(nextRandom())
                }
                v = if (v.length - 1 - x > 0) {
                    when (val l2 = abs(l % (v.length - 1 - x)) + x) {
                        0 -> ".$v"
                        v.length -> ".0"
                        else -> v.substring(0, l2) + "." + v.substring(l2, v.length)
                    }
                } else {
                    ".0"
                }
            }
        }
        if (v.startsWith("+.")) {
            return "+0." + v.substring(2, v.length)
        } else if (v.startsWith("-.")) {
            return "-0." + v.substring(2, v.length)
        } else if (v.startsWith(".")) {
            return "0." + v.substring(1, v.length)
        }
        return v
    }

    fun generateIntegerNumber(len: Int): String {
        var remaining = len
        var v = ""
        if (remaining == 0) {
            v = "0"
        } else if (remaining < 3) {
            v = nextRandom().toString()
        } else {
            remaining--
            when (AssertionFunctions.randomRangePositive(nextRandom(), 3)) {
                0 -> v += "+"
                1 -> v += "-"
            }
            for (i in 0 until remaining) {
                remaining--
                v += AssertionFunctions.randomPrintableNumber(nextRandom())
            }
        }
        return v
    }

    fun bnodeToByteArray_1() {
        resetRandom()
        if (hasNextRandom() > 0) {
            val v = DictionaryValueHelper.fromInt(nextRandom())
            DictionaryHelper.bnodeToByteArray(buffer, v)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BLANK_NODE })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_I(buffer) }, { v })
            AssertionFunctions.assumeException { DictionaryHelper.byteArrayToBnode_S(buffer) }
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_A(buffer) }, { "_:$v" })
        }
    }

    fun bnodeToByteArray_2() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += AssertionFunctions.randomPrintableChar(nextRandom())
        }
        DictionaryHelper.bnodeToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BLANK_NODE })
        AssertionFunctions.assumeException { DictionaryHelper.byteArrayToBnode_I(buffer) }
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBnode_A(buffer) }, { v })
    }

    fun booleanToByteArray1() {
        DictionaryHelper.booleanToByteArray(buffer, true)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BOOLEAN })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBoolean(buffer) }, { true })
    }

    fun booleanToByteArray2() {
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray3() {
        DictionaryHelper.sparqlToByteArray(buffer2, "true")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray4() {
        DictionaryHelper.sparqlToByteArray(buffer2, "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray5() {
        DictionaryHelper.booleanToByteArray(buffer, false)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.BOOLEAN })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToBoolean(buffer) }, { false })
    }

    fun booleanToByteArray6() {
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray7() {
        DictionaryHelper.sparqlToByteArray(buffer2, "false")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray8() {
        DictionaryHelper.sparqlToByteArray(buffer2, "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun booleanToByteArray() {
        resetRandom()
        booleanToByteArray1()
        booleanToByteArray2()
        booleanToByteArray3()
        booleanToByteArray4()
        booleanToByteArray5()
        booleanToByteArray6()
        booleanToByteArray7()
        booleanToByteArray8()
    }

    fun iriToByteArray() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += AssertionFunctions.randomPrintableChar(nextRandom())
        }
        if (verbose) {
            println("iriToByteArray '$v'")
        }
        DictionaryHelper.iriToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.IRI })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToIri(buffer) }, { v })
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun stringToByteArray() {
        resetRandom()
        var v = ""
        for (i in 0 until hasNextRandom()) {
            v += AssertionFunctions.randomPrintableChar(nextRandom())
        }
        if (verbose) {
            println("stringToByteArray '$v'")
        }
        DictionaryHelper.stringToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToString(buffer) }, { v })
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun typedToByteArray() {
        resetRandom()
        var v = ""
        if (hasNextRandom() > 2) {
            val l = nextRandom()
            for (i in 0 until hasNextRandom()) {
                v += AssertionFunctions.randomPrintableLetter(nextRandom())
            }
            val l2 = AssertionFunctions.randomRangePositive(l, v.length - 1)
            val a = v.substring(0, l2)
            val b = v.substring(l2, v.length)
            if (verbose) {
                println("typedToByteArray '$a' '$b'")
            }
            DictionaryHelper.typedToByteArray(buffer, a, b)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING_TYPED })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToTyped_Content(buffer) }, { a })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToTyped_Type(buffer) }, { b })
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
    }

    fun langToByteArray() {
        resetRandom()
        var v = ""
        if (hasNextRandom() > 2) {
            val l = nextRandom()
            for (i in 0 until hasNextRandom()) {
                v += AssertionFunctions.randomPrintableLetter(nextRandom())
            }
            val l2 = AssertionFunctions.randomRangePositive(l, v.length - 1)
            val a = v.substring(0, l2)
            val b = v.substring(l2, v.length)
            if (verbose) {
                println("langToByteArray '$a' '$b'")
            }
            DictionaryHelper.langToByteArray(buffer, a, b)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.STRING_LANG })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToLang_Content(buffer) }, { a })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToLang_Lang(buffer) }, { b.lowercase() })
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
    }

    fun integerToByteArray() {
        resetRandom()
        var v = generateIntegerNumber(hasNextRandom())
        if (v.startsWith('+')) {
            v = v.substring(1)
        }
        while (v.startsWith('0')) {
            v = v.substring(1)
        }
        while (v.startsWith("-0")) {
            v = "-" + v.substring(2)
        }
        if (v == "") {
            v = "0"
        } else if (v == "-") {
            v = "-0"
        }
        if (verbose) {
            println("integerToByteArray '$v'")
        }
        DictionaryHelper.integerToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.INTEGER })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToInteger_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToInteger_I(buffer).toString() }, { v })
        DictionaryHelper.integerToByteArray(buffer2, DictionaryHelper.byteArrayToInteger_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, "\"$v\"^^<http://www.w3.org/2001/XMLSchema#integer>")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, v)
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        val action = {
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
        action()
    }

    fun decimalToByteArray() {
        resetRandom()
        var v = generateDecimalNumber(hasNextRandom())
        if (v.startsWith('+')) {
            v = v.substring(1)
        }
        while (v.startsWith('0')) {
            v = v.substring(1)
        }
        while (v.startsWith("-0")) {
            v = "-" + v.substring(2)
        }
        if (v.contains('.')) {
            while (v.endsWith('0')) {
                v = v.substring(0, v.length - 1)
            }
            if (v.endsWith('.')) {
                v += "0"
            }
            if (v.startsWith('.')) {
                v = "0$v"
            } else if (v.startsWith("-.")) {
                v = "-0" + v.substring(1)
            }
        }
        if (v == "-0.0") {
            v = "0.0"
        }
        if (verbose) {
            println("decimalToByteArray '$v'")
        }
        DictionaryHelper.decimalToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DECIMAL })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDecimal_S(buffer) }, { v })
        DictionaryHelper.decimalToByteArray(buffer2, DictionaryHelper.byteArrayToDecimal_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, "\"$v\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, v)
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        val action = {
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
        action()
    }

    fun floatToByteArray() {
        resetRandom()
        val v1 = generateDoubleNumber(hasNextRandom())
        if (verbose) {
            println("floatToByteArray tmp '$v1'")
        }
        val v = v1.toDouble().toString()
        if (verbose) {
            println("floatToByteArray '$v'")
        }
        DictionaryHelper.floatToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.FLOAT })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToFloat_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToFloat_I(buffer).toString() }, { v })
        DictionaryHelper.floatToByteArray(buffer2, DictionaryHelper.byteArrayToFloat_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun doubleToByteArray() {
        resetRandom()
        val v1 = generateDoubleNumber(hasNextRandom())
        if (verbose) {
            println("doubleToByteArray tmp '$v1'")
        }
        val v = v1.toDouble().toString()
        if (verbose) {
            println("doubleToByteArray '$v'")
        }
        DictionaryHelper.doubleToByteArray(buffer, v)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DOUBLE })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDouble_S(buffer) }, { v })
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDouble_I(buffer).toString() }, { v })
        DictionaryHelper.doubleToByteArray(buffer2, DictionaryHelper.byteArrayToDouble_I(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        DictionaryHelper.sparqlToByteArray(buffer2, "\"$v\"^^<http://www.w3.org/2001/XMLSchema#double>")
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        if (v1.toDouble() == v.toDouble()) {
            if (v1.contains("e") || v1.contains("E")) {
                DictionaryHelper.sparqlToByteArray(buffer2, v1)
                AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
            }
        }
        val action = {
            if (v.contains("e") || v.contains("E")) {
                DictionaryHelper.sparqlToByteArray(buffer2, v)
                AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
            }
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
        }
        action()
    }

    fun errorToByteArray() {
        resetRandom()
        DictionaryHelper.errorToByteArray(buffer)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.ERROR })
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun undefToByteArray() {
        resetRandom()
        DictionaryHelper.undefToByteArray(buffer)
        AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.UNDEF })
        DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
        AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
    }

    fun dateTimeToByteArray() {
        resetRandom()
        if (hasNextRandom() > 9) {
            val month: Int = abs(nextRandom() % 100)
            val day: Int = abs(nextRandom() % 100)
            val hours: Int = abs(nextRandom() % 24)
            val minutes: Int = abs(nextRandom() % 100)
            val timezoneHours: Int = nextRandom() % 24
            val timezoneMinutes: Int = abs(nextRandom() % 100)
            var year: String
            var seconds: String
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
            if (seconds.startsWith("-")) {
                seconds = seconds.substring(1)
            }
            if (seconds.startsWith("+")) {
                seconds = seconds.substring(1)
            }
            if (seconds.contains('.')) {
                while (seconds[seconds.length - 1] == '0') {
                    seconds = seconds.substring(0, seconds.length - 1)
                }
                if (seconds[seconds.length - 1] == '.') {
                    seconds = seconds.substring(0, seconds.length - 1)
                }
            }
            if (seconds.isNotEmpty() && seconds[0] == '.') {
                seconds = seconds.substring(1)
            }
            if (seconds.indexOf('.') > 2) {
                seconds = seconds.substring(seconds.indexOf('.') - 2)
            }
            if (seconds.indexOf('.') >= 0) {
                while (seconds.indexOf('.') < 2) {
                    seconds = "0$seconds"
                }
            }
            if (seconds.contains('.')) {
                if (seconds.indexOf('.') > 2) {
                    seconds = seconds.substring(seconds.indexOf('.') - 2, seconds.length)
                }
            } else {
                if (seconds.length > 2) {
                    seconds = seconds.substring(0, 2)
                }
            }
            while (seconds.length < 2) {
                seconds = "0$seconds"
            }
            if (year[0] == '+') {
                year = year.substring(1)
            }
            while (year.startsWith('0')) {
                year = year.substring(1)
            }
            while (year.startsWith("-0")) {
                year = "-" + year.substring(2)
            }
            if (year == "" || year == "-") {
                year = "0"
            }
            val myseconds = BigDecimal.parseString(seconds, 10)
            val myyear = BigInteger.parseString(year, 10)
            if (verbose) {
                println("dateTimeToByteArray '$year' '$month' '$day' '$hours' '$minutes' '$seconds' '$timezoneHours' '$timezoneMinutes'")
            }
            DictionaryHelper.dateTimeToByteArray(buffer, myyear, month, day, hours, minutes, myseconds, timezoneHours, timezoneMinutes, true)
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToType(buffer) }, { ETripleComponentTypeExt.DATE_TIME })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Month(buffer).toString().toInt() }, { month })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Day(buffer).toString().toInt() }, { day })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Hours(buffer).toString().toInt() }, { hours })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Minutes(buffer).toString().toInt() }, { minutes })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Seconds(buffer) }, { myseconds })
            AssertionFunctions.assumeEQ({ DictionaryHelper.byteArrayToDateTime_Year(buffer).toString() }, { year })
            val tmp = DictionaryHelper.byteArrayToDateTimeAsTyped_Content(buffer)
            DictionaryHelper.dateTimeToByteArray(buffer2, tmp)
            AssertionFunctions.assumeEQ({ tmp }, { DictionaryHelper.byteArrayToDateTimeAsTyped_Content(buffer2) })
            AssertionFunctions.assumeEQ({ buffer }, { buffer2 })
            DictionaryHelper.sparqlToByteArray(buffer2, DictionaryHelper.byteArrayToSparql(buffer))
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
