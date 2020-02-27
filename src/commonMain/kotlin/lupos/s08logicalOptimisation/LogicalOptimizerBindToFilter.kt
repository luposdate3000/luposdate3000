package lupos.s08logicalOptimisation
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.multiinput.AOPEQ
import lupos.s04arithmetikOperators.AOPBase

import lupos.s00misc.EOperatorID
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerBindToFilter(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.LogicalOptimizerBindToFilterID) {
    override val classname = "LogicalOptimizerBindToFilter"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPBind && (node.children[0].getProvidedVariableNames().contains(node.name.name))) {
            res = LOPFilter(AOPEQ(AOPVariable(node.name.name), node.children[1] as AOPBase), node.children[0])
            onChange()
        }
        res
    })
}
