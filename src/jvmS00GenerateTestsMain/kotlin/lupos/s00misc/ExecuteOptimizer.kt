package lupos.s00misc

import lupos.s04logicalOperators.*
import lupos.s08logicalOptimisation.*


object ExecuteOptimizer {
    val enabledOptimizers = mutableMapOf<String, Boolean>()
    inline fun invoke(crossinline optimizer: () -> OptimizerBase, crossinline node: () -> OPBase, crossinline action: () -> OPBase): OPBase {
        if (optimizer().optional) {
            val tmp = enabledOptimizers[optimizer().classname]
            if (tmp == null)
                enabledOptimizers[optimizer().classname] = false
            if (!enabledOptimizers[optimizer().classname]!!)
                return node()
        }
        return action()
    }
}
