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
package lupos.triple_store_manager

import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.IQuery
import lupos.shared.ITripleStoreIndexDescription
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.XMLElement
import kotlin.jvm.JvmField
public abstract class TripleStoreIndexDescription(@JvmField internal var instance: Luposdate3000Instance) : ITripleStoreIndexDescription {
    @JvmField
    internal var idx_set: IntArray = intArrayOf()

    @JvmField
    internal var tripleStoreDescription: TripleStoreDescription = TripleStoreDescription(arrayOf(), instance)
    internal abstract fun toByteArray(): ByteArray
    internal abstract fun assignHosts()
    internal abstract fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>>
    internal abstract fun findPartitionFor(query: IQuery, triple: DictionaryValueTypeArray): Int
    internal fun hasPattern(idx: EIndexPattern): Boolean = idx_set.contains(idx)
    internal fun getIndexWithMaximumPartitions(max_partitions: Int?, column: Int): TripleStoreIndexDescription {
        var count = -1
        val distributionCount = -1
        var currentindex: TripleStoreIndexDescription = this
        for (index in tripleStoreDescription.indices) {
            if (index.hasPattern(idx_set[0])) {
                if (index.getPartitionCount() >= count) {
                    if ((max_partitions == null || index.getPartitionCount() <= max_partitions)) {
                        if (index !is TripleStoreIndexDescriptionPartitionedByID || index.partitionColumn == column) {
                            if (count != index.getPartitionCount() || distributionCount < index.getDistributionCount()) {
                                count = index.getPartitionCount()
                                currentindex = index
                            }
                        }
                    }
                }
            }
        }
        if (count > -1) {
            return currentindex
        }
        throw Exception("no matching index found")
    }

    public override fun toXMLElement(): XMLElement {
        val res = XMLElement("TripleStoreIndexDescription")
        val manager = (instance.tripleStoreManager!!) as TripleStoreManagerImpl
        for ((k, v) in manager.metadataGet()) {
            if (v == tripleStoreDescription) {
                res.addAttribute("graphName", k)
                break
            }
        }
        res.addAttribute("pattern", EIndexPatternExt.names[idx_set[0]])
        return res
    }
}
