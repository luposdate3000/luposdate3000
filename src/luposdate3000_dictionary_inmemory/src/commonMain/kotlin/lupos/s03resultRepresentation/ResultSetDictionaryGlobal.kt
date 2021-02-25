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
package lupos.s03resultRepresentation

import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.MyOutputStream
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManagerExt
import kotlin.jvm.JvmField

public val nodeGlobalDictionary: ResultSetDictionaryGlobal = ResultSetDictionaryGlobal()

public class ResultSetDictionaryGlobal {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isLocalBNode(value: Int) = (value and ResultSetDictionaryShared.mask3) == ResultSetDictionaryShared.flaggedValueLocalBnode

    @JvmField
    internal val localBnodeToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var bNodeCounter = 5

    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<Int, Int>()

    @JvmField
    internal val iriToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var iriToValue = Array(1) { ResultSetDictionaryShared.emptyString }

    @JvmField
    internal val langTaggedToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var langTaggedToValue = Array(1) { ResultSetDictionaryShared.emptyString }

    @JvmField
    internal val typedToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var typedToValue = Array(1) { ResultSetDictionaryShared.emptyString }

    @JvmField
    internal val doubleToInt = mutableMapOf<Double, Int>()

    @JvmField
    internal var doubleToValue = DoubleArray(1) { 0.0 }

    @JvmField
    internal val floatToInt = mutableMapOf<Double, Int>()

    @JvmField
    internal var floatToValue = DoubleArray(1) { 0.0 }

    @JvmField
    internal val decimalToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var decimalToValue = Array(1) { ResultSetDictionaryShared.emptyString }

    @JvmField
    internal val intToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var intToValue = Array(1) { ResultSetDictionaryShared.emptyString }

    @JvmField
    internal var outputDictionaryFile: IMyOutputStream

    @JvmField
    internal var initializationphase = true

    @JvmField
    internal val byteBuf = ByteArray(1)

    init {
        outputDictionaryFile = MyOutputStream()
        if (!BufferManagerExt.isInMemoryOnly) {
            outputDictionaryFile = if (BufferManagerExt.initializedFromDisk) {
                importFromDictionaryFile(BufferManagerExt.bufferPrefix + "dictionary.data")
                File(BufferManagerExt.bufferPrefix + "dictionary.data").openOutputStream(true)
            } else {
                File(BufferManagerExt.bufferPrefix + "dictionary.data").openOutputStream(false)
            }
        }
        initializationphase = false
    }

