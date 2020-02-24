package lupos.s04arithmetikOperators

import java.io.File
import lupos.s00misc.*
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
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
import lupos.s04arithmetikOperators.noinput.AOPUndef
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallIRI
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallURI
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s15tripleStoreDistributed.*


val prefix = ""
val listOfMicroTests = ThreadSafeMutableList<String>()
val mapOfAggregationChilds = ThreadSafeMutableMap<Long, MutableList<String>>()

open class MicroTest0(val input: OPBase, val expected: Any) {
}

class MicroTestA1(input: AOPBase, val resultRow: ResultRow, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected) {
}

class MicroTestAN(input: AOPBase, val resultRows: List<ResultRow>, val resultSet: ResultSet, expected: Any) : MicroTest0(input, expected) {
}

class MicroTestPN(input: POPBase, expected: Any) : MicroTest0(input, expected) {
}

val mapOfResultRows = ThreadSafeMutableMap<Long, MutableList<String>>()

val popMap = ThreadSafeMutableMap<Long, POPBase>()
val rowMapConsumed = ThreadSafeMutableMap<Pair<Long, Long>, MutableList<ResultRow>>()
val rowMapProduced = ThreadSafeMutableMap<Long, MutableList<ResultRow>>()

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

fun testCaseFromResultRowsAsPOPValues(rows: MutableList<ResultRow>?, resultSet: ResultSet, prefix: String): String {
    var res = "${prefix}POPValues(dictionary, listOf(\n"
    val tmp = resultSet.getVariableNames()
    if (tmp.size > 0) {
        for (v in tmp) {
            res += "${prefix}        \"$v\",\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    ), listOf(\n"
    if (rows != null) {
        for (row in rows) {
            res += "${prefix}        mutableMapOf(\n"
            if (resultSet.getVariableNames().size > 0) {
                for (k in resultSet.getVariableNames()) {
                    val v = resultSet.getValue(row[resultSet.createVariable(k)])?.replace("\"", "\\\"")
                    if (v == null)
                        res += "${prefix}            \"${k}\" to null,\n"
                    else
                        res += "${prefix}            \"${k}\" to \"${v}\",\n"
                }
                res = res.substring(0, res.length - 2) + "\n"
            }
            res += "${prefix}        ),\n"
        }
        res = res.substring(0, res.length - 2) + "\n"
    }
    res += "${prefix}    )\n"
    res += "${prefix})"
    return res
}

fun testCaseFromPOPBaseSimple(op: POPBase): String {
    var res = "{\n"
    res += "${prefix}                val dictionary=ResultSetDictionary()\n"
    res += "${prefix}                MicroTestPN(\n"
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
        res += "${prefix}                    {\n"
        res += "${prefix}                        val graphName = \"graph\" + DistributedTripleStore.getGraphNames().size\n"
        res += "${prefix}                        val graph=DistributedTripleStore.createGraph(graphName)\n"
val tmp=rowMapProduced[op.uuid]
if(tmp!=null){
            for (r in tmp) {
                res += "${prefix}                        graph.addData(1L,listOf(\""
                if (sv)
                    res += s?.replace("\"","\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(s!!)]!!)!!.replace("\"","\\\"")
                res += "\",\""
                if (pv)
                    res += p?.replace("\"","\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(p!!)]!!)!!.replace("\"","\\\"")
                res += "\",\""
                if (ov)
                    res += o?.replace("\"","\\\"")
                else
                    res += op.resultSet.getValue(r[op.resultSet.createVariable(o!!)]!!)!!.replace("\"","\\\"")
                res += "\"))\n"
            }
        }
	    res += "${prefix}                        DistributedTripleStore.commit(1L)\n"
            res += "${prefix}                        TripleStoreIteratorGlobal(1L,dictionary,graphName,\"${s?.replace("\"","\\\"")}\",\"${p?.replace("\"","\\\"")}\",\"${o?.replace("\"","\\\"")}\",$sv,$pv,$ov,EIndexPattern.${op.idx})\n"
            res += "${prefix}                    }(),\n"
    } else {
        res += "${prefix}                    ${classNameToString(op)}(\n"
        res += "${prefix}                        dictionary,\n"
        when (op) {
            is POPUnion -> {
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + ",\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1].resultSet, "${prefix}                        ") + "\n"
            }
            is POPJoinHashMap -> {
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + ",\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1].resultSet, "${prefix}                        ") + ",\n"
                res += "${prefix}                        ${op.optional}"
            }
            is POPJoinNestedLoop -> {
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + ",\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1].resultSet, "${prefix}                        ") + ",\n"
                res += "${prefix}                        ${op.optional}"
            }
            is POPRename -> {
                res += "${prefix}                        AOPVariable(\"${op.nameTo.name}\"),\n"
                res += "${prefix}                        AOPVariable(\"${op.nameFrom.name}\"),\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPFilter -> {
                res += "${prefix}                        POPExpression(dictionary, ${testCaseFromAOPBase(op.children[1].children[0] as AOPBase)}),\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPDistinct -> {
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPOffset -> {
                res += "${prefix}                        ${op.offset},\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPLimit -> {
                res += "${prefix}                        ${op.limit},\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPSort -> {
                res += "${prefix}                        AOPVariable(\"${op.resultSet.getVariable(op.sortBy)}\"),\n"
                res += "${prefix}                        ${op.sortOrder},\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPBindUndefined -> {
                res += "${prefix}                        AOPVariable(\"${op.name.name}\"),\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPBind -> {
                res += "${prefix}                        AOPVariable(\"${op.name.name}\"),\n"
                res += "${prefix}                        POPExpression(dictionary, ${testCaseFromAOPBase(op.children[1].children[0] as AOPBase)}),\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPProjection -> {
                res += "${prefix}                        mutableListOf(\n"
                for (i in 0 until op.variables.size - 1)
                    res += "${prefix}                            AOPVariable(\"${op.variables[i].name}\"),\n"
                if (op.variables.size > 0)
                    res += "${prefix}                            AOPVariable(\"${op.variables[op.variables.size - 1].name}\")\n"
                res += "${prefix}                        ),\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            is POPFilterExact -> {
                res += "${prefix}                        AOPVariable(\"${op.variable.name}\"),\n"
                res += "${prefix}                        \"${op.value}\",\n"
                res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + "\n"
            }
            else -> throw Exception("not implemented testCaseFromPOPBaseSimple(${classNameToString(op)})")
        }
        res += "${prefix}                    ),\n"
    }
    res += testCaseFromResultRowsAsPOPValues(rowMapProduced[op.uuid], op.resultSet, "${prefix}                    ") + "\n"
    res += "${prefix}                )\n"
    return res + "${prefix}            }()"
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
                out.println("    constructor(){")
                out.println("        P2P.knownClients.add(EndpointImpl.fullname)")
                out.println("    }")
                out.println("    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {")
                out.println("        for (n in node.children)")
                out.println("            setAggregationMode(n, mode, count)")
                out.println("        if (node is AOPAggregation) {")
                out.println("            node.count = count")
                out.println("            node.collectMode = mode")
                out.println("            if (node.collectMode)")
                out.println("                node.a = null")
                out.println("        }")
                out.println("    }")
                out.println("")
                out.println("${prefix}    @TestFactory")
                out.println("${prefix}    fun test() = listOf(")
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
                out.println("${prefix}                } else if (data.input is POPBase) {")
                out.println("${prefix}                    val input = data.input as POPBase")
                out.println("${prefix}                    assertTrue(data.expected is POPValues)")
                out.println("${prefix}                    val output = QueryResultToXML.toXML(input).first()")
                out.println("${prefix}                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()")
                out.println("${prefix}                    if (!expected.myEquals(output)){")
                out.println("${prefix}                        println(output.toPrettyString())")
                out.println("${prefix}                        println(expected.toPrettyString())")
                out.println("${prefix}                    }")
                out.println("${prefix}                    assertTrue(expected.myEquals(output))")
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
                        tmp["${prefix}            " + testCaseFromPOPBaseSimple(it)] = queryFile
                    else
                        tmp["${prefix}            /*" + testCaseFromPOPBaseSimple(it) + "*/"] = queryFile
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


