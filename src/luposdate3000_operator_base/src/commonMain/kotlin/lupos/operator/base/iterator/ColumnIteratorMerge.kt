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
package lupos.operator.base.iterator

import lupos.operator.base.MERGE_SORT_MIN_ROWS
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator

public object ColumnIteratorMerge {
    public /*suspend*/ operator fun invoke(a: ColumnIterator, comparator: Comparator<DictionaryValueType>): ColumnIterator {
        var buf1 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        var buf2 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        var done = false
        val resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == DictionaryValueHelper.nullValue) {
                    done = true
                    a.close()
                    break
                } else {
                    buf1[i++] = next
                }
            }
            val total = i
            var off: Int
            var shift = 0
            var size = 1 shl shift
            var count: Int
            var mid: Int
            while (size / 2 < total) {
                off = 0
                shift++
                size = 1 shl shift
                while (off < total) {
                    count = if (off + size <= total) {
                        size
                    } else {
                        total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a2 = off
                    var b = aEnd
                    var c = a2
                    if (count < mid) {
                        b = a2
                        a2 = aEnd
                    }
                    loop@ while (a2 < aEnd && b < bEnd) {
                        if (comparator.compare(buf1[a2], buf1[b]) < 0) {
                            buf2[c++] = buf1[a2++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a2 < aEnd) {
                        buf2[c++] = buf1[a2++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                val t = buf1
                buf1 = buf2
                buf2 = t
            }
            val it = ColumnIteratorMultiValue(buf1, total)
            if (i > 0 || resultList.size == 0) {
                if (resultList.size == 0) {
                    resultList.add(it)
                } else if (resultList[0] == null) {
                    resultList[0] = it
                } else {
                    resultList[0] = ColumnIteratorMerge1(resultList[0]!!, it, comparator)
                    if (resultList[resultList.size - 1] != null) {
                        resultList.add(null)
                    }
                    var j = 1
                    while (j < resultList.size) {
                        if (resultList[j] == null) {
                            resultList[j] = resultList[j - 1]
                            resultList[j - 1] = null
                            break
                        } else {
                            resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
                            resultList[j - 1] = null
                        }
                        j++
                    }
                }
            }
            buf1 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        }
        var j = 1
        while (j < resultList.size) {
            if (resultList[j] == null) {
                resultList[j] = resultList[j - 1]
            } else if (resultList[j - 1] != null) {
                resultList[j] = ColumnIteratorMerge1(resultList[j]!!, resultList[j - 1]!!, comparator)
            }
            j++
        }
        if (SanityCheck.enabled) { if (!(resultList.size > 0)) { throw Exception("SanityCheck failed") } }
        return resultList[resultList.size - 1]!!
    }

    public /*suspend*/ operator fun invoke(a: ColumnIterator): ColumnIterator {
        var buf1 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        var buf2 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        var done = false
        val resultList = mutableListOf<ColumnIterator?>()
        while (!done) {
            var i = 0
            while (i < buf1.size) {
                val next = a.next()
                if (next == DictionaryValueHelper.nullValue) {
                    done = true
                    a.close()
                    break
                } else {
                    buf1[i++] = next
                }
            }
            val total = i
            var off: Int
            var shift = 0
            var size = 1 shl shift
            var count: Int
            var mid: Int
            while (size / 2 < total) {
                off = 0
                shift++
                size = 1 shl shift
                while (off < total) {
                    count = if (off + size <= total) {
                        size
                    } else {
                        total - off
                    }
                    mid = size / 2
                    val aEnd = (off + mid)
                    val bEnd = (off + count)
                    var a2 = off
                    var b = aEnd
                    var c = a2
                    if (count < mid) {
                        b = a2
                        a2 = aEnd
                    }
                    loop@ while (a2 < aEnd && b < bEnd) {
                        if (buf1[a2] < buf1[b]) {
                            buf2[c++] = buf1[a2++]
                        } else {
                            buf2[c++] = buf1[b++]
                        }
                    }
                    while (a2 < aEnd) {
                        buf2[c++] = buf1[a2++]
                    }
                    while (b < bEnd) {
                        buf2[c++] = buf1[b++]
                    }
                    off += size
                }
                val t = buf1
                buf1 = buf2
                buf2 = t
            }
            val it = ColumnIteratorMultiValue(buf1, total)
            if (i > 0 || resultList.size == 0) {
                if (resultList.size == 0) {
                    resultList.add(it)
                } else if (resultList[0] == null) {
                    resultList[0] = it
                } else {
                    resultList[0] = ColumnIteratorMerge2(resultList[0]!!, it)
                    if (resultList[resultList.size - 1] != null) {
                        resultList.add(null)
                    }
                    var j = 1
                    while (j < resultList.size) {
                        if (resultList[j] == null) {
                            resultList[j] = resultList[j - 1]
                            resultList[j - 1] = null
                            break
                        } else {
                            resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
                            resultList[j - 1] = null
                        }
                        j++
                    }
                }
            }
            buf1 = DictionaryValueTypeArray(MERGE_SORT_MIN_ROWS)
        }
        var j = 1
        while (j < resultList.size) {
            if (resultList[j] == null) {
                resultList[j] = resultList[j - 1]
            } else if (resultList[j - 1] != null) {
                resultList[j] = ColumnIteratorMerge2(resultList[j]!!, resultList[j - 1]!!)
            }
            j++
        }
        if (SanityCheck.enabled) { if (!(resultList.size > 0)) { throw Exception("SanityCheck failed") } }
        return resultList[resultList.size - 1]!!
    }
}
