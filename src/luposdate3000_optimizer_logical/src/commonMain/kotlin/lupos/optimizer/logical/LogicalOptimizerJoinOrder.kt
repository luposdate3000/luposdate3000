/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.optimizer.logical

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.base.Query
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.physical.noinput.POPNothing
import lupos.shared.ESortTypeExt
import lupos.shared.EmptyResultException
import lupos.shared.SanityCheck
import lupos.shared.SortHelper
import lupos.shared.myPrintStackTrace
import lupos.shared.operator.IOPBase

public class LogicalOptimizerJoinOrder(query: Query, internal val capture: Boolean) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerJoinOrderID, "LogicalOptimizerJoinOrder") {
    private fun findAllJoinsInChildren(node: LOPJoin): List<IOPBase> {
        val res = mutableListOf<IOPBase>()
        for (c in node.getChildren()) {
            if (c is LOPJoin && !c.optional) {
                res.addAll(findAllJoinsInChildren(c))
            } else if (c is LOPProjection) {
                var d = c.getChildren()[0]
                while (d is LOPProjection) {
                    d = d.getChildren()[0]
                }
                if (d is LOPJoin && !d.optional) {
                    res.addAll(findAllJoinsInChildren(d))
                } else {
                    res.add(d)
                }
            } else if (c is POPNothing) {
                // there can not be any result, if_ one of the children does not have any output.
                throw EmptyResultException()
            } else if (c is OPEmptyRow) {
                // skip those unnecessary joins, without any observeable effekt
            } else {
                res.add(c)
            }
        }
        return res
    }

    private fun clusterizeChildren(nodes: List<IOPBase>): List<MutableList<IOPBase>> {
        val res = mutableListOf<MutableList<IOPBase>>()
        val cacheProvidedVariableNames = nodes.map { it.getProvidedVariableNames().toSet().toList() }
        val cachePossibleSortPriorities = nodes.map { it.getPossibleSortPriorities() }
        val allVariables = cacheProvidedVariableNames.flatten().toSet().toList()
        val cachePossibleSortPrioritiesIdx = cachePossibleSortPriorities.map {
            val x = it.map { it2 ->
                val f = it2.first()
                if (f.sortType == ESortTypeExt.FAST) {
                    allVariables.indexOf(f.variableName)
                } else {
                    -1
                }
            }.toMutableSet()
            x.remove(-1)
            x
        }

        val remainingNodes = IntArray(nodes.size) { it }.toMutableList()
        while (remainingNodes.size > 0) {
            val allVariablesSortCounters = IntArray(allVariables.size)
            for (i in remainingNodes) {
                for (j in cachePossibleSortPrioritiesIdx[i]) {
                    allVariablesSortCounters[j]++
                }
            }
            var max = 0
            var maxIdx = 0
            for (i in 0 until allVariables.size) {
                if (allVariablesSortCounters[i] >= max) {
                    max = allVariablesSortCounters[i]
                    maxIdx = i
                }
            }
            val current = mutableListOf<IOPBase>()
            val groupIds = mutableSetOf<Int>()
            for (i in remainingNodes.toList()) {
                if (cachePossibleSortPrioritiesIdx[i].contains(maxIdx)) {
                    val node = nodes[i]
                    node.selectSortPriority(listOf(SortHelper(allVariables[maxIdx], ESortTypeExt.FAST)))
                    groupIds.add(i)
                    remainingNodes.remove(i)
                    current.add(node)
                }
            }
            if (groupIds.size == 0) {
                val i = remainingNodes.first()
                val node = nodes[i]
                groupIds.add(i)
                remainingNodes.remove(i)
                current.add(node)
            }
            res.add(current)
        }
        return res
    }

    /*suspend*/ private fun applyOptimisation(nodes: List<IOPBase>, root: LOPJoin): IOPBase {
        when {
            nodes.size > 2 -> {
                var result = LogicalOptimizerJoinOrderStore(nodes, root)
                if (result != null) {
                    return result
                }
try{
                if (query.getInstance().enableJoinOrderOnDynamicProgramming) {
                    result = LogicalOptimizerJoinOrderCostBasedOnDynamicProgramming(nodes, root)
                    return result
                }
}catch(e:Throwable){}
try{
                if (query.getInstance().enableJoinOrderOnHistogram) {
                    result = LogicalOptimizerJoinOrderCostBasedOnHistogram(nodes, root)
                    return result
                }
}catch(e:Throwable){}
                result = LogicalOptimizerJoinOrderCostBasedOnVariable(nodes, root)
                if (result != null) {
                    return result
                }
                SanityCheck.checkUnreachable()
            }
            nodes.size == 2 -> {
                val res = LOPJoin(root.query, nodes[0], nodes[1], false)
                res.onlyExistenceRequired = root.onlyExistenceRequired
                return res
            }
            else -> {
                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerJoinOrder.kt:147"/*SOURCE_FILE_END*/ }, { nodes.size == 1 })
                return nodes[0]
            }
        }
/*Coverage Unreachable*/
    }

    internal fun internalOptimize(node: LOPJoin, allChilds2: List<IOPBase>, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        val originalProvided = node.getProvidedVariableNames()
        if (allChilds2.size > 2) {
            var result: IOPBase? = null
            if (result == null) {
if(query.getInstance().enableJoinOrderOnDynamicProgrammingNoCluster){
result = applyOptimisation(allChilds2, node)
println("optimized for no cluster")
}else{
                val allChilds3 = clusterizeChildren(allChilds2)
                val allChilds4 = mutableListOf<IOPBase>()
                for (child in allChilds3) {
                    allChilds4.add(applyOptimisation(child, node))
                }
                result = applyOptimisation(allChilds4, node)
}
            }
            if (result != res) {
                onChange()
                if (!originalProvided.containsAll(result.getProvidedVariableNames())) {
                    result = LOPProjection(query, originalProvided.map { AOPVariable(query, it) }.toMutableList(), result)
                }
                res = result
            }
        }
        return res
    }

    private fun calculateJoinOrderTree(originalChilds: Array<IOPBase>, head: IOPBase, res: MutableList<Int>): Int {
        if (head is LOPJoin) {
            val ll = head.children[0]
            val rr = head.children[1]
            val l = calculateJoinOrderTree(originalChilds, ll, res)
            val r = calculateJoinOrderTree(originalChilds, rr, res)
            res.add(l)
            res.add(r)
            return -1 - (res.size - 2) / 2
        } else {
            val ret = originalChilds.indexOf(head)
            if (ret < 0) {
                TODO("child not found")
            }
            return ret
        }
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            val originalProvided = node.getProvidedVariableNames()
            try {
                if (capture) {
                    val allChilds2 = findAllJoinsInChildren(node)
                    val backup = allChilds2.toTypedArray()
                    res = internalOptimize(node, allChilds2, onChange)
                    val jo = mutableListOf<Int>()
                    calculateJoinOrderTree(backup, res, jo)
                    query.machineLearningOptimizerOrder2 = jo
                } else {
                    val allChilds2 = findAllJoinsInChildren(node)
                    res = internalOptimize(node, allChilds2, onChange)
                }
            } catch (e: EmptyResultException) {
                e.myPrintStackTrace(/*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_optimizer_logical/src/commonMain/kotlin/lupos/optimizer/logical/LogicalOptimizerJoinOrder.kt:218"/*SOURCE_FILE_END*/)
                res = POPNothing(query, originalProvided)
            }
        }
        return res
    }
}
