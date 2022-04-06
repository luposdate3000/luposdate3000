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
package lupos.shared
import lupos.shared.myPrintStackTrace

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

public object DictionaryHelperLarge {
    public fun dateTimeToByteArray(buffer: ByteArrayWrapper, str: String) {
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
        var done = false
        if (timezoneHours == -99 && timezoneMinutes == -99) {
            var shortEncoding = true
            var secondsBeforeDot = 0L
            var secondsAfterDot = 0L
            var digitsAfterDot = 0
            var si = 0
            while (si < seconds.length) {
                val s = seconds[si]
                si++
                when (s) {
                    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                        secondsBeforeDot = secondsBeforeDot * 10 + (s - '0')
                        if (secondsBeforeDot >= 60) {
                            shortEncoding = false
                            break
                        }
                    }
                    '.' -> {
                        while (si < seconds.length) {
                            val s2 = seconds[si]
                            si++
                            when (s2) {
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                    digitsAfterDot++
                                    secondsAfterDot = secondsAfterDot * 10 + (s2 - '0')
                                    if (secondsAfterDot >= 1000) {
                                        shortEncoding = false
                                        break
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (shortEncoding) {
                var componentMilliseconds = secondsBeforeDot * 1000L
                when (digitsAfterDot) {
                    0 -> {
                    }
                    1 -> {
                        componentMilliseconds += secondsAfterDot * 100
                    }
                    2 -> {
                        componentMilliseconds += secondsAfterDot * 10
                    }
                    3 -> {
                        componentMilliseconds += secondsAfterDot
                    }
                    else -> {
                        shortEncoding = false
                    }
                }
                if (shortEncoding) {
                    componentMilliseconds += (minutes * 1000L * 60L)
                    componentMilliseconds += (hours * 1000L * 60L * 60L)
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:214"/*SOURCE_FILE_END*/ }, { componentMilliseconds >= 0 })
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:215"/*SOURCE_FILE_END*/ }, { componentMilliseconds < (1L shl 27) })
                    if (day < 32 && month < 16) {
                        val componentDay = day.toLong() shl 27
                        SanityCheck.check(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:219"/*SOURCE_FILE_END*/ },
                            { componentDay >= (1L shl 27) },
                            { "${(1L shl 27).toString(16)} - ${componentDay.toString(16)} - ${(1L shl 32).toString(16)} ... $day ... $str" }
                        )
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:223"/*SOURCE_FILE_END*/ }, { componentDay < (1L shl 32) })
                        val componentMonth = month.toLong() shl 32
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:225"/*SOURCE_FILE_END*/ }, { componentMonth >= (1L shl 32) })
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:226"/*SOURCE_FILE_END*/ }, { componentMonth < (1L shl 36) })
                        var componentYearSign = 1L shl 36
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:228"/*SOURCE_FILE_END*/ }, { componentYearSign >= (1L shl 36) })
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:229"/*SOURCE_FILE_END*/ }, { componentYearSign < (1L shl 37) })
                        var componentYear = 0L
                        var si2 = 0
                        while (si2 < year.length) {
                            val s = year[si2]
                            si2++
                            when (s) {
                                '-' -> {
                                    componentYearSign = 0L
                                }
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                                    componentYear = componentYear * 10 + (s - '0')
                                }
                            }
                        }
                        if (componentYear < (1L shl 11)) {
                            componentYear = componentYear shl 37
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:246"/*SOURCE_FILE_END*/ }, { componentYear >= (1L shl 37) })
                            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:247"/*SOURCE_FILE_END*/ }, { componentYear < (1L shl 48) })
                            val componentAll = componentMilliseconds or componentDay or componentMonth or componentYearSign or componentYear
                            ByteArrayWrapperExt.setSize(buffer, DictionaryHelper.headerSize() + 6, false)
                            DictionaryHelper.headerEncode(buffer, ETripleComponentTypeExt.DATE_TIME, 0x80)
                            ByteArrayWrapperExt.writeLong6(buffer, DictionaryHelper.headerSize(), componentAll)
                            done = true
                        }
                    }
                }
            }
        }

/*
 * 48 bits = 6 bytes total
 *
 * 27 bits milliseconds on day   (max 86400000)
 *  1 bit year-sign
 *  5 bits day in month
 *  4 bits month in year
 * 11 seconds year
 * no timezone
 */
        if (!done) {
            dateTimeToByteArray(buffer, DictionaryHelper.helper_intFromString(year), month, day, hours, minutes, DictionaryHelper.helper_decimalFromString(seconds), timezoneHours, timezoneMinutes, hasTimeZone)
        }
    }

    public fun dateTimeToByteArray(buffer: ByteArrayWrapper, year: BigInteger, month: Int, day: Int, hours: Int, minutes: Int, seconds: BigDecimal, timezoneHours: Int, timezoneMinutes: Int, hasTimeZone: Boolean) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:275"/*SOURCE_FILE_END*/ }, { month >= 0 }, { "dateTimeToByteArray.month : $month" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:276"/*SOURCE_FILE_END*/ }, { month <= 99 }, { "dateTimeToByteArray.month : $month" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:277"/*SOURCE_FILE_END*/ }, { day >= 0 }, { "dateTimeToByteArray.day : $day" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:278"/*SOURCE_FILE_END*/ }, { day <= 99 }, { "dateTimeToByteArray.day : $day" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:279"/*SOURCE_FILE_END*/ }, { hours >= 0 }, { "dateTimeToByteArray.hours : $hours" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:280"/*SOURCE_FILE_END*/ }, { hours <= 24 }, { "dateTimeToByteArray.hours : $hours" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:281"/*SOURCE_FILE_END*/ }, { minutes >= 0 }, { "dateTimeToByteArray.minutes : $minutes" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:282"/*SOURCE_FILE_END*/ }, { minutes <= 99 }, { "dateTimeToByteArray.minutes : $minutes" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:283"/*SOURCE_FILE_END*/ }, { !hasTimeZone || timezoneHours >= -24 }, { "dateTimeToByteArray.timezoneHours : $timezoneHours" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:284"/*SOURCE_FILE_END*/ }, { !hasTimeZone || timezoneHours <= 24 }, { "dateTimeToByteArray.timezoneHours : $timezoneHours" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:285"/*SOURCE_FILE_END*/ }, { !hasTimeZone || timezoneMinutes >= 0 }, { "dateTimeToByteArray.timezoneMinutes : $timezoneMinutes" })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:286"/*SOURCE_FILE_END*/ }, { !hasTimeZone || timezoneMinutes <= 99 }, { "dateTimeToByteArray.timezoneMinutes : $timezoneMinutes" })
        val buf1 = DictionaryHelper.helper_intToByteArray(year)
        val buf2 = DictionaryHelper.helper_decimalToByteArray(seconds)
        val l1 = buf1.size
        val l2 = buf2.size
        ByteArrayWrapperExt.setSize(buffer, DictionaryHelper.headerSize() + 28 + l1 + l2, false)
        var off = 0
        DictionaryHelper.headerEncode(buffer, ETripleComponentTypeExt.DATE_TIME, 0)
        off += DictionaryHelper.headerSize()
        ByteArrayWrapperExt.writeInt4(buffer, off, l1)
        off += 4
        ByteArrayWrapperExt.writeInt4(buffer, off, month)
        off += 4
        ByteArrayWrapperExt.writeInt4(buffer, off, day)
        off += 4
        ByteArrayWrapperExt.writeInt4(buffer, off, hours)
        off += 4
        ByteArrayWrapperExt.writeInt4(buffer, off, minutes)
        off += 4
        if (hasTimeZone) {
            ByteArrayWrapperExt.writeInt4(buffer, off, timezoneHours)
            off += 4
            ByteArrayWrapperExt.writeInt4(buffer, off, timezoneMinutes)
            off += 4
        } else {
            ByteArrayWrapperExt.writeInt4(buffer, off, -99)
            off += 4
            ByteArrayWrapperExt.writeInt4(buffer, off, -99)
            off += 4
        }
        buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l1
        buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
        off += l2
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:320"/*SOURCE_FILE_END*/ }, { off == ByteArrayWrapperExt.getSize(buffer) })
    }

    public fun byteArrayToDateTimeAsTyped_Content(buffer: ByteArrayWrapper): String {
        if (DictionaryHelper.headerDecodeFlag(buffer) == 0x80) {
            val componentAll = ByteArrayWrapperExt.readLong6(buffer, DictionaryHelper.headerSize())
            val componentMilliseconds = (componentAll and 0x7FFFFFF)
            val milliseconds = componentMilliseconds % (60 * 1000)
            val minutes = (componentMilliseconds / (1000 * 60)) % 60
            val hours = componentMilliseconds / (1000 * 60 * 60)
            val day = ((componentAll shr 27) and 0x1F)
            val month = ((componentAll shr 32) and 0xF)
            var year = ((componentAll shr 37) and 0x7FF)
            if ((componentAll and (1L shl 36)) != (1L shl 36)) {
                year = -year
            }
            val secondsString = if ((milliseconds % 1000) != 0L) {
                (milliseconds / 1000).toString().padStart(2, '0') + "." + (milliseconds % 1000).toString().padStart(3, '0')
            } else {
                (milliseconds / 1000).toString().padStart(2, '0')
            }
            val res = "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:$secondsString"
            return res
        } else {
            var off = 0
            off += DictionaryHelper.headerSize()
            val l1 = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val month = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val day = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val hours = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val minutes = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val timezoneHours = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val timezoneMinutes = ByteArrayWrapperExt.readInt4(buffer, off)
            off += 4
            val buf1 = ByteArray(l1)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf1, 0, off, off + l1)
            off += l1
            val l2 = ByteArrayWrapperExt.getSize(buffer) - l1 - DictionaryHelper.headerSize() - 28
            val buf2 = ByteArray(l2)
            ByteArrayWrapperExt.getBuf(buffer).copyInto(buf2, 0, off, off + l2)
            buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), off)
            off += l2
            SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:368"/*SOURCE_FILE_END*/ }, { off == ByteArrayWrapperExt.getSize(buffer) })
            val year = DictionaryHelper.helper_intFromByteArray(buf1)
            val seconds = DictionaryHelper.helper_decimalFromByteArray(buf2)
            val secondsString2 = seconds.toStringExpanded().split(".")
            var secondsString = secondsString2[0].padStart(2, '0')
            if (secondsString2.size > 1) {
                var tmp = secondsString2[1]
                while (tmp.endsWith('0')) {
                    tmp = tmp.substring(0, tmp.length - 1)
                }
                if (tmp.isNotEmpty()) {
                    secondsString += ".$tmp"
                }
            }
            return if (timezoneHours == -99 && timezoneMinutes == -99) {
                "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:$secondsString"
            } else if (timezoneHours == 0 && timezoneMinutes == 0) {
                "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}Z"
            } else {
                var timezoneHoursLocal = timezoneHours.toString()
                timezoneHoursLocal = if (timezoneHoursLocal[0] == '-' || timezoneHoursLocal[0] == '+') {
                    "" + timezoneHoursLocal[0] + timezoneHoursLocal.substring(1).padStart(2, '0')
                } else {
                    "+" + timezoneHoursLocal.padStart(2, '0')
                }
                "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}$timezoneHoursLocal:${timezoneMinutes.toString().padStart(2, '0')}"
            }
        }
    }

    public fun typedToByteArray(buffer: ByteArrayWrapper, content: String, type: String) {
        try {
            when (type) {
                "http://www.w3.org/2001/XMLSchema#integer" -> DictionaryHelper.integerToByteArray(buffer, content)
                "http://www.w3.org/2001/XMLSchema#decimal" -> DictionaryHelper.decimalToByteArray(buffer, content)
                "http://www.w3.org/2001/XMLSchema#double" -> DictionaryHelper.doubleToByteArray(buffer, content.toDouble())
                "http://www.w3.org/2001/XMLSchema#float" -> DictionaryHelper.floatToByteArray(buffer, content.toDouble())
                "http://www.w3.org/2001/XMLSchema#boolean" -> DictionaryHelper.booleanToByteArray(buffer, content.lowercase() == "true")
                "http://www.w3.org/2001/XMLSchema#dateTime" -> DictionaryHelper.dateTimeToByteArray(buffer, content)
                "http://www.w3.org/2001/XMLSchema#string" -> DictionaryHelper.stringToByteArray(buffer, content)
                else -> {
                    val buf1 = type.encodeToByteArray()
                    val buf2 = content.encodeToByteArray()
                    ByteArrayWrapperExt.setSize(buffer, DictionaryHelper.headerSize() + 4 + buf1.size + buf2.size, false)
                    DictionaryHelper.headerEncode(buffer, ETripleComponentTypeExt.STRING_TYPED, 0)
                    ByteArrayWrapperExt.writeInt4(buffer, DictionaryHelper.headerSize() + buf1.size + buf2.size, buf1.size)
                    buf1.copyInto(ByteArrayWrapperExt.getBuf(buffer), DictionaryHelper.headerSize())
                    buf2.copyInto(ByteArrayWrapperExt.getBuf(buffer), DictionaryHelper.headerSize() + buf1.size)
                }
            }
        } catch (e: Exception) {
            e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:419"/*SOURCE_FILE_END*/ )()
            DictionaryHelper.stringToByteArray(buffer, content)
        }
    }

    public fun sparqlToByteArray(buffer: ByteArrayWrapper, value: String?) {
        if (value == null || value.isEmpty() || value.lowercase() == "undef") {
            DictionaryHelper.undefToByteArray(buffer)
            return
        }
        if (value.lowercase() == "error") {
            DictionaryHelper.errorToByteArray(buffer)
            return
        }
        if (value.lowercase() == "true") {
            DictionaryHelper.booleanToByteArray(buffer, true)
            return
        }
        if (value.lowercase() == "false") {
            DictionaryHelper.booleanToByteArray(buffer, false)
            return
        }
        if (value.startsWith("_:")) {
            DictionaryHelper.bnodeToByteArray(buffer, value.substring(2, value.length))
            return
        }
        if (value.startsWith("<") && value.endsWith(">")) {
            DictionaryHelper.iriToByteArray(buffer, value.substring(1, value.length - 1))
            return
        }
        if (!value.contains('.')) {
            try {
                val i = DictionaryHelper.helper_intFromString(value)
                DictionaryHelper.integerToByteArray(buffer, i)
                return
            } catch (e: Exception) {
                // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:455"/*SOURCE_FILE_END*/ )() this is handled correctly
            }
        }
        if (!value.contains("e") && !value.contains("E")) {
            try {
                val d = DictionaryHelper.helper_decimalFromString(value)
                DictionaryHelper.decimalToByteArray(buffer, d)
                return
            } catch (e: Exception) {
                // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:464"/*SOURCE_FILE_END*/ )() this is handled correctly
            }
        }
        try {
            val d = value.toDouble()
            DictionaryHelper.doubleToByteArray(buffer, d)
            return
        } catch (e: Exception) {
            // e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:472"/*SOURCE_FILE_END*/ )() this is handled correctly
        }
        if (!value.endsWith("" + value[0])) {
            val typeIdx = value.lastIndexOf("" + value[0] + "^^<")
            val langIdx = value.lastIndexOf("" + value[0] + "@")
            if (value.endsWith(">") && typeIdx > 0) {
                typedToByteArray(buffer, DictionaryHelper.removeQuotesFromString(value.substring(0, typeIdx + 1)), value.substring(typeIdx + 4, value.length - 1))
                return
            } else {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/DictionaryHelperLarge.kt:481"/*SOURCE_FILE_END*/ }, { langIdx > 0 }, { "$langIdx :: $value" })
                DictionaryHelper.langToByteArray(buffer, DictionaryHelper.removeQuotesFromString(value.substring(0, langIdx + 1)), value.substring(langIdx + 2, value.length))
                return
            }
        }
        DictionaryHelper.stringToByteArray(buffer, DictionaryHelper.removeQuotesFromString(value))
    }
}
