package lupos.s05tripleStore

import lupos.s00misc.Coverage
import lupos.s00misc.EIndexPattern

class TripleStoreLocalBPlusTreePartition(name: String) : TripleStoreLocalBase(name) {
    init {
        dataDistinct = arrayOf(/*return*/
/*return*/                TripleStoreDistinctContainer("SPO", TripleStoreIndex_IDTriple(), { it -> it.dataSPO }, EIndexPattern.SPO),
/*return*/                TripleStoreDistinctContainer("SOP", TripleStoreIndex_IDTriple(), { it -> it.dataSOP }, EIndexPattern.SOP),
/*return*/                TripleStoreDistinctContainer("POS", TripleStoreIndex_IDTriple(), { it -> it.dataPOS }, EIndexPattern.POS),
/*return*/                TripleStoreDistinctContainer("PSO", TripleStoreIndex_IDTriple(), { it -> it.dataPSO }, EIndexPattern.PSO),
/*return*/                TripleStoreDistinctContainer("OSP", TripleStoreIndex_IDTriple(), { it -> it.dataOSP }, EIndexPattern.OSP),
/*return*/                TripleStoreDistinctContainer("OPS", TripleStoreIndex_IDTriple(), { it -> it.dataOPS }, EIndexPattern.OPS),
/*return*/                TripleStoreDistinctContainer("SPkO", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataSPO }, EIndexPattern.SPO),//TODO
/*return*/                TripleStoreDistinctContainer("SOkP", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataSOP }, EIndexPattern.SOP),//TODO
/*return*/                TripleStoreDistinctContainer("POkS", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataPOS }, EIndexPattern.POS),//TODO
/*return*/                TripleStoreDistinctContainer("PSkO", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataPSO }, EIndexPattern.PSO),//TODO
/*return*/                TripleStoreDistinctContainer("OSkP", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataOSP }, EIndexPattern.OSP),//TODO
/*return*/                TripleStoreDistinctContainer("OPkS", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},1), { it -> it.dataOPS }, EIndexPattern.OPS),//TODO
/*return*/                TripleStoreDistinctContainer("SPOk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataSPO }, EIndexPattern.SPO),//TODO
/*return*/                TripleStoreDistinctContainer("SOPk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataSOP }, EIndexPattern.SOP),//TODO
/*return*/                TripleStoreDistinctContainer("POSk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataPOS }, EIndexPattern.POS),//TODO
/*return*/                TripleStoreDistinctContainer("PSOk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataPSO }, EIndexPattern.PSO),//TODO
/*return*/                TripleStoreDistinctContainer("OSPk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataOSP }, EIndexPattern.OSP),//TODO
/*return*/                TripleStoreDistinctContainer("OPSk", TripleStoreIndex_Partition({TripleStoreIndex_IDTriple()},2), { it -> it.dataOPS }, EIndexPattern.OPS),//TODO
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
        var s = d.size
        for (it in 0 until EIndexPattern.values().size) {
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    d.add(6)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    d.add(7)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    d.add(8)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    d.add(9)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    d.add(10)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    d.add(11)
                }
            }
        }
        for (it in 0 until EIndexPattern.values().size) {
            when (EIndexPattern.values()[it]) {
                EIndexPattern.SPO, EIndexPattern.SP_O, EIndexPattern.S_PO -> {
                    d.add(12)
                }
                EIndexPattern.SOP, EIndexPattern.SO_P, EIndexPattern.S_OP -> {
                    d.add(13)
                }
                EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S -> {
                    d.add(14)
                }
                EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O -> {
                    d.add(15)
                }
                EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P -> {
                    d.add(16)
                }
                EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S -> {
                    d.add(17)
                }
            }
        }
        featureDataMap[TripleStoreFeature.PARTITION.ordinal] = Pair(s, d.size)
        s = d.size
        data = IntArray(d.size)
        for (i in 0 until d.size) {
            data[i] = d[i]
        }
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    override fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
        return when (feature) {
            TripleStoreFeature.DEFAULT -> {
                true
            }
            TripleStoreFeature.PARTITION -> {
                val p = params as TripleStoreFeatureParamsPartition
var c=p.getColumn()
                c >= 1 && c <= 2
            }
        }
    }
}
