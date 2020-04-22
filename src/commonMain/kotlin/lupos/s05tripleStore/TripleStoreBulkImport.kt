package lupos.s05tripleStore

import lupos.s00misc.*
import lupos.s03resultRepresentation.*

class TripleStoreBulkImport {
    @JvmField
    val dictionaryS = PatriciaTrie()
    @JvmField
    val dictionaryP = PatriciaTrie()
    @JvmField
    val dictionaryO = PatriciaTrie()
    @JvmField
    val dataSPO = mutableListOf<MyMapInt<MySetInt>>()
    @JvmField
    val dataSOP = mutableListOf<MyMapInt<MySetInt>>()
    @JvmField
    val dataPOS = mutableListOf<MyMapInt<MySetInt>>()
    @JvmField
    val dataOSP = mutableListOf<MyMapInt<MySetInt>>()
    @JvmField
    val dataPSO = mutableListOf<MyMapInt<MySetInt>>()
    @JvmField
    val dataOPS = mutableListOf<MyMapInt<MySetInt>>()

    fun insert(s: String, p: String, o: String) {
        val si = dictionaryS.insert(s)
        val pi = dictionaryP.insert(p)
        val oi = dictionaryO.insert(o)
        require(dataSOP.size == dataSPO.size)
        require(si <= dataSOP.size)
        require(pi <= dataPOS.size)
        require(oi <= dataOSP.size)
        if (si == dataSOP.size) {
            dataSOP.add(MyMapInt<MySetInt>())
            dataSPO.add(MyMapInt<MySetInt>())
        }
        if (pi == dataPOS.size) {
            dataPOS.add(MyMapInt<MySetInt>())
            dataPSO.add(MyMapInt<MySetInt>())
        }
        if (oi == dataOSP.size) {
            dataOSP.add(MyMapInt<MySetInt>())
            dataOPS.add(MyMapInt<MySetInt>())
        }
        var tmp: MySetInt?
        tmp = dataSOP[si][oi]
        if (tmp == null) {
            dataSOP[si][oi] = MySetInt(pi)
        } else {
            tmp.add(pi)
        }
        tmp = dataSPO[si][pi]
        if (tmp == null) {
            dataSPO[si][pi] = MySetInt(oi)
        } else {
            tmp.add(oi)
        }
        tmp = dataPOS[pi][oi]
        if (tmp == null) {
            dataPOS[pi][oi] = MySetInt(si)
        } else {
            tmp.add(si)
        }
        tmp = dataOSP[oi][si]
        if (tmp == null) {
            dataOSP[oi][si] = MySetInt(pi)
        } else {
            tmp.add(pi)
        }
        tmp = dataOPS[oi][pi]
        if (tmp == null) {
            dataOPS[oi][pi] = MySetInt(si)
        } else {
            tmp.add(si)
        }
        tmp = dataPSO[pi][si]
        if (tmp == null) {
            dataPSO[pi][si] = MySetInt(oi)
        } else {
            tmp.add(oi)
        }
    }
}
