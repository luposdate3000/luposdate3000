package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern

class TripleStoreLocalBPlusTree(name: String) : TripleStoreLocalBase(name) {
    init {
        dataDistinct = arrayOf(/*return*/
/*return*/                TripleStoreDistinctContainer("SPO", TripleStoreIndex_IDTriple(), { it -> it.dataSPO }, EIndexPattern.SPO),
/*return*/                TripleStoreDistinctContainer("SOP", TripleStoreIndex_IDTriple(), { it -> it.dataSOP }, EIndexPattern.SOP),
/*return*/                TripleStoreDistinctContainer("POS", TripleStoreIndex_IDTriple(), { it -> it.dataPOS }, EIndexPattern.POS),
/*return*/                TripleStoreDistinctContainer("PSO", TripleStoreIndex_IDTriple(), { it -> it.dataPSO }, EIndexPattern.PSO),
/*return*/                TripleStoreDistinctContainer("OSP", TripleStoreIndex_IDTriple(), { it -> it.dataOSP }, EIndexPattern.OSP),
/*return*/                TripleStoreDistinctContainer("OPS", TripleStoreIndex_IDTriple(), { it -> it.dataOPS }, EIndexPattern.OPS)
        )
        var d = mutableListOf<Int>()
        for (it in 0 until EIndexPattern.values().size) {
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    d.add(0)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    d.add(1)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    d.add(2)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    d.add(3)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    d.add(4)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    d.add(5)
                }
            }
        }
        featureDataMap[TripleStoreFeature.DEFAULT.ordinal] = Pair(0, d.size)
        data = IntArray(d.size)
        for (i in 0 until d.size) {
            data[i] = d[i]
        }
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    companion object {
        fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
            return when (feature) {
                TripleStoreFeature.DEFAULT -> {
/*return*/                true
                }
                TripleStoreFeature.PARTITION -> {
                    /*return*/      false
                }
            }
        }
    }
}
