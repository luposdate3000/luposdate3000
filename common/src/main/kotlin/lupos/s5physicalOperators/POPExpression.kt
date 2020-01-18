package lupos.s5physicalOperators

import lupos.s1buildSyntaxTree.sparql1_1.Aggregation
import lupos.s1buildSyntaxTree.sparql1_1.ASTAddition
import lupos.s1buildSyntaxTree.sparql1_1.ASTAggregation
import lupos.s1buildSyntaxTree.sparql1_1.ASTAnd
import lupos.s1buildSyntaxTree.sparql1_1.ASTBinaryOperationFixedName
import lupos.s1buildSyntaxTree.sparql1_1.ASTBooleanLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTBuiltInCall
import lupos.s1buildSyntaxTree.sparql1_1.ASTDecimal
import lupos.s1buildSyntaxTree.sparql1_1.ASTDivision
import lupos.s1buildSyntaxTree.sparql1_1.ASTDouble
import lupos.s1buildSyntaxTree.sparql1_1.ASTEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTGEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTGT
import lupos.s1buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s1buildSyntaxTree.sparql1_1.ASTIri
import lupos.s1buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s1buildSyntaxTree.sparql1_1.ASTLiteral
import lupos.s1buildSyntaxTree.sparql1_1.ASTLT
import lupos.s1buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s1buildSyntaxTree.sparql1_1.ASTNode
import lupos.s1buildSyntaxTree.sparql1_1.ASTOr
import lupos.s1buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s1buildSyntaxTree.sparql1_1.ASTVar
import lupos.s1buildSyntaxTree.sparql1_1.BuiltInFunctions
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

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
    RSBoolean, RSString, RSInteger, RSDecimal, RSDateTime, RSUndefined, RSDouble
}

enum class TmpAggregateMode {
    AMCollect, AMResult
}

class EvaluateNumber<T : Number>(val expression: POPExpression, val resultType: TmpResultType, val dataType: String) {

    fun myAbs(a: T): T {
        return when (a) {
            is Double -> abs(a) as T
            is Int -> abs(a) as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperPlus")
        }
    }

    fun myCeil(a: T): T {
        return when (a) {
            is Double -> ceil(a) as T
            is Int -> a as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperPlus")
        }
    }

    fun myFloor(a: T): T {
        return when (a) {
            is Double -> floor(a) as T
            is Int -> a as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperPlus")
        }
    }

