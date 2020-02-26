package lupos.s04arithmetikOperators

import java.io.File
import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPAggregation
import lupos.s04arithmetikOperators.noinput.AOPBnode
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPDateTime
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPTypedLiteral
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s11outputResult.*
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.*


val prefix = ""
val listOfMicroTests = ThreadSafeMutableList<String>()
val mapOfAggregationChilds = ThreadSafeMutableMap<Long, MutableList<String>>()

val mapOfResultRows = ThreadSafeMutableMap<Long, MutableList<String>>()

val popMap = ThreadSafeMutableMap<Long, POPBase>()
val rowMapConsumed = ThreadSafeMutableMap<Pair<Long, Long>, MutableList<ResultRow>>()
val rowMapProduced = ThreadSafeMutableMap<Long, MutableList<ResultRow>>()
val mutableMapsForTest = ThreadSafeMutableMap<String, String>()
val myuuid = ThreadSafeUuid()

val mapPopToLop = mapOf(
        EOperatorID.POPBindID to EOperatorID.LOPBindID,
        EOperatorID.POPBindUndefinedID to EOperatorID.LOPBindID,
        EOperatorID.POPDistinctID to EOperatorID.LOPDistinctID,
        EOperatorID.POPEmptyRowID to EOperatorID.OPNothingID,
        EOperatorID.POPFilterExactID to EOperatorID.LOPFilterID,
        EOperatorID.POPFilterID to EOperatorID.LOPFilterID,
        EOperatorID.POPGraphOperationID to EOperatorID.LOPGraphOperationID,
        EOperatorID.POPGroupID to EOperatorID.LOPGroupID,
        EOperatorID.POPJoinHashMapID to EOperatorID.LOPJoinID,
        EOperatorID.POPJoinNestedLoopID to EOperatorID.LOPJoinID,
        EOperatorID.POPLimitID to EOperatorID.LOPLimitID,
        EOperatorID.POPMakeBooleanResultID to EOperatorID.POPMakeBooleanResultID,
        EOperatorID.POPModifyDataID to EOperatorID.LOPModifyDataID,
        EOperatorID.POPModifyID to EOperatorID.LOPModifyID,
        EOperatorID.POPOffsetID to EOperatorID.LOPOffsetID,
        EOperatorID.POPProjectionID to EOperatorID.LOPProjectionID,
        EOperatorID.POPRenameID to EOperatorID.LOPRenameID,
        EOperatorID.POPServiceIRIID to EOperatorID.LOPServiceIRIID,
        EOperatorID.POPSortID to EOperatorID.LOPSortID,
        EOperatorID.POPUnionID to EOperatorID.LOPUnionID,
        EOperatorID.POPValuesID to EOperatorID.LOPValuesID,
        EOperatorID.TripleStoreIteratorGlobalID to EOperatorID.LOPTripleID
)

