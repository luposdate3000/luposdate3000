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

class TripleStoreLocal(name: String) : TripleStoreLocalBase(name) {
    init {
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SO_P, EIndexPattern.PO_S, EIndexPattern.P_SO, EIndexPattern.O_SP, EIndexPattern.O_PS)
        dataDistinct = arrayOf(
                Pair("SPO", TripleStoreIndex_MapMapList()),
                Pair("SOP", TripleStoreIndex_MapMapList()),
                Pair("POS", TripleStoreIndex_MapMapList()),
                Pair("PSO", TripleStoreIndex_MapMapList()),
                Pair("OSP", TripleStoreIndex_MapMapList()),
                Pair("OPS", TripleStoreIndex_MapMapList())
        )
        data = Array(EIndexPattern.values().size) {
            val res: TripleStoreIndex
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    res = dataDistinct[0].second
                }
                EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    res = dataDistinct[1].second
                }
                EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    res = dataDistinct[2].second
                }
                EIndexPattern.P_SO -> {
                    res = dataDistinct[3].second
                }
                EIndexPattern.O_SP -> {
                    res = dataDistinct[4].second
                }
                EIndexPattern.O_PS -> {
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
                EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    res = intArrayOf(0, 2, 1)
                }
                EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    res = intArrayOf(1, 2, 0)
                }
                EIndexPattern.P_SO -> {
                    res = intArrayOf(1, 0, 2)
                }
                EIndexPattern.O_SP -> {
                    res = intArrayOf(2, 0, 1)
                }
                EIndexPattern.O_PS -> {
                    res = intArrayOf(2, 1, 0)
                }
            }
/*return*/res
        }
    }
}
