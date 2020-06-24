package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern

class TripleStoreLocalMapMapList(name: String) : TripleStoreLocalBase(name) {
    init {
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
        dataDistinct = arrayOf(/*return*/                Pair("SPO", TripleStoreIndex_MapMapList()),
/*return*/                Pair("SOP", TripleStoreIndex_MapMapList()),
/*return*/                Pair("POS", TripleStoreIndex_MapMapList()),
/*return*/                Pair("PSO", TripleStoreIndex_MapMapList()),
/*return*/                Pair("OSP", TripleStoreIndex_MapMapList()),
/*return*/                Pair("OPS", TripleStoreIndex_MapMapList())
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
}
