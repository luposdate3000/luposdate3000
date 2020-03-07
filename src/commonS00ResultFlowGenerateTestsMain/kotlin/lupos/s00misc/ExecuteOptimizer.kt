package lupos.s00misc
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.OPBase
import lupos.s08logicalOptimisation.OptimizerBase


object ExecuteOptimizer {
    @JvmField
    val enabledOptimizers = ThreadSafeMutableMap<EOptimizerID, Boolean>()

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
