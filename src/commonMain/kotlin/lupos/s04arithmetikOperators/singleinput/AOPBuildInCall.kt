package lupos.s04arithmetikOperators.singleinput

import com.benasher44.uuid.uuid4
import com.soywiz.krypto.md5
import com.soywiz.krypto.sha1
import com.soywiz.krypto.sha256
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import lupos.s00misc.classNameToString
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.parseFromAOPBase
import lupos.s04logicalOperators.toAOPBase


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class AOPBuiltInCall(var function: BuiltInFunctions, childs: List<AOPBase>) : AOPBase() {
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }
    val localbnode = ThreadSafeUuid()


    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuiltInCall")
        res.addAttribute("function", "" + function)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuiltInCall)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        if (function != other.function)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = if (children.size > 0)
            (children[0] as AOPBase).calculate(resultSet, resultRow)
        else
            AOPUndef()
        when (function) {
            BuiltInFunctions.ABS -> {
                if (a is AOPDouble)
                    return AOPDouble(abs(a.value))
                if (a is AOPDecimal)
                    return AOPDecimal(abs(a.value))
                if (a is AOPInteger)
                    return AOPInteger(abs(a.value).toInt())
                throw Exception("AOPBuiltInCall ABS only works with numeric input")
            }
            BuiltInFunctions.CEIL -> {
                if (a is AOPDouble)
                    return AOPInteger(ceil(a.toDouble()).toInt())
                if (a is AOPDecimal)
                    return AOPInteger(ceil(a.toDouble()).toInt())
                if (a is AOPInteger)
                    return a
                throw Exception("AOPBuiltInCall CEIL only works with numeric input")
            }
            BuiltInFunctions.FLOOR -> {
                if (a is AOPDouble)
                    return AOPInteger(floor(a.toDouble()).toInt())
                if (a is AOPDecimal)
                    return AOPInteger(floor(a.toDouble()).toInt())
                if (a is AOPInteger)
                    return a
                throw Exception("AOPBuiltInCall FLOOR only works with numeric input")
            }
            BuiltInFunctions.ROUND -> {
                if (a is AOPDouble)
                    return AOPInteger(a.toDouble().roundToInt())
                if (a is AOPDecimal)
                    return AOPInteger(a.toDouble().roundToInt())
                if (a is AOPInteger)
                    return a
                throw Exception("AOPBuiltInCall ROUND only works with numeric input")

            }
            BuiltInFunctions.DAY -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.day)
                throw Exception("AOPBuiltInCall DAY only works with dateTime input")
            }
            BuiltInFunctions.MONTH -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.month)
                throw Exception("AOPBuiltInCall MONTH only works with dateTime input")
            }
            BuiltInFunctions.YEAR -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.year)
                throw Exception("AOPBuiltInCall YEAR only works with dateTime input")
            }
            BuiltInFunctions.HOURS -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.hours)
                throw Exception("AOPBuiltInCall HOURS only works with dateTime input")
            }
            BuiltInFunctions.MINUTES -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.minutes)
                throw Exception("AOPBuiltInCall MINUTES only works with dateTime input")
            }
            BuiltInFunctions.SECONDS -> {
                if (a is AOPDateTime)
                    return AOPInteger(a.seconds)
                throw Exception("AOPBuiltInCall SECONDS only works with dateTime input")
            }
            BuiltInFunctions.TZ -> {
                if (a is AOPDateTime)
                    return AOPSimpleLiteral("\"", a.getTZ())
                throw Exception("AOPBuiltInCall TZ only works with dateTime input")
            }
            BuiltInFunctions.TIMEZONE -> {
                if (a is AOPDateTime)
                    return AOPSimpleLiteral("\"", a.getTimeZone())
                throw Exception("AOPBuiltInCall TIMEZONE only works with dateTime input")
            }
            BuiltInFunctions.STRLEN -> {
                if (a is AOPSimpleLiteral)
                    return AOPInteger(a.content.length)
                if (a is AOPTypedLiteral)
                    return AOPInteger(a.content.length)
                if (a is AOPLanguageTaggedLiteral)
                    return AOPInteger(a.content.length)
                throw Exception("AOPBuiltInCall STRLEN only works with string input")
            }
            BuiltInFunctions.IF -> {
                if (a is AOPBoolean) {
                    if (a.value)
                        return (children[1] as AOPBase).calculate(resultSet, resultRow)
                    else
                        return (children[2] as AOPBase).calculate(resultSet, resultRow)
                }
                throw Exception("AOPBuiltInCall IF only works with boolean condition")
            }
            BuiltInFunctions.NOW ->
                return AOPDateTime()
            BuiltInFunctions.BOUND ->
                return AOPBoolean(a !is AOPUndef)
            BuiltInFunctions.isNUMERIC ->
                return AOPBoolean(a is AOPDouble || a is AOPDecimal || a is AOPInteger)
            BuiltInFunctions.isLITERAL ->
                return AOPBoolean(a is AOPSimpleLiteral)
            BuiltInFunctions.isIRI ->
                return AOPBoolean(a is AOPIri)
            BuiltInFunctions.LANGMATCHES -> {
                val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
                    return AOPBoolean(a.content == b.content)
                throw Exception("AOPBuiltInCall LANGMATCHES only works with simple language string input")
            }
            BuiltInFunctions.STRENDS -> {
                if (a is AOPConstantString) {
                    val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                    if (b is AOPSimpleLiteral)
                        return AOPBoolean(a.content.endsWith(b.content))
                    else
                        throw Exception("AOPBuiltInCall STRENDS only works with simple compare string input")
                }
                throw Exception("AOPBuiltInCall STRENDS only works with string input")
            }
            BuiltInFunctions.STRSTARTS -> {
                if (a is AOPConstantString) {
                    val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                    if (b is AOPSimpleLiteral)
                        return AOPBoolean(a.content.startsWith(b.content))
                    else
                        throw Exception("AOPBuiltInCall STRSTARTS only works with simple compare string input")
                }
                throw Exception("AOPBuiltInCall STRSTARTS only works with string input")
            }
            BuiltInFunctions.CONTAINS -> {
                if (a is AOPConstantString) {
                    val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                    if (b is AOPSimpleLiteral)
                        return AOPBoolean(a.content.contains(b.content))
                    else
                        throw Exception("AOPBuiltInCall CONTAINS only works with simple compare string input")
                }
                throw Exception("AOPBuiltInCall CONTAINS only works with string input")
            }
            BuiltInFunctions.LCASE -> {
                if (a is AOPConstantString) {
                    a.content = a.content.toLowerCase()
                    return a
                }
                throw Exception("AOPBuiltInCall LCASE only works with string input")
            }
            BuiltInFunctions.UCASE -> {
                if (a is AOPConstantString) {
                    a.content = a.content.toUpperCase()
                    return a
                }
                throw Exception("AOPBuiltInCall UCASE only works with string input")
            }
            BuiltInFunctions.BNODE -> {
                if (children.size == 1)
                    return AOPBnode("" + uuid + a.valueToString())
                else
                    return AOPBnode("" + uuid + localbnode.next())
            }
            BuiltInFunctions.URI -> {
                if (a is AOPSimpleLiteral)
                    return AOPIri(a.content)
                throw Exception("AOPBuiltInCall URI only works with simple string input")
            }
            BuiltInFunctions.IRI -> {
                if (a is AOPSimpleLiteral)
                    return AOPIri(a.content)
                throw Exception("AOPBuiltInCall IRI only works with simple string input")
            }
            BuiltInFunctions.CONCAT -> {
                val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                if (a is AOPLanguageTaggedLiteral && b is AOPLanguageTaggedLiteral) {
                    if (a.language == b.language) {
                        a.content += b.content
                        return a
                    } else
                        throw Exception("AOPBuiltInCall CONCAT only works with compatible languages input")
                } else if (a is AOPSimpleLiteral && b is AOPSimpleLiteral) {
                    a.content += b.content
                    return a
                } else if (a is AOPSimpleLiteral && b is AOPLanguageTaggedLiteral) {
                    b.content += a.content
                    return b
                } else if (a is AOPLanguageTaggedLiteral && b is AOPSimpleLiteral) {
                    a.content += b.content
                    return a
                }
                throw Exception("AOPBuiltInCall CONCAT only works with compatible string input")
            }
            BuiltInFunctions.STRDT -> {
                val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
                    return AOPTypedLiteral(a.delimiter, a.content, b.content)
                throw Exception("AOPBuiltInCall STRDT only works with simple string input")
            }
            BuiltInFunctions.STRLANG -> {
                val b = (children[1] as AOPBase).calculate(resultSet, resultRow)
                if (a is AOPSimpleLiteral && b is AOPSimpleLiteral)
                    return AOPLanguageTaggedLiteral(a.delimiter, a.content, b.content)
                throw Exception("AOPBuiltInCall STRLANG only works with simple string input")
            }
            BuiltInFunctions.DATATYPE -> {
                if (a is AOPTypedLiteral)
                    return AOPSimpleLiteral(a.delimiter, a.type_iri)
                throw Exception("AOPBuiltInCall DATATYPE only works with typed string input")
            }
            BuiltInFunctions.LANG -> {
                if (a is AOPLanguageTaggedLiteral) {
                    println("LANG a :: ${a.language} ${a.content}")
                    return AOPSimpleLiteral(a.delimiter, a.language)
                }
                println("LANG b :: ?")
                return AOPSimpleLiteral("\"", "")
            }
            BuiltInFunctions.STR -> {
                if (a is AOPConstantString)
                    return AOPSimpleLiteral(a.delimiter, a.content)
                throw Exception("AOPBuiltInCall STR only works with string input")
            }
            BuiltInFunctions.UUID -> return AOPIri("urn:uuid:" + uuid4())
            BuiltInFunctions.STRUUID -> return AOPSimpleLiteral("\"", "" + uuid4())
            BuiltInFunctions.MD5 -> {
                if (a is AOPConstantString)
                    return AOPSimpleLiteral(a.delimiter, a.content.encodeToByteArray().md5().toHexString())
                throw Exception("AOPBuiltInCall MD5 only works with string input")
            }
            BuiltInFunctions.SHA1 -> {
                if (a is AOPConstantString)
                    return AOPSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha1().toHexString())
                throw Exception("AOPBuiltInCall SHA1 only works with string input")
            }
            BuiltInFunctions.SHA256 -> {
                if (a is AOPConstantString)
                    return AOPSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha256().toHexString())
                throw Exception("AOPBuiltInCall SHA256 only works with string input")
            }
            else -> throw Exception("AOPBuiltInCall ${function} not implemented")
        }
    }
}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
fun ByteArray.toHexString(): String {
    val sb = StringBuilder()
    for (b in this) {
        val tmp = (b + 256) % 256
        if (tmp == 0)
            sb.append("00")
        else
            sb.append(tmp.toString(16).padStart(2, '0'))
    }
    return sb.toString()
}
