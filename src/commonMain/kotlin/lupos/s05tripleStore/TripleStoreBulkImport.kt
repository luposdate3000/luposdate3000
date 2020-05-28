package lupos.s05tripleStore
import lupos.s00misc.BenchmarkUtils
import lupos.s00misc.Coverage
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
Coverage.funStart(6705)
        data[8][idx++] = si
Coverage.statementStart(6706)
        data[8][idx++] = pi
Coverage.statementStart(6707)
        data[8][idx++] = oi
Coverage.statementStart(6708)
    }
    fun reset() {
Coverage.funStart(6709)
        idx = 0
Coverage.statementStart(6710)
    }
    fun full() = idx >= size
    companion object {
        fun mergeSort(source: IntArray, target: IntArray, off: Int, mid: Int, count: Int, orderBy: IntArray) {
Coverage.funStart(6711)
            //assuming that "off .. off + count / 2" and "off + count / 2 .. off + count" are sorted
Coverage.statementStart(6712)
            val aEnd = (off + mid) * 3
Coverage.statementStart(6713)
            val bEnd = (off + count) * 3
Coverage.statementStart(6714)
            var a = off * 3
Coverage.statementStart(6715)
            var b = aEnd
Coverage.statementStart(6716)
            var c = a
Coverage.statementStart(6717)
            if (count < mid) {
Coverage.ifStart(6718)
                b = a
Coverage.statementStart(6719)
                a = aEnd
Coverage.statementStart(6720)
            }
Coverage.statementStart(6721)
            loop@ while (a < aEnd && b < bEnd) {
Coverage.whileLoopStart(6722)
                for (i in 0 until 3) {
Coverage.forLoopStart(6723)
                    if (source[a + orderBy[i]] < source[b + orderBy[i]]) {
Coverage.ifStart(6724)
                        target[c++] = source[a++]
Coverage.statementStart(6725)
                        target[c++] = source[a++]
Coverage.statementStart(6726)
                        target[c++] = source[a++]
Coverage.statementStart(6727)
                        continue@loop
                    } else if (source[a + orderBy[i]] > source[b + orderBy[i]]) {
Coverage.statementStart(6728)
                        target[c++] = source[b++]
Coverage.statementStart(6729)
                        target[c++] = source[b++]
Coverage.statementStart(6730)
                        target[c++] = source[b++]
Coverage.statementStart(6731)
                        continue@loop
                    }
Coverage.statementStart(6732)
                }
Coverage.statementStart(6733)
                target[c++] = source[a++]
Coverage.statementStart(6734)
                target[c++] = source[a++]
Coverage.statementStart(6735)
                target[c++] = source[a++]
Coverage.statementStart(6736)
                target[c++] = source[b++]
Coverage.statementStart(6737)
                target[c++] = source[b++]
Coverage.statementStart(6738)
                target[c++] = source[b++]
Coverage.statementStart(6739)
            }
Coverage.statementStart(6740)
            while (a < aEnd) {
Coverage.whileLoopStart(6741)
                target[c++] = source[a++]
Coverage.statementStart(6742)
                target[c++] = source[a++]
Coverage.statementStart(6743)
                target[c++] = source[a++]
Coverage.statementStart(6744)
            }
Coverage.statementStart(6745)
            while (b < bEnd) {
Coverage.whileLoopStart(6746)
                target[c++] = source[b++]
Coverage.statementStart(6747)
                target[c++] = source[b++]
Coverage.statementStart(6748)
                target[c++] = source[b++]
Coverage.statementStart(6749)
            }
Coverage.statementStart(6750)
        }
        fun sortUsingBuffers(firstIdx: Int, dataIdxA: Int, dataIdxB: Int, data: Array<IntArray>, total: Int, order: IntArray) {
Coverage.funStart(6751)
/*in the first step the data is moved into dataIdxB*/
Coverage.statementStart(6752)
            var off: Int
Coverage.statementStart(6753)
            var shift = 0
Coverage.statementStart(6754)
            var count = 1 shl shift
Coverage.statementStart(6755)
            var first = true
Coverage.statementStart(6756)
            while (count / 2 < total) {
Coverage.whileLoopStart(6757)
                off = 0
Coverage.statementStart(6758)
                shift++
Coverage.statementStart(6759)
                count = 1 shl shift
Coverage.statementStart(6760)
                var sourceIdx = dataIdxA
Coverage.statementStart(6761)
                if (first) {
Coverage.ifStart(6762)
                    first = false
Coverage.statementStart(6763)
                    sourceIdx = firstIdx
Coverage.statementStart(6764)
                }
Coverage.statementStart(6765)
                while (off + count <= total) {
Coverage.whileLoopStart(6766)
                    mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, count, order)
Coverage.statementStart(6767)
                    off += count
Coverage.statementStart(6768)
                }
Coverage.statementStart(6769)
                if (off < total) {
Coverage.ifStart(6770)
                    mergeSort(data[sourceIdx], data[dataIdxB], off, count / 2, total - off, order)
Coverage.statementStart(6771)
                }
Coverage.statementStart(6772)
                var t = data[dataIdxA]
Coverage.statementStart(6773)
                data[dataIdxA] = data[dataIdxB]
Coverage.statementStart(6774)
                data[dataIdxB] = t
Coverage.statementStart(6775)
            }
Coverage.statementStart(6776)
        }
    }
    fun sort() {
Coverage.funStart(6777)
        BenchmarkUtils.start(EBenchmark.IMPORT_SORT)
Coverage.statementStart(6778)
        //the target data is sorted, but may contain duplicates, _if the input contains those
Coverage.statementStart(6779)
        val total = idx / 3
Coverage.statementStart(6780)
        val orderSPO = intArrayOf(0, 1, 2)
Coverage.statementStart(6781)
        val orderSOP = intArrayOf(0, 2, 1)
Coverage.statementStart(6782)
        val orderPSO = intArrayOf(1, 0, 2)
Coverage.statementStart(6783)
        val orderPOS = intArrayOf(1, 2, 0)
Coverage.statementStart(6784)
        val orderOSP = intArrayOf(2, 0, 1)
Coverage.statementStart(6785)
        val orderOPS = intArrayOf(2, 1, 0)
Coverage.statementStart(6786)
        val orders = arrayOf(orderSPO, orderSOP, orderPSO, orderPOS, orderOSP, orderOPS)
Coverage.statementStart(6787)
        if (total == 1) {
Coverage.ifStart(6788)
            dataSPO = data[8]
Coverage.statementStart(6789)
            dataSOP = data[8]
Coverage.statementStart(6790)
            dataPSO = data[8]
Coverage.statementStart(6791)
            dataPOS = data[8]
Coverage.statementStart(6792)
            dataOSP = data[8]
Coverage.statementStart(6793)
            dataOPS = data[8]
Coverage.statementStart(6794)
        } else {
Coverage.ifStart(6795)
            for (j in 0 until 2) {
Coverage.forLoopStart(6796)
                for (i in 0 until 3) {
Coverage.forLoopStart(6797)
                    val order = orders[i * 2 + j]
Coverage.statementStart(6798)
                    val firstIdx = 8
Coverage.statementStart(6799)
                    val dataIdxA = i
Coverage.statementStart(6800)
                    val dataIdxB = i + 3
Coverage.statementStart(6801)
                    sortUsingBuffers(firstIdx, dataIdxA, dataIdxB, data, total, order)
Coverage.statementStart(6802)
                }
Coverage.statementStart(6803)
                if (j == 0) {
Coverage.ifStart(6804)
                    dataSPO = data[0]
Coverage.statementStart(6805)
                    dataPSO = data[1]
Coverage.statementStart(6806)
                    dataOSP = data[2]
Coverage.statementStart(6807)
                    data[0] = data[6]
Coverage.statementStart(6808)
                    data[1] = data[7]
Coverage.statementStart(6809)
                    data[2] = data[8]
Coverage.statementStart(6810)
                    data[6] = dataSPO
Coverage.statementStart(6811)
                    data[7] = dataPSO
Coverage.statementStart(6812)
                    data[8] = dataOSP
Coverage.statementStart(6813)
                } else {
Coverage.ifStart(6814)
                    dataSOP = data[0]
Coverage.statementStart(6815)
                    dataPOS = data[1]
Coverage.statementStart(6816)
                    dataOPS = data[2]
Coverage.statementStart(6817)
                }
Coverage.statementStart(6818)
            }
Coverage.statementStart(6819)
        }
Coverage.statementStart(6820)
        BenchmarkUtils.elapsedSeconds(EBenchmark.IMPORT_SORT)
Coverage.statementStart(6821)
    }
}
