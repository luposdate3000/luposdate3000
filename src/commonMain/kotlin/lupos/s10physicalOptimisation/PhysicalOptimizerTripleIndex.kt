package lupos.s10physicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class PhysicalOptimizerTripleIndex(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerTripleIndexID) {
    override val classname = "PhysicalOptimizerTripleIndex"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPTriple) {
            onChange()
            val store = DistributedTripleStore.getNamedGraph(query, node.graph)
            var count = 0
            for (n in node.children) {
                if (n is AOPConstant) {
                    count++
                }
            }
            require(count <= 3)
            val params = Array<AOPBase>(3) { node.children[it] as AOPBase }
            when (count) {
                0 -> {
                    res = store.getIterator(params, EIndexPattern.SPO)
                }
                1 -> {
                    if (node.children[0] is AOPConstant) {
                        res = store.getIterator(params, EIndexPattern.S)
                    } else if (node.children[1] is AOPConstant) {
                        res = store.getIterator(params, EIndexPattern.P)
                    } else {
                        SanityCheck.check({ node.children[2] is AOPConstant })
                        res = store.getIterator(params, EIndexPattern.O)
                    }
                }
                2 -> {
                    SanityCheck.checkEQ({ count }, { 2 })
                    if (node.children[0] !is AOPConstant) {
                        res = store.getIterator(params, EIndexPattern.PO)
                    } else if (node.children[1] !is AOPConstant) {
                        res = store.getIterator(params, EIndexPattern.SO)
                    } else {
                        SanityCheck.check({ node.children[2] !is AOPConstant })
                        res = store.getIterator(params, EIndexPattern.SP)
                    }
                }
                else -> {
                    require(count == 3)
                    params[1] = AOPVariable(query, "generated${node.uuid}")
                    val tmp = store.getIterator(params, EIndexPattern.SO)
                    res = POPFilter(query, AOPEQ(query, node.children[1] as AOPBase, params[1]), tmp)
                }
            }
        }
        res
    })
}
