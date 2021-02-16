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

import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.ETripleIndexTypeExt
import lupos.s00misc.PartitionExt
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s01io.BufferManagerExt
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

public class TripleStoreIndexPartition(childIndex: (Int, Boolean) -> TripleStoreIndex, @JvmField private val column: Int, @JvmField public val partitionCount: Int, store_root_page_id_: Int, store_root_page_init: Boolean) : TripleStoreIndex(store_root_page_id_) {
    @JvmField
    public val bufferManager: BufferManager = BufferManagerExt.getBuffermanager("stores")
    private val partitions: Array<TripleStoreIndex>

    init {
        SanityCheck.check { partitionCount * 4 + 4 <= BufferManagerExt.getPageSize() }
        val rootPage = bufferManager.getPage(store_root_page_id)
        partitions = Array(partitionCount) { partition ->
            if (store_root_page_init) {
                val pageid = ByteArrayHelper.readInt4(rootPage, partition * 4 + 4)
                val childPage = bufferManager.getPage(pageid)
                val res = when (ByteArrayHelper.readInt4(childPage, 0)) {
                    ETripleIndexTypeExt.ID_TRIPLE -> TripleStoreIndexIDTriple(pageid, store_root_page_init)
                    else -> SanityCheck.checkUnreachable()
                }
                bufferManager.releasePage(pageid)
                res
            } else {
                ByteArrayHelper.writeInt4(rootPage, 0, ETripleIndexTypeExt.PARTITION)
                var pageid2 = -1
                bufferManager.createPage { p, pageid3 ->
                    pageid2 = pageid3
                }
                bufferManager.releasePage(pageid2)
                ByteArrayHelper.writeInt4(rootPage, partition * 4 + 4, pageid2)
                childIndex(pageid2, store_root_page_init)
            }
        }
        if (!store_root_page_init) {
            bufferManager.flushPage(store_root_page_id)
        }
        bufferManager.releasePage(store_root_page_id)
    }

    override fun dropIndex() {
        for (p in partitions) {
            p.dropIndex()
        }
        bufferManager.getPage(store_root_page_id)
        bufferManager.deletePage(store_root_page_id)
    }

    override /*suspend*/ fun getHistogram(query: IQuery, params: TripleStoreFeatureParams): Pair<Int, Int> {
        var i = -1
        val partition = (params as TripleStoreFeatureParamsPartition).partition
        for ((k, v) in partition.data) {
// this should be implemented more nice, as there is only one entry in the map
            i = v
        }
        val p2 = params.toTripleStoreFeatureParamsDefault()
        return partitions[i].getHistogram(query, p2)
    }

    override /*suspend*/ fun getIterator(query: IQuery, params: TripleStoreFeatureParams): IteratorBundle {
        var i = -1
        val partition = (params as TripleStoreFeatureParamsPartition).partition
        for ((k, v) in partition.data) {
// this should be implemented more nice, as there is only one entry in the map
            i = v
        }
        val p2 = params.toTripleStoreFeatureParamsDefault()
        return partitions[i].getIterator(query, p2)
    }

    override /*suspend*/ fun insertAsBulk(dataImport: IntArray, order: IntArray) {
        var counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = PartitionExt.hashFunction(dataImport[a + order[column]], partitionCount)
            counters[h]++
        }
        val data = Array(partitionCount) { IntArray(counters[it] * 3) }
        counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = PartitionExt.hashFunction(dataImport[a + order[column]], partitionCount)
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until partitionCount) {
            if (data[i].isNotEmpty()) {
                partitions[i].insertAsBulk(data[i], order)
            }
        }
    }

    override /*suspend*/ fun removeAsBulk(dataImport: IntArray, order: IntArray) {
        var counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = PartitionExt.hashFunction(dataImport[a + order[column]], partitionCount)
            counters[h]++
        }
        val data = Array(partitionCount) { IntArray(counters[it] * 3) }
        counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = PartitionExt.hashFunction(dataImport[a + order[column]], partitionCount)
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until partitionCount) {
            if (data[i].isNotEmpty()) {
                partitions[i].removeAsBulk(data[i], order)
            }
        }
    }

    override /*suspend*/ fun flush() {
        for (i in 0 until partitionCount) {
            partitions[i].flush()
        }
    }

    override fun insert(a: Int, b: Int, c: Int) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Int, b: Int, c: Int) {
        SanityCheck.checkUnreachable()
    }

    override /*suspend*/ fun clear() {
        for (i in 0 until partitionCount) {
            partitions[i].clear()
        }
    }

    override /*suspend*/ fun printContents() {
        for (i in 0 until partitionCount) {
            SanityCheck.println { "TripleStoreIndex_Partition :: $i" }
            partitions[i].printContents()
        }
    }
}
