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
package lupos.shared

import lupos.ProguardTestAnnotation
import lupos.shared.operator.iterator.IteratorBundle

public interface TripleStoreIndex {
    public fun getRootPageID(): Int
    public fun delete()
    public fun getIterator(query: IQuery, filter: DictionaryValueTypeArray, projection: List<String>): IteratorBundle
    public fun clear()
    public fun flush()
    public fun getHistogram(query: IQuery, filter: DictionaryValueTypeArray): Pair<Int, Int>
    public fun insertAsBulk(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int, isSorted: Boolean)
    public fun removeAsBulk(data: DictionaryValueTypeArray, order: IntArray, dataSize: Int, isSorted: Boolean)

    @ProguardTestAnnotation
    public fun close()
}
