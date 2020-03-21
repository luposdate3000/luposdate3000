package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.ResultChunk
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.ResultIterator

sealed class ValueDefinition : Comparable<ValueDefinition> {
    abstract fun toXMLElement(): XMLElement
    abstract fun valueToString(): String?
    abstract fun toDouble(): Double
    abstract fun toInt(): Int
    abstract fun toBoolean(): Boolean

    companion object {
        fun create(tmp: String?): ValueDefinition {
Coverage.funStart(283)

            if (tmp == null || tmp.length == 0) {
Coverage.ifStart(284)

                return ValueUndef()
            }
            when {
                tmp.startsWith("_:") -> return ValueBnode(tmp.substring(2, tmp.length))
                tmp.startsWith("<") && tmp.endsWith(">") -> return ValueIri(tmp.substring(1, tmp.length - 1))
                !tmp.endsWith("" + tmp.get(0)) -> {
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
                    if (tmp.endsWith(">") && typeIdx > 0) {
Coverage.ifStart(285)

                        return ValueTypedLiteral.create("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    } else if (langIdx > 0) {
Coverage.ifStart(286)

                        return ValueLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    } else {
Coverage.ifStart(287)

                        throw Exception("AOPVariable unknown type #${tmp}#") 
                    }
                }
                else -> {
Coverage.ifStart(288)

                    return ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
                }
            }
        }
    }

    fun toSparql(): String {
Coverage.funStart(289)

        val res = valueToString()
        if (res == null) {
Coverage.ifStart(290)

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
Coverage.funStart(291)

        if (other !is ValueBnode) {
Coverage.ifStart(292)

            throw Exception("type error")
        }
        return value.compareTo(other.value)
    }
}

class ValueBoolean(@JvmField var value: Boolean) : ValueDefinition() {
    override fun toXMLElement() = XMLElement("ValueBnode").addAttribute("value", "" + value)
    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?) = (other is ValueBoolean) && value == other.value
    override fun toDouble() = throw Exception("cannot cast ValueBoolean to Double")
    override fun toInt() = throw Exception("cannot cast ValueBoolean to Int")
    override fun toBoolean() = value
    override operator fun compareTo(other: ValueDefinition): Int {
Coverage.funStart(293)

        if (other !is ValueBoolean) {
Coverage.ifStart(294)

            throw Exception("type error")
        }
        if (value == other.value) {
Coverage.ifStart(295)

            return 0
        }
        if (value && !other.value) {
Coverage.ifStart(296)

            return 1
        }
        return -1
    }

    override fun hashCode() = value.hashCode()
}

abstract class ValueNumeric() : ValueDefinition()
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

abstract class ValueStringBase(@JvmField val delimiter: String, @JvmField val content: String) : ValueDefinition() {
    override operator fun compareTo(other: ValueDefinition): Int {
Coverage.funStart(297)

        if (other !is ValueStringBase) {
Coverage.ifStart(298)

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
    override fun equals(other: Any?): Boolean = other is ValueLanguageTaggedLiteral && delimiter == other.delimiter && language == other.language && content == other.content
    override fun hashCode() = delimiter.hashCode() + content.hashCode() + language.hashCode()
}

class ValueSimpleLiteral(delimiter: String, content: String) : ValueStringBase(delimiter, content) {
    override fun toXMLElement() = XMLElement("ValueSimpleLiteral").addAttribute("delimiter", delimiter).addAttribute("content", content)
    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean = other is ValueSimpleLiteral && delimiter == other.delimiter && content == other.content
    override fun hashCode() = delimiter.hashCode() + content.hashCode()
}

class ValueTypedLiteral(delimiter: String, content: String, @JvmField val type_iri: String) : ValueStringBase(delimiter, content) {
    companion object {
        fun create(delimiter: String, content: String, type_iri: String): ValueDefinition {
Coverage.funStart(299)

            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> return ValueBoolean(content.toBoolean())
                "http://www.w3.org/2001/XMLSchema#integer" -> return ValueInteger(content.toInt())
                "http://www.w3.org/2001/XMLSchema#double" -> return ValueDouble(content.toDouble())
                "http://www.w3.org/2001/XMLSchema#decimal" -> return ValueDecimal(content.toDouble())
                "http://www.w3.org/2001/XMLSchema#dateTime" -> return ValueDateTime(delimiter + content + delimiter + "^^<" + type_iri + ">")
                else -> {
Coverage.ifStart(300)

                    return ValueTypedLiteral(delimiter, content, type_iri)
                }
            }
        }
    }

    override fun toXMLElement() = XMLElement("ValueTypedLiteral").addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)
    override fun valueToString() = delimiter + content + delimiter + "^^<" + type_iri + ">"
    override fun equals(other: Any?): Boolean = other is ValueTypedLiteral && delimiter == other.delimiter && type_iri == other.type_iri && content == other.content
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
Coverage.funStart(301)

