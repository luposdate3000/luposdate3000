package lupos.s05tripleStore
import lupos.s00misc.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.Partition
import lupos.s00misc.ETripleIndexType
import lupos.s00misc.SanityCheck
import lupos.s01io.BufferManager
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField
class TripleStoreIndexPartition(childIndex: (Int, Boolean) -> TripleStoreIndex, @JvmField private val column: Int, @JvmField val partitionCount: Int, store_root_page_id_: Int, store_root_page_init: Boolean) : TripleStoreIndex(store_root_page_id_) {
@JvmField
        val bufferManager = BufferManager.getBuffermanager("stores")

    private val partitions: Array<TripleStoreIndex>
    init {
        SanityCheck.check { partitionCount * 4+4 <= BUFFER_MANAGER_PAGE_SIZE_IN_BYTES }
        val rootPage = bufferManager.getPage(store_root_page_id)
  ByteArrayHelper.writeInt4(rootPage,0,ETripleIndexType.PARTITION.ordinal)
        partitions = Array(partitionCount) { partition ->
            if (store_root_page_init) {
                val pageid=ByteArrayHelper.readInt4(rootPage, partition * 4+4)
val childPage=bufferManager.getPage(pageid)
val type=ETripleIndexType.values()[ByteArrayHelper.readInt4(childPage,0)]
var res=when(type){
ETripleIndexType.ID_TRIPLE->TripleStoreIndexIDTriple(pageid,store_root_page_init)
else->throw Exception("")
}
bufferManager.releasePage(pageid)
res
            } else {
                var pageid2 = -1
                bufferManager.createPage { p, pageid3 ->
                    pageid2 = pageid3
                }
                bufferManager.releasePage(pageid2)
                ByteArrayHelper.writeInt4(rootPage, partition * 4+4, pageid2)
            childIndex(pageid2, store_root_page_init)
            }
        }
        bufferManager.releasePage(store_root_page_id)
    }
override fun dropIndex(){
for(p in partitions){
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
    override /*suspend*/ fun import(dataImport: IntArray, count: Int, order: IntArray) {
        var counters = IntArray(partitionCount)
        for (i in 0 until count / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
            SanityCheck.println { "partitioning by ${dataImport[a + order[column]]} -> $h" }
            counters[h]++
        }
        val data = Array(partitionCount) { IntArray(counters[it] * 3) }
        counters = IntArray(partitionCount)
        for (i in 0 until count / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until partitionCount) {
            if (data[i].isNotEmpty()) {
                partitions[i].import(data[i], data[i].size, order)
            }
        }
    }
    override /*suspend*/ fun insertAsBulk(dataImport: IntArray, order: IntArray) {
        var counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
            counters[h]++
        }
        val data = Array(partitionCount) { IntArray(counters[it] * 3) }
        counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
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
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
            counters[h]++
        }
        val data = Array(partitionCount) { IntArray(counters[it] * 3) }
        counters = IntArray(partitionCount)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]], partitionCount)
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
