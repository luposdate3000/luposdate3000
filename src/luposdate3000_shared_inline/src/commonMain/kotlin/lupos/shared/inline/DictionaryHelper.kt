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
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.Sign
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.ETripleComponentType
import lupos.shared.ETripleComponentTypeExt
import lupos.shared.SanityCheck
import lupos.shared.ValueBnode
import lupos.shared.ValueDateTime
import lupos.shared.ValueDecimal
import lupos.shared.ValueDefinition
import lupos.shared.ValueDouble
import lupos.shared.ValueFloat
import lupos.shared.ValueInteger
import lupos.shared.ValueIri
import lupos.shared.ValueLanguageTaggedLiteral
import lupos.shared.ValueSimpleLiteral
import lupos.shared.ValueTypedLiteral
import lupos.shared.XMLElement
import lupos.shared.dictionary.DictionaryExt
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

internal object DictionaryHelper {
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
     * -> string
     * ETripleComponentTypeExt.DECIMAL
     * -> string
     * ETripleComponentTypeExt.FLOAT
     * -> IEEE 754 floating-point "double format" bit layout, preserving Not-a-Number (NaN) values.
     * ETripleComponentTypeExt.DATE_TIME
     * -> year.length
     * -> year.string
     * -> month.Int
     * -> day.Int
     * -> hours.Int
     * -> minutes.Int
     * -> seconds.string
     * -> timezoneHours.Int
     * -> timezoneMinutes.Int
     */
    @Suppress("NOTHING_TO_INLINE")
    public inline fun errorToByteArray(buffer: ByteArrayWrapper) {
        ByteArrayWrapperExt.setSize(buffer, 4)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.ERROR)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun undefToByteArray(buffer: ByteArrayWrapper) {
        ByteArrayWrapperExt.setSize(buffer, 4)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.UNDEF)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun dateTimeToByteArray(buffer: ByteArrayWrapper, str: String) {
        val year: String
        val month: Int
        val day: Int
        val hours: Int
        val minutes: Int
        val seconds: String
        val timezoneHours: Int
        val timezoneMinutes: Int
        val hasTimeZone: Boolean
        var idx = 0
        var idx2 = str.indexOf('-', 1)
        if (idx2 < idx) {
            idx2 = str.length - 1
        }
        if (idx2 > idx) {
            year = str.substring(idx, idx2)
            idx = idx2
            idx2 = str.indexOf('-', idx + 1)
            if (idx2 < idx) {
                idx2 = str.length - 1
            }
            if (idx2 > idx) {
                month = str.substring(idx + 1, idx2).toInt()
                idx = idx2
                idx2 = str.indexOf('T', idx + 1)
                if (idx2 < idx) {
                    idx2 = str.length - 1
                }
                if (idx2 > idx) {
                    day = str.substring(idx + 1, idx2).toInt()
                    idx = idx2
                    idx2 = str.indexOf(':', idx + 1)
                    if (idx2 < idx) {
                        idx2 = str.length - 1
                    }
                    if (idx2 > idx) {
                        hours = str.substring(idx + 1, idx2).toInt()
                        idx = idx2
                        idx2 = str.indexOf(':', idx + 1)
                        if (idx2 < idx) {
                            idx2 = str.length - 1
                        }
                        if (idx2 > idx) {
                            minutes = str.substring(idx + 1, idx2).toInt()
                            idx = idx2
                            val idxa = str.indexOf('Z', idx + 1)
                            val idxb = str.indexOf('+', idx + 1)
                            val idxc = str.indexOf('-', idx + 1)
                            if (idxa > idx) {
                                seconds = str.substring(idx + 1, idxa)
                                timezoneHours = 0
                                timezoneMinutes = 0
                                hasTimeZone = true
                            } else if (idxb > idx) {
                                seconds = str.substring(idx + 1, idxb)
                                idx = idxb
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length).toInt()
                                    hasTimeZone = true
                                } else {
                                    timezoneHours = -99
                                    timezoneMinutes = -99
                                    hasTimeZone = false
                                }
                            } else if (idxc > idx) {
                                seconds = str.substring(idx + 1, idxc)
                                idx = idxc
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length).toInt()
                                    hasTimeZone = true
                                } else {
                                    timezoneHours = -99
                                    timezoneMinutes = -99
                                    hasTimeZone = false
                                }
                            } else {
                                seconds = str.substring(idx + 1, str.length)
                                timezoneHours = -99
                                timezoneMinutes = -99
                                hasTimeZone = false
                            }
                        } else {
                            minutes = 0
                            seconds = "0.0"
                            timezoneHours = -99
                            timezoneMinutes = -99
                            hasTimeZone = false
                        }
                    } else {
                        hours = 0
                        minutes = 0
                        seconds = "0.0"
                        timezoneHours = -99
                        timezoneMinutes = -99
                        hasTimeZone = false
                    }
                } else {
                    day = 0
                    hours = 0
                    minutes = 0
                    seconds = "0.0"
                    timezoneHours = -99
                    timezoneMinutes = -99
                    hasTimeZone = false
                }
            } else {
                month = 0
                day = 0
                hours = 0
                minutes = 0
                seconds = "0.0"
                timezoneHours = -99
                timezoneMinutes = -99
                hasTimeZone = false
            }
        } else {
            year = "0"
            month = 0
            day = 0
            hours = 0
            minutes = 0
            seconds = "0.0"
            timezoneHours = -99
            timezoneMinutes = -99
            hasTimeZone = false
        }
        dateTimeToByteArray(buffer, BigInteger.parseString(year, 10), month, day, hours, minutes, BigDecimal.parseString(seconds, 10), timezoneHours, timezoneMinutes, hasTimeZone)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun dateTimeToByteArray(buffer: ByteArrayWrapper) {
        val dateNow = DateHelper()
        dateTimeToByteArray(buffer, BigInteger.parseString(dateNow.year().toString(), 10), dateNow.month(), dateNow.day(), dateNow.hours(), dateNow.minutes(), BigDecimal.parseString(dateNow.seconds().toString(), 10), -99, -99, false)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun dateTimeToByteArray(buffer: ByteArrayWrapper, year: BigInteger, month: Int, day: Int, hours: Int, minutes: Int, seconds: BigDecimal, timezoneHours: Int, timezoneMinutes: Int, hasTimeZone: Boolean) {
        SanityCheck.check({ month >= 0 }, { "dateTimeToByteArray.month : $month" })
        SanityCheck.check({ month <= 99 }, { "dateTimeToByteArray.month : $month" })
        SanityCheck.check({ day >= 0 }, { "dateTimeToByteArray.day : $day" })
        SanityCheck.check({ day <= 99 }, { "dateTimeToByteArray.day : $day" })
        SanityCheck.check({ hours >= 0 }, { "dateTimeToByteArray.hours : $hours" })
        SanityCheck.check({ hours <= 24 }, { "dateTimeToByteArray.hours : $hours" })
        SanityCheck.check({ minutes >= 0 }, { "dateTimeToByteArray.minutes : $minutes" })
        SanityCheck.check({ minutes <= 99 }, { "dateTimeToByteArray.minutes : $minutes" })
        SanityCheck.check({ !hasTimeZone || timezoneHours >= -24 }, { "dateTimeToByteArray.timezoneHours : $timezoneHours" })
        SanityCheck.check({ !hasTimeZone || timezoneHours <= 24 }, { "dateTimeToByteArray.timezoneHours : $timezoneHours" })
        SanityCheck.check({ !hasTimeZone || timezoneMinutes >= 0 }, { "dateTimeToByteArray.timezoneMinutes : $timezoneMinutes" })
        SanityCheck.check({ !hasTimeZone || timezoneMinutes <= 99 }, { "dateTimeToByteArray.timezoneMinutes : $timezoneMinutes" })
        val buf1 = year.toByteArray()
        val buf2 = seconds.significand.toByteArray()
        val l1 = buf1.size
        val l2 = buf2.size
        ByteArrayWrapperExt.setSize(buffer, 42 + l1 + l2)
        var off = 0
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, ETripleComponentTypeExt.DATE_TIME)
        off += 4
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, l1)
        off += 4
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, month)
        off += 4
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, day)
        off += 4
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, hours)
        off += 4
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, minutes)
        off += 4
        if (hasTimeZone) {
            ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, timezoneHours)
            off += 4
            ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, timezoneMinutes)
            off += 4
        } else {
            ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, -99)
            off += 4
            ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), off, -99)
            off += 4
        }
        ByteArrayHelper.writeLong8(ByteArrayWrapperExt.getBuf(buffer), off, seconds.exponent)
        off += 8
        ByteArrayWrapperExt.getBuf(buffer)[off] = year.signum().toByte()
        off++
        ByteArrayWrapperExt.getBuf(buffer)[off] = seconds.signum().toByte()
        off++
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l1
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l2
        SanityCheck.check { off == ByteArrayWrapperExt.getSize(buffer) }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Year(buffer: ByteArrayWrapper): BigInteger {
        var off = 0
        off += 4
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 8
        val yearSignum = when (ByteArrayWrapperExt.getBuf(buffer)[off]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        off++
        off++
        val buf1 = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf1, 0, off, off + l1)
        off += l1
        val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - 42
        off += l2
        SanityCheck.check { off == ByteArrayWrapperExt.getSize(buffer) }
        val year = BigInteger.fromByteArray(buf1, yearSignum)
        return year
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Month(buffer: ByteArrayWrapper): BigInteger {
        var off = 0
        off += 4
        off += 4
        val month = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        return BigInteger(month)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Day(buffer: ByteArrayWrapper): BigInteger {
        var off = 0
        off += 4
        off += 4
        off += 4
        val day = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        return BigInteger(day)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Hours(buffer: ByteArrayWrapper): BigInteger {
        var off = 0
        off += 4
        off += 4
        off += 4
        off += 4
        val hours = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        return BigInteger(hours)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Minutes(buffer: ByteArrayWrapper): BigInteger {
        var off = 0
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        val minutes = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        return BigInteger(minutes)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_Seconds(buffer: ByteArrayWrapper): BigDecimal {
        var off = 0
        off += 4
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        val secondsExponent = ByteArrayHelper.readLong8(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 8
        off++
        val secondsSignum = when (ByteArrayWrapperExt.getBuf(buffer)[off]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        off++
        off += l1
        val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - 42
        val buf2 = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf2, 0, off, off + l2)
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l2
        SanityCheck.check { off == ByteArrayWrapperExt.getSize(buffer) }
        val seconds = BigDecimal.fromBigIntegerWithExponent(BigInteger.fromByteArray(buf2, secondsSignum), secondsExponent)
        return seconds
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTimeAsTyped_Content(buffer: ByteArrayWrapper): String {
        var off = 0
        off += 4
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val month = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val day = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val hours = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val minutes = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val timezoneHours = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val timezoneMinutes = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val secondsExponent = ByteArrayHelper.readLong8(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 8
        val yearSignum = when (ByteArrayWrapperExt.getBuf(buffer)[off]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        off++
        val secondsSignum = when (ByteArrayWrapperExt.getBuf(buffer)[off]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        off++
        val buf1 = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf1, 0, off, off + l1)
        off += l1
        val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - 42
        val buf2 = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf2, 0, off, off + l2)
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l2
        SanityCheck.check { off == ByteArrayWrapperExt.getSize(buffer) }
        val year = BigInteger.fromByteArray(buf1, yearSignum)
        val seconds = BigDecimal.fromBigIntegerWithExponent(BigInteger.fromByteArray(buf2, secondsSignum), secondsExponent)
        val secondsString2 = seconds.toStringExpanded().split(".")
        var secondsString = secondsString2[0].padStart(2, '0')
        if (secondsString2.size > 1) {
            var tmp = secondsString2[1]
            while (tmp.endsWith('0')) {
                tmp = tmp.substring(0, tmp.length - 1)
            }
            if (tmp.length > 0) {
                secondsString += "." + tmp
            }
        }
        return if (timezoneHours == -99 && timezoneMinutes == -99) {
            "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:$secondsString"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
            "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}Z"
        } else {
            var timezoneHoursLocal = timezoneHours.toString()
            if (timezoneHoursLocal[0] == '-' || timezoneHoursLocal[0] == '+') {
                timezoneHoursLocal = "" + timezoneHoursLocal[0] + timezoneHoursLocal.substring(1).padStart(2, '0')
            } else {
                timezoneHoursLocal = "+" + timezoneHoursLocal.padStart(2, '0')
            }
            "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}$timezoneHoursLocal:${timezoneMinutes.toString().padStart(2, '0')}"
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_TZ(buffer: ByteArrayWrapper): String {
        var off = 0
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        val timezoneHours = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val timezoneMinutes = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "Z"
        }
        if (timezoneHours == -99 && timezoneMinutes == -99) {
            return ""
        }
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDateTime_TimeZone(buffer: ByteArrayWrapper): String {
        var off = 0
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        off += 4
        val timezoneHours = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        off += 4
        val timezoneMinutes = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), off)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        return ""
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun booleanToByteArray(buffer: ByteArrayWrapper, value: Boolean) {
        ByteArrayWrapperExt.setSize(buffer, 5)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.BOOLEAN)
        if (value) {
            ByteArrayWrapperExt.getBuf(buffer)[4] = 1
        } else {
            ByteArrayWrapperExt.getBuf(buffer)[4] = 0
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToBoolean(buffer: ByteArrayWrapper): Boolean {
        return ByteArrayWrapperExt.getBuf(buffer)[4] != 0.toByte()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun integerToByteArray(buffer: ByteArrayWrapper, value: String) {
        integerToByteArray(buffer, BigInteger.parseString(value, 10))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun integerToByteArray(buffer: ByteArrayWrapper, value: BigInteger) {
        val buf1 = value.toByteArray()
        ByteArrayWrapperExt.setSize(buffer, 5 + buf1.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.INTEGER)
        ByteArrayWrapperExt.getBuf(buffer)[4] = value.signum().toByte()
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 5)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToInteger_S(buffer: ByteArrayWrapper): String {
        return byteArrayToInteger_I(buffer).toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToInteger_I(buffer: ByteArrayWrapper): BigInteger {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - 5
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 5, 5 + l1)
        val sign = when (ByteArrayWrapperExt.getBuf(buffer)[4]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        return BigInteger.fromByteArray(buf, sign)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun decimalToByteArray(buffer: ByteArrayWrapper, value: String) {
        decimalToByteArray(buffer, BigDecimal.parseString(value, 10))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun decimalToByteArray(buffer: ByteArrayWrapper, value: BigDecimal) {
        val buf1 = value.significand.toByteArray()
        ByteArrayWrapperExt.setSize(buffer, 13 + buf1.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.DECIMAL)
        ByteArrayHelper.writeLong8(ByteArrayWrapperExt.getBuf(buffer), 4, value.exponent)
        ByteArrayWrapperExt.getBuf(buffer)[12] = value.signum().toByte()
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 13)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDecimal_I(buffer: ByteArrayWrapper): BigDecimal {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - 13
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 13, 13 + l1)
        val exponent = ByteArrayHelper.readLong8(ByteArrayWrapperExt.getBuf(buffer), 4)
        val sign = when (ByteArrayWrapperExt.getBuf(buffer)[12]) {
            (-1).toByte() -> Sign.NEGATIVE
            1.toByte() -> Sign.POSITIVE
            else -> Sign.ZERO
        }
        return BigDecimal.fromBigIntegerWithExponent(BigInteger.fromByteArray(buf, sign), exponent)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDecimal_S(buffer: ByteArrayWrapper): String {
        val tmp = byteArrayToDecimal_I(buffer).toStringExpanded()
        if (tmp.contains('.')) {
            return tmp
        }
        return tmp + ".0"
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun doubleToByteArray(buffer: ByteArrayWrapper, value: Double) {
        ByteArrayWrapperExt.setSize(buffer, 12)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.DOUBLE)
        ByteArrayHelper.writeDouble8(ByteArrayWrapperExt.getBuf(buffer), 4, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun doubleToByteArray(buffer: ByteArrayWrapper, value: String) {
        ByteArrayWrapperExt.setSize(buffer, 12)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.DOUBLE)
        ByteArrayHelper.writeDouble8(ByteArrayWrapperExt.getBuf(buffer), 4, value.toDouble())
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDouble_I(buffer: ByteArrayWrapper): Double {
        return ByteArrayHelper.readDouble8(ByteArrayWrapperExt.getBuf(buffer), 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToDouble_S(buffer: ByteArrayWrapper): String {
        return ByteArrayHelper.readDouble8(ByteArrayWrapperExt.getBuf(buffer), 4).toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun floatToByteArray(buffer: ByteArrayWrapper, value: Double) {
        ByteArrayWrapperExt.setSize(buffer, 12)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.FLOAT)
        ByteArrayHelper.writeDouble8(ByteArrayWrapperExt.getBuf(buffer), 4, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun floatToByteArray(buffer: ByteArrayWrapper, value: String) {
        ByteArrayWrapperExt.setSize(buffer, 12)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.FLOAT)
        ByteArrayHelper.writeDouble8(ByteArrayWrapperExt.getBuf(buffer), 4, value.toDouble())
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToFloat_I(buffer: ByteArrayWrapper): Double {
        return ByteArrayHelper.readDouble8(ByteArrayWrapperExt.getBuf(buffer), 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToFloat_S(buffer: ByteArrayWrapper): String {
        return ByteArrayHelper.readDouble8(ByteArrayWrapperExt.getBuf(buffer), 4).toString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun langToByteArray(buffer: ByteArrayWrapper, content: String, lang: String) {
        val buf1 = lang.encodeToByteArray()
        val buf2 = content.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, 9 + buf1.size + buf2.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.STRING_LANG)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 5 + buf1.size + buf2.size, buf1.size)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 4)
        ByteArrayWrapperExt.getBuf(buffer)[4 + buf1.size] = 0
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), 5 + buf1.size)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToLang_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer) - 4)
        val l2 = ByteArrayWrapperExt.getSize(buffer) - 9 - l1
        val buf = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 5 + l1, 5 + l1 + l2)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToLang_Lang(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer) - 4)
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun typedToByteArray(buffer: ByteArrayWrapper, content: String, type: String) {
        try {
            when (type) {
                "http://www.w3.org/2001/XMLSchema#integer" -> integerToByteArray(buffer, content)
                "http://www.w3.org/2001/XMLSchema#decimal" -> decimalToByteArray(buffer, content)
                "http://www.w3.org/2001/XMLSchema#double" -> doubleToByteArray(buffer, content.toDouble())
                "http://www.w3.org/2001/XMLSchema#float" -> floatToByteArray(buffer, content.toDouble())
                "http://www.w3.org/2001/XMLSchema#boolean" -> booleanToByteArray(buffer, content.lowercase() == "true")
                "http://www.w3.org/2001/XMLSchema#dateTime" -> dateTimeToByteArray(buffer, content)
                else -> {
                    val buf1 = type.encodeToByteArray()
                    val buf2 = content.encodeToByteArray()
                    ByteArrayWrapperExt.setSize(buffer, 9 + buf1.size + buf2.size)
                    ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.STRING_TYPED)
                    ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 5 + buf1.size + buf2.size, buf1.size)
                    buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 4)
                    ByteArrayWrapperExt.getBuf(buffer)[4 + buf1.size] = 0
                    buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), 5 + buf1.size)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            stringToByteArray(buffer, content)
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToTyped_Content(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer) - 4)
        val l2 = ByteArrayWrapperExt.getSize(buffer) - 9 - l1
        val buf = ByteArray(l2)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 5 + l1, 5 + l1 + l2)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToTyped_Type(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), ByteArrayWrapperExt.getSize(buffer) - 4)
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: String) {
        SanityCheck.check { value.length > 0 }
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, 8 + buf1.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.BLANK_NODE)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 4, buf1.size)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 8)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun bnodeToByteArray(buffer: ByteArrayWrapper, value: DictionaryValueType) {
        ByteArrayWrapperExt.setSize(buffer, 4 + DictionaryValueHelper.getSize())
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.BLANK_NODE)
        DictionaryValueHelper.toByteArray(buffer, 4, value)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToBnode_I(buffer: ByteArrayWrapper): DictionaryValueType {
        if (ByteArrayWrapperExt.getSize(buffer) == 4 + DictionaryValueHelper.getSize()) {
            return DictionaryValueHelper.fromByteArray(buffer, 4)
        } else {
            throw Exception("this is not ready to be used as instanciated value")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToBnode_S(buffer: ByteArrayWrapper): String {
        if (ByteArrayWrapperExt.getSize(buffer) == 8) {
            throw Exception("this is not ready to be used as import value")
        } else {
            val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), 4)
            val buf = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 8, 8 + l1)
            return buf.decodeToString()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToBnode_A(buffer: ByteArrayWrapper): String {
        if (ByteArrayWrapperExt.getSize(buffer) == 8) {
            return "_:" + ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), 4).toString()
        } else {
            val l1 = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), 4)
            val buf = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 8, 8 + l1)
            return buf.decodeToString()
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun iriToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, 4 + buf1.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.IRI)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToIri(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - 4
        SanityCheck.check({ l1 >= 0 }, { "byteArrayToIri $l1" })
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToString(buffer: ByteArrayWrapper): String {
        val l1 = ByteArrayWrapperExt.getSize(buffer) - 4
        val buf = ByteArray(l1)
        ByteArrayWrapperExt.getBuf(buffer).copyInto(buf, 0, 4, 4 + l1)
        return buf.decodeToString()
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun stringToByteArray(buffer: ByteArrayWrapper, value: String) {
        val buf1 = value.encodeToByteArray()
        ByteArrayWrapperExt.setSize(buffer, 4 + buf1.size)
        ByteArrayHelper.writeInt4(ByteArrayWrapperExt.getBuf(buffer), 0, ETripleComponentTypeExt.STRING)
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), 4)
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun sparqlToByteArray(buffer: ByteArrayWrapper, value: String?) {
        if (value == null || value.isEmpty() || value.lowercase() == "undef") {
            undefToByteArray(buffer)
            return
        }
        if (value.lowercase() == "error") {
            errorToByteArray(buffer)
            return
        }
        if (value.lowercase() == "true") {
            booleanToByteArray(buffer, true)
            return
        }
        if (value.lowercase() == "false") {
            booleanToByteArray(buffer, false)
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
        if (!value.contains('.')) {
            try {
                val i = BigInteger.parseString(value, 10)
                integerToByteArray(buffer, i)
                return
            } catch (e: Exception) {
                // e.printStackTrace() this is handled correctly
            }
        }
        if (!value.contains("e") && !value.contains("E")) {
            try {
                val d = BigDecimal.parseString(value, 10)
                decimalToByteArray(buffer, d)
                return
            } catch (e: Exception) {
                // e.printStackTrace() this is handled correctly
            }
        }
        try {
            val d = value.toDouble()
            doubleToByteArray(buffer, d)
            return
        } catch (e: Exception) {
            // e.printStackTrace() this is handled correctly
        }
        if (!value.endsWith("" + value[0])) {
            val typeIdx = value.lastIndexOf("" + value[0] + "^^<")
            val langIdx = value.lastIndexOf("" + value[0] + "@")
            if (value.endsWith(">") && typeIdx > 0) {
                typedToByteArray(buffer, removeQuotesFromString(value.substring(0, typeIdx + 1)), value.substring(typeIdx + 4, value.length - 1))
                return
            } else {
                SanityCheck.check({ langIdx > 0 }, { "$langIdx :: $value" })
                langToByteArray(buffer, removeQuotesFromString(value.substring(0, langIdx + 1)), value.substring(langIdx + 2, value.length))
                return
            }
        }
        stringToByteArray(buffer, removeQuotesFromString(value))
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun removeQuotesFromString(s: String): String {
        var c = s[0]
        var cntLeft = 1
        var cntRight = 0
        if (c != '\'' && c != '"' || c != s[s.length - 1]) {
            throw Exception("invalid quoted string >$s<")
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
    public inline fun valueDefinitionToByteArray(buffer: ByteArrayWrapper, value: ValueDefinition) {
        sparqlToByteArray(buffer, value.valueToString())
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToType(buffer: ByteArrayWrapper): ETripleComponentType {
        val res = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), 0)
        SanityCheck.check({ res >= 0 }, { "$res" })
        SanityCheck.check({ res < ETripleComponentTypeExt.values_size }, { "$res" })
        return res
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToXMLElement(buffer: ByteArrayWrapper): XMLElement {
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
            else -> throw Exception("unreachable $type")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToSparql(buffer: ByteArrayWrapper): String {
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
            else -> throw Exception("unreachable $type")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    public inline fun byteArrayToValueDefinition(buffer: ByteArrayWrapper): ValueDefinition {
        val type = byteArrayToType(buffer)
        return when (type) {
            ETripleComponentTypeExt.UNDEF -> DictionaryExt.undefValue2
            ETripleComponentTypeExt.ERROR -> DictionaryExt.errorValue2
            ETripleComponentTypeExt.BLANK_NODE -> ValueBnode("" + byteArrayToBnode_I(buffer))
            ETripleComponentTypeExt.BOOLEAN -> {
                if (byteArrayToBoolean(buffer)) {
                    DictionaryExt.booleanTrueValue2
                } else {
                    DictionaryExt.booleanFalseValue2
                }
            }
            ETripleComponentTypeExt.DOUBLE -> ValueDouble(byteArrayToDouble_I(buffer))
            ETripleComponentTypeExt.FLOAT -> ValueFloat(byteArrayToFloat_I(buffer))
            ETripleComponentTypeExt.INTEGER -> ValueInteger(byteArrayToInteger_I(buffer))
            ETripleComponentTypeExt.DECIMAL -> ValueDecimal(byteArrayToDecimal_I(buffer))
            ETripleComponentTypeExt.IRI -> ValueIri(byteArrayToIri(buffer))
            ETripleComponentTypeExt.STRING -> ValueSimpleLiteral("\"", byteArrayToString(buffer))
            ETripleComponentTypeExt.STRING_LANG -> ValueLanguageTaggedLiteral("\"", byteArrayToLang_Content(buffer), byteArrayToLang_Lang(buffer))
            ETripleComponentTypeExt.STRING_TYPED -> ValueTypedLiteral("\"", byteArrayToTyped_Content(buffer), byteArrayToTyped_Type(buffer))
            ETripleComponentTypeExt.DATE_TIME -> ValueDateTime(byteArrayToSparql(buffer))
            else -> throw Exception("unreachable $type")
        }
    }

    public inline fun byteArrayToCallback(
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
        val type = ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(buffer), 0)
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
            else -> throw Exception("unreachable $type")
        }
    }

    @Suppress("NOTHING_TO_INLINE")
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
                if (ByteArrayWrapperExt.getSize(a) == 8 && ByteArrayWrapperExt.getSize(b) == 8) {
                    return ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(a), 4) - ByteArrayHelper.readInt4(ByteArrayWrapperExt.getBuf(b), 4)
                } else {
                    return a.compareTo(b)
                }
            } else if (typeA == ETripleComponentTypeExt.BOOLEAN) {
                return ByteArrayWrapperExt.getBuf(a)[4] - ByteArrayWrapperExt.getBuf(b)[4]
            } else if (typeA == ETripleComponentTypeExt.DATE_TIME) {
                return a.compareTo(b)
            } else if (typeA == ETripleComponentTypeExt.DECIMAL) {
                val av = byteArrayToDecimal_I(a)
                val bv = byteArrayToDecimal_I(b)
                return av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.DOUBLE) {
                val av = byteArrayToDouble_I(a)
                val bv = byteArrayToDouble_I(b)
                return av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.FLOAT) {
                val av = byteArrayToFloat_I(a)
                val bv = byteArrayToFloat_I(b)
                return av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.INTEGER) {
                val av = byteArrayToInteger_I(a)
                val bv = byteArrayToInteger_I(b)
                return av.compareTo(bv)
            } else if (typeA == ETripleComponentTypeExt.STRING_LANG || typeA == ETripleComponentTypeExt.STRING_TYPED || typeA == ETripleComponentTypeExt.IRI || typeA == ETripleComponentTypeExt.STRING) {
                val lenA = ByteArrayWrapperExt.getSize(a)
                val lenB = ByteArrayWrapperExt.getSize(b)
                var i = 4
                var res = 0
                while (i < lenA && i < lenB && res == 0) {
                    res = ByteArrayWrapperExt.getBuf(a)[i] - ByteArrayWrapperExt.getBuf(b)[i]
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
