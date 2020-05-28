package lupos.s05tripleStore
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
class TripleStoreLocalMapMapList(name: String) : TripleStoreLocalBase(name) {
    init {
Coverage.funStart(7858)
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
Coverage.statementStart(7859)
        dataDistinct = arrayOf(/*return*/                Pair("SPO", TripleStoreIndex_MapMapList()),
/*return*/                Pair("SOP", TripleStoreIndex_MapMapList()),
/*return*/                Pair("POS", TripleStoreIndex_MapMapList()),
/*return*/                Pair("PSO", TripleStoreIndex_MapMapList()),
/*return*/                Pair("OSP", TripleStoreIndex_MapMapList()),
/*return*/                Pair("OPS", TripleStoreIndex_MapMapList())
        )
        data = Array(EIndexPattern.values().size) {
Coverage.statementStart(7860)
            val res: TripleStoreIndex
Coverage.statementStart(7861)
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
Coverage.whenCaseStart(7863)
                    res = dataDistinct[0].second
Coverage.statementStart(7864)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
Coverage.whenCaseStart(7865)
                    res = dataDistinct[1].second
Coverage.statementStart(7866)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
Coverage.whenCaseStart(7867)
                    res = dataDistinct[2].second
Coverage.statementStart(7868)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
Coverage.whenCaseStart(7869)
                    res = dataDistinct[3].second
Coverage.statementStart(7870)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
Coverage.whenCaseStart(7871)
                    res = dataDistinct[4].second
Coverage.statementStart(7872)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
Coverage.whenCaseStart(7873)
                    res = dataDistinct[5].second
Coverage.statementStart(7874)
                }
            }
Coverage.statementStart(7875)
/*return*/res
        }
Coverage.statementStart(7876)
    }
}
