package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.*
import lupos.s00misc.*
import lupos.s01io.*
import lupos.s02buildSyntaxTree.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallTZ
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallUCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallYEAR
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s05tripleStore.*
import lupos.s06buildOperatorGraph.*
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s12p2p.*
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s14endpoint.*
import lupos.s15tripleStoreDistributed.*


enum class TestCase(val action: (DynamicByteArray) -> Unit) {
    Sparql(::executeBinaryTest),
    SortedArray(::sortedArrayTest)
}

enum class ValueEnum {
    ValueBnode,
    ValueBoolean,
    ValueDateTime,
    ValueDecimal,
    ValueDouble,
    ValueInteger,
    ValueIri,
    ValueLanguageTaggedLiteral,
    ValueSimpleLiteral,
    ValueTypedLiteral,
    ValueUndef
}

fun ValueToID(v: ValueDefinition): ValueEnum {
    return when (v) {
        is ValueBnode -> ValueEnum.ValueBoolean
        is ValueBoolean -> ValueEnum.ValueBoolean
        is ValueDateTime -> ValueEnum.ValueDateTime
        is ValueDecimal -> ValueEnum.ValueDecimal
        is ValueDouble -> ValueEnum.ValueDouble
        is ValueInteger -> ValueEnum.ValueInteger
        is ValueIri -> ValueEnum.ValueIri
        is ValueLanguageTaggedLiteral -> ValueEnum.ValueLanguageTaggedLiteral
        is ValueSimpleLiteral -> ValueEnum.ValueSimpleLiteral
        is ValueTypedLiteral -> ValueEnum.ValueTypedLiteral
        is ValueUndef -> ValueEnum.ValueUndef
        else -> throw Exception("type error")
    }
}

class ExceptionTopLevelOperator(@JvmField val data: OPBase) : Exception("this object needs to be toplevel")

val MAX_SET = 10
val MAX_VARIABLES = 10
val MAX_LIMIT = 100
val MAX_OFFSET = 100
val MAX_TRIPLES = 100
val MAX_GRAPH_NAMES = 10

val testDictionaryVarName = ThreadSafeMutableList<String?>()
val testDictionaryValue = ThreadSafeMutableList<String?>()
val testDictionaryValueTyped = ThreadSafeMutableMap<ValueEnum, ThreadSafeMutableList<String?>>()
var hadArrayIndexOutOfBoundsException = false

fun fromBinaryListOfVariables(query: Query, buffer: DynamicByteArray, count: Int): MutableList<AOPVariable> {
    var res = mutableListOf<AOPVariable>()
    for (i in 0 until count)
        res.add(AOPVariable(query, nextStringVarName(query, buffer, res)))
    return res
}

fun nextStringVarName(query: Query, buffer: DynamicByteArray, exclude: MutableList<AOPVariable> = mutableListOf()): String {
    while (true) {
        val v = AOPVariable(query, testDictionaryVarName[nextInt(query, buffer, testDictionaryVarName.size())]!!)
        var found = false
        for (c in exclude)
            if (c.name == v.name) {
                found = true
                break
            }
        if (!found)
            return v.name
    }
}

fun nextStringValue(query: Query, buffer: DynamicByteArray): String {
    return testDictionaryValue[nextInt(query, buffer, testDictionaryValue.size())]!!
}

fun nextStringValueTyped(query: Query, buffer: DynamicByteArray, type: ValueEnum): String {
    val idx = nextInt(query, buffer, testDictionaryValue.size())
    val tmp = ValueDefinition.create(testDictionaryValue[idx])
    if (ValueToID(tmp) == type)
        return testDictionaryValue[idx]!!
    return testDictionaryValueTyped[type]!![idx % testDictionaryValueTyped[type]!!.size()]!!
}

fun fromBinaryAOPConstOrVar(query: Query, buffer: DynamicByteArray): AOPBase {
    try {
        if (nextInt(query, buffer, 2) == 0)
            return AOPVariable(query, testDictionaryVarName[nextInt(query, buffer, testDictionaryVarName.size())]!!)
        return AOPConstant(query, ValueDefinition.create(testDictionaryValue[nextInt(query, buffer, testDictionaryValue.size())]!!))
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPConstant(query, ValueUndef())
    }
}

fun fromBinaryValueIriOrVar(query: Query, buffer: DynamicByteArray): AOPBase {
    try {
        if (nextInt(query, buffer, 2) == 0)
            return AOPVariable(query, testDictionaryVarName[nextInt(query, buffer, testDictionaryVarName.size())]!!)
        val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
        return AOPConstant(query, ValueDefinition.create(resultSet[nextInt(query, buffer, resultSet.size())]))
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
        return AOPConstant(query, ValueDefinition.create(resultSet[0]))
    }
}