fun toBinary(operator: OPBase, buffer: DynamicByteArray, asPOP: Boolean) {
    if (asPOP)
        buffer.appendInt(operator.operatorID.ordinal)
    else {
        val mapped = mapPopToLop[operator.operatorID]
        if (mapped != null)
            buffer.appendInt(mapped.ordinal)
        else
            buffer.appendInt(operator.operatorID.ordinal)
    }
    when (operator) {
        is AOPAddition -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallCONTAINS -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallIsNUMERIC -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallLANGMATCHES -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallSTRENDS -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallSTRSTARTS -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPEQ -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPGEQ -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPOr -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPVariable -> {
            buffer.appendString(operator.name)
        }
        is POPEmptyRow -> {
        }
        is AOPBuildInCallABS -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallBNODE0 -> {
        }
        is AOPBuildInCallBNODE1 -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallCEIL -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallCONCAT -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallDATATYPE -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallDAY -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallFLOOR -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallHOURS -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallIF -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
            toBinary(operator.children[2], buffer, asPOP)
        }
        is AOPBuildInCallIRI -> {
            toBinary(operator.children[0], buffer, asPOP)
            buffer.appendString(operator.prefix)
        }
        is AOPBuildInCallLANG -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallLCASE -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallMD5 -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallMINUTES -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallMONTH -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallROUND -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallSECONDS -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallSHA1 -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallSHA256 -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallSTRDT -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallSTR -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallSTRLANG -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPBuildInCallSTRLEN -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallTZ -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallUCASE -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPBuildInCallURI -> {
            toBinary(operator.children[0], buffer, asPOP)
            buffer.appendString(operator.prefix)
        }
        is AOPBuildInCallYEAR -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPDateTime -> {
            buffer.appendString(operator.valueToString())
        }
        is AOPInteger -> {
            buffer.appendString(operator.valueToString())
        }
        is AOPIri -> {
            buffer.appendString(operator.valueToString())
        }
        is AOPSimpleLiteral -> {
            buffer.appendString(operator.valueToString())
        }
        is AOPUndef -> {
        }
        is AOPAggregation -> {
            buffer.appendInt(operator.type.ordinal)
            buffer.appendInt(DynamicByteArray.boolToInt(operator.distinct))
            buffer.appendInt(operator.children.size)
            for (c in operator.children)
                toBinary(c, buffer, asPOP)
        }
        is AOPBoolean -> {
            buffer.appendString(operator.valueToString())
        }
        is AOPDivision -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }

        is POPValues -> {
            val variables = operator.getProvidedVariableNames()
            buffer.appendInt(variables.size)
            for (v in variables)
                buffer.appendString(v)
            if (operator.data != null) {
                buffer.appendInt(operator.data.size)
                for (row in operator.data) {
                    for (k in variables) {
                        val v = operator.resultSet.getValue(row[operator.resultSet.createVariable(k)]!!)
                        buffer.appendInt(DynamicByteArray.boolToInt(v == null))
                        if (v != null)
                            buffer.appendString(v)
                    }
                }
            } else
                buffer.appendInt(0)
        }
        is POPUnion -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is POPJoinHashMap -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
            buffer.appendInt(DynamicByteArray.boolToInt(operator.optional))
        }
        is POPRename -> {
            toBinary(operator.nameTo, buffer, asPOP)
            toBinary(operator.nameFrom, buffer, asPOP)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPFilter -> {
            toBinary(operator.children[1], buffer, asPOP)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPProjection -> {
            buffer.appendInt(operator.variables.size)
            for (v in operator.variables)
                toBinary(v, buffer, asPOP)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPBindUndefined -> {
            if (asPOP) {
                toBinary(operator.name, buffer, asPOP)
                toBinary(operator.children[0], buffer, asPOP)
            } else {
                toBinary(operator.name, buffer, asPOP)
                toBinary(AOPUndef(), buffer, asPOP)
                toBinary(operator.children[0], buffer, asPOP)
            }
        }
        is POPBind -> {
            toBinary(operator.name, buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPFilterExact -> {
            if (asPOP) {
                toBinary(operator.variable, buffer, asPOP)
                buffer.appendString(operator.value)
                toBinary(operator.children[0], buffer, asPOP)
            } else {
                toBinary(AOPEQ(operator.variable, AOPVariable.calculate(operator.value)), buffer, asPOP)
                toBinary(operator.children[0], buffer, asPOP)
            }
        }
        is POPSort -> {
            toBinary(AOPVariable(operator.resultSet.getVariable(operator.sortBy)), buffer, asPOP)
            buffer.appendInt(DynamicByteArray.boolToInt(operator.sortOrder))
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPDistinct -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPLimit -> {
            buffer.appendInt(operator.limit)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is POPOffset -> {
            buffer.appendInt(operator.offset)
            toBinary(operator.children[0], buffer, asPOP)
        }
        is TripleStoreIteratorGlobal -> {
            val s: String
            val p: String
            val o: String
            val sv = operator.sFilter != null
            val pv = operator.pFilter != null
            val ov = operator.oFilter != null
            if (sv)
                s = operator.sFilter!!
            else
                s = operator.nameS
            if (pv)
                p = operator.pFilter!!
            else
                p = operator.nameP
            if (ov)
                o = operator.oFilter!!
            else
                o = operator.nameO
            val tmp = rowMapProduced[operator.uuid]
            buffer.appendInt(DynamicByteArray.boolToInt(sv))
            buffer.appendInt(DynamicByteArray.boolToInt(pv))
            buffer.appendInt(DynamicByteArray.boolToInt(ov))
            buffer.appendString(s)
            buffer.appendString(p)
            buffer.appendString(o)
            buffer.appendInt(operator.idx.ordinal)
            if (tmp != null) {
                buffer.appendInt(tmp.size)
                for (r in tmp) {
                    if (sv)
                        buffer.appendString(s)
                    else
                        buffer.appendString(operator.resultSet.getValue(r[operator.resultSet.createVariable(s)]!!)!!)
                    if (pv)
                        buffer.appendString(p)
                    else
                        buffer.appendString(operator.resultSet.getValue(r[operator.resultSet.createVariable(p)]!!)!!)
                    if (ov)
                        buffer.appendString(o)
                    else
                        buffer.appendString(operator.resultSet.getValue(r[operator.resultSet.createVariable(o)]!!)!!)
                }
            } else {
                buffer.appendInt(0)
            }
        }
        else -> throw Exception("BinaryHelper.toBinary ${operator.operatorID} undefined")
    }
}

fun fromBinary(dictionary: ResultSetDictionary, buffer: DynamicByteArray): OPBase {
    val operatorID = EOperatorID.values()[buffer.getNextInt()]
    when (operatorID) {
        EOperatorID.AOPAdditionID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPAddition(childA, childB)
        }
        EOperatorID.AOPBuildInCallCONTAINSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallCONTAINS(childA, childB)
        }
        EOperatorID.AOPBuildInCallIsNUMERICID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallIsNUMERIC(childA)
        }
        EOperatorID.AOPBuildInCallLANGMATCHESID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallLANGMATCHES(childA, childB)
        }
        EOperatorID.AOPBuildInCallSTRENDSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTRENDS(childA, childB)
        }
        EOperatorID.AOPBuildInCallSTRSTARTSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTRSTARTS(childA, childB)
        }
        EOperatorID.AOPEQID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPEQ(childA, childB)
        }
        EOperatorID.AOPGEQID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPGEQ(childA, childB)
        }
        EOperatorID.AOPOrID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPOr(childA, childB)
        }
        EOperatorID.AOPVariableID -> {
            val name = buffer.getNextString()
            return AOPVariable(name)
        }
        EOperatorID.POPEmptyRowID -> {
            return POPEmptyRow(dictionary)
        }
        EOperatorID.OPNothingID -> {
            return OPNothing()
        }
        EOperatorID.AOPBuildInCallABSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallABS(childA)
        }
        EOperatorID.AOPBuildInCallBNODE0ID -> {
            return AOPBuildInCallBNODE0()
        }
        EOperatorID.AOPBuildInCallBNODE1ID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallBNODE1(childA)
        }
        EOperatorID.AOPBuildInCallCEILID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallCEIL(childA)
        }
        EOperatorID.AOPBuildInCallCONCATID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallCONCAT(childA, childB)
        }
        EOperatorID.AOPBuildInCallDATATYPEID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallDATATYPE(childA)
        }
        EOperatorID.AOPBuildInCallDAYID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallDAY(childA)
        }
        EOperatorID.AOPBuildInCallFLOORID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallFLOOR(childA)
        }
        EOperatorID.AOPBuildInCallHOURSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallHOURS(childA)
        }
        EOperatorID.AOPBuildInCallIFID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            val childC = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallIF(childA, childB, childC)
        }
        EOperatorID.AOPBuildInCallIRIID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val prefix = buffer.getNextString()
            return AOPBuildInCallIRI(childA, prefix)
        }
        EOperatorID.AOPBuildInCallLANGID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallLANG(childA)
        }
        EOperatorID.AOPBuildInCallLCASEID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallLCASE(childA)
        }
        EOperatorID.AOPBuildInCallMD5ID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallMD5(childA)
        }
        EOperatorID.AOPBuildInCallMINUTESID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallMINUTES(childA)
        }
        EOperatorID.AOPBuildInCallMONTHID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallMONTH(childA)
        }
        EOperatorID.AOPBuildInCallROUNDID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallROUND(childA)
        }
        EOperatorID.AOPBuildInCallSECONDSID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSECONDS(childA)
        }
        EOperatorID.AOPBuildInCallSHA1ID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSHA1(childA)
        }
        EOperatorID.AOPBuildInCallSHA256ID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSHA256(childA)
        }
        EOperatorID.AOPBuildInCallSTRDTID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTRDT(childA, childB)
        }
        EOperatorID.AOPBuildInCallSTRID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTR(childA)
        }
        EOperatorID.AOPBuildInCallSTRLANGID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTRLANG(childA, childB)
        }
        EOperatorID.AOPBuildInCallSTRLENID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallSTRLEN(childA)
        }
        EOperatorID.AOPBuildInCallTZID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallTZ(childA)
        }
        EOperatorID.AOPBuildInCallUCASEID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            return AOPBuildInCallUCASE(childA)
        }
        EOperatorID.AOPBuildInCallURIID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val prefix = buffer.getNextString()
            return AOPBuildInCallURI(childA, prefix)
        }
        EOperatorID.AOPBuildInCallYEARID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
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
        EOperatorID.AOPSimpleLiteralID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPSimpleLiteral
        }
        EOperatorID.AOPUndefID -> {
            return AOPUndef()
        }
        EOperatorID.AOPAggregationID -> {
            val type = Aggregation.values()[buffer.getNextInt()]
            val distinct = DynamicByteArray.intToBool(buffer.getNextInt())
            val count = buffer.getNextInt()
            val variables = Array(count) { fromBinary(dictionary, buffer) as AOPBase }
            return AOPAggregation(type, distinct, variables)
        }
        EOperatorID.AOPBooleanID -> {
            return AOPVariable.calculate(buffer.getNextString()) as AOPBoolean
        }
        EOperatorID.AOPDivisionID -> {
            val childA = fromBinary(dictionary, buffer) as AOPBase
            val childB = fromBinary(dictionary, buffer) as AOPBase
            return AOPDivision(childA, childB)
        }
        EOperatorID.POPUnionID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            return POPUnion(dictionary, childA, childB)
        }
        EOperatorID.LOPUnionID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            return LOPUnion(childA, childB)
        }
        EOperatorID.POPJoinHashMapID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            val optional = DynamicByteArray.intToBool(buffer.getNextInt())
            return POPJoinHashMap(dictionary, childA, childB, optional)
        }
        EOperatorID.LOPJoinID -> {
            val childA = fromBinary(dictionary, buffer)
            val childB = fromBinary(dictionary, buffer)
            val optional = DynamicByteArray.intToBool(buffer.getNextInt())
            return LOPJoin(childA, childB, optional)
        }
        EOperatorID.POPRenameID -> {
            val nameTo = fromBinary(dictionary, buffer) as AOPVariable
            val nameFrom = fromBinary(dictionary, buffer) as AOPVariable
            val child = fromBinary(dictionary, buffer)
            return POPRename(dictionary, nameTo, nameFrom, child)
        }
        EOperatorID.LOPRenameID -> {
            val nameTo = fromBinary(dictionary, buffer) as AOPVariable
            val nameFrom = fromBinary(dictionary, buffer) as AOPVariable
            val child = fromBinary(dictionary, buffer)
            return LOPRename(nameTo, nameFrom, child)
        }
        EOperatorID.POPFilterID -> {
            val filter = fromBinary(dictionary, buffer) as AOPBase
            val child = fromBinary(dictionary, buffer)
            return POPFilter(dictionary, filter, child)
        }
        EOperatorID.LOPFilterID -> {
            val filter = fromBinary(dictionary, buffer) as AOPBase
            val child = fromBinary(dictionary, buffer)
            return LOPFilter(filter, child)
        }
        EOperatorID.POPBindUndefinedID -> {
            val name = fromBinary(dictionary, buffer) as AOPVariable
            val child = fromBinary(dictionary, buffer)
            return POPBindUndefined(dictionary, name, child)
        }
        EOperatorID.POPFilterExactID -> {
            val name = fromBinary(dictionary, buffer) as AOPVariable
            val value = buffer.getNextString()
            val child = fromBinary(dictionary, buffer)
            return POPFilterExact(dictionary, name, value, child)
        }
        EOperatorID.POPBindID -> {
            val name = fromBinary(dictionary, buffer) as AOPVariable
            val value = fromBinary(dictionary, buffer) as AOPBase
            val child = fromBinary(dictionary, buffer)
            return POPBind(dictionary, name, value, child)
        }
        EOperatorID.LOPBindID -> {
            val name = fromBinary(dictionary, buffer) as AOPVariable
            val value = fromBinary(dictionary, buffer) as AOPBase
            val child = fromBinary(dictionary, buffer)
            return LOPBind(name, value, child)
        }
        EOperatorID.POPSortID -> {
            val sortBy = fromBinary(dictionary, buffer) as AOPVariable
            val sortOrder = DynamicByteArray.intToBool(buffer.getNextInt())
            val child = fromBinary(dictionary, buffer)
            return POPSort(dictionary, sortBy, sortOrder, child)
        }
        EOperatorID.LOPSortID -> {
            val sortBy = fromBinary(dictionary, buffer) as AOPVariable
            val sortOrder = DynamicByteArray.intToBool(buffer.getNextInt())
            val child = fromBinary(dictionary, buffer)
            return LOPSort(sortOrder, sortBy, child)
        }
        EOperatorID.POPDistinctID -> {
            val child = fromBinary(dictionary, buffer)
            return POPDistinct(dictionary, child)
        }
        EOperatorID.LOPDistinctID -> {
            val child = fromBinary(dictionary, buffer)
            return LOPDistinct(child)
        }
        EOperatorID.POPProjectionID -> {
            val childCount = buffer.getNextInt()
            val variables = mutableListOf<AOPVariable>()
            for (i in 0 until childCount)
                variables.add(fromBinary(dictionary, buffer) as AOPVariable)
            val child = fromBinary(dictionary, buffer)
            return POPProjection(dictionary, variables, child)
        }
        EOperatorID.LOPProjectionID -> {
            val childCount = buffer.getNextInt()
            val variables = mutableListOf<AOPVariable>()
            for (i in 0 until childCount)
                variables.add(fromBinary(dictionary, buffer) as AOPVariable)
            val child = fromBinary(dictionary, buffer)
            return LOPProjection(variables, child)
        }
        EOperatorID.POPLimitID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return POPLimit(dictionary, value, child)
        }
        EOperatorID.LOPLimitID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return LOPLimit(value, child)
        }
        EOperatorID.POPOffsetID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return POPOffset(dictionary, value, child)
        }
        EOperatorID.LOPOffsetID -> {
            var value = buffer.getNextInt()
            val child = fromBinary(dictionary, buffer)
            return LOPOffset(value, child)
        }
        EOperatorID.TripleStoreIteratorGlobalID -> {
            var graphName = "graph" + DistributedTripleStore.getGraphNames().size
            val graph = DistributedTripleStore.createGraph(graphName)
            val sv = DynamicByteArray.intToBool(buffer.getNextInt())
            val pv = DynamicByteArray.intToBool(buffer.getNextInt())
            val ov = DynamicByteArray.intToBool(buffer.getNextInt())
            val s = buffer.getNextString()
            val p = buffer.getNextString()
            val o = buffer.getNextString()
            val idx = EIndexPattern.values()[buffer.getNextInt()]
            val tripleCount = buffer.getNextInt()
            for (i in 0 until tripleCount) {
                val st = buffer.getNextString()
                val pt = buffer.getNextString()
                val ot = buffer.getNextString()
                graph.addData(1L, listOf(st, pt, ot))
            }
            DistributedTripleStore.commit(1L)
            return TripleStoreIteratorGlobal(1L, dictionary, graphName, s, p, o, sv, pv, ov, idx)
        }
        EOperatorID.LOPTripleID -> {
            var graphName = "graph" + DistributedTripleStore.getGraphNames().size
            val graph = DistributedTripleStore.createGraph(graphName)
            val sv = DynamicByteArray.intToBool(buffer.getNextInt())
            val pv = DynamicByteArray.intToBool(buffer.getNextInt())
            val ov = DynamicByteArray.intToBool(buffer.getNextInt())
            val s = buffer.getNextString()
            val p = buffer.getNextString()
            val o = buffer.getNextString()
            val idx = EIndexPattern.values()[buffer.getNextInt()]
            val tripleCount = buffer.getNextInt()
            for (i in 0 until tripleCount) {
                val st = buffer.getNextString()
                val pt = buffer.getNextString()
                val ot = buffer.getNextString()
                graph.addData(1L, listOf(st, pt, ot))
            }
            DistributedTripleStore.commit(1L)
            val mys: OPBase
            if (sv)
                mys = AOPVariable.calculate(s)
            else
                mys = AOPVariable(s)
            val myp: OPBase
            if (pv)
                myp = AOPVariable.calculate(p)
            else
                myp = AOPVariable(p)
            val myo: OPBase
            if (ov)
                myo = AOPVariable.calculate(o)
            else
                myo = AOPVariable(o)
            return LOPTriple(mys, myp, myo, graphName, false)
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
        else -> throw Exception("BinaryHelper.fromBinary ${operatorID} undefined")
    }
}

