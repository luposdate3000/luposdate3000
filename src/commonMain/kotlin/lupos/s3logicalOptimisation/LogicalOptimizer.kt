package lupos.s3logicalOptimisation

import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.LOPNOOP
import lupos.s2buildOperatorGraph.singleinput.modifiers.LOPPrefix
import lupos.s3logicalOptimisation.OptimizerVisitorLOP

class LogicalOptimizer() : OptimizerVisitorLOP() {
    override fun visit(node: LOPPrefix): OPBase {
        return optimize(node.child)
    }

    override fun visit(node: LOPNOOP): OPBase {
        return optimize(node.child)
    }

}
