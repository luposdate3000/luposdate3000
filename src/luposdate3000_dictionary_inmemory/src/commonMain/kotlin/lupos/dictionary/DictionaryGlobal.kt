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
package lupos.dictionary

import lupos.buffermanager.BufferManagerExt
import lupos.fileformat.DictionaryIntermediate
import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.File
import lupos.s00misc.IMyOutputStream
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.MyOutputStream
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import kotlin.jvm.JvmField

public val nodeGlobalDictionary: DictionaryGlobal = DictionaryGlobal()

public class DictionaryGlobal {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun isLocalBNode(value: Int) = (value and DictionaryShared.mask3) == DictionaryShared.flaggedValueLocalBnode

    @JvmField
    internal val localBnodeToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var bNodeCounter = 5

    @JvmField
    internal val bnodeMapToGlobal = mutableMapOf<Int, Int>()

    @JvmField
    internal val iriToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var iriToValue = Array(1) { DictionaryShared.emptyString }

    @JvmField
    internal val langTaggedToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var langTaggedToValue = Array(1) { DictionaryShared.emptyString }

    @JvmField
    internal val typedToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var typedToValue = Array(1) { DictionaryShared.emptyString }

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
    internal var decimalToValue = Array(1) { DictionaryShared.emptyString }

    @JvmField
    internal val intToInt = mutableMapOf<String, Int>()

    @JvmField
    internal var intToValue = Array(1) { DictionaryShared.emptyString }

    @JvmField
    internal var outputDictionaryFile: IMyOutputStream

    @JvmField
    internal var initializationphase = true

    @JvmField
    internal val byteBuf = ByteArray(1)

    public constructor() {
        outputDictionaryFile = MyOutputStream()
        if (!BufferManagerExt.isInMemoryOnly) {
            val filename = BufferManagerExt.bufferPrefix + "dictionary.data"
            outputDictionaryFile = if (File(filename).exists()) {
                importFromDictionaryFile(filename)
                File(filename).openOutputStream(true)
            } else {
                File(filename).openOutputStream(false)
            }
        }
        initializationphase = false
    }

    @ProguardTestAnnotation
    public constructor(bufferManager: BufferManager) : this()

