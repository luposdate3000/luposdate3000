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
package lupos.s05tripleStore

import lupos.s00misc.HistogramNotImplementedException
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

public abstract class TripleStoreIndex(@JvmField public val store_root_page_id: Int) {
    public /*suspend*/ abstract fun getIterator(query: IQuery, filter: IntArray, projection: List<String>): IteratorBundle
    public /*suspend*/ abstract fun clear()
    public /*suspend*/ abstract fun flush()
    public /*suspend*/ open fun getHistogram(query: IQuery, filter: IntArray): Pair<Int, Int> = throw HistogramNotImplementedException("TripleStoreIndex")
    public /*suspend*/ abstract fun insertAsBulk(data: IntArray, order: IntArray, dataSize: Int)
    public /*suspend*/ abstract fun removeAsBulk(data: IntArray, order: IntArray, dataSize: Int)
}
