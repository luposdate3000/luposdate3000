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

import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.XMLElement
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class POPLimit public constructor(query: IQuery, projectedVariables: List<String>, @JvmField public val limit: Int, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPLimitID, "POPLimit", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check { children[0].getPartitionCount(variable) == 1 }
        return 1
    }

    override fun toSparql(): String {
        val sparql = children[0].toSparql()
        if (sparql.startsWith("{SELECT ")) {
            return sparql.substring(0, sparql.length - 1) + " LIMIT " + limit + "}"
        }
        return "{SELECT * {$sparql} LIMIT $limit}"
    }

    override fun equals(other: Any?): Boolean = other is POPLimit && limit == other.limit && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = POPLimit(query, projectedVariables, limit, children[0].cloneOP())
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val outMap = mutableMapOf<String, ColumnIterator>()
        val child = children[0].evaluate(parent)
        for (variable in variables) {
            val tmp = object : ColumnIterator() {
                @JvmField
                var count = 0

                @JvmField
                val iterator = child.columns[variable]!!

                @JvmField
                var label = 1
                override /*suspend*/ fun next(): DictionaryValueType {
                    return if (label != 0) {
                        if (count == limit) {
                            _close()
                            DictionaryValueHelper.nullValue
                        } else {
                            count++
                            iterator.next()
                        }
                    } else {
                        DictionaryValueHelper.nullValue
                    }
                }

                @Suppress("NOTHING_TO_INLINE")
                /*suspend*/ inline fun _close() {
                    if (label != 0) {
                        label = 0
                        iterator.close()
                    }
                }

                override /*suspend*/ fun close() {
                    _close()
                }
            }
            outMap[variable] = tmp
        }
        return IteratorBundle(outMap)
    }

    override /*suspend*/ fun toXMLElement(partial: Boolean): XMLElement = super.toXMLElement(partial).addAttribute("limit", "" + limit)
}
