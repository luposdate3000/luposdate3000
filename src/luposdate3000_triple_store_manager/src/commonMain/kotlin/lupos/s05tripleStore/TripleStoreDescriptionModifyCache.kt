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
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.communicationHandler

public class TripleStoreDescriptionModifyCache : ITripleStoreDescriptionModifyCache {

    @JvmField
    internal val description: TripleStoreDescription
    @JvmField
    internal val type: EModifyType
    internal val row = IntArray(3)

    internal val idx: Array<IntArray>
    internal val allBuf: Array<Array<MyBuf>>
    internal val allStore: Array<List<Pair<LuposHostname, LuposStoreKey>>>
    internal val allStoreParams: Array<Array<Map<String, LuposStoreKey>>>
    internal val allStoreLocal: Array<Array<TripleStoreIndex>>

    public constructor(description: TripleStoreDescription, type: EModifyType, sortedBy: EIndexPattern) {
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
        allBuf = Array(usedIndices.size) { index -> Array(usedIndices[index].getAllLocations().size) { MyBuf() } }
        allStore = Array(usedIndices.size) { usedIndices[it].getAllLocations() }
        allStoreParams = Array(allStore.size) { allStore[it].map { j -> mapOf("key" to j.second, "idx" to idx[it].toString(), "mode" to EModifyTypeExt.names[type]) }.toTypedArray() }
        allStoreLocal = Array(allStore.size) { allStore[it].map { j -> (tripleStoreManager as TripleStoreManagerImpl).localStoresGet()[j.second]!! }.toTypedArray() }
    }

    public constructor(description: TripleStoreDescription, type: EModifyType) {
        this.description = description
        this.type = type
        idx = Array(description.indices.size) { EIndexPatternHelper.tripleIndicees[description.indices[it].idx_set[0]] }
        allBuf = Array(description.indices.size) { index -> Array(description.indices[index].getAllLocations().size) { MyBuf() } }
        allStore = Array(description.indices.size) { description.indices[it].getAllLocations() }
        allStoreParams = Array(description.indices.size) { allStore[it].map { j -> mapOf("key" to j.second, "idx" to idx[it].toString(), "mode" to EModifyTypeExt.names[type]) }.toTypedArray() }
        allStoreLocal = Array(description.indices.size) { allStore[it].map { j -> (tripleStoreManager as TripleStoreManagerImpl).localStoresGet()[j.second]!! }.toTypedArray() }
    }

    internal fun mySendSorted(i: Int, j: Int, sortedBy: EIndexPattern) {
        val buf = allBuf[i][j]
        val store = allStore[i][j]
        if (store.first == (tripleStoreManager as TripleStoreManagerImpl).localhost) {
            if (type == EModifyTypeExt.INSERT) {
                allStoreLocal[i][j].insertAsBulkSorted(buf.buf, buf.offset)
            } else {
                allStoreLocal[i][j].removeAsBulkSorted(buf.buf, buf.offset)
            }
        } else {
            val conn = communicationHandler.openConnection(store.first, "/distributed/graph/modifysorted", allStoreParams[i][j])
            conn.second.writeInt(buf.offset)
            for (k in 0 until buf.offset) {
                conn.second.writeInt(buf.buf[k])
            }
            conn.second.flush()
            conn.first.close()
            conn.second.close()
        }
        buf.offset = 0
    }

    internal fun mySend(i: Int, j: Int) {
        val buf = allBuf[i][j]
        val store = allStore[i][j]
        if (store.first == (tripleStoreManager as TripleStoreManagerImpl).localhost) {
            if (type == EModifyTypeExt.INSERT) {
                allStoreLocal[i][j].insertAsBulk(buf.buf, idx[i], buf.offset)
            } else {
                allStoreLocal[i][j].removeAsBulk(buf.buf, idx[i], buf.offset)
            }
        } else {
            val conn = communicationHandler.openConnection(store.first, "/distributed/graph/modify", allStoreParams[i][j])
            conn.second.writeInt(buf.offset)
            for (k in 0 until buf.offset) {
                conn.second.writeInt(buf.buf[k])
            }
            conn.second.flush()
            conn.first.close()
            conn.second.close()
        }
        buf.offset = 0
    }
}
