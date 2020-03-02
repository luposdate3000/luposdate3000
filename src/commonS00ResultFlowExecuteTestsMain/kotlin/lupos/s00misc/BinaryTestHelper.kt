package lupos.s00misc

import lupos.*
import lupos.s00misc.*
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
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
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPDateTime
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
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
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.LOPRename
import lupos.s04logicalOperators.singleinput.LOPSort
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.LogicalOptimizer
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class ExceptionTopLevelOperator(val data: OPBase) : Exception("this object needs to be toplevel")

val MAX_SET = 10
val MAX_VARIABLES = 10
val MAX_LIMIT = 100
val MAX_OFFSET = 100
val MAX_TRIPLES = 400
val MAX_GRAPH_NAMES = 10

val testDictionaryVarName = ResultSetDictionary()
val testDictionaryValue = ResultSetDictionary()
val testDictionaryValueTyped = mutableMapOf<EOperatorID, ResultSetDictionary>()


fun nextStringVarName(buffer: DynamicByteArray): String {
    return testDictionaryVarName.getValue(nextInt(buffer, testDictionaryVarName.mapLTS.size()))!!
}

fun nextStringValue(buffer: DynamicByteArray): String {
    return testDictionaryValue.getValue(nextInt(buffer, testDictionaryValue.mapLTS.size()))!!
}

fun nextStringValueTyped(buffer: DynamicByteArray, type: EOperatorID): String {
    val idx = nextInt(buffer, testDictionaryValue.mapLTS.size())
    val tmp = AOPVariable.calculate(testDictionaryValue.mapLTS[idx])
    if (tmp.operatorID == type)
        return testDictionaryValue.mapLTS[idx]!!
    return testDictionaryValueTyped[type]!!.getValue(idx % testDictionaryValueTyped[type]!!.mapLTS.size())!!
}

fun nextInt(buffer: DynamicByteArray, maxValue: Int): Int {
    val tmp = buffer.getNextInt()
    return tmp % maxValue
}

fun nextInt(buffer: DynamicByteArray): Int {
    return buffer.getNextInt()
}

fun fromBinary(dictionary: ResultSetDictionary, buffer: DynamicByteArray): OPBase {
    try {
        var id = buffer.getInt(buffer.pos)
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size)
            operatorID = EOperatorID.values()[id % EOperatorID.values().size]
        else
            operatorID = EOperatorID.values()[id]
        if (operatorID == EOperatorID.OPNothingID)
            return OPNothing()
        if (EOperatorIDLOP.contains(operatorID))
            return fromBinaryLOP(dictionary, buffer)
        if (EOperatorIDPOP.contains(operatorID))
            return fromBinaryPOP(dictionary, buffer)
        if (EOperatorIDAOP.contains(operatorID))
            return fromBinaryAOP(dictionary, buffer)
    } catch (e: ArrayIndexOutOfBoundsException) {
    }
    return OPNothing()
}

fun hasChildRecoursive(base: OPBase, search: List<EOperatorID>, skip: List<EOperatorID>): Boolean {
    if (search.contains(base.operatorID))
        return true
    for (c in base.children)
        if (skip.contains(c.operatorID) && hasChildRecoursive(c, search, skip))
            return true
    return false
}

