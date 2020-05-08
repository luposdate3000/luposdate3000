package lupos.s05tripleStore

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ELoggerType
import lupos.s00misc.EModifyType
import lupos.s00misc.GlobalLogger
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeMutableList
import lupos.s00misc.ThreadSafeMutableSet
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query

class TripleStoreLocalSingleList(name: String) : TripleStoreLocalBase(name) {
    init {
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SO_P, EIndexPattern.PO_S, EIndexPattern.P_SO, EIndexPattern.O_SP, EIndexPattern.O_PS)
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
order = Array(EIndexPattern.values().size) {
            val res: IntArray
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    res = intArrayOf(0, 1, 2)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    res = intArrayOf(0, 2, 1)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    res = intArrayOf(1, 2, 0)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    res = intArrayOf(1, 0, 2)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    res = intArrayOf(2, 0, 1)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    res = intArrayOf(2, 1, 0)
                }
            }
/*return*/res
        }
    }
}
