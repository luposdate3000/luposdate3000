package lupos.s04arithmetikOperators

import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase

val prefix="--MicroTest--"

data class MicroTest(val input: AOPBase, val resultRow: ResultRow, val resultSet: ResultSet, val expected: Any) {
}

fun <T> addMicroTest(input: AOPBase, resultRow: ResultRow, resultSet: ResultSet, expected: T): T {
    if (input is AOPVariable)
        return expected
    var res = "{"
    res += "val resultSet = ResultSet(ResultSetDictionary());"
    res += "val resultRow = resultSet.createResultRow();"
    res += "MicroTest("
    res += input.toTestCaseInput() + ","
    res += "{"
    for (v in resultSet.getVariableNames())
        res += "resultRow[resultSet.createVariable(\"$v\")]=resultSet.createValue(\"${AOPVariable("v").calculate(resultSet, resultRow).toTestCaseInput()}\");"
    res += "}(),"
    res += "resultSet"
    if (expected is AOPBase)
        res += expected.toTestCaseInput()
    else
        res += "Exception(${(expected as Throwable).message})"
    res += ")}()"
    listOfMicroTests.add(res)
    return expected
}

val listOfMicroTests = ThreadSafeMutableSet<String>()

fun printAllMicroTest(testName: String, success: Boolean) {
    if (listOfMicroTests.size() > 0) {
        val name = testName.replace("/", "_").replace(".", "_")
        println("${prefix}@TestFactory")
        println("${prefix}fun test${name}()=listOf(")
        listOfMicroTests.forEach {
            if (success)
                println("${prefix}        "+it + ",")
            else
                println("${prefix}      /*" + it + "*/")
        }
        listOfMicroTests.clear()
        println("${prefix}        {val resultSet = ResultSet(ResultSetDictionary());val resultRow = resultSet.createResultRow();MicroTest(AOPUndef(),resultSet,resultRow,AOPUndef())}()")
        println("${prefix}    ).mapIndexed{index,data->")
        println("${prefix}        DynamicTest.dynamicTest(\"test-\$index\") {")
        println("${prefix}        try{")
        println("${prefix}            val output = data.input.calculate(data.resultSet, data.resultRow)")
        println("${prefix}            assertTrue(data.expected is AOPBase)")
        println("${prefix}            assertTrue(data.expected.equals(output))")
        println("${prefix}        }catch(e:Throwable){ ")
        println("${prefix}            assertTrue(data.expected is Throwable)")
        println("${prefix}        }")
        println("${prefix}    }")
        println("${prefix}}")
    }
}
