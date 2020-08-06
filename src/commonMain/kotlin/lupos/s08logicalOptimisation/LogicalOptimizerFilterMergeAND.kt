package lupos.s08logicalOptimisation

import lupos.s00misc.BugException
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.SanityCheck
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPAnd
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCOALESCE
import lupos.s04arithmetikOperators.multiinput.AOPOr
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallBOUND
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.singleinput.LOPFilter

class LogicalOptimizerFilterMergeAND(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerFilterMergeANDID) {
    override val classname = "LogicalOptimizerFilterMergeAND"
    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit): OPBase {
        var res: OPBase = node
        if (node is LOPFilter) {
            val child = node.children[0]
            if (child is LOPFilter) {
                if (node.dontSplitFilter == 0 && child.dontSplitFilter == 0) {
                    res = LOPFilter(query, AOPAnd(query, node.children[1] as AOPBase, child.children[1] as AOPBase), child.children[0])
                    onChange()
                } else {
                    SanityCheck.check { node.dontSplitFilter == 0 || child.dontSplitFilter == 0 }
                    SanityCheck.check { node.dontSplitFilter == 1 || child.dontSplitFilter == 1 }
                    val a: AOPBase
                    val b: AOPBase
                    if (node.dontSplitFilter < child.dontSplitFilter) {
                        a = node.children[1] as AOPBase
                        b = child.children[1] as AOPBase
                    } else {
                        a = child.children[1] as AOPBase
                        b = node.children[1] as AOPBase
                    }
                    SanityCheck.check { b is AOPOr }
                    val c = b.children[0] as AOPBase
                    SanityCheck.check { c is AOPAnd }
                    val d = c.children[1] as AOPBase
                    SanityCheck.check { d is AOPBuildInCallCOALESCE }
                    val e = d.children[0] as AOPBase //original-filter
                    if (a is AOPBuildInCallBOUND) {
                        //TODO check if that bound is one of the options for this optional block
                        res = LOPFilter(query, c, child.children[0])
                        res.dontSplitFilter = 2
                        onChange()
                    } else if (a is AOPNot && a.children[0] is AOPBuildInCallBOUND) {
                        //TODO check if that bound is one of the options for this optional block
                        res = LOPFilter(query, AOPOr(query, a, AOPNot(query, d)), child.children[0])
                        res.dontSplitFilter = 2
                        onChange()
                    } else if (containsBound(a)) {
                        throw BugException("not evaluated", "dont know what happens here?? debug later if it happens")
                    } else {
                        res = LOPFilter(query, AOPAnd(query, node.children[1] as AOPBase, child.children[1] as AOPBase), child.children[0])
                        onChange()
                    }
                }
            }
        }
        return res
    }

    fun containsBound(filter: AOPBase): Boolean {
        if (filter is AOPBuildInCallBOUND) {
            return true
        }
        for (f in filter.children) {
            if (containsBound(f as AOPBase)) {
                return true
            }
        }
        return false
    }
}
