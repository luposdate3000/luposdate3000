package lupos.s10physicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPJoinWithStoreExists
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.modifiers.POPReduced
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal

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
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
            if (columns[0].size == 0) {
                /*cartesian product*/
                res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
            } else {
                if (node.optional) {
                    res = POPJoinHashMap(query, projectedVariables, childA, childB, true)
                } else {
                    if (node.mySortPriority.size >= columns[0].size) {
                        if (projectedVariables.size == 1 && childA.getProvidedVariableNames().size == 1 && childB.getProvidedVariableNames().size == 1 && childA.getProvidedVariableNames()[0] == projectedVariables[0] && childB.getProvidedVariableNames()[0] == projectedVariables[0]) {
                            res = POPJoinMergeSingleColumn(query, projectedVariables, childA, childB, false)
                        } else {
                            var flag = true
                            for (i in 0 until columns[0].size) {
                                if ((childA.mySortPriority.size > i && childA.mySortPriority[i] != node.mySortPriority[i]) || (childB.mySortPriority.size > i && childB.mySortPriority[i] != node.mySortPriority[i])) {
                                    flag = false
                                    break
                                }
                            }
                            if (flag) {
                                if (childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                                    res = POPJoinMerge(query, projectedVariables, childA, childB, false)
                                } else {
                                    res = POPJoinMerge(query, projectedVariables, childB, childA, false)
                                }
                            }
                        }
                    }
                    if (res is LOPJoin) {
                        if (node.partOfAskQuery&&projectedVariables.size == 0 && childA is LOPTriple) {
                            res = POPJoinWithStoreExists(query, projectedVariables, childB, childA, false)
                        } else if (node.partOfAskQuery&&projectedVariables.size == 0 && childB is LOPTriple) {
                            res = POPJoinWithStoreExists(query, projectedVariables, childA, childB, false)
                        } else if (node.partOfAskQuery&&childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                            res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
                        } else if (node.partOfAskQuery&&childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                            res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
                        } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                            res = POPJoinHashMap(query, projectedVariables, childB, childA, false)
                        } else {
                            res = POPJoinHashMap(query, projectedVariables, childA, childB, false)
                        }
                    }
                }
            }
            if (node.onlyExistenceRequired) {
                SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
                res.mySortPriority = node.mySortPriority
                res.sortPriorities = node.sortPriorities
                res = POPReduced(query, projectedVariables, res)
            }
            SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
            res.mySortPriority = node.mySortPriority
            res.sortPriorities = node.sortPriorities
            onChange()
        }
/*return*/ res
    })
}
