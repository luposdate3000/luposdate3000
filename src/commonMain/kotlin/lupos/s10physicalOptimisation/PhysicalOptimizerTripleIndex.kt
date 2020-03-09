package lupos.s10physicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore


class PhysicalOptimizerTripleIndex(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerTripleIndexID) {
    override val classname = "PhysicalOptimizerTripleIndex"

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPTriple) {
            onChange()
            val store: DistributedGraph
            if (node.graph == null)
                store = DistributedTripleStore.getDefaultGraph(query)
            else
                store = DistributedTripleStore.getNamedGraph(query, node.graph)
            val idx: EIndexPattern
            var count = 0
            for (n in node.children)
                if (n is AOPConstant)
                    count++
            if (count == 0 || count == 3)
                idx = EIndexPattern.SPO
            else if (count == 1) {
                if (node.children[0] is AOPConstant)
                    idx = EIndexPattern.S
                else if (node.children[1] is AOPConstant)
                    idx = EIndexPattern.P
                else {
                    SanityCheck.check({ node.children[2] is AOPConstant })
                    idx = EIndexPattern.O
                }
            } else {
                SanityCheck.checkEQ({ count }, { 2 })
                if (node.children[0] !is AOPConstant)
                    idx = EIndexPattern.PO
                else if (node.children[1] !is AOPConstant)
                    idx = EIndexPattern.SO
                else {
                    SanityCheck.check({ node.children[2] !is AOPConstant })
                    idx = EIndexPattern.SP
                }
            }
            res = store.getIterator(Array<AOPBase>(3) { node.children[it] as AOPBase }, idx)
        }
        res
    })
}