fun testCaseBinaryFromResultRowsAsPOPValues(buffer: DynamicByteArray, rows: MutableList<ResultRow>?, o: OPBase) {
    buffer.appendInt(EOperatorID.POPValuesID.ordinal)
    val variables = o.getProvidedVariableNames()
    buffer.appendInt(variables.size)
    for (v in variables)
        buffer.appendString(v)
    if (rows != null) {
        buffer.appendInt(rows.size)
        for (row in rows) {
            for (k in variables) {
                val v = o.resultSet.getValue(row[o.resultSet.createVariable(k)])
                buffer.appendInt(DynamicByteArray.boolToInt(v == null))
                if (v != null)
                    buffer.appendString(v)
            }
        }
    } else
        buffer.appendInt(0)
}

var testcasenumber = 0
var hasExecutedTests = false
fun createBinaryTestCase(operator: OPBase, asPOP: Boolean) {
    if (hasExecutedTests)
        return
    synchronized(testcasenumber) {
        try {
            val buffer = DynamicByteArray()
            buffer.appendInt(DynamicByteArray.boolToInt(asPOP))
            toBinary(operator, buffer, asPOP)
            val filename = "src/commonTest/kotlin/lupos/testcase-${testcasenumber++}.bin"
            File(filename).outputStream().use { out ->
                val data = buffer.finish()
                out.write(data, 0, buffer.pos)
            }
            val buffer2 = DynamicByteArray()
            testCaseBinaryFromResultRowsAsPOPValues(buffer2, rowMapProduced[operator.uuid], operator)
            File(filename + ".expect").outputStream().use { out ->
                val data = buffer2.finish()
                out.write(data, 0, buffer2.pos)
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}

fun executeBinaryTests(folder: String) {
    hasExecutedTests = true
    try {
        File(folder).walk().forEach {
            val filename: String = it.toRelativeString(File("."))
            if (filename.endsWith(".bin")) {
                println("execute test $filename")
                val dictionary = ResultSetDictionary()
                var input: OPBase? = null
                var asPOP = false
                File(filename).inputStream().use { instream ->
                    val data = instream.readBytes()
                    val buffer = DynamicByteArray(data)
                    asPOP = DynamicByteArray.intToBool(buffer.getNextInt())
                    input = fromBinary(dictionary, buffer) as OPBase
                }
                var expectPOP: POPValues? = null
                try {
                    File(filename + ".expect").inputStream().use { instream ->
                        val dictionary = ResultSetDictionary()
                        val data = instream.readBytes()
                        val buffer = DynamicByteArray(data)
                        expectPOP = fromBinary(dictionary, buffer) as POPValues
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                require(expectPOP!! is POPValues)
                val expected = QueryResultToXML.toXML(expectPOP!!).first()
                if (asPOP) {
                    val output = QueryResultToXML.toXML(input!! as POPBase).first()
                    if (!expected.myEquals(output)) {
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    require(expected.myEquals(output))
                } else {
                    val lop_node = input!! as LOPBase
                    ExecuteOptimizer.enabledOptimizers.clear()
                    val lOptimizer = LogicalOptimizer(1L, dictionary)
                    val pOptimizer = PhysicalOptimizer(1L, dictionary)
                    val dOptimizer = KeyDistributionOptimizer(1L, dictionary)
                    val lop_node2 = lOptimizer.optimizeCall(lop_node)
                    val pop_node = pOptimizer.optimizeCall(lop_node2)
                    val input = dOptimizer.optimizeCall(pop_node) as POPBase
                    val output = QueryResultToXML.toXML(input).first()
                    if (!expected.myEquals(output)) {
                        println((expectPOP as POPValues).toXMLElement().toPrettyString())
                        println(input!!.toXMLElement().toPrettyString())
                        println(expected.toPrettyString())
                        println(output.toPrettyString())
                    }
                    require(expected.myEquals(output))
                }
            }
        }
    } catch (e: Throwable) {
        e.printStackTrace()
    }
}

fun resultFlowConsume(consumerv: () -> OPBase, producerv: () -> OPBase, action: () -> ResultRow): ResultRow {
    val res = action()
    val consumer = consumerv() as POPBase
    val producer = producerv() as POPBase
    popMap[consumer.uuid] = consumer
    popMap[producer.uuid] = producer
    val key = Pair(consumer.uuid, producer.uuid)
    val list = rowMapConsumed[key]
    if (list == null)
        rowMapConsumed[key] = mutableListOf(res)
    else
        list.add(res)
    return res
}

fun resultFlowProduce(producerv: () -> OPBase, action: () -> ResultRow): ResultRow {
    val res = action()
    val producer = producerv() as POPBase
    popMap[producer.uuid] = producer
    val list = rowMapProduced[producer.uuid]
    if (list == null)
        rowMapProduced[producer.uuid] = mutableListOf(res)
    else
        list.add(res)
    return res
}

fun addMutableMapToTests(code: String): String {
    synchronized(mutableMapsForTest) {
        val res = mutableMapsForTest[code]
        if (res != null)
            return res
        mutableMapsForTest[code] = "map${myuuid.next()}map"
        return mutableMapsForTest[code]!!
    }
}

fun testCaseFromResultRowsAsPOPValues(rows: MutableList<ResultRow>?, o: OPBase, prefix: String): String {
    var res = "${prefix}POPValues(dictionary, listOf(\n"
    val variables = o.getProvidedVariableNames()
    if (variables.size > 0) {
        for (v in variables) {
            res += "${prefix}        \"$v\",\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    ), listOf(\n"
    if (rows != null) {
        for (row in rows) {
            var tmpMap = ""
            tmpMap += "mutableMapOf<String,String?>(\n"
            if (variables.size > 0) {
                for (k in variables) {
                    val v = o.resultSet.getValue(row[o.resultSet.createVariable(k)])?.replace("\"", "\\\"")
                    if (v == null)
                        tmpMap += "    \"${k}\" to null,\n"
                    else
                        tmpMap += "    \"${k}\" to \"${v}\",\n"
                }
                tmpMap = tmpMap.substring(0, tmpMap.length - 2)
            }
            tmpMap += ")\n"
            res += "${prefix}        GeneratedMutableMap.${addMutableMapToTests(tmpMap)},\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    )\n"
    res += "${prefix})"
    return res
}

fun testCaseFromResultRowsAsLOPValues(rows: MutableList<ResultRow>?, o: OPBase, prefix: String): String {
    var res = "${prefix}LOPValues(listOf(\n"
    val variables = o.getProvidedVariableNames()
    if (variables.size > 0) {
        for (v in variables) {
            res += "${prefix}        AOPVariable(\"$v\"),\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    ), listOf(\n"
    if (rows != null) {
        for (row in rows) {
            var tmpMap = ""
            tmpMap += "AOPValue(listOf(\n"
            if (variables.size > 0) {
                for (k in variables) {
                    val v = o.resultSet.getValue(row[o.resultSet.createVariable(k)])?.replace("\"", "\\\"")
                    if (v == null)
                        tmpMap += "    AOPUndef(),\n"
                    else
                        tmpMap += "    AOPVariable.calculate(\"${v}\"),\n"
                }
                tmpMap = tmpMap.substring(0, tmpMap.length - 2)
            }
            tmpMap += "))\n"
            res += "${prefix}        GeneratedMutableMap.${addMutableMapToTests(tmpMap)},\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    )\n"
    res += "${prefix})"
    return res
}


fun testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive: Boolean, rows: MutableList<ResultRow>?, o: OPBase, prefix: String): String {
    if (recoursive) {
        return testCaseFromPOPBaseSimple(o as POPBase, true, prefix + "    ", false)
    } else
        return testCaseFromResultRowsAsPOPValues(rows, o, prefix)
}

fun testCaseFromPOPBaseSimple(op: POPBase, recoursive: Boolean, prefix: String, first: Boolean = true): String {
    var res = ""

    if (first) {
        res += "{\n"
        res += "${prefix}    val dictionary = ResultSetDictionary()\n"
        res += "${prefix}    MicroTestPN(\n"
        res += "${prefix}        dictionary,\n"
    }
    if (op is TripleStoreIteratorGlobal) {
        val s: String?
        val p: String?
        val o: String?
        val sv = op.sFilter != null
        val pv = op.pFilter != null
        val ov = op.oFilter != null
        if (sv)
            s = op.sFilter
        else
            s = op.nameS
        if (pv)
            p = op.pFilter
        else
            p = op.nameP
        if (ov)
            o = op.oFilter
        else
            o = op.nameO
        res += "${prefix}        {\n"
        res += "${prefix}            val graphName = \"graph\" + DistributedTripleStore.getGraphNames().size\n"
        res += "${prefix}            val graph=DistributedTripleStore.createGraph(graphName)\n"
        val tmp = rowMapProduced[op.uuid]
        if (tmp != null) {
            for (r in tmp) {
                res += "${prefix}            graph.addData(1L,listOf(\""
                if (sv)
                    res += s?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(s!!)]!!)!!.replace("\"", "\\\"")
                res += "\",\""
                if (pv)
                    res += p?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(p!!)]!!)!!.replace("\"", "\\\"")
                res += "\",\""
                if (ov)
                    res += o?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(o!!)]!!)!!.replace("\"", "\\\"")
                res += "\"))\n"
            }
        }
        res += "${prefix}            DistributedTripleStore.commit(1L)\n"
        res += "${prefix}            TripleStoreIteratorGlobal(1L,dictionary,graphName,\"${s?.replace("\"", "\\\"")}\",\"${p?.replace("\"", "\\\"")}\",\"${o?.replace("\"", "\\\"")}\",$sv,$pv,$ov,EIndexPattern.${op.idx})\n"
        if (first)
            res += "${prefix}        }(),\n"
        else
            res += "${prefix}        }()\n"
    } else {
        res += "${prefix}        ${op.classname}(\n"
        res += "${prefix}            dictionary,\n"
        when (op) {
            is POPUnion -> {
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + "\n"
            }
            is POPJoinHashMap -> {
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + ",\n"
                res += "${prefix}            ${op.optional}"
            }
            is POPJoinNestedLoop -> {
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + ",\n"
                res += "${prefix}            ${op.optional}"
            }
            is POPRename -> {
                res += "${prefix}            AOPVariable(\"${op.nameTo.name}\"),\n"
                res += "${prefix}            AOPVariable(\"${op.nameFrom.name}\"),\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPFilter -> {
                res += "${prefix}            ${testCaseFromAOPBase(op.children[1] as AOPBase)},\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPDistinct -> {
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPOffset -> {
                res += "${prefix}            ${op.offset},\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPLimit -> {
                res += "${prefix}            ${op.limit},\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPSort -> {
                res += "${prefix}            AOPVariable(\"${op.resultSet.getVariable(op.sortBy)}\"),\n"
                res += "${prefix}            ${op.sortOrder},\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPBindUndefined -> {
                res += "${prefix}            AOPVariable(\"${op.name.name}\"),\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPBind -> {
                res += "${prefix}            AOPVariable(\"${op.name.name}\"),\n"
                res += "${prefix}            ${testCaseFromAOPBase(op.children[1] as AOPBase)},\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPProjection -> {
                res += "${prefix}            mutableListOf(\n"
                for (i in 0 until op.variables.size - 1)
                    res += "${prefix}                AOPVariable(\"${op.variables[i].name}\"),\n"
                if (op.variables.size > 0)
                    res += "${prefix}                AOPVariable(\"${op.variables[op.variables.size - 1].name}\")\n"
                res += "${prefix}            ),\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPFilterExact -> {
                res += "${prefix}            AOPVariable(\"${op.variable.name}\"),\n"
                res += "${prefix}            \"${op.value}\",\n"
                res += testCaseFromPOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            else -> throw Exception("not implemented testCaseFromPOPBaseSimple(${classNameToString(op)})")
        }
        if (first)
            res += "${prefix}        ),\n"
        else
            res += "${prefix}        )\n"
    }
    if (first) {
        res += testCaseFromResultRowsAsPOPValues(rowMapProduced[op.uuid], op, "${prefix}        ") + "\n"
        res += "${prefix}    )\n"
        res += "${prefix}}()"
    }
    return res
}

fun <T> resultFlow(inputv: () -> AOPBase, resultRowv: () -> ResultRow, resultSetv: () -> ResultSet, action: () -> T): T {
    val input = inputv()
    val resultRow = resultRowv()
    val resultSet = resultSetv()
    val expected = action()
    val variableNames = mutableMapOf<String, String>()
    if (input is AOPVariable)
        return expected
    var res = "{\n"
    if (input is AOPAggregation) {
        if (input.collectMode) {
            val tmp = mapOfAggregationChilds[input.uuid]
            if (tmp == null)
                mapOfAggregationChilds[input.uuid] = mutableListOf(testCaseFromResultRow(resultRow, resultSet, "${prefix}                            ", variableNames))
            else
                tmp.add(testCaseFromResultRow(resultRow, resultSet, "${prefix}                            ", variableNames))
            return expected
        } else {
            res += "${prefix}                val resultSet = ResultSet(ResultSetDictionary())\n"
            for (v in resultSet.getVariableNames()) {
                val name = helperVariableName(v, variableNames)
                res += "${prefix}                resultSet.createVariable(\"$name\")\n"
            }
            res += "${prefix}                MicroTestAN(\n"
            res += "${prefix}                        " + testCaseFromAOPBase(input) + ",\n"
            res += "${prefix}                        listOf(\n"
            val tmp = mapOfAggregationChilds[input.uuid]
            if (tmp != null) {
                for (x in tmp)
                    res += x
                res = res.substring(0, res.length - 2) + "\n"
            }
            res += "${prefix}                        ),\n"
            res += "${prefix}                        resultSet,\n"
            if (expected is AOPBase)
                res += "${prefix}                        " + testCaseFromAOPBase(expected) + "\n"
            else
                res += "${prefix}                        Exception(\"${(expected as Throwable).message!!.replace("\"", "\\\"")}\")\n"
            res += "${prefix}                )\n"
            mapOfAggregationChilds.remove(input.uuid)
        }
    } else if (childContainsAggregation(input)) {
        return expected
    } else if (childContainsVariable(input)) {
        res += "${prefix}                val resultSet = ResultSet(ResultSetDictionary())\n"
        for (v in resultSet.getVariableNames()) {
            val name = helperVariableName(v, variableNames)
            res += "${prefix}                resultSet.createVariable(\"$name\")\n"
        }
        res += "${prefix}                MicroTestA1(\n"
        res += "${prefix}                        " + testCaseFromAOPBase(input) + ",\n"
        res += testCaseFromResultRow(resultRow, resultSet, "${prefix}                        ", variableNames)
        res += "${prefix}                        resultSet,\n"
        if (expected is AOPBase)
            res += "${prefix}                        " + testCaseFromAOPBase(expected) + "\n"
        else
            res += "${prefix}                        Exception(\"${(expected as Throwable).message!!.replace("\"", "\\\"")}\")\n"
        res += "${prefix}                )\n"
    } else {
        res += "${prefix}                MicroTest0(\n"
        res += "${prefix}                        " + testCaseFromAOPBase(input) + ",\n"
        if (expected is AOPBase)
            res += "${prefix}                        " + testCaseFromAOPBase(expected) + "\n"
        else
            res += "${prefix}                        Exception(\"${(expected as Throwable).message!!.replace("\"", "\\\"")}\")\n"
        res += "${prefix}                )\n"

    }
    res += "${prefix}            }()"
    var found = false
    listOfMicroTests.forEach {
        if (it == res)
            found = true
    }
    if (!found)
        listOfMicroTests.add(res)
    return expected
}

