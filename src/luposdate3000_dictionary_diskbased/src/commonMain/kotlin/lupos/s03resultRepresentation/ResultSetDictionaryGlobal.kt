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
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import kotlin.jvm.JvmField

public val nodeGlobalDictionary: ResultSetDictionaryGlobal = ResultSetDictionaryGlobal()

public class ResultSetDictionaryGlobal {
    @JvmField
    internal val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("dictionary")

    @JvmField
    internal var bNodeCounter = 5
    private var lastPage: Int = 0
    private var lastPageBuf: ByteArray = ByteArray(0)
    private var lastPageOffset: Int = 0
    private var nextID = 0
    private var mappingID2Page = IntArray(100)
    private var mappingID2Off = IntArray(100)
    private var mappingSorted = IntArray(100)

    init {
        bufferManager.createPage { page, id ->
            lastPageBuf = page
            lastPage = id
            lastPageOffset = 4
        }
    }

    private inline fun readString(page: Int, off: Int, crossinline action: (type: Int, s: String) -> Unit) {
        var p = bufferManager.getPage(page)
        var pid = page
        val type = ByteArrayHelper.readInt4(p, off)
        val l = ByteArrayHelper.readInt4(p, off + 4)
        val buf = ByteArray(l)
        var bufoff = 0
        var toread = l
        var pageoff = off + 8
        while (toread > 0) {
            var available = p.size - pageoff
            if (available == 0) {
                var id = ByteArrayHelper.readInt4(p, 0)
                bufferManager.releasePage(pid)
                p = bufferManager.getPage(id)
                pid = id
                pageoff = 4
                available = p.size - pageoff
            }
            var len = if (available < toread) {
                available
            } else {
                toread
            }
            p.copyInto(buf, bufoff, pageoff, pageoff + len)
            bufoff += len
            pageoff += len
            toread -= len
        }
        bufferManager.releasePage(pid)
        action(type, buf.decodeToString())
    }

    private inline fun writeString(type: Int, s: String, crossinline action: (page: Int, off: Int) -> Unit) {
        if (lastPageOffset >= lastPageBuf.size - 8) {
            bufferManager.createPage { page, id ->
                ByteArrayHelper.writeInt4(lastPageBuf, 0, id)
                bufferManager.releasePage(lastPage)
                lastPageBuf = page
                lastPage = id
            }
            lastPageOffset = 4
        }
        var resPage = lastPage
        var resOff = lastPageOffset
        val buf = s.encodeToByteArray()
        ByteArrayHelper.writeInt4(lastPageBuf, lastPageOffset, type)
        ByteArrayHelper.writeInt4(lastPageBuf, lastPageOffset + 4, buf.size)
        lastPageOffset += 8
        var bufoff = 0
        var towrite = buf.size
        while (towrite > 0) {
            var available = lastPageBuf.size - lastPageOffset
            if (available == 0) {
                bufferManager.createPage { page, id ->
                    ByteArrayHelper.writeInt4(lastPageBuf, 0, id)
                    bufferManager.releasePage(lastPage)
                    lastPageBuf = page
                    lastPage = id
                }
                lastPageOffset = 4
                available = lastPageBuf.size - lastPageOffset
            }
            var len = if (available < towrite) {
                available
            } else {
                towrite
            }
            buf.copyInto(lastPageBuf, lastPageOffset, bufoff, bufoff + len)
            towrite -= len
            lastPageOffset += len
            bufoff += len
        }
        action(resPage, resOff)
    }

