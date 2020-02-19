package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class LogicalOptimizerRemoveNOOPTest {
    fun helper(input: OPBase, target: OPBase, transactionID: Long, dictionary: ResultSetDictionary) {
        val output = LogicalOptimizerRemoveNOOP(transactionID, dictionary).optimizeCall(input)
        println(target.toXMLElement().toPrettyString()) 
        println(output.toXMLElement().toPrettyString())
        assertTrue(target.equals(output))
    }

    @Test
    fun test1() {
        helper(
                LOPNOOP(),
                OPNothing(),
                0,
                ResultSetDictionary()
        )
    }
    @Test
    fun test2() {
        helper(
                LOPNOOP(LOPNOOP()),
                OPNothing(),
                0,
                ResultSetDictionary()
        )
    }
    @Test
    fun test3() {
        helper(
                LOPProjection(mutableListOf(LOPVariable("a")),LOPNOOP()),
                LOPProjection(mutableListOf(LOPVariable("a")),OPNothing()),
                0,
                ResultSetDictionary()
        )
    }
}
