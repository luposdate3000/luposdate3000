package lupos.s05logicalOptimisation

import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s05logicalOptimisation.OptimizerVisitorLOP


class LogicalOptimizer() : OptimizerVisitorLOP() {
    override fun visit(node: LOPPrefix): OPBase {
        return optimize(node.child)
    }

    override fun visit(node: LOPNOOP): OPBase {
        return optimize(node.child)
    }

}
