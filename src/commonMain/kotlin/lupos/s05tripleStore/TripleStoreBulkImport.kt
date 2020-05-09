package lupos.s05tripleStore

import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.EBenchmark
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.Value

class TripleStoreBulkImport {
    @JvmField
    val dictionaryBNode = MyMapStringIntPatriciaTrie()
    val sizeshift = 20
    val size = 3 * (1 shl sizeshift)
    val data = Array(9) { IntArray(size) }
    var idx = 0
    var dataSPO = data[0]
    var dataSOP = data[0]
    var dataPSO = data[0]
    var dataPOS = data[0]
    var dataOSP = data[0]
    var dataOPS = data[0]
    fun insert(si: Value, pi: Value, oi: Value) {
        data[8][idx++] = si
        data[8][idx++] = pi
        data[8][idx++] = oi
    }

    fun reset() {
        idx = 0
    }

    fun full() = idx >= size
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

    fun sort() {
        BenchmarkUtils.start(EBenchmark.IMPORT_SORT)
//the target data is sorted, but may contain duplicates, _if the input contains those
        val total = idx / 3
        var off = IntArray(3)
        var shift = IntArray(3)
        var count = IntArray(3)
        val orderSPO = intArrayOf(0, 1, 2)
        val orderSOP = intArrayOf(0, 2, 1)
        val orderPSO = intArrayOf(1, 0, 2)
        val orderPOS = intArrayOf(1, 2, 0)
        val orderOSP = intArrayOf(2, 0, 1)
        val orderOPS = intArrayOf(2, 1, 0)
        val orders = arrayOf(orderSPO, orderSOP, orderPSO, orderPOS, orderOSP, orderOPS)
        if (total == 1) {
            dataSPO = data[8]
            dataSOP = data[8]
            dataPSO = data[8]
            dataPOS = data[8]
            dataOSP = data[8]
            dataOPS = data[8]
        } else {
            for (j in 0 until 2) {
                for (i in 0 until 3) {
                    off[i] = 0
                    shift[i] = 0
                    count[i] = 1 shl shift[i]
                    var first = true
                    while (count[i] / 2 < total) {
                        off[i] = 0
                        shift[i]++
                        count[i] = 1 shl shift[i]
                        var sourceIdx = i
                        if (first) {
                            first = false
                            sourceIdx = 8
                        }
                        while (off[i] + count[i] <= total) {
                            mergeSort(data[sourceIdx], data[3 + i], off[i], count[i] / 2, count[i], orders[i * 2 + j])
                            off[i] += count[i]
                        }
                        if (off[i] < total) {
                            mergeSort(data[sourceIdx], data[3 + i], off[i], count[i] / 2, total - off[i], orders[i * 2 + j])
                        }
                        var t = data[i]
                        data[i] = data[3 + i]
                        data[3 + i] = t
                    }
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
