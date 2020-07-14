package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern

class TripleStoreLocalSingleList(name: String) : TripleStoreLocalBase(name) {
    init {
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
        dataDistinct = arrayOf(/*return*/
/*return*/                Pair("SPO", TripleStoreIndex_SingleList()),
/*return*/                Pair("SOP", TripleStoreIndex_SingleList()),
/*return*/                Pair("POS", TripleStoreIndex_SingleList()),
/*return*/                Pair("PSO", TripleStoreIndex_SingleList()),
/*return*/                Pair("OSP", TripleStoreIndex_SingleList()),
/*return*/                Pair("OPS", TripleStoreIndex_SingleList())
        )
        data = Array(EIndexPattern.values().size) {
            val res: TripleStoreIndex
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    res = dataDistinct[0].second
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    res = dataDistinct[1].second
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    res = dataDistinct[2].second
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    res = dataDistinct[3].second
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    res = dataDistinct[4].second
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    res = dataDistinct[5].second
                }
            }
/*return*/res
        }
    }
override fun providesFeature(feature: TripleStoreFeature,params:TripleStoreFeatureParams?): Boolean {
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
