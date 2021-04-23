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

import lupos.dictionary.DictionaryExt
import lupos.operator_arithmetik.noinput.AOPConstant
import lupos.operator_logical.IOPBase
import lupos.operator_logical.Query
import lupos.operator_logical.multiinput.LOPJoin
import lupos.operator_logical.multiinput.LOPMinus
import lupos.operator_logical.multiinput.LOPUnion
import lupos.operator_logical.noinput.OPEmptyRow
import lupos.operator_logical.noinput.OPNothing
import lupos.operator_logical.singleinput.LOPFilter
import lupos.operator_logical.singleinput.LOPMakeBooleanResult
import lupos.operator_logical.singleinput.LOPNOOP
import lupos.operator_logical.singleinput.LOPSubGroup

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
