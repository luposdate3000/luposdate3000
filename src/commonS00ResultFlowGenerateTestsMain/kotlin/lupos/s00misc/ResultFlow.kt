package lupos.s00misc

import lupos.s00misc.*
import lupos.s00misc.classNameToString
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EOperatorID
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
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
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.noinput.AOPBoolean
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
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
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal


val testDictionaryVarName = ResultSetDictionary()
val testDictionaryValue = ResultSetDictionary()

val prefix = ""
val listOfMicroTests = ThreadSafeMutableList<String>()
val mapOfAggregationChilds = ThreadSafeMutableMap<Long, ThreadSafeMutableList<String>>()

val mapOfResultRows = ThreadSafeMutableMap<Long, ThreadSafeMutableList<String>>()

val popMap = ThreadSafeMutableMap<Long, POPBase>()
val rowMapConsumed = ThreadSafeMutableMap<Pair<Long, Long>, ThreadSafeMutableList<ResultRow>>()
val rowMapProduced = ThreadSafeMutableMap<Long, ThreadSafeMutableList<ResultRow>>()
val mutableMapsForTest = ThreadSafeMutableMap<String, String>()
val myuuid = ThreadSafeUuid()

val mapPopToLop = mapOf(
        EOperatorID.POPBindID to EOperatorID.LOPBindID,
        EOperatorID.POPDistinctID to EOperatorID.LOPDistinctID,
        EOperatorID.POPEmptyRowID to EOperatorID.OPNothingID,
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
        is AOPAnd -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPSimpleLiteral -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPLanguageTaggedLiteral -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPTypedLiteral -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPLT -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPNEQ -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is AOPNot -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
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
            buffer.appendInt(testDictionaryVarName.createValue(operator.name))
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
            buffer.appendInt(testDictionaryValue.createValue(operator.prefix))
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
            buffer.appendInt(testDictionaryValue.createValue(operator.prefix))
        }
        is AOPBuildInCallYEAR -> {
            toBinary(operator.children[0], buffer, asPOP)
        }
        is AOPDateTime -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPInteger -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPIri -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPUndef -> {
        }
        is AOPBoolean -> {
            buffer.appendInt(testDictionaryValue.createValue(operator.valueToString()))
        }
        is AOPDivision -> {
            toBinary(operator.children[0], buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
        }
        is POPValues -> {
            val variables = operator.getProvidedVariableNames()
            buffer.appendInt(variables.size)
            for (v in variables) {
                buffer.appendInt(testDictionaryVarName.createValue(v))
            }
            buffer.appendInt(operator.data.size)
            for (row in operator.data) {
                for (k in variables) {
                    val tmp = row[operator.resultSet.createVariable(k)]
                    if (tmp == null)
                        buffer.appendInt(DynamicByteArray.boolToInt(true))
                    else {
                        val v = operator.resultSet.getValue(tmp)
                        buffer.appendInt(DynamicByteArray.boolToInt(v == null))
                        if (v != null)
                            buffer.appendInt(testDictionaryValue.createValue(v))
                    }
                }
            }
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
        is POPBind -> {
            toBinary(operator.name, buffer, asPOP)
            toBinary(operator.children[1], buffer, asPOP)
            toBinary(operator.children[0], buffer, asPOP)
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
            toBinary(operator.sparam, buffer, asPOP)
            toBinary(operator.pparam, buffer, asPOP)
            toBinary(operator.oparam, buffer, asPOP)
            val tmp = rowMapProduced[operator.uuid]
            buffer.appendInt(operator.index.ordinal)
            if (tmp != null) {
                buffer.appendInt(tmp.size())
                tmp.forEach { r ->
                    if (operator.sparam is AOPConstant)
                        buffer.appendInt(testDictionaryValue.createValue((operator.sparam as AOPConstant).valueToString()!!))
                    else
                        buffer.appendInt(testDictionaryValue.createValue(operator.resultSet.getValue(r[operator.resultSet.createVariable((operator.sparam as AOPVariable).name)])!!))
                    if (operator.pparam is AOPConstant)
                        buffer.appendInt(testDictionaryValue.createValue((operator.pparam as AOPConstant).valueToString()!!))
                    else
                        buffer.appendInt(testDictionaryValue.createValue(operator.resultSet.getValue(r[operator.resultSet.createVariable((operator.pparam as AOPVariable).name)])!!))
                    if (operator.oparam is AOPConstant)
                        buffer.appendInt(testDictionaryValue.createValue((operator.oparam as AOPConstant).valueToString()!!))
                    else
                        buffer.appendInt(testDictionaryValue.createValue(operator.resultSet.getValue(r[operator.resultSet.createVariable((operator.oparam as AOPVariable).name)])!!))
                }
            } else {
                buffer.appendInt(0)
            }
        }
        else -> throw Exception("BinaryHelper.toBinary ${operator.operatorID} undefined")
    }
}


fun testCaseBinaryFromResultRowsAsPOPValues(buffer: DynamicByteArray, rows: ThreadSafeMutableList<ResultRow>?, o: OPBase) {
    buffer.appendInt(EOperatorID.POPValuesID.ordinal)
    val variables = o.getProvidedVariableNames()
    buffer.appendInt(variables.size)
    for (v in variables) {
        buffer.appendInt(testDictionaryVarName.createValue(v))
    }
    if (rows != null) {
        buffer.appendInt(rows.size())
        rows.forEach { row ->
            for (k in variables) {
                val v = o.resultSet.getValue(row[o.resultSet.createVariable(k)])
                buffer.appendInt(DynamicByteArray.boolToInt(v == null))
                if (v != null)
                    buffer.appendInt(testDictionaryValue.createValue(v))
            }
        }
    } else
        buffer.appendInt(0)
}

