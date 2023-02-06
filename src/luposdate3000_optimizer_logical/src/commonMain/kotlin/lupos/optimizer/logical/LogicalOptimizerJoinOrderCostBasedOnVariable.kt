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

import lupos.operator.logical.multiinput.LOPJoin
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public object LogicalOptimizerJoinOrderCostBasedOnVariable {
    public /*suspend*/ fun optimize(plans: Array<LogicalOptimizerJoinOrderCostBasedOnVariable_Plan?>, target: Int, variables: List<Int>) {
        val targetInv = target.inv()
        for (a in 1 until target) {
            // the other half is already calculated due to the inverse
            if (a and targetInv == 0) {
                val b = target xor a
                if (a < b) {
                    val newPlan = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan(plans, a, b, variables)
                    if (plans[target] == null) {
                        plans[target] = newPlan
                    } else if (newPlan < plans[target]!!) {
                        plans[target] = newPlan
                    }
                }
            }
        }
    }

    public /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase? {
if(SanityCheck.enabled){if(!( allChilds.size > 2 )){throw Exception("SanityCheck failed")}}
        if (allChilds.size < 30) {
            val allVariables = mutableListOf<String>()
            val allVariablesCounters = mutableListOf<Int>()
            val plans = arrayOfNulls<LogicalOptimizerJoinOrderCostBasedOnVariable_Plan?>(1 shl allChilds.size)
            var key = 1
            for (i in allChilds.indices) {
                val tmp = allChilds[i].getProvidedVariableNames()
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
            for (t in root.getProvidedVariableNames()) {
                /*this loop makes sure, that variables referenced after all of these joins are considered unavoidable*/
                for (j in allVariables.indices) {
                    if (allVariables[j] == t) {
                        allVariablesCounters[j]++
                        break
                    }
                }
            }
            for (i in allChilds.indices) {
                val variables = Array(allVariables.size) { 0 }
                val tmp = allChilds[i].getProvidedVariableNames()
                for (t in tmp) {
if(SanityCheck.enabled){if(!( allVariables.contains(t) )){throw Exception("SanityCheck failed")}}
                    for (j in allVariables.indices) {
                        if (allVariables[j] == t) {
                            variables[j]++
                            break
                        }
                    }
                }
                plans[key] = LogicalOptimizerJoinOrderCostBasedOnVariable_Plan(allChilds[i], variables, allVariablesCounters)
                key *= 2
            }
            for (i in plans.indices) {
                optimize(plans, i, allVariablesCounters)
            }
            val bestPlan = plans[plans.size - 1]!!
            return bestPlan.toOPBase(plans)
        }
        return null
    }
}