val mapOfTestCases = ThreadSafeMutableMap</*mainoperator*/String, MutableMap<String/*query*/, String/*file*/>>()

fun printAllMicroTest() {
    mapOfTestCases.forEach { operator, testcases ->
        if (testcases.keys.size > 0) {
            val fileName = "src/commonTest/kotlin/lupos/Generated${operator}.kt"
            val myfile = File(fileName)
            myfile.printWriter().use { out ->
                out.println("package lupos")
                out.println("")
                out.println("import lupos.s10physicalOptimisation.PhysicalOptimizer")
                out.println("import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer")
                out.println("import lupos.s12p2p.P2P")
                out.println("import lupos.s14endpoint.EndpointImpl")
                out.println("import lupos.s00misc.*")
                out.println("import lupos.s15tripleStoreDistributed.*")
                out.println("import lupos.s02buildSyntaxTree.sparql1_1.*")
                out.println("import lupos.s03resultRepresentation.*")
                out.println("import lupos.s04arithmetikOperators.*")
                out.println("import lupos.s04arithmetikOperators.multiinput.*")
                out.println("import lupos.s04arithmetikOperators.noinput.*")
                out.println("import lupos.s04arithmetikOperators.singleinput.*")
                out.println("import lupos.s04logicalOperators.*")
                out.println("import lupos.s04logicalOperators.noinput.*")
                out.println("import lupos.s04logicalOperators.multiinput.*")
                out.println("import lupos.s04logicalOperators.singleinput.*")
                out.println("import lupos.s04logicalOperators.singleinput.modifiers.*")
                out.println("import lupos.s08logicalOptimisation.*")
                out.println("import lupos.s09physicalOperators.*")
                out.println("import lupos.s09physicalOperators.multiinput.*")
                out.println("import lupos.s09physicalOperators.noinput.*")
                out.println("import lupos.s09physicalOperators.singleinput.*")
                out.println("import lupos.s09physicalOperators.singleinput.modifiers.*")
                out.println("import lupos.s11outputResult.*")
                out.println("import org.junit.jupiter.api.*")
                out.println("import org.junit.jupiter.api.Assertions.*")
                out.println("")
                out.println("")
                out.println("class Generated${operator}Test {")
                out.println("    constructor() {")
                out.println("        P2P.knownClients.clear()")
                out.println("        P2P.knownClients.add(EndpointImpl.fullname)")
                out.println("    }")
                out.println("    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {")
                out.println("        for (n in  node.children)")
                out.println("            setAggregationMode(n, mode, count)")
                out.println("        if (node is AOPAggregation) {")
                out.println("                node.count = count")
                out.println("            node.collectMode = mode")
                out.println("            if (node.collectMode)")
                out.println("                node.a = null")
                out.println("        }")
                out.println("    }")
                out.println("")
                out.println("${prefix} @TestFactory")
                out.println("${prefix} fun test() = listOf(")
                for ((k, v) in testcases) {
                    if (k.endsWith("*/"))
                        out.println("${prefix}$k /* $v */")
                    else
                        out.println("${prefix}$k /* $v */ ,")
                }
                out.println("${prefix}            {")
                out.println("${prefix}                MicroTest0(AOPUndef(), AOPUndef())")
                out.println("${prefix}            }()")
                out.println("${prefix}    ).mapIndexed { index, data ->")
                out.println("${prefix}        DynamicTest.dynamicTest(\"\$index\") {")
                out.println("${prefix}            try {")
                out.println("${prefix}                if (data.input is AOPBase) {")
                out.println("${prefix}                    val input = data.input as AOPBase")
                out.println("${prefix}                    val output: AOPConstant")
                out.println("${prefix}                    if (data is MicroTestA1) {")
                out.println("${prefix}                        output = input.calculate(data.resultSet, data.resultRow)")
                out.println("${prefix}                    } else if (data is MicroTestAN) {")
                out.println("${prefix}                        setAggregationMode(input, true, data.resultRows.count())")
                out.println("${prefix}                        for (resultRow in data.resultRows)")
                out.println("${prefix}                            input.calculate(data.resultSet, resultRow)")
                out.println("${prefix}                        setAggregationMode(input, false, data.resultRows.count())")
                out.println("${prefix}                        output = input.calculate(data.resultSet, data.resultSet.createResultRow())")
                out.println("${prefix}                    } else {")
                out.println("${prefix}                        val resultSet = ResultSet(ResultSetDictionary())")
                out.println("${prefix}                        output = input.calculate(resultSet, resultSet.createResultRow())")
                out.println("${prefix}                    }")
                out.println("${prefix}                    assertTrue(data.expected is AOPConstant)")
                out.println("${prefix}                    if (!data.expected.equals(output)) {")
                out.println("${prefix}                        if (data is MicroTestA1)")
                out.println("${prefix}                            println(data.resultRow)")
                out.println("${prefix}                        println(output.valueToString())")
                out.println("${prefix}                        println((data.expected as AOPConstant).valueToString())")
                out.println("${prefix}                    }")
                out.println("${prefix}                    assertTrue(data.expected.equals(output))")
                out.println("${prefix}                } else if (data.input is POPBase && data is MicroTestPN) {")
                out.println("${prefix}                    val input = data.input as POPBase")
                out.println("${prefix}                    assertTrue(data.expected is POPValues)")
                out.println("${prefix}                    val output = QueryResultToXML.toXML(input).first()")
                out.println("${prefix}                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()")
                out.println("${prefix}                    if (!expected.myEquals(output)){")
                out.println("${prefix}                        println(output.toPrettyString())")
                out.println("${prefix}                        println(expected.toPrettyString())")
                out.println("${prefix}                    }")
                out.println("${prefix}                    assertTrue(expected.myEquals(output))")
                out.println("${prefix}                } else if (data.input is LOPBase && data is MicroTestLN) {")
                out.println("${prefix}                    val lop_node = data.input as LOPBase")
                out.println("${prefix}                    val dictionary = data.dictionary")
                out.println("${prefix}                    ExecuteOptimizer.enabledOptimizers.clear()")
                out.println("${prefix}                    val lOptimizer=LogicalOptimizer(1L, dictionary)")
                out.println("${prefix}                    val pOptimizer=PhysicalOptimizer(1L, dictionary)")
                out.println("${prefix}                    val dOptimizer=KeyDistributionOptimizer(1L, dictionary)")
                out.println("${prefix}                    val lop_node2 =lOptimizer.optimizeCall(lop_node)")
                out.println("${prefix}                    val pop_node = pOptimizer.optimizeCall(lop_node2)")
                out.println("${prefix}                    val input = dOptimizer.optimizeCall(pop_node) as POPBase")
                out.println("${prefix}                    assertTrue(data.expected is POPValues)")
                out.println("${prefix}                    val output = QueryResultToXML.toXML(input).first()")
                out.println("${prefix}                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()")
                out.println("${prefix}                    if (!expected.myEquals(output)){")
                out.println("${prefix}                        println(output.toPrettyString())")
                out.println("${prefix}                        println(expected.toPrettyString())")
                out.println("${prefix}                    }")
                out.println("${prefix}                    assertTrue(expected.myEquals(output))")
                out.println("${prefix}                    for(k in ExecuteOptimizer.enabledOptimizers.keys){")
                out.println("${prefix}                        ExecuteOptimizer.enabledOptimizers[k]=true")
                out.println("${prefix}                        val lop_node2 =lOptimizer.optimizeCall(lop_node)")
                out.println("${prefix}                        val pop_node = pOptimizer.optimizeCall(lop_node2)")
                out.println("${prefix}                        val input = dOptimizer.optimizeCall(pop_node) as POPBase")
                out.println("${prefix}                        assertTrue(data.expected is POPValues)")
                out.println("${prefix}                        val output = QueryResultToXML.toXML(input).first()")
                out.println("${prefix}                        val expected = QueryResultToXML.toXML(data.expected as POPValues).first()")
                out.println("${prefix}                        if (!expected.myEquals(output)){")
                out.println("${prefix}                            println(ExecuteOptimizer.enabledOptimizers.keys.map{it to ExecuteOptimizer.enabledOptimizers[it]})")
                out.println("${prefix}                            println(output.toPrettyString())")
                out.println("${prefix}                            println(expected.toPrettyString())")
                out.println("${prefix}                        }")
                out.println("${prefix}                        assertTrue(expected.myEquals(output))")
                out.println("${prefix}                    }")
                out.println("${prefix}                }")
                out.println("${prefix}            } catch (e: Throwable) {")
                out.println("${prefix}                e.printStackTrace()")
                out.println("${prefix}                assertTrue(data.expected is Throwable)")
                out.println("${prefix}            }")
                out.println("${prefix}        }")
                out.println("${prefix}    }")
                out.println("${prefix}}")
            }
        }
    }
    val fileName = "src/commonTest/kotlin/lupos/GeneratedMutableMap.kt"
    val myfile = File(fileName)
    myfile.printWriter().use { out ->
        out.println("object GeneratedMutableMap {\n")
        mutableMapsForTest.forEach { code, varname ->
            out.println("    val $varname = $code")
        }
        out.println("}")
    }
}