        if (other is ValueInteger) {
Coverage.ifStart(302)

            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
Coverage.ifStart(303)

            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
Coverage.ifStart(304)

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
Coverage.funStart(305)

        if (other is ValueInteger) {
Coverage.ifStart(306)

            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
Coverage.ifStart(307)

            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
Coverage.ifStart(308)

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
Coverage.funStart(309)

        if (other is ValueInteger) {
Coverage.ifStart(310)

            return value.compareTo(other.value)
        }
        if (other is ValueDecimal) {
Coverage.ifStart(311)

            return value.compareTo(other.value)
        }
        if (other is ValueDouble) {
Coverage.ifStart(312)

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
Coverage.funStart(313)

        if (other !is ValueIri) {
Coverage.ifStart(314)

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
Coverage.funStart(315)

        if (other !is ValueDateTime) {
Coverage.ifStart(316)

            throw Exception("type error")
        }
        if (year != (other as ValueDateTime).year) {
Coverage.ifStart(317)

            return year.compareTo((other as ValueDateTime).year)
        }
        if (month != (other as ValueDateTime).month) {
Coverage.ifStart(318)

            return month.compareTo((other as ValueDateTime).month)
        }
        if (day != (other as ValueDateTime).day) {
Coverage.ifStart(319)

            return day.compareTo((other as ValueDateTime).day)
        }
        if (hours != (other as ValueDateTime).hours) {
Coverage.ifStart(320)

            return hours.compareTo((other as ValueDateTime).hours)
        }
        if (minutes != (other as ValueDateTime).minutes) {
Coverage.ifStart(321)

            return minutes.compareTo((other as ValueDateTime).minutes)
        }
        if (seconds != (other as ValueDateTime).seconds) {
Coverage.ifStart(322)

            return seconds.compareTo((other as ValueDateTime).seconds)
        }
        if (timezoneHours != (other as ValueDateTime).timezoneHours) {
Coverage.ifStart(323)

            return timezoneHours.compareTo((other as ValueDateTime).timezoneHours)
        }
        if (timezoneMinutes != (other as ValueDateTime).timezoneMinutes) {
Coverage.ifStart(324)

            return timezoneMinutes.compareTo((other as ValueDateTime).timezoneMinutes)
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
Coverage.ifStart(325)

            year = str.substring(1, 5).toInt()
            month = str.substring(6, 8).toInt()
            day = str.substring(9, 11).toInt()
        } else {
Coverage.ifStart(326)

            year = 0
            month = 0
            day = 0
        }
        if (str.length >= 19) {
Coverage.ifStart(327)

            hours = str.substring(12, 14).toInt()
            minutes = str.substring(15, 17).toInt()
            seconds = str.substring(18, 20).toInt()
        } else {
Coverage.ifStart(328)

            hours = 0
            minutes = 0
            seconds = 0
        }
        if (str.length >= 25 && str[20] == '-') {
Coverage.ifStart(329)

            timezoneHours = str.substring(21, 23).toInt()
            timezoneMinutes = str.substring(24, 26).toInt()
        } else if (str.length >= 20 && str[20] == 'Z') {
Coverage.ifStart(330)

            timezoneHours = 0
            timezoneMinutes = 0
        } else {
Coverage.ifStart(331)

            timezoneHours = -1
            timezoneMinutes = -1
        }
    }

    fun getTZ(): String {
Coverage.funStart(332)

        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(333)

            return "Z"
        }
        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(334)

            return ""
        }
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }

    fun getTimeZone(): String {
Coverage.funStart(335)

        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(336)

            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
Coverage.ifStart(337)

            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
        return ""
    }

    override fun valueToString(): String {
Coverage.funStart(338)

        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(339)

            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(340)

            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
Coverage.ifStart(341)

            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
    }

    override fun toXMLElement() = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    override fun equals(other: Any?): Boolean = other is ValueDateTime && valueToString() == other.valueToString()
    override fun hashCode() = valueToString().hashCode()
    override fun toDouble(): Double = throw Exception("cannot cast ValueDateTime to Double")
    override fun toInt(): Int = throw Exception("cannot cast ValueDateTime to Integer")
    override fun toBoolean(): Boolean = throw Exception("cannot cast ValueDateTime to Boolean")
}
