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
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public open class RowIteratorMerge(@JvmField public val a: RowIterator, @JvmField public val b: RowIterator, @JvmField public val comparator: Comparator<DictionaryValueType>, @JvmField public val compCount: Int) : RowIterator() {
    public companion object {
        public /*suspend*/ operator fun invoke(a: RowIterator, comparator: Comparator<DictionaryValueType>, compCount: Int, columns: Array<String>): RowIterator {
            if (SanityCheck.enabled) { if (!(columns.size == a.columns.size)) { throw Exception("SanityCheck failed") } }
            var buf1 = DictionaryValueTypeArray(columns.size * MERGE_SORT_MIN_ROWS)
            var buf2 = DictionaryValueTypeArray(columns.size * MERGE_SORT_MIN_ROWS)
            var done = false
            val resultList = mutableListOf<RowIterator?>()
            val columnMapping = IntArray(columns.size)
            for (i in columns.indices) {
                for (j in columns.indices) {
                    if (a.columns[j] == columns[i]) {
                        columnMapping[i] = j
                    }
                }
            }
            while (!done) {
                var i = 0
                while (i < buf1.size) {
                    val next = a.next()
                    if (next < 0) {
                        done = true
                        a.close()
                        break
                    }
                    for (j in 0 until columns.size) {
                        buf1[i++] = a.buf[next + columnMapping[j]]
                    }
                }
                val total = i / columns.size
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
                        val aEnd = (off + mid) * columns.size
                        val bEnd = (off + count) * columns.size
                        var a2 = off * columns.size
                        var b = aEnd
                        var c = a2
                        if (count < mid) {
                            b = a2
                            a2 = aEnd
                        }
                        loop@ while (a2 < aEnd && b < bEnd) {
                            for (l in columns.indices) {
                                var cmp: Int
                                var j = 0
                                while (j < compCount) {
                                    cmp = comparator.compare(buf1[a2 + l], buf1[b + l])
                                    if (cmp < 0) {
                                        for (k in columns.indices) {
                                            buf2[c++] = buf1[a2++]
                                        }
                                        continue@loop
                                    } else if (cmp > 0) {
                                        for (k in columns.indices) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                                var cmp2: DictionaryValueType
                                while (j < columns.size) {
                                    cmp2 = buf1[a2 + l] - buf1[b + l]
                                    if (cmp2 < 0) {
                                        for (k in columns.indices) {
                                            buf2[c++] = buf1[a2++]
                                        }
                                        continue@loop
                                    } else if (cmp2 > 0) {
                                        for (k in columns.indices) {
                                            buf2[c++] = buf1[b++]
                                        }
                                        continue@loop
                                    }
                                    j++
                                }
                            }
                            for (j in columns.indices) {
                                buf2[c++] = buf1[a2++]
                            }
                            for (j in columns.indices) {
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
                val it = RowIteratorBuf(buf1, columns, i)
                if (i > 0 || resultList.size == 0) {
                    if (resultList.size == 0) {
                        resultList.add(it)
                    } else if (resultList[0] == null) {
                        resultList[0] = it
                    } else {
                        resultList[0] = RowIteratorMerge(resultList[0]!!, it, comparator, compCount)
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
                                resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
                                resultList[j - 1] = null
                            }
                            j++
                        }
                    }
                }
                buf1 = DictionaryValueTypeArray(columns.size * MERGE_SORT_MIN_ROWS)
            }
            var j = 1
            while (j < resultList.size) {
                if (resultList[j] == null) {
                    resultList[j] = resultList[j - 1]
                } else if (resultList[j - 1] != null) {
                    resultList[j] = RowIteratorMerge(resultList[j]!!, resultList[j - 1]!!, comparator, compCount)
                }
                j++
            }
            if (SanityCheck.enabled) { if (!(resultList.size > 0)) { throw Exception("SanityCheck failed") } }
            return resultList[resultList.size - 1]!!
        }
    }

    @JvmField
    public var flag: Int = 3

    @JvmField
    public var aIdx: Int = -1

    @JvmField
    public var bIdx: Int = -1

    init {
        if (SanityCheck.enabled) {
            if (SanityCheck.enabled) { if (!(a.columns.size == b.columns.size)) { throw Exception("SanityCheck failed") } }
            for (i in a.columns.indices) {
                if (SanityCheck.enabled) { if (!(a.columns[i] == b.columns[i])) { throw Exception("SanityCheck failed") } }
            }
        }

        columns = a.columns
        close = {
            a.close()
            b.close()
            _close()
        }
        next = {
            var res = -1
            when (flag) {
                1 -> { // call next on a, b is empty
                    res = a.next()
                    buf = a.buf
                    if (res < 0) {
                        a.close()
                        flag = 0
                    }
                }
                2 -> { // call next on b, a is empty
                    res = b.next()
                    buf = b.buf
                    if (res < 0) {
                        b.close()
                        flag = 0
                    }
                }
                4 -> { // call next on a, b is not empty
                    aIdx = a.next()
                    if (aIdx < 0) {
                        buf = b.buf
                        res = bIdx
                        flag = 2
                    } else {
                        compare(
                            {
                                buf = a.buf
                                res = aIdx
                                flag = 4
                            },
                            {
                                buf = b.buf
                                res = bIdx
                                flag = 5
                            },
                        )
                    }
                }
                5 -> { // call next on b, a is not empty
                    bIdx = b.next()
                    if (bIdx < 0) {
                        buf = a.buf
                        res = aIdx
                        flag = 1
                    } else {
                        compare(
                            {
                                buf = a.buf
                                res = aIdx
                                flag = 4
                            },
                            {
                                buf = b.buf
                                res = bIdx
                                flag = 5
                            },
                        )
                    }
                }
                3 -> { // call next on both
                    aIdx = a.next()
                    bIdx = b.next()
                    if (aIdx < 0 && bIdx < 0) {
                        res = -1
                        a.close()
                        b.close()
                        flag = 0
                    } else if (bIdx < 0) {
                        buf = a.buf
                        b.close()
                        res = aIdx
                        flag = 1
                    } else if (aIdx < 0) {
                        buf = b.buf
                        a.close()
                        res = bIdx
                        flag = 2
                    } else {
                        compare(
                            {
                                buf = a.buf
                                res = aIdx
                                flag = 4
                            },
                            {
                                buf = b.buf
                                res = bIdx
                                flag = 5
                            },
                        )
                    }
                }
            }
            res
        }
    }

    private fun compare(actionA: () -> Unit, actionB: () -> Unit) {
        var i = 0
        while (i < compCount) {
            val cmp = comparator.compare(a.buf[aIdx + i], b.buf[bIdx + i])
            if (cmp < 0) {
                actionA()
                return
            } else if (cmp > 0) {
                actionB()
                return
            }
            i++
        }
        while (i < columns.size) {
            val cmp = a.buf[aIdx + i] - b.buf[bIdx + i]
            if (cmp < 0) {
                actionA()
                return
            } else if (cmp > 0) {
                actionB()
                return
            }
            i++
        }
        actionA()
    }
}