fun updateAllMicroTest(testName: String, queryFile: String, success: Boolean) {
    if (listOfMicroTests.size() > 0 || popMap.keySize() > 0) {
        val name = ("" + testName.hashCode() + "_" + queryFile).replace("[^a-zA-Z0-9]".toRegex(), "_")
        if (listOfMicroTests.size() > 0) {
            listOfMicroTests.forEach {
                val c = it.indexOf("MicroTest")
                val a = it.indexOf("AOP", c)
                val b = it.indexOf("(", a + 1)
                val name = it.substring(a, b).replace(".* ".toRegex(), "")
                val x = mapOfTestCases[name]
                val tmp: MutableMap<String, String>
                if (x == null) {
                    mapOfTestCases[name] = mutableMapOf<String, String>()
                    tmp = mapOfTestCases[name]!!
                } else
                    tmp = x
                if (success) {
                    if (it.contains("AOPBuildInCallBNODE1") || it.contains("AOPBuildInCallBNODE0") || it.contains("AOPBuildInCallNOW"))
                        tmp["${prefix}            /*" + it + "*/"] = queryFile
                    else
                        tmp["${prefix}            " + it] = queryFile
                } else
                    tmp["${prefix}            /*" + it + "*/"] = queryFile
            }
        }
        if (popMap.keySize() > 0) {
            popMap.forEachValue {
                val name = classNameToString(it)
                val x = mapOfTestCases[name]
                val tmp: MutableMap<String, String>
                if (x == null) {
                    mapOfTestCases[name] = mutableMapOf<String, String>()
                    tmp = mapOfTestCases[name]!!
                } else
                    tmp = x
                try {
                    if (success) {
                        createBinaryTestCase(it, true)
                        createBinaryTestCase(it, false)
                        tmp["${prefix}            " + testCaseFromPOPBaseSimple(it, false, "${prefix}            ")] = queryFile
                        tmp["${prefix}            " + testCaseFromPOPBaseSimple(it, true, "${prefix}            ")] = queryFile
                        tmp["${prefix}            " + testCaseFromLOPBaseSimple(it, false, "${prefix}            ")] = queryFile
                        tmp["${prefix}            " + testCaseFromLOPBaseSimple(it, true, "${prefix}            ")] = queryFile
                    } else
                        tmp["${prefix}            /* " + testCaseFromPOPBaseSimple(it, false, "${prefix}            ") + " */"] = queryFile
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
    }
    listOfMicroTests.clear()
    mapOfAggregationChilds.clear()
    popMap.clear()
    rowMapConsumed.clear()
    rowMapProduced.clear()
}

fun testCaseFromAOPBase(input: AOPBase): String {
    when (input) {
        is AOPBuildInCallURI -> return "AOPBuildInCallURI(${testCaseFromAOPBase(input.children[0] as AOPBase)}, \"${input.prefix}\")"
        is AOPBuildInCallIRI -> return "AOPBuildInCallIRI(${testCaseFromAOPBase(input.children[0] as AOPBase)}, \"${input.prefix}\")"
        is AOPDecimal -> return "AOPDecimal(${input.value})"
        is AOPBoolean -> return "AOPBoolean(${input.value})"
        is AOPDateTime -> return "AOPDateTime(\"${input.valueToString().replace("\"", "\\\"")}\")"
        is AOPVariable -> return "AOPVariable(\"${input.name}\")"
        is AOPUndef -> return "AOPUndef()"
        is AOPInteger -> return "AOPInteger(${input.value})"
        is AOPLanguageTaggedLiteral -> {
            if (input.delimiter == "\"")
                return "AOPLanguageTaggedLiteral(\"\\\"\", \"${input.content.replace("\"", "\\\"")}\", \"${input.language.replace("\"", "\\\"")}\")"
            return "AOPLanguageTaggedLiteral(\"${input.delimiter}\", \"${input.content.replace("\"", "\\\"")}\", \"${input.language.replace("\"", "\\\"")}\")"
        }
        is AOPSimpleLiteral -> {
            if (input.delimiter == "\"")
                return "AOPSimpleLiteral(\"\\\"\", \"${input.content.replace("\"", "\\\"")}\")"
            return "AOPSimpleLiteral(\"${input.delimiter}\", \"${input.content.replace("\"", "\\\"")}\")"
        }
        is AOPDouble -> return "AOPDouble(${input.value})"
        is AOPIri -> return "AOPIri(\"${input.iri}\")"
        is AOPTypedLiteral -> {
            if (input.delimiter == "\"")
                return "AOPTypedLiteral(\"\\\"\", \"${input.content.replace("\"", "\\\"")}\", \"${input.type_iri.replace("\"", "\\\"")}\")"
            return "AOPTypedLiteral(\"${input.delimiter}\", \"${input.content.replace("\"", "\\\"")}\", \"${input.type_iri.replace("\"", "\\\"")}\")"
        }
        is AOPBnode -> return "AOPBnode(\"${input.value.replace("\"", "\\\"")}\")"
        is AOPAggregation -> {
            var res = "AOPAggregation(Aggregation.${input.type}, ${input.distinct}, arrayOf("
            if (input.children.size > 0)
                res += testCaseFromAOPBase(input.children[0] as AOPBase)
            for (i in 1 until input.children.size)
                res += "," + testCaseFromAOPBase(input.children[i] as AOPBase)
            return res + "))"
        }
        else -> {
            var res = ""
            res += "${classNameToString(input)}("
            if (input.children.size > 0)
                res += testCaseFromAOPBase((input.children[0] as AOPBase))
            for (i in 1 until input.children.size)
                res += ", " + testCaseFromAOPBase((input.children[i] as AOPBase))
            res += ")"
            return res
        }
    }
}