fun fromBinaryValueIriOrBnodeOrVar(query: Query, buffer: DynamicByteArray): AOPBase {
    try {
        var id = nextInt(query, buffer, 3)
        when (id % 3) {
            0 -> return AOPVariable(query, testDictionaryVarName[nextInt(query, buffer, testDictionaryVarName.size())]!!)
            1 -> {
                val resultSet = testDictionaryValueTyped[ValueEnum.ValueIri]!!
                return AOPConstant(query, ValueDefinition.create(resultSet[nextInt(query, buffer, resultSet.size())]))
            }
            else -> {
                return AOPBuildInCallBNODE0(query)
            }
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPBuildInCallBNODE0(query)
    }
}

fun nextInt(query: Query, buffer: DynamicByteArray, maxValue: Int = Int.MAX_VALUE, increment: Boolean = true): Int {
    val tmp = if (increment)
        buffer.getNextInt()
    else
        buffer.getInt(buffer.pos)
    if (tmp < 0)
        return (-tmp) % maxValue
    return tmp % maxValue
}

fun fromBinary(query: Query, buffer: DynamicByteArray): OPBase {
    try {
        var id = nextInt(query, buffer, EOperatorID.values().size, false)
        val operatorID = EOperatorID.values()[id]
        if (operatorID == EOperatorID.OPNothingID)
            return OPNothing(query)
        if (EOperatorIDLOP.contains(operatorID))
            return fromBinaryLOP(query, buffer)
        if (EOperatorIDPOP.contains(operatorID))
            return fromBinaryPOP(query, buffer)
        if (EOperatorIDAOP.contains(operatorID))
            return fromBinaryAOP(query, buffer)
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
    }
    return OPNothing(query)
}

fun fromBinaryPOPLOP(query: Query, buffer: DynamicByteArray): OPBase {
    try {
        val poploplist = EOperatorIDPOP + EOperatorIDLOP
        var id = nextInt(query, buffer, poploplist.size)
        val operatorID = poploplist[id]
        if (operatorID == EOperatorID.OPNothingID)
            return OPNothing(query)
        if (EOperatorIDLOP.contains(operatorID))
            return fromBinaryLOP(query, buffer)
        if (EOperatorIDPOP.contains(operatorID))
            return fromBinaryPOP(query, buffer)
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
    }
    return OPNothing(query)
}

fun fromBinaryPOP(query: Query, buffer: DynamicByteArray): POPBase {
    try {
        var id = nextInt(query, buffer)
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDPOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDPOP[id % EOperatorIDPOP.size]
        else
            operatorID = EOperatorID.values()[id]

        when (operatorID) {
            EOperatorID.POPServiceIRIID -> {
                return fromBinaryPOP(query, buffer)
            }
            EOperatorID.POPImportFromNetworkPackageID -> {
                return fromBinaryPOP(query, buffer)
            }
            EOperatorID.POPModifyDataID -> {
                val type = EModifyType.values()[nextInt(query, buffer, EModifyType.values().size)]
                val data = mutableListOf<LOPTriple>()
                val count = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until count)
                    data.add(fromBinaryLopTriple(query, buffer))
                throw ExceptionTopLevelOperator(POPModifyData(query, type, data))
            }
            EOperatorID.POPModifyID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                val insert = mutableListOf<LOPTriple>()
                val delete = mutableListOf<LOPTriple>()
                val insertCount = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until insertCount)
                    insert.add(fromBinaryLopTriple(query, buffer))
                val deleteCount = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until deleteCount)
                    delete.add(fromBinaryLopTriple(query, buffer))
                throw ExceptionTopLevelOperator(POPModify(query, insert, delete, child))
            }
            EOperatorID.POPEmptyRowID -> {
                return POPEmptyRow(query)
            }
            EOperatorID.POPUnionID -> {
                val childA = fromBinaryPOPLOP(query, buffer)
                val childB = fromBinaryPOPLOP(query, buffer)
                return POPUnion(query, childA, childB)
            }
            EOperatorID.POPJoinHashMapID -> {
                val childA = fromBinaryPOPLOP(query, buffer)
                val childB = fromBinaryPOPLOP(query, buffer)
                val optional = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                return POPJoinHashMap(query, childA, childB, optional)
            }
            EOperatorID.POPJoinNestedLoopID -> {
                val childA = fromBinaryPOPLOP(query, buffer)
                val childB = fromBinaryPOPLOP(query, buffer)
                val optional = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                return POPJoinHashMap(query, childA, childB, optional)
            }
            EOperatorID.POPFilterID -> {
                val filter = fromBinaryAOP(query, buffer)
                val child = fromBinaryPOPLOP(query, buffer)
                return POPFilter(query, filter, child)
            }
            EOperatorID.POPMakeBooleanResultID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                throw ExceptionTopLevelOperator(POPMakeBooleanResult(query, child))
            }
            EOperatorID.POPBindID -> {
                val name = AOPVariable(query, nextStringVarName(query, buffer))
                val value = fromBinaryAOP(query, buffer)
                val child = fromBinaryPOPLOP(query, buffer)
                return POPBind(query, name, value, child)
            }
            EOperatorID.POPSortID -> {
                val sortBy = AOPVariable(query, nextStringVarName(query, buffer))
                val sortOrder = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val child = fromBinaryPOPLOP(query, buffer)
                return POPSort(query, sortBy, sortOrder, child)
            }
            EOperatorID.POPDistinctID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                return POPDistinct(query, child)
            }
            EOperatorID.POPGroupID -> {
                var bindings: POPBind? = null
                val byCount = nextInt(query, buffer, MAX_VARIABLES)
                val by = fromBinaryListOfVariables(query, buffer, byCount)
                val bindCount = nextInt(query, buffer, MAX_VARIABLES)
                val tmpList = mutableListOf<AOPVariable>()
                tmpList.addAll(by)
                for (i in 0 until bindCount) {
                    val name = AOPVariable(query, nextStringVarName(query, buffer, tmpList))
                    tmpList.add(name)
                    val value = fromBinaryAOP(query, buffer)
                    if (bindings == null)
                        bindings = POPBind(query, name, value, POPEmptyRow(query))
                    else
                        bindings = POPBind(query, name, value, bindings)
                }
                val child = fromBinaryPOPLOP(query, buffer)
                return POPGroup(query, by, bindings, child)
            }
            EOperatorID.POPProjectionID -> {
                val childCount = nextInt(query, buffer, MAX_VARIABLES)
                if (childCount == 0)
                    return fromBinaryPOP(query, buffer)
                val variables = fromBinaryListOfVariables(query, buffer, childCount)
                val child = fromBinaryPOPLOP(query, buffer)
                return POPProjection(query, variables, child)
            }
            EOperatorID.POPLimitID -> {
                var value = nextInt(query, buffer, MAX_LIMIT)
                val child = fromBinaryPOPLOP(query, buffer)
                return POPLimit(query, value, child)
            }
            EOperatorID.POPOffsetID -> {
                var value = nextInt(query, buffer, MAX_OFFSET)
                val child = fromBinaryPOPLOP(query, buffer)
                return POPOffset(query, value, child)
            }
            EOperatorID.POPGraphOperationID -> {
                val action = EGraphOperationType.values()[nextInt(query, buffer, EGraphOperationType.values().size)]
                val silent = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val graph1type = EGraphRefType.values()[nextInt(query, buffer, EGraphRefType.values().size)]
                val graph1nameTmp = (nextStringValueTyped(query, buffer, ValueEnum.ValueIri))
                val graph1name = graph1nameTmp.substring(1, graph1nameTmp.length - 1)
                val graph2type = EGraphRefType.values()[nextInt(query, buffer, EGraphRefType.values().size)]
                val graph2nameTmp = (nextStringValueTyped(query, buffer, ValueEnum.ValueIri))
                val graph2name = graph2nameTmp.substring(1, graph2nameTmp.length - 1)
                throw ExceptionTopLevelOperator(POPGraphOperation(query, silent, graph1type, graph1name, graph2type, graph2name, action))
            }
            EOperatorID.TripleStoreIteratorGlobalID, EOperatorID.TripleStoreIteratorLocalFilterID, EOperatorID.TripleStoreIteratorLocalID -> {
                val graphNameTmp = (nextStringValueTyped(query, buffer, ValueEnum.ValueIri))
                val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
                val graph = DistributedTripleStore.getNamedGraph(query, graphName, true)
                val s = fromBinaryValueIriOrBnodeOrVar(query, buffer)
                val p = fromBinaryValueIriOrVar(query, buffer)
                val o = fromBinaryAOPConstOrVar(query, buffer)
                val idx = EIndexPattern.values()[nextInt(query, buffer, EIndexPattern.values().size)]
                val tripleCount = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until tripleCount)
                    graph.addData(Array(3) { ValueDefinition.create(nextStringValue(query, buffer)) })
                query.commit()
                return DistributedTripleStore.getNamedGraph(query, graphName).getIterator(arrayOf(s, p, o), idx)
            }
            EOperatorID.POPValuesID -> {
                val variables = mutableListOf<String>()
                val values = mutableListOf<List<String?>>()
                val variableCount = nextInt(query, buffer, MAX_VARIABLES)
                for (i in 0 until variableCount)
                    variables.add(nextStringVarName(query, buffer))
                val valuesCount = nextInt(query, buffer, MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val map = mutableListOf<String?>()
                    values.add(map)
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                        if (!isNull)
                            map.add(nextStringValue(query, buffer))
                        else
                            map.add(null)
                    }
                }
                return POPValues(query, variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryPOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return POPEmptyRow(query)
    }
}

