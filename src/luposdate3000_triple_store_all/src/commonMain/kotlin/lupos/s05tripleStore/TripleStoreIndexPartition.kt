package lupos.s05tripleStore

import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
import kotlin.jvm.JvmField

class TripleStoreIndexPartition(childIndex: (Int) -> TripleStoreIndex, private val column: Int, @JvmField val partitionCount: Int) : TripleStoreIndex() {
    private val partitions = Array(partitionCount) { childIndex(it) }
    override /*suspend*/ fun safeToFile(filename: String) {
        val a = filename.lastIndexOf('k')
        val b = filename.substring(0, a)
        val c = filename.substring(a + 1, filename.length)
        for (i in 0 until partitionCount) {
            partitions[i].safeToFile("$b$i$c")
        }
    }

    override /*suspend*/ fun loadFromFile(filename: String) {
        val a = filename.lastIndexOf('k')
        val b = filename.substring(0, a)
        val c = filename.substring(a + 1, filename.length)
        for (i in 0 until partitionCount) {
            partitions[i].loadFromFile("$b$i$c")
        }
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
