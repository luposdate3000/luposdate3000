package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import lupos.s15tripleStoreDistributed.distributedTripleStore
import kotlin.jvm.JvmField
public class TripleStoreBulkImport(@JvmField public val query: IQuery, @JvmField public val graphName: String) : ITripleStoreBulkImport {
    @JvmField
    public var totalflushed: Int = 0
    @JvmField
    public val data: Array<IntArray> = Array(9) { IntArray(size) }
    @JvmField
    public var idx: Int = 0
    @JvmField
    public var dataSPO: IntArray = data[0]
    @JvmField
    public var dataSOP: IntArray = data[0]
    @JvmField
    public var dataPSO: IntArray = data[0]
    @JvmField
    public var dataPOS: IntArray = data[0]
    @JvmField
    public var dataOSP: IntArray = data[0]
    @JvmField
    public var dataOPS: IntArray = data[0]
    override fun getData(idx: EIndexPattern): IntArray {
        return when (idx) {
            EIndexPattern.SPO -> dataSPO
            EIndexPattern.SOP -> dataSOP
            EIndexPattern.PSO -> dataPSO
            EIndexPattern.POS -> dataPOS
            EIndexPattern.OPS -> dataOPS
            EIndexPattern.OSP -> dataOSP
            else -> SanityCheck.checkUnreachable()
        }
    }
    override fun getIdx(): Int = idx
    override /*suspend*/ fun insert(si: Int, pi: Int, oi: Int) {
        data[8][idx++] = si
        data[8][idx++] = pi
        data[8][idx++] = oi
        if (full()) {
            sort()
            flush()
            reset()
        }
    }
    override /*suspend*/ fun finishImport() {
        sort()
        flush()
    }
    public /*suspend*/ fun flush() {
        totalflushed += idx / 3
        println("flushed triples $totalflushed")
        distributedTripleStore.getLocalStore().getNamedGraph(query, graphName).import(this)
    }
    private fun reset() {
        idx = 0
    }
    private fun full() = idx >= size
    public companion object {
        private const val sizeshift = 20
        public const val size: Int = 3 * (1 shl sizeshift)
    }
    private fun sort() {
        // the target data is sorted, but may contain duplicates, _if the input contains those
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
                    val dataIdxB = i + 3
                    TripleStoreBulkImportExt.sortUsingBuffers(8, i, dataIdxB, data, total, order)
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
