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
import lupos.shared.EIndexPatternExt
import lupos.shared.EIndexPatternHelper
import lupos.shared.EModifyType
import lupos.shared.EModifyTypeExt
import lupos.shared.IMyInputStream
import lupos.shared.IMyOutputStream
import lupos.shared.IQuery
import lupos.shared.ITripleStoreDescriptionModifyCache
import lupos.shared.Luposdate3000Instance
import lupos.shared.SanityCheck

public class TripleStoreDescriptionModifyCache : ITripleStoreDescriptionModifyCache {
    private class LocalSortedInputStream(key: String, val mode: EModifyType, val idx: EIndexPattern, instance: Luposdate3000Instance) : IMyOutputStream {
        var off = 0
        val buf = DictionaryValueTypeArray(instance.LUPOS_BUFFER_SIZE / 4)
        val limit = buf.size - (buf.size % 3)
        val store = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localStoresGet()[key]!!
        override fun flush() {}
        override fun close() { }

        override fun print(x: Boolean) {
        }

        override fun print(x: Double) {
        }

        override fun print(x: Int) {
        }

        override fun print(x: String) {
        }

        override fun println() {
        }

        override fun println(x: String) {
        }

        override fun write(buf: ByteArray) {
        }

        override fun write(buf: ByteArray, len: Int) {
        }
        override fun writeInt(i: Int) {}
        override fun writeLong(value: Long) {}
        override fun writeDictionaryValueType(i: DictionaryValueType) {
            if (i != DictionaryValueHelper.nullValue) {
                if (off >= limit) {
                    if (mode == EModifyTypeExt.INSERT) {
                        store.insertAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                    } else {
                        store.removeAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                    }
                    off = 0
                }
                buf[off++] = i
            } else if (off> 0) {
                if (mode == EModifyTypeExt.INSERT) {
                    store.insertAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                } else {
                    store.removeAsBulkSorted(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                }
                off = 0
            }
        }
    }
    private class LocalInputStream(key: String, val mode: EModifyType, val idx: EIndexPattern, instance: Luposdate3000Instance) : IMyOutputStream {
        var off = 0
        val buf = DictionaryValueTypeArray(instance.LUPOS_BUFFER_SIZE / 4)
        val limit = buf.size - (buf.size % 3)
        val store = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localStoresGet()[key]!!
        override fun flush() {}
        override fun close() { }

        override fun print(x: Boolean) {
        }

        override fun print(x: Double) {
        }

        override fun print(x: Int) {
        }

        override fun print(x: String) {
        }

        override fun println() {
        }

        override fun println(x: String) {
        }

        override fun write(buf: ByteArray) {
        }

        override fun write(buf: ByteArray, len: Int) {
        }
        override fun writeInt(i: Int) {}
        override fun writeLong(value: Long) {}
        override fun writeDictionaryValueType(i: DictionaryValueType) {
            if (i != DictionaryValueHelper.nullValue) {
                if (off >= limit) {
                    if (mode == EModifyTypeExt.INSERT) {
                        store.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                    } else {
                        store.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                    }
                    off = 0
                }
                buf[off++] = i
            } else if (off> 0) {
                if (mode == EModifyTypeExt.INSERT) {
                    store.insertAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                } else {
                    store.removeAsBulk(buf, EIndexPatternHelper.tripleIndicees[idx], off)
                }
                off = 0
            }
        }
    }

// list of all indices, containing list of all distributed instances
    private val allConn: MutableList<MutableList<Pair<IMyInputStream?, IMyOutputStream>>>
    private val allIndices = mutableListOf<TripleStoreIndexDescription>()
    private val row = DictionaryValueTypeArray(3)

    public constructor(query: IQuery, description: TripleStoreDescription, type: EModifyType, sortedBy: EIndexPattern, instance: Luposdate3000Instance) {
        val localH = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localhost
        allConn = mutableListOf()
        for (index in description.indices) {
            val idx = index.idx_set[0]
            if (EIndexPatternHelper.tripleIndicees[idx][0] == EIndexPatternHelper.tripleIndicees[sortedBy][0] && EIndexPatternHelper.tripleIndicees[idx][1] == EIndexPatternHelper.tripleIndicees[sortedBy][1] && EIndexPatternHelper.tripleIndicees[idx][2] == EIndexPatternHelper.tripleIndicees[sortedBy][2]) {
                val l = mutableListOf<Pair<IMyInputStream?, IMyOutputStream>>()
                for ((host, key) in index.getAllLocations()) {
                    if (host == localH) {
                        l.add(Pair(null, LocalSortedInputStream(key, type, idx, instance)))
                    } else {
                        l.add(instance.communicationHandler!!.openConnection(host, "/distributed/graph/modifysorted", mapOf("key" to key, "idx" to EIndexPatternExt.names[idx], "mode" to EModifyTypeExt.names[type]), query.getTransactionID().toInt()))
                    }
                }
                allIndices.add(index)
                allConn.add(l)
            }
        }
    }
    public constructor(query: IQuery, description: TripleStoreDescription, type: EModifyType, instance: Luposdate3000Instance) {
        val localH = (instance.tripleStoreManager!! as TripleStoreManagerImpl).localhost
        allConn = mutableListOf()
        for (index in description.indices) {
            val idx = index.idx_set[0]
            val l = mutableListOf<Pair<IMyInputStream?, IMyOutputStream>>()
            for ((host, key) in index.getAllLocations()) {
                if (host == localH) {
                    l.add(Pair(null, LocalInputStream(key, type, idx, instance)))
                } else {
                    l.add(instance.communicationHandler!!.openConnection(host, "/distributed/graph/modify", mapOf("key" to key, "idx" to EIndexPatternExt.names[idx], "mode" to EModifyTypeExt.names[type]), query.getTransactionID().toInt()))
                }
            }
            allIndices.add(index)
            allConn.add(l)
        }
    }
    public override fun writeRow(s: DictionaryValueType, p: DictionaryValueType, o: DictionaryValueType, query: IQuery) {
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:184"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(s) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:185"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(p) })
        SanityCheck.check({ /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_triple_store_manager/src/commonMain/kotlin/lupos/triple_store_manager/TripleStoreDescriptionModifyCache.kt:186"/*SOURCE_FILE_END*/ }, { !query.getDictionary().isLocalValue(o) })
        for (i in 0 until allConn.size) {
            row[0] = s
            row[1] = p
            row[2] = o
            val j = allIndices[i].findPartitionFor(query, row)
            val conn = allConn[i][j]
            conn.second.writeDictionaryValueType(s)
            conn.second.writeDictionaryValueType(p)
            conn.second.writeDictionaryValueType(o)
        }
    }
    public override fun close() {
        for (i in 0 until allConn.size) {
            for (j in 0 until allConn[i].size) {
                val conn = allConn[i][j]
                conn.second.writeDictionaryValueType(DictionaryValueHelper.nullValue)
                conn.second.close()
                conn.first?.close()
            }
        }
    }
}
