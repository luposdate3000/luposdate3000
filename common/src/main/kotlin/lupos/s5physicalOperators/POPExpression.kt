package lupos.s5physicalOperators

import kotlin.math.*
import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase
import com.soywiz.krypto.md5
import com.soywiz.krypto.sha1
import com.soywiz.krypto.sha256

class ArithmeticException() : Exception()

class DateTime {
    val year: Int
    val month: Int
    val day: Int
    val hours: Int
    val minutes: Int
    val seconds: Int
    val timezoneHours: Int
    val timezoneMinutes: Int

    constructor(str: String) {
        println("DateTime from $str")
        if (str.length >= 10) {
            println("#" + str.substring(1, 5) + "#")
            year = str.substring(1, 5).toInt()
            println("#" + str.substring(6, 8) + "#")
            month = str.substring(6, 8).toInt()
            println("#" + str.substring(9, 11) + "#")
            day = str.substring(9, 11).toInt()
        } else {
            year = 0
            month = 0
            day = 0
        }
        if (str.length >= 19) {
            println("#" + str.substring(12, 14) + "#")
            hours = str.substring(12, 14).toInt()
            println("#" + str.substring(15, 17) + "#")
            minutes = str.substring(15, 17).toInt()
            println("#" + str.substring(18, 20) + "#")
            seconds = str.substring(18, 20).toInt()
        } else {
            hours = 0
            minutes = 0
            seconds = 0
        }
        if (str.length >= 25 && str[20] == '-') {
            println("#" + str[21] + "#")
            println("#" + str.substring(21, 23) + "#")
            timezoneHours = str.substring(21, 23).toInt()
            println("#" + str.substring(24, 26) + "#")
            timezoneMinutes = str.substring(24, 26).toInt()
        } else if (str.length >= 20 && str[20] == 'Z') {
            println("#" + str[20] + "#")
            timezoneHours = 0
            timezoneMinutes = 0
        } else {
            timezoneHours = -1
            timezoneMinutes = -1
        }
        println("DateTime extracted :: " + toString())
    }

    fun getTZ(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0)
            return "\"Z\""
        if (timezoneHours == -1 && timezoneMinutes == -1)
            return "\"\""
        return "\"-${timezoneHours.toString().padStart(2, '0')}:${timezoneMinutes.toString().padStart(2, '0')}\""
    }

    fun getTimeZone(): String {
        if (timezoneHours == 0 && timezoneMinutes == 0)
            return "\"PT0S\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        if (timezoneHours >= 0 && timezoneMinutes == 0)
            return "\"-PT${timezoneHours}H\"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>"
        return ""
    }

    override fun toString(): String {
        if (timezoneHours == -1 && timezoneMinutes == -1)
            return "${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}"
        else if (timezoneHours == 0 && timezoneMinutes == 0)
            return "${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}Z"
        else
            return "${year.toString().padStart(4, '0')}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}T${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}-timezoneHours:${timezoneMinutes.toString().padStart(2, '0')}"
    }
}

