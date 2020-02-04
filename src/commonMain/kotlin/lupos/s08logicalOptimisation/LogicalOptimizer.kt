package lupos.s05logicalOptimisation
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.modifiers.LOPPrefix
import lupos.s05logicalOptimisation.OptimizerVisitorLOP



class LogicalOptimizer(transactionID: Long) : OptimizerVisitorLOP(transactionID) {
    override fun visit(node: LOPPrefix): OPBase {
        return optimize(node.child)
    }

    override fun visit(node: LOPNOOP): OPBase {
        return optimize(node.child)
    }

}
