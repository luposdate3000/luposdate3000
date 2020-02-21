package lupos.s08logicalOptimisation

import lupos.s00misc.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerFilterDown(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        if (node is LOPFilter) {
            val c = node.children[0]
            if (c.children.size == 1) {
                val cc = c.children[0]
                println("a:: ${classNameToString(cc)} ${cc.getProvidedVariableNames()}")
                println("b:: ${classNameToString(node)} ${node.getRequiredVariableNames()}")
                if (cc.getProvidedVariableNames().containsAll(node.getRequiredVariableNames())) {
                    c.children[0] = node
                    node.children[0] = cc
                    onChange()
                    return c
                }
            }
        }
        return node
    }
}
