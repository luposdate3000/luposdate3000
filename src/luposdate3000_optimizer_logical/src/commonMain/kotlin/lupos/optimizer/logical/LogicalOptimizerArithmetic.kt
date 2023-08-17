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
import lupos.operator.arithmetik.AOPAggregationBase
import lupos.operator.arithmetik.AOPBase
import lupos.operator.arithmetik.multiinput.AOPSet
import lupos.operator.arithmetik.noinput.AOPConstant
import lupos.operator.arithmetik.noinput.AOPValue
import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.arithmetik.singleinput.AOPBuildInCallExists
import lupos.operator.arithmetik.singleinput.AOPBuildInCallNotExists
import lupos.operator.base.Query
import lupos.shared.DictionaryValueHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle

public class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerArithmeticID, "LogicalOptimizerArithmetic") {
    private fun hasAggregation(node: IOPBase): Boolean {
        for (n in node.getChildren()) {
            if (hasAggregation(n)) {
                return true
            }
        }
        return node is AOPAggregationBase
    }

    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res = node
        if (node is AOPBase && node !is AOPValue && node !is AOPBuildInCallNotExists && node !is AOPBuildInCallExists && node !is AOPVariable && node !is AOPSet) {
            if (node.getChildren().isNotEmpty() && node.getRequiredVariableNamesRecoursive().isEmpty() && !hasAggregation(node)) {
                var value = node.evaluateID(IteratorBundle(0))()
                if (value == DictionaryValueHelper.errorValue) {
                    value = DictionaryValueHelper.undefValue
                }
                res = AOPConstant(query, value)
                onChange()
            }
        }
        return res
    }
}
