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
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.MemoryTable
import lupos.shared.Partition
import lupos.shared.XMLElement
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public open class POPValues2(query: IQuery, @JvmField public val data: MemoryTable) : POPBase(query, data.columns.toList(), EOperatorIDExt.POPValuesID, "POPValues", arrayOf(), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = 1

    override fun toSparql(): String {
        throw Exception("not implemented")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is POPValues2) {
            return false
        }
        throw Exception("not implemented")
    }

    override fun cloneOP(): POPValues2 {
        return POPValues2(query, data)
    }

    override fun getProvidedVariableNamesInternal(): List<String> = data.columns.distinct()
    override fun getRequiredVariableNames(): MutableList<String> = mutableListOf()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        throw Exception("not implemented")
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement {
        throw Exception("not implemented")
    }
}
