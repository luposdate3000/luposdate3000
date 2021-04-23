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

import lupos.operator.logical.iterator.ColumnIterator
import lupos.shared.dictionary.DictionaryExt
import kotlin.jvm.JvmField

internal class POPJoinMergeSingleColumn_Iterator(@JvmField internal val child0: ColumnIterator, @JvmField internal val child1: ColumnIterator, @JvmField internal var head0: Int, @JvmField internal var head1: Int) : ColumnIterator() {
    @JvmField
    internal var counter: Int = 0

    @JvmField
    internal var value: Int = head0

    @JvmField
    internal var label = 1

    @JvmField
    internal var sipbuf = IntArray(2)
    override /*suspend*/ fun next(): Int {
        when (label) {
            1 -> {
                if (counter == 0) {
                    var change = true
                    while (change) {
                        change = false
                        while (head0 < head1) {
                            child0.nextSIP(head1, sipbuf)
                            val c = sipbuf[1]
                            if (c == DictionaryExt.nullValue) {
                                _close()
                                return DictionaryExt.nullValue
                            } else {
                                head0 = c
                            }
                        }
                        while (head1 < head0) {
                            change = true
                            child1.nextSIP(head0, sipbuf)
                            val c = sipbuf[1]
                            if (c == DictionaryExt.nullValue) {
                                _close()
                                return DictionaryExt.nullValue
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
                        if (d == DictionaryExt.nullValue) {
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
                        if (d == DictionaryExt.nullValue) {
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
                    return DictionaryExt.nullValue
                } else {
                    counter--
                }
                return value
            }
            else -> {
                return DictionaryExt.nullValue
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
