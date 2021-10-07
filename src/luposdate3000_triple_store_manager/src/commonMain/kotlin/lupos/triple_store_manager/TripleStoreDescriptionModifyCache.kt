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

import lupos.shared.DictionaryValueHelper
import lupos.shared.DictionaryValueType
import lupos.shared.DictionaryValueTypeArray
import lupos.shared.EIndexPattern
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescriptionModifyCache
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck
import lupos.shared.dynamicArray.ByteArrayWrapper
import lupos.shared.inline.ByteArrayHelper
import lupos.shared.inline.dynamicArray.ByteArrayWrapperExt

internal class FilterFunc(val j: Int, val index: TripleStoreIndexDescription, val action: (DictionaryValueTypeArray, Int, TripleStoreIndexDescription) -> Boolean)

internal class TripleStoreDescriptionModifyCacheConnection(val input: IMyInputStream?, val output: IMyOutputStream, val filters: MutableList<FilterFunc>, val isLocal: Boolean) {
    internal var hadInit = false
    internal var header = ByteArrayWrapper()
}
public class TripleStoreDescriptionModifyCacheFilterEntry(public val host: String, public val key: String, public val idx: Int)
public class TripleStoreDescriptionModifyCache : ITripleStoreDescriptionModifyCache {
    private val row = DictionaryValueTypeArray(3)
    private val allConn = mutableListOf<TripleStoreDescriptionModifyCacheConnection>()
    public companion object {
        internal var debug_ctr_global = 0
    }
    public constructor(
        query: IQuery,
        description: TripleStoreDescription,
        type: EModifyType,
        sortedBy: EIndexPattern,
        instance: Luposdate3000Instance,
        isSorted: Boolean,
        filteredBy: List<TripleStoreDescriptionModifyCacheFilterEntry>?,
    ) {
        val debug_ctr = debug_ctr_global++
        val allConnLocal = mutableListOf<TripleStoreDescriptionModifyCacheConnection>()
        val allConnMap = mutableMapOf<String, TripleStoreDescriptionModifyCacheConnection>() // address -> real-targets
        val localH = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localhost
        val allTargetsList = mutableSetOf<Int>()
            for (index in description.indices) {
                val idx = index.idx_set[0]
                if (!isSorted || (EIndexPatternHelper.tripleIndicees[idx][0] == EIndexPatternHelper.tripleIndicees[sortedBy][0] && EIndexPatternHelper.tripleIndicees[idx][1] == EIndexPatternHelper.tripleIndicees[sortedBy][1] && EIndexPatternHelper.tripleIndicees[idx][2] == EIndexPatternHelper.tripleIndicees[sortedBy][2])) {
                    var j = 0
                    for ((host, key) in index.getAllLocations()) {
                        allTargetsList.add(instance.LUPOS_PROCESS_URLS_ALL.indexOf(host))
                    }
                }
        }
        val allTargets = allTargetsList.toIntArray()
        val allTargetsHops = instance.LUPOS_PROCESS_URLS_ALL_NEXT_HOP(allTargets)
        for (index in description.indices) {
            val idx = index.idx_set[0]
            if (!isSorted || (EIndexPatternHelper.tripleIndicees[idx][0] == EIndexPatternHelper.tripleIndicees[sortedBy][0] && EIndexPatternHelper.tripleIndicees[idx][1] == EIndexPatternHelper.tripleIndicees[sortedBy][1] && EIndexPatternHelper.tripleIndicees[idx][2] == EIndexPatternHelper.tripleIndicees[sortedBy][2])) {
                var j = 0
                for ((host, key) in index.getAllLocations()) {
                    val host2 =                        instance.LUPOS_PROCESS_URLS_ALL[allTargetsHops[allTargets.indexOf(instance.LUPOS_PROCESS_URLS_ALL.indexOf(host))]]
                    var flag: Boolean
                    if (filteredBy == null) {
                        flag = true
                    } else {
                        flag = false
                        for (it in filteredBy) {
                            val h: String = it.host
                            val k: String = it.key
                            val i: Int = it.idx
                            if (h == host && // !!
                                k == key &&
                                i == idx
                            ) {
                                flag = true
                                break
                            }
                        }
                    }
                    if (flag) {
                        val filter = FilterFunc(
                            j,
                            index,
                            { row2, j2, index2 ->
                                index2.findPartitionFor(query, row2) == j2
                            }
                        )
                        if (host == localH) {
                            allConnLocal.add(TripleStoreDescriptionModifyCacheConnection(null, TripleStoreDescriptionModifyCacheLocalInputStream(key, type, idx, instance, isSorted, j), mutableListOf(filter), true))
                        } else {
                            var conn = allConnMap[host2]
                            if (conn == null) {
                                val tmp = instance.communicationHandler!!.openConnection(host2, "/distributed/graph/modify", mapOf("graph" to description.graph, "sortedBy" to "$sortedBy", "isSorted" to "$isSorted", "mode" to EModifyTypeExt.names[type]), query.getTransactionID().toInt())
                                conn = TripleStoreDescriptionModifyCacheConnection(tmp.first, tmp.second, mutableListOf(), false)
                                allConnMap[host2] = conn
                            }
                            conn.filters.add(filter)
                            val bytes = host.encodeToByteArray()
                            val bytes2 = key.encodeToByteArray()
                            var off = ByteArrayWrapperExt.getSize(conn.header)
                            ByteArrayWrapperExt.setSize(conn.header, off + 4 + 4 + 4 + bytes.size + bytes2.size, true)
                            val buf = ByteArrayWrapperExt.getBuf(conn.header) // after changing the size
                            ByteArrayHelper.writeInt4(buf, off, bytes.size)
                            off += 4
                            bytes.copyInto(buf, off)
                            off += bytes.size
                            ByteArrayHelper.writeInt4(buf, off, bytes2.size)
                            off += 4
                            bytes2.copyInto(buf, off)
                            off += bytes2.size
                            ByteArrayHelper.writeInt4(buf, off, idx)
                        }
                    }
                    j++
                }
            }
        }
        for (conn in allConnMap.values) {
            var off = ByteArrayWrapperExt.getSize(conn.header)
            ByteArrayWrapperExt.setSize(conn.header, off + 4, true)
            val buf = ByteArrayWrapperExt.getBuf(conn.header) // after changing the size
            ByteArrayHelper.writeInt4(buf, off, -1)
            allConn.add(conn)
        }
        allConn.addAll(allConnLocal)
    }

    public override fun writeRow(s: DictionaryValueType, p: DictionaryValueType, o: DictionaryValueType, query: IQuery) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:152"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(s) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:153"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(p) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:154"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(o) })
        var i = 0
        loop@ for (c in allConn) {
            i++
            row[0] = s
            row[1] = p
            row[2] = o
            for (f in c.filters) {
                if (f.action(row, f.j, f.index)) {
                    if (!c.hadInit) {
                        c.hadInit = true
                        c.output.write(ByteArrayWrapperExt.getBuf(c.header), ByteArrayWrapperExt.getSize(c.header))
                    }
                    c.output.writeDictionaryValueType(s)
                    c.output.writeDictionaryValueType(p)
                    c.output.writeDictionaryValueType(o)
                    continue@loop
                }
            }
        }
    }

    public override fun close() {
        for (c in allConn) {
            if (c.hadInit) {
                c.output.writeDictionaryValueType(DictionaryValueHelper.nullValue)
            } else if (c.isLocal) {
                c.output.write(ByteArrayWrapperExt.getBuf(c.header), ByteArrayWrapperExt.getSize(c.header))
            }
            c.output.close()
            c.input?.close()
        }
    }
}
