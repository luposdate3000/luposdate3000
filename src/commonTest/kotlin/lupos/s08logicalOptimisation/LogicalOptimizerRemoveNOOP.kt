package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPProjection
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


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
                LOPProjection(mutableListOf(AOPVariable("a")), LOPNOOP()),
                LOPProjection(mutableListOf(AOPVariable("a")), OPNothing()),
                0,
                ResultSetDictionary()
        )
    }
}
