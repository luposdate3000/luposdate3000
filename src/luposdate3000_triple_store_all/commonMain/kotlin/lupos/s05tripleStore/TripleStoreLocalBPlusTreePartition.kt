package lupos.s05tripleStore

import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition

class TripleStoreLocalBPlusTreePartition(name: String) : TripleStoreLocalBase(name) {
    init {
        println("TripleStoreLocalBPlusTreePartition init a")
        enabledPartitions = arrayOf(//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), -1, 1),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), 1, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), 2, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), 2, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), 2, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), 2, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), 2, Partition.default_k),//
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), 2, Partition.default_k),//
        )
        println("TripleStoreLocalBPlusTreePartition init b")
        var dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        println("TripleStoreLocalBPlusTreePartition init c")
        for (p in enabledPartitions) {
            val name = StringBuilder(p.index.toString())
            if (p.column >= 0) {
                println("TripleStoreLocalBPlusTreePartition init d")
                name.insert(p.column, p.partitionCount)
                when {
                    p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.SPO) }, EIndexPattern.SPO))
                    p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.SOP) }, EIndexPattern.SOP))
                    p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.POS) }, EIndexPattern.POS))
                    p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.PSO) }, EIndexPattern.PSO))
                    p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.OSP) }, EIndexPattern.OSP))
                    p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_Partition({ TripleStoreIndex_IDTriple() }, p.column, Partition.default_k), { it -> it.getData(EIndexPattern.OPS) }, EIndexPattern.OPS))
                    else -> throw Exception("")
                }
                println("TripleStoreLocalBPlusTreePartition init e")
            } else {
                println("TripleStoreLocalBPlusTreePartition init f")
                when {
                    p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.SPO) }, EIndexPattern.SPO))
                    p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.SOP) }, EIndexPattern.SOP))
                    p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.POS) }, EIndexPattern.POS))
                    p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.PSO) }, EIndexPattern.PSO))
                    p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.OSP) }, EIndexPattern.OSP))
                    p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name.toString(), TripleStoreIndex_IDTriple(), { it -> it.getData(EIndexPattern.OPS) }, EIndexPattern.OPS))
                    else -> throw Exception("")
                }
                println("TripleStoreLocalBPlusTreePartition init g")
            }
        }
        println("TripleStoreLocalBPlusTreePartition init h")
        dataDistinct = dataDistinctList.toTypedArray()
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf<Long, MutableList<Int>>() }
    }

    companion object {
        fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
            println("TripleStoreLocalBPlusTreePartition init i")
            return when (feature) {
                TripleStoreFeature.DEFAULT -> {
                    println("TripleStoreLocalBPlusTreePartition init j")
                    /*return*/ true
                }
                TripleStoreFeature.PARTITION -> {
                    println("TripleStoreLocalBPlusTreePartition init k")
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
