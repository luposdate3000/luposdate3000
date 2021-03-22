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
import lupos.s04arithmetikOperators.AOPAggregationBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallExists
import lupos.s04arithmetikOperators.singleinput.AOPBuildInCallNotExists
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.iterator.IteratorBundle

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
        if (node is AOPBase && node !is AOPValue && node !is AOPBuildInCallNotExists && node !is AOPBuildInCallExists && node !is AOPVariable) {
            if (node.getChildren().isNotEmpty() && node.getRequiredVariableNamesRecoursive().isEmpty() && !hasAggregation(node)) {
                var value = node.evaluateID(IteratorBundle(0))()
                if (value == DictionaryExt.errorValue) {
                    value = DictionaryExt.undefValue
                }
                res = AOPConstant(query, value)
                onChange()
            }
        }
        return res
    }

}
