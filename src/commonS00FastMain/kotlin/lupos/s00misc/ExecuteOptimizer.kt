package lupos.s00misc
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s04logicalOperators.OPBase

import lupos.s00misc.EOperatorID


object ExecuteOptimizer {
    inline fun invoke(crossinline optimizer: () -> OptimizerBase, crossinline node: () -> OPBase, crossinline action: () -> OPBase) = action()
}
