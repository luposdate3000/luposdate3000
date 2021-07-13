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
package lupos.operator.physical.noinput

import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public class POPNothing public constructor(query: IQuery, projectedVariables: List<String>) : POPBase(query, projectedVariables, EOperatorIDExt.POPNothingID, "POPNothing", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1
    override fun getProvidedVariableNames(): List<String> = projectedVariables
    override fun toSparql(): String = "{}"
    override fun equals(other: Any?): Boolean = other is POPNothing
    override fun cloneOP(): IOPBase = this
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        for (variable in variables) {
            val tmp = object : ColumnIterator() {
                override /*suspend*/ fun next(): DictionaryValueType {
                    return DictionaryValueHelper.nullValue
                }
                override /*suspend*/ fun close() {
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }
}
