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
import lupos.s00misc.EIndexPatternExt
import kotlin.jvm.JvmField

public open class TripleStoreIndexDescriptionFactory : ITripleStoreIndexDescriptionFactory {
    @JvmField
    internal var res: TripleStoreIndexDescription = TripleStoreIndexDescriptionSimple(EIndexPatternExt.SPO)
    public override fun simple(idx: EIndexPattern): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionSimple(idx)
        return this
    }

    public override fun partitionedByID(idx: EIndexPattern, partitionCount: Int, partitionColumn: Int): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByID(idx, partitionCount, partitionColumn)
        return this
    }

    public override fun partitionedByKey(idx: EIndexPattern, partitionCount: Int): ITripleStoreIndexDescriptionFactory {
        res = TripleStoreIndexDescriptionPartitionedByKey(idx, partitionCount)
        return this
    }

    internal fun build(): TripleStoreIndexDescription {
        return res
    }
}
