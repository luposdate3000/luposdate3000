package lupos.s05tripleStore

import kotlin.jvm.JvmField
import kotlinx.coroutines.runBlocking
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EBenchmark
import lupos.s00misc.EIndexPattern
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.Query
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class TripleStoreBulkImport(@JvmField val query: Query, @JvmField val graphName: String) {
    @JvmField
    val dictionaryBNode = MyMapStringIntPatriciaTrie()

    @JvmField
    var totalflushed = 0

    @JvmField
    val sizeshift = 20

    @JvmField
    val size = 3 * (1 shl sizeshift)

    @JvmField
    val data = Array(9) { IntArray(size) }

    @JvmField
    var idx = 0

    @JvmField
    var dataSPO = data[0]

    @JvmField
    var dataSOP = data[0]

    @JvmField
    var dataPSO = data[0]

    @JvmField
    var dataPOS = data[0]

    @JvmField
    var dataOSP = data[0]

    @JvmField
    var dataOPS = data[0]
    fun insert(si: Value, pi: Value, oi: Value) {
        data[8][idx++] = si
        data[8][idx++] = pi
        data[8][idx++] = oi
        if (full()) {
            runBlocking {
                sort()
                flush()
                reset()
            }
        }
    }

    fun finishImport() {
        sort()
        flush()
    }

    fun flush() {
        totalflushed += idx / 3
        println("flushed triples $totalflushed")
        DistributedTripleStore.localStore.getNamedGraph(query, graphName).import(this)
    }

    fun reset() {
        idx = 0
    }

    fun full() = idx >= size

    companion object {
        fun mergeSort(source: IntArray, target: IntArray, off: Int, mid: Int, count: Int, orderBy: IntArray) {
            //assuming that "off .. off + count / 2" and "off + count / 2 .. off + count" are sorted
            val aEnd = (off + mid) * 3
            val bEnd = (off + count) * 3
            var a = off * 3
            var b = aEnd
            var c = a
            if (count < mid) {
                b = a
                a = aEnd
            }
            loop@ while (a < aEnd && b < bEnd) {
                for (i in 0 until 3) {
                    if (source[a + orderBy[i]] < source[b + orderBy[i]]) {
                        target[c++] = source[a++]
                        target[c++] = source[a++]
                        target[c++] = source[a++]
                        continue@loop
                    } else if (source[a + orderBy[i]] > source[b + orderBy[i]]) {
                        target[c++] = source[b++]
                        target[c++] = source[b++]
                        target[c++] = source[b++]
                        continue@loop
                    }
                }
                target[c++] = source[a++]
                target[c++] = source[a++]
                target[c++] = source[a++]
                target[c++] = source[b++]
                target[c++] = source[b++]
                target[c++] = source[b++]
            }
            while (a < aEnd) {
                target[c++] = source[a++]
                target[c++] = source[a++]
                target[c++] = source[a++]
            }
            while (b < bEnd) {
                target[c++] = source[b++]
                target[c++] = source[b++]
                target[c++] = source[b++]
            }
        }

        fun sortUsingBuffers(firstIdx: Int, dataIdxA: Int, dataIdxB: Int, data: Array<IntArray>, total: Int, order: IntArray) {
            /*in the first step the data is moved into dataIdxB*/
            var off: Int
            var shift = 0
            var count = 1 shl shift
            var first = true
            while (count / 2 < total) {
                off = 0
                shift++
                count = 1 shl shift
                var sourceIdx = dataIdxA
                if (first) {
                    first = false
                    sourceIdx = firstIdx
                }
                while (off + count <= total) {
                    mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, count, order)
                    off += count
                }
                if (off < total) {
                    mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, total - off, order)
                }
                var t = data[dataIdxA]
                data[dataIdxA] = data[dataIdxB]
                data[dataIdxB] = t
            }
        }
    }

    fun sort() {
        BenchmarkUtils.start(EBenchmark.IMPORT_SORT)
        //the target data is sorted, but may contain duplicates, _if the input contains those
        val total = idx / 3
        val orderSPO = EIndexPattern.SPO.tripleIndicees
        val orderSOP = EIndexPattern.SOP.tripleIndicees
        val orderPSO = EIndexPattern.PSO.tripleIndicees
        val orderPOS = EIndexPattern.POS.tripleIndicees
        val orderOSP = EIndexPattern.OSP.tripleIndicees
        val orderOPS = EIndexPattern.OPS.tripleIndicees
        val orders = arrayOf(orderSPO, orderSOP, orderPSO, orderPOS, orderOSP, orderOPS)
        if (total <= 1) {
            dataSPO = data[8]
            dataSOP = data[8]
            dataPSO = data[8]
            dataPOS = data[8]
            dataOSP = data[8]
            dataOPS = data[8]
        } else {
            for (j in 0 until 2) {
                for (i in 0 until 3) {
                    val order = orders[i * 2 + j]
                    val firstIdx = 8
                    val dataIdxA = i
                    val dataIdxB = i + 3
                    sortUsingBuffers(firstIdx, dataIdxA, dataIdxB, data, total, order)
                }
                if (j == 0) {
                    dataSPO = data[0]
                    dataPSO = data[1]
                    dataOSP = data[2]
                    data[0] = data[6]
                    data[1] = data[7]
                    data[2] = data[8]
                    data[6] = dataSPO
                    data[7] = dataPSO
                    data[8] = dataOSP
                } else {
                    dataSOP = data[0]
                    dataPOS = data[1]
                    dataOPS = data[2]
                }
            }
        }
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_SORT)
    }
}
