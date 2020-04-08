package lupos.s10physicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
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
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
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
import lupos.s15tripleStoreDistributed.*
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class PhysicalOptimizerJoinType(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerJoinTypeID) {
    override val classname = "PhysicalOptimizerJoinType"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
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
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            var variablesJ = mutableListOf<String>()
            var variablesA = mutableListOf<String>()
            var variablesB = mutableListOf<String>()
            variablesA.addAll(childA.getProvidedVariableNames())
            variablesB.addAll(childB.getProvidedVariableNames())
            for (name in childA.getProvidedVariableNames()) {
                if (variablesB.contains(name)) {
                    variablesA.remove(name)
                    variablesB.remove(name)
                    variablesJ.add(name)
                }
            }
            if (variablesJ.size == 0) {
                /*cartesian product*/
                res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
            } else if (node.optional) {
                if (childA is TripleStoreIteratorGlobal || childA is LOPTriple) {
                    res = POPJoinHashMap(query, projectedVariables, childB, childA, true)
                } else {
                    res = POPJoinHashMap(query, projectedVariables, childA, childB, true)
                }
            } else {
                if (childA is LOPTriple && variablesA.size>0) {
                    res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
                } else if (childB is LOPTriple&&variablesB.size>0) {
                    res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
                } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple) {
                    res = POPJoinHashMap(query, projectedVariables, childB, childA, false)
                } else {
                    res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
                }
            }
        }
/*return*/ res
})
}
