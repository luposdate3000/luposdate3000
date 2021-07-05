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
package lupos.operator.physical.singleinput

import lupos.operator.arithmetik.noinput.AOPVariable
import lupos.operator.physical.POPBase
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public class POPProjection public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPProjectionID, "POPProjection", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = children[0].getPartitionCount(variable)
    override fun toSparql(): String {
        var res = "{SELECT "
        for (c in projectedVariables) {
            res += AOPVariable(query, c).toSparql() + " "
        }
        res += "{"
        res += children[0].toSparql()
        res += "}}"
        return res
    }

    override fun cloneOP(): IOPBase = POPProjection(query, projectedVariables, children[0].cloneOP())
    override fun equals(other: Any?): Boolean = other is POPProjection && projectedVariables == other.projectedVariables && children[0] == other.children[0]
    override fun getProvidedVariableNamesInternal(): List<String> = projectedVariables
    override fun getRequiredVariableNames(): List<String> = projectedVariables
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val variables = getProvidedVariableNames()
        val child = children[0].evaluate(parent)
        val outMap = mutableMapOf<String, ColumnIterator>()
        when {
            variables.containsAll(children[0].getProvidedVariableNames()) -> {
                return child
            }
            variables.isEmpty() -> {
                val variables2 = children[0].getProvidedVariableNames()
                SanityCheck(
                    { /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ },
                    {
                        SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { variables2.isNotEmpty() })
                        for (variable in variables2) {
                            SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { child.columns[variable] != null })
                        }
                    }
                )
                val column = child.columns[variables2[0]]!!
                return object : IteratorBundle(0) {
                    override /*suspend*/ fun hasNext2(): Boolean {
                        return column.next() != DictionaryValueHelper.nullValue
                    }

                    override /*suspend*/ fun hasNext2Close() {
                        column.close()
                    }
                }
            }
            else -> {
                for (variable in variables) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/""/*SOURCE_FILE_END*/ }, { child.columns[variable] != null })
                    outMap[variable] = child.columns[variable]!!
                }
                return IteratorBundle(outMap)
            }
        }
    }
}
