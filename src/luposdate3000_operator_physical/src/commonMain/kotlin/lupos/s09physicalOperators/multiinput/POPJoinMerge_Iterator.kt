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
package lupos.s09physicalOperators.multiinput

import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.DictionaryExt
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorChildIterator
import kotlin.jvm.JvmField

internal class POPJoinMerge_Iterator(
    @JvmField val columnsINJ0: List<ColumnIterator>,
    @JvmField val columnsINJ1: List<ColumnIterator>,
    @JvmField val columnsINO0: List<ColumnIterator>,
    @JvmField val columnsINO1: List<ColumnIterator>,
    @JvmField val columnsOUT0: List<ColumnIteratorChildIterator>,
    @JvmField val columnsOUT1: List<ColumnIteratorChildIterator>,
    @JvmField val columnsOUTJ: List<ColumnIteratorChildIterator>,
    @JvmField val key0: IntArray,
    @JvmField val key1: IntArray
) : ColumnIteratorChildIterator() {
    @JvmField
    val data0 = Array(columnsINO0.size) { IntArray(100) }

    @JvmField
    val data1 = Array(columnsINO1.size) { IntArray(100) }

    @JvmField
    var localNextI = 0

    @JvmField
    var localNextJ = 0

    @JvmField
    var localNextCounta = 0

    @JvmField
    var localNextCountb = 0

    @JvmField
    val localNextKeycopy = IntArray(columnsINJ0.size)

    @JvmField
    var localCloseI = 0

    @JvmField
    var skipO0 = 0

    @JvmField
    var skipO1 = 0

    @JvmField
    var sipbuf = IntArray(2)

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ private inline fun __close() {
        if (label != 0) {
            localCloseI = 0
            while (localCloseI < columnsOUT0.size) {
                columnsOUT0[localCloseI].closeOnNoMoreElements()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsOUT1.size) {
                columnsOUT1[localCloseI].closeOnNoMoreElements()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsOUTJ.size) {
                columnsOUTJ[localCloseI].closeOnNoMoreElements()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsINJ0.size) {
                columnsINJ0[localCloseI].close()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsINJ1.size) {
                columnsINJ1[localCloseI].close()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsINO0.size) {
                columnsINO0[localCloseI].close()
                localCloseI++
            }
            localCloseI = 0
            while (localCloseI < columnsINO1.size) {
                columnsINO1[localCloseI].close()
                localCloseI++
            }
            _close()
        }
    }

    override /*suspend*/ fun close() {
        __close()
    }

    override /*suspend*/ fun next(): Int {
        return nextHelper(
            {
                if (key0[0] != DictionaryExt.nullValue && key1[0] != DictionaryExt.nullValue) {
                    loop@ while (true) {
                        SanityCheck.check { columnsINJ0.isNotEmpty() }
// first join column
                        if (key0[0] != key1[0]) {
                            var skip0 = 0
                            var skip1 = 0
                            while (key0[0] != key1[0]) {
                                if (key0[0] < key1[0]) {
                                    columnsINJ0[0].nextSIP(key1[0], sipbuf)
                                    key0[0] = sipbuf[1]
                                    skip0 += sipbuf[0]
                                    skipO0 += sipbuf[0]
                                    skip0++
                                    skipO0++
                                    SanityCheck.check { key0[0] != DictionaryExt.undefValue }
                                    if (key0[0] == DictionaryExt.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                } else {
                                    columnsINJ1[0].nextSIP(key0[0], sipbuf)
                                    key1[0] = sipbuf[1]
                                    skip1 += sipbuf[0]
                                    skipO1 += sipbuf[0]
                                    skip1++
                                    skipO1++
                                    SanityCheck.check { key1[0] != DictionaryExt.undefValue }
                                    if (key1[0] == DictionaryExt.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                }
                            }
                            if (skip0 > 0) {
                                localNextJ = 1
                                while (localNextJ < columnsINJ0.size) {
                                    key0[localNextJ] = columnsINJ0[localNextJ].skipSIP(skip0)
                                    SanityCheck.check { key0[localNextJ] != DictionaryExt.undefValue }
                                    SanityCheck.check { key0[localNextJ] != DictionaryExt.nullValue }
                                    localNextJ++
                                }
                            }
                            if (skip1 > 0) {
                                localNextJ = 1
                                while (localNextJ < columnsINJ1.size) {
                                    key1[localNextJ] = columnsINJ1[localNextJ].skipSIP(skip1)
                                    SanityCheck.check { key1[localNextJ] != DictionaryExt.undefValue }
                                    SanityCheck.check { key1[localNextJ] != DictionaryExt.nullValue }
                                    localNextJ++
                                }
                            }
                        }
// other join columns
                        localNextI = 1
                        while (localNextI < columnsINJ0.size) {
                            if (key0[localNextI] < key1[localNextI]) {
                                skipO0++
                                localNextJ = 0
                                while (localNextJ < columnsINJ0.size) {
                                    key0[localNextJ] = columnsINJ0[localNextJ].next()
                                    SanityCheck.check { key0[localNextJ] != DictionaryExt.undefValue }
                                    if (key0[localNextJ] == DictionaryExt.nullValue) {
                                        SanityCheck.check { localNextJ == 0 }
                                        __close()
                                        break@loop
                                    }
                                    localNextJ++
                                }
                                continue@loop
                            } else if (key0[localNextI] > key1[localNextI]) {
                                skipO1++
                                localNextJ = 0
                                while (localNextJ < columnsINJ1.size) {
                                    key1[localNextJ] = columnsINJ1[localNextJ].next()
                                    SanityCheck.check { key1[localNextJ] != DictionaryExt.undefValue }
                                    if (key1[localNextJ] == DictionaryExt.nullValue) {
                                        SanityCheck.check { localNextJ == 0 }
                                        __close()
                                        break@loop
                                    }
                                    localNextJ++
                                }
                                continue@loop
                            }
                            localNextI++
                        }
// safe the join columns
                        localNextI = 0
                        while (localNextI < columnsINJ0.size) {
                            localNextKeycopy[localNextI] = key0[localNextI]
                            localNextI++
                        }
// the only-A columns
                        localNextCounta = 0
                        loop2@ while (true) {
                            if (columnsINO0.isNotEmpty()) {
                                if (localNextCounta >= data0[0].size) {
                                    localNextI = 0
                                    while (localNextI < data0.size) {
                                        val x = data0[localNextI]
                                        val d = IntArray(localNextCounta * 2)
                                        localNextJ = 0
                                        while (localNextJ < localNextCounta) {
                                            d[localNextJ] = x[localNextJ]
                                            localNextJ++
                                        }
                                        data0[localNextI] = d
                                        localNextI++
                                    }
                                }
                                localNextI = 0
                                while (localNextI < columnsINO0.size) {
                                    data0[localNextI][localNextCounta] = columnsINO0[localNextI].skipSIP(skipO0)
                                    localNextI++
                                }
                                skipO0 = 0
                            }
                            localNextCounta++
                            localNextI = 0
                            while (localNextI < columnsINJ0.size) {
                                key0[localNextI] = columnsINJ0[localNextI].next()
                                SanityCheck.check { key0[localNextI] != DictionaryExt.undefValue }
                                localNextI++
                            }
                            localNextI = 0
                            while (localNextI < columnsINJ0.size) {
                                if (key0[localNextI] != localNextKeycopy[localNextI]) {
                                    break@loop2
                                }
                                localNextI++
                            }
                        }
// the only-B columns
                        localNextCountb = 0
                        loop2@ while (true) {
                            if (columnsINO1.isNotEmpty()) {
                                if (localNextCountb >= data1[0].size) {
                                    localNextI = 0
                                    while (localNextI < data1.size) {
                                        val x = data1[localNextI]
                                        val d = IntArray(localNextCountb * 2)
                                        localNextJ = 0
                                        while (localNextJ < localNextCountb) {
                                            d[localNextJ] = x[localNextJ]
                                            localNextJ++
                                        }
                                        data1[localNextI] = d
                                        localNextI++
                                    }
                                }
                                localNextI = 0
                                while (localNextI < columnsINO1.size) {
                                    data1[localNextI][localNextCountb] = columnsINO1[localNextI].skipSIP(skipO1)
                                    localNextI++
                                }
                                skipO1 = 0
                            }
                            localNextCountb++
                            localNextI = 0
                            while (localNextI < columnsINJ1.size) {
                                key1[localNextI] = columnsINJ1[localNextI].next()
                                SanityCheck.check { key1[localNextI] != DictionaryExt.undefValue }
                                localNextI++
                            }
                            localNextI = 0
                            while (localNextI < columnsINJ1.size) {
                                if (key1[localNextI] != localNextKeycopy[localNextI]) {
                                    break@loop2
                                }
                                localNextI++
                            }
                        }
                        POPJoin.crossProduct(data0, data1, localNextKeycopy, columnsOUT0, columnsOUT1, columnsOUTJ, localNextCounta, localNextCountb)
                        break@loop
                    }
                } else {
                    __close()
                }
            },
            { __close() }
        )
    }
}
