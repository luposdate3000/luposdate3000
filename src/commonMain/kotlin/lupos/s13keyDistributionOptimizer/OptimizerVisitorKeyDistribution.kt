package lupos.s13keyDistributionOptimizer

import lupos.s03resultRepresentation.*
import lupos.s04logicalOperators.noinput.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s08logicalOptimisation.OptimizerVisitorLOP
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPRename
import lupos.s10physicalOptimisation.OptimizerVisitorPOP
import lupos.s12p2p.POPServiceIRI
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer


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
