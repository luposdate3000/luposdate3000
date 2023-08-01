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

import lupos.shared.IQuery
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.DictionaryHelper
import lupos.shared.operator.iterator.IteratorBundle
import lupos.shared.operator.iterator.RowIterator

public object EvalVisualisation {
    public operator fun invoke(
        child: IteratorBundle,
        visualTest: MutableList<String>?,
        query: IQuery,
        childUUID: Long,
        parentUUID: Long,
    ): IteratorBundle {
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
                    val string = "?" + iterator.columns[j] + " = " + DictionaryHelper.byteArrayToSparql(buffer).replace("\\", "\\\\").replace("\"", "\\\"")
                    var outputString = "[" + childUUID.toString() + ","
                    outputString += parentUUID.toString() + ","
                    outputString += "\"$string\","
                    outputString += iterator.buf[res + j].toString() + "]"
                    visualTest!!.add(outputString)
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
}
