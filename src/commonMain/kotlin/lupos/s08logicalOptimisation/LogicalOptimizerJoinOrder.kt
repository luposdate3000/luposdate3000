package lupos.s08logicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.classNameToString
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.DynamicByteArray
import lupos.s00misc.EBenchmark
import lupos.s00misc.EGraphRefType
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s00misc.EOperatorID
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ESortPriority
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.File
import lupos.s00misc.JenaWrapper
import lupos.s00misc.MemoryStatistics
import lupos.s00misc.MyListDouble
import lupos.s00misc.MyListGeneric
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapDoubleInt
import lupos.s00misc.MyMapIntGeneric
import lupos.s00misc.MyMapIntInt
import lupos.s00misc.MyMapLongGeneric
import lupos.s00misc.MyMapLongInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s00misc.MyMapStringIntPatriciaTrieDouble
import lupos.s00misc.MySetInt
import lupos.s00misc.OperatorGraphToLatex
import lupos.s00misc.parseFromXml
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.MyListValue
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase



class Plan : Comparable<Plan> {
    val child: OPBase?
    val childs: Pair<Int, Int>?
    val variables: Array<Int>
    val columns: Int
    val cost: Int

    constructor(child: OPBase, variables: Array<Int>, allVariables: List<Int>) {
        this.child = child
        childs = null
        this.variables = variables
        var c = 0
        for (i in 0 until variables.size) {
            if (allVariables[i] > variables[i] && variables[i] > 0) {
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
        this.variables = Array(allVariables.size) { va[it] + vb[it] }
        var c = 0
        for (i in 0 until variables.size) {
            val t = va[i] + vb[i]
            if (allVariables[i] > t && t > 0) {
                c++
            }
        }
        columns = c
        cost = sqr(plans[childA]!!.columns) + sqr(plans[childB]!!.columns)
    }

    override operator fun compareTo(other: Plan): Int {
        var res = cost.compareTo(other.cost)
        return res
    }

    fun toOPBase(plans: Array<Plan?>): OPBase {
        if (child != null) {
            return child
        }
        val cA = plans[childs!!.first]!!.toOPBase(plans)
        val cB = plans[childs.second]!!.toOPBase(plans)
        return LOPJoin(cA.query, cA, cB, false)
    }
}

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"
    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
        val res = mutableListOf<OPBase>()
        for (c in node.children) {
            if (c is LOPJoin && !c.optional) {
                res.addAll(findAllJoinsInChildren(c))
            } else {
                res.add(c)
            }
        }
        return res
    }

    fun optimize(plans: Array<Plan?>, target: Int, variables: List<Int>) {
        val targetInv = target.inv()
        for (a in 1 until target) {
            if (a and targetInv == 0) {
                val b = target - a
                if (b != 0) {
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

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            val allChilds = findAllJoinsInChildren(node)
            if (allChilds.size > 2 && allChilds.size < 30) {
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
                for (i in 1 until plans.size) {
                    optimize(plans, i, allVariablesCounters)
                }
                val bestPlan = plans[plans.size - 1]!!
                val result = bestPlan.toOPBase(plans)
                if (result != res) {
                    res = result
                    onChange()
                }
            }
        }
/*return*/res
    })
}
