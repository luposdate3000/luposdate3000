package lupos.s05tripleStore
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
class TripleStoreLocalSingleList(name: String) : TripleStoreLocalBase(name) {
    init {
Coverage.funStart(7877)
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
Coverage.statementStart(7878)
        dataDistinct = arrayOf(/*return*/
/*return*/                Pair("SPO", TripleStoreIndex_SingleList()),
/*return*/                Pair("SOP", TripleStoreIndex_SingleList()),
/*return*/                Pair("POS", TripleStoreIndex_SingleList()),
/*return*/                Pair("PSO", TripleStoreIndex_SingleList()),
/*return*/                Pair("OSP", TripleStoreIndex_SingleList()),
/*return*/                Pair("OPS", TripleStoreIndex_SingleList())
        )
        data = Array(EIndexPattern.values().size) {
Coverage.statementStart(7879)
            val res: TripleStoreIndex
Coverage.statementStart(7880)
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
Coverage.whenCaseStart(7882)
                    res = dataDistinct[0].second
Coverage.statementStart(7883)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
Coverage.whenCaseStart(7884)
                    res = dataDistinct[1].second
Coverage.statementStart(7885)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
Coverage.whenCaseStart(7886)
                    res = dataDistinct[2].second
Coverage.statementStart(7887)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
Coverage.whenCaseStart(7888)
                    res = dataDistinct[3].second
Coverage.statementStart(7889)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
Coverage.whenCaseStart(7890)
                    res = dataDistinct[4].second
Coverage.statementStart(7891)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
Coverage.whenCaseStart(7892)
                    res = dataDistinct[5].second
Coverage.statementStart(7893)
                }
            }
Coverage.statementStart(7894)
/*return*/res
        }
Coverage.statementStart(7895)
    }
}