fun testCaseFromResultRow(resultRow: ResultRow, resultSet: ResultSet, prefix: String, variableNames: MutableMap<String, String>): String {
    var res = ""
    res += "${prefix}{\n"
    res += "${prefix}    val resultRow = resultSet.createResultRow()\n"
    for (v in resultSet.getVariableNames()) {
        val name = helperVariableName(v, variableNames)
        val value = AOPVariable("$name").calculate(resultSet, resultRow).valueToString()
        if (value == null)
            res += "${prefix}    resultSet.setUndefValue(resultRow, resultSet.createVariable(\"$name\"))\n"
        else
            res += "${prefix}    resultRow[resultSet.createVariable(\"$name\")] = resultSet.createValue(\"${value.replace("\"", "\\\"")}\")\n"
    }
    res += "${prefix}    resultRow\n"
    res += "${prefix}}(),\n"
    return res
}

fun childContainsAggregation(input: OPBase): Boolean {
    if (input is AOPAggregation)
        return true
    for (c in input.children)
        if (childContainsAggregation(c))
            return true
    return false
}

fun childContainsVariable(input: OPBase): Boolean {
    if (input is AOPVariable)
        return true
    for (c in input.children)
        if (childContainsVariable(c))
            return true
    return false
}


fun helperVariableName(v: String, variableNames: MutableMap<String, String>): String {
    return when {
        variableNames[v] != null -> variableNames[v]!!
        v.startsWith("#") -> {
            variableNames[v] = "#" + variableNames.keys.size
            variableNames[v]!!
        }
        else -> {
            variableNames[v] = v
            variableNames[v]!!
        }
    }
}


