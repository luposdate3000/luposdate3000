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
Coverage.funStart(1151)
            if (tmp == null || tmp.length == 0) {
Coverage.ifStart(1152)
                return ValueUndef()
            }
Coverage.statementStart(1153)
            when {
                tmp.startsWith("_:") -> {
Coverage.whenCaseStart(1154)
                    return ValueBnode(tmp.substring(2, tmp.length))
                }
                tmp.startsWith("<") && tmp.endsWith(">") -> {
Coverage.whenCaseStart(1155)
                    return ValueIri(tmp.substring(1, tmp.length - 1))
                }
                !tmp.endsWith("" + tmp.get(0)) -> {
Coverage.whenCaseStart(1156)
                    val typeIdx = tmp.lastIndexOf("" + tmp.get(0) + "^^<")
Coverage.statementStart(1157)
                    val langIdx = tmp.lastIndexOf("" + tmp.get(0) + "@")
Coverage.statementStart(1158)
                    if (tmp.endsWith(">") && typeIdx > 0) {
Coverage.ifStart(1159)
                        return ValueTypedLiteral.create("" + tmp.get(0), tmp.substring(1, typeIdx), tmp.substring(typeIdx + 4, tmp.length - 1))
                    } else if (langIdx > 0) {
Coverage.ifStart(1160)
                        return ValueLanguageTaggedLiteral("" + tmp.get(0), tmp.substring(1, langIdx), tmp.substring(langIdx + 2, tmp.length))
                    } else {
Coverage.ifStart(1161)
                        throw Exception("AOPVariable unknown type #${tmp}#")
                    }
Coverage.statementStart(1162)
                }
                else -> {
Coverage.whenCaseStart(1163)
                    return ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.length - 1))
                }
            }
