package lupos.s10physicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s09physicalOperators.multiinput.POPJoinCartesianProduct
import lupos.s09physicalOperators.multiinput.POPJoinHashMap
import lupos.s09physicalOperators.multiinput.POPJoinMerge
import lupos.s09physicalOperators.multiinput.POPJoinMergeOptional
import lupos.s09physicalOperators.multiinput.POPJoinMergeSingleColumn
import lupos.s09physicalOperators.multiinput.POPJoinWithStore
import lupos.s09physicalOperators.multiinput.POPJoinWithStoreExists
import lupos.s09physicalOperators.parallel.POPMergeParallel
import lupos.s09physicalOperators.parallel.POPSplitParallel
import lupos.s09physicalOperators.POPBase
import lupos.s09physicalOperators.singleinput.POPProjection
import lupos.s15tripleStoreDistributed.TripleStoreIteratorGlobal

class PhysicalOptimizerJoinType(query: Query) : OptimizerBase(query, EOptimizerID.PhysicalOptimizerJoinTypeID) {
    override val classname = "PhysicalOptimizerJoinType"
    fun localGetProjected(node: OPBase, parent: OPBase?): List<String> {
        if (parent is LOPProjection) {
            return parent.getProvidedVariableNames()
        } else if (parent is POPProjection) {
            return parent.getProvidedVariableNamesInternal()
        } else if (node is POPBase) {
            return node.getProvidedVariableNamesInternal()
        } else {
            return node.getProvidedVariableNames()
        }
    }

    fun embedWithinPartitionContext(joinColumns: MutableList<String>, parent: OPBase?, childA: OPBase, childB: OPBase, create: (OPBase, OPBase) -> OPBase): OPBase {
        var a = childA
        var b = childB
        for (s in joinColumns) {
            a = POPSplitParallel(query, a.getProvidedVariableNames(), s, a)
            b = POPSplitParallel(query, b.getProvidedVariableNames(), s, b)
        }
        var c = create(a, b)
        for (s in joinColumns) {
            c = POPMergeParallel(query, c.getProvidedVariableNames(), s, c)
        }
        return c
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res = node
        val projectedVariables = localGetProjected(node, parent)
        if (node is LOPJoin) {
            val childA = node.children[0]
            val childB = node.children[1]
            val columns = LOPJoin.getColumns(childA.getProvidedVariableNames(), childB.getProvidedVariableNames())
            if (columns[0].size == 0) {
                res = POPJoinCartesianProduct(query, projectedVariables, childA, childB, false)
            } else {
                if (node.mySortPriority.size >= columns[0].size) {
                    if (projectedVariables.size == 1 && childA.getProvidedVariableNames().size == 1 && childB.getProvidedVariableNames().size == 1 && childA.getProvidedVariableNames()[0] == projectedVariables[0] && childB.getProvidedVariableNames()[0] == projectedVariables[0]) {
                        if (node.optional) {
                            res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
                        } else {
                            res = POPJoinMergeSingleColumn(query, projectedVariables, childA, childB, false)
                        }
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
                                if (node.optional) {
                                    res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
                                } else {
                                    res = POPJoinMerge(query, projectedVariables, childA, childB, false)
                                }
                            } else {
                                if (node.optional) {
                                    res = POPJoinMergeOptional(query, projectedVariables, childA, childB, true)
                                } else {
                                    res = POPJoinMerge(query, projectedVariables, childB, childA, false)
                                }
                            }
                        }
                    }
                }
                if (res is LOPJoin) {
                    if (node.optional) {
                        res = embedWithinPartitionContext(columns[0], parent, childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, true) })
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childA is LOPTriple) {
                        res = POPJoinWithStoreExists(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childB is LOPTriple) {
                        res = POPJoinWithStoreExists(query, projectedVariables, childA, childB, false)
                    } else if (node.partOfAskQuery && childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
                    } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = embedWithinPartitionContext(columns[0], parent, childB, childA, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) })
                    } else {
                        res = embedWithinPartitionContext(columns[0], parent, childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) })
                    }
                }
            }
            SanityCheck { res.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName }) }
            res.mySortPriority = node.mySortPriority
            res.sortPriorities = node.sortPriorities
            onChange()
        }
        return res
    }
}
