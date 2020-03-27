package lupos.s00misc

import kotlin.jvm.JvmField

import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator
import lupos.s08logicalOptimisation.OptimizerBase

object ExecuteOptimizer {
    inline fun invoke(crossinline optimizer: () -> OptimizerBase, crossinline node: () -> OPBase, crossinline action: () -> OPBase) = action()
}
