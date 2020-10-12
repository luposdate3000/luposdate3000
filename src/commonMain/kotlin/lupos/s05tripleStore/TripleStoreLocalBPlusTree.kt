package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition

class TripleStoreLocalBPlusTree(name: String) : TripleStoreLocalBase(name) {
    init {
        var dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        for (p in enabledPartitions) {
            val name = StringBuilder(p.index.toString())
            when {
                p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataSPO }, EIndexPattern.SPO))
                p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataSOP }, EIndexPattern.SOP))
                p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataPOS }, EIndexPattern.POS))
                p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataPSO }, EIndexPattern.PSO))
                p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataOSP }, EIndexPattern.OSP))
                p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataOPS }, EIndexPattern.OPS))
                else -> throw Exception("")
            }
        }
        dataDistinct = dataDistinctList.toTypedArray()
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    companion object {
        fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
            return when (feature) {
                TripleStoreFeature.DEFAULT -> {
/*return*/                true
                }
                TripleStoreFeature.PARTITION -> {
                    /*return*/      false
                }
            }
        }
    }
}
