package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
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
import lupos.s00misc.MyBigDecimal
import lupos.s00misc.MyBigInteger
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement

sealed class ValueDefinition : Comparable<ValueDefinition> {
    abstract /*suspend*/ fun toXMLElement(): XMLElement
    abstract fun valueToString(): String?
    abstract fun toDouble(): Double
    abstract fun toDecimal(): MyBigDecimal
    abstract fun toInt(): MyBigInteger
    abstract fun toBoolean(): Boolean

    companion object {
        operator fun invoke(tmp: String?): ValueDefinition {
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
                return ValueInteger(MyBigInteger(tmp))
            } catch (e: Throwable) {
            }
            if (!tmp.contains("e") && !tmp.contains("E")) {
                try {
                    return ValueDecimal(MyBigDecimal(tmp))
                } catch (e: Throwable) {
                }
            }
            try {
                return ValueDouble(tmp.toDouble())
            } catch (e: Throwable) {
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

    fun toSparql(): String {
        return valueToString() ?: return "UNDEF"
    }

    override operator fun compareTo(other: ValueDefinition): Int = throw IncompatibleTypesDuringCompareException()
}

class ValueBnode(@JvmField var value: String) : ValueDefinition() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueBnode").addAttribute("value", "" + value)
    override fun valueToString() = "_:$value"
    override fun equals(other: Any?): Boolean {
        if (other is ValueBnode) {
            return value == other.value
        }
        return false
    }

    override fun toDouble() = throw CanNotCastBNodeToDoubleException()
    override fun toDecimal() = throw CanNotCastBNodeToDecimalException()
    override fun toInt() = throw CanNotCastBNodeToIntException()
    override fun toBoolean() = throw CanNotCastBNodeToBooleanException()
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBnode) {
            return 1
        }
        return value.compareTo(other.value)
    }
}

class ValueBoolean(@JvmField var value: Boolean, x: Boolean) : ValueDefinition() {
    companion object {
        private val localTrue = ValueBoolean(true, true)
        private val localFalse = ValueBoolean(false, true)
        operator fun invoke(value: Boolean): ValueBoolean {
            return if (value) {
                localTrue
            } else {
                localFalse
            }
        }
    }

    override /*suspend*/ fun toXMLElement() = XMLElement("ValueBoolean").addAttribute("value", "" + value)
    override fun valueToString() = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?): Boolean {
        if (other is ValueBoolean) {
            return value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    override fun toDouble() = throw CanNotCastBooleanToDoubleException()
    override fun toDecimal() = throw CanNotCastBooleanToDecimalException()
    override fun toInt() = throw CanNotCastBooleanToIntException()
    override fun toBoolean() = value
    override operator fun compareTo(other: ValueDefinition): Int {
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

    override fun hashCode() = value.hashCode()
}

sealed class ValueNumeric : ValueDefinition()
class ValueUndef : ValueDefinition() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueUndef")
    override fun valueToString(): String? = null
    override fun equals(other: Any?): Boolean {
        if (other is ValueUndef) {
            return true
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun toDouble() = throw CanNotCastUndefToDoubleException()
    override fun toDecimal() = throw CanNotCastUndefToDecimalException()
    override fun toInt() = throw CanNotCastUndefToIntException()
    override fun toBoolean() = throw CanNotCastUndefToBooleanException()
    override fun hashCode() = 0
}

class ValueError : ValueDefinition() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueError")
    override fun valueToString(): String? = null
    override fun equals(other: Any?): Boolean = throw IncompatibleTypesDuringCompareException()
    override fun toDouble() = throw CanNotCastErrorToDoubleException()
    override fun toDecimal() = throw CanNotCastErrorToDecimalException()
    override fun toInt() = throw CanNotCastErrorToIntException()
    override fun toBoolean() = throw CanNotCastErrorToBooleanException()
    override fun hashCode() = 0
}

sealed class ValueStringBase(@JvmField val delimiter: String, @JvmField val content: String) : ValueDefinition() {
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueBnode || other is ValueIri) {
            return -1
        } else if (other !is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        }
        return valueToString()!!.compareTo(other.valueToString()!!)
    }

    override fun toBoolean() = content.isNotEmpty()
    override fun toDouble() = throw CanNotCastLiteralToDoubleException()
    override fun toDecimal() = throw CanNotCastLiteralToDecimalException()
    override fun toInt() = throw CanNotCastLiteralToIntException()
}