enum class TmpResultType {
    RSBoolean, RSString, RSInteger, RSDouble, RSDateTime
}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class POPExpression : OPBase {
    private val dataTypeInteger = "^^<http://www.w3.org/2001/XMLSchema#integer>"
    private val dataTypeDouble = "^^<http://www.w3.org/2001/XMLSchema#decimal>"
    private val dataTypeBoolean = "^^<http://www.w3.org/2001/XMLSchema#boolean>"
    private val dataTypeDateTime = "^^<http://www.w3.org/2001/XMLSchema#dateTime>"
    private val dataTypeString = "^^<http://www.w3.org/2001/XMLSchema#string>"
    var child: ASTNode

    constructor(child: ASTNode) {
        this.child = child
    }

    private fun getResultType(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): TmpResultType {
        when (node) {
            is ASTLiteral -> return TmpResultType.RSString
            is ASTBooleanLiteral -> return TmpResultType.RSBoolean
            is ASTOr -> return TmpResultType.RSBoolean
            is ASTAnd -> return TmpResultType.RSBoolean
            is ASTEQ -> return TmpResultType.RSBoolean
            is ASTGEQ -> return TmpResultType.RSBoolean
            is ASTLEQ -> return TmpResultType.RSBoolean
            is ASTGT -> return TmpResultType.RSBoolean
            is ASTLT -> return TmpResultType.RSBoolean
            is ASTIri -> return TmpResultType.RSString
            is ASTInteger -> return TmpResultType.RSInteger
            is ASTAddition -> return TmpResultType.RSInteger
            is ASTMultiplication -> return TmpResultType.RSInteger
            is ASTDivision -> return TmpResultType.RSInteger
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                println("XXX ${tmp} -> ${tmp.endsWith(dataTypeInteger)} ${tmp.endsWith(dataTypeDouble)} ${tmp.startsWith("<http")}")
                when {
                    tmp.endsWith(dataTypeInteger) -> return TmpResultType.RSInteger
                    tmp.endsWith(dataTypeDouble) -> return TmpResultType.RSDouble
                    tmp.endsWith(dataTypeBoolean) -> return TmpResultType.RSBoolean
                    tmp.endsWith(dataTypeDateTime) -> return TmpResultType.RSDateTime
                    tmp.startsWith("<http") -> return TmpResultType.RSString
                    else -> {
                        println("guess result type to be TmpResultType.RSString (${tmp})")
                        return TmpResultType.RSString
                    }
                }
            }
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.BOUND -> return TmpResultType.RSBoolean
                    BuiltInFunctions.RAND -> return TmpResultType.RSInteger
                    BuiltInFunctions.ABS -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.CEIL -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.FLOOR -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.ROUND -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.sameTerm -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isIRI -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isURI -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isBLANK -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isLITERAL -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isNUMERIC -> return TmpResultType.RSBoolean
                    BuiltInFunctions.Exists -> return TmpResultType.RSBoolean
                    BuiltInFunctions.NotExists -> return TmpResultType.RSBoolean
                    BuiltInFunctions.DAY -> return TmpResultType.RSInteger
                    BuiltInFunctions.MONTH -> return TmpResultType.RSInteger
                    BuiltInFunctions.YEAR -> return TmpResultType.RSInteger
                    BuiltInFunctions.HOURS -> return TmpResultType.RSInteger
                    BuiltInFunctions.MINUTES -> return TmpResultType.RSInteger
                    BuiltInFunctions.SECONDS -> return TmpResultType.RSInteger
                    BuiltInFunctions.STRLEN -> return TmpResultType.RSInteger
                    else -> return TmpResultType.RSString
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} getResultType ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperDouble2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Double {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSDouble && typeB == TmpResultType.RSDouble -> {
                val a = evaluateHelperDouble(resultSet, resultRow, left)
                val b = evaluateHelperDouble(resultSet, resultRow, right)
                when (node) {
                    is ASTAddition -> return a + b
                    is ASTMultiplication -> return a * b
                    is ASTDivision -> return a / b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble2 ${node::class.simpleName}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun evaluateHelperDateTime(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): DateTime {
        when (node) {
            is ASTVar -> {
                return DateTime(resultSet.getValue(resultRow[resultSet.createVariable(node.name)]))
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDateTime ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperDouble(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Double {
        when (node) {
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeDouble.length)
                println("XXD1 " + tmp)
                println("XXD2 " + tmp2)
                return tmp2.toDouble()
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperDouble2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.ABS -> return abs(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.CEIL -> return ceil(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.FLOOR -> return floor(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.ROUND -> return evaluateHelperDouble(resultSet, resultRow, node.children[0]).roundToInt().toDouble()
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperInteger2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Int {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTAddition -> return a + b
                    is ASTMultiplication -> return a * b
                    is ASTDivision -> return a / b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger2 ${node::class.simpleName}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun evaluateHelperInteger(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Int {
        when (node) {
            is ASTInteger -> return node.value
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeInteger.length)
                println("XXI" + tmp2)
                return tmp2.toInt()
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperInteger2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                val typeA = getResultType(resultSet, resultRow, node.children[0])
                when (node.function) {
                    BuiltInFunctions.ABS -> return abs(evaluateHelperInteger(resultSet, resultRow, node.children[0])).toInt()
                    BuiltInFunctions.CEIL -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.FLOOR -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.ROUND -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.DAY -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).day
                    BuiltInFunctions.MONTH -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).month
                    BuiltInFunctions.YEAR -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).year
                    BuiltInFunctions.HOURS -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).hours
                    BuiltInFunctions.MINUTES -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).minutes
                    BuiltInFunctions.SECONDS -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).seconds
                    BuiltInFunctions.STRLEN -> return extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0])).length
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperBoolean2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Boolean {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSBoolean && typeB == TmpResultType.RSBoolean -> {
                val a = evaluateHelperBoolean(resultSet, resultRow, left)
                val b = evaluateHelperBoolean(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> {
                        println("ASTEQ a:: ${a} ${b} ${a == b}")
                        return a == b
                    }
                    is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }
            }
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> {
                        println("ASTEQ b:: ${a} ${b} ${a == b}")
                        return a == b
                    }
                    is ASTGEQ -> return a >= b
                    is ASTLEQ -> return a <= b
                    is ASTGT -> return a > b
                    is ASTLT -> return a < b
                    is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }

            }
            typeA == TmpResultType.RSDouble && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperDouble(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> {
                        println("ASTEQ c:: ${a} ${b} ${a == b.toDouble()}")
                        return a == b.toDouble()
                    }
                    is ASTGEQ -> return a >= b
                    is ASTLEQ -> return a <= b
                    is ASTGT -> return a > b
                    is ASTLT -> return a < b
                    is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }

            }
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSDouble -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperDouble(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> {
                        println("ASTEQ d:: ${a} ${b} ${a.toDouble() == b}")
                        return a.toDouble() == b
                    }
                    is ASTGEQ -> return a >= b
                    is ASTLEQ -> return a <= b
                    is ASTGT -> return a > b
                    is ASTLT -> return a < b
                    is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }

            }
            typeA == TmpResultType.RSString && typeB == TmpResultType.RSString -> {
                val a = evaluateHelperString(resultSet, resultRow, left)
                val b = evaluateHelperString(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> return a == b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }
            }
            node is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun extractStringFromLiteral(literal: String): String {
        println("extractStringFromLiteral ${literal} ${literal.endsWith(dataTypeString)} ${!literal.endsWith(">")}")
        when {
            literal.endsWith(dataTypeString) -> return literal.substring(1, literal.length - 1 - dataTypeString.length)
            !literal.endsWith("\"") && !literal.endsWith(">") -> return literal.substring(1, literal.lastIndexOf("@") - 1)
            else -> return literal.substring(1, literal.length - 1)
        }
    }

    private fun extractLanguageFromLiteral(literal: String): String {
        println("extractLanguageFromLiteral ${literal} ${literal.endsWith(dataTypeString)} ${!literal.endsWith(">")}")
        when {
            !literal.endsWith("\"") && !literal.endsWith(">") -> return literal.substring(literal.lastIndexOf("@") + 1, literal.length)
            else -> return ""
        }
    }

    private fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Boolean {
        when (node) {
            is ASTBooleanLiteral -> return node.value
            is ASTOr -> {
                var res = false
                for (n in node.children) {
                    res = res || evaluateHelperBoolean(resultSet, resultRow, n)
                }
                return res
            }
            is ASTAnd -> {
                var res = true
                for (n in node.children) {
                    res = res && evaluateHelperBoolean(resultSet, resultRow, n)
                }
                return res
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperBoolean2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.isNUMERIC -> {
                        val typeA = getResultType(resultSet, resultRow, node.children[0])
                        return typeA == TmpResultType.RSInteger || typeA == TmpResultType.RSDouble
                    }
                    BuiltInFunctions.LANGMATCHES -> {
                        val a = extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0]))
                        val b = extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1]))
                        return a == b
                    }
                    BuiltInFunctions.STRENDS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0]))
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1]))
                        println("STRENDS $a $b")
                        return a.endsWith(b)
                    }
                    BuiltInFunctions.STRSTARTS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0]))
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1]))
                        return a.startsWith(b)
                    }
                    BuiltInFunctions.CONTAINS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0]))
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1]))
                        return a.contains(b)
                    }
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperString(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): String {
        when (getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSBoolean -> return "\"" + evaluateHelperBoolean(resultSet, resultRow, node) + "\"" + dataTypeBoolean
            TmpResultType.RSInteger -> return "\"" + evaluateHelperInteger(resultSet, resultRow, node) + "\"" + dataTypeInteger
            TmpResultType.RSDouble -> {
                val res = evaluateHelperDouble(resultSet, resultRow, node).toString()
                when {
                    res.endsWith(".0") -> return "\"" + res.substring(0, res.length - 2) + "\"" + dataTypeDouble
                    res.contains('.') -> return "\"" + res + "\"" + dataTypeDouble
                    else -> return "\"" + res + ".0\"" + dataTypeDouble
                }
            }
        }
        when (node) {
            is ASTIri -> return node.iri
            is ASTLiteral -> return node.delimiter + node.content + node.delimiter
            is ASTVar -> return resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.IF -> {
                        if (evaluateHelperBoolean(resultSet, resultRow, node.children[0]))
                            return evaluateHelperString(resultSet, resultRow, node.children[1])
                        else
                            return evaluateHelperString(resultSet, resultRow, node.children[2])
                    }
                    BuiltInFunctions.IRI -> return evaluateHelperString(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.TZ -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).getTZ()
                    BuiltInFunctions.TIMEZONE -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0]).getTimeZone()
                    BuiltInFunctions.LCASE -> {
                        var tmp = evaluateHelperString(resultSet, resultRow, node.children[0])
                        println("LCASE $tmp")
                        if (tmp.endsWith("\""))
                            return tmp.toLowerCase()
                        if (tmp.endsWith(dataTypeString)) {
                            return tmp.substring(0, tmp.length - dataTypeString.length).toLowerCase() + dataTypeString
                        }
                        val idx = tmp.lastIndexOf("@")
                        return tmp.substring(0, idx).toLowerCase() + tmp.substring(idx, tmp.length)
                    }
                    BuiltInFunctions.UCASE -> {
                        var tmp = evaluateHelperString(resultSet, resultRow, node.children[0])
                        println("UCASE $tmp")
                        if (tmp.endsWith("\""))
                            return tmp.toUpperCase()
                        if (tmp.endsWith(dataTypeString)) {
                            return tmp.substring(0, tmp.length - dataTypeString.length).toUpperCase() + dataTypeString
                        }
                        val idx = tmp.lastIndexOf("@")
                        return tmp.substring(0, idx).toUpperCase() + tmp.substring(idx, tmp.length)
                    }
                    BuiltInFunctions.CONCAT -> return evaluateHelperString(resultSet, resultRow, node.children[0]) + evaluateHelperString(resultSet, resultRow, node.children[1])
                    BuiltInFunctions.LANG -> return extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.STR -> return evaluateHelperString(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.MD5 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0])).encodeToByteArray().md5().toHexString() + "\""
                    BuiltInFunctions.SHA1 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0])).encodeToByteArray().sha1().toHexString() + "\""
                    BuiltInFunctions.SHA256 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0])).encodeToByteArray().sha256().toHexString() + "\""
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperString ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperString ${node::class.simpleName}")
        }
    }

    fun evaluate(resultSet: ResultSet, resultRow: ResultRow): String {
        println("resultRow:: " + resultRow)
        return evaluateHelperString(resultSet, resultRow, child)
    }

    fun evaluateBoolean(resultSet: ResultSet, resultRow: ResultRow): Boolean {
        println("resultRow:: " + resultRow)
        return evaluateHelperBoolean(resultSet, resultRow, child)
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${child.toString("${indentation}\t")}"
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
