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
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueFloat
import lupos.s03resultRepresentation.ValueInteger
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
     * -> size > 8 !!!! this is only used in intermediate file format
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
     * ETripleComponentTypeExt.DOUBLE
     * -> IEEE 754 floating-point "double format" bit layout, preserving Not-a-Number (NaN) values.
     * ETripleComponentTypeExt.INTEGER
     * ETripleComponentTypeExt.DECIMAL
     * ETripleComponentTypeExt.FLOAT
     * -> IEEE 754 floating-point "double format" bit layout, preserving Not-a-Number (NaN) values.
     */
    public inline fun errorToByteArray(buffer: ByteArrayWrapper) {
        buffer.setSize(4)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.ERROR)
    }

    public inline fun booleanToByteArray(buffer: ByteArrayWrapper, value: Boolean) {
        buffer.setSize(5)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BOOLEAN)
        if (value) {
            buffer.getBuf()[4] = 1
        } else {
            buffer.getBuf()[4] = 0
        }
    }

    public inline fun byteArrayToBoolean(buffer: ByteArrayWrapper): Boolean {
        return buffer.getBuf()[4] != 0.toByte()
    }

    public inline fun integerToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        buffer.setSize(4 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.INTEGER)
        buf1.copyInto(buffer.getBuf(), 4)
    }

    public inline fun byteArrayToInteger(buffer: ByteArrayWrapper): String {
        val l1 = buffer.getSize() - 4
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun decimalToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        buffer.setSize(4 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.DECIMAL)
        buf1.copyInto(buffer.getBuf(), 4)
    }

    public inline fun byteArrayToDecimal(buffer: ByteArrayWrapper): String {
        val l1 = buffer.getSize() - 4
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun doubleToByteArray(buffer: ByteArrayWrapper, value: Double) {
        buffer.setSize(12)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.DOUBLE)
        ByteArrayHelper.writeDouble8(buffer.getBuf(), 4, value)
    }

    public inline fun byteArrayToDouble(buffer: ByteArrayWrapper): Double {
        return ByteArrayHelper.readDouble8(buffer.getBuf(), 4)
    }

    public inline fun floatToByteArray(buffer: ByteArrayWrapper, value: Double) {
        buffer.setSize(12)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.FLOAT)
        ByteArrayHelper.writeDouble8(buffer.getBuf(), 4, value)
    }

    public inline fun byteArrayToFloat(buffer: ByteArrayWrapper): Double {
        return ByteArrayHelper.readDouble8(buffer.getBuf(), 4)
    }

    public inline fun langToByteArray(buffer: ByteArrayWrapper, content: String, lang: String) {
        val buf1 = lang.encodeToByteArray()
        val buf2 = content.encodeToByteArray()
        buffer.setSize(9 + buf1.size + buf2.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_LANG)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
        buf1.copyInto(buffer.getBuf(), 4)
        buffer.getBuf()[4 + buf1.size] = 0
        buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
    }

    public fun byteArrayToLang_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
        val l2 = buffer.getSize() - 9 - l1
        val buf = ByteArray(l2)
        buffer.getBuf().copyInto(buf, 0, 5 + l1, 5 + l1 + l2)
        return buf.decodeToString()
    }

    public fun byteArrayToLang_Lang(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun typedToByteArray(buffer: ByteArrayWrapper, content: String, type: String) {
        when (type) {
            "http://www.w3.org/2001/XMLSchema#integer" -> integerToByteArray(buffer, content)
            "http://www.w3.org/2001/XMLSchema#decimal" -> decimalToByteArray(buffer, content)
            "http://www.w3.org/2001/XMLSchema#double" -> doubleToByteArray(buffer, content.toDouble())
            "http://www.w3.org/2001/XMLSchema#float" -> floatToByteArray(buffer, content.toDouble())
            "http://www.w3.org/2001/XMLSchema#boolean" -> booleanToByteArray(buffer, content.toLowerCase() == "true")
            else -> {
                val buf1 = type.encodeToByteArray()
                val buf2 = content.encodeToByteArray()
                buffer.setSize(9 + buf1.size + buf2.size)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING_TYPED)
                ByteArrayHelper.writeInt4(buffer.getBuf(), 5 + buf1.size + buf2.size, buf1.size)
                buf1.copyInto(buffer.getBuf(), 4)
                buffer.getBuf()[4 + buf1.size] = 0
                buf2.copyInto(buffer.getBuf(), 5 + buf1.size)
            }
        }
    }

    public fun byteArrayToTyped_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
        val l2 = buffer.getSize() - 9 - l1
        val buf = ByteArray(l2)
        buffer.getBuf().copyInto(buf, 0, 5 + l1, 5 + l1 + l2)
        return buf.decodeToString()
    }

    public fun byteArrayToTyped_Type(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), buffer.getSize() - 4)
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: String) {
        SanityCheck.check { value.length > 0 }
        val buf1 = value.encodeToByteArray()
        buffer.setSize(8 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 4, buf1.size)
        buf1.copyInto(buffer.getBuf(), 8)
    }

    public inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: Int) {
        val buf1 = value.toString().encodeToByteArray()
        buffer.setSize(8)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.BLANK_NODE)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 4, value)
    }

    public inline fun byteArrayToBnode(buffer: ByteArrayWrapper): Int {
        if (buffer.getSize() == 8) {
            return ByteArrayHelper.readInt4(buffer.getBuf(), 4)
        } else {
            throw Exception("this is not ready to be used as instanciated value")
        }
    }

    public inline fun byteArrayToBnodeIntermediate(buffer: ByteArrayWrapper): String {
        if (buffer.getSize() == 8) {
            throw Exception("this is not ready to be used as import value")
        } else {
            val l1 = ByteArrayHelper.readInt4(buffer.getBuf(), 4)
            val buf = ByteArray(l1)
            buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
            return buf.decodeToString()
        }
    }

    public inline fun iriToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        buffer.setSize(4 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.IRI)
        buf1.copyInto(buffer.getBuf(), 4)
    }

    public inline fun byteArrayToIri(buffer: ByteArrayWrapper): String {
        val l1 = buffer.getSize() - 4
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun byteArrayToString(buffer: ByteArrayWrapper): String {
        val l1 = buffer.getSize() - 4
        val buf = ByteArray(l1)
        buffer.getBuf().copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    public inline fun stringToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        buffer.setSize(4 + buf1.size)
        ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.STRING)
        buf1.copyInto(buffer.getBuf(), 4)
    }

    public inline fun valueToByteArray(buffer: ByteArrayWrapper, value: String?) {
        if (value == null || value.isEmpty()) {
            buffer.setSize(4)
            ByteArrayHelper.writeInt4(buffer.getBuf(), 0, ETripleComponentTypeExt.UNDEF)
            return
        }
        if (value.startsWith("_:")) {
            bnodeToByteArray(buffer, value.substring(2, value.length))
            return
        }
        if (value.startsWith("<") && value.endsWith(">")) {
            iriToByteArray(buffer, value.substring(1, value.length - 1))
            return
        }
        try {
            val i = MyBigInteger(value)
            integerToByteArray(buffer, i.toString())
            return
        } catch (e: Throwable) {
        }
        if (!value.contains("e") && !value.contains("E")) {
            try {
                val d = MyBigDecimal(value)
                decimalToByteArray(buffer, d.toString())
                return
            } catch (e: Throwable) {
            }
        }
        try {
            val d = value.toDouble()
            doubleToByteArray(buffer, d)
            return
        } catch (e: Throwable) {
        }
        if (!value.endsWith("" + value[0])) {
            val typeIdx = value.lastIndexOf("" + value[0] + "^^<")
            val langIdx = value.lastIndexOf("" + value[0] + "@")
            if (value.endsWith(">") && typeIdx > 0) {
                typedToByteArray(buffer, removeQuotesFromString(value.substring(0, typeIdx + 1)), value.substring(typeIdx + 4, value.length - 1))
                return
            } else {
                SanityCheck.check { langIdx > 0 }
                langToByteArray(buffer, removeQuotesFromString(value.substring(0, langIdx + 1)), value.substring(langIdx + 2, value.length))
                return
            }
        }
        stringToByteArray(buffer, removeQuotesFromString(value))
    }

    public inline fun removeQuotesFromString(s: String): String {
        var c = s[0]
        var cntLeft = 1
        var cntRight = 0
        if (c != '\'' && c != '"') {
            throw Exception("invalid quoted string >$s<")
        }
        while (s[cntLeft] == c) {
            cntLeft++
        }
        while (s[s.length - cntRight - 1] == c) {
            cntRight++
        }
        if (cntLeft == 6) {
            return ""
        }
        if (cntLeft >= 3 && cntRight >= 3) {
            return s.substring(3, s.length - 3)
        }
        return s.substring(1, s.length - 1)
    }

    public inline fun valueToByteArray(buffer: ByteArrayWrapper, value: ValueDefinition) {
        valueToByteArray(buffer, value.valueToString())
    }

    public inline fun byteArrayToType(buffer: ByteArrayWrapper): ETripleComponentType = ByteArrayHelper.readInt4(buffer.getBuf(), 0)
    public inline fun byteArrayToValueDefinition(buffer: ByteArrayWrapper): ValueDefinition {
        val type = byteArrayToType(buffer)
        return when (type) {
            ETripleComponentTypeExt.UNDEF -> DictionaryExt.undefValue2
            ETripleComponentTypeExt.ERROR -> DictionaryExt.errorValue2
            ETripleComponentTypeExt.BLANK_NODE -> ValueBnode("" + byteArrayToBnode(buffer))
            ETripleComponentTypeExt.BOOLEAN -> {
                if (byteArrayToBoolean(buffer)) {
                    DictionaryExt.booleanTrueValue2
                } else {
                    DictionaryExt.booleanFalseValue2
                }
            }
            ETripleComponentTypeExt.DOUBLE -> ValueDouble(byteArrayToDouble(buffer))
            ETripleComponentTypeExt.FLOAT -> ValueFloat(byteArrayToDouble(buffer))
            ETripleComponentTypeExt.INTEGER -> ValueInteger(MyBigInteger(byteArrayToInteger(buffer)))
            ETripleComponentTypeExt.DECIMAL -> ValueDecimal(MyBigDecimal(byteArrayToDecimal(buffer)))
            ETripleComponentTypeExt.IRI -> ValueIri(byteArrayToIri(buffer))
            ETripleComponentTypeExt.STRING -> ValueSimpleLiteral("\"", byteArrayToString(buffer))
            ETripleComponentTypeExt.STRING_LANG -> ValueLanguageTaggedLiteral("\"", byteArrayToLang_Content(buffer), byteArrayToLang_Lang(buffer))
            ETripleComponentTypeExt.STRING_TYPED -> ValueTypedLiteral("\"", byteArrayToTyped_Content(buffer), byteArrayToTyped_Type(buffer))
            else -> throw Exception("unreachable $type")
        }
    }

    public inline fun byteArrayToCallback(
        buffer: ByteArrayWrapper,
        crossinline onBNode: (value: Int) -> Unit,
        crossinline onBoolean: (value: Boolean) -> Unit,
        crossinline onLanguageTaggedLiteral: (content: String, lang: String) -> Unit,
        crossinline onSimpleLiteral: (content: String) -> Unit,
        crossinline onTypedLiteral: (content: String, type: String) -> Unit,
        crossinline onDecimal: (value: String) -> Unit,
        crossinline onFloat: (value: Double) -> Unit,
        crossinline onDouble: (value: Double) -> Unit,
        crossinline onInteger: (value: String) -> Unit,
        crossinline onIri: (value: String) -> Unit,
        crossinline onError: () -> Unit,
        crossinline onUndefined: () -> Unit
    ) {
        val type = ByteArrayHelper.readInt4(buffer.getBuf(), 0)
        when (type) {
            ETripleComponentTypeExt.FLOAT -> onFloat(byteArrayToDouble(buffer))
            ETripleComponentTypeExt.DOUBLE -> onDouble(byteArrayToDouble(buffer))
            ETripleComponentTypeExt.INTEGER -> onInteger(byteArrayToInteger(buffer))
            ETripleComponentTypeExt.DECIMAL -> onDecimal(byteArrayToDecimal(buffer))
            ETripleComponentTypeExt.UNDEF -> onUndefined()
            ETripleComponentTypeExt.ERROR -> onError()
            ETripleComponentTypeExt.BLANK_NODE -> onBNode(byteArrayToBnode(buffer))
            ETripleComponentTypeExt.BOOLEAN -> onBoolean(byteArrayToBoolean(buffer))
            ETripleComponentTypeExt.IRI -> onIri(byteArrayToIri(buffer))
            ETripleComponentTypeExt.STRING -> onSimpleLiteral(byteArrayToString(buffer))
            ETripleComponentTypeExt.STRING_LANG -> onLanguageTaggedLiteral(byteArrayToLang_Content(buffer), byteArrayToLang_Lang(buffer))
            ETripleComponentTypeExt.STRING_TYPED -> onTypedLiteral(byteArrayToTyped_Content(buffer), byteArrayToTyped_Type(buffer))
        }
    }

    public inline fun byteArrayAnyToBooleanID(buffer: ByteArrayWrapper): Int {
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

    public inline fun byteArrayCompareAny(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
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
