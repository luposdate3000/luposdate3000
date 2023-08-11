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
package lupos.triple_store_id_triple.index_IDTriple

import lupos.shared.DictionaryValueType
import kotlin.jvm.JvmField

internal class MergeIterator(@JvmField val a: TripleIterator, @JvmField val b: TripleIterator) : TripleIterator() {
    // assuming no duplicates in each input
    @JvmField
    var flag = 0

    init {
        if (a.hasNext() && b.hasNext()) {
            a.next()
            b.next()
            flag = 3
        } else if (a.hasNext()) {
            value = a.value
            flag = 4
        } else if (b.hasNext()) {
            value = b.value
            flag = 5
        }
    }
override fun close(){ 
a.close()
b.close()
}
    override fun hasNext(): Boolean {
        return flag != 0
    }

    override fun next(component: Int): DictionaryValueType {
        when (flag) {
            3 -> {
                if (a.value[0] < b.value[0] || (a.value[0] == b.value[0] && a.value[1] < b.value[1]) || (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] <= b.value[2])) {
                    if (a.value[0] == b.value[0] && a.value[1] == b.value[1] && a.value[2] == b.value[2]) {
                        if (!b.hasNext()) {
                            flag = 1
                        } else {
                            b.next()
                        }
                    }
                    value[0] = a.value[0]
                    value[1] = a.value[1]
                    value[2] = a.value[2]
                    if (!a.hasNext()) {
                        flag -= 1
                    } else {
                        a.next()
                    }
                } else {
                    value[0] = b.value[0]
                    value[1] = b.value[1]
                    value[2] = b.value[2]
                    if (!b.hasNext()) {
                        flag = 1
                    } else {
                        b.next()
                    }
                }
            }
            1 -> {
                value = a.value
                flag = if (a.hasNext()) {
                    4
                } else {
                    0
                }
            }
            2 -> {
                value = b.value
                flag = if (b.hasNext()) {
                    5
                } else {
                    0
                }
            }
            4 -> {
                a.next()
                if (!a.hasNext()) {
                    flag = 0
                }
            }
            5 -> {
                b.next()
                if (!b.hasNext()) {
                    flag = 0
                }
            }
        }
        return value[component]
    }
}
