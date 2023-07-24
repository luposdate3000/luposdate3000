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

import lupos.shared.DictionaryValueTypeArray
import lupos.shared.inline.ParallelThread
import lupos.shared.operator.iterator.RowIterator
import kotlin.jvm.JvmField

public open class RowIteratorMinus(@JvmField public val a: RowIterator, @JvmField public val b: RowIterator, @JvmField internal val projection: Array<String>) : RowIterator() {
    @JvmField
    public var flag: Int = 2

    @JvmField
    internal var aIdx = -1

    @JvmField
    internal var bIdx = -1
    public /*suspend*/ fun _init() {
        var compCount = 0
        val columnsA = mutableListOf<String>()
        val columnsB = mutableListOf<String>()
        for (i in a.columns.indices) {
            for (element in b.columns) {
                if (a.columns[i] == element) {
                    columnsA.add(a.columns[i])
                    columnsB.add(a.columns[i])
                    compCount++
                }
            }
        }
        for (i in a.columns.indices) {
            if (!columnsA.contains(a.columns[i])) {
                columnsA.add(a.columns[i])
            }
        }
        for (j in b.columns.indices) {
            if (!columnsB.contains(b.columns[j])) {
                columnsB.add(b.columns[j])
            }
        }
        columns = projection
        val mapping = IntArray(projection.size)
        for (i in projection.indices) {
            for (j in a.columns.indices) {
                if (projection[i] == a.columns[j]) {
                    mapping[i] = j
                }
            }
        }
        buf = DictionaryValueTypeArray(mapping.size)
        ParallelThread.runBlocking {
            bIdx = b.next()
            if (bIdx < 0) {
                flag = 1
            }
            close = {
                _close()
                a.close()
                b.close()
            }
            next = {
                var res = -1
                loop@ while (true) {
                    when (flag) {
                        0 -> {
                            break@loop
                        }
                        1 -> { // nothing to remove left
                            aIdx = a.next()
                            if (aIdx < 0) {
                                flag = 0
                                close()
                            } else {
                                res = 0
                                for (i in mapping.indices) {
                                    buf[i] = a.buf[mapping[i] + aIdx]
                                }
                            }
                            break@loop
                        }
                        2 -> {
                            aIdx = a.next()
                            if (aIdx >= 0) {
                                for (i in 0 until compCount) {
                                    if (a.buf[i] < b.buf[i]) {
                                        res = 0
                                        for (k in mapping.indices) {
                                            buf[k] = a.buf[mapping[k] + aIdx]
                                        }
                                        break@loop
                                    } else if (a.buf[i] > b.buf[i]) {
                                        bIdx = b.next()
                                        if (bIdx < 0) {
                                            flag = 1
                                            res = 0
                                            for (k in mapping.indices) {
                                                buf[k] = a.buf[mapping[k] + aIdx]
                                            }
                                            break@loop
                                        }
                                        continue@loop
                                    }
                                }
                            } else {
                                close()
                                flag = 0
                                break@loop
                            }
                        }
                    }
                }
                res
            }
        }
    }
}
