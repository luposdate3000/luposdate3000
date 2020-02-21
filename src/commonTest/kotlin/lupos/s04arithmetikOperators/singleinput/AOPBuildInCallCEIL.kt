package lupos.s04arithmetikOperators.singleinput

import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
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


class AOPBuildInCallCEILTest {

    @TestFactory
    fun testCalculate() = helperTest.forEachNumeric { x, ad, at ->
        val expected = helperTest.toConstant(ceil(ad), at)
        DynamicTest.dynamicTest("calculate(${x.valueToString()} to ${expected.valueToString()})") {
            val resultSet = ResultSet(ResultSetDictionary())
            val output = AOPBuildInCallCEIL(x).calculate(resultSet, resultSet.createResultRow())
            println("$expected $output")
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
        }
    }

    fun testInvalidInput() {
        TODO("not implemented")
    }
}
