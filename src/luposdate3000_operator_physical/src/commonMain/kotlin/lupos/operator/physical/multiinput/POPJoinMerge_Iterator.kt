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
package lupos.operator.physical.multiinput

import lupos.shared.ColumnIteratorChildIterator
import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.inline.ColumnIteratorChildIteratorExt
import lupos.shared.operator.iterator.ColumnIterator
import kotlin.jvm.JvmField
internal class POPJoinMerge_Iterator(
    query: IQuery,
    @JvmField internal val columnsINJ0: List<ColumnIterator>,
    @JvmField internal val columnsINJ1: List<ColumnIterator>,
    @JvmField internal val columnsINO0: List<ColumnIterator>,
    @JvmField internal val columnsINO1: List<ColumnIterator>,
    @JvmField internal val columnsOUT0: List<ColumnIteratorChildIterator>,
    @JvmField internal val columnsOUT1: List<ColumnIteratorChildIterator>,
    @JvmField internal val columnsOUTJ: List<ColumnIteratorChildIterator>,
    @JvmField internal val key0: DictionaryValueTypeArray,
    @JvmField internal val key1: DictionaryValueTypeArray,
) : ColumnIteratorChildIterator(query) {
    @JvmField
    internal val data0 = Array(columnsINO0.size) { DictionaryValueTypeArray(100) }
    internal val enableSIP = false

    @JvmField
    internal val data1 = Array(columnsINO1.size) { DictionaryValueTypeArray(100) }

    @JvmField
    internal var localNextI = 0

    @JvmField
    internal var localNextJ = 0

    @JvmField
    internal var localNextCounta = 0

    @JvmField
    internal var localNextCountb = 0

    @JvmField
    internal val localNextKeycopy = DictionaryValueTypeArray(columnsINJ0.size)

    @JvmField
    internal var localCloseI = 0

    @JvmField
    internal var sipbufSkip = IntArray(1)

    @JvmField
    internal var sipbufValue = DictionaryValueTypeArray(1)

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

    override /*suspend*/ fun next(): DictionaryValueType {
        return ColumnIteratorChildIteratorExt.nextHelper(
            query,
            this,
            {
                var skipO0 = 0
                var skipO1 = 0
                if (key0[0] != DictionaryValueHelper.nullValue && key1[0] != DictionaryValueHelper.nullValue) {
                    loop@ while (true) {
                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:125"/*SOURCE_FILE_END*/ }, { columnsINJ0.isNotEmpty() })
// first join column
                        if (key0[0] != key1[0]) {
                            var skip0 = 0
                            var skip1 = 0
                            while (key0[0] != key1[0]) {
                                if (key0[0] < key1[0]) {
                                    if (enableSIP) {
                                        columnsINJ0[0].nextSIP(key1[0], sipbufValue, sipbufSkip)
                                        key0[0] = sipbufValue[0]
                                        skip0 += sipbufSkip[0]
                                        skipO0 += sipbufSkip[0]
                                    } else {
                                        key0[0] = columnsINJ0[0].next()
                                    }
                                    skip0++
                                    skipO0++
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:142"/*SOURCE_FILE_END*/ }, { key0[0] != DictionaryValueHelper.undefValue })
                                    if (key0[0] == DictionaryValueHelper.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                } else {
                                    if (enableSIP) {
                                        columnsINJ1[0].nextSIP(key0[0], sipbufValue, sipbufSkip)
                                        key1[0] = sipbufValue[0]
                                        skip1 += sipbufSkip[0]
                                        skipO1 += sipbufSkip[0]
                                    } else {
                                        key1[0] = columnsINJ1[0].next()
                                    }
                                    skip1++
                                    skipO1++
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:158"/*SOURCE_FILE_END*/ }, { key1[0] != DictionaryValueHelper.undefValue })
                                    if (key1[0] == DictionaryValueHelper.nullValue) {
                                        __close()
                                        break@loop
                                    }
                                }
                            }
                            if (skip0 > 0) {
                                localNextJ = 1
                                while (localNextJ < columnsINJ0.size) {
                                    if (enableSIP) {
                                        key0[localNextJ] = columnsINJ0[localNextJ].skipSIP(skip0)
                                    } else {
                                        for (i in 0 until skip0) {
                                            key0[localNextJ] = columnsINJ0[localNextJ].next()
                                        }
                                    }
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:175"/*SOURCE_FILE_END*/ }, { key0[localNextJ] != DictionaryValueHelper.undefValue })
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:176"/*SOURCE_FILE_END*/ }, { key0[localNextJ] != DictionaryValueHelper.nullValue })
                                    localNextJ++
                                }
                            }
                            if (skip1 > 0) {
                                localNextJ = 1
                                while (localNextJ < columnsINJ1.size) {
                                    if (enableSIP) {
                                        key1[localNextJ] = columnsINJ1[localNextJ].skipSIP(skip1)
                                    } else {
                                        for (i in 0 until skip1) {
                                            key1[localNextJ] = columnsINJ1[localNextJ].next()
                                        }
                                    }
                                    SanityCheck.check(
                                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:191"/*SOURCE_FILE_END*/ },
                                        { key1[localNextJ] != DictionaryValueHelper.undefValue }
                                    )
                                    SanityCheck.check(
                                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:195"/*SOURCE_FILE_END*/ },
                                        { key1[localNextJ] != DictionaryValueHelper.nullValue }
                                    )
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
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:210"/*SOURCE_FILE_END*/ }, { key0[localNextJ] != DictionaryValueHelper.undefValue })
                                    if (key0[localNextJ] == DictionaryValueHelper.nullValue) {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:212"/*SOURCE_FILE_END*/ }, { localNextJ == 0 })
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
                                    SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:224"/*SOURCE_FILE_END*/ }, { key1[localNextJ] != DictionaryValueHelper.undefValue })
                                    if (key1[localNextJ] == DictionaryValueHelper.nullValue) {
                                        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:226"/*SOURCE_FILE_END*/ }, { localNextJ == 0 })
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
                                        val d = DictionaryValueTypeArray(localNextCounta * 2)
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
                                    if (enableSIP) {
                                        data0[localNextI][localNextCounta] = columnsINO0[localNextI].skipSIP(skipO0)
                                    } else {
                                        for (i in 0 until skipO0 + 1) {
                                            data0[localNextI][localNextCounta] = columnsINO0[localNextI].next()
                                        }
                                    }
                                    localNextI++
                                }
                                skipO0 = 0
                            }
                            localNextCounta++
                            localNextI = 0
                            while (localNextI < columnsINJ0.size) {
                                key0[localNextI] = columnsINJ0[localNextI].next()
                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:277"/*SOURCE_FILE_END*/ }, { key0[localNextI] != DictionaryValueHelper.undefValue })
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
                                        val d = DictionaryValueTypeArray(localNextCountb * 2)
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
                                    if (enableSIP) {
                                        data1[localNextI][localNextCountb] = columnsINO1[localNextI].skipSIP(skipO1)
                                    } else {
                                        for (i in 0 until skipO1 + 1) {
                                            data1[localNextI][localNextCountb] = columnsINO1[localNextI].next()
                                        }
                                    }
                                    localNextI++
                                }
                                skipO1 = 0
                            }
                            localNextCountb++
                            localNextI = 0
                            while (localNextI < columnsINJ1.size) {
                                key1[localNextI] = columnsINJ1[localNextI].next()
                                SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMerge_Iterator.kt:323"/*SOURCE_FILE_END*/ }, { key1[localNextI] != DictionaryValueHelper.undefValue })
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
