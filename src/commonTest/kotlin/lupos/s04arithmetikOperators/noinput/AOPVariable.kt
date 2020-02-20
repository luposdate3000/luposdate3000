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
val timeNow=AOPDateTime()
    @TestFactory
    fun test1() = listOf(
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
AOPSimpleLiteral("\"","a") to AOPSimpleLiteral("\"","a"),
AOPTypedLiteral("\"","a","b") to AOPTypedLiteral("\"","a","b"),
AOPLanguageTaggedLiteral("\"","a","b") to AOPLanguageTaggedLiteral("\"","a","b"),
timeNow to timeNow,
	AOPUndef() to AOPUndef()
    ).map { (input, expected) ->
        val tmp = input.valueToString()
        DynamicTest.dynamicTest("$tmp") {
            val output = AOPVariable.calculate(tmp)
            assertTrue(expected.equals(output))
            assertTrue(output.equals(output))
            assertTrue(input.equals(input))
        }
    }
}
