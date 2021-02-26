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

import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPProjection
import lupos.s04logicalOperators.singleinput.modifiers.LOPLimit
import lupos.s04logicalOperators.singleinput.modifiers.LOPOffset

public class LogicalOptimizerBindUp(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerBindUpID, "LogicalOptimizerBindUp") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPBind) {
            if (node.getChildren()[1] !is AOPConstant) {
                val child = node.getChildren()[0]
                if (child is LOPBind) {
                    if (child.getChildren()[1] is AOPConstant) {
                        if (!node.getChildren()[1].getRequiredVariableNamesRecoursive().contains(child.name.name)) {
                            node.getChildren()[0] = child.getChildren()[0]
                            child.getChildren()[0] = node
                            res = child
                            onChange()
                        }
                    }
                }
            }
        } else if (node is LOPFilter) {
            val child0 = node.getChildren()[0]
            if (child0 is LOPBind) {
                val child01 = child0.getChildren()[1]
                if (child01 is AOPConstant) {
                    node.getChildren()[1].replaceVariableWithConstant(child0.name.name, child01.value)
                    node.getChildren()[0] = child0.getChildren()[0]
                    child0.getChildren()[0] = node
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPProjection) {
            val child0 = node.getChildren()[0]
            if (child0 is LOPBind) {
                val variables = node.variables.map { it.name }.toMutableList()
                if (variables.contains(child0.name.name) && variables.containsAll(child0.getRequiredVariableNames())) {
                    variables.remove(child0.name.name)
                    child0.getChildren()[0] = LOPProjection(query, variables.map { AOPVariable(query, it) }.toMutableList(), child0.getChildren()[0])
                    res = child0
                    onChange()
                }
            }
        } else if (node is LOPMinus) {
            val child = node.getChildren()[0]
            if (child is LOPBind && child.getChildren()[1] is AOPConstant) {
                node.getChildren()[0] = child.getChildren()[0]
                child.getChildren()[0] = node
                res = child
                onChange()
            }
        } else if (node is LOPLimit || node is LOPOffset || node is LOPJoin) {
            for (i in node.getChildren().indices) {
                val child = node.getChildren()[i]
                if (child is LOPBind && child.getChildren()[1] is AOPConstant) {
                    node.getChildren()[i] = child.getChildren()[0]
                    child.getChildren()[0] = node
                    res = child
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
