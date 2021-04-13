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

import lupos.dictionary.DictionaryExt
import lupos.s00misc.BugException
import lupos.s00misc.EIndexPattern
import lupos.s00misc.EIndexPatternExt
import lupos.s00misc.EIndexPatternHelper
import lupos.s00misc.EModifyType
import lupos.s00misc.EModifyTypeExt
import lupos.s00misc.SanityCheck
import lupos.s00misc.communicationHandler
import lupos.s04arithmetikOperators.IAOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04arithmetikOperators.noinput.IAOPConstant
import lupos.s04arithmetikOperators.noinput.IAOPVariable
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.ColumnIterator
import kotlin.jvm.JvmField

public class TripleStoreDescriptionModifyCache(type: EModifyType) : ITripleStoreDescriptionModifyCache {
    internal val allBuf = Array(indices.size) { index -> Array(indices[index].getAllLocations().size) { MyBuf() } }
    internal val allStore = index.getAllLocations()
    internal val allStoreParams = allStore.map { mapOf("key" to it.second, "idx" to EIndexPatternExt.names[index.idx_set[0]], "mode" to EModifyTypeExt.names[type], ) }.toTypedArray()
    internal val row = IntArray(3)
    internal val idx = EIndexPatternHelper.tripleIndicees[index.idx_set[0]]
    internal val allStoreLocal = allStore.map { (tripleStoreManager as TripleStoreManagerImpl).localStoresGet()[it.second]!! }.toTypedArray()
    internal fun mySend(i: Int, j: Int) {
        val buf = allBuf[i][j]
        val index = indices[i]
        val store = allStore[j]
        if (store.first == (tripleStoreManager as TripleStoreManagerImpl).localhost) {
            if (type == EModifyTypeExt.INSERT) {
                allStoreLocal[j].insertAsBulk(buf.buf, idx, buf.offset)
            } else {
                allStoreLocal[j].removeAsBulk(buf.buf, idx, buf.offset)
            }
        } else {
            val conn = communicationHandler.openConnection(store.first, "/distributed/graph/modify", allStoreParams[j])
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
