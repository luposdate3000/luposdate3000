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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.IQuery
import lupos.shared.SanityCheck
import lupos.shared.operator.iterator.ColumnIterator
import kotlin.jvm.JvmField
internal class POPJoinMergeSingleColumn_Iterator(@JvmField internal val query: IQuery, @JvmField internal val child0: ColumnIterator, @JvmField internal val child1: ColumnIterator, @JvmField internal var head0: DictionaryValueType, @JvmField internal var head1: DictionaryValueType) : ColumnIterator() {
    @JvmField
    internal var counter: Int = 0

    @JvmField
    internal var value: DictionaryValueType = head0

    @JvmField
    internal var label = 1

    @JvmField
    internal var sipbufSkip = IntArray(1)

    @JvmField
    internal var sipbufValue = DictionaryValueTypeArray(1)

    @JvmField
    internal var debugHead0 = head0

    @JvmField
    internal var debugHead1 = head1

    init {
    }

    override /*suspend*/ fun next(): DictionaryValueType {
        if (query.shouldAbortNow()) {
            _close()
            return DictionaryValueHelper.nullValue
        }
        when (label) {
            1 -> {
                if (counter == 0) {
                    var change = true
                    while (change) {
                        change = false
                        while (head0 < head1) {
                            child0.nextSIP(head1, sipbufValue, sipbufSkip)
                            val c = sipbufValue[0]
                            //  val c = child0.next() // TODO reenable sip
                            SanityCheck(
                                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:66"/*SOURCE_FILE_END*/ },
                                {
                                    SanityCheck.check(
                                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:69"/*SOURCE_FILE_END*/ },
                                        { c >= debugHead0 || c == DictionaryValueHelper.nullValue }
                                    )
                                    debugHead0 = c
                                }
                            )
                            if (c == DictionaryValueHelper.nullValue) {
                                _close()
                                return DictionaryValueHelper.nullValue
                            } else {
                                head0 = c
                            }
                        }
                        while (head1 < head0) {
                            change = true
                            child1.nextSIP(head0, sipbufValue, sipbufSkip)
                            val c = sipbufValue[0]
//                            val c = child1.next() // TODO reenable sip
                            SanityCheck(
                                { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:88"/*SOURCE_FILE_END*/ },
                                {
                                    SanityCheck.check(
                                        { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:91"/*SOURCE_FILE_END*/ },
                                        { c >= debugHead1 || c == DictionaryValueHelper.nullValue }
                                    )
                                    debugHead1 = c
                                }
                            )
                            if (c == DictionaryValueHelper.nullValue) {
                                _close()
                                return DictionaryValueHelper.nullValue
                            } else {
                                head1 = c
                            }
                        }
                    }
                    value = head0
                    var hadnull = false
                    var count0 = 0
                    while (head0 == value) {
                        count0++
                        val d = child0.next()
                        SanityCheck(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:112"/*SOURCE_FILE_END*/ },
                            {
                                SanityCheck.check(
                                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:115"/*SOURCE_FILE_END*/ },
                                    { d >= debugHead0 || d == DictionaryValueHelper.nullValue }
                                )
                                debugHead0 = d
                            }
                        )
                        if (d == DictionaryValueHelper.nullValue) {
                            hadnull = true
                            break
                        } else {
                            head0 = d
                        }
                    }
                    var count1 = 0
                    while (head1 == value) {
                        count1++
                        val d = child1.next()
                        SanityCheck(
                            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:133"/*SOURCE_FILE_END*/ },
                            {
                                SanityCheck.check(
                                    { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_operator_physical/src/commonMain/kotlin/lupos/operator/physical/multiinput/POPJoinMergeSingleColumn_Iterator.kt:136"/*SOURCE_FILE_END*/ },
                                    { d >= debugHead1 || d == DictionaryValueHelper.nullValue }
                                )
                                debugHead1 = d
                            }
                        )
                        if (d == DictionaryValueHelper.nullValue) {
                            hadnull = true
                            break
                        } else {
                            head1 = d
                        }
                    }
                    counter = count0 * count1
                    if (hadnull) {
                        if (counter == 0) {
                            _close()
                        } else {
                            label = 2
                        }
                    }
                }
                counter--
                return value
            }
            2 -> {
                if (counter == 0) {
                    _close()
                    return DictionaryValueHelper.nullValue
                } else {
                    counter--
                }
                return value
            }
            else -> {
                return DictionaryValueHelper.nullValue
            }
        }
    }

    @Suppress("NOTHING_TO_INLINE")
    /*suspend*/ internal inline fun _close() {
        if (label != 0) {
            label = 0
            child0.close()
            child1.close()
        }
    }

    override /*suspend*/ fun close() {
        _close()
    }
}
