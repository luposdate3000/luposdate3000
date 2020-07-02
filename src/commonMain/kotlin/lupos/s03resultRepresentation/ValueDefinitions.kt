package lupos.s03resultRepresentation
import kotlin.jvm.JvmField
import lupos.s00misc.CanNotCastBNodeToBooleanException
import lupos.s00misc.CanNotCastBNodeToDoubleException
import lupos.s00misc.CanNotCastBNodeToDecimalException
import lupos.s00misc.CanNotCastBNodeToIntException
import lupos.s00misc.CanNotCastBooleanToDoubleException
import lupos.s00misc.CanNotCastBooleanToDecimalException
import lupos.s00misc.CanNotCastBooleanToIntException
import lupos.s00misc.CanNotCastDateTimeToBooleanException
import lupos.s00misc.CanNotCastDateTimeToDoubleException
import lupos.s00misc.CanNotCastDateTimeToDecimalException
import lupos.s00misc.CanNotCastDateTimeToIntException
import lupos.s00misc.CanNotCastErrorToBooleanException
import lupos.s00misc.CanNotCastErrorToDoubleException
import lupos.s00misc.CanNotCastErrorToDecimalException
import lupos.s00misc.CanNotCastErrorToIntException
import lupos.s00misc.CanNotCastIriToBooleanException
import lupos.s00misc.CanNotCastIriToDoubleException
import lupos.s00misc.CanNotCastIriToDecimalException
import lupos.s00misc.CanNotCastIriToIntException
import lupos.s00misc.CanNotCastLiteralToDoubleException
import lupos.s00misc.CanNotCastLiteralToDecimalException
import lupos.s00misc.CanNotCastLiteralToIntException
import lupos.s00misc.CanNotCastUndefToBooleanException
import lupos.s00misc.CanNotCastUndefToDoubleException
import lupos.s00misc.CanNotCastUndefToDecimalException
import lupos.s00misc.CanNotCastUndefToIntException
import lupos.s00misc.Coverage
import lupos.s00misc.BigInteger
import lupos.s00misc.BigDecimal
import lupos.s00misc.DateHelper
import lupos.s00misc.IncompatibleTypesDuringCompareException
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
sealed class ValueDefinition : Comparable<ValueDefinition> {
    abstract fun toXMLElement(): XMLElement
    abstract fun valueToString(): String?
    abstract fun toDouble(): Double
    abstract fun toDecimal(): BigDecimal
    abstract fun toInt(): BigInteger
    abstract fun toBoolean(): Boolean
    companion object {
        operator fun invoke(tmp: String?): ValueDefinition {
            if (tmp == null || tmp.length == 0) {
                return ValueUndef()
            }
            when {
                tmp.startsWith("_:") -> {
                    return ValueBnode(tmp.substring(2, tmp.length))
                }
                tmp.startsWith("<") && tmp.endsWith(">") -> {
                    return ValueIri(tmp.substring(1, tmp.length - 1))
                }
                !tmp.endsWith("" + tmp.get(0)) -> {
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
                    if (tmp.endsWith(">") && typeIdx > 0) {
                        return ValueTypedLiteral("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    } else {
                        SanityCheck.check { langIdx > 0 }
                        return ValueLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    }
/*Coverage Unreachable*/
                }
                else -> {
                    return ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
                }
            }
/*Coverage Unreachable*/
        }
    }
    fun toSparql(): String {
        val res = valueToString()
        if (res == null) {
            return "UNDEF"
        }
        return res
    }
    override operator fun compareTo(other: ValueDefinition): Int = throw IncompatibleTypesDuringCompareException()
}
class ValueBnode(@JvmField var value: String) : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueBnode").addAttribute("value", "" + value)
    override fun valueToString() = "_:" + value
    override fun equals(other: Any?) = (other is ValueBnode) && value == other.value
    override fun toDouble() = throw CanNotCastBNodeToDoubleException()
    override fun toDecimal() = throw CanNotCastBNodeToDecimalException()
    override fun toInt() = throw CanNotCastBNodeToIntException()
    override fun toBoolean() = throw CanNotCastBNodeToBooleanException()
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBnode) {
            throw IncompatibleTypesDuringCompareException()
        }
        return value.compareTo(other.value)
    }
}
class ValueBoolean(@JvmField var value: Boolean, x: Boolean) : ValueDefinition() {
    companion object {
        val localTrue = ValueBoolean(true, true)
        val localFalse = ValueBoolean(false, true)
        operator fun invoke(value: Boolean): ValueBoolean {
            if (value) {
                return localTrue
            } else {
                return localFalse
            }
/*Coverage Unreachable*/
        }
    }
    override fun toXMLElement() = XMLElement("ValueBoolean").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?) = (other is ValueBoolean) && value == other.value
    override fun toDouble() = throw CanNotCastBooleanToDoubleException()
    override fun toDecimal() = throw CanNotCastBooleanToDecimalException()
    override fun toInt() = throw CanNotCastBooleanToIntException()
    override fun toBoolean() = value
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBoolean) {
            throw IncompatibleTypesDuringCompareException()
        }
        if (value == other.value) {
            return 0
        }
        if (value && !other.value) {
            return 1
        }
        return -1
    }
    override fun hashCode() = value.hashCode()
}
sealed class ValueNumeric() : ValueDefinition()
class ValueUndef() : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueUndef")
    override fun valueToString() = null
    override fun equals(other: Any?) = (other is ValueUndef)
    override fun toDouble() = throw CanNotCastUndefToDoubleException()
    override fun toDecimal() = throw CanNotCastUndefToDecimalException()
    override fun toInt() = throw CanNotCastUndefToIntException()
    override fun toBoolean() = throw CanNotCastUndefToBooleanException()
    override fun hashCode() = 0
}
class ValueError() : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueError")
    override fun valueToString() = null
    override fun equals(other: Any?) = false
    override fun toDouble() = throw CanNotCastErrorToDoubleException()
    override fun toDecimal() = throw CanNotCastErrorToDecimalException()
    override fun toInt() = throw CanNotCastErrorToIntException()
    override fun toBoolean() = throw CanNotCastErrorToBooleanException()
    override fun hashCode() = 0
}
sealed class ValueStringBase(@JvmField val delimiter: String, @JvmField val content: String) : ValueDefinition() {
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueStringBase) {
            throw IncompatibleTypesDuringCompareException()
        }
        return valueToString()!!.compareTo(other.valueToString()!!)
    }
    override fun toBoolean() = content.length > 0
    override fun toDouble() = throw CanNotCastLiteralToDoubleException()
    override fun toDecimal() = throw CanNotCastLiteralToDecimalException()
    override fun toInt() = throw CanNotCastLiteralToIntException()
}
class ValueLanguageTaggedLiteral(delimiter: String, content: String, val language: String) : ValueStringBase(delimiter, content) {
    override fun toXMLElement() = XMLElement("ValueLanguageTaggedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("language", "" + language)
    override fun valueToString() = delimiter + content + delimiter + "@" + language
    override fun equals(other: Any?): Boolean = other is ValueLanguageTaggedLiteral && language == other.language && content == other.content
    override fun hashCode() = delimiter.hashCode() + content.hashCode() + language.hashCode()
}
class ValueSimpleLiteral(delimiter: String, content: String) : ValueStringBase(delimiter, content) {
    override fun toXMLElement() = XMLElement("ValueSimpleLiteral").addAttribute("delimiter", delimiter).addAttribute("content", content)
    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean = other is ValueSimpleLiteral && content == other.content
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
                    return ValueInteger(BigInteger(content))
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#float" -> {
                    return ValueFloat(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
                    return ValueDecimal(BigDecimal(content))
                }
                "http://www.w3.org/2001/XMLSchema#dateTime" -> {
                    return ValueDateTime(delimiter + content + delimiter + "^^<" + type_iri + ">")
                }
                else -> {
                    return ValueTypedLiteral(delimiter, content, type_iri, true)
                }
            }
/*Coverage Unreachable*/
        }
    }
    override fun toXMLElement() = XMLElement("ValueTypedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)
    override fun valueToString() = delimiter + content + delimiter + "^^<" + type_iri + ">"
    override fun equals(other: Any?): Boolean = other is ValueTypedLiteral && type_iri == other.type_iri && content == other.content
    override fun hashCode() = delimiter.hashCode() + content.hashCode() + type_iri.hashCode()
}
val decimalZero=BigDecimal("0.0")
val integerZero=BigInteger("0")

