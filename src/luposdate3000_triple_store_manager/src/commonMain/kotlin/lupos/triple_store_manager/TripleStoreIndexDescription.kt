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
import lupos.shared.operator.IOPBase
import kotlin.jvm.JvmField

public abstract class TripleStoreIndexDescription(@JvmField internal var instance: Luposdate3000Instance) : ITripleStoreIndexDescription {
    @JvmField
    internal var idx_set: IntArray = intArrayOf()

    internal lateinit var tripleStoreDescription: TripleStoreDescription
    internal abstract fun requireSplitFromStore(): Boolean
    internal abstract fun requiresPartitioning(params: Array<IOPBase>): Map<String, Int>
    internal abstract fun toByteArray(): ByteArray
    internal abstract fun assignHosts()
    internal abstract fun getAllLocations(): List<Pair<LuposHostname, LuposStoreKey>>
    internal abstract fun findPartitionFor(query: IQuery, triple: DictionaryValueTypeArray): Int
    internal fun hasPattern(idx: EIndexPattern): Boolean = idx_set.contains(idx)
internal fun getPatterns():IntArray=idx_set
    internal fun getIndexWithMaximumPartitions(max_partitions: Int?, column: Int, params: Array<IOPBase>): TripleStoreIndexDescription? {
        var count = -1
        val distributionCount = -1
        var currentindex: TripleStoreIndexDescription = this
        for (index in tripleStoreDescription.indices) {
            if (index.hasPattern(idx_set[0])) {
                if (index.getPartitionCount(params) >= count) {
                    if ((max_partitions == null || index.getPartitionCount(params) <= max_partitions)) {
                        if (index !is TripleStoreIndexDescriptionPartitionedByID || index.partitionColumn == column) {
                            if (count != index.getPartitionCount(params) || distributionCount < index.getDistributionCount()) {
                                count = index.getPartitionCount(params)
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
        return null
    }

    override fun getGraphName(): String {
        val manager = (instance.tripleStoreManager!!) as TripleStoreManagerImpl
        for ((k, v) in manager.metadataGet()) {
            if (v == tripleStoreDescription) {
                return k
            }
        }
        TODO()
    }

    override fun toXMLElement(): XMLElement {
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
