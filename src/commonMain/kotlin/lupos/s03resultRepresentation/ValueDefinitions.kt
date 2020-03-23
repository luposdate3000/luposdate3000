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
        operator fun invoke(tmp: String?): ValueDefinition {
Coverage.funStart(1048)
            if (tmp == null || tmp.length == 0) {
Coverage.ifStart(1049)
                return ValueUndef()
            }
Coverage.statementStart(1050)
            when {
                tmp.startsWith("_:") -> {
Coverage.whenCaseStart(1051)
                    return ValueBnode(tmp.substring(2, tmp.length))
                }
                tmp.startsWith("<") && tmp.endsWith(">") -> {
Coverage.whenCaseStart(1052)
                    return ValueIri(tmp.substring(1, tmp.length - 1))
                }
                !tmp.endsWith("" + tmp.get(0)) -> {
Coverage.whenCaseStart(1053)
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
Coverage.statementStart(1054)
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
Coverage.statementStart(1055)
                    if (tmp.endsWith(">") && typeIdx > 0) {
Coverage.ifStart(1056)
                        return ValueTypedLiteral("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    } else {
Coverage.ifStart(1057)
                        require(langIdx > 0)
Coverage.statementStart(1058)
                        return ValueLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    }
/*Coverage Unreachable*/
                }
                else -> {
Coverage.whenCaseStart(1059)
                    return ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
                }
            }
/*Coverage Unreachable*/
        }
    }
    fun toSparql(): String {
Coverage.funStart(1060)
        val res = valueToString()
Coverage.statementStart(1061)
        if (res == null) {
Coverage.ifStart(1062)
            return "UNDEF"
        }
Coverage.statementStart(1063)
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
Coverage.funStart(1064)
        if (other !is ValueBnode) {
Coverage.ifStart(1065)
            throw Exception("type error")
        }
Coverage.statementStart(1066)
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
Coverage.funStart(1067)
        if (other !is ValueBoolean) {
Coverage.ifStart(1068)
            throw Exception("type error")
        }
Coverage.statementStart(1069)
        if (value == other.value) {
Coverage.ifStart(1070)
            return 0
        }
Coverage.statementStart(1071)
        if (value && !other.value) {
Coverage.ifStart(1072)
            return 1
        }
Coverage.statementStart(1073)
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
Coverage.funStart(1074)
        if (other !is ValueStringBase) {
Coverage.ifStart(1075)
            throw Exception("type error")
        }
Coverage.statementStart(1076)
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
class ValueTypedLiteral(delimiter: String, content: String, @JvmField val type_iri: String, b: Boolean) : ValueStringBase(delimiter, content) {
    companion object {
        operator fun invoke(delimiter: String, content: String, type_iri: String): ValueDefinition {
Coverage.funStart(1077)
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> {
Coverage.whenCaseStart(1078)
                    return ValueBoolean(content.toBoolean())
                }
                "http://www.w3.org/2001/XMLSchema#integer" -> {
Coverage.whenCaseStart(1079)
                    return ValueInteger(content.toInt())
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
Coverage.whenCaseStart(1080)
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
Coverage.whenCaseStart(1081)
                    return ValueDecimal(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#dateTime" -> {
Coverage.whenCaseStart(1082)
                    return ValueDateTime(delimiter + content + delimiter + "^^<" + type_iri + ">")
                }
                else -> {
Coverage.whenCaseStart(1083)
                    return ValueTypedLiteral(delimiter, content, type_iri, true)
                }
            }
/*Coverage Unreachable*/
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
Coverage.funStart(1084)
        if (other is ValueInteger) {
Coverage.ifStart(1085)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1086)
        if (other is ValueDecimal) {
Coverage.ifStart(1087)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1088)
        if (other is ValueDouble) {
Coverage.ifStart(1089)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1090)
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
Coverage.funStart(1091)
        if (other is ValueInteger) {
Coverage.ifStart(1092)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1093)
        if (other is ValueDecimal) {
Coverage.ifStart(1094)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1095)
        if (other is ValueDouble) {
Coverage.ifStart(1096)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1097)
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
Coverage.funStart(1098)
        if (other is ValueInteger) {
Coverage.ifStart(1099)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1100)
        if (other is ValueDecimal) {
Coverage.ifStart(1101)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1102)
        if (other is ValueDouble) {
Coverage.ifStart(1103)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1104)
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
Coverage.funStart(1105)
        if (other !is ValueIri) {
Coverage.ifStart(1106)
            throw Exception("type error")
        }
Coverage.statementStart(1107)
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
Coverage.funStart(1108)
        if (other !is ValueDateTime) {
Coverage.ifStart(1109)
            throw Exception("type error")
        }
Coverage.statementStart(1110)
        if (year != other.year) {
Coverage.ifStart(1111)
            return year.compareTo(other.year)
        }
Coverage.statementStart(1112)
        if (month != other.month) {
Coverage.ifStart(1113)
            return month.compareTo(other.month)
        }
Coverage.statementStart(1114)
        if (day != other.day) {
Coverage.ifStart(1115)
            return day.compareTo(other.day)
        }
Coverage.statementStart(1116)
        if (hours != other.hours) {
Coverage.ifStart(1117)
            return hours.compareTo(other.hours)
        }
Coverage.statementStart(1118)
        if (minutes != other.minutes) {
Coverage.ifStart(1119)
            return minutes.compareTo(other.minutes)
        }
Coverage.statementStart(1120)
        if (seconds != other.seconds) {
Coverage.ifStart(1121)
            return seconds.compareTo(other.seconds)
        }
Coverage.statementStart(1122)
        if (timezoneHours != other.timezoneHours) {
Coverage.ifStart(1123)
            return timezoneHours.compareTo(other.timezoneHours)
        }
Coverage.statementStart(1124)
        if (timezoneMinutes != other.timezoneMinutes) {
Coverage.ifStart(1125)
            return timezoneMinutes.compareTo(other.timezoneMinutes)
        }
Coverage.statementStart(1126)
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
Coverage.ifStart(1127)
            year = str.substring(1, 5).toInt()
            month = str.substring(6, 8).toInt()
            day = str.substring(9, 11).toInt()
        } else {
Coverage.ifStart(1128)
            year = 0
            month = 0
            day = 0
        }
        if (str.length >= 19) {
Coverage.ifStart(1129)
            hours = str.substring(12, 14).toInt()
            minutes = str.substring(15, 17).toInt()
            seconds = str.substring(18, 20).toInt()
        } else {
Coverage.ifStart(1130)
            hours = 0
            minutes = 0
            seconds = 0
        }
        if (str.length >= 25 && str[20] == '-') {
Coverage.ifStart(1131)
            timezoneHours = str.substring(21, 23).toInt()
            timezoneMinutes = str.substring(24, 26).toInt()
        } else if (str.length >= 20 && str[20] == 'Z') {
Coverage.ifStart(1132)
            timezoneHours = 0
            timezoneMinutes = 0
        } else {
Coverage.ifStart(1133)
            timezoneHours = -1
            timezoneMinutes = -1
        }
    }
    fun getTZ(): String {
Coverage.funStart(1134)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1135)
            return "Z"
        }
Coverage.statementStart(1136)
        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(1137)
            return ""
        }
Coverage.statementStart(1138)
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }
    fun getTimeZone(): String {
Coverage.funStart(1139)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1140)
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
Coverage.statementStart(1141)
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
Coverage.ifStart(1142)
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
Coverage.statementStart(1143)
        return ""
    }
    override fun valueToString(): String {
Coverage.funStart(1144)
        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(1145)
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1146)
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
Coverage.ifStart(1147)
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
