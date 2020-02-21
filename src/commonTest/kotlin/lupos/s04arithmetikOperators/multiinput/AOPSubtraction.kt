package lupos.s04arithmetikOperators.multiinput

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


class AOPSubtractionTest {
    @TestFactory
    fun testCalculate() = listOf(
            listOf(AOPInteger(0), AOPInteger(1)) to AOPInteger(-1),
            listOf(AOPInteger(1), AOPInteger(0)) to AOPInteger(1),
            listOf(AOPInteger(0), AOPInteger(-1)) to AOPInteger(1),
            listOf(AOPInteger(-1), AOPInteger(0)) to AOPInteger(-1),
            listOf(AOPDecimal(0.0), AOPDecimal(1.0)) to AOPDecimal(-1.0),
            listOf(AOPDecimal(1.0), AOPDecimal(0.0)) to AOPDecimal(1.0),
            listOf(AOPDecimal(0.0), AOPDecimal(-1.0)) to AOPDecimal(1.0),
            listOf(AOPDecimal(-1.0), AOPDecimal(0.0)) to AOPDecimal(-1.0),
            listOf(AOPDouble(0.0), AOPDouble(1.0)) to AOPDouble(-1.0),
            listOf(AOPDouble(1.0), AOPDouble(0.0)) to AOPDouble(1.0),
            listOf(AOPDouble(0.0), AOPDouble(-1.0)) to AOPDouble(1.0),
            listOf(AOPDouble(-1.0), AOPDouble(0.0)) to AOPDouble(-1.0),
            listOf(AOPInteger(0), AOPDecimal(1.0)) to AOPDecimal(-1.0),
            listOf(AOPInteger(1), AOPDecimal(0.0)) to AOPDecimal(1.0),
            listOf(AOPInteger(0), AOPDecimal(-1.0)) to AOPDecimal(1.0),
            listOf(AOPInteger(-1), AOPDecimal(0.0)) to AOPDecimal(-1.0),
            listOf(AOPInteger(0), AOPDouble(1.0)) to AOPDouble(-1.0),
            listOf(AOPInteger(1), AOPDouble(0.0)) to AOPDouble(1.0),
            listOf(AOPInteger(0), AOPDouble(-1.0)) to AOPDouble(1.0),
            listOf(AOPInteger(-1), AOPDouble(0.0)) to AOPDouble(-1.0),
            listOf(AOPDecimal(0.0), AOPDouble(1.0)) to AOPDouble(-1.0),
            listOf(AOPDecimal(1.0), AOPDouble(0.0)) to AOPDouble(1.0),
            listOf(AOPDecimal(0.0), AOPDouble(-1.0)) to AOPDouble(1.0),
            listOf(AOPDecimal(-1.0), AOPDouble(0.0)) to AOPDouble(-1.0),
            listOf(AOPDecimal(0.0), AOPInteger(1)) to AOPDecimal(-1.0),
            listOf(AOPDecimal(1.0), AOPInteger(0)) to AOPDecimal(1.0),
            listOf(AOPDecimal(0.0), AOPInteger(-1)) to AOPDecimal(1.0),
            listOf(AOPDecimal(-1.0), AOPInteger(0)) to AOPDecimal(-1.0),
            listOf(AOPDouble(0.0), AOPInteger(1)) to AOPDouble(-1.0),
            listOf(AOPDouble(1.0), AOPInteger(0)) to AOPDouble(1.0),
            listOf(AOPDouble(0.0), AOPInteger(-1)) to AOPDouble(1.0),
            listOf(AOPDouble(-1.0), AOPInteger(0)) to AOPDouble(-1.0),
            listOf(AOPDouble(0.0), AOPDecimal(1.0)) to AOPDouble(-1.0),
            listOf(AOPDouble(1.0), AOPDecimal(0.0)) to AOPDouble(1.0),
            listOf(AOPDouble(0.0), AOPDecimal(-1.0)) to AOPDouble(1.0),
            listOf(AOPDouble(-1.0), AOPDecimal(0.0)) to AOPDouble(-1.0)
    ).map { (input, expected) ->
        val list = mutableListOf<String>()
        for (i in input)
            list.add("" + i.valueToString())
        DynamicTest.dynamicTest("calculate(${list} to ${expected.valueToString()})") {
            assertTrue(input.size == 2)
            val resultSet = ResultSet(ResultSetDictionary())
            val output = AOPSubtraction(input[0], input[1]).calculate(resultSet, resultSet.createResultRow())
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
            assertTrue(input.equals(input))
        }
    }

    fun testInvalidInput() {
        TODO("not implemented")
    }
}
