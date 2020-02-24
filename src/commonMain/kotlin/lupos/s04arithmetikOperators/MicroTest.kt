package lupos.s04arithmetikOperators

import lupos.s00misc.classNameToString
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


val prefix = "--MicroTest--"
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
    var res = "${prefix}{\n"
    res += "${prefix}    POPValues(dictionary, listOf(\n"
    if (rows != null) {
        for (row in rows) {
            res += "${prefix}            mutableMapOf(\n"
            for (k in resultSet.getVariableNames())
                res += "${prefix}                \"${k}\" to \"${resultSet.getValue(row[resultSet.createVariable(k)])!!.replace("\"", "\\\"")}\",\n"
	    res=res.substring(0,res.length-2)+"\n"
            res += "${prefix}            ),\n"
        }
	res=res.substring(0,res.length-2)+"\n"
    }
    res += "${prefix}        )\n"
    res += "${prefix}    )\n"
    return res + "${prefix}}"
}

fun testCaseFromPOPBaseSimple(op: POPBase): String {
    var res = "${prefix}            {\n"
    res += "${prefix}                MicroTestPN(\n"
    res += "${prefix}                    ${classNameToString(op)}(\n"
    when (op) {
        is POPUnion -> {
            res += "${prefix}                        dictionary,\n"
            res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[0].uuid)], op.children[0].resultSet, "${prefix}                        ") + ",\n"
            res += testCaseFromResultRowsAsPOPValues(rowMapConsumed[Pair(op.uuid, op.children[1].uuid)], op.children[1].resultSet, "${prefix}                        ") + "\n"
        }
        else -> throw Exception("not implemented testCaseFromPOPBaseSimple(${classNameToString(op)})")
