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
package lupos.s08logicalOptimisation
import lupos.s00misc.EOptimizerIDExt
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.multiinput.LOPMinus
import lupos.s04logicalOperators.singleinput.LOPBind
public class LogicalOptimizerDetectMinusStep2(query: Query) : OptimizerBase(query, EOptimizerIDExt.LogicalOptimizerDetectMinusStep2ID) {
    override val classname: String = "LogicalOptimizerDetectMinusStep2"
    override /*suspend*/ fun optimize(node: IOPBase, parent: IOPBase?, onChange: () -> Unit): IOPBase {
        var res: IOPBase = node
        if (node is LOPMinus) {
            val tmp = node.tmpFakeVariables.toMutableSet()
            tmp.removeAll(node.getChildren()[0].getProvidedVariableNames())
            if (tmp.size > 0) {
                for (v in tmp) {
                    res = LOPBind(query, AOPVariable(query, v), AOPConstant(query, ValueUndef()), res)
                }
                onChange()
                node.tmpFakeVariables = listOf()
            }
        }
        return res
    }
}
