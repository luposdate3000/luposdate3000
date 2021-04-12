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

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import com.ionspin.kotlin.bignum.integer.BigInteger
import com.ionspin.kotlin.bignum.integer.toBigInteger
import lupos.s00misc.CanNotCastBNodeToBooleanException
import lupos.s00misc.CanNotCastBNodeToDecimalException
import lupos.s00misc.CanNotCastBNodeToDoubleException
import lupos.s00misc.CanNotCastBNodeToIntException
import lupos.s00misc.CanNotCastBooleanToDecimalException
import lupos.s00misc.CanNotCastBooleanToDoubleException
import lupos.s00misc.CanNotCastBooleanToIntException
import lupos.s00misc.CanNotCastDateTimeToBooleanException
import lupos.s00misc.CanNotCastDateTimeToDecimalException
import lupos.s00misc.CanNotCastDateTimeToDoubleException
import lupos.s00misc.CanNotCastDateTimeToIntException
import lupos.s00misc.CanNotCastErrorToBooleanException
import lupos.s00misc.CanNotCastErrorToDecimalException
import lupos.s00misc.CanNotCastErrorToDoubleException
import lupos.s00misc.CanNotCastErrorToIntException
import lupos.s00misc.CanNotCastIriToBooleanException
import lupos.s00misc.CanNotCastIriToDecimalException
import lupos.s00misc.CanNotCastIriToDoubleException
import lupos.s00misc.CanNotCastIriToIntException
import lupos.s00misc.CanNotCastLiteralToDecimalException
import lupos.s00misc.CanNotCastLiteralToDoubleException
import lupos.s00misc.CanNotCastLiteralToIntException
import lupos.s00misc.CanNotCastUndefToBooleanException
import lupos.s00misc.CanNotCastUndefToDecimalException
import lupos.s00misc.CanNotCastUndefToDoubleException
import lupos.s00misc.CanNotCastUndefToIntException
import lupos.s00misc.DateHelper
import lupos.s00misc.IncompatibleTypesDuringCompareException
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import kotlin.jvm.JvmField

public sealed class ValueDefinition : Comparable<ValueDefinition> {
    public /*suspend*/ abstract fun toXMLElement(partial: Boolean): XMLElement
    public abstract fun valueToString(): String?
    public abstract fun toDouble(): Double
    public abstract fun toDecimal(): BigDecimal
    public abstract fun toInt(): BigInteger
    public abstract fun toBoolean(): Boolean

