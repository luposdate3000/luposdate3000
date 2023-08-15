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
import lupos.shared.inline.DictionaryHelper
import kotlin.jvm.JvmField

public class MemoryTable public constructor(@JvmField public val columns: Array<String>) {

    @JvmField
    public val data: MutableList<DictionaryValueTypeArray> = mutableListOf() // array of rows

    @JvmField
    public var booleanResult: Boolean? = null

    @JvmField
    public var query: IQuery? = null
    public fun column(name: String): DictionaryValueTypeArray? {
        val j = columns.indexOf(name)
        if (j < 0) {
            return null
        }
        val res = DictionaryValueTypeArray(data.size)
        var i = 0
        for (row in data) {
            res[i] = row[j]
            i++
        }
        return res
    }

    override fun toString(): String {
        val buffer = ByteArrayWrapper()
        fun idToString(id: DictionaryValueType): String {
            val q = query
            return if (q != null) {
                q.getDictionary().getValue(buffer, id)
                "${DictionaryHelper.byteArrayToSparql(buffer)} ($buffer)"
            } else {
                ""
            }
        }
        return "$booleanResult - ${columns.map { it }} - ${data.map { it.map { it2 -> "$it2 -> ${idToString(it2)}" } }}"
    }

    override fun equals(other: Any?): Boolean {
        return equalsVerbose(other, false, false, true, null)
    }

    public fun equalsVerbose(actual: Any?, ignoreOrder: Boolean, verbose: Boolean, checkColumnOrder: Boolean, out: IMyOutputStream?): Boolean {
        val other = actual
        if (other !is MemoryTable) {
            if (verbose) {
                out!!.println("other is not a MemoryTable")
            }
            return false
        }
        val buffer1 = ByteArrayWrapper()
        val buffer2 = ByteArrayWrapper()
        val dict1 = query!!.getDictionary()
        val dict2 = other.query!!.getDictionary()

        if (columns.size != other.columns.size) {
            if (verbose) {
                out!!.println("columns differ : ${columns.map { it }} vs ${other.columns.map { it }}")
            }
            return false
        }
        for (i in 0 until columns.size) {
            if (columns[i] != other.columns[i]) {
                if (checkColumnOrder) {
                    if (verbose) {
                        out!!.println("columns differ : ${columns.map { it }} vs ${other.columns.map { it }}")
                    }
                    return false
                } else {
                    val o2 = MemoryTable(columns)
                    val mapping = IntArray(columns.size) { other.columns.indexOf(columns[it]) }
                    if (mapping.contains(-1)) {
                        if (verbose) {
                            out!!.println("columns differ : ${columns.map { it }} vs ${other.columns.map { it }}")
                        }
                        return false
                    } else {
                        for (d in other.data) {
                            val r = DictionaryValueTypeArray(d.size)
                            for (i in 0 until mapping.size) {
                                r[i] = d[mapping[i]]
                            }
                            o2.data.add(r)
                        }
                        o2.query = other.query
                        o2.booleanResult = other.booleanResult
                        return equalsVerbose(o2, ignoreOrder, verbose, true, out)
                    }
                }
            }
        }
        if (booleanResult != null || other.booleanResult != null) {
            return if (booleanResult == other.booleanResult) {
                true
            } else {
                if (verbose) {
                    out!!.println("boolean result differ : $booleanResult vs ${other.booleanResult}")
                }
                false
            }
        }
        val flags1 = IntArray(data.size) { -1 }
        val flags2 = IntArray(other.data.size) { -1 }
        for (i in 0 until data.size) {
            loop2@ for (j in 0 until other.data.size) {
                if (flags2[j] == -1) {
                    var flag = true
                    loop1@ for (k in 0 until columns.size) {
                        dict1.getValue(buffer1, data[i][k])
                        dict2.getValue(buffer2, other.data[j][k])
                        if (buffer1 != buffer2) {
                            if (DictionaryHelper.byteArrayToType(buffer1) == DictionaryHelper.byteArrayToType(buffer2) && DictionaryHelper.byteArrayToType(buffer1) == ETripleComponentTypeExt.BLANK_NODE) {
// bnodes should not be compared
                            } else {
                                flag = false
                                break@loop1
                            }
                        }
                    }
                    if (flag) {
                        flags2[j] = i
                        flags1[i] = j
                        break@loop2
                    }
                }
            }
        }

        var result = true
        if (ignoreOrder) {
            var i = 0
            while (i < data.size || i < other.data.size) {
                if (i < data.size) {
                    if (flags1[i] == -1) {
                        result = false
                        if (verbose) {
                            out!!.println(
                                "expected has ${data[i].map { it }} : ${
                                    data[i].map {
                                        dict1.getValue(buffer1, it)
                                        DictionaryHelper.byteArrayToSparql(buffer1)
                                    }
                                }",
                            )
                        }
                    }
                }
                if (i < other.data.size) {
                    if (flags2[i] == -1) {
                        result = false
                        if (verbose) {
                            out!!.println(
                                "actual has ${other.data[i].map { it }} : ${
                                    other.data[i].map {
                                        dict2.getValue(buffer2, it)
                                        DictionaryHelper.byteArrayToSparql(buffer2)
                                    }
                                }",
                            )
                        }
                    }
                }
                i++
            }
        } else {
            var i = 0
            while (i < data.size || i < other.data.size) {
                if (i < data.size) {
                    if (flags1[i] != i) {
                        result = false
                        if (verbose) {
                            out!!.println(
                                "expected has ${data[i].map { it }} : ${
                                    data[i].map {
                                        dict1.getValue(buffer1, it)
                                        DictionaryHelper.byteArrayToSparql(buffer1)
                                    }
                                }",
                            )
                        }
                    }
                }
                if (i < other.data.size) {
                    if (flags2[i] != i) {
                        result = false
                        if (verbose) {
                            out!!.println(
                                "actual has ${other.data[i].map { it }} : ${
                                    other.data[i].map {
                                        dict2.getValue(buffer2, it)
                                        DictionaryHelper.byteArrayToSparql(buffer2)
                                    }
                                }",
                            )
                        }
                    }
                }
                i++
            }
        }
        return result
    }

    public companion object {

        @JvmField
        public val parseFromAnyRegistered: MutableMap<String, MemoryTableParser> = mutableMapOf()

        @Suppress("NOTHING_TO_INLINE")
        internal fun merge(a: MemoryTable, b: MemoryTable): MemoryTable {
            if (a.columns.size != b.columns.size) {
                throw IncompatibleInputException()
            }
            if (a.booleanResult != null) {
                throw IncompatibleInputException()
            }
            if (b.booleanResult != null) {
                throw IncompatibleInputException()
            }
            for (i in a.columns.indices) {
                if (a.columns[i] != b.columns[i]) {
                    throw IncompatibleInputException()
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
