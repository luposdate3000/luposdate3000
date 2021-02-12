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

import lupos.s00misc.EModifyType
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle

public interface ITripleStoreLocalBase {
    public fun clear()
    public fun getEnabledPartitions(): Array<EnabledPartitionContainer>
    /*suspend*/ public fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int>
    /*suspend*/ public fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle
    /*suspend*/ public fun import(dataImport: ITripleStoreBulkImport)
    /*suspend*/ public fun modify(query: IQuery, dataModify: Array<ColumnIterator>, type: EModifyType)
}
