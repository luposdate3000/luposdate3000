package lupos.s10physicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.Partition
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
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPSplitPartition
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

    fun embedWithinPartitionContext(joinColumns: MutableList<String>, childA: OPBase, childB: OPBase, create: (OPBase, OPBase) -> OPBase): OPBase {
        if (USE_PARTITIONS && Partition.k > 0) {
            var a = childA
            var b = childB
            for (s in joinColumns) {
                a = POPSplitPartition(query, a.getProvidedVariableNames(), s, a)
                b = POPSplitPartition(query, b.getProvidedVariableNames(), s, b)
            }
            var c = create(a, b)
            if (c.getProvidedVariableNames().size == 0) {
                for (s in joinColumns) {
                    c = POPMergePartitionCount(query, c.getProvidedVariableNames(), s, c)
                }
            } else {
                for (s in joinColumns) {
                    c = POPMergePartition(query, c.getProvidedVariableNames(), s, c)
                }
            }
            return c
        } else {
            return create(childA, childB)
        }
    }

    override suspend fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
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
                            res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) })
                        } else {
                            res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeSingleColumn(query, projectedVariables, a, b, false) })
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
                                    res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) })
                                } else {
                                    res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMerge(query, projectedVariables, a, b, false) })
                                }
                            } else {
                                if (node.optional) {
                                    res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinMergeOptional(query, projectedVariables, a, b, true) })
                                } else {
                                    res = embedWithinPartitionContext(columns[0], childB, childA, { a, b -> POPJoinMerge(query, projectedVariables, a, b, false) })
                                }
                            }
                        }
                    }
                }
                if (res is LOPJoin) {
                    if (node.optional) {
                        res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, true) })
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childA is LOPTriple) {
                        res = POPJoinWithStoreExists(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && projectedVariables.size == 0 && childB is LOPTriple) {
                        res = POPJoinWithStoreExists(query, projectedVariables, childA, childB, false)
                    } else if (node.partOfAskQuery && childA is LOPTriple && columns[1].size > 0 && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = POPJoinWithStore(query, projectedVariables, childB, childA, false)
                    } else if (node.partOfAskQuery && childB is LOPTriple && columns[2].size > 0 && childA.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = POPJoinWithStore(query, projectedVariables, childA, childB, false)
                    } else if (childA is TripleStoreIteratorGlobal || childA is LOPTriple && childB.getProvidedVariableNames().containsAll(node.mySortPriority.map { it.variableName })) {
                        res = embedWithinPartitionContext(columns[0], childB, childA, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) })
                    } else {
                        res = embedWithinPartitionContext(columns[0], childA, childB, { a, b -> POPJoinHashMap(query, projectedVariables, a, b, false) })
                    }
                }
            }
SanityCheck{
val tmp=node.mySortPriority.map { it.variableName }
            SanityCheck.check { (!projectedVariables.containsAll(tmp))||(projectedVariables.containsAll(tmp)&&res.getProvidedVariableNames().containsAll(tmp) )}
}
            res.mySortPriority = node.mySortPriority
            res.sortPriorities = node.sortPriorities
            onChange()
        }
        return res
    }
}
