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

internal class FilterFunc(val j: Int, val index: TripleStoreIndexDescription, val action: (DictionaryValueTypeArray, Int, TripleStoreIndexDescription) -> Boolean)

internal class TripleStoreDescriptionModifyCacheConnection(val input: IMyInputStream?, val output: IMyOutputStream, val filters: MutableList<FilterFunc>)
public class TripleStoreDescriptionModifyCacheFilterEntry(public val host: String, public val key: String, public val idx: Int)
public class TripleStoreDescriptionModifyCache : ITripleStoreDescriptionModifyCache {
    private val row = DictionaryValueTypeArray(3)
    private val allConn = mutableListOf<TripleStoreDescriptionModifyCacheConnection>()

    public constructor(
        query: IQuery,
        description: TripleStoreDescription,
        type: EModifyType,
        sortedBy: EIndexPattern,
        instance: Luposdate3000Instance,
        isSorted: Boolean,
        filteredBy: List<TripleStoreDescriptionModifyCacheFilterEntry>?,
    ) {
        println("TripleStoreDescriptionModifyCache.constructor start $type $sortedBy $isSorted $filteredBy")
        val allConnLocal = mutableListOf<TripleStoreDescriptionModifyCacheConnection>()
        val allConnMap = mutableMapOf<String, TripleStoreDescriptionModifyCacheConnection>() // address -> real-targets
        val localH = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localhost
        for (index in description.indices) {
            val idx = index.idx_set[0]
            println("TripleStoreDescriptionModifyCache.constructor for index $idx")
            if (!isSorted || (EIndexPatternHelper.tripleIndicees[idx][0] == EIndexPatternHelper.tripleIndicees[sortedBy][0] && EIndexPatternHelper.tripleIndicees[idx][1] == EIndexPatternHelper.tripleIndicees[sortedBy][1] && EIndexPatternHelper.tripleIndicees[idx][2] == EIndexPatternHelper.tripleIndicees[sortedBy][2])) {
                println("TripleStoreDescriptionModifyCache.constructor sortedFilter passed")
                var j = 0
                for ((host, key) in index.getAllLocations()) {
                    println("TripleStoreDescriptionModifyCache.constructor location $host $key")
                    var flag: Boolean
                    if (filteredBy == null) {
                        flag = true
                    } else {
                        for (it in filteredBy) {
                            if (it.host == host && it.key == key && it.idx == idx) {
                                flag = true
                                break
                            }
                        }
                        flag = false
                    }
                    if (flag) {
                        println("TripleStoreDescriptionModifyCache.constructor filters passed")
                        val filter = FilterFunc(
                            j,
                            index,
                            { row2, j2, index2 ->
                                println("TripleStoreDescriptionModifyCache.constructor filterfunc $j")
                                index2.findPartitionFor(query, row2) == j2
                            }
                        )
                        if (host == localH) {
                            println("TripleStoreDescriptionModifyCache.constructor localCacheConnection")
                            allConnLocal.add(TripleStoreDescriptionModifyCacheConnection(null, TripleStoreDescriptionModifyCacheLocalInputStream(key, type, idx, instance, isSorted), mutableListOf(filter)))
                        } else {
                            println("TripleStoreDescriptionModifyCache.constructor remoteCacheConnection")
                            var conn = allConnMap[host]
                            if (conn == null) {
                                println("TripleStoreDescriptionModifyCache.constructor first connection to $host")
                                val tmp = instance.communicationHandler!!.openConnection(host, "/distributed/graph/modify", mapOf("graph" to description.graph, "sortedBy" to "$sortedBy", "isSorted" to "$isSorted", "mode" to EModifyTypeExt.names[type]), query.getTransactionID().toInt())
                                conn = TripleStoreDescriptionModifyCacheConnection(tmp.first, tmp.second, mutableListOf())
                                allConnMap[host] = conn
                            }
                            conn.filters.add(filter)
                            val bytes = host.encodeToByteArray()
                            conn.output.writeInt(bytes.size)
                            conn.output.write(bytes)
                            val bytes2 = key.encodeToByteArray()
                            conn.output.writeInt(bytes2.size)
                            conn.output.write(bytes2)
                            conn.output.writeInt(idx)
                        }
                        j++
                    }
                }
            }
        }
        for (conn in allConnMap.values) {
            conn.output.writeInt(-1)
            allConn.add(conn)
        }
        allConn.addAll(allConnLocal)
        println("TripleStoreDescriptionModifyCache.constructor end ${allConn.size}")
    }

    public override fun writeRow(s: DictionaryValueType, p: DictionaryValueType, o: DictionaryValueType, query: IQuery) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:117"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(s) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:118"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(p) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:119"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(o) })
        println("TripleStoreDescriptionModifyCache.writeRow $s $p $o")
        loop@ for (c in allConn) {
            println("TripleStoreDescriptionModifyCache.writeRow for conn")
            row[0] = s
            row[1] = p
            row[2] = o
            for (f in c.filters) {
                println("TripleStoreDescriptionModifyCache.writeRow check filter")
                if (f.action(row, f.j, f.index)) {
                    println("TripleStoreDescriptionModifyCache.writeRow filter passed")
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
            c.output.writeDictionaryValueType(DictionaryValueHelper.nullValue)
            c.output.close()
            c.input?.close()
        }
    }
}
