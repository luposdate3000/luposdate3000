package lupos.s00misc

import java.io.File
import lupos.s00misc.*
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.*
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

fun executeBinaryTests(folder: String) {
var testcases=0
    try {
        File(folder).walk().forEach {
            val filename: String = it.toRelativeString(File("."))
            if (filename.endsWith(".bin")) {
testcases++
                println("execute test $filename")
                val dictionary = ResultSetDictionary()
                var input: OPBase? = null
                var asPOP = false
                File(filename).inputStream().use { instream ->
                    val data = instream.readBytes()
                    val buffer = DynamicByteArray(data)
                    val optimizerEnabledCount = buffer.getNextInt()
                    for (o in 0 until optimizerEnabledCount) {
                        val optimizer = EOptimizerID.values()[buffer.getNextInt()]
                        ExecuteOptimizer.enabledOptimizers[optimizer] = true
                    }
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
                if (input!! is POPBase) {
                    val output = QueryResultToXML.toXML(input!! as POPBase).first()
                    if (!expected.myEquals(output)) {
                        println((expectPOP as POPValues).toXMLElement().toPrettyString())
                        println(input!!.toXMLElement().toPrettyString())
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
println("executed testcases : $testcases")
}
