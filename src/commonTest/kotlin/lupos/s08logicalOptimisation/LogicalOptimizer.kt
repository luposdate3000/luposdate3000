package lupos.s08logicalOptimisation

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class LogicalOptimizerTest {
    fun helper(input: OPBase, target: OPBase, transactionID: Long, dictionary: ResultSetDictionary) {
        val output = LogicalOptimizer(transactionID, dictionary).optimizeCall(input)
        println(target.toXMLElement().toPrettyString())
        println(output.toXMLElement().toPrettyString())
        assertTrue(target.equals(output))
    }

    @Test
    fun removeAllPrefix() {
        helper(
                LOPPrefix("a", "b"),
                OPNothing(),
                0,
                ResultSetDictionary()
        )
    }
}
