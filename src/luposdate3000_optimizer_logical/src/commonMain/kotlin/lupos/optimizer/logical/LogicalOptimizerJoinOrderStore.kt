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

import lupos.operator_logical.IOPBase
import lupos.operator_logical.multiinput.LOPJoin
import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.SanityCheck

public object LogicalOptimizerJoinOrderStore {
    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase? {
        SanityCheck.check { allChilds.size > 2 }
        if (root.onlyExistenceRequired) {
            SanityCheck {
                for (c in allChilds) {
                    SanityCheck.check { c.getOnlyExistenceRequired() }
                }
            }
            val queue = mutableListOf<IOPBase>()
            queue.addAll(allChilds)
            var lastVariable = 0
            var lastVariableCount = Int.MAX_VALUE
            for (i in queue.indices) {
                val tmp = queue[i].getProvidedVariableNames().size
                if (tmp < lastVariableCount) {
                    lastVariableCount = tmp
                    lastVariable = i
                } else {
                    try {
                        if (tmp == lastVariableCount && queue[i].getHistogram().count < queue[lastVariable].getHistogram().count) {
                            lastVariableCount = tmp
                            lastVariable = i
                        }
                    } catch (e: HistogramNotImplementedException) {
                        e.printStackTrace()
                    }
                }
            }
            val lastChild = queue.removeAt(lastVariable)
            val allVariables = mutableListOf<String>()
            val allVariablesCounters = mutableListOf<Int>()
            for (i in queue.indices) {
                val tmp = queue[i].getProvidedVariableNames()
                for (t in tmp) {
                    if (!allVariables.contains(t)) {
                        allVariables.add(t)
                        allVariablesCounters.add(1)
                    } else {
                        for (j in allVariables.indices) {
                            if (allVariables[j] == t) {
                                allVariablesCounters[j]++
                                break
                            }
                        }
                    }
                }
            }
            val allVariablesOrdered = mutableListOf<String>()
            allVariablesOrdered.addAll(lastChild.getProvidedVariableNames())
            val result = mutableListOf<IOPBase>()
            while (queue.size > 0) {
                var max = -1
                var maxI = 0
                var i = 0
                while (i < queue.size) {
                    val provided = queue[i].getProvidedVariableNames()
                    var score = provided.size
                    loop@ for (p in provided) {
                        for (s in allVariablesOrdered.indices) {
                            if (p == allVariablesOrdered[s]) {
                                score += provided.size - s + 100
                                continue@loop
                            }
                        }
                    }
                    if (score > max) {
                        maxI = i
                        max = score
                    }
                    i++
                }
                val node = queue.removeAt(maxI)
                val tmp = mutableListOf<String>() // push the variables of this node the the front of preferred variables ... this way, consecutive joins use the same variables.
                tmp.addAll(node.getProvidedVariableNames())
                allVariablesOrdered.removeAll(tmp)
                tmp.addAll(allVariablesOrdered)
                allVariablesOrdered.clear()
                allVariablesOrdered.addAll(tmp)
                result.add(node)
            }
            var res = lastChild
            while (result.size > 0) {
                val b = result.removeAt(0)
                res = LOPJoin(root.query, res, b, false)
                res.onlyExistenceRequired = true
            }
            return res
        }
        return null
    }
}
