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
import lupos.shared.SanityCheck
import kotlin.jvm.JvmField

public open class IteratorBundle {
    @JvmField
    internal var mode: IteratorBundleMode

    @JvmField
    internal var _columns: Map<String, ColumnIterator>?

    @JvmField
    internal var _rows: RowIterator?

    @JvmField
    public val names: Array<String>

    @JvmField
    internal var counter: Int = 0
    public fun hasColumnMode(): Boolean = mode == IteratorBundleModeExt.COLUMN
    public fun hasCountMode(): Boolean = mode == IteratorBundleModeExt.COUNT
    public fun hasRowMode(): Boolean = mode == IteratorBundleModeExt.ROW

    public constructor (columns: Map<String, ColumnIterator>) {
        _rows = null
        _columns = columns
        mode = IteratorBundleModeExt.COLUMN
        names = columns.keys.toTypedArray()
    }

    public constructor(count: Int) {
        _rows = null
        _columns = null
        counter = count
        mode = IteratorBundleModeExt.COUNT
        names = arrayOf()
    }

    public constructor(rows: RowIterator) {
        _rows = rows
        _columns = null
        mode = IteratorBundleModeExt.ROW
        names = rows.columns
    }

    public val columns: Map<String, ColumnIterator>
        get() {
            return when (mode) {
                IteratorBundleModeExt.COLUMN -> {
                    _columns!!
                }
                IteratorBundleModeExt.ROW -> {
                    if (_columns == null) {
                        _columns = ColumnIteratorFromRow(_rows!!)
                    }
                    _columns!!
                }
                else -> {
                    TODO()
                }
            }
        }
    public val rows: RowIterator
        get() {
            return when (mode) {
                IteratorBundleModeExt.ROW -> {
                    _rows!!
                }
                IteratorBundleModeExt.COLUMN -> {
                    if (_rows == null) {
                        _rows = RowIteratorFromColumn(this)
                    }
                    _rows!!
                }
                else -> {
                    TODO()
                }
            }
        }

    public open /*suspend*/ fun hasNext2(): Boolean {
        if (counter > 0) {
            counter--
            return true
        }
        return false
    }

    public open /*suspend*/ fun hasNext2Close() {
    }

    /*suspend*/ public fun count(): Int {
        return when (mode) {
            IteratorBundleModeExt.COLUMN -> {
                if (names.size == 0) {
                    0
                } else {
                    var res = 0
                    val col = _columns!![_columns!!.keys.first()]!!
                    while (col.next() != DictionaryValueHelper.nullValue) {
                        res++
                    }
                    res
                }
            }
            IteratorBundleModeExt.ROW -> {
                if (names.size == 0) {
                    0
                } else {
                    var res = 0
                    while (_rows!!.next() != -1) {
                        res++
                    }
                    res
                }
            }
            IteratorBundleModeExt.COUNT -> {
                if (counter > 0) {
                    counter
                } else {
                    var res = 0
                    while (hasNext2()) {
                        res++
                    }
                    hasNext2Close()
                    counter = res
                    res
                }
            }
            else -> TODO("")
        }
    }
}
