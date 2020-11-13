package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin

object LogicalOptimizerJoinOrderCostBasedOnVariable {
    class Plan : Comparable<Plan> {
        @JvmField
        val child: IOPBase?

        @JvmField
        val childs: Pair<Int, Int>?

        @JvmField
        val variables: Array<Int>

        @JvmField
        val columns: Int

        @JvmField
        val cost: Int

        @JvmField
        val depth: Int

        constructor(child: IOPBase, variables: Array<Int>, allVariables: List<Int>) {
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

        inline private fun sqr(i: Int) = i * i

        constructor(plans: Array<Plan?>, childA: Int, childB: Int, allVariables: List<Int>) {
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
            cost = if (depthA == depthB) {
                sqr(plans[childA]!!.columns + plans[childB]!!.columns)
            } else if (depthA < depthB) {
                sqr(plans[childA]!!.columns + plans[childB]!!.columns + plans[childB]!!.columns)
            } else {
                sqr(plans[childA]!!.columns + plans[childA]!!.columns + plans[childB]!!.columns)
            }
            //cost calculation ... the least cost for_ deepest partial results
        }

        override operator fun compareTo(other: Plan): Int {
            return cost.compareTo(other.cost)
        }

        fun toOPBase(plans: Array<Plan?>): IOPBase {
            if (child != null) {
                return child
            }
            val cA = plans[childs!!.first]!!.toOPBase(plans)
            val cB = plans[childs.second]!!.toOPBase(plans)
            return LOPJoin(cA.getQuery(), cA, cB, false)
        }
    }

    /*suspend*/ fun optimize(plans: Array<Plan?>, target: Int, variables: List<Int>) {
        val targetInv = target.inv()
        for (a in 1 until target) {
            //the other half is already calculated due to the inverse
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

    /*suspend*/ operator fun invoke(allChilds: List<IOPBase>, root: LOPJoin): IOPBase? {
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