class ValueLanguageTaggedLiteral(delimiter: String, content: String, val language: String) : ValueStringBase(delimiter, content) {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueLanguageTaggedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("language", "" + language)
    override fun valueToString() = "$delimiter$content$delimiter@$language"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueLanguageTaggedLiteral) {
            language == other.language && content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun hashCode() = delimiter.hashCode() + content.hashCode() + language.hashCode()
}

class ValueSimpleLiteral(delimiter: String, content: String) : ValueStringBase(delimiter, content) {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueSimpleLiteral").addAttribute("delimiter", delimiter).addAttribute("content", content)
    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean {
        return if (other is ValueSimpleLiteral) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun hashCode() = delimiter.hashCode() + content.hashCode()
}

class ValueTypedLiteral(delimiter: String, content: String, @JvmField val type_iri: String, b: Boolean) : ValueStringBase(delimiter, content) {
    companion object {
        operator fun invoke(delimiter: String, content: String, type_iri: String): ValueDefinition {
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> {
                    return ValueBoolean(content.toBoolean())
                }
                "http://www.w3.org/2001/XMLSchema#integer" -> {
                    return ValueInteger(MyBigInteger(content))
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#float" -> {
                    return ValueFloat(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
                    return ValueDecimal(MyBigDecimal(content))
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

    override /*suspend*/ fun toXMLElement() = XMLElement("ValueTypedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)
    override fun valueToString() = "$delimiter$content$delimiter^^<$type_iri>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueTypedLiteral && type_iri == other.type_iri) {
            content == other.content
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun hashCode() = delimiter.hashCode() + content.hashCode() + type_iri.hashCode()
}

class ValueDecimal(@JvmField var value: MyBigDecimal) : ValueNumeric() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueDecimal").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value.toPlainString() + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueDecimal) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun toDouble(): Double = value.toDouble()
    override fun toDecimal(): MyBigDecimal = value
    override fun toInt(): MyBigInteger = value.toMyBigInteger()
    override fun toBoolean() = value != MyBigDecimal("0.0")
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.toMyBigDecimal())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value)
        } else if (other is ValueDouble) {
            value.toDouble().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.toDouble().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

class ValueDouble(@JvmField var value: Double) : ValueNumeric() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueDouble").addAttribute("value", "" + value)
    override fun valueToString() = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#double>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueDouble) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun toDouble(): Double = value
    override fun toDecimal(): MyBigDecimal = MyBigDecimal(value)
    override fun toInt(): MyBigInteger = MyBigDecimal(value).toMyBigInteger()
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.toDouble())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.toDouble())
        } else if (other is ValueDouble) {
            value.compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

class ValueFloat(@JvmField var value: Double) : ValueNumeric() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueFloat").addAttribute("value", "" + value)
    override fun valueToString() = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#float>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueFloat) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun toDecimal(): MyBigDecimal = MyBigDecimal(value)
    override fun toDouble(): Double = value
    override fun toInt(): MyBigInteger = MyBigDecimal(value).toMyBigInteger()
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value.toDouble())
        } else if (other is ValueDecimal) {
            value.compareTo(other.value.toDouble())
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

class ValueInteger(@JvmField var value: MyBigInteger) : ValueNumeric() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueInteger").addAttribute("value", "" + value)
    override fun valueToString() = "\"$value\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueInteger) {
            value == other.value
        } else if (other is ValueBnode || other is ValueIri) {
            false
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }

    override fun toDecimal(): MyBigDecimal = value.toMyBigDecimal()
    override fun toDouble(): Double = value.toDouble()
    override fun toInt(): MyBigInteger = value
    override fun toBoolean() = value != MyBigInteger("0")
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        return if (other is ValueInteger) {
            value.compareTo(other.value)
        } else if (other is ValueDecimal) {
            value.toMyBigDecimal().compareTo(other.value)
        } else if (other is ValueDouble) {
            value.toDouble().compareTo(other.value)
        } else if (other is ValueFloat) {
            value.toDouble().compareTo(other.value)
        } else if (other is ValueBnode || other is ValueIri) {
            -1
        } else {
            throw IncompatibleTypesDuringCompareException()
        }
    }
}