fun fromBinaryPOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): POPBase {
    try {
        var id = nextInt(buffer)
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDPOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDPOP[id % EOperatorIDPOP.size]
        else
            operatorID = EOperatorID.values()[id]

        when (operatorID) {
            EOperatorID.POPEmptyRowID -> {
                return POPEmptyRow(dictionary)
            }
            EOperatorID.POPUnionID -> {
                val childA = fromBinary(dictionary, buffer)
                val childB = fromBinary(dictionary, buffer)
                return POPUnion(dictionary, childA, childB)
            }
            EOperatorID.POPJoinHashMapID -> {
                val childA = fromBinary(dictionary, buffer)
                val childB = fromBinary(dictionary, buffer)
                val optional = DynamicByteArray.intToBool(nextInt(buffer, 2))
                return POPJoinHashMap(dictionary, childA, childB, optional)
            }
            EOperatorID.POPRenameID -> {
                val nameTo = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val nameFrom = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val child = fromBinary(dictionary, buffer)
                return POPRename(dictionary, nameTo, nameFrom, child)
            }
            EOperatorID.POPFilterID -> {
                val filter = fromBinaryAOP(dictionary, buffer)
                val child = fromBinary(dictionary, buffer)
                return POPFilter(dictionary, filter, child)
            }
            EOperatorID.POPMakeBooleanResultID -> {
                val child = fromBinary(dictionary, buffer)
                throw ExceptionTopLevelOperator(POPMakeBooleanResult(dictionary, child))
            }
            EOperatorID.POPFilterExactID -> {
                val name = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val value = nextStringValue(buffer)
                val child = fromBinary(dictionary, buffer)
                return POPFilterExact(dictionary, name, value, child)
            }
            EOperatorID.POPBindID -> {
                val name = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val value = fromBinaryAOP(dictionary, buffer)
                val child = fromBinary(dictionary, buffer)
                return POPBind(dictionary, name, value, child)
            }
            EOperatorID.POPSortID -> {
                val sortBy = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val sortOrder = DynamicByteArray.intToBool(nextInt(buffer, 2))
                val child = fromBinary(dictionary, buffer)
                return POPSort(dictionary, sortBy, sortOrder, child)
            }
            EOperatorID.POPDistinctID -> {
                val child = fromBinary(dictionary, buffer)
                return POPDistinct(dictionary, child)
            }
            EOperatorID.POPProjectionID -> {
                val childCount = nextInt(buffer, MAX_VARIABLES)
                if (childCount == 0)
                    return fromBinaryPOP(dictionary, buffer)
                val variables = mutableListOf<AOPVariable>()
                for (i in 0 until childCount)
                    variables.add(fromBinaryAOP(dictionary, buffer) as AOPVariable)
                val child = fromBinary(dictionary, buffer)
                return POPProjection(dictionary, variables, child)
            }
            EOperatorID.POPLimitID -> {
                var value = nextInt(buffer, MAX_LIMIT)
                val child = fromBinary(dictionary, buffer)
                if (hasChildRecoursive(child, listOf(EOperatorID.POPLimitID, EOperatorID.LOPLimitID), listOf(EOperatorID.POPLimitID, EOperatorID.LOPLimitID, EOperatorID.POPOffsetID, EOperatorID.LOPOffsetID))) {
                    if (child is POPBase)
                        return child
                    return POPEmptyRow(dictionary)
                }
                return POPLimit(dictionary, value, child)
            }
            EOperatorID.POPOffsetID -> {
                var value = nextInt(buffer, MAX_OFFSET)
                val child = fromBinary(dictionary, buffer)
                return POPOffset(dictionary, value, child)
            }
            EOperatorID.TripleStoreIteratorGlobalID, EOperatorID.TripleInsertIteratorID, EOperatorID.TripleStoreIteratorLocalFilterID -> {
                val graphName = (nextStringValueTyped(buffer, EOperatorID.AOPIriID) as AOPIri).iri
                val graph = DistributedTripleStore.createGraph(graphName)
                val s = fromBinaryAOP(dictionary, buffer)
                val p = fromBinaryAOP(dictionary, buffer)
                val o = fromBinaryAOP(dictionary, buffer)
                val idx = EIndexPattern.values()[nextInt(buffer, EIndexPattern.values().size)]
                val tripleCount = nextInt(buffer, MAX_TRIPLES)
                for (i in 0 until tripleCount) {
                    val st = AOPVariable.calculate(nextStringValue(buffer))
                    val pt = AOPVariable.calculate(nextStringValue(buffer))
                    val ot = AOPVariable.calculate(nextStringValue(buffer))
                    graph.addData(1L, listOf(st, pt, ot))
                }
                DistributedTripleStore.commit(1L)
                return DistributedTripleStore.getNamedGraph(graphName).getIterator(1L, dictionary, s, p, o, idx)
            }
            EOperatorID.POPValuesID -> {
                val variables = mutableListOf<String>()
                val values = mutableListOf<MutableMap<String, String>>()
                val variableCount = nextInt(buffer, MAX_VARIABLES)
                for (i in 0 until variableCount)
                    variables.add(nextStringVarName(buffer))
                val valuesCount = nextInt(buffer, MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val map = mutableMapOf<String, String>()
                    values.add(map)
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(nextInt(buffer, 2))
                        if (!isNull) {
                            val value = nextStringValue(buffer)
                            map[variables[i]] = value
                        }
                    }
                }
                return POPValues(dictionary, variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryPOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return POPEmptyRow(dictionary)
    }
}

fun fromBinaryLOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): LOPBase {
    try {
        var id = nextInt(buffer)
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDLOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDLOP[id % EOperatorIDLOP.size]
        else
            operatorID = EOperatorID.values()[id]

        when (operatorID) {
            EOperatorID.LOPGraphOperationID -> {
                val action = EGraphOperationType.values()[nextInt(buffer, EGraphOperationType.values().size)]
                val silent = DynamicByteArray.intToBool(nextInt(buffer, 2))
                val graph1type = EGraphRefType.values()[nextInt(buffer, EGraphRefType.values().size)]
                val graph1name = (nextStringValueTyped(buffer, EOperatorID.AOPIriID) as AOPIri).iri
                val graph2type = EGraphRefType.values()[nextInt(buffer, EGraphRefType.values().size)]
                val graph2name = (nextStringValueTyped(buffer, EOperatorID.AOPIriID) as AOPIri).iri
                return LOPGraphOperation(action, silent, graph1type, graph1name, graph2type, graph2name)
            }
            EOperatorID.LOPMakeBooleanResultID -> {
                val child = fromBinary(dictionary, buffer)
                throw ExceptionTopLevelOperator(LOPMakeBooleanResult(child))
            }
            EOperatorID.LOPUnionID -> {
                val childA = fromBinary(dictionary, buffer)
                val childB = fromBinary(dictionary, buffer)
                return LOPUnion(childA, childB)
            }
            EOperatorID.LOPJoinID -> {
                val childA = fromBinary(dictionary, buffer)
                val childB = fromBinary(dictionary, buffer)
                val optional = DynamicByteArray.intToBool(nextInt(buffer, 2))
                return LOPJoin(childA, childB, optional)
            }
            EOperatorID.LOPRenameID -> {
                val nameTo = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val nameFrom = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val child = fromBinary(dictionary, buffer)
                return LOPRename(nameTo, nameFrom, child)
            }
            EOperatorID.LOPFilterID -> {
                val filter = fromBinaryAOP(dictionary, buffer)
                val child = fromBinary(dictionary, buffer)
                return LOPFilter(filter, child)
            }
            EOperatorID.LOPBindID -> {
                val name = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val value = fromBinaryAOP(dictionary, buffer)
                val child = fromBinary(dictionary, buffer)
                return LOPBind(name, value, child)
            }
            EOperatorID.LOPSortID -> {
                val sortBy = fromBinaryAOP(dictionary, buffer) as AOPVariable
                val sortOrder = DynamicByteArray.intToBool(nextInt(buffer, 2))
                val child = fromBinary(dictionary, buffer)
                return LOPSort(sortOrder, sortBy, child)
            }
            EOperatorID.LOPDistinctID -> {
                val child = fromBinary(dictionary, buffer)
                return LOPDistinct(child)
            }
            EOperatorID.LOPProjectionID -> {
                val childCount = nextInt(buffer, MAX_VARIABLES)
                val variables = mutableListOf<AOPVariable>()
                for (i in 0 until childCount)
                    variables.add(fromBinaryAOP(dictionary, buffer) as AOPVariable)
                val child = fromBinary(dictionary, buffer)
                return LOPProjection(variables, child)
            }
            EOperatorID.LOPLimitID -> {
                var value = nextInt(buffer, MAX_LIMIT)
                val child = fromBinary(dictionary, buffer)
                if (hasChildRecoursive(child, listOf(EOperatorID.POPLimitID, EOperatorID.LOPLimitID), listOf(EOperatorID.POPLimitID, EOperatorID.LOPLimitID, EOperatorID.POPOffsetID, EOperatorID.LOPOffsetID))) {
                    if (child is LOPBase)
                        return child
                    return OPNothing()
                }
                return LOPLimit(value, child)
            }
            EOperatorID.LOPOffsetID -> {
                var value = nextInt(buffer, MAX_OFFSET)
                val child = fromBinary(dictionary, buffer)
                return LOPOffset(value, child)
            }
            EOperatorID.LOPTripleID -> {
                val graphNameTmp = (nextStringValueTyped(buffer, EOperatorID.AOPIriID))
                val graphName = graphNameTmp.substring(1, graphNameTmp.length - 1)
                val graph = DistributedTripleStore.createGraph(graphName)
                val s = fromBinaryAOP(dictionary, buffer)
                val p = fromBinaryAOP(dictionary, buffer)
                val o = fromBinaryAOP(dictionary, buffer)
                val idx = EIndexPattern.values()[nextInt(buffer, EIndexPattern.values().size)]
                val tripleCount = nextInt(buffer, MAX_TRIPLES)
                for (i in 0 until tripleCount) {
                    val st = AOPVariable.calculate(nextStringValue(buffer))
                    val pt = AOPVariable.calculate(nextStringValue(buffer))
                    val ot = AOPVariable.calculate(nextStringValue(buffer))
                    graph.addData(1L, listOf(st, pt, ot))
                }
                DistributedTripleStore.commit(1L)
                return LOPTriple(s, p, o, graphName, false)
            }
            EOperatorID.LOPValuesID -> {
                val variables = mutableListOf<AOPVariable>()
                val values = mutableListOf<AOPValue>()
                val variableCount = nextInt(buffer, MAX_VARIABLES)
                for (i in 0 until variableCount)
                    variables.add(AOPVariable(nextStringVarName(buffer)))
                val valuesCount = nextInt(buffer, MAX_TRIPLES)
                for (j in 0 until valuesCount) {
                    val list = mutableListOf<AOPConstant>()
                    for (i in 0 until variableCount) {
                        val isNull = DynamicByteArray.intToBool(nextInt(buffer, 2))
                        if (!isNull) {
                            val value = nextStringValue(buffer)
                            list.add(AOPVariable.calculate(value))
                        } else
                            list.add(AOPUndef())
                    }
                    values.add(AOPValue(list))
                }
                return LOPValues(variables, values)
            }
            else -> throw Exception("BinaryHelper.fromBinaryLOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return OPNothing()
    }
}

fun fromBinaryAOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): AOPBase {
    try {
        var id = nextInt(buffer)
        val operatorID: EOperatorID
        if (id > EOperatorID.values().size || !EOperatorIDAOP.contains(EOperatorID.values()[id]))
            operatorID = EOperatorIDAOP[id % EOperatorIDAOP.size]
        else
            operatorID = EOperatorID.values()[id]

        when (operatorID) {
            EOperatorID.AOPBuildInCallIsIriID -> {
                val child = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallIsIri(child)
            }
            EOperatorID.AOPAndID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPAnd(childA, childB)
            }
            EOperatorID.AOPLEQID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPLEQ(childA, childB)
            }
            EOperatorID.AOPMultiplicationID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPMultiplication(childA, childB)
            }
            EOperatorID.AOPSetID -> {
                val childCount = nextInt(buffer, MAX_SET)
                val list = mutableListOf<AOPBase>()
                for (i in 0 until childCount)
                    list.add(fromBinaryAOP(dictionary, buffer))
                return AOPSet(list)
            }
            EOperatorID.AOPValueID -> {
                val childCount = nextInt(buffer, MAX_SET)
                val list = mutableListOf<AOPConstant>()
                for (i in 0 until childCount) {
                    list.add(AOPVariable.calculate(nextStringValue(buffer)))
                }
                return AOPValue(list)
            }
            EOperatorID.AOPBuildInCallSTRUUIDID -> {
                return AOPBuildInCallSTRUUID()
            }
            EOperatorID.AOPBuildInCallUUIDID -> {
                return AOPBuildInCallUUID()
            }
            EOperatorID.AOPSimpleLiteralID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPSimpleLiteralID))
            }
            EOperatorID.AOPDecimalID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPDecimalID))
            }
            EOperatorID.AOPLanguageTaggedLiteralID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPLanguageTaggedLiteralID))
            }
            EOperatorID.AOPTypedLiteralID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPTypedLiteralID))
            }
            EOperatorID.AOPDoubleID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPDoubleID))
            }
            EOperatorID.AOPLTID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPLT(childA, childB)
            }
            EOperatorID.AOPGTID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPGT(childA, childB)
            }
            EOperatorID.AOPSubtractionID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPSubtraction(childA, childB)
            }
            EOperatorID.AOPNEQID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPNEQ(childA, childB)
            }
            EOperatorID.AOPNotID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPNot(childA)
            }
            EOperatorID.AOPInID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPIn(childA, childB)
            }
            EOperatorID.AOPNotInID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPNotIn(childA, childB)
            }
            EOperatorID.AOPBuildInCallBOUNDID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallBOUND(childA)
            }
            EOperatorID.AOPBuildInCallTIMEZONEID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallTIMEZONE(childA)
            }
            EOperatorID.AOPAdditionID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPAddition(childA, childB)
            }
            EOperatorID.AOPBuildInCallCONTAINSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallCONTAINS(childA, childB)
            }
            EOperatorID.AOPBuildInCallIsNUMERICID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallIsNUMERIC(childA)
            }
            EOperatorID.AOPBuildInCallLANGMATCHESID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallLANGMATCHES(childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRENDSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTRENDS(childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRSTARTSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTRSTARTS(childA, childB)
            }
            EOperatorID.AOPEQID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPEQ(childA, childB)
            }
            EOperatorID.AOPGEQID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPGEQ(childA, childB)
            }
            EOperatorID.AOPOrID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPOr(childA, childB)
            }
            EOperatorID.AOPVariableID -> {
                val name = nextStringVarName(buffer)
                return AOPVariable(name)
            }
            EOperatorID.AOPBuildInCallABSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallABS(childA)
            }
            EOperatorID.AOPBuildInCallBNODE0ID -> {
                return AOPBuildInCallBNODE0()
            }
            EOperatorID.AOPBuildInCallBNODE1ID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallBNODE1(childA)
            }
            EOperatorID.AOPBuildInCallCEILID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallCEIL(childA)
            }
            EOperatorID.AOPBuildInCallCONCATID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallCONCAT(childA, childB)
            }
            EOperatorID.AOPBuildInCallDATATYPEID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallDATATYPE(childA)
            }
            EOperatorID.AOPBuildInCallDAYID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallDAY(childA)
            }
            EOperatorID.AOPBuildInCallFLOORID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallFLOOR(childA)
            }
            EOperatorID.AOPBuildInCallHOURSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallHOURS(childA)
            }
            EOperatorID.AOPBuildInCallIFID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                val childC = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallIF(childA, childB, childC)
            }
            EOperatorID.AOPBuildInCallIRIID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val prefix = nextStringValue(buffer)
                return AOPBuildInCallIRI(childA, prefix)
            }
            EOperatorID.AOPBuildInCallLANGID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallLANG(childA)
            }
            EOperatorID.AOPBuildInCallLCASEID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallLCASE(childA)
            }
            EOperatorID.AOPBuildInCallMD5ID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallMD5(childA)
            }
            EOperatorID.AOPBuildInCallMINUTESID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallMINUTES(childA)
            }
            EOperatorID.AOPBuildInCallMONTHID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallMONTH(childA)
            }
            EOperatorID.AOPBuildInCallROUNDID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallROUND(childA)
            }
            EOperatorID.AOPBuildInCallSECONDSID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSECONDS(childA)
            }
            EOperatorID.AOPBuildInCallSHA1ID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSHA1(childA)
            }
            EOperatorID.AOPBuildInCallSHA256ID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSHA256(childA)
            }
            EOperatorID.AOPBuildInCallSTRDTID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTRDT(childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTR(childA)
            }
            EOperatorID.AOPBuildInCallSTRLANGID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTRLANG(childA, childB)
            }
            EOperatorID.AOPBuildInCallSTRLENID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallSTRLEN(childA)
            }
            EOperatorID.AOPBuildInCallTZID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallTZ(childA)
            }
            EOperatorID.AOPBuildInCallUCASEID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallUCASE(childA)
            }
            EOperatorID.AOPBuildInCallURIID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val prefix = nextStringValue(buffer)
                return AOPBuildInCallURI(childA, prefix)
            }
            EOperatorID.AOPBuildInCallYEARID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                return AOPBuildInCallYEAR(childA)
            }
            EOperatorID.AOPDateTimeID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPDateTimeID))
            }
            EOperatorID.AOPIntegerID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPIntegerID))
            }
            EOperatorID.AOPBnodeID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPBnodeID))
            }
            EOperatorID.AOPIriID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPIriID))
            }
            EOperatorID.AOPUndefID -> {
                return AOPUndef()
            }
            EOperatorID.AOPAggregationID -> {
                val type = Aggregation.values()[nextInt(buffer, Aggregation.values().size)]
                val distinct = DynamicByteArray.intToBool(nextInt(buffer, 2))
                val count = nextInt(buffer, 2)
                val variables = Array(count) { fromBinaryAOP(dictionary, buffer) }
                return AOPAggregation(type, distinct, variables)
            }
            EOperatorID.AOPBooleanID -> {
                return AOPVariable.calculate(nextStringValueTyped(buffer, EOperatorID.AOPBooleanID))
            }
            EOperatorID.AOPDivisionID -> {
                val childA = fromBinaryAOP(dictionary, buffer)
                val childB = fromBinaryAOP(dictionary, buffer)
                return AOPDivision(childA, childB)
            }
            else -> throw Exception("BinaryHelper.fromBinaryAOP ${operatorID} undefined")
        }
    } catch (e: ArrayIndexOutOfBoundsException) {
        return AOPUndef()
    }
}

