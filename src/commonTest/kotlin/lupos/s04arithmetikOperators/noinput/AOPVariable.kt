package lupos.s04arithmetikOperators.noinput

import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.ResultSetDictionary
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


class AOPVariableTest {
    @TestFactory
    fun test1() = listOf(
            AOPInteger(0) to AOPInteger(0),
            AOPInteger(-1) to AOPInteger(-1),
            AOPInteger(1) to AOPInteger(1)
    ).map { (input, expected) ->
        val tmp = input.valueToString()
        DynamicTest.dynamicTest("$tmp") {
            val output = AOPVariable.calculate(tmp)
            assertTrue(input.equals(output))
        }
    }
}
