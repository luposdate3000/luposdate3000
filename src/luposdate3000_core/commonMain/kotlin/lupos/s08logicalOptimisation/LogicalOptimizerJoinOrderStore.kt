package lupos.s08logicalOptimisation

import lupos.s00misc.HistogramNotImplementedException
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase

object LogicalOptimizerJoinOrderStore {
    suspend operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): IOPBase? {
        SanityCheck.check { allChilds.size > 2 }
        if (root.onlyExistenceRequired) {
            SanityCheck {
                for (c in allChilds) {
                    SanityCheck.check { c.onlyExistenceRequired }
                }
            }
            val queue = mutableListOf<OPBase>()
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
            var allVariablesOrdered = mutableListOf<String>()
            allVariablesOrdered.addAll(lastChild.getProvidedVariableNames())
            val result = mutableListOf<OPBase>()
            while (queue.size > 0) {
                var max = -1
                var maxI = 0
                var i = 0
                while (i < queue.size) {
                    var provided = queue[i].getProvidedVariableNames()
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
                var node = queue.removeAt(maxI)
                var tmp = mutableListOf<String>()//push the variables of this node the the front of preferred variables ... this way, consecutive joins use the same variables.
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
