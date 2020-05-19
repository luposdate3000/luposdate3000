package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortType
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SortHelper
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.OptimizerBase

class LogicalOptimizerDistinctSplit(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerDistinctSplitID) {
    override val classname = "LogicalOptimizerDistinctSplit"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPDistinct) {
            val child = node.children[0]
            val provided = child.getProvidedVariableNames().toMutableList()
            if (provided.size == 0) {
                //no variables -> no sort required
                res = LOPReduced(query, child)
                onChange()
            } else {
                if (child.mySortPriority.size == provided.size) {
                    res = LOPReduced(query, LOPSortAny(query, child.mySortPriority, child))
                    onChange()
                } else {
                    if (child is LOPJoin) {
                        val columns = LOPJoin.getColumns(child.children[0].getProvidedVariableNames(), child.children[1].getProvidedVariableNames())
                        val variables = mutableListOf<String>()
                        variables.addAll(columns[0])
                        variables.addAll(columns[1])
                        variables.addAll(columns[2])
                        res = LOPReduced(query, LOPSortAny(query, variables.map { SortHelper(it, ESortType.FAST) }, child))
                        onChange()
                    } else {
                        res = LOPReduced(query, LOPSortAny(query, provided.map { SortHelper(it, ESortType.FAST) }, child))
                        onChange()
                    }
                }
            }
        } else if (node is LOPSortAny) {
            val variables = node.possibleSortOrder.map { it.variableName }
            val child = node.children[0]
            var flag = node.possibleSortOrder.size == child.mySortPriority.size
            var i = 0
            while (flag && i < child.mySortPriority.size) {
                if (child.mySortPriority[i].variableName != node.possibleSortOrder[i].variableName || child.mySortPriority[i].sortType != node.possibleSortOrder[i].sortType) {
                    flag = false
                }
                i++
            }
            if (flag) {
                res = child
                onChange()
            } else {
                if (child is LOPJoin) {
/*
                    var flag = true
                    var provided = child.children[0].getProvidedVariableNames().distinct()
                    var i = 0
                    var aList = mutableListOf<SortHelper>()
                    var bList = mutableListOf<SortHelper>()
                    while (flag && i < provided.size && i < variables.size) {
                        if (!provided.contains(variables[i])) {
                            flag = false
                        }
                        aList.add(node.possibleSortOrder[i])
                        i++
                    }
                    provided = child.children[1].getProvidedVariableNames().distinct()
                    i = 0
                    while (flag && i < provided.size && i < variables.size) {
                        if (!provided.contains(variables[i])) {
                            flag = false
                        }
                        bList.add(node.possibleSortOrder[i])
                        i++
                    }
                    if (flag) {
                        child.children[0] = LOPSortAny(query, aList, child.children[0])
                        child.children[1] = LOPSortAny(query, bList, child.children[1])
                        res = child
                        onChange()
                    }
*/
                } else if (child is LOPFilter) {
                    child.children[0] = LOPSortAny(query, node.possibleSortOrder, child.children[0])
                    res = child
                    onChange()
                }
            }
        } else if (node is LOPMinus) {
            if (!node.hadReducedPushDown) {
                node.hadReducedPushDown = true
                node.children[1] = LOPReduced(query, node.children[1])
                onChange()
            }
        } else if (node is LOPReduced) {
            val child = node.children[0]
            if (child is LOPReduced) {
                res = child
                onChange()
            } else if (child is LOPProjection && child.children[0] is LOPMinus) {
                val child2 = child.children[0]//as LOPMinus
                child2.children[0] = LOPReduced(query, LOPProjection(query, child.variables.toMutableList(), child2.children[0]))
                child2.children[1] = LOPReduced(query, LOPProjection(query, child.variables.toMutableList(), child2.children[1]))
                res = child
                onChange()
            } else if (!node.hadPushDown) {
                node.hadPushDown = true
                if (child is LOPProjection) {
                    if (node.partOfAskQuery) {
                        child.children[0] = LOPReduced(query, child.children[0])
                    } else if (child.variables.size == 1) {
                        child.children[0] = LOPReduced(query, LOPSortAny(query, mutableListOf(SortHelper(child.variables[0].name, ESortType.FAST)), child.children[0]))
                    }
                    onChange()
                } else if (child is LOPTriple) {
                    res = child
                    onChange()
                } else if (child is LOPJoin) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    child.children[1] = LOPReduced(query, child.children[1])
                    res = child
                    onChange()
                } else if (child is LOPUnion) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    child.children[1] = LOPReduced(query, child.children[1])
                    onChange()
                } else if (child is LOPMinus) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    res = child
                    onChange()
                } else if (child is LOPFilter) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    res = child
                    onChange()
                } else if (child is LOPSortAny) {
                    child.children[0] = LOPReduced(query, child.children[0])
                    onChange()
                }
            }
        }
/*return*/res
    })
}
