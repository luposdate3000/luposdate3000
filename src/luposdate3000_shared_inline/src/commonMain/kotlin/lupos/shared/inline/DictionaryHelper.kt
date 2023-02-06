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
package lupos.shared.inline

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import lupos.shared.InvalidInputException
import lupos.shared.DictionaryHelperLarge
import lupos.shared.UnreachableException
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

internal object DictionaryHelper {
    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_intToString(value: BigInteger): String {
        return helper_intCheckString(value.toString())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_intCheckString(value: String): String {
        if (value.length == 0) {
            return "0"
        }
        var v = value
        var negative = false
        if (v[0] == '+') {
            v = v.substring(1)
        } else if (v[0] == '-') {
            negative = true
            v = v.substring(1)
        }
        while (v.startsWith('0')) {
            v = v.substring(1)
        }
        if (v.length == 0) {
            v = "0"
        }
        if (negative) {
            v = '-' + v
        }
        return v
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_intToByteArray(value: BigInteger): ByteArray {
        return helper_intToString(value).encodeToByteArray()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_intFromString(value: String): BigInteger {
        return BigInteger.parseString(value, 10)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_intFromByteArray(value: ByteArray): BigInteger {
        return helper_intFromString(value.decodeToString())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_decimalToString(value: BigDecimal): String {
        return helper_decimalCheckString(value.toStringExpanded())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_decimalCheckString(value: String): String {
        if (value.length == 0) {
            return "0.0"
        }
        var v = value
        var negative = false
        if (v[0] == '+') {
            v = v.substring(1)
        } else if (v[0] == '-') {
            negative = true
            v = v.substring(1)
        }
        while (v.startsWith('0')) {
            v = v.substring(1)
        }
        if (v.contains('.')) {
            while (v.endsWith('0')) {
                v = v.substring(0, v.length - 1)
            }
        } else {
            v = v + '.'
        }
        if (v.endsWith(".")) {
            v = v + '0'
        }
        if (v.startsWith('.')) {
            v = '0' + v
        }
        if (negative) {
            v = '-' + v
        }
        return v
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_decimalToByteArray(value: BigDecimal): ByteArray {
        return helper_decimalToString(value).encodeToByteArray()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_decimalFromString(value: String): BigDecimal {
        return BigDecimal.parseString(value, 10)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun helper_decimalFromByteArray(value: ByteArray): BigDecimal {
        return helper_decimalFromString(value.decodeToString())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun headerSize(): Int = 1

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun headerEncode(buffer: ByteArrayWrapper, type: ETripleComponentType, flag: Int) {
if(SanityCheck.enabled){if(!( (type and ETripleComponentTypeExt.values_mask) == type )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( (flag and ETripleComponentTypeExt.values_mask_inversed) == flag )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( (type or flag) <= 0xff )){throw Exception("SanityCheck failed")}}
        ByteArrayWrapperExt.writeInt1(buffer, 0, type or flag)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun headerDecodeType(buffer: ByteArrayWrapper): ETripleComponentType {
        val res = ByteArrayWrapperExt.readInt1(buffer, 0) and ETripleComponentTypeExt.values_mask
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun headerDecodeFlag(buffer: ByteArrayWrapper): Int {
        val res = ByteArrayWrapperExt.readInt1(buffer, 0) and ETripleComponentTypeExt.values_mask_inversed
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun errorToByteArray(buffer: ByteArrayWrapper) {
        ByteArrayWrapperExt.setSize(buffer, headerSize(), false)
        headerEncode(buffer, ETripleComponentTypeExt.ERROR, 0)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun undefToByteArray(buffer: ByteArrayWrapper) {
        ByteArrayWrapperExt.setSize(buffer, headerSize(), false)
        headerEncode(buffer, ETripleComponentTypeExt.UNDEF, 0)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun dateTimeToByteArray(buffer: ByteArrayWrapper, str: String) {
        DictionaryHelperLarge.dateTimeToByteArray(buffer, str)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun dateTimeToByteArray(buffer: ByteArrayWrapper) {
        val dateNow = DateHelper()
        DictionaryHelperLarge.dateTimeToByteArray(buffer, helper_intFromString(dateNow.year().toString()), dateNow.month(), dateNow.day(), dateNow.hours(), dateNow.minutes(), helper_decimalFromString(dateNow.seconds().toString()), -99, -99, false)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun dateTimeToByteArray(buffer: ByteArrayWrapper, year: BigInteger, month: Int, day: Int, hours: Int, minutes: Int, seconds: BigDecimal, timezoneHours: Int, timezoneMinutes: Int, hasTimeZone: Boolean) {
        DictionaryHelperLarge.dateTimeToByteArray(buffer, year, month, day, hours, minutes, seconds, timezoneHours, timezoneMinutes, hasTimeZone)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Year(buffer: ByteArrayWrapper): BigInteger {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            var year = ((componentAll shr 37) and 0x7FF)
            if ((componentAll and (1L shl 36)) != (1L shl 36)) {
                year = -year
            }
            return year.toBigInteger()
        } else {
            var off = 0
            off += headerSize()
            val l1 = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            val buf1 = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf1, 0, off, off + l1)
            off += l1
            val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - headerSize() - 28
            off += l2
   if(SanityCheck.enabled){if(!(          off == ByteArrayWrapperExt.getSize(buffer)  )){throw Exception("SanityCheck failed")}}
            val year = helper_intFromByteArray(buf1)
            return year
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Month(buffer: ByteArrayWrapper): BigInteger {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            return ((componentAll shr 32) and 0xF).toBigInteger()
        } else {
            var off = 0
            off += headerSize()
            off += 4
            val month = ByteArrayWrapperExt.readInt4(buffer, off)
            return BigInteger(month)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Day(buffer: ByteArrayWrapper): BigInteger {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            return ((componentAll shr 27) and 0x1F).toBigInteger()
        } else {
            var off = 0
            off += headerSize()
            off += 4
            off += 4
            val day = ByteArrayWrapperExt.readInt4(buffer, off)
            return BigInteger(day)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Hours(buffer: ByteArrayWrapper): BigInteger {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            val hours = (componentAll and 0x7FFFFFF) / (1000 * 60 * 60)
            return hours.toBigInteger()
        } else {
            var off = 0
            off += headerSize()
            off += 4
            off += 4
            off += 4
            val hours = ByteArrayWrapperExt.readInt4(buffer, off)
            return BigInteger(hours)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Minutes(buffer: ByteArrayWrapper): BigInteger {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            val minutes = ((componentAll and 0x7FFFFFF) / (1000 * 60)) % 60
            return minutes.toBigInteger()
        } else {
            var off = 0
            off += headerSize()
            off += 4
            off += 4
            off += 4
            off += 4
            val minutes = ByteArrayWrapperExt.readInt4(buffer, off)
            return BigInteger(minutes)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_Seconds(buffer: ByteArrayWrapper): BigDecimal {
        if (headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, headerSize())
            val milliseconds = ((componentAll and 0x7FFFFFF)) % (60 * 1000)
            return milliseconds.toBigDecimal() / 1000
        } else {
            var off = 0
            off += headerSize()
            val l1 = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            off += l1
            val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - headerSize() - 28
            val buf2 = ByteArray(l2)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf2, 0, off, off + l2)
            buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
            off += l2
if(SanityCheck.enabled){if(!( off == ByteArrayWrapperExt.getSize(buffer) )){throw Exception("SanityCheck failed")}}
            val seconds = helper_decimalFromByteArray(buf2)
            return seconds
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTimeAsTyped_Content(buffer: ByteArrayWrapper): String {
        return DictionaryHelperLarge.byteArrayToDateTimeAsTyped_Content(buffer)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_TZ(buffer: ByteArrayWrapper): String {
        if (headerDecodeFlag(buffer) == 0x80) {
            return ""
        } else {
            var off = 0
            off += headerSize()
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            val timezoneHours = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val timezoneMinutes = ByteArrayWrapperExt.readInt4(buffer, off)
            if (timezoneHours == 0 && timezoneMinutes == 0) {
                return "Z"
            }
            if (timezoneHours == -99 && timezoneMinutes == -99) {
                return ""
            }
            return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDateTime_TimeZone(buffer: ByteArrayWrapper): String {
        if (headerDecodeFlag(buffer) == 0x80) {
            return ""
        } else {
            var off = 0
            off += headerSize()
            off += 4
            off += 4
            off += 4
            off += 4
            off += 4
            val timezoneHours = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val timezoneMinutes = ByteArrayWrapperExt.readInt4(buffer, off)
            if (timezoneHours == 0 && timezoneMinutes == 0) {
                return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
            }
            if (timezoneHours >= 0 && timezoneMinutes == 0) {
                return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
            }
            return ""
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun booleanToByteArray(buffer: ByteArrayWrapper, value: String) {
        booleanToByteArray(buffer, value.toBoolean())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun booleanToByteArray(buffer: ByteArrayWrapper, value: Boolean) {
        ByteArrayWrapperExt.setSize(buffer, headerSize(), false)
        if (value) {
            headerEncode(buffer, ETripleComponentTypeExt.BOOLEAN, 0x80)
        } else {
            headerEncode(buffer, ETripleComponentTypeExt.BOOLEAN, 0x00)
        }
if(SanityCheck.enabled){if(!( byteArrayToBoolean(buffer) == value )){throw Exception("SanityCheck failed")}}
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayAsBoolean(buffer: ByteArrayWrapper): Boolean {
        return when (byteArrayToType(buffer)) {
            ETripleComponentTypeExt.BOOLEAN -> byteArrayToBoolean(buffer)
            ETripleComponentTypeExt.ERROR -> throw InvalidInputException("Error can not be cast to boolean")
            ETripleComponentTypeExt.UNDEF -> throw InvalidInputException("Undef can not be cast to boolean")
            ETripleComponentTypeExt.BLANK_NODE -> throw InvalidInputException("BlankNode can not be cast to boolean")
            ETripleComponentTypeExt.DATE_TIME,
            ETripleComponentTypeExt.IRI,
            ETripleComponentTypeExt.STRING,
            ETripleComponentTypeExt.STRING_LANG,
            ETripleComponentTypeExt.STRING_TYPED,
            -> false
            ETripleComponentTypeExt.DECIMAL -> byteArrayToDecimal_I(buffer) == BigDecimal.ONE
            ETripleComponentTypeExt.INTEGER -> byteArrayToInteger_I(buffer) == BigInteger.ONE
            ETripleComponentTypeExt.DOUBLE -> byteArrayToDouble_I(buffer) == 1.0
            ETripleComponentTypeExt.FLOAT -> byteArrayToFloat_I(buffer) == 1.0

            else -> TODO("byteArrayAsBoolean ${byteArrayToType(buffer)}")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToBoolean(buffer: ByteArrayWrapper): Boolean {
        val flag = headerDecodeFlag(buffer)
if(SanityCheck.enabled){if(!( flag == 0x0 || flag == 0x80  )){throw Exception("SanityCheck failed")}}
        return flag == 0x80
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun integerToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = helper_intCheckString(value).encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + buf1.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.INTEGER, 0)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun integerToByteArray(buffer: ByteArrayWrapper, value: BigInteger) {
        integerToByteArray(buffer, helper_intToString(value))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToInteger_S(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - headerSize()
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToInteger_I(buffer: ByteArrayWrapper): BigInteger {
        return helper_intFromString(byteArrayToInteger_S(buffer))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun decimalToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = helper_decimalCheckString(value).encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + buf1.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.DECIMAL, 0)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun decimalToByteArray(buffer: ByteArrayWrapper, value: BigDecimal) {
        decimalToByteArray(buffer, helper_decimalToString(value))
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDecimal_I(buffer: ByteArrayWrapper): BigDecimal {
        val res = helper_decimalFromString(byteArrayToDecimal_S(buffer))
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDecimal_S(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - headerSize()
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun doubleToByteArray(buffer: ByteArrayWrapper, value: Double) {
        ByteArrayWrapperExt.setSize(buffer, headerSize() + 8, false)
        headerEncode(buffer, ETripleComponentTypeExt.DOUBLE, 0)
        ByteArrayWrapperExt.writeDouble8(buffer, headerSize(), value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun doubleToByteArray(buffer: ByteArrayWrapper, value: String) {
        doubleToByteArray(buffer, value.toDouble())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDouble_I(buffer: ByteArrayWrapper): Double {
        return ByteArrayWrapperExt.readDouble8(buffer, headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToDouble_S(buffer: ByteArrayWrapper): String {
        return byteArrayToDouble_I(buffer).toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun floatToByteArray(buffer: ByteArrayWrapper, value: Double) {
        ByteArrayWrapperExt.setSize(buffer, headerSize() + 8, false)
        headerEncode(buffer, ETripleComponentTypeExt.FLOAT, 0)
        ByteArrayWrapperExt.writeDouble8(buffer, headerSize(), value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun floatToByteArray(buffer: ByteArrayWrapper, value: String) {
        floatToByteArray(buffer, value.toDouble())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToFloat_I(buffer: ByteArrayWrapper): Double {
        return ByteArrayWrapperExt.readDouble8(buffer, headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToFloat_S(buffer: ByteArrayWrapper): String {
        return byteArrayToFloat_I(buffer).toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun langToByteArray(buffer: ByteArrayWrapper, content: String, lang: String) {
        val buf1 = lang.lowercase().encodeToByteArray()
        val buf2 = content.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + 4 + buf1.size + buf2.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.STRING_LANG, 0)
        ByteArrayWrapperExt.writeInt4(buffer, headerSize() + buf1.size + buf2.size, buf1.size)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize())
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize() + buf1.size)
if(SanityCheck.enabled){if(!( content == byteArrayToLang_Content(buffer)  )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!(  lang.lowercase() == byteArrayToLang_Lang(buffer)  )){throw Exception("SanityCheck failed")}}
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToLang_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.readInt4(buffer, ByteArrayWrapperExt.getSize(buffer) - 4)
        val l2 = ByteArrayWrapperExt.getSize(buffer) - headerSize() - 4 - l1
        val buf = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize() + l1, headerSize() + l1 + l2)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToLang_Lang(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.readInt4(buffer, ByteArrayWrapperExt.getSize(buffer) - 4)
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun typedToByteArray(buffer: ByteArrayWrapper, content: String, type: String) {
        DictionaryHelperLarge.typedToByteArray(buffer, content, type)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToTyped_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.readInt4(buffer, ByteArrayWrapperExt.getSize(buffer) - 4)
        val l2 = ByteArrayWrapperExt.getSize(buffer) - headerSize() - 4 - l1
        val buf = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize() + l1, headerSize() + l1 + l2)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToTyped_Type(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.readInt4(buffer, ByteArrayWrapperExt.getSize(buffer) - 4)
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + 4 + buf1.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.BLANK_NODE, 0)
        ByteArrayWrapperExt.writeInt4(buffer, headerSize(), buf1.size)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize() + 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        ByteArrayWrapperExt.setSize(buffer, headerSize() + DictionaryValueHelper.getSize(), false)
        headerEncode(buffer, ETripleComponentTypeExt.BLANK_NODE, 0x80)
        DictionaryValueHelper.toByteArray(buffer, headerSize(), value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToBnode_I(buffer: ByteArrayWrapper): DictionaryValueType {
        if (headerDecodeFlag(buffer) == 0x80) {
            return DictionaryValueHelper.fromByteArray(buffer, headerSize())
        } else {
            throw InvalidInputException("this is not ready to be used as instanciated value")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToBnode_S(buffer: ByteArrayWrapper): String {
        if (headerDecodeFlag(buffer) == 0x80) {
            throw InvalidInputException("this is not ready to be used as import value")
        } else {
            val l1 = ByteArrayWrapperExt.readInt4(buffer, headerSize())
            val buf = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize() + 4, headerSize() + 4 + l1)
            return buf.decodeToString()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToBnode_A(buffer: ByteArrayWrapper): String {
        if (headerDecodeFlag(buffer) == 0x80) {
            return "_:" + DictionaryValueHelper.fromByteArray(buffer, headerSize()).toString()
        } else {
            val l1 = ByteArrayWrapperExt.readInt4(buffer, headerSize())
            val buf = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize() + 4, headerSize() + 4 + l1)
            return buf.decodeToString()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun iriToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + buf1.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.IRI, 0)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToIri(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - headerSize()
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToString(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - headerSize()
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, headerSize(), headerSize() + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun stringToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, headerSize() + buf1.size, false)
        headerEncode(buffer, ETripleComponentTypeExt.STRING, 0)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), headerSize())
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun sparqlToByteArray(buffer: ByteArrayWrapper, value: String?) {
        DictionaryHelperLarge.sparqlToByteArray(buffer, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun removeQuotesFromString(s: String): String {
        var c = s[0]
        var cntLeft = 1
        var cntRight = 0
        if (c != '\'' && c != '"' || c != s[s.length - 1]) {
            throw InvalidInputException("invalid quoted string >$s<")
        }
        while (cntLeft < s.length && s[cntLeft] == c) {
            cntLeft++
        }
        while (cntRight < s.length && s[s.length - cntRight - 1] == c) {
            cntRight++
        }
        if (cntLeft >= 3 && cntRight >= 3 && s.length >= 6) {
            return s.substring(3, s.length - 3)
        }
        return s.substring(1, s.length - 1)
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToType(buffer: ByteArrayWrapper): ETripleComponentType {
        val res = headerDecodeType(buffer)
if(SanityCheck.enabled){if(!( res >= 0 )){throw Exception("SanityCheck failed")}}
if(SanityCheck.enabled){if(!( res < ETripleComponentTypeExt.values_size )){throw Exception("SanityCheck failed")}}
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToXMLElement(buffer: ByteArrayWrapper): XMLElement {
        val type = byteArrayToType(buffer)
        return when (type) {
            ETripleComponentTypeExt.BLANK_NODE -> XMLElement("ValueBnode").addAttribute("dictvalue", byteArrayToBnode_I(buffer).toString())
            ETripleComponentTypeExt.BOOLEAN -> XMLElement("ValueBoolean").addAttribute("value", byteArrayToBoolean(buffer).toString())
            ETripleComponentTypeExt.UNDEF -> XMLElement("ValueUndef")
            ETripleComponentTypeExt.ERROR -> XMLElement("ValueError")
            ETripleComponentTypeExt.DOUBLE -> XMLElement("ValueDouble").addAttribute("value", byteArrayToDouble_S(buffer))
            ETripleComponentTypeExt.FLOAT -> XMLElement("ValueFloat").addAttribute("value", byteArrayToFloat_S(buffer))
            ETripleComponentTypeExt.INTEGER -> XMLElement("ValueInteger").addAttribute("value", byteArrayToInteger_S(buffer))
            ETripleComponentTypeExt.DECIMAL -> XMLElement("ValueDecimal").addAttribute("value", byteArrayToDecimal_S(buffer))
            ETripleComponentTypeExt.IRI -> XMLElement("ValueIri").addAttribute("value", byteArrayToIri(buffer))
            ETripleComponentTypeExt.STRING -> XMLElement("ValueSimpleLiteral").addAttribute("delimiter", "\"").addAttribute("content", byteArrayToString(buffer))
            ETripleComponentTypeExt.STRING_LANG -> XMLElement("ValueLanguageTaggedLiteral").addAttribute("delimiter", "\"").addAttribute("content", byteArrayToLang_Content(buffer)).addAttribute("language", byteArrayToLang_Lang(buffer))
            ETripleComponentTypeExt.STRING_TYPED -> XMLElement("ValueTypedLiteral").addAttribute("delimiter", "\"").addAttribute("content", byteArrayToTyped_Content(buffer)).addAttribute("type_iri", byteArrayToTyped_Type(buffer))
            ETripleComponentTypeExt.DATE_TIME -> XMLElement("ValueDateTime").addAttribute("value", byteArrayToDateTimeAsTyped_Content(buffer))
            else -> throw UnreachableException()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayToSparql(buffer: ByteArrayWrapper): String {
        val type = byteArrayToType(buffer)
        return when (type) {
            ETripleComponentTypeExt.UNDEF -> "UNDEF"
            ETripleComponentTypeExt.ERROR -> "ERROR"
            ETripleComponentTypeExt.BLANK_NODE -> byteArrayToBnode_A(buffer)
            ETripleComponentTypeExt.BOOLEAN -> {
                if (byteArrayToBoolean(buffer)) {
                    "\"true\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                } else {
                    "\"false\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
                }
            }
            ETripleComponentTypeExt.DOUBLE -> "\"" + byteArrayToDouble_S(buffer) + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
            ETripleComponentTypeExt.FLOAT -> "\"" + byteArrayToFloat_S(buffer) + "\"^^<http://www.w3.org/2001/XMLSchema#float>"
            ETripleComponentTypeExt.INTEGER -> "\"" + byteArrayToInteger_S(buffer) + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
            ETripleComponentTypeExt.DECIMAL -> "\"" + byteArrayToDecimal_S(buffer) + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
            ETripleComponentTypeExt.IRI -> "<" + byteArrayToIri(buffer) + ">"
            ETripleComponentTypeExt.STRING -> "\"" + byteArrayToString(buffer) + "\""
            ETripleComponentTypeExt.STRING_LANG -> "\"" + byteArrayToLang_Content(buffer) + "\"@" + byteArrayToLang_Lang(buffer)
            ETripleComponentTypeExt.STRING_TYPED -> "\"" + byteArrayToTyped_Content(buffer) + "\"^^<" + byteArrayToTyped_Type(buffer) + ">"
            ETripleComponentTypeExt.DATE_TIME -> "\"" + byteArrayToDateTimeAsTyped_Content(buffer) + "\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
            else -> throw UnreachableException()
        }
    }

    internal inline fun byteArrayToCallback(
        buffer: ByteArrayWrapper,
        crossinline onBNode: (value: DictionaryValueType) -> Unit,
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
        val type = byteArrayToType(buffer)
        when (type) {
            ETripleComponentTypeExt.FLOAT -> onFloat(byteArrayToFloat_I(buffer))
            ETripleComponentTypeExt.DOUBLE -> onDouble(byteArrayToDouble_I(buffer))
            ETripleComponentTypeExt.INTEGER -> onInteger(byteArrayToInteger_S(buffer))
            ETripleComponentTypeExt.DECIMAL -> onDecimal(byteArrayToDecimal_S(buffer))
            ETripleComponentTypeExt.UNDEF -> onUndefined()
            ETripleComponentTypeExt.ERROR -> onError()
            ETripleComponentTypeExt.BLANK_NODE -> onBNode(byteArrayToBnode_I(buffer))
            ETripleComponentTypeExt.BOOLEAN -> onBoolean(byteArrayToBoolean(buffer))
            ETripleComponentTypeExt.IRI -> onIri(byteArrayToIri(buffer))
            ETripleComponentTypeExt.STRING -> onSimpleLiteral(byteArrayToString(buffer))
            ETripleComponentTypeExt.STRING_LANG -> onLanguageTaggedLiteral(byteArrayToLang_Content(buffer), byteArrayToLang_Lang(buffer))
            ETripleComponentTypeExt.STRING_TYPED -> onTypedLiteral(byteArrayToTyped_Content(buffer), byteArrayToTyped_Type(buffer))
            ETripleComponentTypeExt.DATE_TIME -> onTypedLiteral(byteArrayToDateTimeAsTyped_Content(buffer), "http://www.w3.org/2001/XMLSchema#dateTime")
            else -> throw UnreachableException()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    internal inline fun byteArrayCompareAny(a: ByteArrayWrapper, b: ByteArrayWrapper): Int {
        val typeA = byteArrayToType(a)
        val typeB = byteArrayToType(b)
        val result = if (typeA != typeB) {
            val reverse = typeB < typeA
            val typeC = if (reverse) typeB else typeA
            val typeD = if (reverse) typeA else typeB
            val c = if (reverse) b else a
            val d = if (reverse) a else b
            val res = if (typeC == ETripleComponentTypeExt.UNDEF) {
                -1
            } else if (typeD == ETripleComponentTypeExt.UNDEF) {
                1
            } else if (typeC == ETripleComponentTypeExt.ERROR) {
                -1
            } else if (typeD == ETripleComponentTypeExt.ERROR) {
                1
            } else if (typeC == ETripleComponentTypeExt.BLANK_NODE) {
                -1
            } else if (typeD == ETripleComponentTypeExt.BLANK_NODE) {
                1
            } else if (typeC == ETripleComponentTypeExt.IRI) {
                -1
            } else if (typeD == ETripleComponentTypeExt.IRI) {
                1
            } else if (typeC == ETripleComponentTypeExt.DECIMAL) {
                val cv = byteArrayToDecimal_I(c)
                if (typeD == ETripleComponentTypeExt.DOUBLE) {
                    cv.compareTo(byteArrayToDouble_I(d))
                } else if (typeD == ETripleComponentTypeExt.FLOAT) {
                    cv.compareTo(byteArrayToFloat_I(d))
                } else if (typeD == ETripleComponentTypeExt.INTEGER) {
                    cv.compareTo(BigDecimal.fromBigInteger(byteArrayToInteger_I(d)))
                } else if (typeD == ETripleComponentTypeExt.STRING_TYPED) {
"http://www.w3.org/2001/XMLSchema#decimal".compareTo(byteArrayToTyped_Type(d))
                } else if (typeD == ETripleComponentTypeExt.STRING) {
"http://www.w3.org/2001/XMLSchema#decimal".compareTo("http://www.w3.org/2001/XMLSchema#string")
                } else {
                    TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
                }
            } else if (typeC == ETripleComponentTypeExt.DOUBLE) {
                val cv = byteArrayToDouble_I(c)
                if (typeD == ETripleComponentTypeExt.FLOAT) {
                    cv.compareTo(byteArrayToFloat_I(d))
                } else if (typeD == ETripleComponentTypeExt.INTEGER) {
                    -byteArrayToInteger_I(d).compareTo(cv)
                } else if (typeD == ETripleComponentTypeExt.STRING_TYPED) {
"http://www.w3.org/2001/XMLSchema#double".compareTo(byteArrayToTyped_Type(d))
                } else if (typeD == ETripleComponentTypeExt.STRING) {
"http://www.w3.org/2001/XMLSchema#double".compareTo("http://www.w3.org/2001/XMLSchema#string")
                } else {
                    TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
                }
            } else if (typeC == ETripleComponentTypeExt.FLOAT) {
                val cv = byteArrayToFloat_I(c)
                if (typeD == ETripleComponentTypeExt.INTEGER) {
                    -byteArrayToInteger_I(d).compareTo(cv)
                } else if (typeD == ETripleComponentTypeExt.STRING_TYPED) {
"http://www.w3.org/2001/XMLSchema#float".compareTo(byteArrayToTyped_Type(d))
                } else if (typeD == ETripleComponentTypeExt.STRING) {
"http://www.w3.org/2001/XMLSchema#float".compareTo("http://www.w3.org/2001/XMLSchema#string")
                } else {
                    TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
                }
            } else if (typeC == ETripleComponentTypeExt.INTEGER) {
                if (typeD == ETripleComponentTypeExt.STRING_TYPED) {
"http://www.w3.org/2001/XMLSchema#integer".compareTo(byteArrayToTyped_Type(d))
                } else if (typeD == ETripleComponentTypeExt.STRING) {
"http://www.w3.org/2001/XMLSchema#integer".compareTo("http://www.w3.org/2001/XMLSchema#string")
                } else {
                    TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
                }
} else if (typeC == ETripleComponentTypeExt.DATE_TIME) {
                if (typeD == ETripleComponentTypeExt.STRING_TYPED) {
"http://www.w3.org/2001/XMLSchema#dateTime".compareTo(byteArrayToTyped_Type(d))
                } else if (typeD == ETripleComponentTypeExt.STRING) {
"http://www.w3.org/2001/XMLSchema#dateTime".compareTo("http://www.w3.org/2001/XMLSchema#string")
                } else {
                    TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
                }
            } else {
                TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeC]} vs ${ETripleComponentTypeExt.names[typeD]}")
            }
            if (reverse) -res else res
        } else {
            if (typeA == ETripleComponentTypeExt.UNDEF || typeA == ETripleComponentTypeExt.ERROR) {
                0
            } else if (typeA == ETripleComponentTypeExt.BLANK_NODE) {
                a.compareTo(b)
            } else if (typeA == ETripleComponentTypeExt.BOOLEAN) {
                a.compareTo(b)
            } else if (typeA == ETripleComponentTypeExt.DATE_TIME) {
                a.compareTo(b)
            } else if (typeA == ETripleComponentTypeExt.DECIMAL) {
                val av = byteArrayToDecimal_I(a)
                val bv = byteArrayToDecimal_I(b)
                av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.DOUBLE) {
                val av = byteArrayToDouble_I(a)
                val bv = byteArrayToDouble_I(b)
                av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.FLOAT) {
                val av = byteArrayToFloat_I(a)
                val bv = byteArrayToFloat_I(b)
                av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.INTEGER) {
                val av = byteArrayToInteger_I(a)
                val bv = byteArrayToInteger_I(b)
                av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.STRING_LANG || typeA == ETripleComponentTypeExt.STRING_TYPED || typeA == ETripleComponentTypeExt.IRI || typeA == ETripleComponentTypeExt.STRING) {
                a.compareTo(b)
            } else {
                TODO("byteArrayCompareAny UNKNOWN combination ${ETripleComponentTypeExt.names[typeA]} vs ${ETripleComponentTypeExt.names[typeB]}")
            }
        }
        // println("compare ${byteArrayToSparql(a)} to  ${byteArrayToSparql(b)} -> $result")
        return result
    }
}
