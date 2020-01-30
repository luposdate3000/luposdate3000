package lupos.s12keyDistributionOptimizer

import lupos.s08tripleStore.POPTripleStoreIteratorBase
import lupos.s09physicalOptimisation.*
import lupos.s07physicalOperators.singleinput.POPRename
import lupos.s07physicalOperators.singleinput.POPProjection
import lupos.s07physicalOperators.singleinput.POPFilterExact
import lupos.s07physicalOperators.singleinput.POPBind
import lupos.s07physicalOperators.POPBase
import lupos.s05logicalOptimisation.OptimizerVisitorLOP
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.data.LOPVariable


abstract class OptimizerVisitorKeyDistribution() : OptimizerVisitorPOP() {

    override open fun optimize(node: OPBase): OPBase {
        return super.optimize(node)
    }
}