/*is POPJoinHashMap -> {}
is POPJoinNestedLoop -> {}
is POPModify -> {}
is POPRename -> {}
is POPFilter -> {}
is POPBindUndefined -> {}
is POPTemporaryStore -> {}
is POPLimit -> {}
is POPOffset -> {}
is POPDistinct -> {}
is POPProjection -> {}
is POPBind -> {}
is POPFilterExact -> {}
is POPMakeBooleanResult -> {}
is POPGroup -> {}
is POPSort -> {}
is POPModifyData -> {}
is POPGraphOperation -> {}
is POPEmptyRow -> {}
is POPValues -> {}
is POPImportFromXml -> {}
*/
    }
    res += "${prefix}                    ),\n"
    res += testCaseFromResultRowsAsPOPValues(rowMapProduced[op.uuid], op.resultSet, "${prefix}                    ") + "\n"
    res += "${prefix}                )\n"
    return res + "${prefix}            }"
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
            res += "${prefix}                        " + testCaseFromAOPBase(input, resultRow, resultSet) + ",\n"

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
                res += "${prefix}                        " + testCaseFromAOPBase(expected, resultRow, resultSet) + "\n"
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
        res += "${prefix}                        " + testCaseFromAOPBase(input, resultRow, resultSet) + ",\n"
        res += testCaseFromResultRow(resultRow, resultSet, "${prefix}                        ", variableNames)
        res += "${prefix}                        resultSet,\n"
        if (expected is AOPBase)
            res += "${prefix}                        " + testCaseFromAOPBase(expected, resultRow, resultSet) + "\n"
        else
            res += "${prefix}                        Exception(\"${(expected as Throwable).message!!.replace("\"", "\\\"")}\")\n"
        res += "${prefix}                )\n"
    } else {
        res += "${prefix}                MicroTest0(\n"
        res += "${prefix}                        " + testCaseFromAOPBase(input, resultRow, resultSet) + ",\n"
        if (expected is AOPBase)
            res += "${prefix}                        " + testCaseFromAOPBase(expected, resultRow, resultSet) + "\n"
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

fun printAllMicroTest(testName: String, success: Boolean) {
    if (listOfMicroTests.size() > 0 || popMap.keySize()>0) {
if (listOfMicroTests.size() > 0){
        val name = testName.replace("/", "_").replace(".", "_").replace("-", "_")
        println("${prefix}    @TestFactory")
        println("${prefix}    fun test${name}() = listOf(")
        val tmp = mutableListOf<String>()
        listOfMicroTests.forEach {
            tmp.add(it)
        }
        tmp.sorted().forEach {
            if (success) {
                if (it.contains("AOPBuildInCallBNODE1") || it.contains("AOPBuildInCallBNODE0") || it.contains("AOPBuildInCallNOW"))
                    println("${prefix}            /*" + it + "*/")
                else
                    println("${prefix}            " + it + ",")
            } else
                println("${prefix}            /*" + it + "*/")
        }
}
if(popMap.keySize()>0){
        popMap.forEachValue {
            try {
                println(testCaseFromPOPBaseSimple(it)+",")
            } catch (e: Throwable) {
                println(e.message)
            }
        }
}
        println("${prefix}            {")
        println("${prefix}                MicroTest0(AOPUndef(), AOPUndef())")
        println("${prefix}            }()")
        println("${prefix}    ).mapIndexed { index, data ->")
        println("${prefix}        DynamicTest.dynamicTest(\"test->${testName}<-\$index\") {")
        println("${prefix}            try {")
        println("${prefix}                if(data.input is AOPBase){")
        println("${prefix}                    val output:AOPConstant")
        println("${prefix}                    if (data is MicroTestA1) {")
        println("${prefix}                        output = data.input.calculate(data.resultSet, data.resultRow)")
        println("${prefix}                    } else if (data is MicroTestAN) {")
        println("${prefix}                        setAggregationMode(data.input, true, data.resultRows.count())")
        println("${prefix}                        for (resultRow in data.resultRows)")
        println("${prefix}                            data.input.calculate(data.resultSet, resultRow)")
        println("${prefix}                        setAggregationMode(data.input, false, data.resultRows.count())")
        println("${prefix}                        output = data.input.calculate(data.resultSet, data.resultSet.createResultRow())")
        println("${prefix}                    } else {")
        println("${prefix}                        val resultSet = ResultSet(ResultSetDictionary())")
        println("${prefix}                        output = data.input.calculate(resultSet, resultSet.createResultRow())")
        println("${prefix}                    }")
        println("${prefix}                    assertTrue(data.expected is AOPConstant)")
        println("${prefix}                    if (!data.expected.equals(output)) {")
        println("${prefix}                        if(data is MicroTestA1)")
        println("${prefix}                            println(data.resultRow)")
        println("${prefix}                        println(output.valueToString())")
        println("${prefix}                        println((data.expected as AOPConstant).valueToString())")
        println("${prefix}                    }")
        println("${prefix}                    assertTrue(data.expected.equals(output))")
        println("${prefix}                } else if (data.input is POPBase){")
        println("${prefix}                    assertTrue(data.expected is POPValues)")
        println("${prefix}                    val output=QueryResultToXML(data.input)")
        println("${prefix}                    val expected=QueryResultToXML(data.expected as POPValues)")
        println("${prefix}                    if(!expected.myEquals(output))")
        println("${prefix}                        println(output.toPrettyString())")
        println("${prefix}                    assertTrue(expected.myEquals(output))")
        println("${prefix}                }")
        println("${prefix}            } catch (e: Throwable) {")
        println("${prefix}                assertTrue(data.expected is Throwable)")
        println("${prefix}            }")
        println("${prefix}        }")
        println("${prefix}    }")
        println("${prefix}")
    }
    listOfMicroTests.clear()
    mapOfAggregationChilds.clear()
    popMap.clear()
    rowMapConsumed.clear()
    rowMapProduced.clear()
}

fun testCaseFromAOPBase(input: AOPBase, resultRow: ResultRow, resultSet: ResultSet): String {
    when (input) {
        is AOPBuildInCallURI -> return "AOPBuildInCallURI(${testCaseFromAOPBase(input.children[0] as AOPBase, resultRow, resultSet)}, \"${input.prefix}\")"
        is AOPBuildInCallIRI -> return "AOPBuildInCallIRI(${testCaseFromAOPBase(input.children[0] as AOPBase, resultRow, resultSet)}, \"${input.prefix}\")"
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
            var res = "AOPAggregation(Aggregation.${input.type},${input.distinct},arrayOf("
            if (input.children.size > 0)
                res += testCaseFromAOPBase(input.children[0] as AOPBase, resultRow, resultSet)
            for (i in 1 until input.children.size)
                res += "," + testCaseFromAOPBase(input.children[i] as AOPBase, resultRow, resultSet)
            return res + "))"
        }
        else -> {
            var res = ""
            res += "${classNameToString(input)}("
            if (input.children.size > 0)
                res += testCaseFromAOPBase((input.children[0] as AOPBase), resultRow, resultSet)
            for (i in 1 until input.children.size)
                res += ", " + testCaseFromAOPBase((input.children[i] as AOPBase), resultRow, resultSet)
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