fun fromBinaryLOP(query: Query, buffer: DynamicByteArray): LOPBase {
    try {
        var id = nextInt(query, buffer)
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDLOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDLOP[id % EOperatorIDLOP.size]
        else
            operatorID = EOperatorID.values()[id]
        when (operatorID) {
            EOperatorID.LOPServiceVARID -> {
//TODO
                return fromBinaryLOP(query, buffer)
            }
            EOperatorID.LOPServiceIRIID -> {
//TODO
                return fromBinaryLOP(query, buffer)
            }
            EOperatorID.LOPOptionalID -> {
//TODO
                return fromBinaryLOP(query, buffer)
            }
            EOperatorID.LOPReducedID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPReduced(query, child)
            }
            EOperatorID.LOPPrefixID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                val name = nextStringValueTyped(query, buffer, ValueEnum.ValueIri)
                val prefix = nextStringValueTyped(query, buffer, ValueEnum.ValueIri)
                return LOPPrefix(query, name.substring(1, name.length - 1), prefix.substring(1, prefix.length - 1), child)
            }
            EOperatorID.LOPGroupID -> {
                var bindings: POPBind? = null
                val byCount = nextInt(query, buffer, MAX_VARIABLES)
                val by = fromBinaryListOfVariables(query, buffer, byCount)
                val tmpList = mutableListOf<AOPVariable>()
                tmpList.addAll(by)
                val bindCount = nextInt(query, buffer, MAX_VARIABLES)
                for (i in 0 until bindCount) {
                    val name = AOPVariable(query, nextStringVarName(query, buffer, tmpList))
                    tmpList.add(name)
                    val value = fromBinaryAOP(query, buffer)
                    if (bindings == null)
                        bindings = POPBind(query, name, value, POPEmptyRow(query))
                    else
                        bindings = POPBind(query, name, value, bindings)
                }
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPGroup(query, by, bindings, child)
            }
            EOperatorID.LOPNOOPID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPNOOP(query, child)
            }
            EOperatorID.LOPSubGroupID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPSubGroup(query, child)
            }
            EOperatorID.LOPModifyID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                val insert = mutableListOf<LOPTriple>()
                val delete = mutableListOf<LOPTriple>()
                val insertCount = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until insertCount)
                    insert.add(fromBinaryLopTriple(query, buffer))
                val deleteCount = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until deleteCount)
                    delete.add(fromBinaryLopTriple(query, buffer))
                throw ExceptionTopLevelOperator(LOPModify(query, insert, delete, child))
            }
            EOperatorID.LOPModifyDataID -> {
                val type = EModifyType.values()[nextInt(query, buffer, EModifyType.values().size)]
                val data = mutableListOf<LOPTriple>()
                val count = nextInt(query, buffer, MAX_TRIPLES)
                for (i in 0 until count)
                    data.add(fromBinaryLopTriple(query, buffer))
                throw ExceptionTopLevelOperator(LOPModifyData(query, type, data))
            }
            EOperatorID.LOPGraphOperationID -> {
                val action = EGraphOperationType.values()[nextInt(query, buffer, EGraphOperationType.values().size)]
                val silent = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val graph1type = EGraphRefType.values()[nextInt(query, buffer, EGraphRefType.values().size)]
                val graph1name = nextStringValueTyped(query, buffer, ValueEnum.ValueIri)
                val graph2type = EGraphRefType.values()[nextInt(query, buffer, EGraphRefType.values().size)]
                val graph2name = nextStringValueTyped(query, buffer, ValueEnum.ValueIri)
                throw ExceptionTopLevelOperator(LOPGraphOperation(query, action, silent, graph1type, graph1name, graph2type, graph2name))
            }
            EOperatorID.LOPMakeBooleanResultID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                throw ExceptionTopLevelOperator(LOPMakeBooleanResult(query, child))
            }
            EOperatorID.LOPUnionID -> {
                val childA = fromBinaryPOPLOP(query, buffer)
                val childB = fromBinaryPOPLOP(query, buffer)
                return LOPUnion(query, childA, childB)
            }
            EOperatorID.LOPJoinID -> {
                val childA = fromBinaryPOPLOP(query, buffer)
                val childB = fromBinaryPOPLOP(query, buffer)
                val optional = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                return LOPJoin(query, childA, childB, optional)
            }
            EOperatorID.LOPFilterID -> {
                val filter = fromBinaryAOP(query, buffer)
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPFilter(query, filter, child)
            }
            EOperatorID.LOPBindID -> {
                val name = AOPVariable(query, nextStringVarName(query, buffer))
                val value = fromBinaryAOP(query, buffer)
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPBind(query, name, value, child)
            }
            EOperatorID.LOPSortID -> {
                val sortBy = AOPVariable(query, nextStringVarName(query, buffer))
                val sortOrder = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPSort(query, sortOrder, sortBy, child)
            }
            EOperatorID.LOPDistinctID -> {
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPDistinct(query, child)
            }
            EOperatorID.LOPProjectionID -> {
                val childCount = nextInt(query, buffer, MAX_VARIABLES)
                val variables = fromBinaryListOfVariables(query, buffer, childCount)
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPProjection(query, variables, child)
            }
            EOperatorID.LOPLimitID -> {
                var value = nextInt(query, buffer, MAX_LIMIT)
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPLimit(query, value, child)
            }
            EOperatorID.LOPOffsetID -> {
                var value = nextInt(query, buffer, MAX_OFFSET)
                val child = fromBinaryPOPLOP(query, buffer)
                return LOPOffset(query, value, child)
            }
            EOperatorID.LOPTripleID -> {
                return fromBinaryLopTriple(query, buffer)
            }
            EOperatorID.LOPValuesID -> {
                val values = mutableListOf<AOPValue>()
                val variableCount = nextInt(query, buffer, MAX_VARIABLES)
                val variables = fromBinaryListOfVariables(query, buffer, variableCount)
                val valuesCount = nextInt(query, buffer, MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val list = mutableListOf<AOPConstant>()
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                        if (!isNull) {
                            val value = nextStringValue(query, buffer)
                            list.add(AOPConstant(query, ValueDefinition.create(value)))
                        } else
                            list.add(AOPConstant(query, ValueUndef()))
                    }
                    values.add(AOPValue(query, list))
                }
                return LOPValues(query, variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryLOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return LOPTriple(query, AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o"), DistributedTripleStore.getGraphNames(true).last(), false)
    }
}

