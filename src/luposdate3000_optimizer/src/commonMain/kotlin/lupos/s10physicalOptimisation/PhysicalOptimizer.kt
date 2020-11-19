package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s08logicalOptimisation.OptimizerCompoundBase

class PhysicalOptimizer(query: Query) : OptimizerCompoundBase(query, EOptimizerID.PhysicalOptimizerID) {
    override val classname: String = "PhysicalOptimizer"
    override val childrenOptimizers: Array<Array<OptimizerBase>> = arrayOf(//
            arrayOf(
                    PhysicalOptimizerJoinType(query),//
            ),
            arrayOf(
                    PhysicalOptimizerTripleIndex(query),//
            ),
            arrayOf(
                    PhysicalOptimizerNaive(query),//
            ),
            arrayOf(
                    PhysicalOptimizerPartition1(query),//
                    PhysicalOptimizerPartition2(query),//
                    PhysicalOptimizerPartition3(query),//
            ),
            arrayOf(
                    PhysicalOptimizerPartition4(query),//
            ),
            arrayOf(
                    PhysicalOptimizerPartition5(query),//
            ),
            arrayOf(
                    PhysicalOptimizerDebug(query)
            )
    )
}
