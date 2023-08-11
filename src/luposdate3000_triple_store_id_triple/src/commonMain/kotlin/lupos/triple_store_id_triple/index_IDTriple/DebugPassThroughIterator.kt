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

internal class DebugPassThroughIterator(@JvmField internal val a: TripleIterator) : TripleIterator() {
    @JvmField
    internal val queueS = mutableListOf<DictionaryValueType>()

    @JvmField
    internal val queueP = mutableListOf<DictionaryValueType>()

    @JvmField
    internal val queueO = mutableListOf<DictionaryValueType>()
override fun close(){
a.close()
}
    override fun hasNext() = a.hasNext()
    override fun next(component: Int): DictionaryValueType {
        a.next()
        value[0] = a.value[0]
        value[1] = a.value[1]
        value[2] = a.value[2]
        queueS.add(value[0])
        queueP.add(value[1])
        queueO.add(value[2])
        return value[component]
    }
}