    public companion object {
        public operator fun invoke(tmp: String?): ValueDefinition {
            if (tmp == null || tmp.isEmpty()) {
                return ValueUndef()
            }
            if (tmp.startsWith("_:")) {
                return ValueBnode(tmp.substring(2, tmp.length))
            }
            if (tmp.startsWith("<") && tmp.endsWith(">")) {
                return ValueIri(tmp.substring(1, tmp.length - 1))
            }
            try {
                return ValueInteger(BigInteger.parseString(tmp, 10))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            if (!tmp.contains("e") && !tmp.contains("E")) {
                try {
                    return ValueDecimal(BigDecimal.parseString(tmp, 10))
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
            try {
                return ValueDouble(tmp.toDouble())
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            if (!tmp.endsWith("" + tmp[0])) {
                val typeIdx = tmp.lastIndexOf("" + tmp[0] + "^^<")
                val langIdx = tmp.lastIndexOf("" + tmp[0] + "@")
                return if (tmp.endsWith(">") && typeIdx > 0) {
                    ValueTypedLiteral("" + tmp[0], tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                } else {
                    SanityCheck.check { langIdx > 0 }
                    ValueLanguageTaggedLiteral("" + tmp[0], tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                }
            }
            return ValueSimpleLiteral("" + tmp[0], tmp.substring(1, tmp.length - 1))
        }
    }

    public fun toSparql(): String {
        return valueToString() ?: return "UNDEF"
    }

    public override operator fun compareTo(other: ValueDefinition): Int = throw IncompatibleTypesDuringCompareException()
}

public class ValueBnode(@JvmField public var value: String) : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueBnode").addAttribute("value", "" + value)
    public override fun valueToString(): String = "_:$value"
    public override fun equals(other: Any?): Boolean {
        if (other is ValueBnode) {
            return value == other.value
        }
        return false
    }

    public override fun toDouble(): Nothing = throw CanNotCastBNodeToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastBNodeToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastBNodeToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastBNodeToBooleanException()
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBnode) {
            return 1
        }
        return value.compareTo(other.value)
    }
}

public class ValueBoolean(@JvmField public var value: Boolean, x: Boolean) : ValueDefinition() {
    public companion object {
        private val localTrue = ValueBoolean(true, true)
        private val localFalse = ValueBoolean(false, true)
        public operator fun invoke(value: Boolean): ValueBoolean {
            return if (value) {
                localTrue
            } else {
                localFalse
            }
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueBoolean").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    public override fun equals(other: Any?): Boolean {
        if (other is ValueBoolean) {
            return value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override fun toDouble(): Nothing = throw CanNotCastBooleanToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastBooleanToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastBooleanToIntException()
    public override fun toBoolean(): Boolean = value
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueBoolean) {
            throw IncompatibleTypesDuringCompareException()
        } else if (value == other.value) {
            return 0
        } else if (value && !other.value) {
            return 1
        }
        return -1
    }

    public override fun hashCode(): Int = value.hashCode()
}

public sealed class ValueNumeric : ValueDefinition()
public class ValueUndef : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueUndef")
    public override fun valueToString(): String? = null
    public override fun equals(other: Any?): Boolean {
        if (other is ValueUndef) {
            return true
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Nothing = throw CanNotCastUndefToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastUndefToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastUndefToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastUndefToBooleanException()
    public override fun hashCode(): Int = 0
}

public class ValueError : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueError")
    public override fun valueToString(): String? = null
    public override fun equals(other: Any?): Boolean = throw IncompatibleTypesDuringCompareException()
    public override fun toDouble(): Nothing = throw CanNotCastErrorToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastErrorToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastErrorToIntException()
    public override fun toBoolean(): Nothing = throw CanNotCastErrorToBooleanException()
    public override fun hashCode(): Int = 0
}

public sealed class ValueStringBase(@JvmField public val delimiter: String, @JvmField public val content: String) : ValueDefinition() {
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        }
        return valueToString()!!.compareTo(other.valueToString()!!)
    }

    public override fun toBoolean(): Boolean = content.isNotEmpty()
    public override fun toDouble(): Nothing = throw CanNotCastLiteralToDoubleException()
    public override fun toDecimal(): Nothing = throw CanNotCastLiteralToDecimalException()
    public override fun toInt(): Nothing = throw CanNotCastLiteralToIntException()
}

public class ValueLanguageTaggedLiteral(delimiter: String, content: String, @JvmField public val language: String) : ValueStringBase(delimiter, content) {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueLanguageTaggedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("language", "" + language)
    public override fun valueToString(): String = "$delimiter$content$delimiter@$language"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueLanguageTaggedLiteral) {
            language == other.language && content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode() + language.hashCode()
}

public class ValueSimpleLiteral(delimiter: String, content: String) : ValueStringBase(delimiter, content) {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueSimpleLiteral").addAttribute("delimiter", delimiter).addAttribute("content", content)
    public override fun valueToString(): String = delimiter + content + delimiter
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueSimpleLiteral) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode()
}

public class ValueTypedLiteral(delimiter: String, content: String, @JvmField public val type_iri: String, b: Boolean) : ValueStringBase(delimiter, content) {
    public companion object {
        public operator fun invoke(delimiter: String, content: String, type_iri: String): ValueDefinition {
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> {
                    return ValueBoolean(content.toBoolean())
                }
                "http://www.w3.org/2001/XMLSchema#integer" -> {
                    return ValueInteger(BigInteger.parseString(content, 10))
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#float" -> {
                    return ValueFloat(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
                    return ValueDecimal(BigDecimal.parseString(content, 10))
                }
                "http://www.w3.org/2001/XMLSchema#dateTime" -> {
                    return ValueDateTime("$delimiter$content$delimiter^^<$type_iri>")
                }
                else -> {
                    return ValueTypedLiteral(delimiter, content, type_iri, true)
                }
            }
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueTypedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)
    public override fun valueToString(): String = "$delimiter$content$delimiter^^<$type_iri>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueTypedLiteral && type_iri == other.type_iri) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun hashCode(): Int = delimiter.hashCode() + content.hashCode() + type_iri.hashCode()
}

public class ValueDecimal(@JvmField public var value: BigDecimal) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDecimal").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"" + value.toPlainString() + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueDecimal) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Double = value.doubleValue()
    public override fun toDecimal(): BigDecimal = value
    public override fun toInt(): BigInteger = value.toBigInteger()
    public override fun toBoolean(): Boolean = value != BigDecimal.parseString("0.0", 10)
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(BigDecimal.fromBigInteger(other.value))
        } else if (other is ValueDecimal) {
            value.compareTo(other.value)
        } else if (other is ValueDouble) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

public class ValueDouble(@JvmField public var value: Double) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDouble").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#double>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueDouble) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDouble(): Double = value
    public override fun toDecimal(): BigDecimal = value.toBigDecimal()
    public override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger()
    public override fun toBoolean(): Boolean = value > 0 || value < 0
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDouble) {
            value.compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

public class ValueFloat(@JvmField public var value: Double) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueFloat").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#float>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueFloat) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDecimal(): BigDecimal = value.toBigDecimal()
    public override fun toDouble(): Double = value
    public override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger()
    public override fun toBoolean(): Boolean = value > 0 || value < 0
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.doubleValue())
        } else if (other is ValueDouble) {
            value.compareTo(other.value)
        } else if (other is ValueFloat) {
            value.compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

public class ValueInteger(@JvmField public var value: BigInteger) : ValueNumeric() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueInteger").addAttribute("value", "" + value)
    public override fun valueToString(): String = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueInteger) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    public override fun toDecimal(): BigDecimal = BigDecimal.fromBigInteger(value)
    public override fun toDouble(): Double = value.doubleValue()
    public override fun toInt(): BigInteger = value
    public override fun toBoolean(): Boolean = value != BigInteger.parseString("0", 10)
    public override fun hashCode(): Int = value.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value)
        } else if (other is ValueDecimal) {
            BigDecimal.fromBigInteger(value).compareTo(other.value)
        } else if (other is ValueDouble) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.doubleValue().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

