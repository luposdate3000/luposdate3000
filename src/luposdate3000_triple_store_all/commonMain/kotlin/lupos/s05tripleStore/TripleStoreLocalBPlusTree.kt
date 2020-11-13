package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern

class TripleStoreLocalBPlusTree(name: String) : TripleStoreLocalBase(name) {
    init {
        val dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        for (p in enabledPartitions) {
            val name = StringBuilder(p.index.toString())
            when {
                p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.SPO) }, EIndexPattern.SPO))
                p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.SOP) }, EIndexPattern.SOP))
                p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.POS) }, EIndexPattern.POS))
                p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.PSO) }, EIndexPattern.PSO))
                p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.OSP) }, EIndexPattern.OSP))
                p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it.getData(EIndexPattern.OPS) }, EIndexPattern.OPS))
                else -> throw Exception("")
            }
        }
        dataDistinct = dataDistinctList.toTypedArray()
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf() }
    }

    companion object {
        fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
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
}