fun fromBinaryLopTriple(query: Query, buffer: DynamicByteArray): LOPTriple {
    val graphNameTmp = (nextStringValueTyped(query, buffer, ValueEnum.ValueIri))
    val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
    val graph = DistributedTripleStore.getNamedGraph(query, graphName, true)
    var s = fromBinaryValueIriOrBnodeOrVar(query, buffer)
    var p = fromBinaryValueIriOrVar(query, buffer)
    var o = fromBinaryAOPConstOrVar(query, buffer)
    val idx = EIndexPattern.values()[nextInt(query, buffer, EIndexPattern.values().size)]
    val tripleCount = nextInt(query, buffer, MAX_TRIPLES)
    for (i in 0 until tripleCount)
        graph.addData(Array(3) { ValueDefinition.create(nextStringValue(query, buffer)) })
    query.commit()
    return LOPTriple(query, s, p, o, graphName, false)
}

fun fromBinaryAOP(query: Query, buffer: DynamicByteArray): AOPBase {
    try {
        var id = nextInt(query, buffer)
        if (id < 0)
            id = -id
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDAOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDAOP[id % EOperatorIDAOP.size]
        else
            operatorID = EOperatorID.values()[id]

        when (operatorID) {
            EOperatorID.AOPBuildInCallIsIriID -> {
                val child = fromBinaryAOP(query, buffer)
                return AOPBuildInCallIsIri(query, child)
            }
            EOperatorID.AOPBuildInCallIsLITERALID -> {
                val child = fromBinaryAOP(query, buffer)
                return AOPBuildInCallIsLITERAL(query, child)
            }
            EOperatorID.AOPAndID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPAnd(query, childA, childB)
            }
            EOperatorID.AOPLEQID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPLEQ(query, childA, childB)
            }
            EOperatorID.AOPMultiplicationID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPMultiplication(query, childA, childB)
            }
            EOperatorID.AOPSetID -> {
                val childCount = nextInt(query, buffer, MAX_SET)
                val list = mutableListOf<AOPBase>()
                for (i in 0 until childCount)
                    list.add(fromBinaryAOP(query, buffer))
                return AOPSet(query, list)
            }
            EOperatorID.AOPValueID -> {
                val childCount = nextInt(query, buffer, MAX_SET)
                val list = mutableListOf<AOPConstant>()
                for (i in 0 until childCount) {
                    list.add(AOPConstant(query, ValueDefinition.create(nextStringValue(query, buffer))))
                }
                return AOPValue(query, list)
            }
            EOperatorID.AOPBuildInCallSTRUUIDID -> {
                return AOPBuildInCallSTRUUID(query)
            }
            EOperatorID.AOPBuildInCallUUIDID -> {
                return AOPBuildInCallUUID(query)
            }
            EOperatorID.AOPLTID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPLT(query, childA, childB)
            }
            EOperatorID.AOPGTID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPGT(query, childA, childB)
            }
            EOperatorID.AOPSubtractionID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPSubtraction(query, childA, childB)
            }
            EOperatorID.AOPNEQID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPNEQ(query, childA, childB)
            }
            EOperatorID.AOPNotID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPNot(query, childA)
            }
            EOperatorID.AOPInID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPIn(query, childA, childB)
            }
            EOperatorID.AOPNotInID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPNotIn(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallBOUNDID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallBOUND(query, childA)
            }
            EOperatorID.AOPBuildInCallTIMEZONEID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallTIMEZONE(query, childA)
            }
            EOperatorID.AOPAdditionID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPAddition(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallCONTAINSID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallCONTAINS(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallIsNUMERICID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallIsNUMERIC(query, childA)
            }
            EOperatorID.AOPBuildInCallLANGMATCHESID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallLANGMATCHES(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRENDSID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTRENDS(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRSTARTSID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTRSTARTS(query, childA, childB)
            }
            EOperatorID.AOPEQID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPEQ(query, childA, childB)
            }
            EOperatorID.AOPGEQID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPGEQ(query, childA, childB)
            }
            EOperatorID.AOPOrID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPOr(query, childA, childB)
            }
            EOperatorID.AOPVariableID -> {
                val name = nextStringVarName(query, buffer)
                return AOPVariable(query, name)
            }
            EOperatorID.AOPBuildInCallABSID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallABS(query, childA)
            }
            EOperatorID.AOPBuildInCallBNODE0ID -> {
                return AOPBuildInCallBNODE0(query)
            }
            EOperatorID.AOPBuildInCallBNODE1ID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallBNODE1(query, childA)
            }
            EOperatorID.AOPBuildInCallCEILID -> {
                val childA = fromBinaryAOP(query, buffer)
                if (childA is AOPBuildInCallCEIL)
                    return childA
                return AOPBuildInCallCEIL(query, childA)
            }
            EOperatorID.AOPBuildInCallCONCATID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallCONCAT(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallDATATYPEID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallDATATYPE(query, childA)
            }
            EOperatorID.AOPBuildInCallDAYID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallDAY(query, childA)
            }
            EOperatorID.AOPBuildInCallFLOORID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallFLOOR(query, childA)
            }
            EOperatorID.AOPBuildInCallHOURSID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallHOURS(query, childA)
            }
            EOperatorID.AOPBuildInCallIFID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                val childC = fromBinaryAOP(query, buffer)
                return AOPBuildInCallIF(query, childA, childB, childC)
            }
            EOperatorID.AOPBuildInCallIRIID -> {
                val childA = fromBinaryAOP(query, buffer)
                val prefix = nextStringValue(query, buffer)
                return AOPBuildInCallIRI(query, childA, prefix)
            }
            EOperatorID.AOPBuildInCallLANGID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallLANG(query, childA)
            }
            EOperatorID.AOPBuildInCallLCASEID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallLCASE(query, childA)
            }
            EOperatorID.AOPBuildInCallMD5ID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallMD5(query, childA)
            }
            EOperatorID.AOPBuildInCallMINUTESID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallMINUTES(query, childA)
            }
            EOperatorID.AOPBuildInCallMONTHID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallMONTH(query, childA)
            }
            EOperatorID.AOPBuildInCallROUNDID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallROUND(query, childA)
            }
            EOperatorID.AOPBuildInCallSECONDSID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSECONDS(query, childA)
            }
            EOperatorID.AOPBuildInCallSHA1ID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSHA1(query, childA)
            }
            EOperatorID.AOPBuildInCallSHA256ID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSHA256(query, childA)
            }
            EOperatorID.AOPBuildInCallSTRDTID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTRDT(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTR(query, childA)
            }
            EOperatorID.AOPBuildInCallSTRLANGID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTRLANG(query, childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRLENID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallSTRLEN(query, childA)
            }
            EOperatorID.AOPBuildInCallTZID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallTZ(query, childA)
            }
            EOperatorID.AOPBuildInCallUCASEID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallUCASE(query, childA)
            }
            EOperatorID.AOPBuildInCallURIID -> {
                val childA = fromBinaryAOP(query, buffer)
                val prefix = nextStringValue(query, buffer)
                return AOPBuildInCallURI(query, childA, prefix)
            }
            EOperatorID.AOPBuildInCallYEARID -> {
                val childA = fromBinaryAOP(query, buffer)
                return AOPBuildInCallYEAR(query, childA)
            }
            EOperatorID.AOPConstantID -> {
                return AOPConstant(query, ValueDefinition.create(nextStringValue(query, buffer)))
            }
            EOperatorID.AOPAggregationCOUNTID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationCOUNT(query, distinct, variables)
            }
            EOperatorID.AOPAggregationSAMPLEID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationSAMPLE(query, distinct, variables)
            }
            EOperatorID.AOPAggregationSUMID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationSUM(query, distinct, variables)
            }
            EOperatorID.AOPAggregationAVGID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationAVG(query, distinct, variables)
            }
            EOperatorID.AOPAggregationMINID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationMIN(query, distinct, variables)
            }
            EOperatorID.AOPAggregationMAXID -> {
                val distinct = DynamicByteArray.intToBool(nextInt(query, buffer, 2))
                val count = nextInt(query, buffer, 2)
                val variables = Array(count) { fromBinaryAOP(query, buffer) }
                return AOPAggregationMAX(query, distinct, variables)
            }
            EOperatorID.AOPDivisionID -> {
                val childA = fromBinaryAOP(query, buffer)
                val childB = fromBinaryAOP(query, buffer)
                return AOPDivision(query, childA, childB)
            }
            else -> throw Exception("BinaryHelper.fromBinaryAOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        hadArrayIndexOutOfBoundsException = true
        return AOPConstant(query, ValueUndef())
    }
}

