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

import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.ITripleStoreDescriptionModifyCache
import lupos.shared.LuposHostname
import lupos.shared.LuposStoreKey
import lupos.shared.Luposdate3000Instance
import lupos.shared.TripleStoreIndex
import kotlin.jvm.JvmField

public class TripleStoreDescriptionModifyCache : ITripleStoreDescriptionModifyCache {

    @JvmField
    internal val description: TripleStoreDescription

    @JvmField
    internal val type: EModifyType

    @JvmField
    internal val row = IntArray(3)

    @JvmField
    internal val idx: Array<IntArray>

    @JvmField
    internal val allConn: Array<Array<Pair<IMyInputStream, IMyOutputStream>>>

    @JvmField
    internal val allStore: Array<List<Pair<LuposHostname, LuposStoreKey>>>

    @JvmField
    internal val allStoreParams: Array<Array<Map<String, LuposStoreKey>>>

    @JvmField
    internal val allStoreLocal: Array<Array<TripleStoreIndex?>>

    @JvmField
    internal var instance: Luposdate3000Instance

    public constructor(description: TripleStoreDescription, type: EModifyType, sortedBy: EIndexPattern, instance: Luposdate3000Instance) {
        val localStores = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).localStoresGet()
        val localH = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).localhost
        this.instance = instance
        this.description = description
        this.type = type
        val usedIndices = mutableListOf<TripleStoreIndexDescription>()
        for (d in description.indices) {
            val i = d.idx_set[0]
            if (EIndexPatternHelper.tripleIndicees[i][0] == EIndexPatternHelper.tripleIndicees[sortedBy][0] && EIndexPatternHelper.tripleIndicees[i][1] == EIndexPatternHelper.tripleIndicees[sortedBy][1] && EIndexPatternHelper.tripleIndicees[i][2] == EIndexPatternHelper.tripleIndicees[sortedBy][2]) {
                usedIndices.add(d)
            }
        }
        idx = Array(usedIndices.size) { EIndexPatternHelper.tripleIndicees[usedIndices[it].idx_set[0]] }
        allStore = Array(usedIndices.size) { usedIndices[it].getAllLocations() }
        allStoreParams = Array(allStore.size) { allStore[it].map { j -> mapOf("key" to j.second, "idx" to EIndexPatternExt.names[idx[it].first()], "mode" to EModifyTypeExt.names[type]) }.toTypedArray() }
        allStoreLocal = Array(allStore.size) {
            allStore[it].map { j ->
                if (j.first == localH) {
                    localStores[j.second]
                } else {
                    null
                }
            }.toTypedArray()
        }
        allConn = Array(usedIndices.size) { i -> Array(usedIndices[i].getAllLocations().size) { j -> instance.communicationHandler!!.openConnection(allStore[i][j].first, "/distributed/graph/modifysorted", allStoreParams[i][j]) } }
    }

    public constructor(description: TripleStoreDescription, type: EModifyType, instance: Luposdate3000Instance) {
        val localStores = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).localStoresGet()
        val localH = ((instance.tripleStoreManager!!) as TripleStoreManagerImpl).localhost
        this.instance = instance
        this.description = description
        this.type = type
        idx = Array(description.indices.size) { EIndexPatternHelper.tripleIndicees[description.indices[it].idx_set[0]] }
        allStore = Array(description.indices.size) { description.indices[it].getAllLocations() }
        allStoreParams = Array(allStore.size) { allStore[it].map { j -> mapOf("key" to j.second, "idx" to EIndexPatternExt.names[idx[it].first()], "mode" to EModifyTypeExt.names[type]) }.toTypedArray() }
        allStoreLocal = Array(allStore.size) {
            allStore[it].map { j ->
                if (j.first == localH) {
                    localStores[j.second]
                } else {
                    null
                }
            }.toTypedArray()
        }
        allConn = Array(description.indices.size) { i -> Array(description.indices[i].getAllLocations().size) { j -> instance.communicationHandler!!.openConnection(allStore[i][j].first, "/distributed/graph/modify", allStoreParams[i][j]) } }
    }
}
