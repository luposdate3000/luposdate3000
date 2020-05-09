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
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.Query


class TripleStoreLocalMapMapList(name: String) : TripleStoreLocalBase(name) {
    init {
        distinctIndices = arrayOf<EIndexPattern>(EIndexPattern.SPO, EIndexPattern.SOP, EIndexPattern.POS, EIndexPattern.PSO, EIndexPattern.OSP, EIndexPattern.OPS)
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
