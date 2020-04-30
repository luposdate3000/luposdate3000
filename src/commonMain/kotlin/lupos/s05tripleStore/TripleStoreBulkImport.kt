package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class TripleStoreBulkImport {
    @JvmField
    val dictionaryBNode = MyMapStringIntPatriciaTrie()
    val sizeshift = 17
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

    fun full() = idx >= size

    fun mergeSort(source: IntArray, target: IntArray, off: Int, count: Int, orderBy: IntArray) {
        //assuming that "off .. off + count / 2" and "off + count / 2 .. off + count" are sorted
        val aEnd = (off + count / 2) * 3
        val bEnd = (off + count) * 3
        var a = off * 3
        var b = aEnd
        var c = a
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
//the target data is sorted, but may contain duplicates, if the input contains those
        val total = idx / 3
        var off = 0
        var shift = 1
        var count = 1 shl shift
        val orderSPO = intArrayOf(0, 1, 2)
        val orderSOP = intArrayOf(0, 2, 1)
        val orderPSO = intArrayOf(1, 0, 2)
        val orderPOS = intArrayOf(1, 2, 0)
        val orderOSP = intArrayOf(2, 0, 1)
        val orderOPS = intArrayOf(2, 1, 0)
        val orders = arrayOf(orderSPO, orderSOP, orderPSO, orderPOS, orderOSP, orderOPS)
        for (j in 0 until 2) {
//these 3 loops can be done in parallel
            for (i in 0 until 3) {
                while (off + count < total) {
//all these can be done parallel
                    mergeSort(data[8], data[i], off, count, orders[i * 2 + j])
off+=count
                }
                if (off + count != total) {
//parallel to the one above
                    mergeSort(data[8], data[i], off, total - off, orders[i * 2 + j])
                }
                while (count < total) {
                    shift++
                    count = 1 shl shift
                    while (off + count < total) {
                        mergeSort(data[i], data[3 + i], off, count, orders[i * 2 + j])
                    }
                    if (off + count != total) {
                        mergeSort(data[i], data[3 + i], off, total - off, orders[i * 2 + j])
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
}
