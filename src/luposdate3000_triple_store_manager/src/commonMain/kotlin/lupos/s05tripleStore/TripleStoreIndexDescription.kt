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
import kotlin.jvm.JvmField

public abstract class TripleStoreIndexDescription(
    @JvmField internal val idx: EIndexPattern,
) : ITripleStoreIndexDescription {
    @JvmField
    internal var tripleStoreDescription: TripleStoreDescription = TripleStoreDescriptionDummy
    internal abstract fun assignHosts()
    internal abstract fun releaseHosts()
    internal abstract fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>>
    internal fun getIndexWithMaximumPartitions(max_partitions: Int?, column: Int): ITripleStoreIndexDescription {
        var count = -1
        var currentindex: TripleStoreIndexDescription = this
        for (index in tripleStoreDescription.indices) {
            if (index.idx == idx &&
                (
                    index is TripleStoreIndexDescriptionPartitionedByID &&
                        (max_partitions == null || index.partitionCount < max_partitions) &&
                        index.partitionCount > count &&
                        index.partitionColumn == column
                    ) || (
                    index.getPartitionCount() == 1 &&
                        1 < count
                    )
            ) {
                count = index.getPartitionCount()
                currentindex = index
            }
        }
        if (count > -1) {
            return currentindex
        }
        throw Exception("no matching index found")
    }
}
