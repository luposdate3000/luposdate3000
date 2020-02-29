package lupos.s00misc

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
import lupos.s04arithmetikOperators.noinput.AOPConstant
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
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
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
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPBindUndefined
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.QueryResultToXML
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.DistributedTripleStore


fun fromBinary(dictionary: ResultSetDictionary, buffer: DynamicByteArray): OPBase {
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
    throw Exception("BinaryHelper.fromBinary ${operatorID} undefined")
}

fun fromBinaryPOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): POPBase {
    var id = buffer.getNextInt()
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
            val optional = DynamicByteArray.intToBool(buffer.getNextInt())
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
        EOperatorID.POPBindUndefinedID -> {
            val name = fromBinaryAOP(dictionary, buffer) as AOPVariable
            val child = fromBinary(dictionary, buffer)
            return POPBindUndefined(dictionary, name, child)
        }
        EOperatorID.POPFilterExactID -> {
            val name = fromBinaryAOP(dictionary, buffer) as AOPVariable
            val value = buffer.getNextString()
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
            val sortOrder = DynamicByteArray.intToBool(buffer.getNextInt())
            val child = fromBinary(dictionary, buffer)
            return POPSort(dictionary, sortBy, sortOrder, child)
        }
        EOperatorID.POPDistinctID -> {
            val child = fromBinary(dictionary, buffer)
            return POPDistinct(dictionary, child)
        }
        EOperatorID.POPProjectionID -> {
            val childCount = buffer.getNextInt()
            val variables = mutableListOf<AOPVariable>()
            for (i in 0 until childCount)
                variables.add(fromBinaryAOP(dictionary, buffer) as AOPVariable)
            val child = fromBinary(dictionary, buffer)
            return POPProjection(dictionary, variables, child)
        }
        EOperatorID.POPLimitID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return POPLimit(dictionary, value, child)
        }
        EOperatorID.POPOffsetID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return POPOffset(dictionary, value, child)
        }
        EOperatorID.TripleStoreIteratorGlobalID -> {
            var graphName = "graph" + DistributedTripleStore.getGraphNames().size
            val graph = DistributedTripleStore.createGraph(graphName)
            val s = fromBinaryAOP(dictionary, buffer)
            val p = fromBinaryAOP(dictionary, buffer)
            val o = fromBinaryAOP(dictionary, buffer)
            val idx = EIndexPattern.values()[buffer.getNextInt()]
            val tripleCount = buffer.getNextInt()
            for (i in 0 until tripleCount) {
                val st = AOPVariable.calculate(buffer.getNextString())
                val pt = AOPVariable.calculate(buffer.getNextString())
                val ot = AOPVariable.calculate(buffer.getNextString())
                graph.addData(1L, listOf(st, pt, ot))
            }
            DistributedTripleStore.commit(1L)
            return DistributedTripleStore.getNamedGraph(graphName).getIterator(1L, dictionary, s, p, o, idx)
        }
        EOperatorID.POPValuesID -> {
            val variables = mutableListOf<String>()
            val values = mutableListOf<MutableMap<String, String>>()
            val variableCount = buffer.getNextInt()
            for (i in 0 until variableCount)
                variables.add(buffer.getNextString())
            val valuesCount = buffer.getNextInt()
            for (j in 0 until valuesCount) {
                val map = mutableMapOf<String, String>()
                values.add(map)
                for (i in 0 until variableCount) {
                    val isNull = DynamicByteArray.intToBool(buffer.getNextInt())
                    if (!isNull) {
                        val value = buffer.getNextString()
                        map[variables[i]] = value
                    }
                }
            }
            return POPValues(dictionary, variables, values)
        }
        else -> throw Exception("BinaryHelper.fromBinaryPOP ${operatorID} undefined")
    }
}

