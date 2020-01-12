package lupos.s3logicalOptimisation

import lupos.s2buildOperatorGraph.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.singleinput.*
import lupos.s2buildOperatorGraph.singleinput.modifiers.*
import lupos.s2buildOperatorGraph.multiinput.*
import lupos.s2buildOperatorGraph.data.*
import lupos.s3logicalOptimisation.OptimizerVisitorLOP

class LogicalOptimizer() : OptimizerVisitorLOP() {
    override fun visit(node: LOPPrefix): OPBase {
        return optimize(node.child)
    }

    override fun visit(node: LOPNOOP): OPBase {
        return optimize(node.child)
    }

}
