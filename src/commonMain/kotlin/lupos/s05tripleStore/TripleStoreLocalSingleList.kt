package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern

class TripleStoreLocalSingleList(name: String) : TripleStoreLocalBase(name) {
    init {
        dataDistinct = arrayOf(/*return*/
/*return*/                TripleStoreDistinctContainer("SPO", TripleStoreIndex_SingleList(), { it -> it.dataSPO }, EIndexPattern.SPO),
/*return*/                TripleStoreDistinctContainer("SOP", TripleStoreIndex_SingleList(), { it -> it.dataSOP }, EIndexPattern.SOP),
/*return*/                TripleStoreDistinctContainer("POS", TripleStoreIndex_SingleList(), { it -> it.dataPOS }, EIndexPattern.POS),
/*return*/                TripleStoreDistinctContainer("PSO", TripleStoreIndex_SingleList(), { it -> it.dataPSO }, EIndexPattern.PSO),
/*return*/                TripleStoreDistinctContainer("OSP", TripleStoreIndex_SingleList(), { it -> it.dataOSP }, EIndexPattern.OSP),
/*return*/                TripleStoreDistinctContainer("OPS", TripleStoreIndex_SingleList(), { it -> it.dataOPS }, EIndexPattern.OPS)
        )
        data = IntArray(EIndexPattern.values().size) {
            val res: Int
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    res = 0
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    res = 1
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    res = 2
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    res = 3
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    res = 4
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    res = 5
                }
            }
/*return*/res
        }
        featureDataMap[TripleStoreFeature.DEFAULT.ordinal] = Pair(0, data.size)
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    override fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
        return when (feature) {
            TripleStoreFeature.DEFAULT -> {
                true
            }
            TripleStoreFeature.PARTITION -> {
                false
            }
        }
    }
}
