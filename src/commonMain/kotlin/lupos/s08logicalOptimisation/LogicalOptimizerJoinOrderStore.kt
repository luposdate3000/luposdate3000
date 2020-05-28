package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s08logicalOptimisation.OptimizerBase
object LogicalOptimizerJoinOrderStore {
    operator fun invoke(allChilds: List<OPBase>, root: LOPJoin): OPBase? {
Coverage.funStart(9409)
        SanityCheck.check { allChilds.size > 2 }
Coverage.statementStart(9410)
        if (root.onlyExistenceRequired) {
Coverage.ifStart(9411)
            SanityCheck {
Coverage.statementStart(9412)
                for (c in allChilds) {
Coverage.forLoopStart(9413)
                    SanityCheck.check { c.onlyExistenceRequired }
Coverage.statementStart(9414)
                }
Coverage.statementStart(9415)
            }
Coverage.statementStart(9416)
            val queue = mutableListOf<OPBase>()
Coverage.statementStart(9417)
            queue.addAll(allChilds)
Coverage.statementStart(9418)
            var lastVariable = 0
Coverage.statementStart(9419)
            var lastVariableCount = Int.MAX_VALUE
Coverage.statementStart(9420)
            for (i in queue.indices) {
Coverage.forLoopStart(9421)
                val tmp = queue[i].getProvidedVariableNames().size
Coverage.statementStart(9422)
                if (tmp < lastVariableCount || (tmp == lastVariableCount && queue[i].getHistogram().count < queue[lastVariable].getHistogram().count)) {
Coverage.ifStart(9423)
                    lastVariableCount = tmp
Coverage.statementStart(9424)
                    lastVariable = i
Coverage.statementStart(9425)
                }
Coverage.statementStart(9426)
            }
Coverage.statementStart(9427)
            val lastChild = queue.removeAt(lastVariable)
Coverage.statementStart(9428)
            val allVariables = mutableListOf<String>()
Coverage.statementStart(9429)
            val allVariablesCounters = mutableListOf<Int>()
Coverage.statementStart(9430)
            for (i in queue.indices) {
Coverage.forLoopStart(9431)
                val tmp = queue[i].getProvidedVariableNames()
Coverage.statementStart(9432)
                for (t in tmp) {
Coverage.forLoopStart(9433)
                    if (!allVariables.contains(t)) {
Coverage.ifStart(9434)
                        allVariables.add(t)
Coverage.statementStart(9435)
                        allVariablesCounters.add(1)
Coverage.statementStart(9436)
                    } else {
Coverage.ifStart(9437)
                        for (j in allVariables.indices) {
Coverage.forLoopStart(9438)
                            if (allVariables[j] == t) {
Coverage.ifStart(9439)
                                allVariablesCounters[j]++
Coverage.statementStart(9440)
                                break
                            }
Coverage.statementStart(9441)
                        }
Coverage.statementStart(9442)
                    }
Coverage.statementStart(9443)
                }
Coverage.statementStart(9444)
            }
Coverage.statementStart(9445)
            var allVariablesOrdered = mutableListOf<String>()
Coverage.statementStart(9446)
            allVariablesOrdered.addAll(lastChild.getProvidedVariableNames())
Coverage.statementStart(9447)
            val result = mutableListOf<OPBase>()
Coverage.statementStart(9448)
            while (queue.size > 0) {
Coverage.whileLoopStart(9449)
                var max = -1
Coverage.statementStart(9450)
                var maxI = 0
Coverage.statementStart(9451)
                var i = 0
Coverage.statementStart(9452)
                while (i < queue.size) {
Coverage.whileLoopStart(9453)
                    var provided = queue[i].getProvidedVariableNames()
Coverage.statementStart(9454)
                    var score = provided.size
Coverage.statementStart(9455)
                    loop@ for (p in provided) {
Coverage.forLoopStart(9456)
                        for (s in allVariablesOrdered.indices) {
Coverage.forLoopStart(9457)
                            if (p == allVariablesOrdered[s]) {
Coverage.ifStart(9458)
                                score += provided.size - s + 100
Coverage.statementStart(9459)
                                continue@loop
                            }
Coverage.statementStart(9460)
                        }
Coverage.statementStart(9461)
                    }
Coverage.statementStart(9462)
                    if (score > max) {
Coverage.ifStart(9463)
                        maxI = i
Coverage.statementStart(9464)
                        max = score
Coverage.statementStart(9465)
                    }
Coverage.statementStart(9466)
                    i++
Coverage.statementStart(9467)
                }
Coverage.statementStart(9468)
                var node = queue.removeAt(maxI)
Coverage.statementStart(9469)
                var tmp = mutableListOf<String>()//push the variables of this node the the front of preferred variables ... this way, consecutive joins use the same variables.
Coverage.statementStart(9470)
                tmp.addAll(node.getProvidedVariableNames())
Coverage.statementStart(9471)
                allVariablesOrdered.removeAll(tmp)
Coverage.statementStart(9472)
                tmp.addAll(allVariablesOrdered)
Coverage.statementStart(9473)
                allVariablesOrdered.clear()
Coverage.statementStart(9474)
                allVariablesOrdered.addAll(tmp)
Coverage.statementStart(9475)
                result.add(node)
Coverage.statementStart(9476)
            }
Coverage.statementStart(9477)
            var res = lastChild
Coverage.statementStart(9478)
            while (result.size > 0) {
Coverage.whileLoopStart(9479)
                val b = result.removeAt(0)
Coverage.statementStart(9480)
                res = LOPJoin(root.query, res, b, false)
Coverage.statementStart(9481)
                res.onlyExistenceRequired = true
Coverage.statementStart(9482)
            }
Coverage.statementStart(9483)
            return res
        }
Coverage.statementStart(9484)
        return null
    }
}