fun executeBinaryTests(folder: String) {
    var testcases = 0
    try {
        File(folder).walk {
            if (it.endsWith(".bin")) {
                testcases++
                if (true) {
                    val tmp = DistributedTripleStore.getGraphNames().toList()
                    val query = Query()
                    tmp.forEach {
                        DistributedTripleStore.dropGraph(query, it)
                    }
                    query.commit()
                    executeBinaryTest(it, false)
                }
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    println("executed testcases : $testcases")
}


fun executeBinaryTest(filename: String, detailedLog: Boolean) {
    val buffer = File(filename).readAsDynamicByteArray()
    executeBinaryTest(buffer)
}

fun executeBinaryTest(buffer: DynamicByteArray) {
    val query = Query()
    var node1: OPBase = OPNothing(query)
    var node2: OPBase = OPNothing(query)
    var node3: OPBase = OPNothing(query)
    var node4: OPBase = OPNothing(query)
    var node5: OPBase = OPNothing(query)
    val lOptimizer = LogicalOptimizer(query)
    val pOptimizer = PhysicalOptimizer(query)
    val dOptimizer = KeyDistributionOptimizer(query)
    try {
        val optimizerEnabledCount = nextInt(query, buffer, EOptimizerID.values().size)
        ExecuteOptimizer.enabledOptimizers.clear()
        for (o in 0 until optimizerEnabledCount) {
            val optimizer = EOptimizerID.values()[nextInt(query, buffer, EOptimizerID.values().size)]
            ExecuteOptimizer.enabledOptimizers[optimizer] = true
        }
    } catch (e: Throwable) {
    }
    val backupOptimizers = ExecuteOptimizer.enabledOptimizers
    ExecuteOptimizer.enabledOptimizers.clear()
    var globalSparql = mutableListOf<String>()
    hadArrayIndexOutOfBoundsException = false
    while (!hadArrayIndexOutOfBoundsException) {
        try {
            var node1: OPBase
            try {
                if (buffer.pos < buffer.data.size / 2) {
                    try {
                        val backuppos = buffer.pos
                        val graphNameTmp = (nextStringValueTyped(query, buffer, ValueEnum.ValueIri))
                        val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
                        val graph = DistributedTripleStore.getNamedGraph(query, graphName, true)
                        val s = fromBinaryValueIriOrBnodeOrVar(query, buffer)
                        val p = fromBinaryValueIriOrVar(query, buffer)
                        val o = fromBinaryAOPConstOrVar(query, buffer)
                        val idx = EIndexPattern.values()[nextInt(query, buffer, EIndexPattern.values().size)]
                        val tripleCount = nextInt(query, buffer, MAX_TRIPLES)
                        for (i in 0 until tripleCount)
                            graph.addData(Array(3) { ValueDefinition.create(nextStringValue(query, buffer)) })
                    } catch (e: Throwable) {
                    }
                    query.commit()
                    if (buffer.pos > buffer.data.size / 2)
                        buffer.pos = buffer.data.size / 2
                }
                node1 = OPNothing(query)
                node2 = OPNothing(query)
                node3 = OPNothing(query)
                node4 = OPNothing(query)
                node1 = fromBinaryPOPLOP(query, buffer)
            } catch (e: ExceptionTopLevelOperator) {
                node1 = e.data
            }
            node2 = lOptimizer.optimizeCall(node1)
            node3 = pOptimizer.optimizeCall(node2)
            node4 = dOptimizer.optimizeCall(node3) as POPBase
            val sparql = node4.toSparqlQuery()
            globalSparql.add(sparql)
        } catch (e: Throwable) {
        }
    }
    for (gname in DistributedTripleStore.getGraphNames(true)) {
        val g = DistributedTripleStore.getNamedGraph(query, gname)
        val iterator = g.getIterator(arrayOf(AOPVariable(query, "s"), AOPVariable(query, "p"), AOPVariable(query, "o")), EIndexPattern.SPO)
        val data = QueryResultToXML.toXML(iterator).first()
        var hasData = false
        var sparql = "INSERT DATA { "
        if (gname != PersistentStoreLocal.defaultGraphName)
            sparql += "GRAPH <$gname> "
        if (data.tag != "sparql")
            throw Exception("can only parse sparql xml into an iterator")
        for (node in data["results"]!!.childs) {
            val result = mutableMapOf<String, String>()
            for (v in node.childs) {
                val name = v.attributes["name"]
                val child = v.childs.first()
                val content = child.content
                val value = when {
                    child.tag == "uri" -> "<" + content + ">"
                    child.tag == "literal" && child.attributes["datatype"] != null -> "\"" + content + "\"^^<" + child.attributes["datatype"] + ">"
                    child.tag == "literal" && child.attributes["xml:lang"] != null -> "\"" + content + "\"@" + child.attributes["xml:lang"]
                    child.tag == "bnode" -> "_:" + content
                    else -> "\"" + content + "\""
                }
                result[name!!] = value!!
            }
            hasData = true
            sparql += "( " + result["s"] + " " + result["p"] + " " + result["o"] + " " + ")."
        }
        sparql += "}"
        if (hasData)
            globalSparql.add(0, sparql)
    }
    for (i in 0 until 3) {
        ExecuteOptimizer.enabledOptimizers.clear()
        if (1 > 0)
            backupOptimizers.forEach { k, v ->
                ExecuteOptimizer.enabledOptimizers[k] = v
            }
        val jena = JenaRequest()
        P2P.execGraphClearAll(query)
        query.commit()
        try {
            for (sparql in globalSparql) {
                var e1: Throwable? = null
                var e2: Throwable? = null
                var output = XMLElement("crashed")
                var isUpdate = false
                node1 = OPNothing(query)
                node2 = OPNothing(query)
                node3 = OPNothing(query)
                node4 = OPNothing(query)
                node5 = OPNothing(query)
                try {
                    //println("sparql::" + sparql)
                    val lcit = LexerCharIterator(sparql)
                    val tit = TokenIteratorSPARQLParser(lcit)
                    val ltit = LookAheadTokenIterator(tit, 3)
                    val parser = SPARQLParser(ltit)
                    val ast_node = parser.expr()
                    node1 = ast_node.visit(OperatorGraphVisitor(query))
                    node2 = lOptimizer.optimizeCall(node1)
                    node3 = pOptimizer.optimizeCall(node2)
                    node4 = dOptimizer.optimizeCall(node3) as POPBase
                    node5 = node4.cloneOP()
                    SanityCheck.checkEQ({ node4 }, { node5 })
                    node5 = node1.cloneOP()
                    SanityCheck.checkEQ({ node1 }, { node5 })
                    val node1xml = node1.toXMLElement()
                    node5 = XMLElement.convertToOPBase(query, node1xml)
                    SanityCheck.checkEQ({ node1 }, { node5 })
                    isUpdate = node4 is POPGraphOperation || node4 is POPModifyData || node4 is POPModify
                    output = QueryResultToXML.toXML(node4).first()
                    query.commit()
                } catch (e: Throwable) {
                    e1 = e
                }
                if (isUpdate) {
                    try {
                        val expected = jena.requestUpdate(sparql)
                    } catch (e: Throwable) {
                        if (e is ExceptionJenaBug)
                            throw e
                        e2 = e
                    }
                } else {
                    var expected = XMLElement("crashed")
                    try {
                        expected = jena.requestQuery(sparql)
                    } catch (e: Throwable) {
                        if (e is ExceptionJenaBug)
                            throw e
                        e2 = e
                    }
                    if (!expected.myEqualsUnclean(output)) {
                        println(node1)
                        println(node2)
                        println(node3)
                        println(node4)
                        println(node5)
                        println("expected :: $expected")
                        println("output :: $output")
                        e1?.printStackTrace()
                        e2?.printStackTrace()
                        throw Exception("failed $sparql")
                    }
                }
            }
        } catch (e: ExceptionJenaBug) {
        } finally {
            jena.finalize()
        }
    }
}