Coverage.statementStart(1164)
        }
    }
    fun toSparql(): String {
Coverage.funStart(1165)
        val res = valueToString()
Coverage.statementStart(1166)
        if (res == null) {
Coverage.ifStart(1167)
            return "UNDEF"
        }
Coverage.statementStart(1168)
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
Coverage.funStart(1169)
        if (other !is ValueBnode) {
Coverage.ifStart(1170)
            throw Exception("type error")
        }
Coverage.statementStart(1171)
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
Coverage.funStart(1172)
        if (other !is ValueBoolean) {
Coverage.ifStart(1173)
            throw Exception("type error")
        }
Coverage.statementStart(1174)
        if (value == other.value) {
Coverage.ifStart(1175)
            return 0
        }
Coverage.statementStart(1176)
        if (value && !other.value) {
Coverage.ifStart(1177)
            return 1
        }
Coverage.statementStart(1178)
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
Coverage.funStart(1179)
        if (other !is ValueStringBase) {
Coverage.ifStart(1180)
            throw Exception("type error")
        }
Coverage.statementStart(1181)
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
Coverage.funStart(1182)
            when (type_iri) {
                "http://www.w3.org/2001/XMLSchema#boolean" -> {
Coverage.whenCaseStart(1183)
                    return ValueBoolean(content.toBoolean())
                }
                "http://www.w3.org/2001/XMLSchema#integer" -> {
Coverage.whenCaseStart(1184)
                    return ValueInteger(content.toInt())
                }
                "http://www.w3.org/2001/XMLSchema#double" -> {
Coverage.whenCaseStart(1185)
                    return ValueDouble(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#decimal" -> {
Coverage.whenCaseStart(1186)
                    return ValueDecimal(content.toDouble())
                }
                "http://www.w3.org/2001/XMLSchema#dateTime" -> {
Coverage.whenCaseStart(1187)
                    return ValueDateTime(delimiter + content + delimiter + "^^<" + type_iri + ">")
                }
                else -> {
Coverage.whenCaseStart(1188)
                    return ValueTypedLiteral(delimiter, content, type_iri)
                }
            }
Coverage.statementStart(1189)
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
Coverage.funStart(1190)
        if (other is ValueInteger) {
Coverage.ifStart(1191)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1192)
        if (other is ValueDecimal) {
Coverage.ifStart(1193)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1194)
        if (other is ValueDouble) {
Coverage.ifStart(1195)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1196)
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
Coverage.funStart(1197)
        if (other is ValueInteger) {
Coverage.ifStart(1198)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1199)
        if (other is ValueDecimal) {
Coverage.ifStart(1200)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1201)
        if (other is ValueDouble) {
Coverage.ifStart(1202)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1203)
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
Coverage.funStart(1204)
        if (other is ValueInteger) {
Coverage.ifStart(1205)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1206)
        if (other is ValueDecimal) {
Coverage.ifStart(1207)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1208)
        if (other is ValueDouble) {
Coverage.ifStart(1209)
            return value.compareTo(other.value)
        }
Coverage.statementStart(1210)
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
Coverage.funStart(1211)
        if (other !is ValueIri) {
Coverage.ifStart(1212)
            throw Exception("type error")
        }
Coverage.statementStart(1213)
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
Coverage.funStart(1214)
        if (other !is ValueDateTime) {
Coverage.ifStart(1215)
            throw Exception("type error")
        }
Coverage.statementStart(1216)
        if (year != (other as ValueDateTime).year) {
Coverage.ifStart(1217)
            return year.compareTo((other as ValueDateTime).year)
        }
Coverage.statementStart(1218)
        if (month != (other as ValueDateTime).month) {
Coverage.ifStart(1219)
            return month.compareTo((other as ValueDateTime).month)
        }
Coverage.statementStart(1220)
        if (day != (other as ValueDateTime).day) {
Coverage.ifStart(1221)
            return day.compareTo((other as ValueDateTime).day)
        }
Coverage.statementStart(1222)
        if (hours != (other as ValueDateTime).hours) {
Coverage.ifStart(1223)
            return hours.compareTo((other as ValueDateTime).hours)
        }
Coverage.statementStart(1224)
        if (minutes != (other as ValueDateTime).minutes) {
Coverage.ifStart(1225)
            return minutes.compareTo((other as ValueDateTime).minutes)
        }
Coverage.statementStart(1226)
        if (seconds != (other as ValueDateTime).seconds) {
Coverage.ifStart(1227)
            return seconds.compareTo((other as ValueDateTime).seconds)
        }
Coverage.statementStart(1228)
        if (timezoneHours != (other as ValueDateTime).timezoneHours) {
Coverage.ifStart(1229)
            return timezoneHours.compareTo((other as ValueDateTime).timezoneHours)
        }
Coverage.statementStart(1230)
        if (timezoneMinutes != (other as ValueDateTime).timezoneMinutes) {
Coverage.ifStart(1231)
            return timezoneMinutes.compareTo((other as ValueDateTime).timezoneMinutes)
        }
Coverage.statementStart(1232)
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
Coverage.ifStart(1233)
            year = str.substring(1, 5).toInt()
            month = str.substring(6, 8).toInt()
            day = str.substring(9, 11).toInt()
        } else {
Coverage.ifStart(1234)
            year = 0
            month = 0
            day = 0
        }
        if (str.length >= 19) {
Coverage.ifStart(1235)
            hours = str.substring(12, 14).toInt()
            minutes = str.substring(15, 17).toInt()
            seconds = str.substring(18, 20).toInt()
        } else {
Coverage.ifStart(1236)
            hours = 0
            minutes = 0
            seconds = 0
        }
        if (str.length >= 25 && str[20] == '-') {
Coverage.ifStart(1237)
            timezoneHours = str.substring(21, 23).toInt()
            timezoneMinutes = str.substring(24, 26).toInt()
        } else if (str.length >= 20 && str[20] == 'Z') {
Coverage.ifStart(1238)
            timezoneHours = 0
            timezoneMinutes = 0
        } else {
Coverage.ifStart(1239)
            timezoneHours = -1
            timezoneMinutes = -1
        }
    }
    fun getTZ(): String {
Coverage.funStart(1240)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1241)
            return "Z"
        }
Coverage.statementStart(1242)
        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(1243)
            return ""
        }
Coverage.statementStart(1244)
        return "-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}"
    }
    fun getTimeZone(): String {
Coverage.funStart(1245)
        if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1246)
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
Coverage.statementStart(1247)
        if (timezoneHours >= 0 && timezoneMinutes == 0) {
Coverage.ifStart(1248)
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        }
Coverage.statementStart(1249)
        return ""
    }
    override fun valueToString(): String {
Coverage.funStart(1250)
        if (timezoneHours == -1 && timezoneMinutes == -1) {
Coverage.ifStart(1251)
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else if (timezoneHours == 0 && timezoneMinutes == 0) {
Coverage.ifStart(1252)
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        } else {
Coverage.ifStart(1253)
            return "\"${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
        }
Coverage.statementStart(1254)
    }
    override fun toXMLElement() = XMLElement("ValueDateTime").addAttribute("value", valueToString())
    override fun equals(other: Any?): Boolean = other is ValueDateTime && valueToString() == other.valueToString()
    override fun hashCode() = valueToString().hashCode()
    override fun toDouble(): Double = throw Exception("cannot cast ValueDateTime to Double")
    override fun toInt(): Int = throw Exception("cannot cast ValueDateTime to Integer")
    override fun toBoolean(): Boolean = throw Exception("cannot cast ValueDateTime to Boolean")
}
