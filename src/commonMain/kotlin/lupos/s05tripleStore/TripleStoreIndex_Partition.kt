package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.File
import lupos.s00misc.Partition
import lupos.s00misc.ReadWriteLock
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.Query

class TripleStoreIndex_Partition(childIndex: (Int) -> TripleStoreIndex, val column: Int) : TripleStoreIndex() {
    val partitions = Array(Partition.k) { childIndex(it) }
    suspend override fun safeToFile(filename: String) {
        val a = filename.lastIndexOf('k')
        val b = filename.substring(0, a)
        val c = filename.substring(a + 1, filename.length)
        for (i in 0 until Partition.k) {
            partitions[i].safeToFile("$b$i$c")
        }
    }

    suspend override fun loadFromFile(filename: String) {
        val a = filename.lastIndexOf('k')
        val b = filename.substring(0, a)
        val c = filename.substring(a + 1, filename.length)
        for (i in 0 until Partition.k) {
            partitions[i].loadFromFile("$b$i$c")
        }
    }

    suspend override fun getHistogram(query: Query, params: TripleStoreFeatureParams): Pair<Int, Int> {
        var i = -1
        val partition = (params as TripleStoreFeatureParamsPartition).partition
        for ((k, v) in partition.data) {
//this should be implemented more nice, as there is only one entry in the map
            i = v
        }
        val p2 = params.toTripleStoreFeatureParamsDefault()
        return partitions[i].getHistogram(query, p2)
    }

    suspend override fun getIterator(query: Query, params: TripleStoreFeatureParams): IteratorBundle {
        var i = -1
        val partition = (params as TripleStoreFeatureParamsPartition).partition
        for ((k, v) in partition.data) {
//this should be implemented more nice, as there is only one entry in the map
            i = v
        }
        val p2 = params.toTripleStoreFeatureParamsDefault()
        return partitions[i].getIterator(query, p2)
    }

    suspend override fun import(dataImport: IntArray, count: Int, order: IntArray) {
        var counters = IntArray(Partition.k)
        for (i in 0 until count / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            SanityCheck.println({ "partitioning by ${dataImport[a + order[column]]} -> $h" })
            counters[h]++
        }
        val data = Array(Partition.k) { IntArray(counters[it] * 3) }
        counters = IntArray(Partition.k)
        for (i in 0 until count / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until Partition.k) {
            if (data[i].size > 0) {
                partitions[i].import(data[i], data[i].size, order)
            }
        }
    }

    suspend override fun insertAsBulk(dataImport: IntArray, order: IntArray) {
        var counters = IntArray(Partition.k)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            counters[h]++
        }
        val data = Array(Partition.k) { IntArray(counters[it] * 3) }
        counters = IntArray(Partition.k)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until Partition.k) {
            if (data[i].size > 0) {
                partitions[i].insertAsBulk(data[i], order)
            }
        }
    }

    suspend override fun removeAsBulk(dataImport: IntArray, order: IntArray) {
        var counters = IntArray(Partition.k)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            counters[h]++
        }
        val data = Array(Partition.k) { IntArray(counters[it] * 3) }
        counters = IntArray(Partition.k)
        for (i in 0 until dataImport.size / 3) {
            val a = i * 3
            val h = Partition.hashFunction(dataImport[a + order[column]])
            val b = counters[h] * 3
            counters[h]++
            for (j in 0 until 3) {
                data[h][b + j] = dataImport[a + j]
            }
        }
        for (i in 0 until Partition.k) {
            if (data[i].size > 0) {
                partitions[i].removeAsBulk(data[i], order)
            }
        }
    }

    suspend override fun flush() {
        for (i in 0 until Partition.k) {
            partitions[i].flush()
        }
    }

    override fun insert(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    override fun remove(a: Value, b: Value, c: Value) {
        SanityCheck.checkUnreachable()
    }

    suspend override fun clear() {
        for (i in 0 until Partition.k) {
            partitions[i].clear()
        }
    }

    suspend override fun printContents() {
        for (i in 0 until Partition.k) {
            SanityCheck.println({ "TripleStoreIndex_Partition :: " + i })
            partitions[i].printContents()
        }
    }
}
