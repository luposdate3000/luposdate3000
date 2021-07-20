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

/*

 Needed for the visualization process
 Will be added between each operator during the optimization process and will send
 the data that is passing through the operator to the visualization framework.
 Code is based of the Debug Operator.

*/

package lupos.operator.physical.singleinput

import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.IVisualisation
import lupos.shared.Partition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public class POPVisualisation public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPDebugID, "POPVisualisation", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    public var visualTest: IVisualisation? = null

    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPVisualisation && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase = POPVisualisation(query, projectedVariables, getChildren()[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = getChildren()[0].toSparql()
    override fun evaluate(parent: Partition): IteratorBundle {
        val child = getChildren()[0].evaluate(parent)
        val rowMode = child.rows.columns.toMutableList()
        val target = getChildren()[0].getProvidedVariableNames()
        rowMode.containsAll(target)
        target.containsAll(rowMode)
        // Map Column Iterator
        val iterator = RowIterator()
        var counter = 0
        iterator.columns = child.rows.columns
        val buffer = ByteArrayWrapper()
        iterator.next = {
            val res = child.rows.next()
            iterator.buf = child.rows.buf
            if (res >= 0) {
                // For each Column the data is received from the Dictionary and send to the
                // visualization framework.
                counter++
                // Columns auf ein mal senden
                for (j in 0 until iterator.columns.size) {
                    query.getDictionary().getValue(buffer, iterator.buf[res + j])
                    val string = "?" + this.projectedVariables[j] + " = " + DictionaryHelper.byteArrayToSparql(buffer).replace("\\", "\\\\").replace("\"", "\\\"")
                    var outputString = "[" + getChildren()[0].getVisualUUUID().toString() + ","
                    outputString += getParent().getVisualUUUID().toString() + ","
                    outputString += "\"$string\","
                    outputString += iterator.buf[res + j].toString() + "]"
                    visualTest!!.sendData(outputString)
                }
            }
            res
        }
        iterator.close = {
            child.rows.close()
            iterator._close()
        }
        return IteratorBundle(iterator)
    }
    public open override fun usesDictionary(): Boolean {
        return true
    }
}
