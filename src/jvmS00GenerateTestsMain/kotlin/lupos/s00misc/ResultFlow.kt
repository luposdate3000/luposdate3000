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
val tmp=row[operator.resultSet.createVariable(k)]
if(tmp==null)
                        buffer.appendInt(DynamicByteArray.boolToInt(true))
else{
                        val v = operator.resultSet.getValue(tmp)
buffer.appendInt(DynamicByteArray.boolToInt(v == null))
                        if (v != null)
                            buffer.appendString(v)
                    }
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
fun createBinaryTestCase(operator: OPBase) {
    synchronized(testcasenumber) {
        try {
            var asPOP = true
            val buffer = DynamicByteArray()
            buffer.appendInt(0)
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
        try {
            var asPOP = false
            val buffer = DynamicByteArray()
            buffer.appendInt(0)
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
        val optimizers = mutableSetOf<EOptimizerID>()
        for (o in EOptimizerID.values()) {
            if (!o.optional)
                continue
            optimizers.add(o)
            try {
                var asPOP = false
                val buffer = DynamicByteArray()
                buffer.appendInt(optimizers.size)
                for (x in optimizers)
                    buffer.appendInt(x.ordinal)
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
    val fileName = "src/commonTest/kotlin/lupos/GeneratedMain.kt"
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
		out.println("open class MicroTest0(val input: OPBase, val expected: Any)\n")
out.println("class MicroTestA1(input: AOPBase, val resultRow: ResultRow, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected)\n")
out.println("class MicroTestAN(input: AOPBase, val resultRows: List<ResultRow>, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected)\n")
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
                    if (success)
                        createBinaryTestCase(it)
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
