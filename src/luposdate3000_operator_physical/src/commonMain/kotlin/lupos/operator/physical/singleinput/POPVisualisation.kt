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

import lupos.Luposdate3000_Operator_Physical.MyOutputStream
import lupos.operator.physical.POPBase
import lupos.shared.EOperatorIDExt
import lupos.shared.ESortPriorityExt
import lupos.shared.IQuery
import lupos.shared.Partition
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.operator.IOPBase
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator
import lupos.shared_inline.DictionaryHelper

public class POPVisualisation public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPDebugID, "POPVisualisation", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPVisualisation && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase = POPVisualisation(query, projectedVariables, getChildren()[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> = (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()
    override fun toSparql(): String = getChildren()[0].toSparql()
    override fun evaluate(parent: Partition, output: String): String {
        var outputString: String = ""
        var result = IteratorBundle(RowIterator())
        val child = getChildren()[0].evaluate(parent)
        var rowMode = child.rows.columns.toMutableList()
        val target = getChildren()[0].getProvidedVariableNames()
        rowMode = child.rows.columns.toMutableList()
        rowMode.containsAll(target)
        target.containsAll(rowMode)
        // Map Column Iterator
        val iterator = RowIterator()
        var counter = 0
        iterator.columns = child.rows.columns
        val buffer = ByteArrayWrapper()
        iterator.next = {
            println("$uuid next call")
            var visual = Visualisation()
            var res = child.rows.next()
            iterator.buf = child.rows.buf
            if (res < 0) {
                println("$uuid next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue")
            } else {
                // For each Column the data is received from the Dictionary and send to the
                // visualization framework.
                counter++
                println("$uuid next return $counter ${parent.data} ${iterator.buf.map { it.toString(16) }}")
                // Columns auf ein mal senden
                for (j in 0..iterator.columns.size - 1) {
                    query.getDictionary().getValue(buffer, iterator.buf[res + j])
                    var string = "?" + this.projectedVariables[j] + " = " + DictionaryHelper.byteArrayToSparql(buffer)
                    visual.sendData(getParent().getVisualUUUID(), getChildren()[0].getVisualUUUID(), iterator.buf[res + j], string)
                    outputString = getParent().getVisualUUUID().toString()+"||"
                    outputString += getParent().getVisualUUUID().toString()+"||"
                    outputString += iterator.buf[res + j].toString()+"||"
                    outputString += string+"||"

                }
            }
            res
        }
        iterator.close = {
            child.rows.close()
            iterator._close()
        }
        return outputString
    }
}