    public fun debugAllDictionaryContent() {
        File("global.dictionary").withOutputStream { out ->
            out.println("0x0 -> true")
            out.println("0x1 -> false")
            out.println("0x2 -> error")
            out.println("0x3 -> undef")
            out.println("0x4 -> null")
            for (i in 0 until iriToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalIri).toString(16)} -> ${iriToValue[i]}")
            }
            for (i in 0 until langTaggedToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalLangTagged).toString(16)} -> ${langTaggedToValue[i]}")
            }
            for (i in 0 until typedToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalTyped).toString(16)} -> ${typedToValue[i]}")
            }
            for (i in 0 until doubleToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalDouble).toString(16)} -> ${doubleToValue[i]}")
            }
            for (i in 0 until floatToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalFloat).toString(16)} -> ${floatToValue[i]}")
            }
            for (i in 0 until decimalToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalDecimal).toString(16)} -> ${decimalToValue[i]}")
            }
            for (i in 0 until intToInt.size) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalInt).toString(16)} -> ${intToValue[i]}")
            }
            for (i in 5 until bNodeCounter) {
                out.println("0x${(i + ResultSetDictionaryShared.flaggedValueGlobalBnode).toString(16)} -> _:$i")
            }
        }
    }

    public fun importFromDictionaryFile(filename: String, mapping: IntArray) {
        importFromDictionaryFileH(filename, mapping)
    }

    public fun importFromDictionaryFile(filename: String) {
        importFromDictionaryFileH(filename, null)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun importFromDictionaryFileH(filename: String, mapping: IntArray?) {
        val fileDictionary = File(filename)
        var buffer = ByteArray(0)
        var mappingIdx = 0
        fileDictionary.withInputStream { dictStream ->
            while (true) {
                var length = 0
                try {
                    length = dictStream.readInt()
                } catch (e: Exception) {
// TODO more nice end of file detection
                    break
                }
                val type = dictStream.readByte().toInt()
                if (buffer.size < length) {
                    buffer = ByteArray(length)
                }
                val read = dictStream.read(buffer, 0, length)
                if (read < length) {
                    throw Exception("invalid read")
                }
                val s = buffer.decodeToString(0, length)
                val i = createByType(s, type)
                if (mapping != null) {
                    mapping[mappingIdx++] = i
                }
            }
        }
    }

    public fun prepareBulk(total: Int, typed: IntArray) {
        for (t in 0 until ETripleComponentTypeExt.values_size) {
            when (t) {
                ETripleComponentTypeExt.IRI -> {
                    val tmp = Array(iriToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in iriToValue.indices) {
                        tmp[i] = iriToValue[i]
                    }
                    iriToValue = tmp
                }
                ETripleComponentTypeExt.BLANK_NODE -> {
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp = Array(typedToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp = Array(intToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in intToValue.indices) {
                        tmp[i] = intToValue[i]
                    }
                    intToValue = tmp
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp = Array(decimalToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in decimalToValue.indices) {
                        tmp[i] = decimalToValue[i]
                    }
                    decimalToValue = tmp
                }
                ETripleComponentTypeExt.DOUBLE -> {
                    val tmp = DoubleArray(doubleToValue.size + typed[t]) { 0.0 }
                    for (i in doubleToValue.indices) {
                        tmp[i] = doubleToValue[i]
                    }
                    doubleToValue = tmp
                }
                ETripleComponentTypeExt.BOOLEAN -> {
                }
                ETripleComponentTypeExt.STRING_TYPED -> {
                    val tmp = Array(typedToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp = Array(langTaggedToValue.size + typed[t]) { ResultSetDictionaryShared.emptyString }
                    for (i in langTaggedToValue.indices) {
                        tmp[i] = langTaggedToValue[i]
                    }
                    langTaggedToValue = tmp
                }
                else -> {
                    throw Exception("unexpected type")
                }
            }
        }
    }

    public fun createByType(s: String, type: ETripleComponentType): Int {
        when (type) {
            ETripleComponentTypeExt.IRI -> {
                return createIri(s)
            }
            ETripleComponentTypeExt.BLANK_NODE -> {
                return createNewBNode(s)
            }
            ETripleComponentTypeExt.STRING -> {
                return createTyped(s, "")
            }
            ETripleComponentTypeExt.INTEGER -> {
                return createInteger(MyBigInteger(s))
            }
            ETripleComponentTypeExt.DECIMAL -> {
                return createDecimal(MyBigDecimal(s))
            }
            ETripleComponentTypeExt.DOUBLE -> {
                return createDouble(s.toDouble())
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                return if (s.toLowerCase() == "true") {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.STRING_TYPED -> {
                val s2 = s.split("^^")
                var a = s2[0]
                for (i in 1 until s2.size - 1) {
                    a += "^^" + s2[i]
                }
                val b = s2[s2.size - 1]
                return createTyped(a, b)
            }
            ETripleComponentTypeExt.STRING_LANG -> {
                val s2 = s.split("@")
                var a = s2[0]
                for (i in 1 until s2.size - 1) {
                    a += "@" + s2[i]
                }
                val b = s2[s2.size - 1]
                return createLangTagged(a, b)
            }
            else -> {
                throw Exception("unexpected type")
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toBooleanOrError(value: Int): Int {
        var res: Int = ResultSetDictionaryExt.errorValue
        if (value < ResultSetDictionaryExt.undefValue && value >= 0) {
            res = value
        } else {
            try {
                res = if (getValue(value).toBoolean()) {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return res
    }

    public fun createNewBNode(value: String): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        appendToFile(ETripleComponentTypeExt.BLANK_NODE, value)
        return res
    }

    public fun createNewBNode(): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        appendToFile(ETripleComponentTypeExt.BLANK_NODE, "")
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun appendToFile(type: ETripleComponentType, data: String) {
        if (!BufferManagerExt.isInMemoryOnly && !initializationphase) {
            val tmp = data.encodeToByteArray()
            byteBuf[0] = type.toByte()
            outputDictionaryFile.writeInt(tmp.size)
            outputDictionaryFile.write(byteBuf)
            outputDictionaryFile.write(tmp)
            outputDictionaryFile.flush()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createIri(iri: String): Int {
        var res: Int
        val tmp3 = iriToInt[iri]
        if (tmp3 == null) {
            res = iriToInt.size
            iriToInt[iri] = res
            if (iriToValue.size <= res) {
                val tmp = Array(iriToValue.size * 2) { ResultSetDictionaryShared.emptyString }
                for (i in iriToValue.indices) {
                    tmp[i] = iriToValue[i]
                }
                iriToValue = tmp
            }
            iriToValue[res] = iri
            appendToFile(ETripleComponentTypeExt.IRI, iri)
            res = res or ResultSetDictionaryShared.flaggedValueGlobalIri
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalIri
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createLangTagged(content: String, lang: String): Int {
        var res: Int
        val key = "$lang@$content"
        val tmp3 = langTaggedToInt[key]
        if (tmp3 == null) {
            res = langTaggedToInt.size
            langTaggedToInt[key] = res
            if (langTaggedToValue.size <= res) {
                val tmp = Array(langTaggedToValue.size * 2) { ResultSetDictionaryShared.emptyString }
                for (i in langTaggedToValue.indices) {
                    tmp[i] = langTaggedToValue[i]
                }
                langTaggedToValue = tmp
            }
            langTaggedToValue[res] = key
            appendToFile(ETripleComponentTypeExt.STRING_LANG, "$content@$lang")
            res = res or ResultSetDictionaryShared.flaggedValueGlobalLangTagged
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalLangTagged
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createTyped(content: String, type: String): Int {
        var res: Int
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> {
                res = createInteger(MyBigInteger(content))
            }
            "http://www.w3.org/2001/XMLSchema#decimal" -> {
                res = createDecimal(MyBigDecimal(content))
            }
            "http://www.w3.org/2001/XMLSchema#double" -> {
                res = createDouble(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#float" -> {
                res = createFloat(content.toDouble())
            }
            "http://www.w3.org/2001/XMLSchema#boolean" -> {
                res = if (content == "true") {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            }
            else -> {
                val key = "$type>$content"
                val tmp3 = typedToInt[key]
                if (tmp3 == null) {
                    res = typedToInt.size
                    typedToInt[key] = res
                    if (typedToValue.size <= res) {
                        val tmp = Array(typedToValue.size * 2) { ResultSetDictionaryShared.emptyString }
                        for (i in typedToValue.indices) {
                            tmp[i] = typedToValue[i]
                        }
                        typedToValue = tmp
                    }
                    typedToValue[res] = key
                    appendToFile(ETripleComponentTypeExt.STRING_TYPED, "$content^^$type")
                    res = res or ResultSetDictionaryShared.flaggedValueGlobalTyped
                } else {
                    res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalTyped
                }
            }
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createDouble(value: Double): Int {
        var res: Int
        val tmp3 = doubleToInt[value]
        if (tmp3 == null) {
            res = doubleToInt.size
            doubleToInt[value] = res
            if (doubleToValue.size <= res) {
                val tmp = DoubleArray(doubleToValue.size * 2) { 0.0 }
                for (i in doubleToValue.indices) {
                    tmp[i] = doubleToValue[i]
                }
                doubleToValue = tmp
            }
            doubleToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#double>")
            res = res or ResultSetDictionaryShared.flaggedValueGlobalDouble
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalDouble
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createFloat(value: Double): Int {
        var res: Int
        val tmp3 = floatToInt[value]
        if (tmp3 == null) {
            res = floatToInt.size
            floatToInt[value] = res
            if (floatToValue.size <= res) {
                val tmp = DoubleArray(floatToValue.size * 2) { 0.0 }
                for (i in floatToValue.indices) {
                    tmp[i] = floatToValue[i]
                }
                floatToValue = tmp
            }
            floatToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#float>")
            res = res or ResultSetDictionaryShared.flaggedValueGlobalFloat
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalFloat
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createDecimal(value2: MyBigDecimal): Int {
        val value = value2.toString()
        var res: Int
        val tmp3 = decimalToInt[value]
        if (tmp3 == null) {
            res = decimalToInt.size
            decimalToInt[value] = res
            if (decimalToValue.size <= res) {
                val tmp = Array(decimalToValue.size * 2) { ResultSetDictionaryShared.emptyString }
                for (i in decimalToValue.indices) {
                    tmp[i] = decimalToValue[i]
                }
                decimalToValue = tmp
            }
            decimalToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
            res = res or ResultSetDictionaryShared.flaggedValueGlobalDecimal
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalDecimal
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createInteger(value2: MyBigInteger): Int {
        val value = value2.toString()
        var res: Int
        val tmp3 = intToInt[value]
        if (tmp3 == null) {
            res = intToInt.size
            intToInt[value] = res
            if (intToValue.size <= res) {
                val tmp = Array(intToValue.size * 2) { ResultSetDictionaryShared.emptyString }
                for (i in intToValue.indices) {
                    tmp[i] = intToValue[i]
                }
                intToValue = tmp
            }
            intToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#integer>")
            res = res or ResultSetDictionaryShared.flaggedValueGlobalInt
        } else {
            res = tmp3 or ResultSetDictionaryShared.flaggedValueGlobalInt
        }
        return res
    }

    public fun createValue(value: String?): Int {
        val res = createValue(ValueDefinition(value))
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createValue(value: ValueDefinition): Int {
        val res: Int
        when (value) {
            is ValueUndef -> {
                res = ResultSetDictionaryExt.undefValue
            }
            is ValueError -> {
                res = ResultSetDictionaryExt.errorValue
            }
            is ValueBnode -> {
                res = createNewBNode(value.value)
            }
            is ValueBoolean -> {
                res = if (value.value) {
                    ResultSetDictionaryExt.booleanTrueValue
                } else {
                    ResultSetDictionaryExt.booleanFalseValue
                }
            }
            is ValueLanguageTaggedLiteral -> {
                res = createLangTagged(value.content, value.language)
            }
            is ValueSimpleLiteral -> {
                res = createTyped(value.content, ResultSetDictionaryShared.emptyString)
            }
            is ValueTypedLiteral -> {
                res = createTyped(value.content, value.type_iri)
            }
            is ValueDecimal -> {
                res = createDecimal(value.value)
            }
            is ValueDouble -> {
                res = createDouble(value.value)
            }
            is ValueFloat -> {
                res = createFloat(value.value)
            }
            is ValueInteger -> {
                res = createInteger(value.value)
            }
            is ValueIri -> {
                res = createIri(value.iri)
            }
            is ValueDateTime -> {
                val tmp = value.valueToString()
                res = createTyped(tmp.substring(1, tmp.length - 1 - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "http://www.w3.org/2001/XMLSchema#dateTime")
            }
        }
        SanityCheck {
            val tmp2 = getValue(res)
            SanityCheck.check({ (value is ValueBnode && tmp2 is ValueBnode) || (value is ValueError && tmp2 is ValueError) || tmp2 == value || (value is ValueSimpleLiteral && tmp2 is ValueTypedLiteral && tmp2.type_iri == "http://www.w3.org/2001/XMLSchema#string" && tmp2.content == value.content) }, { "$value (${value.toSparql()}) -> ${res.toString(16)} -> $tmp2 (${tmp2.toSparql()})" })
        }
        return res
    }

    public fun getValue(value: Int): ValueDefinition {
        val res: ValueDefinition
        when (value and ResultSetDictionaryShared.mask3) {
            ResultSetDictionaryShared.flaggedValueLocalIri -> {
                res = ValueIri(iriToValue[value and ResultSetDictionaryShared.filter3])
            }
            ResultSetDictionaryShared.flaggedValueLocalBnode -> {
                when (value) {
                    0 -> {
                        res = ResultSetDictionaryExt.booleanTrueValue2
                    }
                    1 -> {
                        res = ResultSetDictionaryExt.booleanFalseValue2
                    }
                    2 -> {
                        res = ResultSetDictionaryExt.errorValue2
                    }
                    3 -> {
                        res = ResultSetDictionaryExt.undefValue2
                    }
                    else -> {
                        res = ValueBnode(ResultSetDictionaryShared.emptyString + value)
                    }
                }
            }
            ResultSetDictionaryShared.flaggedValueLocalTyped -> {
                val tmp = typedToValue[value and ResultSetDictionaryShared.filter3]
                val idx = tmp.indexOf(">")
                val type = tmp.substring(0, idx)
                val content = tmp.substring(idx + 1, tmp.length)
                res = if (idx == 0) {
                    ValueSimpleLiteral("\"", content)
                } else {
                    ValueTypedLiteral("\"", content, type)
                }
            }
            else -> {
                val bit5 = value and ResultSetDictionaryShared.mask6
                res = when (bit5) {
                    ResultSetDictionaryShared.flaggedValueLocalInt -> {
                        ValueInteger(MyBigInteger(intToValue[value and ResultSetDictionaryShared.filter6]))
                    }
                    ResultSetDictionaryShared.flaggedValueLocalDecimal -> {
                        ValueDecimal(MyBigDecimal(decimalToValue[value and ResultSetDictionaryShared.filter6]))
                    }
                    ResultSetDictionaryShared.flaggedValueLocalDouble -> {
                        ValueDouble(doubleToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    ResultSetDictionaryShared.flaggedValueLocalFloat -> {
                        ValueFloat(floatToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    else -> {
                        val tmp = langTaggedToValue[value and ResultSetDictionaryShared.filter6]
                        val idx = tmp.indexOf("@")
                        val lang = tmp.substring(0, idx)
                        val content = tmp.substring(idx + 1, tmp.length)
                        ValueLanguageTaggedLiteral("\"", content, lang)
                    }
                }
            }
        }
        return res
    }

    internal inline fun getValue(
        value: Int,
        onBNode: (value: Int) -> Unit,
        onBoolean: (value: Boolean) -> Unit,
        onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        onSimpleLiteral: (content: String) -> Unit,
        onTypedLiteral: (content: String, type: String) -> Unit,
        onDecimal: (value: String) -> Unit,
        onFloat: (value: Double) -> Unit,
        onDouble: (value: Double) -> Unit,
        onInteger: (value: String) -> Unit,
        onIri: (value: String) -> Unit,
        onError: () -> Unit,
        onUndefined: () -> Unit
    ) {
        when (value and ResultSetDictionaryShared.mask3) {
            ResultSetDictionaryShared.flaggedValueLocalIri -> {
                onIri(iriToValue[value and ResultSetDictionaryShared.filter3])
            }
            ResultSetDictionaryShared.flaggedValueLocalBnode -> {
                when (value) {
                    0 -> {
                        onBoolean(true)
                    }
                    1 -> {
                        onBoolean(false)
                    }
                    2 -> {
                        onError()
                    }
                    3 -> {
                        onUndefined()
                    }
                    else -> {
                        onBNode(value)
                    }
                }
            }
            ResultSetDictionaryShared.flaggedValueLocalTyped -> {
                val tmp = typedToValue[value and ResultSetDictionaryShared.filter3]
                val idx = tmp.indexOf(">")
                if (idx == 0) {
                    onSimpleLiteral(tmp.substring(idx + 1, tmp.length))
                } else {
                    onTypedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                }
            }
            else -> {
                when (value and ResultSetDictionaryShared.mask6) {
                    ResultSetDictionaryShared.flaggedValueLocalInt -> {
                        onInteger(intToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    ResultSetDictionaryShared.flaggedValueLocalDecimal -> {
                        onDecimal(decimalToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    ResultSetDictionaryShared.flaggedValueLocalDouble -> {
                        onDouble(doubleToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    ResultSetDictionaryShared.flaggedValueLocalFloat -> {
                        onFloat(floatToValue[value and ResultSetDictionaryShared.filter6])
                    }
                    else -> {
                        val tmp = langTaggedToValue[value and ResultSetDictionaryShared.filter6]
                        val idx = tmp.indexOf("@")
                        onLanguageTaggedLiteral(tmp.substring(idx + 1, tmp.length), tmp.substring(0, idx))
                    }
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun valueToGlobal(value: Int): Int {
        val res: Int
        if ((value and ResultSetDictionaryShared.mask1) == ResultSetDictionaryShared.mask1) {
            res = value
        } else {
            if (isLocalBNode(value)) {
                val tmp = bnodeMapToGlobal[value]
                if (tmp == null) {
                    res = nodeGlobalDictionary.createNewBNode()
                    bnodeMapToGlobal[value] = res
                } else {
                    res = tmp
                }
            } else {
                res = nodeGlobalDictionary.createValue(getValue(value))
            }
        }
        return res
    }
}
