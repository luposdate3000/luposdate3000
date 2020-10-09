package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition

class TripleStoreLocalBPlusTreePartition(name: String) : TripleStoreLocalBase(name) {
    init {
        enabledPartitions = arrayOf(//
                EnabledPartitionContainer(EIndexPattern.SPO, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.SOP, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.POS, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.PSO, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.OSP, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.OPS, -1, 1),//
                EnabledPartitionContainer(EIndexPattern.SPO, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.SOP, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.POS, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.PSO, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.OSP, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.OPS, 1, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.SPO, 2, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.SOP, 2, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.POS, 2, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.PSO, 2, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.OSP, 2, Partition.default_k),//
                EnabledPartitionContainer(EIndexPattern.OPS, 2, Partition.default_k),//
        )
        var dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        for (p in enabledPartitions) {
            val name = StringBuilder(p.index.toString())
            if (p.column >= 0) {
                name.insert(p.column, p.partitionCount)
                when (p.index) {
                    EIndexPattern.SPO -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataSPO }, p.index))
                    EIndexPattern.SOP -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataSOP }, p.index))
                    EIndexPattern.POS -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataPOS }, p.index))
                    EIndexPattern.PSO -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataPSO }, p.index))
                    EIndexPattern.OSP -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataOSP }, p.index))
                    EIndexPattern.OPS -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, 1, Partition.default_k), { it -> it.dataOPS }, p.index))
                    else -> throw Exception("")
                }
            } else {
                when (p.index) {
                    EIndexPattern.SPO -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataSPO }, p.index))
                    EIndexPattern.SOP -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataSOP }, p.index))
                    EIndexPattern.POS -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataPOS }, p.index))
                    EIndexPattern.PSO -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataPSO }, p.index))
                    EIndexPattern.OSP -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataOSP }, p.index))
                    EIndexPattern.OPS -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.dataOPS }, p.index))
                    else -> throw Exception("")
                }
            }

        }

        dataDistinct= dataDistinctList . toTypedArray ()
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    companion object {
        fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
            return when (feature) {
                TripleStoreFeature.DEFAULT -> {
                    /*return*/ true
                }
                TripleStoreFeature.PARTITION -> {
                    if (params == null) {
                        /*return*/ true
                    } else {
                        val p = params as TripleStoreFeatureParamsPartition
                        var c = p.getColumn()
                        /*return*/ c >= 1 && c <= 2
                    }
                }
            }
        }
    }
}
