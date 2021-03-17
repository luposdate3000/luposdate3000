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

/* Operator by Rico

 Needed for the visualization process
 Will be added between each operator during the optimization process and will send
 the data that is passing through the operator to the visualization framework.
 Code is based of the Debug Operator.

*/

package lupos.s09physicalOperators.singleinput
import lupos.s00misc.EOperatorIDExt
import lupos.s00misc.ESortPriorityExt
import lupos.s00misc.Partition
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.iterator.RowIterator
import lupos.s09physicalOperators.POPBase

public class POPRico public constructor(query: IQuery, projectedVariables: List<String>, child: IOPBase) : POPBase(query, projectedVariables, EOperatorIDExt.POPDebugID, "POPRico", arrayOf(child), ESortPriorityExt.SAME_AS_CHILD) {
    override fun getPartitionCount(variable: String): Int = getChildren()[0].getPartitionCount(variable)
    override fun equals(other: Any?): Boolean = other is POPRico && getChildren()[0] == other.getChildren()[0]
    override fun cloneOP(): IOPBase = POPRico(query, projectedVariables, getChildren()[0].cloneOP())
    override fun getRequiredVariableNames(): List<String> = listOf()
    override fun getProvidedVariableNames(): List<String> = getChildren()[0].getProvidedVariableNames()
    override fun getProvidedVariableNamesInternal(): List<String> =
        (getChildren()[0] as POPBase).getProvidedVariableNamesInternal()

    override fun toSparql(): String = getChildren()[0].toSparql()
    override /*suspend*/ fun evaluate(parent: Partition): IteratorBundle {
        var result = IteratorBundle(RowIterator())
        val child = getChildren()[0].evaluate(parent)
        var rowMode = child.rows.columns.toMutableList()
        val target = getChildren()[0].getProvidedVariableNames()
        rowMode = child.rows.columns.toMutableList()
        rowMode.containsAll(target)
        target.containsAll(rowMode)
        val iterator = RowIterator()
        var counter = 0
        iterator.columns = child.rows.columns
        iterator.next = {
            println("$uuid next call")
            var test = Rico()
            var res = child.rows.next()
            iterator.buf = child.rows.buf
            if (res < 0) {
                println { "$uuid next return closed $counter ${parent.data} ResultSetDictionaryExt.nullValue" }
            } else {
                //For each Column the data is received from the Dictionary and send to the
                //visualization framework.
                counter++
                println { "$uuid next return $counter ${parent.data} ${iterator.buf.map { it.toString(16) }}" }
                for (j in 0..iterator.columns.size - 1) {
                    var string =
                        "?" + this.projectedVariables[j] + " = " + query.getDictionary().getValue(iterator.buf[res + j])
                            .valueToString();
                    string?.let {
                        test.sendData(
                            getParent().getUUID(), getChildren()[0].getUUID(), iterator.buf[res + j],
                            it
                        )
                        println("" + getUUID() + " parent: " + getParent().getUUID())
                    }
                }
            }
            res
        }
        iterator.close = {
            println { "$uuid closed $counter ${parent.data}" }
            child.rows.close()
            iterator._close()
        }
        return IteratorBundle(iterator)
    }
}




