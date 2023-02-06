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
package lupos.shared.operator.iterator

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

public open class RowIteratorFromColumn(@JvmField public val bundle: IteratorBundle) : RowIterator() {
    @JvmField
    public var iterators: Array<ColumnIterator>

    init {
if(SanityCheck.enabled){if(!( bundle.hasColumnMode() )){throw Exception("SanityCheck failed")}}
        val keys = bundle.columns.keys.toList()
        columns = Array(bundle.columns.size) { keys[it] }
        iterators = Array(bundle.columns.size) { bundle.columns[columns[it]]!! }
        buf = DictionaryValueTypeArray(keys.size)
        next = {
            var res = 0
            for (columnIndex in columns.indices) {
                val tmp = iterators[columnIndex].next()
                if (tmp == DictionaryValueHelper.nullValue) {
                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_shared/src/commonMain/kotlin/lupos/shared/operator/iterator/RowIteratorFromColumn.kt:38"/*SOURCE_FILE_END*/ }, { columnIndex == 0 }, { "" + iterators[columnIndex] })
                    res = -1
                    close()
                    break
                } else {
                    buf[columnIndex] = tmp
                }
            }
            res
        }
        close = {
            for (columnIndex in 0 until columns.size) {
                iterators[columnIndex].close()
            }
            _close()
        }
    }
}
