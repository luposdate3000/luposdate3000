package lupos.s04arithmetikOperators

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


object helperTest {
    fun toConstant(d: Double, i: Int): AOPConstant {
        when (i) {
            0 -> return AOPInteger(d.toInt())
            1 -> return AOPDecimal(d)
            else -> return AOPDouble(d)
        }
    }

    fun max(a: Int, b: Int): Int {
        return if (a > b)
            a
        else
            b
    }

    fun min(a: Int, b: Int): Int {
        return if (a < b)
            a
        else
            b
    }

    fun forEachNumericPair(action: (a: AOPConstant, b: AOPConstant, ad: Double, bd: Double, at: Int, bt: Int) -> DynamicTest): List<DynamicTest> {
        val res = mutableListOf<DynamicTest>()
        listOf(
                arrayOf<Double>(0.0, 1.0),
                arrayOf<Double>(0.0, -1.0),
                arrayOf<Double>(2.0, 3.0),
                arrayOf<Double>(2.0, -4.0),
                arrayOf<Double>(1.0, 1.0),
                arrayOf<Double>(-1.0, -1.0)
        ).forEach { input ->
            for (i in 0 until 2) {
                for (a in 0 until 3) {
                    val x = toConstant(input[i], a)
                    for (b in 0 until 3) {
                        val y = toConstant(input[1 - i], b)
                        res.add(action(x, y, input[i], input[1 - i], a, b))
                    }
                }
            }
        }
        return res
    }

    fun forEachNumeric(action: (a: AOPConstant, ad: Double, at: Int) -> DynamicTest): List<DynamicTest> {
        val res = mutableListOf<DynamicTest>()
        listOf(
                1.0,
                -1.0,
                3.0,
                -4.0,
                2.0,
                0.0
        ).forEach { input ->
            for (a in 0 until 3) {
                val x = toConstant(input, a)
                res.add(action(x, input, a))
            }
        }
        return res
    }

    fun forEachNumericInput(expectAction: (a: Double, b: Double) -> Double, operatorAction: (a: AOPConstant, b: AOPConstant) -> AOPBase) = forEachNumericPair { x, y, av, bv, a, b ->
        val expected = toConstant(expectAction(av, bv), max(a, b))
        val list = mutableListOf<String>()
        list.add("" + x.valueToString())
        list.add("" + y.valueToString())
        val s = "calculate(${list} to ${expected.valueToString()})"
        DynamicTest.dynamicTest(s) {
            val resultSet = ResultSet(ResultSetDictionary())
            val output = operatorAction(x, y).calculate(resultSet, resultSet.createResultRow())
            if (!expected.equals(output))
                println("$expected -- $output")
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
        }
    }

    fun forEachNumericBooleanInput(expectAction: (a: Double, b: Double) -> Boolean, operatorAction: (a: AOPConstant, b: AOPConstant) -> AOPBase) = forEachNumericPair { x, y, av, bv, a, b ->
        val expected = AOPBoolean(expectAction(av, bv))
        val list = mutableListOf<String>()
        list.add("" + x.valueToString())
        list.add("" + y.valueToString())
        DynamicTest.dynamicTest("calculate(${list} to ${expected.valueToString()})") {
            val resultSet = ResultSet(ResultSetDictionary())
            val output = operatorAction(x, y).calculate(resultSet, resultSet.createResultRow())
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
        }
    }

    fun forEachNumericDataBooleanInput(expectAction: (a: AOPConstant, b: AOPConstant) -> Boolean, operatorAction: (a: AOPConstant, b: AOPConstant) -> AOPBase) = forEachNumericPair { x, y, av, bv, a, b ->
        val expected = AOPBoolean(expectAction(x, y))
        val list = mutableListOf<String>()
        list.add("" + x.valueToString())
        list.add("" + y.valueToString())
        val s = "calculate(${list} to ${expected.valueToString()})"
        DynamicTest.dynamicTest(s) {
            val resultSet = ResultSet(ResultSetDictionary())
            val output = operatorAction(x, y).calculate(resultSet, resultSet.createResultRow())
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
        }
    }

    fun forEachBooleanInput(expectAction: (a: Boolean, b: Boolean) -> Boolean, operatorAction: (a: AOPConstant, b: AOPConstant) -> AOPBase): List<DynamicTest> {
        val res = mutableListOf<DynamicTest>()
        for (a in 0 until 2) {
            val x = AOPBoolean(a == 0)
            for (b in 0 until 2) {
                val y = AOPBoolean(b == 0)
                val expected = AOPBoolean(expectAction(a == 0, b == 0))
                val list = mutableListOf<String>()
                list.add("" + x.valueToString())
                list.add("" + y.valueToString())
                val s = "calculate(${list} to ${expected.valueToString()})"
                res.add(DynamicTest.dynamicTest(s) {
                    val resultSet = ResultSet(ResultSetDictionary())
                    val output = operatorAction(x, y).calculate(resultSet, resultSet.createResultRow())
                    assertTrue(expected.equals(output))
                    assertTrue(output.equals(output))
                })
            }
        }
        return res
    }

}
