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

import lupos.fileformat.DictionaryIntermediate
import lupos.fileformat.DictionaryIntermediateReader
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import kotlin.jvm.JvmField

public val nodeGlobalDictionary: ResultSetDictionaryGlobal = ResultSetDictionaryGlobal()

public class ResultSetDictionaryGlobal {
    //    @JvmField
//    internal val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("dictionary")
    private val mappingV2I = mutableMapOf<Pair<Int, String>, Int>()
    private val mappingI2V = mutableListOf<Pair<Int, String>>()
    internal inline fun writeValue(value: String, type: Int): Int {
        val v = Pair(type, value)
        var res = mappingV2I[v]
        if (res == null) {
            res = mappingI2V.size
            mappingI2V.add(v)
            mappingV2I[v] = res
        }
        return res or ResultSetDictionaryShared.flaggedValueGlobal
    }

    internal inline fun hasValue(value: String, type: Int): Int? {
        val v = Pair(type, value)
        var res = mappingV2I[v]
        if (res == null) {
            return null
        }
        return res or ResultSetDictionaryShared.flaggedValueGlobal
    }

    internal inline fun readValue(value: Int, crossinline action: (value: String, type: Int) -> Unit) {
        var res = mappingI2V[value and ResultSetDictionaryShared.filter2]
        action(res.second!!, res.first!!)
    }

    @JvmField
    internal var bNodeCounter = 5