class ValueIri(@JvmField var iri: String) : ValueDefinition() {
    override /*suspend*/ fun toXMLElement() = XMLElement("ValueIri").addAttribute("value", "" + iri)
    override fun valueToString() = "<$iri>"
    override fun equals(other: Any?): Boolean {
        return if (other is ValueIri) {
            iri == other.iri
        } else {
            false
        }
    }

    override fun toDouble(): Double = throw CanNotCastIriToDoubleException()
    override fun toDecimal(): MyBigDecimal = throw CanNotCastIriToDecimalException()
    override fun toInt(): MyBigInteger = throw CanNotCastIriToIntException()
    override fun toBoolean(): Boolean = throw CanNotCastIriToBooleanException()
    override fun hashCode() = iri.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueIri) {
            return 1
        }
        return iri.compareTo(other.iri)
    }
}

class ValueDateTime : ValueDefinition {
    @JvmField
    val year: MyBigInteger

    @JvmField
    val month: Int

    @JvmField
    val day: Int

    @JvmField
    val hours: Int

    @JvmField
    val minutes: Int

    @JvmField
    val seconds: MyBigDecimal

    @JvmField
    val timezoneHours: Int

    @JvmField
    val timezoneMinutes: Int
    override operator fun compareTo(other: ValueDefinition): Int {
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

    constructor() : super() {
        val time = DateHelper()
        year = MyBigInteger(time.year().toString())
        month = time.month()
        day = time.day()
        hours = time.hours()
        minutes = time.minutes()
        seconds = MyBigDecimal(time.seconds().toString())
        timezoneHours = 0
        timezoneMinutes = 0
    }

    constructor(str2: String) : super() {
        val str = str2.substring(0, str2.indexOf(str2[0], 1) + 1)
        var idx = 0
        var idx2 = str.indexOf('-', 2)
        if (idx2 < idx) {
            idx2 = str.length - 1
        }
        if (idx2 > idx) {
            year = MyBigInteger(str.substring(idx + 1, idx2))
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
                                seconds = MyBigDecimal(str.substring(idx + 1, idxa))
                                timezoneHours = 0
                                timezoneMinutes = 0
                            } else if (idxb > idx) {
                                seconds = MyBigDecimal(str.substring(idx + 1, idxb))
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
                                seconds = MyBigDecimal(str.substring(idx + 1, idxc))
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
                                seconds = MyBigDecimal(str.substring(idx + 1, str.length - 1))
                                timezoneHours = -1
                                timezoneMinutes = -1
                            }
                        } else {
                            minutes = 0
                            seconds = MyBigDecimal(0.0)
                            timezoneHours = -1
                            timezoneMinutes = -1
                        }
                    } else {
                        hours = 0
                        minutes = 0
                        seconds = MyBigDecimal(0.0)
                        timezoneHours = -1
                        timezoneMinutes = -1
                    }
                } else {
                    day = 0
                    hours = 0
                    minutes = 0
                    seconds = MyBigDecimal(0.0)
                    timezoneHours = -1
                    timezoneMinutes = -1
                }
            } else {
                month = 0
                day = 0
                hours = 0
                minutes = 0
                seconds = MyBigDecimal(0.0)
                timezoneHours = -1
                timezoneMinutes = -1
            }
        } else {
            year = MyBigInteger("0")
            month = 0
            day = 0
            hours = 0
            minutes = 0
            seconds = MyBigDecimal(0.0)
            timezoneHours = -1
            timezoneMinutes = -1
        }
    }

    fun getTZ(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "Z"
        }
        if (timezoneHours == -1 && timezoneMinutes == -1) {
            return ""
        }
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }

    fun getTimeZone(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        return ""
    }

    override fun valueToString(): String {
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
            "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${secondsString}-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
    }

    override /*suspend*/ fun toXMLElement() = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    override fun equals(other: Any?): Boolean {
        if (other is ValueDateTime) {
            return valueToString() == other.valueToString()
        } else if (other is ValueBnode || other is ValueIri) {
            return false
        }
        throw IncompatibleTypesDuringCompareException()
    }

    override fun hashCode() = valueToString().hashCode()
    override fun toDouble(): Double = throw CanNotCastDateTimeToDoubleException()
    override fun toDecimal(): MyBigDecimal = throw CanNotCastDateTimeToDecimalException()
    override fun toInt(): MyBigInteger = throw CanNotCastDateTimeToIntException()
    override fun toBoolean(): Boolean = throw CanNotCastDateTimeToBooleanException()
}
