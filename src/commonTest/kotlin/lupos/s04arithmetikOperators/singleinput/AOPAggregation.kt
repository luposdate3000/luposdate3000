package lupos.s04arithmetikOperators.noinput

import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class AOPAggregationTest {
    val timeNow = AOPDateTime()
    val data = listOf(
            AOPInteger(0) to AOPInteger(0),
            AOPInteger(-1) to AOPInteger(-1),
            AOPInteger(1) to AOPInteger(1),
            AOPInteger(Int.MAX_VALUE) to AOPInteger(Int.MAX_VALUE),
            AOPInteger(Int.MIN_VALUE) to AOPInteger(Int.MIN_VALUE),
            AOPDouble(0.0) to AOPDouble(0.0),
            AOPDouble(-1.0) to AOPDouble(-1.0),
            AOPDouble(1.0) to AOPDouble(1.0),
            AOPDouble(-0.1) to AOPDouble(-0.1),
            AOPDouble(0.1) to AOPDouble(0.1),
            AOPDouble(Double.MAX_VALUE) to AOPDouble(Double.MAX_VALUE),
            AOPDouble(Double.MIN_VALUE) to AOPDouble(Double.MIN_VALUE),
            AOPDecimal(0.0) to AOPDecimal(0.0),
            AOPDecimal(-1.0) to AOPDecimal(-1.0),
            AOPDecimal(1.0) to AOPDecimal(1.0),
            AOPDecimal(-0.1) to AOPDecimal(-0.1),
            AOPDecimal(0.1) to AOPDecimal(0.1),
            AOPDecimal(Double.MAX_VALUE) to AOPDecimal(Double.MAX_VALUE),
            AOPDecimal(Double.MIN_VALUE) to AOPDecimal(Double.MIN_VALUE),
            AOPBooleanLiteral(true) to AOPBooleanLiteral(true),
            AOPBooleanLiteral(false) to AOPBooleanLiteral(false),
            AOPBnode("0") to AOPBnode("0"),
            AOPBnode("a") to AOPBnode("a"),
            AOPBnode("abcde") to AOPBnode("abcde"),
            AOPIri("a") to AOPIri("a"),
            AOPIri("http://bla") to AOPIri("http://bla"),
            AOPSimpleLiteral("\"", "a") to AOPSimpleLiteral("\"", "a"),
            AOPTypedLiteral("\"", "a", "b") to AOPTypedLiteral("\"", "a", "b"),
            AOPLanguageTaggedLiteral("\"", "a", "b") to AOPLanguageTaggedLiteral("\"", "a", "b"),
            timeNow to timeNow,
            AOPUndef() to AOPUndef()
    )

    @TestFactory
    fun testMin() = listOf(
            listOf(AOPInteger(0)) to AOPInteger(0),
            listOf(AOPInteger(0), AOPInteger(1)) to AOPInteger(0),
            listOf(AOPInteger(1), AOPInteger(0)) to AOPInteger(0),
            listOf(AOPInteger(0), AOPInteger(-1)) to AOPInteger(-1),
            listOf(AOPInteger(-1), AOPInteger(0)) to AOPInteger(-1)
    ).map { (input, expected) ->
        val list = mutableListOf<String>()
        for (i in input)
            list.add(i.valueToString())
        DynamicTest.dynamicTest("calculate(${list} to ${expected.valueToString()})") {
            val resultSet = ResultSet(ResultSetDictionary())
            val variable = resultSet.createVariable("a")
            val aggregation = AOPAggregation(Aggregation.MIN, false, arrayOf(AOPVariable("a")))
            aggregation.count = input.size
            for (i in input) {
                val tmp = i.valueToString()
                val resultRow = resultSet.createResultRow()
                if (tmp == null) {
                    resultSet.setUndefValue(resultRow, variable)
                } else {
                    val value = resultSet.createValue(tmp)
                    resultRow[variable] = value
                }
                aggregation.calculate(resultSet, resultRow)
            }
            println("a")
            assertTrue(resultSet.getVariableNames().contains("a"))
            aggregation.collectMode = false
            val output = aggregation.calculate(resultSet, resultSet.createResultRow())
            println("b ${output.valueToString()} ${expected.valueToString()}")
            assertTrue(expected.equals(output))
            println("c")
            assertTrue(output.equals(output))
            println("d")
            assertTrue(input.equals(input))
        }
    }
}
