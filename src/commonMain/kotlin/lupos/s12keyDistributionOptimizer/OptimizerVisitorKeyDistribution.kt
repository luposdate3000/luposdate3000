package lupos.s12keyDistributionOptimizer

import lupos.s03buildOperatorGraph.data.LOPVariable
import lupos.s03buildOperatorGraph.OPBase
import lupos.s05logicalOptimisation.OptimizerVisitorLOP
import lupos.s07physicalOperators.POPBase
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s08tripleStore.POPTripleStoreIteratorBase
import lupos.s09physicalOptimisation.*
import lupos.s11p2p.*


abstract class OptimizerVisitorKeyDistribution(transactionID: Long) : OptimizerVisitorPOP(transactionID) {

    open fun visit(node: POPServiceIRI): OPBase {
        return POPServiceIRI(transactionID, node.serverName, node.silent, optimize(node.originalConstraint))
    }

    override open fun optimize(node: OPBase): OPBase {
        when (node) {
            is POPServiceIRI -> return visit(node)
        }
        return super.optimize(node)
    }
}
