package lupos.s00misc

import java.io.File
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAddition
import lupos.s04arithmetikOperators.multiinput.AOPDivision
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.multiinput.AOPGEQ
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.AOPAggregation
import lupos.s04arithmetikOperators.noinput.AOPBnode
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPDateTime
import lupos.s04arithmetikOperators.noinput.AOPDecimal
import lupos.s04arithmetikOperators.noinput.AOPDouble
import lupos.s04arithmetikOperators.noinput.AOPInteger
import lupos.s04arithmetikOperators.noinput.AOPIri
import lupos.s04arithmetikOperators.noinput.AOPLanguageTaggedLiteral
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04arithmetikOperators.noinput.AOPTypedLiteral
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallABS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBNODE1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCEIL
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDATATYPE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallDAY
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallFLOOR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallHOURS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIsNUMERIC
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallLCASE
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMD5
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMINUTES
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallMONTH
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallROUND
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSECONDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA1
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSHA256
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTR
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRLEN
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallSTRSTARTS
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
            val s = fromBinary(dictionary, buffer) as AOPBase
            val p = fromBinary(dictionary, buffer) as AOPBase
            val o = fromBinary(dictionary, buffer) as AOPBase
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
        EOperatorID.LOPTripleID -> {
            var graphName = "graph" + DistributedTripleStore.getGraphNames().size
            val graph = DistributedTripleStore.createGraph(graphName)
            val s = fromBinary(dictionary, buffer) as AOPBase
            val p = fromBinary(dictionary, buffer) as AOPBase
            val o = fromBinary(dictionary, buffer) as AOPBase
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

fun executeBinaryTests(folder: String) {
    var testcases = 0
    try {
        File(folder).walk().forEach {
            val filename: String = it.toRelativeString(File("."))
            if (filename.endsWith(".bin")) {
                testcases++
                println("execute test $filename")
                val dictionary = ResultSetDictionary()
                var input: OPBase? = null
                File(filename).inputStream().use { instream ->
                    val data = instream.readBytes()
                    val buffer = DynamicByteArray(data)
                    val optimizerEnabledCount = buffer.getNextInt()
                    ExecuteOptimizer.enabledOptimizers.clear()
                    for (o in 0 until optimizerEnabledCount) {
                        val optimizer = EOptimizerID.values()[buffer.getNextInt()]
                        ExecuteOptimizer.enabledOptimizers[optimizer] = true
                    }
                    input = fromBinary(dictionary, buffer)
                }
                println("input::" + input!!.toXMLElement().toPrettyString())
                var expectPOP: POPValues? = null
                try {
                    File(filename + ".expect").inputStream().use { instream ->
                        val data = instream.readBytes()
                        val buffer = DynamicByteArray(data)
                        expectPOP = fromBinary(dictionary, buffer) as POPValues
                    }
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
                val expected = QueryResultToXML.toXML(expectPOP!!).first()
                if (input!! is POPBase) {
                    val output = QueryResultToXML.toXML(input!! as POPBase).first()
                    if (!expected.myEquals(output)) {
                        println((expectPOP as POPValues).toXMLElement().toPrettyString())
                        println(input!!.toXMLElement().toPrettyString())
                        println(expected.toPrettyString())
                        println(output.toPrettyString())
                    }
                    require(expected.myEquals(output))
                } else {
                    val lop_node = input!! as LOPBase
                    val lOptimizer = LogicalOptimizer(1L, dictionary)
                    val pOptimizer = PhysicalOptimizer(1L, dictionary)
                    val dOptimizer = KeyDistributionOptimizer(1L, dictionary)
                    val lop_node2 = lOptimizer.optimizeCall(lop_node)
                    val pop_node = pOptimizer.optimizeCall(lop_node2)
                    val input2 = dOptimizer.optimizeCall(pop_node) as POPBase
                    val output = QueryResultToXML.toXML(input2).first()
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
    println("executed testcases : $testcases")
}
