package lupos.s13keyDistributionOptimizer
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.POPServiceIRI


class KeyDistributionOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary, EOptimizerID.KeyDistributionOptimizerID) {
    override val classname = "KeyDistributionOptimizer"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPServiceIRI) {
            onChange()
            res = POPServiceIRI(dictionary, transactionID, node.name, node.silent, optimizeInternal(node.children[0], null, onChange) as POPBase)
        }
        res
    })
}
