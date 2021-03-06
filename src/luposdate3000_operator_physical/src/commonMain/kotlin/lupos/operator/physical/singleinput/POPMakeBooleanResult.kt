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
import lupos.operator.base.iterator.ColumnIteratorRepeatValue
import lupos.operator.base.noinput.OPEmptyRow
import lupos.operator.physical.POPBase
import lupos.operator.physical.noinput.POPNothing
import lupos.shared.DictionaryValueHelper
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.SanityCheck
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.ColumnIterator
import lupos.shared.operator.iterator.IteratorBundle

public class POPMakeBooleanResult public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPMakeBooleanResultID, "POPMakeBooleanResult", arrayOf(child), ESortPriorityExt.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/singleinput/POPMakeBooleanResult.kt:33"/*SOURCE_FILE_END*/ }, { children[0].getPartitionCount(variable) == 1 })
        return 1
    }

    override fun equals(other: Any?): Boolean = other is POPMakeBooleanResult && children[0] == other.children[0]
    override fun toSparqlQuery(): String = "ASK{" + children[0].toSparql() + "}"
    override fun cloneOP(): IOPBase = POPMakeBooleanResult(query, projectedVariables, children[0].cloneOP())
    override fun getProvidedVariableNamesInternal(): MutableList<String> = mutableListOf("?boolean")
    override fun getRequiredVariableNames(): List<String> = listOf()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        val flag: Boolean
        val outMap = mutableMapOf<String, ColumnIterator>()
        val variables = children[0].getProvidedVariableNames()
        if (children[0] is POPNothing) {
            flag = false
        } else if (children[0] is OPEmptyRow) {
            flag = true
        } else {
            val child = children[0].evaluate(parent)
            if (variables.isNotEmpty()) {
                flag = child.columns[variables[0]]!!.next() != DictionaryValueHelper.nullValue
                for (variable in variables) {
                    child.columns[variable]!!.close()
                }
            } else {
                flag = child.hasNext2()
                child.hasNext2Close()
            }
        }
        val value = if (flag) {
            DictionaryValueHelper.booleanTrueValue
        } else {
            DictionaryValueHelper.booleanFalseValue
        }
        outMap["?boolean"] = ColumnIteratorRepeatValue(1, value)
        return IteratorBundle(outMap)
    }
}
