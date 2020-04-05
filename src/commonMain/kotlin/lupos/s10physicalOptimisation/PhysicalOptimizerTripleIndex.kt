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
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.LOPGraphOperation
import lupos.s04logicalOperators.noinput.LOPModifyData
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.noinput.LOPValues
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPModify
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPDistinct
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPUnion
import lupos.s09physicalOperators.noinput.POPEmptyRow
import lupos.s09physicalOperators.noinput.POPGraphOperation
import lupos.s09physicalOperators.noinput.POPModifyData
import lupos.s09physicalOperators.noinput.POPValues
import lupos.s09physicalOperators.singleinput.modifiers.POPDistinct
import lupos.s09physicalOperators.singleinput.modifiers.POPLimit
import lupos.s09physicalOperators.singleinput.modifiers.POPOffset
import lupos.s09physicalOperators.singleinput.POPBind
import lupos.s09physicalOperators.singleinput.POPFilter
import lupos.s09physicalOperators.singleinput.POPGroup
import lupos.s09physicalOperators.singleinput.POPMakeBooleanResult
import lupos.s09physicalOperators.singleinput.POPModify
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s09physicalOperators.singleinput.POPSort
import lupos.s15tripleStoreDistributed.DistributedGraph
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class PhysicalOptimizerTripleIndex(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerTripleIndexID) {
    override val classname = "PhysicalOptimizerTripleIndex"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is LOPTriple) {
            val projectedVariables: List<String>
            if (parent is LOPProjection) {
                projectedVariables = parent.getProvidedVariableNames()
            } else if (parent is POPProjection) {
                projectedVariables = parent.getProvidedVariableNamesInternal()
            } else if (node is POPBase) {
                projectedVariables = node.getProvidedVariableNamesInternal()
            } else {
                projectedVariables = node.getProvidedVariableNames()
            }
            onChange()
            val store = DistributedTripleStore.getNamedGraph(query, node.graph)
            var count = 0
            for (n in node.children) {
                if (n is AOPConstant) {
                    count++
                }
            }
            require(count <= 3)
            val params = Array<AOPBase>(3) {
                var res2 = node.children[it] as AOPBase
                if (res2 is AOPVariable) {
                    if (!projectedVariables.contains(res2.name)) {
                        res2 = AOPVariable(query, "_")
                    }
                }
/*return*/res2
            }
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
                    res = POPFilter(query, projectedVariables, AOPEQ(query, node.children[1] as AOPBase, params[1]), tmp)
                }
            }
        }
/*return*/res
    })
}