    fun myRound(a: Any): T {
        return when (a) {
            is Double -> a.roundToInt().toDouble() as T
            is Int -> a as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} round")
        }
    }

    operator fun T.plus(b: T): T {
        return when (this) {
            is Double -> (this + (b as Double)) as T
            is Int -> (this + (b as Int)) as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperPlus")
        }
    }

    operator fun T.minus(b: T): T {
        return when (this) {
            is Double -> (this - (b as Double)) as T
            is Int -> (this - (b as Int)) as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperMinus")
        }
    }

    operator fun T.times(b: T): T {
        return when (this) {
            is Double -> (this * (b as Double)) as T
            is Int -> (this * (b as Int)) as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperMultiplication")
        }
    }

    operator fun T.div(b: T): T {
        return when (this) {
            is Double -> {
                if ((b as Double) == 0.0)
                    throw ArithmeticException("the output of ${this::class.simpleName} divide by zero")
                (this / (b as Double)) as T
            }
            is Int -> {
                if ((b as Int) == 0)
                    throw ArithmeticException("the output of ${this::class.simpleName} divide by zero")
                (this / (b as Int)) as T
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperDivision")
        }
    }

    operator fun T.compareTo(b: T): Int {
        return when (this) {
            is Double -> {
                if (this < (b as Double))
                    -1
                else if (this > (b as Double))
                    return 1
                else
                    return 0
            }
            is Int -> {
                if (this < (b as Int))
                    -1
                else if (this > (b as Int))
                    return 1
                else
                    return 0
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperDivision")
        }
    }

    private fun helperNull(): T {
        val a: T? = null
        return when (a) {
            is Double? -> (0.0) as T
            is Int? -> (0) as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperNull")
        }
    }

    private fun helperToT(a: Number): T {
        val b: T? = null
        return when (b) {
            is Double? -> a.toDouble() as T
            is Int? -> a.toInt() as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} helperToT")
        }
    }

    private fun fromString(a: String): T {
        val b: T? = null
        return when (b) {
            is Double? -> a.toDouble() as T
            is Int? -> a.toInt() as T
            else -> throw UnsupportedOperationException("${this::class.simpleName} fromString")
        }
    }

    fun evaluateChildTyped(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): T {
        return when (expression.getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSInteger -> helperToT(expression.evaluateInteger.evaluateHelper(resultSet, resultRow, node))
            TmpResultType.RSDecimal -> helperToT(expression.evaluateDecimal.evaluateHelper(resultSet, resultRow, node))
            TmpResultType.RSDouble -> helperToT(expression.evaluateDouble.evaluateHelper(resultSet, resultRow, node))
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateChildTyped ${node::class.simpleName}")
        }
    }

    fun evaluateHelper2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): T {
        val a = evaluateChildTyped(resultSet, resultRow, node.children[0])
        val b = evaluateChildTyped(resultSet, resultRow, node.children[1])
        when (node) {
            is ASTAddition -> return a + b
            is ASTMultiplication -> return a * b
            is ASTDivision -> return a / b
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelper2 ${node::class.simpleName}")
        }
    }

    fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Boolean {
        val a: T = evaluateChildTyped(resultSet, resultRow, node.children[0])
        val b: T = evaluateChildTyped(resultSet, resultRow, node.children[1])
        when (node) {
            is ASTEQ -> return a == b
            is ASTGEQ -> return a >= b
            is ASTLEQ -> return a <= b
            is ASTGT -> return a > b
            is ASTLT -> return a < b
            else -> throw ArithmeticException("the output of ${node::class.simpleName} is not a boolean")
        }
    }

    fun evaluateHelper(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): T {
        when (node) {
            is ASTAggregation -> {
                if (expression.aggregateTmpType[node.uuid] == null || expression.aggregateTmpType[node.uuid] == resultType)
                    expression.aggregateTmpType[node.uuid] = resultType
                else if (expression.isAUpgradeableToB(expression.aggregateTmpType[node.uuid]!!, resultType)) {
                    expression.aggregateTmpType[node.uuid] = resultType
                    if (expression.aggregateTmp[node.uuid] != null)
                        expression.aggregateTmp[node.uuid] = helperToT(expression.aggregateTmp[node.uuid]!!)
                }
                if (node.type == Aggregation.COUNT)
                    return helperToT(expression.aggregateCount)
                val childValue: T = if (expression.aggregateMode == TmpAggregateMode.AMCollect)
                    evaluateChildTyped(resultSet, resultRow, node.children[0])
                else
                    helperNull()
                if (node.type == Aggregation.SAMPLE) {
                    expression.aggregateTmp[node.uuid] = childValue
                    return childValue
                }
                var last: T? = expression.aggregateTmp[node.uuid] as T?
                if (last == null)
                    last = helperNull()
                if (node.distinct)
                    throw UnsupportedOperationException("${this::class.simpleName} evaluateHelper ${node::class.simpleName} ${node.type} DISTINCT")
                if (expression.aggregateMode == TmpAggregateMode.AMCollect) {
                    when (node.type) {
                        Aggregation.AVG -> {
                            expression.aggregateTmp[node.uuid] = last + childValue / helperToT(expression.aggregateCount)
                        }
                        Aggregation.MIN -> {
                            if (expression.aggregateTmp[node.uuid] == null || childValue < last) {
                                expression.aggregateTmp[node.uuid] = childValue
                                expression.aggregateTmpTypeUsed[node.uuid] = expression.getResultType(resultSet, resultRow, node.children[0])
                            }
                        }
                        Aggregation.MAX -> {
                            if (expression.aggregateTmp[node.uuid] == null || childValue > last) {
                                expression.aggregateTmp[node.uuid] = childValue
                                expression.aggregateTmpTypeUsed[node.uuid] = expression.getResultType(resultSet, resultRow, node.children[0])
                            }
                        }
                        Aggregation.SUM -> {
                            expression.aggregateTmp[node.uuid] = last + childValue
                        }
                        else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelper ${node::class.simpleName} ${node.type}")
                    }
                }
                val res: Number? = expression.aggregateTmp[node.uuid] as Number?
                if (res == null)
                    return helperNull()
                return helperToT(res)
            }
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataType.length)
                return fromString(tmp2)
            }
            is ASTInteger -> return helperToT(node.value)
            is ASTDecimal -> return helperToT(node.toDouble())
            is ASTDouble -> return helperToT(node.toDouble())
            is ASTBinaryOperationFixedName -> return evaluateHelper2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.ABS -> return myAbs(evaluateChildTyped(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.CEIL -> return myCeil(evaluateChildTyped(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.FLOOR -> return myFloor(evaluateChildTyped(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.ROUND -> return myRound(evaluateChildTyped(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.DAY -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).day)
                    BuiltInFunctions.MONTH -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).month)
                    BuiltInFunctions.YEAR -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).year)
                    BuiltInFunctions.HOURS -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).hours)
                    BuiltInFunctions.MINUTES -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).minutes)
                    BuiltInFunctions.SECONDS -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0]).seconds)
                    BuiltInFunctions.STRLEN -> return helperToT(expression.extractStringFromLiteral(expression.evaluateHelperString(resultSet, resultRow, node.children[0])).length)
                    BuiltInFunctions.IF -> {
                        if (expression.aggregateTmp[node.uuid] == 1)
                            return evaluateHelper(resultSet, resultRow, node.children[1])
                        else
                            return evaluateHelper(resultSet, resultRow, node.children[2])
                    }
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelper ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelper ${node::class.simpleName}")
        }
    }


}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class POPExpression : OPBase {

    var aggregateMode = TmpAggregateMode.AMCollect

    private val dataTypeInteger = "^^<http://www.w3.org/2001/XMLSchema#integer>"
    private val dataTypeDecimal = "^^<http://www.w3.org/2001/XMLSchema#decimal>"
    private val dataTypeDouble = "^^<http://www.w3.org/2001/XMLSchema#double>"
    private val dataTypeBoolean = "^^<http://www.w3.org/2001/XMLSchema#boolean>"
    private val dataTypeDateTime = "^^<http://www.w3.org/2001/XMLSchema#dateTime>"
    private val dataTypeString = "^^<http://www.w3.org/2001/XMLSchema#string>"
    val evaluateInteger = EvaluateNumber<Int>(this, TmpResultType.RSInteger, dataTypeInteger)
    val evaluateDecimal = EvaluateNumber<Double>(this, TmpResultType.RSDecimal, dataTypeDecimal)
    val evaluateDouble = EvaluateNumber<Double>(this, TmpResultType.RSDouble, dataTypeDouble)

    var aggregateCount = 0
    val aggregateTmpTypeUsed = mutableMapOf<Int, TmpResultType>()//min and max should return the original type and not something casted
    val aggregateTmpType = mutableMapOf<Int, TmpResultType>()
    val aggregateTmp = mutableMapOf<Int, Number>()
    private val aggregateTmpString = mutableMapOf<Int, String>()
    var child: ASTNode

    constructor(child: ASTNode) {
        this.child = child
    }

    fun isAUpgradeableToB(a: TmpResultType, b: TmpResultType): Boolean {
        if (a == b)
            return true
        when (a) {
            TmpResultType.RSInteger -> {
                when (b) {
                    TmpResultType.RSDecimal, TmpResultType.RSDouble -> return true
                }
            }
            TmpResultType.RSDecimal -> {
                when (b) {
                    TmpResultType.RSDouble -> return true
                }
            }
        }
        return false
    }

    private fun commonDatatype(aType: TmpResultType, bType: TmpResultType): TmpResultType {
        if (aType == bType)
            return aType
        if (isAUpgradeableToB(aType, bType))
            return bType
        if (isAUpgradeableToB(bType, aType))
            return aType
        throw ArithmeticException("incompatible datatypes $aType $bType")
    }

    private fun commonDatatype(resultSet: ResultSet, resultRow: ResultRow, nodeA: ASTNode, nodeB: ASTNode): TmpResultType {
        val aType = getResultType(resultSet, resultRow, nodeA)
        val bType = getResultType(resultSet, resultRow, nodeB)
        return commonDatatype(aType, bType)
    }

    fun getResultType(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): TmpResultType {
        when (node) {
            is ASTAggregation -> {
                if (aggregateMode == TmpAggregateMode.AMResult) {
                    val tmp = aggregateTmpType[node.uuid]
                    if (tmp == null)
                        return TmpResultType.RSUndefined
                    val tmp2 = aggregateTmpTypeUsed[node.uuid]
                    if (tmp2 != null && tmp != tmp2)
                        return tmp2
                    return tmp
                }
                if (node.type == Aggregation.COUNT)
                    return TmpResultType.RSInteger
                var newType = getResultType(resultSet, resultRow, node.children[0])
                if (node.type == Aggregation.AVG)
                    newType = commonDatatype(newType, TmpResultType.RSDecimal)
                val oldType = aggregateTmpType[node.uuid]
                if (oldType == null)
                    return newType
                return commonDatatype(oldType, newType)
            }
            is ASTUndef -> return TmpResultType.RSUndefined
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
            is ASTDecimal -> return TmpResultType.RSDecimal
            is ASTDouble -> return TmpResultType.RSDouble
            is ASTAddition -> return commonDatatype(resultSet, resultRow, node.children[0], node.children[1])
            is ASTMultiplication -> return commonDatatype(resultSet, resultRow, node.children[0], node.children[1])
            is ASTDivision -> return commonDatatype(commonDatatype(resultSet, resultRow, node.children[0], node.children[1]), TmpResultType.RSDecimal)
            is ASTVar -> {
                if (!resultSet.getVariableNames().contains(node.name))
                    return TmpResultType.RSUndefined
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                when {
                    tmp.endsWith(dataTypeInteger) -> return TmpResultType.RSInteger
                    tmp.endsWith(dataTypeDecimal) -> return TmpResultType.RSDecimal
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
                    BuiltInFunctions.IF -> {
                        val condition: Boolean = evaluateHelperBoolean(resultSet, resultRow, node.children[0])
                        if (condition) {
                            aggregateTmp[node.uuid] = 1
                            return getResultType(resultSet, resultRow, node.children[1])
                        } else {
                            aggregateTmp[node.uuid] = 0
                            return getResultType(resultSet, resultRow, node.children[2])
                        }
                    }
                    BuiltInFunctions.CONTAINS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.STRSTARTS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.STRENDS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.LANGMATCHES -> return TmpResultType.RSBoolean
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

    fun evaluateHelperDateTime(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): DateTime {
        when (node) {
            is ASTVar -> {
                return DateTime(resultSet.getValue(resultRow[resultSet.createVariable(node.name)]))
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDateTime ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperBoolean2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Boolean {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSUndefined || typeB == TmpResultType.RSUndefined -> return typeA == typeB
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
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSDouble) -> return evaluateDouble.evaluateHelperBoolean(resultSet, resultRow, node)
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSDecimal) -> return evaluateDecimal.evaluateHelperBoolean(resultSet, resultRow, node)
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSInteger) -> return evaluateInteger.evaluateHelperBoolean(resultSet, resultRow, node)
            typeA == TmpResultType.RSString && typeB == TmpResultType.RSString -> {
                val a = evaluateHelperString(resultSet, resultRow, left)
                val b = evaluateHelperString(resultSet, resultRow, right)
                println("XXXY <$a> <$b>")
                when (node) {
                    is ASTEQ -> return a == b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }
            }
            node is ASTDivision -> throw ArithmeticException("the output of ASTDivision is not a boolean")
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    fun extractStringFromLiteral(literal: String): String {
        println("extractStringFromLiteral ${literal} ${literal.endsWith(dataTypeString)} ${!literal.endsWith(">")}")
        when {
            literal.endsWith(dataTypeString) -> return literal.substring(1, literal.length - 1 - dataTypeString.length)
            !literal.endsWith("\"") && !literal.endsWith(">") -> return literal.substring(1, literal.lastIndexOf("@") - 1)
            else -> return literal.substring(1, literal.length - 1)
        }
    }

    fun extractLanguageFromLiteral(literal: String): String {
        println("extractLanguageFromLiteral ${literal} ${literal.endsWith(dataTypeString)} ${!literal.endsWith(">")}")
        when {
            !literal.endsWith("\"") && !literal.endsWith(">") -> return literal.substring(literal.lastIndexOf("@") + 1, literal.length)
            else -> return ""
        }
    }

    fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Boolean {
        when (node) {
            is ASTBooleanLiteral -> return node.value
            is ASTOr -> {
                var res = false
                for (n in node.children) {
                    res = res || evaluateHelperBoolean(resultSet, resultRow, n)
                }
                return res
            }
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeBoolean.length)
                return tmp2.toBoolean()
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
                    BuiltInFunctions.IF -> {
                        if (aggregateTmp[node.uuid] == 1)
                            return evaluateHelperBoolean(resultSet, resultRow, node.children[1])
                        else
                            return evaluateHelperBoolean(resultSet, resultRow, node.children[2])
                    }
                    BuiltInFunctions.isNUMERIC -> {
                        val typeA = getResultType(resultSet, resultRow, node.children[0])
                        return typeA == TmpResultType.RSInteger || typeA == TmpResultType.RSDecimal
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

    fun evaluateHelperString(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): String {
        when (getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSBoolean -> return "\"" + evaluateHelperBoolean(resultSet, resultRow, node) + "\"" + dataTypeBoolean
            TmpResultType.RSInteger -> return "\"" + evaluateInteger.evaluateHelper(resultSet, resultRow, node) + "\"" + dataTypeInteger
            TmpResultType.RSUndefined -> return resultSet.getUndefValue()
            TmpResultType.RSDecimal -> {
                val tmp = evaluateDecimal.evaluateHelper(resultSet, resultRow, node).toString()
                if (tmp.contains(".") || tmp.contains("e") || tmp.contains("E"))
                    return "\"" + tmp + "\"" + dataTypeDecimal
                return "\"" + tmp + ".0\"" + dataTypeDecimal
            }
            TmpResultType.RSDouble -> return "\"" + evaluateDouble.evaluateHelper(resultSet, resultRow, node) + "\"" + dataTypeDouble
        }
        when (node) {
            is ASTAggregation -> {
                throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperString ${node::class.simpleName} ${node.type} ${node.distinct}")
            }
            is ASTIri -> return "<" + node.iri + ">"
            is ASTLiteral -> return node.delimiter + node.content + node.delimiter
            is ASTVar -> return resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
            is ASTAddition -> throw ArithmeticException("ASTAddition can not be applied to String-Datatype")
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.IF -> {
                        if (aggregateTmp[node.uuid] == 1)
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
                    BuiltInFunctions.CONCAT -> {
                        val a = evaluateHelperString(resultSet, resultRow, node.children[0])
                        val b = evaluateHelperString(resultSet, resultRow, node.children[1])
                        val aas = extractStringFromLiteral(a)
                        val bbs = extractStringFromLiteral(b)
                        if (a.endsWith(dataTypeString) && b.endsWith(dataTypeString))
                            return "\"" + aas + bbs + "\"" + dataTypeString
                        if (a.endsWith(">") && !a.endsWith(dataTypeString))
                            return resultSet.getUndefValue()
                        if (b.endsWith(">") && !b.endsWith(dataTypeString))
                            return resultSet.getUndefValue()
                        if (a.endsWith(">") || b.endsWith(">"))
                            return "\"" + aas + bbs + "\""
                        val aa = extractLanguageFromLiteral(a)
                        val bb = extractLanguageFromLiteral(b)
                        if (aa != bb || aa == "")
                            return "\"" + aas + bbs + "\""
                        return "\"" + aas + bbs + "\"@" + aa
                    }
                    BuiltInFunctions.LANG -> return "\"" + extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0])) + "\""
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

    fun evaluateBoolean(resultSet: ResultSet, resultRow: ResultRow): Boolean {
        println("resultRow:: " + resultRow)
        if (getResultType(resultSet, resultRow, child) != TmpResultType.RSBoolean)
            throw UnsupportedOperationException("${this::class.simpleName} evaluateBoolean ${child::class.simpleName}")
        return evaluateHelperBoolean(resultSet, resultRow, child)
    }

    fun evaluate(resultSet: ResultSet, resultRow: ResultRow): String {
        println("resultRow:: " + resultRow)
        return evaluateHelperString(resultSet, resultRow, child)
    }

    fun evaluate(resultSet: ResultSet, resultRows: List<ResultRow>): String {
        println("resultRow:: " + resultRows)
        aggregateMode = TmpAggregateMode.AMCollect
        aggregateCount = resultRows.count()
        aggregateTmp.clear()
        aggregateTmpType.clear()
        aggregateTmpTypeUsed.clear()
        aggregateTmpString.clear()
        for (resultRow in resultRows) {
            evaluate(resultSet, resultRow)
        }
        aggregateMode = TmpAggregateMode.AMResult
        return evaluate(resultSet, resultSet.createResultRow())
    }

    fun getAllVariablesInChildren(node: ASTNode): List<String> {
        val res = mutableListOf<String>()
        if (node is ASTVar)
            res.add(node.name)
        for (c in node.children)
            res.addAll(getAllVariablesInChildren(c))
        return res
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getAllVariablesInChildren(child)
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
