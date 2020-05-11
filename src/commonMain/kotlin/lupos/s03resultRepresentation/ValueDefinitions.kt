package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement

sealed class ValueDefinition : Comparable<ValueDefinition> {
    abstract fun toXMLElement(): XMLElement
    abstract fun valueToString(): String?
    abstract fun toDouble(): Double
    abstract fun toInt(): Int
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

    override operator fun compareTo(other: ValueDefinition): Int = throw Exception("type error")
}

class ValueBnode(@JvmField var value: String) : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueBnode").addAttribute("value", "" + value)
    override fun valueToString() = "_:" + value
    override fun equals(other: Any?) = (other is ValueBnode) && value == other.value
    override fun toDouble() = throw Exception("cannot cast ValueBnode to Double")
    override fun toInt() = throw Exception("cannot cast ValueBnode to Int")
    override fun toBoolean() = throw Exception("cannot cast ValueBnode to Boolean")
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBnode) {
            throw Exception("type error")
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
        }
    }

    override fun toXMLElement() = XMLElement("ValueBnode").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?) = (other is ValueBoolean) && value == other.value
    override fun toDouble() = throw Exception("cannot cast ValueBoolean to Double")
    override fun toInt() = throw Exception("cannot cast ValueBoolean to Int")
    override fun toBoolean() = value
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueBoolean) {
            throw Exception("type error")
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

abstract sealed class ValueNumeric() : ValueDefinition()
class ValueUndef() : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueUndef")
    override fun valueToString() = null
    override fun equals(other: Any?) = (other is ValueUndef)
    override fun toDouble() = throw Exception("cannot cast ValueUndef to Double")
    override fun toInt() = throw Exception("cannot cast ValueUndef to Int")
    override fun toBoolean() = throw Exception("cannot cast ValueUndef to Boolean")
    override fun hashCode() = 0
}

class ValueError() : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueError")
    override fun valueToString() = null
    override fun equals(other: Any?) = false
    override fun toDouble() = throw Exception("cannot cast ValueError to Double")
    override fun toInt() = throw Exception("cannot cast ValueError to Int")
    override fun toBoolean() = throw Exception("cannot cast ValueError to Boolean")
    override fun hashCode() = 0
}

abstract sealed class ValueStringBase(@JvmField val delimiter: String, @JvmField val content: String) : ValueDefinition() {
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueStringBase) {
            throw Exception("type error")
        }
        return valueToString()!!.compareTo(other.valueToString()!!)
    }

    override fun toBoolean() = content.length > 0
    override fun toDouble() = throw Exception("cannot cast Literal to Double")
    override fun toInt() = throw Exception("cannot cast Literal to Int")
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
                    return ValueInteger(content.toInt())
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
                    return ValueDecimal(content.toDouble())
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

class ValueDecimal(@JvmField var value: Double) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueDecimal").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    override fun equals(other: Any?): Boolean = other is ValueDecimal && value == other.value
    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
            return value.compareTo(other.value)
        }
        throw Exception("type error")
    }
}

class ValueDouble(@JvmField var value: Double) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueDouble").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
    override fun equals(other: Any?): Boolean = other is ValueDouble && value == other.value
    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean() = value > 0 || value < 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
            return value.compareTo(other.value)
        }
        throw Exception("type error")
    }
}

class ValueInteger(@JvmField var value: Int) : ValueNumeric() {
    override fun toXMLElement() = XMLElement("ValueInteger").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    override fun equals(other: Any?): Boolean = other is ValueInteger && value == other.value
    override fun toDouble(): Double = value.toDouble()
    override fun toInt(): Int = value
    override fun toBoolean() = value != 0
    override fun hashCode() = value.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other is ValueInteger) {
            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
            return value.compareTo(other.value)
        }
        throw Exception("type error")
    }
}

class ValueIri(@JvmField var iri: String) : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueIri").addAttribute("value", "" + iri)
    override fun valueToString() = "<" + iri + ">"
    override fun equals(other: Any?): Boolean = other is ValueIri && iri == other.iri
    override fun toDouble(): Double = throw Exception("cannot cast ValueIri to Double")
    override fun toInt(): Int = throw Exception("cannot cast ValueIri to Int")
    override fun toBoolean(): Boolean = throw Exception("cannot cast ValueIri to Boolean")
    override fun hashCode() = iri.hashCode()
    override operator fun compareTo(other: ValueDefinition): Int {
        if (other !is ValueIri) {
            throw Exception("type error")
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
            throw Exception("type error")
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
        val time = com.soywiz.klock.DateTime.now()
        year = time.yearInt
        month = time.month1
        day = time.dayOfMonth
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
    override fun toDouble(): Double = throw Exception("cannot cast ValueDateTime to Double")
    override fun toInt(): Int = throw Exception("cannot cast ValueDateTime to Integer")
    override fun toBoolean(): Boolean = throw Exception("cannot cast ValueDateTime to Boolean")
}
