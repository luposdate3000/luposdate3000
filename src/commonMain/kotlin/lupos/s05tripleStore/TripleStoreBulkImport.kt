package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.*

class TripleStoreBulkImport {
    @JvmField
    val dictionaryS = PatriciaTrie()
    @JvmField
    val dictionaryP = PatriciaTrie()
    @JvmField
    val dictionaryO = PatriciaTrie()
    @JvmField
    val dataSPO = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//s0,sp,spo
    @JvmField
    val dataSOP = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//so,s1
    @JvmField
    val dataPOS = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//p0,po
    @JvmField
    val dataOSP = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//o0
    @JvmField
    val dataPSO = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//p1
    @JvmField
    val dataOPS = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//o1

    fun insert(s: String, p: String, o: String) {
        val si = dictionaryS.insert(s)
        val pi = dictionaryP.insert(p)
        val oi = dictionaryO.insert(o)
        require(dataSOP.size == dataSPO.size)
        require(si <= dataSOP.size)
        require(pi <= dataPOS.size)
        require(oi <= dataOSP.size)
        if (si == dataSOP.size) {
            dataSOP.add(mutableMapOf<Int, MutableSet<Int>>())
            dataSPO.add(mutableMapOf<Int, MutableSet<Int>>())
        }
        if (pi == dataPOS.size) {
            dataPOS.add(mutableMapOf<Int, MutableSet<Int>>())
            dataPSO.add(mutableMapOf<Int, MutableSet<Int>>())
        }
        if (oi == dataOSP.size) {
            dataOSP.add(mutableMapOf<Int, MutableSet<Int>>())
            dataOPS.add(mutableMapOf<Int, MutableSet<Int>>())
        }
        var tmp: MutableSet<Int>?
        tmp = dataSOP[si][oi]
        if (tmp == null) {
            dataSOP[si][oi] = mutableSetOf(pi)
        } else {
            tmp.add(pi)
        }
        tmp = dataSPO[si][pi]
        if (tmp == null) {
            dataSPO[si][pi] = mutableSetOf(oi)
        } else {
            tmp.add(oi)
        }
        tmp = dataPOS[pi][oi]
        if (tmp == null) {
            dataPOS[pi][oi] = mutableSetOf(si)
        } else {
            tmp.add(si)
        }
        tmp = dataOSP[oi][si]
        if (tmp == null) {
            dataOSP[oi][si] = mutableSetOf(pi)
        } else {
            tmp.add(pi)
        }
        tmp = dataOPS[oi][pi]
        if (tmp == null) {
            dataOPS[oi][pi] = mutableSetOf(si)
        } else {
            tmp.add(si)
        }
        tmp = dataPSO[pi][si]
        if (tmp == null) {
            dataPSO[pi][si] = mutableSetOf(oi)
        } else {
            tmp.add(oi)
        }
    }
}
