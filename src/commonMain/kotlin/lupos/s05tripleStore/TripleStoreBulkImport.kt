package lupos.s05tripleStore

import lupos.s03resultRepresentation.*

class TripleStoreBulkImport {
    val dictionaryS = PatriciaTrie()
    val dictionaryP = PatriciaTrie()
    val dictionaryO = PatriciaTrie()
    val dataSPO = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//s,sp,spo
    val dataSOP = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//so
    val dataPOS = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//p,po
    val dataOSP = mutableListOf<MutableMap<Int, MutableSet<Int>>>()//o
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
        }
        if (oi == dataOSP.size) {
            dataOSP.add(mutableMapOf<Int, MutableSet<Int>>())
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
        tmp = dataSPO[si][pi]
        if (tmp == null) {
            dataSPO[si][pi] = mutableSetOf(oi)
        } else {
            tmp.add(oi)
        }
    }
}
