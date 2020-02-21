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


class AOPAndTest {

    @TestFactory
    fun testCalculate(): List<DynamicTest> {
        val res = mutableListOf<DynamicTest>()
        for (a in 0 until 2) {
            val x = AOPBooleanLiteral(a == 0)
            for (b in 0 until 2) {
                val y = AOPBooleanLiteral(b == 0)
                val expected = AOPBooleanLiteral(x.value && y.value)
                val list = mutableListOf<String>()
                list.add("" + x.valueToString())
                list.add("" + y.valueToString())
                val s = "calculate(${list} to ${expected.valueToString()})"
                res.add(DynamicTest.dynamicTest(s) {
                    val resultSet = ResultSet(ResultSetDictionary())
                    val output = AOPAnd(x, y).calculate(resultSet, resultSet.createResultRow())
                    assertTrue(expected.equals(output))
                    assertTrue(output.equals(output))
                })
            }
        }
        return res
    }

    fun testInvalidInput() {
        TODO("not implemented")
    }
}
