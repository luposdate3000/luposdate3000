package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.EIndexPattern
import lupos.s04logicalOperators.Query
import lupos.s15tripleStoreDistributed.distributedTripleStore

class TripleStoreBulkImport(@JvmField val query: Query, @JvmField val graphName: String) : ITripleStoreBulkImport {
    @JvmField
    val dictionaryBNode = mutableMapOf<String, Int>()

    @JvmField
    var totalflushed = 0

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

override fun getData(idx:EIndexPattern):IntArray{
return when(idx){
EIndexPattern.SPO->dataSPO
EIndexPattern.SOP->dataSOP
EIndexPattern.PSO->dataPSO
EIndexPattern.POS->dataPOS
EIndexPattern.OPS->dataOPS
EIndexPattern.OSP->dataOSP
else->throw Exception("unreachable")
}
}

    suspend fun insert(si: Int, pi: Int, oi: Int) {
        data[8][idx++] = si
        data[8][idx++] = pi
        data[8][idx++] = oi
        if (full()) {
            sort()
            flush()
            reset()
        }
    }

    suspend fun finishImport() {
        sort()
        flush()
    }

    suspend fun flush() {
        totalflushed += idx / 3
        println("flushed triples $totalflushed")
        distributedTripleStore.getLocalStore().getNamedGraph(query, graphName).import(this)
    }

    fun reset() {
        idx = 0
    }

    fun full() = idx >= size

    companion object {
        const val sizeshift = 20
        const val size = 3 * (1 shl sizeshift)
    }

    fun sort() {
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
                    val dataIdxA = i
                    val dataIdxB = i + 3
                    TripleStoreBulkImportExt.sortUsingBuffers(8, dataIdxA, dataIdxB, data, total, order)
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
}