var testcasenumber = 0
val testcasenumberLock = CoroutinesHelper.createLock()
fun createBinaryTestCase(operator: OPBase) {
    CoroutinesHelper.runBlockWithLock(testcasenumberLock) {
        try {
            var asPOP = true
            val buffer = DynamicByteArray()
            buffer.appendInt(0)
            toBinary(operator, buffer, asPOP)
            val filename = "/opt/tmpfs/testcase-${testcasenumber++}.bin"
            File(filename).write(buffer)
            val buffer2 = DynamicByteArray()
            testCaseBinaryFromResultRowsAsPOPValues(buffer2, rowMapProduced[operator.uuid], operator)
            File(filename + ".expect").write(buffer2)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        try {
            var asPOP = false
            val buffer = DynamicByteArray()
            buffer.appendInt(0)
            toBinary(operator, buffer, asPOP)
            val filename = "/opt/tmpfs/testcase-${testcasenumber++}.bin"
            File(filename).write(buffer)
            val buffer2 = DynamicByteArray()
            testCaseBinaryFromResultRowsAsPOPValues(buffer2, rowMapProduced[operator.uuid], operator)
            File(filename + ".expect").write(buffer2)
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
                val filename = "/opt/tmpfs/testcase-${testcasenumber++}.bin"
                File(filename).write(buffer)
                val buffer2 = DynamicByteArray()
                testCaseBinaryFromResultRowsAsPOPValues(buffer2, rowMapProduced[operator.uuid], operator)
                File(filename + ".expect").write(buffer2)
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
        rowMapConsumed[key] = ThreadSafeMutableList(res)
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
        rowMapProduced[producer.uuid] = ThreadSafeMutableList(res)
    else
        list.add(res)
    return res
}

fun <T> resultFlow(inputv: () -> AOPBase, resultRowv: () -> ResultRow, resultSetv: () -> ResultSet, action: () -> T): T {
    return action()
}

val mapOfTestCases = ThreadSafeMutableMap</*mainoperator*/String, ThreadSafeMutableMap<String/*query*/, String/*file*/>>()

fun printAllMicroTest() {
    mapOfTestCases.forEach { operator, testcases ->
        if (testcases.keySize() > 0) {
            val fileName = "/opt/tmpfs/Generated${operator}.kt"
            val myfile = File(fileName)
            myfile.printWriter { out ->
                out.println("package lupos")
                out.println("")
                out.println("import lupos.s10physicalOptimisation.PhysicalOptimizer")
                out.println("import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer")
                out.println("import lupos.s12p2p.P2P")
                out.println("import lupos.s14endpoint.EndpointImpl")
                out.println("import lupos.s15tripleStoreDistributed.*")
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
                out.println("        if (node is AOPAggregationBase) {")
                out.println("                node.count = count")
                out.println("            node.collectMode = mode")
                out.println("            if (node.collectMode)")
                out.println("                node.a = null")
                out.println("        }")
                out.println("    }")
                out.println("")
                out.println("${prefix} @TestFactory")
                out.println("${prefix} fun test() = listOf(")
                testcases.forEach { k, v ->
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
    val fileName = "/opt/tmpfs/GeneratedMain.kt"
    val myfile = File(fileName)
    myfile.printWriter { out ->
        out.println("package lupos")
        out.println("")
        out.println("import lupos.s10physicalOptimisation.PhysicalOptimizer")
        out.println("import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer")
        out.println("import lupos.s12p2p.P2P")
        out.println("import lupos.s14endpoint.EndpointImpl")
        out.println("import lupos.s15tripleStoreDistributed.*")
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
    mapOf(
            testDictionaryVarName to "DictionaryVarName",
            testDictionaryValue to "DictionaryValue"
    ).forEach { k, v ->
        val dictbinary = DynamicByteArray()
        val s = k.mapLTS.size()
        dictbinary.appendInt(s)
        for (i in 0 until s)
            dictbinary.appendString(k.getValue(i)!!)
        File("/opt/tmpfs/${v}").write(dictbinary)
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
                val tmp: ThreadSafeMutableMap<String, String>
                if (x == null) {
                    mapOfTestCases[name] = ThreadSafeMutableMap<String, String>()
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
        if (!success) {
            rowMapProduced.forEach { k, v ->
                println("operator-produced :: $k -> ${v.size()}")
            }
            rowMapConsumed.forEach { k, v ->
                println("operator-consumed :: $k -> ${v.size()}")
                if (rowMapProduced[k.first] == null || rowMapProduced[k.first]!!.size() == 0) {
                    println("no-a ${k.second}")
                }
                if (rowMapProduced[k.second] == null || rowMapProduced[k.second]!!.size() == 0) {
                    println("no-b ${k.first}")
                }
            }
        }
        popMap.forEachValue {
            try {
                if (success)
                    createBinaryTestCase(it)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
    listOfMicroTests.clear()
    mapOfAggregationChilds.clear()
    popMap.clear()
    rowMapConsumed.clear()
    rowMapProduced.clear()
}