public class ValueIri(@JvmField public var iri: String) : ValueDefinition() {
    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueIri").addAttribute("value", "" + iri)
    public override fun valueToString(): String = "<$iri>"
    public override fun equals(other: Any?): Boolean {
        return if (other is ValueIri) {
            iri == other.iri
        } else {
            false
        }
    }

    public override fun toDouble(): Double = throw CanNotCastIriToDoubleException()
    public override fun toDecimal(): BigDecimal = throw CanNotCastIriToDecimalException()
    public override fun toInt(): BigInteger = throw CanNotCastIriToIntException()
    public override fun toBoolean(): Boolean = throw CanNotCastIriToBooleanException()
    public override fun hashCode(): Int = iri.hashCode()
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueIri) {
            return 1
        }
        return iri.compareTo(other.iri)
    }
}

public class ValueDateTime : ValueDefinition {
    @JvmField
    public val year: BigInteger

    @JvmField
    public val month: Int

    @JvmField
    public val day: Int

    @JvmField
    public val hours: Int

    @JvmField
    public val minutes: Int

    @JvmField
    public val seconds: BigDecimal

    @JvmField
    public val timezoneHours: Int

    @JvmField
    public val timezoneMinutes: Int
    public override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueDateTime) {
            throw IncompatibleTypesDuringCompareException()
        } else if (year != other.year) {
            return year.compareTo(other.year)
        } else if (month != other.month) {
            return month.compareTo(other.month)
        } else if (day != other.day) {
            return day.compareTo(other.day)
        } else if (hours != other.hours) {
            return hours.compareTo(other.hours)
        } else if (minutes != other.minutes) {
            return minutes.compareTo(other.minutes)
        } else if (seconds != other.seconds) {
            return seconds.compareTo(other.seconds)
        } else if (timezoneHours != other.timezoneHours) {
            return timezoneHours.compareTo(other.timezoneHours)
        } else if (timezoneMinutes != other.timezoneMinutes) {
            return timezoneMinutes.compareTo(other.timezoneMinutes)
        }
        return 0
    }

    public constructor() : super() {
        val time = DateHelper()
        year = BigInteger.parseString(time.year().toString(), 10)
        month = time.month()
        day = time.day()
        hours = time.hours()
        minutes = time.minutes()
        seconds = BigDecimal.parseString(time.seconds().toString(), 10)
        timezoneHours = 0
        timezoneMinutes = 0
    }

    public constructor(str2: String) : super() {
        val str = str2.substring(0, str2.indexOf(str2[0], 1) + 1)
        var idx = 0
        var idx2 = str.indexOf('-', 2)
        if (idx2 < idx) {
            idx2 = str.length - 1
        }
        if (idx2 > idx) {
            year = BigInteger.parseString(str.substring(idx + 1, idx2), 10)
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
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxa), 10)
                                timezoneHours = 0
                                timezoneMinutes = 0
                            } else if (idxb > idx) {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxb), 10)
                                idx = idxb
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx + 1, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length - 1).toInt()
                                } else {
                                    timezoneHours = -1
                                    timezoneMinutes = -1
                                }
                            } else if (idxc > idx) {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, idxc), 10)
                                idx = idxc
                                idx2 = str.indexOf(':', idx + 1)
                                if (idx2 > idx) {
                                    timezoneHours = str.substring(idx + 1, idx2).toInt()
                                    timezoneMinutes = str.substring(idx2 + 1, str.length - 1).toInt()
                                } else {
                                    timezoneHours = -1
                                    timezoneMinutes = -1
                                }
                            } else {
                                seconds = BigDecimal.parseString(str.substring(idx + 1, str.length - 1), 10)
                                timezoneHours = -1
                                timezoneMinutes = -1
                            }
                        } else {
                            minutes = 0
                            seconds = BigDecimal.parseString("0.0", 10)
                            timezoneHours = -1
                            timezoneMinutes = -1
                        }
                    } else {
                        hours = 0
                        minutes = 0
                        seconds = BigDecimal.parseString("0.0", 10)
                        timezoneHours = -1
                        timezoneMinutes = -1
                    }
                } else {
                    day = 0
                    hours = 0
                    minutes = 0
                    seconds = BigDecimal.parseString("0.0", 10)
                    timezoneHours = -1
                    timezoneMinutes = -1
                }
            } else {
                month = 0
                day = 0
                hours = 0
                minutes = 0
                seconds = BigDecimal.parseString("0.0", 10)
                timezoneHours = -1
                timezoneMinutes = -1
            }
        } else {
            year = BigInteger.parseString("0", 10)
            month = 0
            day = 0
            hours = 0
            minutes = 0
            seconds = BigDecimal.parseString("0.0", 10)
            timezoneHours = -1
            timezoneMinutes = -1
        }
    }

    public fun getTZ(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "Z"
        }
        if (timezoneHours == -1 && timezoneMinutes == -1) {
            return ""
        }
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }

    public fun getTimeZone(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        return ""
    }

    public override fun valueToString(): String {
        val secondsString2 = seconds.toString().split(".")
        var secondsString = secondsString2[0].padStart(2, '0')
        if (secondsString2.size > 1 && secondsString2[1] != "0") {
            secondsString += "." + secondsString2[1]
        }
        return if (timezoneHours == -1 && timezoneMinutes == -1) {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:$secondsString-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
    }

    public /*suspend*/ override fun toXMLElement(partial: Boolean): XMLElement = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    public override fun equals(other: Any?): Boolean {
        if (other is ValueDateTime) {
            return valueToString() == other.valueToString()
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    public override fun hashCode(): Int = valueToString().hashCode()
    public override fun toDouble(): Double = throw CanNotCastDateTimeToDoubleException()
    public override fun toDecimal(): BigDecimal = throw CanNotCastDateTimeToDecimalException()
    public override fun toInt(): BigInteger = throw CanNotCastDateTimeToIntException()
    public override fun toBoolean(): Boolean = throw CanNotCastDateTimeToBooleanException()
}
