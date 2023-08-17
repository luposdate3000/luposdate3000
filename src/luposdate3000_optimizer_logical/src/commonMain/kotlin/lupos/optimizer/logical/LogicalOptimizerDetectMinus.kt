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
import lupos.operator.arithmetik.generated.AOPBuildInCallBOUND
import lupos.operator.arithmetik.generated.AOPNot
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPBuildInCallExists
import lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists
import lupos.operator.base.Query
import lupos.operator.logical.multiinput.LOPJoin
import lupos.operator.logical.singleinput.LOPBind
import lupos.operator.logical.singleinput.LOPFilter
import lupos.operator.logical.singleinput.LOPProjection
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase

public class LogicalOptimizerDetectMinus(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDetectMinusID, "LogicalOptimizerDetectMinus") {
    internal companion object {
        internal var ctr = 0
        internal fun getNextVariableName(): String {
            ctr += 1
            return "_LogicalOptimizerDetectMinus$ctr"
        }
    }
    private fun hasNotExists(node: IOPBase, parent: IOPBase, idx: Int): Triple<IOPBase, IOPBase, Int>? {
        if (node is AOPBuildInCallNotExists) {
            return Triple(node.getChildren()[0], parent, idx)
        } else {
            var i = 0
            for (n in node.getChildren()) {
                val res = hasNotExists(n, node, i)
                if (res != null) {
                    return res
                }
            }
        }
        return null
    }
    private fun hasExists(node: IOPBase, parent: IOPBase, idx: Int): Triple<IOPBase, IOPBase, Int>? {
        if (node is AOPBuildInCallExists) {
            return Triple(node.getChildren()[0], parent, idx)
        } else {
            var i = 0
            for (n in node.getChildren()) {
                val res = hasExists(n, node, i)
                if (res != null) {
                    return res
                }
            }
        }
        return null
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPFilter) {
            val existsNotClause = hasNotExists(node.children[1], node, 1)
            if (existsNotClause != null) {
                val indicatorName = getNextVariableName()
                val l = node.children[0]
                val r1 = existsNotClause.first
                val buffer = ByteArrayWrapper()
                DictionaryHelper.booleanToByteArray(buffer, true)
                val r2 = LOPBind(query, AOPVariable(query, indicatorName), AOPConstant(query, buffer), r1)
                val r3 = LOPProjection(query, (l.getProvidedVariableNames() + listOf(indicatorName)).map { AOPVariable(query, it) }.toMutableList(), r2)
                val r4 = LOPDistinct(query, r3)
                val r5 = LOPJoin(query, l, r4, true)
                val cc = existsNotClause.second.getChildren()
                cc[existsNotClause.third] = AOPNot(query, AOPBuildInCallBOUND(query, AOPVariable(query, indicatorName)))
                val r6 = LOPFilter(query, node.children[1] as AOPBase, r5)
                res = LOPProjection(query, l.getProvidedVariableNames().map { AOPVariable(query, it) }.toMutableList(), r6)
                onChange()
            } else {
                val existsClause = hasExists(node.children[1], node, 1)
                if (existsClause != null) {
                    val indicatorName = getNextVariableName()
                    val l = node.children[0]
                    val r1 = existsClause.first
                    val buffer = ByteArrayWrapper()
                    DictionaryHelper.booleanToByteArray(buffer, true)
                    val r2 = LOPBind(query, AOPVariable(query, indicatorName), AOPConstant(query, buffer), r1)
                    val r3 = LOPProjection(query, (l.getProvidedVariableNames() + listOf(indicatorName)).map { AOPVariable(query, it) }.toMutableList(), r2)
                    val r4 = LOPDistinct(query, r3)
                    val r5 = LOPJoin(query, l, r4, true)
                    val cc = existsClause.second.getChildren()
                    cc[existsClause.third] = AOPBuildInCallBOUND(query, AOPVariable(query, indicatorName))
                    val r6 = LOPFilter(query, node.children[1] as AOPBase, r5)
                    res = LOPProjection(query, l.getProvidedVariableNames().map { AOPVariable(query, it) }.toMutableList(), r6)
                    onChange()
                }
            }
        }
        return res
    }
}
