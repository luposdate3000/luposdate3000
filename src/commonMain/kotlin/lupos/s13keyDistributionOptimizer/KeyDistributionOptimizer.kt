package lupos.s13keyDistributionOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPServiceIRI
import lupos.s09physicalOperators.POPBase
import lupos.s12p2p.POPServiceIRI
import lupos.s13keyDistributionOptimizer.OptimizerVisitorKeyDistribution


class KeyDistributionOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerVisitorKeyDistribution(transactionID, dictionary) {

    override fun visit(node: LOPServiceIRI): OPBase {
        return POPServiceIRI(dictionary, transactionID, node.name, node.silent, optimize(node.children[0]) as POPBase)
    }
}
