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
package lupos.shared

import lupos.shared.dynamicArray.ByteArrayWrapper
import kotlin.jvm.JvmField

public class MemoryTable public constructor(@JvmField public val columns: Array<String>) {

    @JvmField
    public val data: MutableList<IntArray> = mutableListOf() // array of rows

    @JvmField
    public var booleanResult: Boolean? = null

    @JvmField
    public var query: IQuery? = null
    public fun column(name: String): IntArray? {
        val j = columns.indexOf(name)
        if (j < 0) {
            return null
        }
        var res = IntArray(data.size)
        var i = 0
        for (row in data) {
            res[i] = row[j]
            i++
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MemoryTable) {
            return false
        }
        if (columns.size != other.columns.size) {
            return false
        }
        for (i in 0 until columns.size) {
            if (columns[i] != other.columns[i]) {
                return false
            }
        }
        if (data.size != other.data.size) {
            return false
        }
        val buffer1 = ByteArrayWrapper()
        val buffer2 = ByteArrayWrapper()
        var i = 0
        var dict1 = query!!.getDictionary()
        var dict2 = other.query!!.getDictionary()
        while (i < data.size && i < other.data.size) {
            for (j in 0 until columns.size) {
                dict1.getValue(buffer1, data[i][j])
                dict2.getValue(buffer2, data[i][j])
                if (buffer1 != buffer2) {
                    return false
                }
            }
            i++
        }
        return true
    }

    public fun equalsIgnoreOrder(other: Any?): Boolean {
        if (other !is MemoryTable) {
            return false
        }
        if (columns.size != other.columns.size) {
            return false
        }
        for (i in 0 until columns.size) {
            if (columns[i] != other.columns[i]) {
                return false
            }
        }
        if (data.size != other.data.size) {
            return false
        }
        val buffer1 = ByteArrayWrapper()
        val buffer2 = ByteArrayWrapper()
        var i = 0
        var dict1 = query!!.getDictionary()
        var dict2 = other.query!!.getDictionary()
        while (i < data.size && i < other.data.size) {
            for (j in 0 until columns.size) {
                dict1.getValue(buffer1, data[i][j])
                dict2.getValue(buffer2, data[i][j])
                if (buffer1 != buffer2) {
                    return false
                }
            }
            i++
        }
        return true
    }

    public companion object {

        @JvmField
        public val parseFromAnyRegistered: MutableMap<String, MemoryTableParser> = mutableMapOf()

        @Suppress("NOTHING_TO_INLINE")
        internal inline fun merge(a: MemoryTable, b: MemoryTable): MemoryTable {
            if (a.columns.size != b.columns.size) {
                throw Exception("incompatible input")
            }
            if (a.booleanResult != null) {
                throw Exception("incompatible input")
            }
            if (b.booleanResult != null) {
                throw Exception("incompatible input")
            }
            for (i in a.columns.indices) {
                if (a.columns[i] != b.columns[i]) {
                    throw Exception("incompatible input")
                }
            }
            val res = MemoryTable(a.columns)
            res.data.addAll(a.data)
            res.data.addAll(b.data)
            return res
        }

        public fun parseFromAny(data: String, filename: String, query: IQuery): MemoryTable? {
            val ext = filename.substring(filename.lastIndexOf(".") + 1)
            val parser = parseFromAnyRegistered[ext]
            if (parser == null) {
                throw UnknownDataFileException("$filename ($ext)")
            } else {
                return parser(data, query)
            }
        }
    }
}
