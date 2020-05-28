package lupos.s05tripleStore
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
class TripleStoreLocalBPlusTree(name: String) : TripleStoreLocalBase(name) {
    init {
Coverage.funStart(7839)
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
Coverage.statementStart(7840)
        dataDistinct = arrayOf(/*return*/
/*return*/                Pair("SPO", TripleStoreIndex_IDTriple()),
/*return*/                Pair("SOP", TripleStoreIndex_IDTriple()),
/*return*/                Pair("POS", TripleStoreIndex_IDTriple()),
/*return*/                Pair("PSO", TripleStoreIndex_IDTriple()),
/*return*/                Pair("OSP", TripleStoreIndex_IDTriple()),
/*return*/                Pair("OPS", TripleStoreIndex_IDTriple())
        )
        data = Array(EIndexPattern.values().size) {
Coverage.statementStart(7841)
            val res: TripleStoreIndex
Coverage.statementStart(7842)
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
Coverage.whenCaseStart(7844)
                    res = dataDistinct[0].second
Coverage.statementStart(7845)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
Coverage.whenCaseStart(7846)
                    res = dataDistinct[1].second
Coverage.statementStart(7847)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
Coverage.whenCaseStart(7848)
                    res = dataDistinct[2].second
Coverage.statementStart(7849)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
Coverage.whenCaseStart(7850)
                    res = dataDistinct[3].second
Coverage.statementStart(7851)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
Coverage.whenCaseStart(7852)
                    res = dataDistinct[4].second
Coverage.statementStart(7853)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
Coverage.whenCaseStart(7854)
                    res = dataDistinct[5].second
Coverage.statementStart(7855)
                }
            }
Coverage.statementStart(7856)
/*return*/res
        }
Coverage.statementStart(7857)
    }
}
