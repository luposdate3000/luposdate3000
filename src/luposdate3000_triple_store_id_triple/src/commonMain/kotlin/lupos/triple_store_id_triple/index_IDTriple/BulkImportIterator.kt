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

import kotlin.jvm.JvmField
import lupos.shared.SanityCheck
internal class BulkImportIterator(@JvmField val data: IntArray, @JvmField val count: Int, @JvmField val order: IntArray) : TripleIterator() {
    @JvmField
    var offset = 0
    override fun hasNext(): Boolean {
        return offset < count
    }

    override fun next(component: Int): Int {
SanityCheck.check{SanityCheck.ignoreTripleFlag||((data[offset] and SanityCheck.TRIPLE_FLAG_S) != SanityCheck.TRIPLE_FLAG_S)}
SanityCheck.check{SanityCheck.ignoreTripleFlag||((data[offset+1] and SanityCheck.TRIPLE_FLAG_P) != SanityCheck.TRIPLE_FLAG_P)}
SanityCheck.check{SanityCheck.ignoreTripleFlag||((data[offset+2] and SanityCheck.TRIPLE_FLAG_O) != SanityCheck.TRIPLE_FLAG_O)}
        value[0] = data[offset + order[0]]
        value[1] = data[offset + order[1]]
        value[2] = data[offset + order[2]]
        offset += 3
        return value[component]
    }
}