class ValueDecimal(@JvmField var value: BigDecimal) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueDecimal").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value.toPlainString() + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    override fun equals(other: Any?): Boolean = other is ValueDecimal && value == other.value
    override fun toDouble(): Double = value.toDouble()
    override fun toDecimal(): BigDecimal = value
    override fun toInt(): BigInteger = value.toBigInteger()
    override fun toBoolean() = value !=decimalZero
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value.toBigDecimal())
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
            return value.toDouble().compareTo(other.value)
        }
        if (other is ValueFloat) {
            return value.toDouble().compareTo(other.value)
        }
        throw IncompatibleTypesDuringCompareException()
    }
}
class ValueDouble(@JvmField var value: Double) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueDouble").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
    override fun equals(other: Any?): Boolean = other is ValueDouble && value == other.value
    override fun toDouble(): Double = value
    override fun toDecimal(): BigDecimal = value.toBigDecimal()
    override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger()
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value.toDouble())
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value.toDouble())
        }
        if (other is ValueDouble) {
            return value.compareTo(other.value)
        }
        throw IncompatibleTypesDuringCompareException()
    }
}
class ValueFloat(@JvmField var value: Double) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueFloat").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#float>"
    override fun equals(other: Any?): Boolean = other is ValueFloat && value == other.value
    override fun toDecimal(): BigDecimal = value.toBigDecimal()
    override fun toDouble(): Double = value
    override fun toInt(): BigInteger = value.toBigDecimal().toBigInteger() 
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value.toDouble())
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value.toDouble())
        }
        if (other is ValueDouble) {
            return value.compareTo(other.value)
        }
        if (other is ValueFloat) {
            return value.compareTo(other.value)
        }
        throw IncompatibleTypesDuringCompareException()
    }
}
class ValueInteger(@JvmField var value: BigInteger) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueInteger").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    override fun equals(other: Any?): Boolean = other is ValueInteger && value == other.value
    override fun toDecimal(): BigDecimal = value.toBigDecimal()
    override fun toDouble(): Double = value.toDouble()
    override fun toInt(): BigInteger = value
    override fun toBoolean() = value != integerZero
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
            return value.toBigDecimal().compareTo(other.value)
        }
        if (other is ValueDouble) {
            return value.toDouble().compareTo(other.value)
        }
        if (other is ValueFloat) {
            return value.toDouble().compareTo(other.value)
        }
        throw IncompatibleTypesDuringCompareException()
    }
}
class ValueIri(@JvmField var iri: String) : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueIri").addAttribute("value", "" + iri)
    override fun valueToString() = "<" + iri + ">"
    override fun equals(other: Any?): Boolean = other is ValueIri && iri == other.iri
    override fun toDouble(): Double = throw CanNotCastIriToDoubleException()
    override fun toDecimal(): BigDecimal = throw CanNotCastIriToDecimalException()
    override fun toInt(): BigInteger = throw CanNotCastIriToIntException()
    override fun toBoolean(): Boolean = throw CanNotCastIriToBooleanException()
    override fun hashCode() = iri.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueIri) {
            throw IncompatibleTypesDuringCompareException()
        }
        return iri.compareTo(other.iri)
    }
}
class ValueDateTime : ValueDefinition {
    @JvmField
    val year: Int
    @JvmField
    val month: Int
    @JvmField
    val day: Int
    @JvmField
    val hours: Int
    @JvmField
    val minutes: Int
    @JvmField
    val seconds: Int
    @JvmField
    val timezoneHours: Int
    @JvmField
    val timezoneMinutes: Int
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueDateTime) {
            throw IncompatibleTypesDuringCompareException()
        }
        if (year != other.year) {
            return year.compareTo(other.year)
        }
        if (month != other.month) {
            return month.compareTo(other.month)
        }
        if (day != other.day) {
            return day.compareTo(other.day)
        }
        if (hours != other.hours) {
            return hours.compareTo(other.hours)
        }
        if (minutes != other.minutes) {
            return minutes.compareTo(other.minutes)
        }
        if (seconds != other.seconds) {
            return seconds.compareTo(other.seconds)
        }
        if (timezoneHours != other.timezoneHours) {
            return timezoneHours.compareTo(other.timezoneHours)
        }
        if (timezoneMinutes != other.timezoneMinutes) {
            return timezoneMinutes.compareTo(other.timezoneMinutes)
        }
        return 0
    }
    constructor() : super() {
        val time = DateHelper()
        year = time.year
        month = time.month
        day = time.day
        hours = time.hours
        minutes = time.minutes
        seconds = time.seconds
        timezoneHours = 0
        timezoneMinutes = 0
    }
    constructor(str: String) : super() {
        if (str.length >= 10) {
            year = str.substring(1, 5).toInt()
            month = str.substring(6, 8).toInt()
            day = str.substring(9, 11).toInt()
        } else {
            year = 0
            month = 0
            day = 0
        }
        if (str.length >= 19) {
            hours = str.substring(12, 14).toInt()
            minutes = str.substring(15, 17).toInt()
            seconds = str.substring(18, 20).toInt()
        } else {
            hours = 0
            minutes = 0
            seconds = 0
        }
        if (str.length >= 25 && str[20] == '-') {
            timezoneHours = str.substring(21, 23).toInt()
            timezoneMinutes = str.substring(24, 26).toInt()
        } else if (str.length >= 20 && str[20] == 'Z') {
            timezoneHours = 0
            timezoneMinutes = 0
        } else {
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
        if (timezoneHours == -1 && timezoneMinutes == -1) {
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
/*Coverage Unreachable*/
    }
    override fun toXMLElement() = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    override fun equals(other: Any?): Boolean = other is ValueDateTime && valueToString() == other.valueToString()
    override fun hashCode() = valueToString().hashCode()
    override fun toDouble(): Double = throw CanNotCastDateTimeToDoubleException()
    override fun toDecimal(): BigDecimal = throw CanNotCastDateTimeToDecimalException()
    override fun toInt(): BigInteger = throw CanNotCastDateTimeToIntException()
    override fun toBoolean(): Boolean = throw CanNotCastDateTimeToBooleanException()
}
