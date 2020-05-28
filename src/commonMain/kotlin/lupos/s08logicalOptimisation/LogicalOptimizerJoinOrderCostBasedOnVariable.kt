package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
object LogicalOptimizerJoinOrderCostBasedOnVariable {
    class Plan : Comparable<Plan> {
        val child: OPBase?
        val childs: Pair<Int, Int>?
        val variables: Array<Int>
        val columns: Int
        val cost: Int
        val depth: Int
        constructor(child: OPBase, variables: Array<Int>, allVariables: List<Int>) {
            depth = 1
            this.child = child
            childs = null
            this.variables = variables
            var c = 0
            for (i in 0 until variables.size) {
Coverage.forLoopStart(9240)
                val t = variables[i]
                if (t > 0 && allVariables[i] > t) {
Coverage.ifStart(9241)
                    c++
                }
            }
            columns = c
            cost = columns
        }
        inline fun sqr(i: Int) = i * i
        constructor(plans: Array<Plan?>, childA: Int, childB: Int, allVariables: List<Int>) {
            child = null
            childs = Pair(childA, childB)
            val va = plans[childA]!!.variables
            val vb = plans[childB]!!.variables
            val depthA = plans[childA]!!.depth
            val depthB = plans[childB]!!.depth
            if (depthB > depthA) {
Coverage.ifStart(9242)
                depth = depthB + 1
            } else {
Coverage.ifStart(9243)
                depth = depthA + 1
            }
            this.variables = Array(allVariables.size) { va[it] + vb[it] }
            var c = 0
            for (i in 0 until variables.size) {
Coverage.forLoopStart(9244)
                val t = va[i] + vb[i]
                if (t > 0 && allVariables[i] > t) {
Coverage.ifStart(9245)
                    c++
                }
            }
            columns = c
            if (depthA == depthB) {
Coverage.ifStart(9246)
                cost = sqr(plans[childA]!!.columns + plans[childB]!!.columns)
            } else if (depthA < depthB) {
Coverage.ifStart(9247)
                cost = sqr(plans[childA]!!.columns + plans[childB]!!.columns + plans[childB]!!.columns)
            } else {
Coverage.ifStart(9248)
                cost = sqr(plans[childA]!!.columns + plans[childA]!!.columns + plans[childB]!!.columns)
            }
            //cost calculation ... the least cost for_ deepest partial results
        }
        override operator fun compareTo(other: Plan): Int {
Coverage.funStart(9249)
            var res = cost.compareTo(other.cost)
Coverage.statementStart(9250)
            return res
        }
        fun toOPBase(plans: Array<Plan?>): OPBase {
Coverage.funStart(9251)
            if (child != null) {
Coverage.ifStart(9252)
                return child
            }
Coverage.statementStart(9253)
            val cA = plans[childs!!.first]!!.toOPBase(plans)
Coverage.statementStart(9254)
            val cB = plans[childs.second]!!.toOPBase(plans)
Coverage.statementStart(9255)
            return LOPJoin(cA.query, cA, cB, false)
        }
    }
    fun optimize(plans: Array<Plan?>, target: Int, variables: List<Int>) {
Coverage.funStart(9256)
        val targetInv = target.inv()
Coverage.statementStart(9257)
        for (a in 1 until target) {
Coverage.forLoopStart(9258)
            //the other half is already calculated due to the inverse
Coverage.statementStart(9259)
            if (a and targetInv == 0) {
Coverage.ifStart(9260)
                val b = target xor a
Coverage.statementStart(9261)
                if (a < b) {
Coverage.ifStart(9262)
                    val newPlan = Plan(plans, a, b, variables)
Coverage.statementStart(9263)
                    if (plans[target] == null) {
Coverage.ifStart(9264)
                        plans[target] = newPlan
Coverage.statementStart(9265)
                    } else if (newPlan < plans[target]!!) {
Coverage.ifStart(9266)
                        plans[target] = newPlan
Coverage.statementStart(9267)
                    }
Coverage.statementStart(9268)
                }
Coverage.statementStart(9269)
            }
Coverage.statementStart(9270)
        }
Coverage.statementStart(9271)
    }
    operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): OPBase? {
Coverage.funStart(9272)
        SanityCheck.check { allChilds.size > 2 }
Coverage.statementStart(9273)
        if (allChilds.size < 30) {
Coverage.ifStart(9274)
            val allVariables = mutableListOf<String>()
Coverage.statementStart(9275)
            val allVariablesCounters = mutableListOf<Int>()
Coverage.statementStart(9276)
            val plans = arrayOfNulls<Plan?>(1 shl allChilds.size)
Coverage.statementStart(9277)
            var key = 1
Coverage.statementStart(9278)
            for (i in allChilds.indices) {
Coverage.forLoopStart(9279)
                val tmp = allChilds[i].getProvidedVariableNames()
Coverage.statementStart(9280)
                for (t in tmp) {
Coverage.forLoopStart(9281)
                    if (!allVariables.contains(t)) {
Coverage.ifStart(9282)
                        allVariables.add(t)
Coverage.statementStart(9283)
                        allVariablesCounters.add(1)
Coverage.statementStart(9284)
                    } else {
Coverage.ifStart(9285)
                        for (j in allVariables.indices) {
Coverage.forLoopStart(9286)
                            if (allVariables[j] == t) {
Coverage.ifStart(9287)
                                allVariablesCounters[j]++
Coverage.statementStart(9288)
                                break
                            }
Coverage.statementStart(9289)
                        }
Coverage.statementStart(9290)
                    }
Coverage.statementStart(9291)
                }
Coverage.statementStart(9292)
            }
Coverage.statementStart(9293)
            for (t in root.getProvidedVariableNames()) {
Coverage.forLoopStart(9294)
                /*this loop makes sure, that variables referenced after all of these joins are considered unavoidable*/
Coverage.statementStart(9295)
                for (j in allVariables.indices) {
Coverage.forLoopStart(9296)
                    if (allVariables[j] == t) {
Coverage.ifStart(9297)
                        allVariablesCounters[j]++
Coverage.statementStart(9298)
                        break
                    }
Coverage.statementStart(9299)
                }
Coverage.statementStart(9300)
            }
Coverage.statementStart(9301)
            for (i in allChilds.indices) {
Coverage.forLoopStart(9302)
                val variables = Array(allVariables.size) { 0 }
Coverage.statementStart(9303)
                val tmp = allChilds[i].getProvidedVariableNames()
Coverage.statementStart(9304)
                for (t in tmp) {
Coverage.forLoopStart(9305)
                    SanityCheck.check { allVariables.contains(t) }
Coverage.statementStart(9306)
                    for (j in allVariables.indices) {
Coverage.forLoopStart(9307)
                        if (allVariables[j] == t) {
Coverage.ifStart(9308)
                            variables[j]++
Coverage.statementStart(9309)
                            break
                        }
Coverage.statementStart(9310)
                    }
Coverage.statementStart(9311)
                }
Coverage.statementStart(9312)
                plans[key] = Plan(allChilds[i], variables, allVariablesCounters)
Coverage.statementStart(9313)
                key *= 2
Coverage.statementStart(9314)
            }
Coverage.statementStart(9315)
            for (i in 0 until plans.size) {
Coverage.forLoopStart(9316)
                optimize(plans, i, allVariablesCounters)
Coverage.statementStart(9317)
            }
Coverage.statementStart(9318)
            val bestPlan = plans[plans.size - 1]!!
Coverage.statementStart(9319)
            return bestPlan.toOPBase(plans)
        }
Coverage.statementStart(9320)
        return null
    }
}
