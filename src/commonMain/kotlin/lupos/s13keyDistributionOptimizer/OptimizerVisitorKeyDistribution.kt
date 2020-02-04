package lupos.s12keyDistributionOptimizer
import lupos.s04logicalOperators.data.LOPVariable
import lupos.s04logicalOperators.OPBase
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s08logicalOptimisation.OptimizerVisitorLOP
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilterExact
import lupos.s09physicalOptimisation.OptimizerVisitorPOP
import lupos.s11p2p.POPServiceIRI



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
