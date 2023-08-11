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

internal class MinusIterator(@JvmField val a: TripleIterator, @JvmField val b: TripleIterator) : TripleIterator() {
    @JvmField
    var flag = 1

    @JvmField
    var useMinus = true

    init {
        if (b.hasNext()) {
            b.next()
        } else {
            useMinus = false
        }
        nextInternal()
    }

    private fun nextInternal() {
        fun nextB() {
            if (b.hasNext()) {
                b.next()
            } else {
                useMinus = false
            }
        }

        fun nextA() {
            if (a.hasNext()) {
                a.next()
            } else {
                flag = 0
b.close()
            }
        }
        nextA()
        while (useMinus && flag != 0) {
            if (b.value[0] < a.value[0]) {
                nextB()
            } else if (b.value[0] > a.value[0]) {
                break
            } else if (b.value[1] < a.value[1]) {
                nextB()
            } else if (b.value[1] > a.value[1]) {
                break
            } else if (b.value[2] < a.value[2]) {
                nextB()
            } else if (b.value[2] > a.value[2]) {
                break
            } else {
                nextA()
            }
        }
    }
override fun close(){
a.close()
b.close()
}
    override fun hasNext() = flag != 0
    override fun next(component: Int): DictionaryValueType {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        nextInternal()
        return value[component]
    }
}
