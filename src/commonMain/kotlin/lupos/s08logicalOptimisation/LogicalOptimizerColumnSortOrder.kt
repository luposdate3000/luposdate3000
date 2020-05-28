package lupos.s08logicalOptimisation
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s00misc.SortHelper
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LogicalOptimizerColumnSortOrder(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerColumnSortOrderID) {
    override val classname = "LogicalOptimizerColumnSortOrder"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
Coverage.funStart(8806)
        var res: OPBase = node
Coverage.statementStart(8807)
        var hadChange = false
Coverage.statementStart(8808)
        SanityCheck {
Coverage.statementStart(8809)
            if (parent != null) {
Coverage.ifStart(8810)
                var found = false
Coverage.statementStart(8811)
                for (c in parent.children) {
Coverage.forLoopStart(8812)
                    if (c === node) {
Coverage.ifStart(8813)
                        found = true
Coverage.statementStart(8814)
                        break
                    }
Coverage.statementStart(8815)
                }
Coverage.statementStart(8816)
                require(found)
Coverage.statementStart(8817)
            }
Coverage.statementStart(8818)
        }
Coverage.statementStart(8819)
        var done = node.initializeSortPriorities {
Coverage.statementStart(8820)
            hadChange = true
Coverage.statementStart(8821)
            onChange()
Coverage.statementStart(8822)
        }
Coverage.statementStart(8823)
        if (!hadChange && !done) {
Coverage.ifStart(8824)
            for (c in node.children) {
Coverage.forLoopStart(8825)
                if (c.sortPriorities.size > 1 && c !is LOPTriple) {
Coverage.ifStart(8826)
                    done = true
Coverage.statementStart(8827)
                    break
                }
Coverage.statementStart(8828)
            }
Coverage.statementStart(8829)
            if (!done) {
Coverage.ifStart(8830)
                var flag = true
Coverage.statementStart(8831)
                if (node is LOPTriple && parent != null) {
Coverage.ifStart(8832)
                    if (!parent.sortPrioritiesInitialized || parent.sortPriorities.size > 1) {
Coverage.ifStart(8833)
                        //let the parent-operator choose first ..
Coverage.statementStart(8834)
                        flag = false
Coverage.statementStart(8835)
                    }
Coverage.statementStart(8836)
                }
Coverage.statementStart(8837)
                if (flag) {
Coverage.ifStart(8838)
                    var maxSize = 0
Coverage.statementStart(8839)
                    if (node.children.size > 0 && node !is LOPTriple) {
Coverage.ifStart(8840)
                        //filter only valid sort orders based on children, which may had an update
Coverage.statementStart(8841)
                        var tmp = mutableListOf<List<SortHelper>>()
Coverage.statementStart(8842)
                        loop@ for (x in node.sortPriorities) {
Coverage.forLoopStart(8843)
                            var maxI = 0
Coverage.statementStart(8844)
                            for (c in node.children) {
Coverage.forLoopStart(8845)
                                loop2@ for (p in c.sortPriorities) {
Coverage.forLoopStart(8846)
                                    var i = 0
Coverage.statementStart(8847)
                                    while (i < x.size && i < p.size) {
Coverage.whileLoopStart(8848)
                                        if (x[i] != p[i]) {
Coverage.ifStart(8849)
                                            if (i > maxI) {
Coverage.ifStart(8850)
                                                maxI = i
Coverage.statementStart(8851)
                                            }
Coverage.statementStart(8852)
                                            continue@loop2
                                        }
Coverage.statementStart(8853)
                                        i++
Coverage.statementStart(8854)
                                    }
Coverage.statementStart(8855)
                                    tmp.add(x)
Coverage.statementStart(8856)
                                    continue@loop
                                }
Coverage.statementStart(8857)
                            }
Coverage.statementStart(8858)
                            if (maxI > 0) {
Coverage.ifStart(8859)
                                var y = mutableListOf<SortHelper>()
Coverage.statementStart(8860)
                                for (i in 0 until maxI) {
Coverage.forLoopStart(8861)
                                    y.add(x[i])
Coverage.statementStart(8862)
                                }
Coverage.statementStart(8863)
                                tmp.add(y)
Coverage.statementStart(8864)
                            }
Coverage.statementStart(8865)
                        }
Coverage.statementStart(8866)
                        if (node.sortPriorities != tmp) {
Coverage.ifStart(8867)
                            node.sortPriorities = tmp
Coverage.statementStart(8868)
                            onChange()
Coverage.statementStart(8869)
                        }
Coverage.statementStart(8870)
                    }
Coverage.statementStart(8871)
                    for (x in node.sortPriorities) {
Coverage.forLoopStart(8872)
                        if (x.size > maxSize) {
Coverage.ifStart(8873)
                            maxSize = x.size
Coverage.statementStart(8874)
                        }
Coverage.statementStart(8875)
                    }
Coverage.statementStart(8876)
                    if (maxSize > 0) {
Coverage.ifStart(8877)
                        for (x in node.sortPriorities) {
Coverage.forLoopStart(8878)
                            if (x.size == maxSize) {
Coverage.ifStart(8879)
                                node.selectSortPriority(x)
Coverage.statementStart(8880)
                                break
                            }
Coverage.statementStart(8881)
                        }
Coverage.statementStart(8882)
                    }
Coverage.statementStart(8883)
                }
Coverage.statementStart(8884)
            }
Coverage.statementStart(8885)
        }
Coverage.statementStart(8886)
        return res
    }
}
