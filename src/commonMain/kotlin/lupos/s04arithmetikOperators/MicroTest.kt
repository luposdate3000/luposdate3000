package lupos.s04arithmetikOperators
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableMap
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s09physicalOperators.*


val prefix = "--MicroTest--"
val listOfMicroTests = ThreadSafeMutableList<String>()

data class MicroTest(val input: AOPBase, val resultRow: ResultRow, val resultSet: ResultSet, val expected: Any) {
}

val mapOfResultRows=ThreadSafeMutableMap<Long,List<String>>()
fun <T> resultFlow(input: POPBase, action: () -> T): T {
return action()
}

fun resultRowToString(resultRow: ResultRow, resultSet: ResultSet,prefix:String,variableNames:Map<String, String>):String{
    var res=""
    res += "${prefix}{\n"
    res += "${prefix}    val resultRow = resultSet.createResultRow()\n"
    for (v in resultSet.getVariableNames()) {
        val name = variableNames[v]!!
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

fun <T> resultFlow(input: AOPBase, resultRow: ResultRow, resultSet: ResultSet, action: () -> T): T {
    val expected = action()
    val variableNames = mutableMapOf<String, String>()
    if (input is AOPVariable)
        return expected
    var res = "{\n"
    res += "${prefix}                val resultSet = ResultSet(ResultSetDictionary())\n"
    for (v in resultSet.getVariableNames()) {
        val name = when {
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
        res += "${prefix}                resultSet.createVariable(\"$name\")\n"
    }
    res += "${prefix}                MicroTest(\n"
    res += "${prefix}                        " + input.toTestCaseInput() + ",\n"
    res += resultRowToString(resultRow,resultSet,"${prefix}                        ",variableNames)
    res += "${prefix}                        resultSet,\n"
    if (expected is AOPBase)
        res += "${prefix}                        " + expected.toTestCaseInput() + "\n"
    else
        res += "${prefix}                        Exception(\"${(expected as Throwable).message!!.replace("\"", "\\\"")}\")\n"
    res += "${prefix}                )\n"
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
    if (listOfMicroTests.size() > 0) {
        val name = testName.replace("/", "_").replace(".", "_").replace("-", "_")
        println("${prefix}    @TestFactory")
        println("${prefix}    fun test${name}() = listOf(")
        listOfMicroTests.forEach {
            if (success) {
                if (it.contains("AOPAggregation") || it.contains("AOPBuildInCallBNODE1") || it.contains("AOPBuildInCallBNODE0"))
                    println("${prefix}            /*" + it + "*/")
                else
                    println("${prefix}            " + it + ",")
            } else
                println("${prefix}            /*" + it + "*/")
        }
        listOfMicroTests.clear()
        println("${prefix}            {")
        println("${prefix}                val resultSet = ResultSet(ResultSetDictionary())")
        println("${prefix}                val resultRow = resultSet.createResultRow()")
        println("${prefix}                MicroTest(AOPUndef(), resultRow, resultSet, AOPUndef())")
        println("${prefix}            }()")
        println("${prefix}    ).mapIndexed { index, data ->")
        println("${prefix}        DynamicTest.dynamicTest(\"test->${testName}<-\$index\") {")
        println("${prefix}            try {")
        println("${prefix}                val output = data.input.calculate(data.resultSet, data.resultRow)")
        println("${prefix}                assertTrue(data.expected is AOPConstant)")
        println("${prefix}                if (!data.expected.equals(output)) {")
        println("${prefix}                    println(data.resultRow)")
        println("${prefix}                    println(output.valueToString())")
        println("${prefix}                    println((data.expected as AOPConstant).valueToString())")
        println("${prefix}                }")
        println("${prefix}                assertTrue(data.expected.equals(output))")
        println("${prefix}            } catch (e: Throwable) {")
        println("${prefix}                assertTrue(data.expected is Throwable)")
        println("${prefix}            }")
        println("${prefix}        }")
        println("${prefix}    }")
        println("${prefix}")
    }
}
