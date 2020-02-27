package lupos.s00misc
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s04logicalOperators.OPBase

import lupos.s00misc.EOperatorID


object ExecuteOptimizer {
    val enabledOptimizers = mutableMapOf<EOptimizerID, Boolean>()
    inline fun invoke(crossinline optimizer: () -> OptimizerBase, crossinline node: () -> OPBase, crossinline action: () -> OPBase): OPBase {
        if (optimizer().optimizerID.optional) {
            val tmp = enabledOptimizers[optimizer().optimizerID]
            if (tmp == null)
                enabledOptimizers[optimizer().optimizerID] = false
            if (!enabledOptimizers[optimizer().optimizerID]!!)
                return node()
        }
        return action()
    }
}
