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

internal class Count1PassThroughIterator(@JvmField val a: TripleIterator) : TripleIterator() {
    @JvmField
    var distinct = 0

    @JvmField
    var count = 0

    @JvmField
    var flag = 0

    init {
        if (a.hasNext()) {
            a.next()
            distinct++
            count++
            flag = 1
        }
    }

    override fun hasNext(): Boolean {
        return flag != 0
    }
    override fun close() {
        a.close()
    }
    override fun next(component: Int): DictionaryValueType {
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        flag = 0
        if (a.hasNext()) {
            flag = 1
            a.next()
            count++
            if (value[0] != a.value[0]) {
                distinct++
            }
        }
        return value[component]
    }
}