    public fun debugAllDictionaryContent() {
        File("global.dictionary.txt").withOutputStream { out ->
            out.println("0x0 -> true")
            out.println("0x1 -> false")
            out.println("0x2 -> error")
            out.println("0x3 -> undef")
            out.println("0x4 -> null")
            for (i in 0 until iriToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalIri).toString(16)} -> ${iriToValue[i]}")
            }
            for (i in 0 until langTaggedToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalLangTagged).toString(16)} -> ${langTaggedToValue[i]}")
            }
            for (i in 0 until typedToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalTyped).toString(16)} -> ${typedToValue[i]}")
            }
            for (i in 0 until doubleToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalDouble).toString(16)} -> ${doubleToValue[i]}")
            }
            for (i in 0 until floatToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalFloat).toString(16)} -> ${floatToValue[i]}")
            }
            for (i in 0 until decimalToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalDecimal).toString(16)} -> ${decimalToValue[i]}")
            }
            for (i in 0 until intToInt.size) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalInt).toString(16)} -> ${intToValue[i]}")
            }
            for (i in 5 until bNodeCounter) {
                out.println("0x${(i + DictionaryShared.flaggedValueGlobalBnode).toString(16)} -> _:$i")
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
        var lastId = -1
        DictionaryIntermediateReader(filename).readAll { type, id, value ->
            val i = createByType(value, type)
            SanityCheck.check { lastId == id - 1 }
            lastId = id
            if (mapping != null) {
                mapping[id] = i
            }
        }
    }

    public fun prepareBulk(total: Int, typed: IntArray) {
        for (t in 0 until ETripleComponentTypeExt.values_size) {
            when (t) {
                ETripleComponentTypeExt.IRI -> {
                    val tmp = Array(iriToValue.size + typed[t]) { DictionaryShared.emptyString }
                    for (i in iriToValue.indices) {
                        tmp[i] = iriToValue[i]
                    }
                    iriToValue = tmp
                }
                ETripleComponentTypeExt.BLANK_NODE -> {
                }
                ETripleComponentTypeExt.STRING -> {
                    val tmp = Array(typedToValue.size + typed[t]) { DictionaryShared.emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentTypeExt.INTEGER -> {
                    val tmp = Array(intToValue.size + typed[t]) { DictionaryShared.emptyString }
                    for (i in intToValue.indices) {
                        tmp[i] = intToValue[i]
                    }
                    intToValue = tmp
                }
                ETripleComponentTypeExt.DECIMAL -> {
                    val tmp = Array(decimalToValue.size + typed[t]) { DictionaryShared.emptyString }
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
                    val tmp = Array(typedToValue.size + typed[t]) { DictionaryShared.emptyString }
                    for (i in typedToValue.indices) {
                        tmp[i] = typedToValue[i]
                    }
                    typedToValue = tmp
                }
                ETripleComponentTypeExt.STRING_LANG -> {
                    val tmp = Array(langTaggedToValue.size + typed[t]) { DictionaryShared.emptyString }
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

    private fun createByType(s: String, type: ETripleComponentType): Int {
        when (type) {
            ETripleComponentTypeExt.IRI -> {
                val tmp = DictionaryIntermediate.decodeIri(s)
                return createIri(tmp)
            }
            ETripleComponentTypeExt.BLANK_NODE -> {
                return createNewBNode(s)
            }
            ETripleComponentTypeExt.STRING -> {
                val tmp = DictionaryIntermediate.decodeStringAsTyped(s)
                return createTyped(tmp.first, tmp.second)
            }
            ETripleComponentTypeExt.INTEGER -> {
                val tmp = DictionaryIntermediate.decodeInteger(s)
                return createInteger(MyBigInteger(tmp))
            }
            ETripleComponentTypeExt.DECIMAL -> {
                val tmp = DictionaryIntermediate.decodeDecimal(s)
                return createDecimal(MyBigDecimal(tmp))
            }
            ETripleComponentTypeExt.DOUBLE -> {
                val tmp = DictionaryIntermediate.decodeDouble(s)
                return createDouble(tmp.toDouble())
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                val tmp = DictionaryIntermediate.decodeBoolean(s)
                return if (tmp.toLowerCase() == "true") {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
            }
            ETripleComponentTypeExt.STRING_TYPED -> {
                val tmp = DictionaryIntermediate.decodeTyped(s)
                return createTyped(tmp.first, tmp.second)
            }
            ETripleComponentTypeExt.STRING_LANG -> {
                val tmp = DictionaryIntermediate.decodeLang(s)
                return createLangTagged(tmp.first, tmp.second)
            }
            else -> {
                throw Exception("unexpected type")
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun toBooleanOrError(value: Int): Int {
        var res: Int = DictionaryExt.errorValue
        if (value < DictionaryExt.undefValue && value >= 0) {
            res = value
        } else {
            try {
                res = if (getValue(value).toBoolean()) {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
        return res
    }

    public fun createNewBNode(value: String): Int {
        val res: Int = (DictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        appendToFile(ETripleComponentTypeExt.BLANK_NODE, value)
        return res
    }

    public fun createNewBNode(): Int {
        val res: Int = (DictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
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
                val tmp = Array(iriToValue.size * 2) { DictionaryShared.emptyString }
                for (i in iriToValue.indices) {
                    tmp[i] = iriToValue[i]
                }
                iriToValue = tmp
            }
            iriToValue[res] = iri
            appendToFile(ETripleComponentTypeExt.IRI, iri)
            res = res or DictionaryShared.flaggedValueGlobalIri
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalIri
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
                val tmp = Array(langTaggedToValue.size * 2) { DictionaryShared.emptyString }
                for (i in langTaggedToValue.indices) {
                    tmp[i] = langTaggedToValue[i]
                }
                langTaggedToValue = tmp
            }
            langTaggedToValue[res] = key
            appendToFile(ETripleComponentTypeExt.STRING_LANG, "$content@$lang")
            res = res or DictionaryShared.flaggedValueGlobalLangTagged
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalLangTagged
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
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
            }
            else -> {
                val key = "$type>$content"
                val tmp3 = typedToInt[key]
                if (tmp3 == null) {
                    res = typedToInt.size
                    typedToInt[key] = res
                    if (typedToValue.size <= res) {
                        val tmp = Array(typedToValue.size * 2) { DictionaryShared.emptyString }
                        for (i in typedToValue.indices) {
                            tmp[i] = typedToValue[i]
                        }
                        typedToValue = tmp
                    }
                    typedToValue[res] = key
                    appendToFile(ETripleComponentTypeExt.STRING_TYPED, "$content^^$type")
                    res = res or DictionaryShared.flaggedValueGlobalTyped
                } else {
                    res = tmp3 or DictionaryShared.flaggedValueGlobalTyped
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
            res = res or DictionaryShared.flaggedValueGlobalDouble
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalDouble
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
            res = res or DictionaryShared.flaggedValueGlobalFloat
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalFloat
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
                val tmp = Array(decimalToValue.size * 2) { DictionaryShared.emptyString }
                for (i in decimalToValue.indices) {
                    tmp[i] = decimalToValue[i]
                }
                decimalToValue = tmp
            }
            decimalToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#decimal>")
            res = res or DictionaryShared.flaggedValueGlobalDecimal
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalDecimal
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
                val tmp = Array(intToValue.size * 2) { DictionaryShared.emptyString }
                for (i in intToValue.indices) {
                    tmp[i] = intToValue[i]
                }
                intToValue = tmp
            }
            intToValue[res] = value
            appendToFile(ETripleComponentTypeExt.STRING_TYPED, "\"$value\"^^<http://www.w3.org/2001/XMLSchema#integer>")
            res = res or DictionaryShared.flaggedValueGlobalInt
        } else {
            res = tmp3 or DictionaryShared.flaggedValueGlobalInt
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
                res = DictionaryExt.undefValue
            }
            is ValueError -> {
                res = DictionaryExt.errorValue
            }
            is ValueBnode -> {
                res = createNewBNode(value.value)
            }
            is ValueBoolean -> {
                res = if (value.value) {
                    DictionaryExt.booleanTrueValue
                } else {
                    DictionaryExt.booleanFalseValue
                }
            }
            is ValueLanguageTaggedLiteral -> {
                res = createLangTagged(value.content, value.language)
            }
            is ValueSimpleLiteral -> {
                res = createTyped(value.content, DictionaryShared.emptyString)
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
        when (value and DictionaryShared.mask3) {
            DictionaryShared.flaggedValueLocalIri -> {
                res = ValueIri(iriToValue[value and DictionaryShared.filter3])
            }
            DictionaryShared.flaggedValueLocalBnode -> {
                when (value and DictionaryShared.mask3) {
                    0 -> {
                        res = DictionaryExt.booleanTrueValue2
                    }
                    1 -> {
                        res = DictionaryExt.booleanFalseValue2
                    }
                    2 -> {
                        res = DictionaryExt.errorValue2
                    }
                    3 -> {
                        res = DictionaryExt.undefValue2
                    }
                    else -> {
                        res = ValueBnode(DictionaryShared.emptyString + value)
                    }
                }
            }
            DictionaryShared.flaggedValueLocalTyped -> {
                val tmp = typedToValue[value and DictionaryShared.filter3]
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
                val bit5 = value and DictionaryShared.mask6
                res = when (bit5) {
                    DictionaryShared.flaggedValueLocalInt -> {
                        ValueInteger(MyBigInteger(intToValue[value and DictionaryShared.filter6]))
                    }
                    DictionaryShared.flaggedValueLocalDecimal -> {
                        ValueDecimal(MyBigDecimal(decimalToValue[value and DictionaryShared.filter6]))
                    }
                    DictionaryShared.flaggedValueLocalDouble -> {
                        ValueDouble(doubleToValue[value and DictionaryShared.filter6])
                    }
                    DictionaryShared.flaggedValueLocalFloat -> {
                        ValueFloat(floatToValue[value and DictionaryShared.filter6])
                    }
                    else -> {
                        val tmp = langTaggedToValue[value and DictionaryShared.filter6]
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
        when (value and DictionaryShared.mask3) {
            DictionaryShared.flaggedValueLocalIri -> {
                onIri(iriToValue[value and DictionaryShared.filter3])
            }
            DictionaryShared.flaggedValueLocalBnode -> {
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
            DictionaryShared.flaggedValueLocalTyped -> {
                val tmp = typedToValue[value and DictionaryShared.filter3]
                val idx = tmp.indexOf(">")
                if (idx == 0) {
                    onSimpleLiteral(tmp.substring(idx + 2, tmp.length - 1))
                } else {
                    onTypedLiteral(tmp.substring(idx + 3, tmp.length - 1), tmp.substring(1, idx))
                }
            }
            else -> {
                when (value and DictionaryShared.mask6) {
                    DictionaryShared.flaggedValueLocalInt -> {
                        onInteger(intToValue[value and DictionaryShared.filter6])
                    }
                    DictionaryShared.flaggedValueLocalDecimal -> {
                        onDecimal(decimalToValue[value and DictionaryShared.filter6])
                    }
                    DictionaryShared.flaggedValueLocalDouble -> {
                        onDouble(doubleToValue[value and DictionaryShared.filter6])
                    }
                    DictionaryShared.flaggedValueLocalFloat -> {
                        onFloat(floatToValue[value and DictionaryShared.filter6])
                    }
                    else -> {
                        val tmp = langTaggedToValue[value and DictionaryShared.filter6]
                        val idx = tmp.indexOf("@")
                        onLanguageTaggedLiteral(tmp.substring(idx + 2, tmp.length - 1), tmp.substring(0, idx))
                    }
                }
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun valueToGlobal(value: Int): Int {
        val res: Int
        if ((value and DictionaryShared.mask1) == DictionaryShared.mask1) {
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
