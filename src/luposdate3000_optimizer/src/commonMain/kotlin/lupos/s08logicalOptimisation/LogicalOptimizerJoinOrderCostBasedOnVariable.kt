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
package lupos.s08logicalOptimisation
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import kotlin.jvm.JvmField
public object LogicalOptimizerJoinOrderCostBasedOnVariable {
    public class Plan : Comparable<Plan> {
        @JvmField
        public val child: IOPBase?
        @JvmField
        public val childs: Pair<Int, Int>?
        @JvmField
        public val variables: Array<Int>
        @JvmField
        public val columns: Int
        @JvmField
        public val cost: Int
        @JvmField
        public val depth: Int
        public constructor(child: IOPBase, variables: Array<Int>, allVariables: List<Int>) {
            depth = 1
            this.child = child
            childs = null
            this.variables = variables
            var c = 0
            for (i in variables.indices) {
                val t = variables[i]
                if (t > 0 && allVariables[i] > t) {
                    c++
                }
            }
            columns = c
            cost = columns
        }
        @Suppress("NOTHING_TO_INLINE") private inline fun sqr(i: Int) = i * i
        public constructor(plans: Array<Plan?>, childA: Int, childB: Int, allVariables: List<Int>) {
            child = null
            childs = Pair(childA, childB)
            val va = plans[childA]!!.variables
            val vb = plans[childB]!!.variables
            val depthA = plans[childA]!!.depth
            val depthB = plans[childB]!!.depth
            depth = if (depthB > depthA) {
                depthB + 1
            } else {
                depthA + 1
            }
            this.variables = Array(allVariables.size) { va[it] + vb[it] }
            var c = 0
            for (i in variables.indices) {
                val t = va[i] + vb[i]
                if (t > 0 && allVariables[i] > t) {
                    c++
                }
            }
            columns = c
            cost = when {
                depthA == depthB -> {
                    sqr(plans[childA]!!.columns + plans[childB]!!.columns)
                }
                depthA < depthB -> {
                    sqr(plans[childA]!!.columns + plans[childB]!!.columns + plans[childB]!!.columns)
                }
                else -> {
                    sqr(plans[childA]!!.columns + plans[childA]!!.columns + plans[childB]!!.columns)
                }
            }
            // cost calculation ... the least cost for_ deepest partial results
        }
        override operator fun compareTo(other: Plan): Int {
            return cost.compareTo(other.cost)
        }
        public fun toOPBase(plans: Array<Plan?>): IOPBase {
            if (child != null) {
                return child
            }
            val cA = plans[childs!!.first]!!.toOPBase(plans)
            val cB = plans[childs.second]!!.toOPBase(plans)
            return LOPJoin(cA.getQuery(), cA, cB, false)
        }
    }
    public /*suspend*/ fun optimize(plans: Array<Plan?>, target: Int, variables: List<Int>) {
        val targetInv = target.inv()
        for (a in 1 until target) {
            // the other half is already calculated due to the inverse
            if (a and targetInv == 0) {
                val b = target xor a
                if (a < b) {
                    val newPlan = Plan(plans, a, b, variables)
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
        SanityCheck.check { allChilds.size > 2 }
        if (allChilds.size < 30) {
            val allVariables = mutableListOf<String>()
            val allVariablesCounters = mutableListOf<Int>()
            val plans = arrayOfNulls<Plan?>(1 shl allChilds.size)
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
                    SanityCheck.check { allVariables.contains(t) }
                    for (j in allVariables.indices) {
                        if (allVariables[j] == t) {
                            variables[j]++
                            break
                        }
                    }
                }
                plans[key] = Plan(allChilds[i], variables, allVariablesCounters)
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
