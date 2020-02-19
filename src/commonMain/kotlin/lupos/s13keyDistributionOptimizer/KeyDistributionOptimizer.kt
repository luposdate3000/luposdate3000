package lupos.s13keyDistributionOptimizer

import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.POPServiceIRI


class KeyDistributionOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerBase(transactionID, dictionary) {
    override fun optimize(node: OPBase, parent: OPBase?): OPBase {
        when (node) {
            is LOPServiceIRI -> return POPServiceIRI(dictionary, transactionID, node.name, node.silent, optimizeInternal(node.children[0], null) as POPBase)
            else -> return node
        }
    }
}
