package lupos.s08logicalOptimisation

import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s08logicalOptimisation.OptimizerBase
import lupos.s00misc.EmptyResultException

class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"
    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
        val res = mutableListOf<OPBase>()
        for (c in node.children) {
            if (c is LOPJoin && !c.optional) {
                res.addAll(findAllJoinsInChildren(c))
            } else if (c is LOPProjection) {
                var d = c.children[0]
                while (d is LOPProjection) {
                    d = d.children[0]
                }
                if (d is LOPJoin && !d.optional) {
                    res.addAll(findAllJoinsInChildren(d))
                } else {
                    res.add(d)
                }
            } else if (c is OPNothing) {
                //there can not be any result, if_ one of the children does not have any output.
                throw EmptyResultException()
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
        return res
    }

    fun applyOptimisation(nodes: List<OPBase>, root: LOPJoin): OPBase {
        if (nodes.size > 2) {
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
        } else if (nodes.size == 2) {
            var res = LOPJoin(root.query, nodes[0], nodes[1], false)
            res.onlyExistenceRequired = root.onlyExistenceRequired
            return res
        } else {
            SanityCheck.check { nodes.size == 1 }
            return nodes[0]
        }
/*Coverage Unreachable*/
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
            var originalProvided = node.getProvidedVariableNames()
            try {
                val allChilds2 = findAllJoinsInChildren(node)
                if (allChilds2.size > 2) {
                    var result: OPBase? = null
                    if (result == null && node.onlyExistenceRequired) {
                        //dont not prefer merge join for_ ask-queries, as this makes it harder later, to avoid any materialisation
                        result = LogicalOptimizerJoinOrderStore(allChilds2, node)
                    }
                    if (result == null) {
                        val allChilds3 = clusterizeChildren(allChilds2)
                        val allChilds4 = mutableListOf<OPBase>()
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
