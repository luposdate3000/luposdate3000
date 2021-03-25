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

import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ByteArrayWrapper
import lupos.s00misc.ETripleComponentType
import lupos.s00misc.ETripleComponentTypeExt
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueTypedLiteral

public object DictionaryHelper {
    /* encoding ::
     *
     * ETripleComponentTypeExt.UNDEF
     * ETripleComponentTypeExt.ERROR
     * ETripleComponentTypeExt.BLANK_NODE
     * -> size=8
     *  -> int.ID
     * -> size > 8
     *  -> string.len followed by string.content
     * ETripleComponentTypeExt.BOOLEAN
     * -> false if byte equals 0
     * ETripleComponentTypeExt.IRI
     * -> string
     * ETripleComponentTypeExt.STRING
     * -> string
     * ETripleComponentTypeExt.STRING_LANG
     * -> last 4 bytes specify lang.length
     * -> languageString followed by 0 followed by contentString
     * ETripleComponentTypeExt.STRING_TYPED
     * -> last 4 bytes specify type.length
     * -> typeString followed by 0 followed by contentString
     */
    public fun valueToByteArray(buffer: ByteArrayWrapper, value: String?) {
        if (value == null || value.isEmpty()) {
            buffer.setSize(4)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.UNDEF)
            return
        }
        if (value.startsWith("_:")) {
            val buf1 = value.substring(2, value.length).encodeToByteArray()
            buffer.setSize(8 + buf1.size)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 4, buf1.size)
            buf1.copyInto(buffer.getBuf(), 8)
            return
        }
        if (value.startsWith("<") && value.endsWith(">")) {
            val buf1 = value.substring(1, value.length - 1).encodeToByteArray()
            buffer.setSize(4 + buf1.size)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.IRI)
            buf1.copyInto(buffer.getBuf(), 4)
            return
        }
        try {
            val i = MyBigInteger(value)
            val buf1 = "http://www.w3.org/2001/XMLSchema#integer".encodeToByteArray()
            val buf2 = i.toString().encodeToByteArray()
            buffer.setSize(9 + buf1.size + buf2.size)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_TYPED)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
            buf1.copyInto(buffer.getBuf(), 4)
            buffer.getBuf()[4 + buf1.size] = 0
            buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
            return
        } catch (e: Throwable) {
        }
        if (!value.contains("e") && !value.contains("E")) {
            try {
                val d = MyBigDecimal(value)
                val buf1 = "http://www.w3.org/2001/XMLSchema#decimal".encodeToByteArray()
                val buf2 = d.toString().encodeToByteArray()
                buffer.setSize(9 + buf1.size + buf2.size)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_TYPED)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
                buf1.copyInto(buffer.getBuf(), 4)
                buffer.getBuf()[4 + buf1.size] = 0
                buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
                return
            } catch (e: Throwable) {
            }
        }
        try {
            val d = value.toDouble()
            val buf1 = "http://www.w3.org/2001/XMLSchema#double".encodeToByteArray()
            val buf2 = d.toString().encodeToByteArray()
            buffer.setSize(9 + buf1.size + buf2.size)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_TYPED)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
            buf1.copyInto(buffer.getBuf(), 4)
            buffer.getBuf()[4 + buf1.size] = 0
            buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
            return
        } catch (e: Throwable) {
        }
        if (!value.endsWith("" + value[0])) {
            val typeIdx = value.lastIndexOf("" + value[0] + "^^<")
            val langIdx = value.lastIndexOf("" + value[0] + "@")
            if (value.endsWith(">") && typeIdx > 0) {
                val buf1 = value.substring(typeIdx + 4, value.length - 1).encodeToByteArray()
                val buf2 = value.substring(1, typeIdx).encodeToByteArray()
                buffer.setSize(9 + buf1.size + buf2.size)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_TYPED)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
                buf1.copyInto(buffer.getBuf(), 4)
                buffer.getBuf()[4 + buf1.size] = 0
                buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
                return
            } else {
                SanityCheck.check { langIdx > 0 }
                val buf1 = value.substring(langIdx + 2, value.length).encodeToByteArray()
                val buf2 = value.substring(1, langIdx).encodeToByteArray()
                buffer.setSize(9 + buf1.size + buf2.size)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_LANG)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
                buf1.copyInto(buffer.getBuf(), 4)
                buffer.getBuf()[4 + buf1.size] = 0
                buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
                return
            }
        }
        val buf1 = value.substring(1, value.length - 1).encodeToByteArray()
        buffer.setSize(4 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING)
        buf1.copyInto(buffer.getBuf(), 4)
    }

    public fun valueToByteArray(buffer: ByteArrayWrapper, value: ValueDefinition) {
        valueToByteArray(buffer, value.valueToString())
    }

    public fun byteArrayToType(buffer: ByteArrayWrapper): ETripleComponentType = ByteArrayHelper.readInt4(buffer.getBuf(), 0)
    public fun byteArrayToValueDefinition(buffer: ByteArrayWrapper): ValueDefinition {
        val type = byteArrayToType(buffer)
        return when (type) {
            ETripleComponentTypeExt.UNDEF -> DictionaryExt.undefValue2
            ETripleComponentTypeExt.ERROR -> DictionaryExt.errorValue2
            ETripleComponentTypeExt.BLANK_NODE -> {
                if (buffer.getSize() == 8) {
                    ValueBnode(ByteArrayHelper.readInt4(buffer.getBuf(), 4).toString())
                } else {
                    val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), 4)
                    val buf1 = ByteArray(l1)
                    buffer.getBuf().copyInto(buf1, 0, 8, 8 + l1)
                    ValueBnode(buf1.decodeToString())
                }
            }
            ETripleComponentTypeExt.BOOLEAN -> {
                if (buffer.getBuf()[4] != 0.toByte()) {
                    DictionaryExt.booleanTrueValue2
                } else {
                    DictionaryExt.booleanFalseValue2
                }
            }
            ETripleComponentTypeExt.IRI -> {
                val l1 = buffer.getSize() - 4
                val buf1 = ByteArray(l1)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                ValueIri(buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING -> {
                val l1 = buffer.getSize() - 4
                val buf1 = ByteArray(l1)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                ValueSimpleLiteral("\"", buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING_LANG -> {
                val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
                val l2 = buffer.getSize() - 9 - l1
                val buf1 = ByteArray(l1)
                val buf2 = ByteArray(l2)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                buffer.getBuf().copyInto(buf2, 0, 5 + l1, 5 + l1 + l2)
                ValueLanguageTaggedLiteral("\"", buf2.decodeToString(), buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING_TYPED -> {
                val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
                val l2 = buffer.getSize() - 9 - l1
                val buf1 = ByteArray(l1)
                val buf2 = ByteArray(l2)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                buffer.getBuf().copyInto(buf2, 0, 5 + l1, 5 + l1 + l2)
                ValueTypedLiteral("\"", buf2.decodeToString(), buf1.decodeToString())
            }
            else -> throw Exception("unreachable $type")
        }
    }

    public fun byteArrayToCallback(
        buffer: ByteArrayWrapper,
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
        val type = ByteArrayHelper.readInt4(buffer.getBuf(), 0)
        when (type) {
            ETripleComponentTypeExt.UNDEF -> onUndefined()
            ETripleComponentTypeExt.ERROR -> onError()
            ETripleComponentTypeExt.BLANK_NODE -> onBNode(ByteArrayHelper.readInt4(buffer.getBuf(), 4))
            ETripleComponentTypeExt.BOOLEAN -> onBoolean(buffer.getBuf()[4] != 0.toByte())
            ETripleComponentTypeExt.IRI -> {
                val l1 = buffer.getSize() - 4
                val buf1 = ByteArray(l1)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                onIri(buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING -> {
                val l1 = buffer.getSize() - 4
                val buf1 = ByteArray(l1)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                onSimpleLiteral(buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING_LANG -> {
                val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
                val l2 = buffer.getSize() - 5 - l1
                val buf1 = ByteArray(l1)
                val buf2 = ByteArray(l2)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                buffer.getBuf().copyInto(buf2, 0, 5 + l1, 1 + l1 + l2)
                onLanguageTaggedLiteral(buf2.decodeToString(), buf1.decodeToString())
            }
            ETripleComponentTypeExt.STRING_TYPED -> {
                val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
                val l2 = buffer.getSize() - 5 - l1
                val buf1 = ByteArray(l1)
                val buf2 = ByteArray(l2)
                buffer.getBuf().copyInto(buf1, 0, 4, 4 + l1)
                buffer.getBuf().copyInto(buf2, 0, 5 + l1, 1 + l1 + l2)
                onTypedLiteral(buf2.decodeToString(), buf1.decodeToString())
            }
        }
    }

    public fun byteArrayAnyToBooleanID(buffer: ByteArrayWrapper): Int {
        val type = byteArrayToType(buffer)
        if (type == ETripleComponentTypeExt.BOOLEAN) {
            if (buffer.getBuf()[4] != 0.toByte()) {
                return DictionaryExt.booleanTrueValue
            } else {
                DictionaryExt.booleanFalseValue
            }
        }
        return DictionaryExt.errorValue
    }

    public fun byteArrayCompareAny(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        val typeA = byteArrayToType(a)
        val typeB = byteArrayToType(b)
        if (typeA != typeB) {
            if (typeA == ETripleComponentTypeExt.UNDEF) {
                return -1
            } else if (typeB == ETripleComponentTypeExt.UNDEF) {
                return 1
            } else if (typeA == ETripleComponentTypeExt.ERROR) {
                return -1
            } else if (typeB == ETripleComponentTypeExt.ERROR) {
                return 1
            } else if (typeA == ETripleComponentTypeExt.BLANK_NODE) {
                return -1
            } else if (typeB == ETripleComponentTypeExt.BLANK_NODE) {
                return 1
            } else if (typeA == ETripleComponentTypeExt.IRI) {
                return -1
            } else if (typeB == ETripleComponentTypeExt.IRI) {
                return 1
            } else if (typeA == ETripleComponentTypeExt.STRING) {
                return -1
            } else if (typeB == ETripleComponentTypeExt.STRING) {
                return 1
            } else {
                return typeA - typeB
            }
        } else {
            if (typeA == ETripleComponentTypeExt.UNDEF || typeA == ETripleComponentTypeExt.ERROR) {
                return 0
            } else if (typeA == ETripleComponentTypeExt.BLANK_NODE) {
                if (a.getSize() == 8 && b.getSize() == 8) {
                    return ByteArrayHelper.readInt4(a.getBuf(), 4) - ByteArrayHelper.readInt4(b.getBuf(), 4)
                } else {
                    return a.compareTo(b)
                }
            } else if (typeA == ETripleComponentTypeExt.BOOLEAN) {
                return a.getBuf()[4] - b.getBuf()[4]
            } else if (typeA == ETripleComponentTypeExt.DATE_TIME) {
            } else if (typeA == ETripleComponentTypeExt.DECIMAL) {
            } else if (typeA == ETripleComponentTypeExt.DOUBLE) {
            } else if (typeA == ETripleComponentTypeExt.FLOAT) {
            } else if (typeA == ETripleComponentTypeExt.INTEGER) {
            } else if (typeA == ETripleComponentTypeExt.STRING_LANG || typeA == ETripleComponentTypeExt.STRING_TYPED || typeA == ETripleComponentTypeExt.IRI || typeA == ETripleComponentTypeExt.STRING) {
                val lenA = a.getSize()
                val lenB = b.getSize()
                var i = 4
                var res = 0
                while (i < lenA && i < lenB && res == 0) {
                    res = a.getBuf()[i] - b.getBuf()[i]
                    i++
                }
                if (res == 0) {
                    res = lenA - lenB
                }
                return res
            }
        }
        throw Exception("can not compare $typeA $typeB")
    }
}
