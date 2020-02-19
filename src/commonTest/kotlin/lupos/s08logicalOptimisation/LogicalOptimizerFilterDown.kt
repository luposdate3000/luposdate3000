package lupos.s08logicalOptimisation

import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class LogicalOptimizerFilterDownTest {
    fun helper(input: OPBase, target: OPBase, transactionID: Long, dictionary: ResultSetDictionary) {
        val output = LogicalOptimizerFilterDown(transactionID, dictionary).optimizeCall(input)
        println(target.toXMLElement().toPrettyString())
        println(output.toXMLElement().toPrettyString())
        assertTrue(target.equals(output))
    }

    @Test
    fun test1() {
        val ast = ASTVar("s")
        helper(
                LOPFilter(LOPExpression(ast), LOPTriple(LOPVariable("s"), LOPVariable("p"), LOPVariable("o"), "")),
                LOPFilter(LOPExpression(ast), LOPTriple(LOPVariable("s"), LOPVariable("p"), LOPVariable("o"), "")),
                0,
                ResultSetDictionary()
        )
    }
}