open class MicroTest0(val input: OPBase, val expected: Any) {
}

class MicroTestA1(input: AOPBase, val resultRow: ResultRow, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected) {
}

class MicroTestAN(input: AOPBase, val resultRows: List<ResultRow>, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected) {
}

class MicroTestPN(val dictionary: ResultSetDictionary, input: POPBase, expected: Any) : MicroTest0(input, expected) {
}

class MicroTestLN(val dictionary: ResultSetDictionary, input: LOPBase, expected: Any) : MicroTest0(input, expected) {
}

fun testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive: Boolean, rows: MutableList<ResultRow>?, o: OPBase, prefix: String): String {
    if (recoursive) {
        return testCaseFromLOPBaseSimple(o as POPBase, true, prefix + "    ", false)
    } else
        return testCaseFromResultRowsAsLOPValues(rows, o, prefix)
}

fun testCaseFromLOPBaseSimple(op: POPBase, recoursive: Boolean, prefix: String, first: Boolean = true): String {
    var res = ""

    if (first) {
        res += "{\n"
        res += "${prefix}    val dictionary=ResultSetDictionary()\n"
        res += "${prefix}    MicroTestLN(\n"
        res += "${prefix}        dictionary,\n"
    }
    if (op is TripleStoreIteratorGlobal) {
        val s: String?
        val p: String?
        val o: String?
        val sv = op.sFilter != null
        val pv = op.pFilter != null
        val ov = op.oFilter != null
        if (sv)
            s = "AOPVariable.calculate(\"${op.sFilter?.replace("\"", "\\\"")}\")"
        else
            s = "AOPVariable(\"${op.nameS}\")"
        if (pv)
            p = "AOPVariable.calculate(\"${op.pFilter?.replace("\"", "\\\"")}\")"
        else
            p = "AOPVariable(\"${op.nameP}\")"
        if (ov)
            o = "AOPVariable.calculate(\"${op.oFilter?.replace("\"", "\\\"")}\")"
        else
            o = "AOPVariable(\"${op.nameO}\")"
        res += "${prefix}        {\n"
        res += "${prefix}            val graphName = \"graph\" + DistributedTripleStore.getGraphNames().size\n"
        res += "${prefix}            val graph=DistributedTripleStore.createGraph(graphName)\n"
        val tmp = rowMapProduced[op.uuid]
        if (tmp != null) {
            for (r in tmp) {
                res += "${prefix}            graph.addData(1L,listOf(\""
                if (sv)
                    res += op.sFilter?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(op.nameS!!)]!!)!!.replace("\"", "\\\"")
                res += "\",\""
                if (pv)
                    res += op.pFilter?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(op.nameP!!)]!!)!!.replace("\"", "\\\"")
                res += "\",\""
                if (ov)
                    res += op.oFilter?.replace("\"", "\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(op.nameO!!)]!!)!!.replace("\"", "\\\"")
                res += "\"))\n"
            }
        }
        res += "${prefix}            DistributedTripleStore.commit(1L)\n"
        res += "${prefix}            LOPTriple($s,$p,$o,graphName,false)"
        if (first)
            res += "${prefix}        }(),\n"
        else
            res += "${prefix}        }()\n"
    } else {
        when (op) {
            is POPUnion -> {
                res += "${prefix}        LOPUnion(\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + "\n"
            }
            is POPJoinHashMap -> {
                res += "${prefix}        LOPJoin(\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + ",\n"
                res += "${prefix}            ${op.optional}"
            }
            is POPJoinNestedLoop -> {
                res += "${prefix}        LOPJoin(\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + ",\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1], "${prefix}            ") + ",\n"
                res += "${prefix}            ${op.optional}"
            }
            is POPRename -> {
                res += "${prefix}        LOPRename(\n"
                res += "${prefix}            AOPVariable(\"${op.nameTo.name}\"),\n"
                res += "${prefix}            AOPVariable(\"${op.nameFrom.name}\"),\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPFilter -> {
                res += "${prefix}        LOPFilter(\n"
                res += "${prefix}            ${testCaseFromAOPBase(op.children[1] as AOPBase)},\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPDistinct -> {
                res += "${prefix}        LOPDistinct(\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPOffset -> {
                res += "${prefix}        LOPOffset(\n"
                res += "${prefix}            ${op.offset},\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPLimit -> {
                res += "${prefix}        LOPLimit(\n"
                res += "${prefix}            ${op.limit},\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPSort -> {
                res += "${prefix}        LOPSort(\n"
                res += "${prefix}            ${op.sortOrder},\n"
                res += "${prefix}            AOPVariable(\"${op.resultSet.getVariable(op.sortBy)}\"),\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPBindUndefined -> {
                res += "${prefix}        LOPBind(\n"
                res += "${prefix}            AOPVariable(\"${op.name.name}\"),\n"
                res += "${prefix}            ${testCaseFromAOPBase(AOPUndef())},\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPBind -> {
                res += "${prefix}        LOPBind(\n"
                res += "${prefix}            AOPVariable(\"${op.name.name}\"),\n"
                res += "${prefix}            ${testCaseFromAOPBase(op.children[1] as AOPBase)},\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPProjection -> {
                res += "${prefix}        LOPProjection(\n"
                res += "${prefix}            mutableListOf(\n"
                for (i in 0 until op.variables.size - 1)
                    res += "${prefix}                AOPVariable(\"${op.variables[i].name}\"),\n"
                if (op.variables.size > 0)
                    res += "${prefix}                AOPVariable(\"${op.variables[op.variables.size - 1].name}\")\n"
                res += "${prefix}            ),\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            is POPFilterExact -> {
                res += "${prefix}        LOPFilterExact(\n"
                res += "${prefix}            AOPVariable(\"${op.variable.name}\"),\n"
                res += "${prefix}            \"${op.value}\",\n"
                res += testCaseFromLOPBaseSimpleRecoursiveHelper(recoursive, rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0], "${prefix}            ") + "\n"
            }
            else -> throw Exception("not implemented testCaseFromLOPBaseSimple(${classNameToString(op)})")
        }
        if (first)
            res += "${prefix}        ),\n"
        else
            res += "${prefix}        )\n"
    }
    if (first) {
        res += testCaseFromResultRowsAsLOPValues(rowMapProduced[op.uuid], op, "${prefix}        ") + "\n"
        res += "${prefix}    )\n"
        res += "${prefix}}()"
    }
    return res
}

