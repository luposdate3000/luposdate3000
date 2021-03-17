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

import lupos.s03resultRepresentation.DictionaryExt
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.multiinput.LOPUnion
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPSubGroup

public class LogicalOptimizerRemoveNOOP(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerRemoveNOOPID, "LogicalOptimizerRemoveNOOP") {
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is LOPNOOP || node is LOPSubGroup) {
            onChange()
            res = node.getChildren()[0]
        } else if (node is LOPJoin) {
            if (!node.optional) {
                for (i in node.getChildren().indices) {
                    val c = node.getChildren()[i]
                    if (c is OPNothing) {
                        res = OPNothing(query, node.getProvidedVariableNames())
                        onChange()
                        break
                    } else if (c is OPEmptyRow) {
                        res = node.getChildren()[1 - i]
                        onChange()
                        break
                    }
                }
            } else {
                if (node.getChildren()[0] is OPNothing) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                } else if (node.getChildren()[0] is OPEmptyRow) {
                    res = node.getChildren()[1]
                    onChange()
                } else if (node.getChildren()[1] is OPNothing || node.getChildren()[1] is OPEmptyRow) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
            }
        } else if (node is LOPUnion) {
            if (node.getChildren()[0] is OPNothing) {
                res = node.getChildren()[1]
                onChange()
            } else if (node.getChildren()[1] is OPNothing) {
                res = node.getChildren()[0]
                onChange()
            }
        } else if (node is LOPFilter && node.getChildren()[1] is AOPConstant && (node.getChildren()[1] as AOPConstant).value == DictionaryExt.booleanFalseValue) {
            res = OPNothing(query, node.getProvidedVariableNames())
            onChange()
        } else if (node is LOPMinus) {
            when {
                node.getChildren()[0] is OPNothing -> {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
                node.getChildren()[0] is OPEmptyRow -> {
                    res = node.getChildren()[0]
                    onChange()
                }
                node.getChildren()[1] is OPNothing -> {
                    res = node.getChildren()[0]
                    onChange()
                }
                node.getChildren()[1] is OPEmptyRow -> {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                }
            }
        } else if (node.getChildren().isNotEmpty() && node !is LOPMakeBooleanResult) {
            for (c in node.getChildren()) {
                if (c is OPNothing) {
                    res = OPNothing(query, node.getProvidedVariableNames())
                    onChange()
                    break
                }
            }
        }
        return res
    }
}