    internal inline fun writeValue(value: String, type: Int): Int {
        var res = 0
        hasValue(
            value, type, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
                res = nextID++
                writeString(type, value) { page, off ->
                    if (res >= mappingID2Page.size) {
                        var tmp = IntArray(mappingID2Page.size * 2)
                        mappingID2Page.copyInto(tmp)
                        mappingID2Page = tmp
                        tmp = IntArray(mappingID2Off.size * 2)
                        mappingID2Off.copyInto(tmp)
                        mappingID2Off = tmp
                        tmp = IntArray(mappingSorted.size * 2)
                        mappingSorted.copyInto(tmp)
                        mappingSorted = tmp
                    }
                    mappingID2Page[res] = page
                    mappingID2Off[res] = off
                    var i = res
                    while (i > it) {
                        mappingSorted[i] = mappingSorted[i - 1]
                        i--
                    }
                    mappingSorted[i] = res
                }
            }
        )
        return res or ResultSetDictionaryShared.flaggedValueGlobal
    }

    internal inline fun hasValue(value: String, type: Int, left: Int, right: Int, crossinline onFound: (Int/*the id to return*/) -> Unit, onNotFound: (Int/*the smallest index, which value is larger than the target*/) -> Unit) {
        var l = left
        var r = right
        var loop = true
        while (loop && r >= l) {
            var m = (r - l) / 2 + l
            readString(mappingID2Page[m], mappingID2Off[m]) { t, s ->
                if (t < type) {
                    if (m == l) {
                        m++
                    }
                    l = m
                } else if (t > type) {
                    if (m == r) {
                        m--
                    }
                    r = m
                } else if (s < value) {
                    if (m == l) {
                        m++
                    }
                    l = m
                } else if (s > value) {
                    if (m == r) {
                        m--
                    }
                    r = m
                } else {
                    loop = false
                    onFound(m)
                }
            }
        }
        if (r < l) {
            var res = l
            SanityCheck {
                if (res > left) {
                    readString(mappingID2Page[res - 1], mappingID2Off[res - 1]) { t, s -> SanityCheck.check { t < type || (t == type && s < value) } }
                }
                if (res <= right) {
                    readString(mappingID2Page[res], mappingID2Off[res]) { t, s -> SanityCheck.check { t > type || (t == type && s > value) } }
                }
            }
            onNotFound(res)
        }
    }

    internal inline fun hasValue(value: String, type: Int): Int? {
        var res: Int? = null
        hasValue(
            value, type, 0, nextID - 1,
            onFound = {
                res = it
            },
            onNotFound = {
            }
        )
        if (res == null) {
            return null
        }
        return res!! or ResultSetDictionaryShared.flaggedValueGlobal
    }

    internal inline fun readValue(value: Int, crossinline action: (value: String, type: Int) -> Unit) {
        val v = value and ResultSetDictionaryShared.filter2
        readString(mappingID2Page[v], mappingID2Off[v]) { type, s ->
            action(s, type)
        }
    }

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
                val tmp = DictionaryIntermediate.encodeLang(value.content, value.language)
                res = writeValue(tmp, ETripleComponentTypeExt.STRING_LANG)
            }
            is ValueSimpleLiteral -> {
                val tmp = DictionaryIntermediate.encodeString(value.content)
                res = writeValue(tmp, ETripleComponentTypeExt.STRING)
            }
            is ValueTypedLiteral -> {
                val tmp = DictionaryIntermediate.encodeTyped(value.content, value.type_iri)
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
                val tmp = DictionaryIntermediate.encodeLang(value.content, value.language)
                res = hasValue(tmp, ETripleComponentTypeExt.STRING_LANG)
            }
            is ValueSimpleLiteral -> {
                val tmp = DictionaryIntermediate.encodeString(value.content)
                res = hasValue(tmp, ETripleComponentTypeExt.STRING)
            }
            is ValueTypedLiteral -> {
                val tmp = DictionaryIntermediate.encodeTyped(value.content, value.type_iri)
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
                    ETripleComponentTypeExt.STRING -> {
                        val tmp = DictionaryIntermediate.decodeString(value)
                        res = ValueSimpleLiteral("\"", tmp.substring(1, tmp.length - 1))
                    }
                    ETripleComponentTypeExt.STRING_LANG -> {
                        val tmp = DictionaryIntermediate.decodeLang(value)
                        res = ValueLanguageTaggedLiteral("\"", tmp.first.substring(1, tmp.first.length - 1), tmp.second)
                    }
                    ETripleComponentTypeExt.STRING_TYPED -> {
                        val tmp = DictionaryIntermediate.decodeTyped(value)
                        res = ValueTypedLiteral("\"", tmp.first.substring(1, tmp.first.length - 1), tmp.second.substring(1, tmp.second.length - 1))
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
                    ETripleComponentTypeExt.STRING -> {
                        val tmp = DictionaryIntermediate.decodeString(value)
                        onSimpleLiteral(tmp.substring(1, tmp.length - 1))
                    }
                    ETripleComponentTypeExt.STRING_LANG -> {
                        val tmp = DictionaryIntermediate.decodeLang(value)
                        onLanguageTaggedLiteral(tmp.first.substring(1, tmp.first.length - 1), tmp.second)
                    }
                    ETripleComponentTypeExt.STRING_TYPED -> {
                        val tmp = DictionaryIntermediate.decodeTyped(value)
                        onTypedLiteral(tmp.first.substring(1, tmp.first.length - 1), tmp.second.substring(1, tmp.second.length - 1))
                    }
                    else -> throw Exception("unexpected type $type")
                }
            }
        }
    }
}
