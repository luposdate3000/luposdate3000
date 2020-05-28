package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s08logicalOptimisation.OptimizerBase
class LogicalOptimizerFilterDown(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterDownID) {
    override val classname = "LogicalOptimizerFilterDown"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8995)
        var res: OPBase = node
Coverage.statementStart(8996)
        if (node is LOPFilter) {
Coverage.ifStart(8997)
            var child = node.children[0]
Coverage.statementStart(8998)
            if (child is LOPMinus) {
Coverage.ifStart(8999)
                if (child.children[0].getProvidedVariableNames().containsAll(node.children[1].getRequiredVariableNamesRecoursive()) && child.tmpFakeVariables.intersect(node.children[1].getRequiredVariableNamesRecoursive()).size == 0) {
Coverage.ifStart(9000)
                    child.children[0] = LOPFilter(query, node.children[1] as AOPBase, child.children[0])
Coverage.statementStart(9001)
                    res = child
Coverage.statementStart(9002)
                    onChange()
Coverage.statementStart(9003)
                } else if (child.children[1].getProvidedVariableNames().containsAll(node.children[1].getRequiredVariableNamesRecoursive())) {
Coverage.ifStart(9004)
                    child.children[1] = LOPFilter(query, node.children[1] as AOPBase, child.children[1])
Coverage.statementStart(9005)
                    res = child
Coverage.statementStart(9006)
                    onChange()
Coverage.statementStart(9007)
                }
Coverage.statementStart(9008)
            } else {
Coverage.ifStart(9009)
                val filters = mutableListOf(node.children[1] as AOPBase)
Coverage.statementStart(9010)
                while (child is LOPFilter) {
Coverage.whileLoopStart(9011)
                    val filter = child.children[1] as AOPBase
Coverage.statementStart(9012)
                    var found = false
Coverage.statementStart(9013)
                    for (f in filters) {
Coverage.forLoopStart(9014)
                        if (f == filter) {
Coverage.ifStart(9015)
                            found == true
Coverage.statementStart(9016)
                            break
                        }
Coverage.statementStart(9017)
                    }
Coverage.statementStart(9018)
                    if (!found) {
Coverage.ifStart(9019)
                        filters.add(filter)
Coverage.statementStart(9020)
                    }
Coverage.statementStart(9021)
                    child = child.children[0]
Coverage.statementStart(9022)
                }
Coverage.statementStart(9023)
                if (child is LOPUnion) {
Coverage.ifStart(9024)
                    var a = child.children[0]
Coverage.statementStart(9025)
                    var b = child.children[1]
Coverage.statementStart(9026)
                    for (filterIndex in 0 until filters.size) {
Coverage.forLoopStart(9027)
                        val filter = filters[filterIndex]
Coverage.statementStart(9028)
                        a = LOPFilter(query, filter, a)
Coverage.statementStart(9029)
                        b = LOPFilter(query, filter, b)
Coverage.statementStart(9030)
                        res = LOPUnion(query, a, b)
Coverage.statementStart(9031)
                        onChange()
Coverage.statementStart(9032)
                    }
Coverage.statementStart(9033)
                } else if (child !is LOPGroup && child.children.size > 0) {
Coverage.ifStart(9034)
                    var flag = true
Coverage.statementStart(9035)
                    if (child is LOPJoin) {
Coverage.ifStart(9036)
                        if (child.optional) {
Coverage.ifStart(9037)
                            var provided = child.children[1].getProvidedVariableNames()
Coverage.statementStart(9038)
                            for (filter in filters) {
Coverage.forLoopStart(9039)
                                for (v in filter.getRequiredVariableNamesRecoursive()) {
Coverage.forLoopStart(9040)
                                    if (provided.contains(v)) {
Coverage.ifStart(9041)
//prevent any filters from beeing pulled down _if those require variables out of the optional join-part
Coverage.statementStart(9042)
                                        flag = false
Coverage.statementStart(9043)
                                        break
                                    }
Coverage.statementStart(9044)
                                }
Coverage.statementStart(9045)
                            }
Coverage.statementStart(9046)
                        }
Coverage.statementStart(9047)
                    }
Coverage.statementStart(9048)
                    if (flag) {
Coverage.ifStart(9049)
                        loop@ for (targetIndex in 0 until child.children.size) {
Coverage.forLoopStart(9050)
                            val target = child.children[targetIndex]
Coverage.statementStart(9051)
                            for (filterIndex in 0 until filters.size) {
Coverage.forLoopStart(9052)
                                val filter = filters[filterIndex]
Coverage.statementStart(9053)
                                if (target.getProvidedVariableNames().containsAll(filter.getRequiredVariableNamesRecoursive())) {
Coverage.ifStart(9054)
                                    child.children[targetIndex] = LOPFilter(query, filter, target)
Coverage.statementStart(9055)
                                    filters.removeAt(filterIndex)
Coverage.statementStart(9056)
                                    res = child
Coverage.statementStart(9057)
                                    for (filter2 in filters) {
Coverage.forLoopStart(9058)
                                        res = LOPFilter(query, filter2, res)
Coverage.statementStart(9059)
                                    }
Coverage.statementStart(9060)
                                    onChange()
Coverage.statementStart(9061)
                                    break@loop
                                }
Coverage.statementStart(9062)
                            }
Coverage.statementStart(9063)
                        }
Coverage.statementStart(9064)
                    }
Coverage.statementStart(9065)
                }
Coverage.statementStart(9066)
            }
Coverage.statementStart(9067)
        }
Coverage.statementStart(9068)
        return res
    }
}
