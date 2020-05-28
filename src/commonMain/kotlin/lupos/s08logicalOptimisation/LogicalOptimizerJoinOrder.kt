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
class EmptyResultException() : Exception("")
val emptyResultException = EmptyResultException()
class LogicalOptimizerJoinOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerJoinOrderID) {
    override val classname = "LogicalOptimizerJoinOrder"
    fun findAllJoinsInChildren(node: LOPJoin): List<OPBase> {
Coverage.funStart(9321)
        val res = mutableListOf<OPBase>()
Coverage.statementStart(9322)
        for (c in node.children) {
Coverage.forLoopStart(9323)
            if (c is LOPJoin && !c.optional) {
Coverage.ifStart(9324)
                res.addAll(findAllJoinsInChildren(c))
Coverage.statementStart(9325)
            } else if (c is LOPProjection) {
Coverage.ifStart(9326)
                var d = c.children[0]
Coverage.statementStart(9327)
                while (d is LOPProjection) {
Coverage.whileLoopStart(9328)
                    d = d.children[0]
Coverage.statementStart(9329)
                }
Coverage.statementStart(9330)
                if (d is LOPJoin && !d.optional) {
Coverage.ifStart(9331)
                    res.addAll(findAllJoinsInChildren(d))
Coverage.statementStart(9332)
                } else {
Coverage.ifStart(9333)
                    res.add(d)
Coverage.statementStart(9334)
                }
Coverage.statementStart(9335)
            } else if (c is OPNothing) {
Coverage.ifStart(9336)
                //there can not be any result, if_ one of the children does not have any output.
Coverage.statementStart(9337)
                throw emptyResultException
            } else if (c is OPEmptyRow) {
Coverage.statementStart(9338)
                //skip those unnecessary joins, without any observeable effekt
Coverage.statementStart(9339)
            } else {
Coverage.ifStart(9340)
                res.add(c)
Coverage.statementStart(9341)
            }
Coverage.statementStart(9342)
        }
Coverage.statementStart(9343)
        return res
    }
    fun clusterizeChildren(nodes: List<OPBase>): List<MutableList<OPBase>> {
Coverage.funStart(9344)
        //put children with same variables into groups, such that those definetly can use Merge-Join as much as possible
Coverage.statementStart(9345)
        var res = mutableListOf<MutableList<OPBase>>()
Coverage.statementStart(9346)
        var variables = mutableListOf<List<String>>()
Coverage.statementStart(9347)
        loop@ for (node in nodes) {
Coverage.forLoopStart(9348)
            val v = node.getProvidedVariableNames()
Coverage.statementStart(9349)
            if (res.size > 0) {
Coverage.ifStart(9350)
                for (i in 0 until variables.size) {
Coverage.forLoopStart(9351)
                    if (variables[i].size == v.size && variables[i].containsAll(v)) {
Coverage.ifStart(9352)
                        res[i].add(node)
Coverage.statementStart(9353)
                        continue@loop
                    }
Coverage.statementStart(9354)
                }
Coverage.statementStart(9355)
            }
Coverage.statementStart(9356)
            res.add(mutableListOf(node))
Coverage.statementStart(9357)
            variables.add(v)
Coverage.statementStart(9358)
        }
Coverage.statementStart(9359)
        return res
    }
    fun applyOptimisation(nodes: List<OPBase>, root: LOPJoin): OPBase {
Coverage.funStart(9360)
        if (nodes.size > 2) {
Coverage.ifStart(9361)
            var result = LogicalOptimizerJoinOrderStore(nodes, root)
Coverage.statementStart(9362)
            if (result != null) {
Coverage.ifStart(9363)
                return result
            }
Coverage.statementStart(9364)
            result = LogicalOptimizerJoinOrderCostBasedOnHistogram(nodes, root)
Coverage.statementStart(9365)
            if (result != null) {
Coverage.ifStart(9366)
                return result
            }
Coverage.statementStart(9367)
            result = LogicalOptimizerJoinOrderCostBasedOnVariable(nodes, root)
Coverage.statementStart(9368)
            if (result != null) {
Coverage.ifStart(9369)
                return result
            }
Coverage.statementStart(9370)
            throw Exception("unreachable")
        } else if (nodes.size == 2) {
Coverage.statementStart(9371)
            var res = LOPJoin(root.query, nodes[0], nodes[1], false)
Coverage.statementStart(9372)
            res.onlyExistenceRequired = root.onlyExistenceRequired
Coverage.statementStart(9373)
            return res
        } else {
Coverage.statementStart(9374)
            SanityCheck.check { nodes.size == 1 }
Coverage.statementStart(9375)
            return nodes[0]
        }
Coverage.statementStart(9376)
    }
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(9377)
        var res: OPBase = node
Coverage.statementStart(9378)
        if (node is LOPJoin && !node.optional && (parent !is LOPJoin || parent.optional)) {
Coverage.ifStart(9379)
            var originalProvided = node.getProvidedVariableNames()
Coverage.statementStart(9380)
            try {
Coverage.statementStart(9381)
                val allChilds2 = findAllJoinsInChildren(node)
Coverage.statementStart(9382)
                if (allChilds2.size > 2) {
Coverage.ifStart(9383)
                    var result: OPBase? = null
Coverage.statementStart(9384)
                    if (result == null && node.onlyExistenceRequired) {
Coverage.ifStart(9385)
                        //dont not prefer merge join for_ ask-queries, as this makes it harder later, to avoid any materialisation
Coverage.statementStart(9386)
                        result = LogicalOptimizerJoinOrderStore(allChilds2, node)
Coverage.statementStart(9387)
                    }
Coverage.statementStart(9388)
                    if (result == null) {
Coverage.ifStart(9389)
                        val allChilds3 = clusterizeChildren(allChilds2)
Coverage.statementStart(9390)
                        val allChilds4 = mutableListOf<OPBase>()
Coverage.statementStart(9391)
                        for (child in allChilds3) {
Coverage.forLoopStart(9392)
                            allChilds4.add(applyOptimisation(child, node))
Coverage.statementStart(9393)
                        }
Coverage.statementStart(9394)
                        result = applyOptimisation(allChilds4, node)
Coverage.statementStart(9395)
                    }
Coverage.statementStart(9396)
                    if (result != res) {
Coverage.ifStart(9397)
                        onChange()
Coverage.statementStart(9398)
                        if (!originalProvided.containsAll(result.getProvidedVariableNames())) {
Coverage.ifStart(9399)
                            result = LOPProjection(query, originalProvided.map { AOPVariable(query, it) }.toMutableList(), result)
Coverage.statementStart(9400)
                        }
Coverage.statementStart(9401)
                        res = result
Coverage.statementStart(9402)
                    }
Coverage.statementStart(9403)
                }
Coverage.statementStart(9404)
            } catch (e: EmptyResultException) {
Coverage.statementStart(9405)
                res = OPNothing(query, originalProvided)
Coverage.statementStart(9406)
            }
Coverage.statementStart(9407)
        }
Coverage.statementStart(9408)
        return res
    }
}
