package lupos.s09physicalOperators.noinput

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


val localbnode = ThreadSafeUuid()

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

    constructor() {
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

    constructor(str: String) {
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
            is Double -> abs(a).toDouble() as T
            is Int -> abs(a) as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperPlus")
        }
    }

    fun myCeil(a: T): T {
        return when (a) {
            is Double -> ceil(a) as T
            is Int -> a
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperPlus")
        }
    }

    fun myFloor(a: T): T {
        return when (a) {
            is Double -> floor(a) as T
            is Int -> a
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperPlus")
        }
    }

    fun myRound(a: Any): T {
        return when (a) {
            is Double -> a.roundToInt().toDouble() as T
            is Int -> a as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} round")
        }
    }

    operator fun T.plus(b: T): T {
        return when (this) {
            is Double -> (this + (b as Double)) as T
            is Int -> (this + (b as Int)) as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperPlus")
        }
    }

    operator fun T.minus(b: T): T {
        return when (this) {
            is Double -> (this - (b as Double)) as T
            is Int -> (this - (b as Int)) as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperMinus")
        }
    }

    operator fun T.times(b: T): T {
        return when (this) {
            is Double -> (this * (b as Double)) as T
            is Int -> (this * (b as Int)) as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperMultiplication")
        }
    }

    operator fun T.div(b: T): T {
        return when (this) {
            is Double -> {
                if ((b as Double) == 0.0)
                    throw ArithmeticException("the output of ${classNameToString(this)} divide by zero")
                (this / (b as Double)) as T
            }
            is Int -> {
                if ((b as Int) == 0)
                    throw ArithmeticException("the output of ${classNameToString(this)} divide by zero")
                (this / (b as Int)) as T
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperDivision")
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
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperDivision")
        }
    }

    private fun helperNull(): T {
        val a: T? = null
        return when (a) {
            is Double? -> (0.0) as T
            is Int? -> (0) as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperNull")
        }
    }

    private fun helperToT(a: Number): T {
        val b: T? = null
        return when (b) {
            is Double? -> a.toDouble() as T
            is Int? -> a.toInt() as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} helperToT")
        }
    }

    private fun fromString(a: String): T {
        val b: T? = null
        return when (b) {
            is Double? -> a.toDouble() as T
            is Int? -> a.toInt() as T
            else -> throw UnsupportedOperationException("${classNameToString(this)} fromString")
        }
    }

    fun evaluateChildTyped(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): T {
        return when (expression.getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSInteger -> helperToT(expression.evaluateInteger.evaluateHelper(resultSet, resultRow, node))
            TmpResultType.RSDecimal -> helperToT(expression.evaluateDecimal.evaluateHelper(resultSet, resultRow, node))
            TmpResultType.RSDouble -> helperToT(expression.evaluateDouble.evaluateHelper(resultSet, resultRow, node))
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateChildTyped ${classNameToString(node)}")
        }
    }

    fun evaluateHelper2(resultSet: ResultSet, resultRow: ResultRow, node: AOPBinaryOperationFixedName): T {
        val a = evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase)
        val b = evaluateChildTyped(resultSet, resultRow, node.children[1] as AOPBase)
        when (node) {
            is AOPAddition -> return a + b
            is AOPMultiplication -> return a * b
            is AOPDivision -> return a / b
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelper2 ${classNameToString(node)}")
        }
    }

    fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): Boolean {
        val a: T = evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase)
        val b: T = evaluateChildTyped(resultSet, resultRow, node.children[1] as AOPBase)
        when (node) {
            is AOPEQ -> return a == b
            is AOPNEQ -> return a == b
            is AOPGEQ -> return a >= b
            is AOPLEQ -> return a <= b
            is AOPGT -> return a > b
            is AOPLT -> return a < b
            else -> throw ArithmeticException("the output of ${classNameToString(node)} is not a boolean")
        }
    }

    fun evaluateHelper(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): T {
        when (node) {
            is AOPAggregation -> {
                if (node.distinct)
                    throw UnsupportedOperationException("${classNameToString(this)} evaluateHelper ${classNameToString(node)} ${node.type} DISTINCT")
                if (expression.aggregateTmpType[node.uuid] == null || expression.aggregateTmpType[node.uuid] == resultType)
                    expression.aggregateTmpType[node.uuid] = resultType
                else if (expression.isAUpgradeableToB(expression.aggregateTmpType[node.uuid]!!, resultType)) {
                    expression.aggregateTmpType[node.uuid] = resultType
                    if (expression.aggregateTmp[node.uuid] != null)
                        expression.aggregateTmp[node.uuid] = helperToT(expression.aggregateTmp[node.uuid]!!)
                }
                if (node.type == Aggregation.COUNT) {
                    require(expression.getResultType(resultSet, resultRow, node) == TmpResultType.RSInteger)
                    return helperToT(expression.aggregateCount)
                }
                val childValue: T = if (expression.aggregateMode == TmpAggregateMode.AMCollect)
                    evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase)
                else
                    helperNull()
                if (node.type == Aggregation.SAMPLE) {
                    if (expression.aggregateTmp[node.uuid] == null && expression.aggregateMode == TmpAggregateMode.AMCollect) {
                        expression.aggregateTmpTypeUsed[node.uuid] = expression.getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                        expression.aggregateTmp[node.uuid] = childValue
                    }
                    val res: Number? = expression.aggregateTmp[node.uuid]
                    if (res == null)
                        return helperNull()
                    return helperToT(res)
                }
                var lAOP: T? = expression.aggregateTmp[node.uuid] as T?
                if (lAOP == null)
                    lAOP = helperNull()
                if (expression.aggregateMode == TmpAggregateMode.AMCollect) {
                    when (node.type) {
                        Aggregation.AVG -> {
                            expression.aggregateTmp[node.uuid] = lAOP + childValue / helperToT(expression.aggregateCount)
                        }
                        Aggregation.MIN -> {
                            if (expression.aggregateTmp[node.uuid] == null || childValue < lAOP) {
                                expression.aggregateTmp[node.uuid] = childValue
                                expression.aggregateTmpTypeUsed[node.uuid] = expression.getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                            }
                        }
                        Aggregation.MAX -> {
                            if (expression.aggregateTmp[node.uuid] == null || childValue > lAOP) {
                                expression.aggregateTmp[node.uuid] = childValue
                                expression.aggregateTmpTypeUsed[node.uuid] = expression.getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                            }
                        }
                        Aggregation.SUM -> {
                            expression.aggregateTmp[node.uuid] = lAOP + childValue
                        }
                        else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelper ${classNameToString(node)} ${node.type}")
                    }
                }
                val res: Number? = expression.aggregateTmp[node.uuid]
                if (res == null)
                    return helperNull()
                return helperToT(res)
            }
            is AOPVariable -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])!!
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataType.length)
                return fromString(tmp2)
            }
            is AOPInteger -> return helperToT(node.value)
            is AOPDecimal -> return helperToT(node.value)
            is AOPDouble -> return helperToT(node.value)
            is AOPBinaryOperationFixedName -> return evaluateHelper2(resultSet, resultRow, node)
            is AOPBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.ABS -> return myAbs(evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase))
                    BuiltInFunctions.CEIL -> return myCeil(evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase))
                    BuiltInFunctions.FLOOR -> return myFloor(evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase))
                    BuiltInFunctions.ROUND -> return myRound(evaluateChildTyped(resultSet, resultRow, node.children[0] as AOPBase))
                    BuiltInFunctions.DAY -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).day)
                    BuiltInFunctions.MONTH -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).month)
                    BuiltInFunctions.YEAR -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).year)
                    BuiltInFunctions.HOURS -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).hours)
                    BuiltInFunctions.MINUTES -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).minutes)
                    BuiltInFunctions.SECONDS -> return helperToT(expression.evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).seconds)
                    BuiltInFunctions.STRLEN -> return helperToT(expression.extractStringFromLiteral(expression.evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!).length)
                    BuiltInFunctions.IF -> {
                        if (expression.aggregateTmp[node.uuid] == 1)
                            return evaluateHelper(resultSet, resultRow, node.children[1] as AOPBase)
                        else
                            return evaluateHelper(resultSet, resultRow, node.children[2] as AOPBase)
                    }
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelper ${classNameToString(node)} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelper ${classNameToString(node)}")
        }
    }


}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class POPExpression : LOPBase {
    val dictionary: ResultSetDictionary
    override val children: Array<OPBase> = arrayOf()
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

    var aggregateCount: Int = 0
    val aggregateTmpTypeUsed = mutableMapOf<Long, TmpResultType>()//min and max should return the original type and not something cAOPed
    val aggregateTmpType = mutableMapOf<Long, TmpResultType>()
    val aggregateTmp = mutableMapOf<Long, Number>()
    private val aggregateTmpString = mutableMapOf<Long, String>()
    var child: AOPBase


    override fun equals(other: Any?): Boolean {
        if (other !is POPExpression)
            return false
        if (dictionary !== other.dictionary)
            return false
        if (child !== other.child)
            return false
        return true
    }

    constructor(dictionary: ResultSetDictionary, child: AOPBase) {
        this.dictionary = dictionary
        this.child = child
    }

    fun isAUpgradeableToB(a: TmpResultType, b: TmpResultType): Boolean {
        if (a == b)
            return true
        when (a) {
            TmpResultType.RSInteger -> {
                when (b) {
                    TmpResultType.RSDecimal, TmpResultType.RSDouble -> return true
                    else -> return false
                }
            }
            TmpResultType.RSDecimal -> {
                when (b) {
                    TmpResultType.RSDouble -> return true
                    else -> return false
                }
            }
            else -> return false
        }
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

    private fun commonDatatype(resultSet: ResultSet, resultRow: ResultRow, nodeA: AOPBase, nodeB: AOPBase): TmpResultType {
        val aType = getResultType(resultSet, resultRow, nodeA)
        val bType = getResultType(resultSet, resultRow, nodeB)
        return commonDatatype(aType, bType)
    }

    fun getResultType(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): TmpResultType {
        when (node) {
            is AOPAggregation -> {
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
                var newType = getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                if (node.type == Aggregation.AVG)
                    newType = commonDatatype(newType, TmpResultType.RSDecimal)
                val oldType = aggregateTmpType[node.uuid]
                if (oldType == null)
                    return newType
                return commonDatatype(oldType, newType)
            }
            is AOPUndef -> return TmpResultType.RSUndefined
            is AOPSimpleLiteral -> return TmpResultType.RSString
            is AOPBooleanLiteral -> return TmpResultType.RSBoolean
            is AOPOr -> return TmpResultType.RSBoolean
            is AOPAnd -> return TmpResultType.RSBoolean
            is AOPEQ -> return TmpResultType.RSBoolean
            is AOPNot -> return TmpResultType.RSBoolean
            is AOPNEQ -> return TmpResultType.RSBoolean
            is AOPGEQ -> return TmpResultType.RSBoolean
            is AOPLEQ -> return TmpResultType.RSBoolean
            is AOPGT -> return TmpResultType.RSBoolean
            is AOPLT -> return TmpResultType.RSBoolean
            is AOPIri -> return TmpResultType.RSString
            is AOPInteger -> return TmpResultType.RSInteger
            is AOPDecimal -> return TmpResultType.RSDecimal
            is AOPDouble -> return TmpResultType.RSDouble
            is AOPAddition -> return commonDatatype(resultSet, resultRow, node.children[0] as AOPBase, node.children[1] as AOPBase)
            is AOPMultiplication -> return commonDatatype(resultSet, resultRow, node.children[0] as AOPBase, node.children[1] as AOPBase)
            is AOPDivision -> return commonDatatype(commonDatatype(resultSet, resultRow, node.children[0] as AOPBase, node.children[1] as AOPBase), TmpResultType.RSDecimal)
            is AOPVariable -> {
                if (!resultSet.getVariableNames().contains(node.name))
                    return TmpResultType.RSUndefined
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                if (tmp == null)
                    return TmpResultType.RSUndefined
                when {
                    tmp.endsWith(dataTypeInteger) -> return TmpResultType.RSInteger
                    tmp.endsWith(dataTypeDecimal) -> return TmpResultType.RSDecimal
                    tmp.endsWith(dataTypeDouble) -> return TmpResultType.RSDouble
                    tmp.endsWith(dataTypeBoolean) -> return TmpResultType.RSBoolean
                    tmp.endsWith(dataTypeDateTime) -> return TmpResultType.RSDateTime
                    tmp.startsWith("<http") -> return TmpResultType.RSString
                    else -> {
                        return TmpResultType.RSString
                    }
                }
            }
            is AOPBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.IF -> {
                        val condition: Boolean = evaluateHelperBoolean(resultSet, resultRow, node.children[0] as AOPBase)
                        if (condition) {
                            aggregateTmp[node.uuid] = 1
                            return getResultType(resultSet, resultRow, node.children[1] as AOPBase)
                        } else {
                            aggregateTmp[node.uuid] = 0
                            return getResultType(resultSet, resultRow, node.children[2] as AOPBase)
                        }
                    }
                    BuiltInFunctions.CONTAINS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.STRSTARTS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.STRENDS -> return TmpResultType.RSBoolean
                    BuiltInFunctions.LANGMATCHES -> return TmpResultType.RSBoolean
                    BuiltInFunctions.BOUND -> return TmpResultType.RSBoolean
                    BuiltInFunctions.RAND -> return TmpResultType.RSInteger
                    BuiltInFunctions.ABS -> return getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                    BuiltInFunctions.CEIL -> return getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                    BuiltInFunctions.FLOOR -> return getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                    BuiltInFunctions.ROUND -> return getResultType(resultSet, resultRow, node.children[0] as AOPBase)
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
                    BuiltInFunctions.NOW -> return TmpResultType.RSDateTime
                    else -> return TmpResultType.RSString
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} getResultType ${classNameToString(node)}")
        }
    }

    fun evaluateHelperDateTime(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): DateTime {
        when (node) {
            is AOPVariable -> {
                return DateTime(resultSet.getValue(resultRow[resultSet.createVariable(node.name)])!!)
            }
            is AOPBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.NOW -> {
                        return DateTime()
                    }
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperDateTime ${classNameToString(node)} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperDateTime ${classNameToString(node)}")
        }
    }

    private fun evaluateHelperBoolean2(resultSet: ResultSet, resultRow: ResultRow, node: AOPBinaryOperationFixedName): Boolean {
        val left = node.children[0] as AOPBase
        val right = node.children[1] as AOPBase
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSUndefined || typeB == TmpResultType.RSUndefined -> return typeA == typeB
            typeA == TmpResultType.RSBoolean && typeB == TmpResultType.RSBoolean -> {
                val a = evaluateHelperBoolean(resultSet, resultRow, left)
                val b = evaluateHelperBoolean(resultSet, resultRow, right)
                when (node) {
                    is AOPEQ -> return a == b
                    is AOPNEQ -> return a != b
                    is AOPDivision -> throw ArithmeticException("the output of AOPDivision is not a boolean")
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperBoolean2 ${classNameToString(node)}")
                }
            }
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSDouble) -> return evaluateDouble.evaluateHelperBoolean(resultSet, resultRow, node)
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSDecimal) -> return evaluateDecimal.evaluateHelperBoolean(resultSet, resultRow, node)
            isAUpgradeableToB(commonDatatype(resultSet, resultRow, left, right), TmpResultType.RSInteger) -> return evaluateInteger.evaluateHelperBoolean(resultSet, resultRow, node)
            typeA == TmpResultType.RSString && typeB == TmpResultType.RSString -> {
                val a = evaluateHelperString(resultSet, resultRow, left)
                val b = evaluateHelperString(resultSet, resultRow, right)
                if (a == null || b == null)
                    return false
                when (node) {
                    is AOPEQ -> return a == b
                    is AOPLT -> return a < b
                    is AOPGT -> return a > b
                    is AOPGEQ -> return a >= b
                    is AOPLEQ -> return a <= b
                    is AOPNEQ -> return a != b
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperBoolean2 ${classNameToString(node)}")
                }
            }
            node is AOPDivision -> throw ArithmeticException("the output of AOPDivision is not a boolean")
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperBoolean2 ${classNameToString(node)} ${typeA} ${typeB}")
        }
    }

    fun extractStringFromLiteral(literal: String): String {
        when {
            literal.endsWith(dataTypeString) -> return literal.substring(1, literal.length - 1 - dataTypeString.length)
            !literal.endsWith("\"") && !literal.endsWith(">") && literal.contains("@") -> return literal.substring(1, literal.lastIndexOf("@") - 1)
            else -> return literal.substring(1, literal.length - 1)
        }
    }

    fun extractLanguageFromLiteral(literal: String?): String? {
        if (literal == null)
            return null
        when {
            !literal.endsWith("\"") && !literal.endsWith(">") -> return literal.substring(literal.lastIndexOf("@") + 1, literal.length)
            else -> return null
        }
    }

    fun extractDatatypeFromLiteral(literal: String?): String? {
        if (literal == null)
            return null
        when {
            literal.contains("^^<") && literal.endsWith(">") -> {
                val res = literal.substring(literal.lastIndexOf("^^<") + 2, literal.length)
                return res
            }
            else -> {
                return null
            }
        }
    }

    fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): Boolean {
        when (node) {
            is AOPBooleanLiteral -> return node.value
            is AOPNot -> return !evaluateHelperBoolean(resultSet, resultRow, node.children[0] as AOPBase)
            is AOPOr -> {
                var res = false
                for (n in node.children) {
                    res = res || evaluateHelperBoolean(resultSet, resultRow, n as AOPBase)
                }
                return res
            }
            is AOPVariable -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])!!
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeBoolean.length)
                return tmp2.toBoolean()
            }
            is AOPAnd -> {
                var res = true
                for (n in node.children) {
                    res = res && evaluateHelperBoolean(resultSet, resultRow, n as AOPBase)
                }
                return res
            }
            is AOPBinaryOperationFixedName -> return evaluateHelperBoolean2(resultSet, resultRow, node)
            is AOPBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.BOUND -> {
                        val name = (node.children[0] as AOPVariable).name
                        return resultSet.getVariableNames().contains(name) && (!resultSet.isUndefValue(resultRow, resultSet.createVariable(name)))
                    }
                    BuiltInFunctions.IF -> {
                        if (aggregateTmp[node.uuid] == 1)
                            return evaluateHelperBoolean(resultSet, resultRow, node.children[1] as AOPBase)
                        else
                            return evaluateHelperBoolean(resultSet, resultRow, node.children[2] as AOPBase)
                    }
                    BuiltInFunctions.isNUMERIC -> {
                        val typeA = getResultType(resultSet, resultRow, node.children[0] as AOPBase)
                        return typeA == TmpResultType.RSInteger || typeA == TmpResultType.RSDecimal
                    }
                    BuiltInFunctions.LANGMATCHES -> {
                        val a = extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase))
                        val b = evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)
                        return a == b
                    }
                    BuiltInFunctions.STRENDS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!)
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!)
                        return a.endsWith(b)
                    }
                    BuiltInFunctions.STRSTARTS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!)
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!)
                        return a.startsWith(b)
                    }
                    BuiltInFunctions.CONTAINS -> {
                        val a = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!)
                        val b = extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!)
                        return a.contains(b)
                    }
                    BuiltInFunctions.isLITERAL -> {
                        val tmp = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        return tmp.startsWith("\"") && tmp.endsWith("\"")
                    }
                    BuiltInFunctions.isIRI -> {
                        val tmp = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        return tmp.startsWith("<") && tmp.endsWith(">")
                    }
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperBoolean ${classNameToString(node)} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperBoolean ${classNameToString(node)}")
        }
    }

    fun evaluateHelperString(resultSet: ResultSet, resultRow: ResultRow, node: AOPBase): String? {
        val type = getResultType(resultSet, resultRow, node)
        when (type) {
            TmpResultType.RSBoolean -> return "\"" + evaluateHelperBoolean(resultSet, resultRow, node) + "\"" + dataTypeBoolean
            TmpResultType.RSDateTime -> return "\"" + evaluateHelperDateTime(resultSet, resultRow, node) + "\"" + dataTypeDateTime
            TmpResultType.RSInteger -> {
/*
ATTENTION !!! here is only ONE correct solution ... if compiled as native
val tmp = evaluateInteger.evaluateHelper(resultSet, resultRow, node)
val tmp2:Int=tmp
GlobalLogger.log(ELoggerType.TEST_DEBUG,("int::")
GlobalLogger.log(ELoggerType.TEST_DEBUG,(evaluateInteger.evaluateHelper(resultSet, resultRow, node))
GlobalLogger.log(ELoggerType.TEST_DEBUG,(evaluateInteger.evaluateHelper(resultSet, resultRow, node).toInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(evaluateInteger.evaluateHelper(resultSet, resultRow, node).toDouble().roundToInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(evaluateInteger.evaluateHelper(resultSet, resultRow, node).toString())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(evaluateInteger.evaluateHelper(resultSet, resultRow, node).toString().replace(".0",""))
GlobalLogger.log(ELoggerType.TEST_DEBUG,(""+evaluateInteger.evaluateHelper(resultSet, resultRow, node))
GlobalLogger.log(ELoggerType.TEST_DEBUG,((""+evaluateInteger.evaluateHelper(resultSet, resultRow, node)).replace(".0",""))
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp)
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp.toInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp.toDouble().roundToInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp.toString())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp.toString().replace(".0",""))
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp2)
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp2.toInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp2.toDouble().roundToInt())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp2.toString())
GlobalLogger.log(ELoggerType.TEST_DEBUG,(tmp2.toString().replace(".0",""))
*/
                return "\"" + ("" + evaluateInteger.evaluateHelper(resultSet, resultRow, node)).replace(".0", "") + "\"" + dataTypeInteger
            }
            TmpResultType.RSUndefined -> return null
            TmpResultType.RSDecimal -> {
                val tmp = "" + evaluateDecimal.evaluateHelper(resultSet, resultRow, node)
                if (tmp.contains(".") || tmp.contains("e") || tmp.contains("E"))
                    return "\"" + tmp + "\"" + dataTypeDecimal
                return "\"" + tmp + ".0\"" + dataTypeDecimal
            }
            TmpResultType.RSDouble -> return "\"" + evaluateDouble.evaluateHelper(resultSet, resultRow, node) + "\"" + dataTypeDouble
            TmpResultType.RSString -> {/*continue afterwards*/
            }
            else -> {
                throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperString ${classNameToString(node)} unknown result Type ${type}")
            }
        }
        when (node) {
            is AOPAggregation -> {
                throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperString ${classNameToString(node)} ${node.type} ${node.distinct}")
            }
            is AOPIri -> return "<" + node.iri + ">"
            is AOPSimpleLiteral -> return node.delimiter + node.content + node.delimiter
            is AOPVariable -> return resultSet.getValue(resultRow[resultSet.createVariable(node.name)])!!
            is AOPAddition -> throw ArithmeticException("AOPAddition can not be applied to String-Datatype")
            is AOPBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.BNODE -> {
                        if (node.children.size > 0) {
                            require(node.children.size == 1)
                            return "_:POPExpressionC" + evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)
                        } else
                            return "_:POPExpression" + localbnode.next()
                    }
                    BuiltInFunctions.IF -> {
                        if (aggregateTmp[node.uuid] == 1)
                            return evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)
                        else
                            return evaluateHelperString(resultSet, resultRow, node.children[2] as AOPBase)
                    }
                    BuiltInFunctions.TZ -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).getTZ()
                    BuiltInFunctions.TIMEZONE -> return evaluateHelperDateTime(resultSet, resultRow, node.children[0] as AOPBase).getTimeZone()
                    BuiltInFunctions.LCASE -> {
                        var tmp = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        if (tmp.endsWith("\""))
                            return tmp.toLowerCase()
                        if (tmp.endsWith(dataTypeString)) {
                            return tmp.substring(0, tmp.length - dataTypeString.length).toLowerCase() + dataTypeString
                        }
                        val idx = tmp.lastIndexOf("@")
                        return tmp.substring(0, idx).toLowerCase() + tmp.substring(idx, tmp.length)
                    }
                    BuiltInFunctions.UCASE -> {
                        var tmp = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        if (tmp.endsWith("\""))
                            return tmp.toUpperCase()
                        if (tmp.endsWith(dataTypeString)) {
                            return tmp.substring(0, tmp.length - dataTypeString.length).toUpperCase() + dataTypeString
                        }
                        val idx = tmp.lastIndexOf("@")
                        return tmp.substring(0, idx).toUpperCase() + tmp.substring(idx, tmp.length)
                    }
                    BuiltInFunctions.CONCAT -> {
                        val a = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        val b = evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!
                        val aas = extractStringFromLiteral(a)
                        val bbs = extractStringFromLiteral(b)
                        if (a.endsWith(dataTypeString) && b.endsWith(dataTypeString))
                            return "\"" + aas + bbs + "\"" + dataTypeString
                        if (a.endsWith(">") && !a.endsWith(dataTypeString))
                            return null
                        if (b.endsWith(">") && !b.endsWith(dataTypeString))
                            return null
                        if (a.endsWith(">") || b.endsWith(">"))
                            return "\"" + aas + bbs + "\""
                        val aa = extractLanguageFromLiteral(a)
                        val bb = extractLanguageFromLiteral(b)
                        if (aa != bb || aa == null || aa == "")
                            return "\"" + aas + bbs + "\""
                        return "\"" + aas + bbs + "\"@" + aa
                    }
                    BuiltInFunctions.URI -> {
//TODO evaluate base prefix and prepend to result
                        val res = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        return "<" + extractStringFromLiteral(res) + ">"
                    }
                    BuiltInFunctions.IRI -> {
//TODO evaluate base prefix and prepend to result
                        val res = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        return "<" + extractStringFromLiteral(res) + ">"
                    }

                    BuiltInFunctions.STRDT -> {
                        val value = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        val type = evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!
                        val res = "\"" + value + "\"^^<" + type + ">"
                        return res
                    }
                    BuiltInFunctions.STRLANG -> {
                        val value = evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                        val lang = evaluateHelperString(resultSet, resultRow, node.children[1] as AOPBase)!!
                        val res = "\"" + value + "\"@" + lang
                        return res
                    }
                    BuiltInFunctions.UUID -> return "<urn:uuid:" + uuid4() + ">"
                    BuiltInFunctions.STRUUID -> return "" + uuid4()
                    BuiltInFunctions.DATATYPE -> return extractDatatypeFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase))
                    BuiltInFunctions.LANG -> return "\"" + extractLanguageFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)) + "\""
                    BuiltInFunctions.STR -> return evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!
                    BuiltInFunctions.MD5 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!).encodeToByteArray().md5().toHexString() + "\""
                    BuiltInFunctions.SHA1 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!).encodeToByteArray().sha1().toHexString() + "\""
                    BuiltInFunctions.SHA256 -> return "\"" + extractStringFromLiteral(evaluateHelperString(resultSet, resultRow, node.children[0] as AOPBase)!!).encodeToByteArray().sha256().toHexString() + "\""
                    else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperString ${classNameToString(node)} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${classNameToString(this)} evaluateHelperString ${classNameToString(node)}")
        }
    }

    fun evaluateBoolean(resultSet: ResultSet, resultRow: ResultRow): Boolean {
        if (getResultType(resultSet, resultRow, child) != TmpResultType.RSBoolean)
            throw UnsupportedOperationException("${classNameToString(this)} evaluateBoolean ${classNameToString(child)}")
        val res = evaluateHelperBoolean(resultSet, resultRow, child)
        return res
    }

    fun evaluate(resultSet: ResultSet, resultRow: ResultRow): String? {
        val res = evaluateHelperString(resultSet, resultRow, child)
        return res
    }

    fun evaluate(resultSet: ResultSet, resultRows: List<ResultRow>): String? {
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
        val res = evaluate(resultSet, resultSet.createResultRow())
        return res
    }

    fun getAllVariablesInChildren(node: OPBase): List<String> {
        val res = mutableListOf<String>()
        if (node is AOPVariable)
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

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("POPExpression")
        res.addContent(XMLElement.parseFromAOPBase(child))
        return res
    }

    companion object {
        fun fromXMLElement(dictionary: ResultSetDictionary, xml: XMLElement): POPExpression {
            return POPExpression(dictionary, XMLElement.toAOPBase(xml.childs.first()))
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

