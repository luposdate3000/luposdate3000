package lupos.s08logicalOptimisation

import kotlin.jvm.JvmField
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase


class Plan : Comparable<Plan> {
    val child: OPBase?
    val childs: Pair<Int, Int>?
    val variables: Set<String>
    val cost: Int

    constructor(child: OPBase) {
        this.child = child
        childs = null
        this.variables = mutableSetOf<String>()
        variables.addAll(child.getProvidedVariableNames())
        cost = -variables.size
    }

    constructor(plans: Array<Plan?>, childA: Int, childB: Int) {
        child = null
        childs = Pair(childA, childB)
        variables = plans[childA]!!.variables + plans[childB]!!.variables
        cost = -(plans[childA]!!.variables.intersect(plans[childB]!!.variables)).size
    }

    override operator fun compareTo(other: Plan) = cost.compareTo(other.cost)

    fun toOPBase(plans: Array<Plan?>): OPBase {
        if (child != null)
            return child
        val cA = plans[childs!!.first]!!.toOPBase(plans)
        val cB = plans[childs!!.second]!!.toOPBase(plans)
        return LOPJoin(cA.query, cA, cB, false)
    }
}

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"

    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
        val res = mutableListOf<OPBase>()
        for (c in node.children)
            if (c is LOPJoin && !c.optional)
                res.addAll(findAllJoinsInChildren(c))
            else
                res.add(c)
        return res
    }

    fun optimize(plans: Array<Plan?>, max: Int) {
        for (i in 1 until max)
            if (i and max == 0) {
                val key = i + max
                val newPlan = Plan(plans, i, max)
                if (plans[key] == null) {
                    plans[key] = newPlan
                    optimize(plans, i)
                } else if (newPlan < plans[key]!!)
                    plans[key] = newPlan
            }
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            val allChilds = findAllJoinsInChildren(node)
            if (allChilds.size > 2 && allChilds.size < 30) {
                val plans = arrayOfNulls<Plan?>(1 shl allChilds.size)
                var key = 1
                for (i in allChilds.indices) {
                    plans[key] = Plan(allChilds[i])
                    optimize(plans, key)
                    key *= 2
                }
                val bestPlan = plans[plans.size - 1]!!
                val result = bestPlan.toOPBase(plans)
                if (result != res) {
                    res = result
                    onChange()
                }
            }
        }
        res
    })
}
