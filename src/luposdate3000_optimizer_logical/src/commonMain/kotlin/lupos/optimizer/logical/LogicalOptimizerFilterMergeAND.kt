/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package lupos.optimizer.logical

import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.generated.AOPAnd
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.generated.AOPOr
import lupos.operator.arithmetik.multiinput.AOPBuildInCallCOALESCE
import lupos.operator.base.Query
import lupos.operator.logical.singleinput.LOPFilter
import lupos.shared.BugException
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase

public class LogicalOptimizerFilterMergeAND(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerFilterMergeANDID, "LogicalOptimizerFilterMergeAND") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val child = node.getChildren()[0]
            if (child is LOPFilter) {
                if (node.dontSplitFilter == 0 && child.dontSplitFilter == 0) {
                    res = LOPFilter(query, AOPAnd(query, node.getChildren()[1] as AOPBase, child.getChildren()[1] as AOPBase), child.getChildren()[0])
                    onChange()
                } else {
                    if (SanityCheck.enabled) { if (!(node.dontSplitFilter == 0 || child.dontSplitFilter == 0)) { throw Exception("SanityCheck failed") } }
                    if (SanityCheck.enabled) { if (!(node.dontSplitFilter == 1 || child.dontSplitFilter == 1)) { throw Exception("SanityCheck failed") } }
                    val a: AOPBase
                    val b: AOPBase
                    if (node.dontSplitFilter < child.dontSplitFilter) {
                        a = node.getChildren()[1] as AOPBase
                        b = child.getChildren()[1] as AOPBase
                    } else {
                        a = child.getChildren()[1] as AOPBase
                        b = node.getChildren()[1] as AOPBase
                    }
                    if (SanityCheck.enabled) { if (!(b is AOPOr)) { throw Exception("SanityCheck failed") } }
                    val c = b.getChildren()[0] as AOPBase
                    if (SanityCheck.enabled) { if (!(c is AOPAnd)) { throw Exception("SanityCheck failed") } }
                    val d = c.getChildren()[1] as AOPBase
                    if (SanityCheck.enabled) { if (!(d is AOPBuildInCallCOALESCE)) { throw Exception("SanityCheck failed") } }
                    if (a is AOPBuildInCallBOUND) {
                        // TODO check if that bound is one of the options for this optional block
                        res = LOPFilter(query, c, child.getChildren()[0])
                        res.dontSplitFilter = 2
                        onChange()
                    } else if (a is AOPNot && a.getChildren()[0] is AOPBuildInCallBOUND) {
                        // TODO check if that bound is one of the options for this optional block
                        res = LOPFilter(query, AOPOr(query, a, AOPNot(query, d)), child.getChildren()[0])
                        res.dontSplitFilter = 2
                        onChange()
                    } else if (containsBound(a)) {
                        throw BugException("not evaluated", "dont know what happens here?? debug later if it happens")
                    } else {
                        res = LOPFilter(query, AOPAnd(query, node.getChildren()[1] as AOPBase, child.getChildren()[1] as AOPBase), child.getChildren()[0])
                        onChange()
                    }
                }
            }
        }
        return res
    }

    private fun containsBound(filter: AOPBase): Boolean {
        if (filter is AOPBuildInCallBOUND) {
            return true
        }
        for (f in filter.getChildren()) {
            if (containsBound(f as AOPBase)) {
                return true
            }
        }
        return false
    }
}
