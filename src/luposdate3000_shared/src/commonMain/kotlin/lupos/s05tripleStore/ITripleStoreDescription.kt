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

import lupos.s00misc.EIndexPattern
import lupos.s00misc.EModifyType
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator

public interface ITripleStoreDescription {
    public fun modify_create_cache(type: EModifyType): ITripleStoreDescriptionModifyCache
    public fun modify_create_cache_sorted(type: EModifyType, sortedBy: EIndexPattern): ITripleStoreDescriptionModifyCache
    public fun modify_cache(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType, cache: ITripleStoreDescriptionModifyCache, flush: Boolean)
    public fun modify_cache_sorted(query: IQuery, columns: Array<ColumnIterator>, type: EModifyType, cache: ITripleStoreDescriptionModifyCache, sortedBy: EIndexPattern, flush: Boolean)
    public fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase
    public fun getHistogram(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
}
