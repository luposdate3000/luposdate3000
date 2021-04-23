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

import lupos.operator.logical.Query
import lupos.operator.logical.multiinput.LOPMinus
import lupos.operator.logical.singleinput.LOPMakeBooleanResult
import lupos.operator.logical.singleinput.modifiers.LOPDistinct
import lupos.operator.logical.singleinput.modifiers.LOPLimit
import lupos.operator.logical.singleinput.modifiers.LOPOffset
import lupos.operator.logical.singleinput.modifiers.LOPReduced
import lupos.shared.operator.IOPBase

public class LogicalOptimizerExists(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerExistsID, "LogicalOptimizerExists") {
    private fun applyRecoursive(node: IOPBase, askFlag: Boolean) {
        if (node !is LOPLimit && node !is LOPOffset) {
            if (askFlag) {
                node.setPartOfAskQuery(true)
            }
            node.setOnlyExistenceRequired(true)
            if (node is LOPMinus) {
                applyRecoursive(node.getChildren()[0], askFlag)
            } else {
                for (c in node.getChildren()) {
                    applyRecoursive(c, askFlag)
                }
            }
        }
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        if (node is LOPMakeBooleanResult) {
            if (!node.partOfAskQuery) {
                applyRecoursive(node, true)
                onChange()
            }
        } else if (node is LOPDistinct || node is LOPReduced) {
            if (!node.getOnlyExistenceRequired()) {
                applyRecoursive(node, false)
                onChange()
            }
        }
        return node
    }
}
