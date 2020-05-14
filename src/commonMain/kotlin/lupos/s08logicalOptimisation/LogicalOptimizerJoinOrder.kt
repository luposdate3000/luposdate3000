package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase

class EmptyResultException() : Exception("")

val emptyResultException = EmptyResultException()

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"
    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
        val res = mutableListOf<OPBase>()
        for (c in node.children) {
            if (c is LOPJoin && !c.optional) {
                res.addAll(findAllJoinsInChildren(c))
            } else if (c is LOPProjection) {
                var d = c.children[0]
                if (d is LOPJoin && !d.optional) {
                    res.addAll(findAllJoinsInChildren(d))
                } else {
                    res.add(c)
                }
            } else if (c is OPNothing) {
//there can not be any result, if one of the children does not have any output.
                throw emptyResultException
            } else if (c is OPEmptyRow) {
//skip those unnecessary joins, without any observeable effekt
            } else {
                res.add(c)
            }
        }
        return res
    }

    fun clusterizeChildren(nodes: List<OPBase>): List<MutableList<OPBase>> {
        //put children with same variables into groups, such that those definetly can use Merge-Join as much as possible
        var res = mutableListOf<MutableList<OPBase>>()
        var variables = mutableListOf<List<String>>()
        loop@ for (node in nodes) {
            val v = node.getProvidedVariableNames()
            if (res.size > 0) {
                for (i in 0 until variables.size) {
                    if (variables[i].size == v.size && variables[i].containsAll(v)) {
                        res[i].add(node)
                        continue@loop
                    }
                }
            }
            res.add(mutableListOf(node))
            variables.add(v)
        }
        var queue = mutableListOf<MutableList<OPBase>>()
        var done = false
        while (!done) {
            done = true
            queue.clear()
            queue.addAll(res)
            res.clear()
            variables.clear()
            loop@ for (childs in queue) {
                val v = childs[0].getProvidedVariableNames()
                if (res.size > 0) {
                    for (i in 0 until variables.size) {
                        if (variables[i].containsAll(v)) {
                            res[i].addAll(childs)
                            continue@loop
                        }
                    }
                    for (i in 0 until variables.size) {
                        if (v.containsAll(variables[i])) {
                            res[i].addAll(childs)
                            variables[i] = v
                            done = false
                            //this somehow beaks in terms of efficiency if there is one child, with all variables provided ..
                            continue@loop
                        }
                    }
                }
                res.add(childs)
                variables.add(v)
            }
        }
        return res
    }

    fun applyOptimisation(nodes: List<OPBase>, root: LOPJoin): OPBase {
        if (nodes.size > 2) {
            var result = LogicalOptimizerJoinOrderStore(nodes, root)
            if (result != null) {
                return result
            }
            result = LogicalOptimizerJoinOrderCostBased(nodes, root)
            if (result != null) {
                return result
            }
            throw Exception("unreachable")
        } else if (nodes.size == 2) {
            var res = LOPJoin(root.query, nodes[0], nodes[1], false)
            res.onlyExistenceRequired = root.onlyExistenceRequired
            return res
        } else {
            require(nodes.size == 1)
            return nodes[0]
        }
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res: OPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            var originalProvided = node.getProvidedVariableNames()
            try {
                val allChilds2 = findAllJoinsInChildren(node)
                if (allChilds2.size > 2) {
                    val allChilds3 = clusterizeChildren(allChilds2)
                    val allChilds4 = mutableListOf<OPBase>()
                    for (child in allChilds3) {
                        allChilds4.add(applyOptimisation(child, node))
                    }
                    var result = applyOptimisation(allChilds4, node)
                    if (result != res) {
                        onChange()
                        if (!originalProvided.containsAll(result.getProvidedVariableNames())) {
                            result = LOPProjection(query, originalProvided.map { AOPVariable(query, it) }.toMutableList(), result)
                        }
                        res = result
                    }
                }
            } catch (e: EmptyResultException) {
                res = OPNothing(query, originalProvided)
            }
        }
        /*return*/ res
    })
}
