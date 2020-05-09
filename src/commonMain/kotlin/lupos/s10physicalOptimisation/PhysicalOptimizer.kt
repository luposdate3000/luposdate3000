package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerCompoundBase

class PhysicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.PhysicalOptimizerID) {
    override val classname = "PhysicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            arrayOf(
                    PhysicalOptimizerJoinType(query),//
                    PhysicalOptimizerTripleIndex(query),//
                    PhysicalOptimizerNaive(query)
            )
    )
}
