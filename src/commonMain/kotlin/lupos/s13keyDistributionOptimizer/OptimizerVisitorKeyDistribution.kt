package lupos.s13keyDistributionOptimizer
import lupos.s03resultRepresentation.ResultSetDictionary

import lupos.s04logicalOperators.OPBase
import lupos.s10physicalOptimisation.OptimizerVisitorPOP
import lupos.s12p2p.POPServiceIRI


abstract class OptimizerVisitorKeyDistribution(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerVisitorPOP(transactionID, dictionary) {

    open fun visit(node: POPServiceIRI): OPBase {
        return POPServiceIRI(dictionary, transactionID, node.serverName, node.silent, optimize(node.originalConstraint))
    }

    override open fun optimize(node: OPBase): OPBase {
        when (node) {
            is POPServiceIRI -> return visit(node)
        }
        return super.optimize(node)
    }
}