    public fun debugAllDictionaryContent() {
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
            val i = createTyped(value, type)
            SanityCheck.check { lastId == id - 1 }
            lastId = id
            if (mapping != null) {
                mapping[id] = i
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun createTyped(value: String, type: Int): Int {
        if (type == ETripleComponentTypeExt.BLANK_NODE) {
            return createNewBNode(value)
        } else {
            return writeValue(value, type)
        }
    }

    public fun prepareBulk(total: Int, typed: IntArray) {
    }

    public fun createNewBNode(value: String): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
        return res
    }

    public fun createNewBNode(): Int {
        val res: Int = (ResultSetDictionaryShared.flaggedValueGlobalBnode or (bNodeCounter++))
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
                val tmp = DictionaryIntermediate.encodeLang("\"${value.content}\"", value.language)
                res = writeValue(tmp, ETripleComponentTypeExt.STRING_LANG)
            }
            is ValueSimpleLiteral -> {
                val tmp = DictionaryIntermediate.encodeString("\"${value.content}\"")
                res = writeValue(tmp, ETripleComponentTypeExt.STRING)
            }
            is ValueTypedLiteral -> {
                val tmp = DictionaryIntermediate.encodeTyped("\"${value.content}\"", "<${value.type_iri}>")
                res = writeValue(tmp, ETripleComponentTypeExt.STRING_TYPED)
            }
            is ValueDecimal -> {
                val tmp = DictionaryIntermediate.encodeDecimal(value.value.toString())
                res = writeValue(tmp, ETripleComponentTypeExt.DECIMAL)
            }
            is ValueDouble -> {
                val tmp = DictionaryIntermediate.encodeDouble(value.value.toString())
                res = writeValue(tmp, ETripleComponentTypeExt.DOUBLE)
            }
            is ValueFloat -> {
                val tmp = DictionaryIntermediate.encodeTyped("\"${value.value}\"", "<http://www.w3.org/2001/XMLSchema#float>")
                res = writeValue(tmp, ETripleComponentTypeExt.STRING_TYPED)
            }
            is ValueInteger -> {
                val tmp = DictionaryIntermediate.encodeInteger("" + value.value)
                res = writeValue(tmp, ETripleComponentTypeExt.INTEGER)
            }
            is ValueIri -> {
                val tmp = DictionaryIntermediate.encodeIri("<${value.iri}>")
                res = writeValue(tmp, ETripleComponentTypeExt.IRI)
            }
            is ValueDateTime -> {
                val tmp = value.valueToString()
                val tmp2 = DictionaryIntermediate.encodeTyped(tmp.substring(0, tmp.length - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "<http://www.w3.org/2001/XMLSchema#dateTime>")
                res = writeValue(tmp2, ETripleComponentTypeExt.STRING_TYPED)
            }
        }
        SanityCheck {
            val tmp2 = getValue(res)
            SanityCheck.check({ (value is ValueBnode && tmp2 is ValueBnode) || (value is ValueError && tmp2 is ValueError) || tmp2 == value || (value is ValueSimpleLiteral && tmp2 is ValueTypedLiteral && tmp2.type_iri == "http://www.w3.org/2001/XMLSchema#string" && tmp2.content == value.content) }, { "$value (${value.toSparql()}) -> ${res.toString(16)} -> $tmp2 (${tmp2.toSparql()})" })
        }
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun hasValue(value: ValueDefinition): Int? {
        val res: Int?
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
                val tmp = DictionaryIntermediate.encodeLang("\"${value.content}\"", value.language)
                res = hasValue(tmp, ETripleComponentTypeExt.STRING_LANG)
            }
            is ValueSimpleLiteral -> {
                val tmp = DictionaryIntermediate.encodeString("\"${value.content}\"")
                res = hasValue(tmp, ETripleComponentTypeExt.STRING)
            }
            is ValueTypedLiteral -> {
                val tmp = DictionaryIntermediate.encodeTyped("\"${value.content}\"", "<${value.type_iri}>")
                res = hasValue(tmp, ETripleComponentTypeExt.STRING_TYPED)
            }
            is ValueDecimal -> {
                val tmp = DictionaryIntermediate.encodeDecimal(value.value.toString())
                res = hasValue(tmp, ETripleComponentTypeExt.DECIMAL)
            }
            is ValueDouble -> {
                val tmp = DictionaryIntermediate.encodeDouble(value.value.toString())
                res = hasValue(tmp, ETripleComponentTypeExt.DOUBLE)
            }
            is ValueFloat -> {
                val tmp = DictionaryIntermediate.encodeTyped("\"${value.value}\"", "<http://www.w3.org/2001/XMLSchema#float>")
                res = hasValue(tmp, ETripleComponentTypeExt.STRING_TYPED)
            }
            is ValueInteger -> {
                val tmp = DictionaryIntermediate.encodeInteger("" + value.value)
                res = hasValue(tmp, ETripleComponentTypeExt.INTEGER)
            }
            is ValueIri -> {
                val tmp = DictionaryIntermediate.encodeIri("<${value.iri}>")
                res = hasValue(tmp, ETripleComponentTypeExt.IRI)
            }
            is ValueDateTime -> {
                val tmp = value.valueToString()
                val tmp2 = DictionaryIntermediate.encodeTyped(tmp.substring(0, tmp.length - "^^<http://www.w3.org/2001/XMLSchema#dateTime>".length), "<http://www.w3.org/2001/XMLSchema#dateTime>")
                res = hasValue(tmp2, ETripleComponentTypeExt.STRING_TYPED)
            }
        }
        return res
    }

    public fun getValue(value: Int): ValueDefinition {
        var res: ValueDefinition

        if (value and ResultSetDictionaryShared.mask2 == ResultSetDictionaryShared.flaggedValueLocalBnode) {
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
        } else {
            res = ResultSetDictionaryExt.errorValue2
            readValue(value) { value, type ->
                when (type) {
                    ETripleComponentTypeExt.DECIMAL -> res = ValueDecimal(MyBigDecimal(DictionaryIntermediate.decodeDecimal(value)))
                    ETripleComponentTypeExt.DOUBLE -> res = ValueDouble(DictionaryIntermediate.decodeDouble(value).toDouble())
                    ETripleComponentTypeExt.INTEGER -> res = ValueInteger(MyBigInteger(DictionaryIntermediate.decodeInteger(value)))
                    ETripleComponentTypeExt.IRI -> res = ValueIri(DictionaryIntermediate.decodeIri(value))
                    ETripleComponentTypeExt.STRING -> res = ValueSimpleLiteral("\"", DictionaryIntermediate.decodeString(value))
                    ETripleComponentTypeExt.STRING_LANG -> {
                        val tmp = DictionaryIntermediate.decodeLang(value)
                        res = ValueLanguageTaggedLiteral("\"", tmp.first, tmp.second)
                    }
                    ETripleComponentTypeExt.STRING_TYPED -> {
                        val tmp = DictionaryIntermediate.decodeTyped(value)
                        res = ValueTypedLiteral("\"", tmp.first, tmp.second)
                    }
                    else -> throw Exception("unexpected type $type")
                }
            }
            if (res == ResultSetDictionaryExt.errorValue2) {
                throw Exception("action not called")
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
        if (value and ResultSetDictionaryShared.mask2 == ResultSetDictionaryShared.flaggedValueLocalBnode) {
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
        } else {
            readValue(value) { value, type ->
                when (type) {
                    ETripleComponentTypeExt.DECIMAL -> onDecimal(DictionaryIntermediate.decodeDecimal(value))
                    ETripleComponentTypeExt.DOUBLE -> onDouble(DictionaryIntermediate.decodeDouble(value).toDouble())
                    ETripleComponentTypeExt.INTEGER -> onInteger(DictionaryIntermediate.decodeInteger(value))
                    ETripleComponentTypeExt.IRI -> onIri(DictionaryIntermediate.decodeIri(value))
                    ETripleComponentTypeExt.STRING -> onSimpleLiteral(DictionaryIntermediate.decodeString(value))
                    ETripleComponentTypeExt.STRING_LANG -> {
                        val tmp = DictionaryIntermediate.decodeLang(value)
                        onLanguageTaggedLiteral(tmp.first, tmp.second)
                    }
                    ETripleComponentTypeExt.STRING_TYPED -> {
                        val tmp = DictionaryIntermediate.decodeTyped(value)
                        onTypedLiteral(tmp.first, tmp.second)
                    }
                    else -> throw Exception("unexpected type $type")
                }
            }
        }
    }
}
