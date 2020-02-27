package lupos.s00misc
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase
import lupos.s08logicalOptimisation.OptimizerBase



object ExecuteOptimizer {
    inline fun invoke(crossinline optimizer: () -> OptimizerBase, crossinline node: () -> OPBase, crossinline action: () -> OPBase) = action()
}
