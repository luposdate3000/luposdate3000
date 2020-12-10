package lupos.s08logicalOptimisation

import lupos.s00misc.EOptimizerID
import lupos.s00misc.EmptyResultException
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.singleinput.LOPProjection

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname: String = "LogicalOptimizerJoinOrder"
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
            } else if (c is OPNothing) {
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
        // put children with same variables into groups, such that those definetly can use Merge-Join as much as possible
        val res = mutableListOf<MutableList<IOPBase>>()
        val variables = mutableListOf<List<String>>()
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
        return res
    }

    /*suspend*/ private fun applyOptimisation(nodes: List<IOPBase>, root: LOPJoin): IOPBase {
        when {
            nodes.size > 2 -> {
                var result = LogicalOptimizerJoinOrderStore(nodes, root)
                if (result != null) {
                    return result
                }
                result = LogicalOptimizerJoinOrderCostBasedOnHistogram(nodes, root)
                if (result != null) {
                    return result
                }
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
                SanityCheck.check { nodes.size == 1 }
                return nodes[0]
            }
        }
/*Coverage Unreachable*/
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            val originalProvided = node.getProvidedVariableNames()
            try {
                val allChilds2 = findAllJoinsInChildren(node)
                if (allChilds2.size > 2) {
                    var result: IOPBase? = null
                    if (result == null && node.onlyExistenceRequired) {
                        // dont not prefer merge join for_ ask-queries, as this makes it harder later, to avoid any materialisation
                        result = LogicalOptimizerJoinOrderStore(allChilds2, node)
                    }
                    if (result == null) {
                        val allChilds3 = clusterizeChildren(allChilds2)
                        val allChilds4 = mutableListOf<IOPBase>()
                        for (child in allChilds3) {
                            allChilds4.add(applyOptimisation(child, node))
                        }
                        result = applyOptimisation(allChilds4, node)
                    }
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
        return res
    }
}