fun fromBinaryLOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): LOPBase {
    var id = buffer.getNextInt()
    val operatorID: EOperatorID
    if (id > EOperatorID.values().size || !EOperatorIDLOP.contains(EOperatorID.values()[id]))
        operatorID = EOperatorIDLOP[id % EOperatorIDLOP.size]
    else
        operatorID = EOperatorID.values()[id]

    when (operatorID) {
        EOperatorID.LOPUnionID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            return LOPUnion(childA, childB)
        }
        EOperatorID.LOPJoinID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            val optional = DynamicByteArray.intToBool(buffer.getNextInt())
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
            val sortOrder = DynamicByteArray.intToBool(buffer.getNextInt())
            val child = fromBinary(dictionary, buffer)
            return LOPSort(sortOrder, sortBy, child)
        }
        EOperatorID.LOPDistinctID -> {
            val child = fromBinary(dictionary, buffer)
            return LOPDistinct(child)
        }
        EOperatorID.LOPProjectionID -> {
            val childCount = buffer.getNextInt()
            val variables = mutableListOf<AOPVariable>()
            for (i in 0 until childCount)
                variables.add(fromBinaryAOP(dictionary, buffer) as AOPVariable)
            val child = fromBinary(dictionary, buffer)
            return LOPProjection(variables, child)
        }
        EOperatorID.LOPLimitID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return LOPLimit(value, child)
        }
        EOperatorID.LOPOffsetID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return LOPOffset(value, child)
        }
        EOperatorID.LOPTripleID -> {
            var graphName = "graph" + DistributedTripleStore.getGraphNames().size
            val graph = DistributedTripleStore.createGraph(graphName)
            val s = fromBinaryAOP(dictionary, buffer)
            val p = fromBinaryAOP(dictionary, buffer)
            val o = fromBinaryAOP(dictionary, buffer)
            val idx = EIndexPattern.values()[buffer.getNextInt()]
            val tripleCount = buffer.getNextInt()
            for (i in 0 until tripleCount) {
                val st = AOPVariable.calculate(buffer.getNextString())
                val pt = AOPVariable.calculate(buffer.getNextString())
                val ot = AOPVariable.calculate(buffer.getNextString())
                graph.addData(1L, listOf(st, pt, ot))
            }
            DistributedTripleStore.commit(1L)
            return LOPTriple(s, p, o, graphName, false)
        }
        EOperatorID.LOPValuesID -> {
            val variables = mutableListOf<AOPVariable>()
            val values = mutableListOf<AOPValue>()
            val variableCount = buffer.getNextInt()
            for (i in 0 until variableCount)
                variables.add(AOPVariable(buffer.getNextString()))
            val valuesCount = buffer.getNextInt()
            for (j in 0 until valuesCount) {
                val list = mutableListOf<AOPConstant>()
                for (i in 0 until variableCount) {
                    val isNull = DynamicByteArray.intToBool(buffer.getNextInt())
                    if (!isNull) {
                        val value = buffer.getNextString()
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
}

fun fromBinaryAOP(dictionary: ResultSetDictionary, buffer: DynamicByteArray): AOPBase {
    var id = buffer.getNextInt()
    val operatorID: EOperatorID
    if (id > EOperatorID.values().size || !EOperatorIDAOP.contains(EOperatorID.values()[id]))
        operatorID = EOperatorIDAOP[id % EOperatorIDAOP.size]
    else
        operatorID = EOperatorID.values()[id]

    when (operatorID) {
        EOperatorID.AOPAndID -> {
            val childA = fromBinaryAOP(dictionary, buffer)
            val childB = fromBinaryAOP(dictionary, buffer)
            return AOPAnd(childA, childB)
        }
        EOperatorID.AOPSimpleLiteralID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPSimpleLiteral
        }
        EOperatorID.AOPLanguageTaggedLiteralID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPLanguageTaggedLiteral
        }
        EOperatorID.AOPTypedLiteralID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPTypedLiteral
        }
        EOperatorID.AOPLTID -> {
            val childA = fromBinaryAOP(dictionary, buffer)
            val childB = fromBinaryAOP(dictionary, buffer)
            return AOPLT(childA, childB)
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
            val name = buffer.getNextString()
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
            val prefix = buffer.getNextString()
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
            val prefix = buffer.getNextString()
            return AOPBuildInCallURI(childA, prefix)
        }
        EOperatorID.AOPBuildInCallYEARID -> {
            val childA = fromBinaryAOP(dictionary, buffer)
            return AOPBuildInCallYEAR(childA)
        }
        EOperatorID.AOPDateTimeID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPDateTime
        }
        EOperatorID.AOPIntegerID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPInteger
        }
        EOperatorID.AOPIriID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPIri
        }
        EOperatorID.AOPUndefID -> {
            return AOPUndef()
        }
        EOperatorID.AOPAggregationID -> {
            val type = Aggregation.values()[buffer.getNextInt()]
            val distinct = DynamicByteArray.intToBool(buffer.getNextInt())
            val count = buffer.getNextInt()
            val variables = Array(count) { fromBinaryAOP(dictionary, buffer) }
            return AOPAggregation(type, distinct, variables)
        }
        EOperatorID.AOPBooleanID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPBoolean
        }
        EOperatorID.AOPDivisionID -> {
            val childA = fromBinaryAOP(dictionary, buffer)
            val childB = fromBinaryAOP(dictionary, buffer)
            return AOPDivision(childA, childB)
        }
        else -> throw Exception("BinaryHelper.fromBinaryAOP ${operatorID} undefined")
    }
}

fun executeBinaryTests(folder: String) {
    var testcases = 0
    try {
        File(folder).walk{
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
buffer=File(filename).readAsDynamicByteArray()
        val optimizerEnabledCount = buffer.getNextInt()
        ExecuteOptimizer.enabledOptimizers.clear()
        for (o in 0 until optimizerEnabledCount) {
            val optimizer = EOptimizerID.values()[buffer.getNextInt()]
            ExecuteOptimizer.enabledOptimizers[optimizer] = true
        }
        input = fromBinary(dictionary, buffer)
    println("execute test $filename ${ExecuteOptimizer.enabledOptimizers}")
    var expectPOP: POPValues? = null
    try {
	buffer=File(filename+ ".expect").readAsDynamicByteArray()
            expectPOP = fromBinary(dictionary, buffer) as POPValues
    } catch (e: Throwable) {
        e.printStackTrace()
    }
    val expected = QueryResultToXML.toXML(expectPOP!!).first()
    if (input!! is POPBase) {
        val output = QueryResultToXML.toXML(input!! as POPBase).first()
        if (!expected.myEqualsUnclean(output)) {
            if (detailedLog) {
                println("a")
                println((expectPOP as POPValues).toXMLElement().toPrettyString())
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
                println((expectPOP as POPValues).toXMLElement().toPrettyString())
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
        if (!expected.myEqualsUnclean(output)) {
            if (detailedLog) {
                println("c")
                println((expectPOP as POPValues).toXMLElement().toPrettyString())
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
                println((expectPOP as POPValues).toXMLElement().toPrettyString())
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
