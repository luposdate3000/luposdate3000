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

import lupos.shared.operator.IAOPBase
import lupos.shared.operator.IOPBase

public interface ITripleStoreDescription {
    public fun modify_create_cache(query: IQuery, type: EModifyType, sortedBy: EIndexPattern, isSorted: Boolean): ITripleStoreDescriptionModifyCache
    public fun getIterator(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): IOPBase
    public fun getHistogram(query: IQuery, params: Array<IAOPBase>, idx: EIndexPattern): Pair<Int, Int>
    public fun toMetaString(): String
}