fun executeBinaryTests(folder: String) {
    var testcases = 0
    try {
        File(folder).walk {
            if (it.endsWith(".bin")) {
                testcases++
                {
                    val tmp = DistributedTripleStore.getGraphNames().toList()
                    tmp.forEach {
                        DistributedTripleStore.dropGraph(it)
                    }
                }
                executeBinaryTest(it, false)
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    println("executed testcases : $testcases")
}


fun executeBinaryTest(filename: String, detailedLog: Boolean) {
    val dictionary = ResultSetDictionary()
    var input: OPBase? = null
    val buffer = File(filename).readAsDynamicByteArray()
    val optimizerEnabledCount = nextInt(buffer, EOptimizerID.values().size)
    ExecuteOptimizer.enabledOptimizers.clear()
    for (o in 0 until optimizerEnabledCount) {
        val optimizer = EOptimizerID.values()[nextInt(buffer, EOptimizerID.values().size)]
        ExecuteOptimizer.enabledOptimizers[optimizer] = true
    }
    try {
        input = fromBinary(dictionary, buffer)
    } catch (e: ExceptionTopLevelOperator) {
        input = e.data
    }
    println("execute test $filename ${ExecuteOptimizer.enabledOptimizers}")

    if (input!! is POPBase) {
        val sparql = input.toSparqlQuery()
        println("sparql::" + sparql)
        val jena = JenaRequest()
        val expected = jena.requestQuery(sparql)
        val output = QueryResultToXML.toXML(input!! as POPBase).first()
        if (!expected.myEqualsUnclean(output)) {
            if (detailedLog) {
                println("a")
                println(input!!.toXMLElement().toPrettyString())
                println(expected.toPrettyString())
                println(output.toPrettyString())
            } else
                println("failed ${input!!.toXMLElement().toPrettyString().length} $filename ${input!!.toXMLElement().toPrettyString()}")
        }
//          require(expected.myEqualsUnclean(output))
        val output2 = QueryResultToXML.toXML(input!!.cloneOP() as POPBase).first()
        if (!expected.myEqualsUnclean(output2)) {
            if (detailedLog) {
                println("b")
                println(input!!.toXMLElement().toPrettyString())
                println(expected.toPrettyString())
                println(output2.toPrettyString())
            } else
                println("failed ${input!!.toXMLElement().toPrettyString().length} $filename ${input!!.toXMLElement().toPrettyString()}")
        }
//          require(expected.myEqualsUnclean(output2))
    } else {
        val lop_node = input!! as LOPBase
        val lOptimizer = LogicalOptimizer(1L, dictionary)
        val pOptimizer = PhysicalOptimizer(1L, dictionary)
        val dOptimizer = KeyDistributionOptimizer(1L, dictionary)
        val lop_node2 = lOptimizer.optimizeCall(lop_node)
        val pop_node = pOptimizer.optimizeCall(lop_node2)
        val input2 = dOptimizer.optimizeCall(pop_node) as POPBase
        val output = QueryResultToXML.toXML(input2).first()
        val sparql = pop_node.toSparqlQuery()
        println("sparql::" + sparql)
        val jena = JenaRequest()
        val expected = jena.requestQuery(sparql)
        if (!expected.myEqualsUnclean(output)) {
            if (detailedLog) {
                println("c")
                println(input!!.toXMLElement().toPrettyString())
                println(input2!!.toXMLElement().toPrettyString())
                println(expected.toPrettyString())
                println(output.toPrettyString())
            } else
                println("failed ${input!!.toXMLElement().toPrettyString().length} $filename ${input!!.toXMLElement().toPrettyString()}")
        }
//          require(expected.myEqualsUnclean(output))
        val output2 = QueryResultToXML.toXML(input2.cloneOP() as POPBase).first()
        if (!expected.myEqualsUnclean(output2)) {
            if (detailedLog) {
                println("d")
                println(input!!.toXMLElement().toPrettyString())
                println(input2!!.toXMLElement().toPrettyString())
                println(expected.toPrettyString())
                println(output2.toPrettyString())
            } else
                println("failed ${input!!.toXMLElement().toPrettyString().length} $filename ${input!!.toXMLElement().toPrettyString()}")
        }
//          require(expected.myEqualsUnclean(output2))
    }
}

fun executeBinaryTest(buffer: DynamicByteArray) {
    val dictionary = ResultSetDictionary()
    val optimizerEnabledCount = nextInt(buffer, EOptimizerID.values().size)
    ExecuteOptimizer.enabledOptimizers.clear()
    for (o in 0 until optimizerEnabledCount) {
        val optimizer = EOptimizerID.values()[nextInt(buffer, EOptimizerID.values().size)]
        ExecuteOptimizer.enabledOptimizers[optimizer] = true
    }
    var input: OPBase = OPNothing()
    try {
        input = fromBinary(dictionary, buffer)
    } catch (e: ExceptionTopLevelOperator) {
        input = e.data
    }
    val lOptimizer = LogicalOptimizer(1L, dictionary)
    val pOptimizer = PhysicalOptimizer(1L, dictionary)
    val dOptimizer = KeyDistributionOptimizer(1L, dictionary)
    if (input is POPBase) {
        val output = QueryResultToXML.toXML(input).first()
        val output2 = QueryResultToXML.toXML(input.cloneOP() as POPBase).first()
        require(output.myEquals(output2))
    } else if (input is LOPBase) {
        val node1 = lOptimizer.optimizeCall(input)
        val node2 = pOptimizer.optimizeCall(node1)
        val node3 = dOptimizer.optimizeCall(node2) as POPBase
        val output = QueryResultToXML.toXML(node3).first()
        val output2 = QueryResultToXML.toXML(node3.cloneOP() as POPBase).first()
        require(output.myEquals(output2))
        ExecuteOptimizer.enabledOptimizers.clear()
        val node4 = lOptimizer.optimizeCall(input)
        val node5 = pOptimizer.optimizeCall(node4)
        val input3 = dOptimizer.optimizeCall(node5) as POPBase
        val output3 = QueryResultToXML.toXML(input3).first()
        val output4 = QueryResultToXML.toXML(input3.cloneOP() as POPBase).first()
        require(output.myEqualsUnclean(output3))
        require(output3.myEquals(output4))
    } else if (input is AOPBase) {
        val node1 = lOptimizer.optimizeCall(input)
        val node2 = pOptimizer.optimizeCall(node1)
        val node3 = dOptimizer.optimizeCall(node2) as AOPBase
        val resultSet = ResultSet(dictionary)
        for (v in node3.getRequiredVariableNamesRecoursive())
            resultSet.createVariable(v)
        val resultRow = resultSet.createResultRow()
        for (v in node3.getRequiredVariableNamesRecoursive())
            resultRow[resultSet.createVariable(v)] = resultSet.createValue(testDictionaryValue.getValue(v.hashCode() % testDictionaryValue.mapLTS.size()))
        val output = node3.calculate(resultSet, resultRow)
        val output2 = input.calculate(resultSet, resultRow)
        require(output == output2)
    }
}
