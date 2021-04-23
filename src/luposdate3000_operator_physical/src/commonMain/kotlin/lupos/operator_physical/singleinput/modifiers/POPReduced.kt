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
package lupos.operator.physical.singleinput.modifiers

import lupos.operator.iterator.ColumnIteratorReduced
import lupos.operator.iterator.IteratorBundle
import lupos.operator.iterator.RowIteratorReduced
import lupos.operator.logical.IQuery
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.Partition
import lupos.shared.operator.IOPBase

public class POPReduced public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPReducedID, "POPReduced", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPReduced && children[0] == other.children[0]
    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return "{SELECT REDUCED " + sparql.substring("{SELECT ".length, sparql.length)
        }
        return "{SELECT REDUCED * {$sparql}}"
    }

    override fun cloneOP(): IOPBase = POPReduced(query, projectedVariables, children[0].cloneOP())
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val child = children[0].evaluate(parent)
        return when {
            projectedVariables.size == 1 -> {
                val reduced = ColumnIteratorReduced(child.columns[projectedVariables[0]]!!)
                IteratorBundle(mapOf(projectedVariables[0] to reduced))
            }
            projectedVariables.isNotEmpty() -> {
                val reduced = RowIteratorReduced(child.rows)
                IteratorBundle(reduced)
            }
            else -> {
                child
            }
        }
    }
}
