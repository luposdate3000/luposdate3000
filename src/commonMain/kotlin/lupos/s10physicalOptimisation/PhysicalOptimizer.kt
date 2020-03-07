package lupos.s10physicalOptimisation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s08logicalOptimisation.OptimizerCompoundBase


class PhysicalOptimizer(transactionID: Long, dictionary: ResultSetDictionary) : OptimizerCompoundBase(transactionID, dictionary, EOptimizerID.PhysicalOptimizerID) {
    override val classname = "PhysicalOptimizer"
    override val childrenOptimizers = arrayOf(//
            PhysicalOptimizerTripleIndex(transactionID, dictionary),//
            PhysicalOptimizerNaive(transactionID, dictionary))
}
