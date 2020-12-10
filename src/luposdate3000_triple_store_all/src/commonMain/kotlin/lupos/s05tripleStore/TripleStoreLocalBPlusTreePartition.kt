package lupos.s05tripleStore
import lupos.s00misc.EIndexPattern
import lupos.s00misc.Partition
class TripleStoreLocalBPlusTreePartition(name: String) : TripleStoreLocalBase(name) {
    init {
        if (Partition.estimatedPartitionsValid) {
            val localindicees = mapOf(
                "SPO" to mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O),
                "SOP" to mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P),
                "PSO" to mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O),
                "POS" to mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S),
                "OSP" to mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P),
                "OPS" to mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S)
            )
            val tmpEnabledPartitions = mutableListOf<EnabledPartitionContainer>()
            for ((k, v) in localindicees) {
                tmpEnabledPartitions.add(EnabledPartitionContainer(v, -1, 1))
                val tmp2 = Partition.estimatedPartitions1[k]
                if (tmp2 != null) {
                    for (tmp in tmp2) {
                        tmpEnabledPartitions.add(EnabledPartitionContainer(v, 1, tmp))
                    }
                }
                val tmp3 = Partition.estimatedPartitions2[k]
                if (tmp3 != null) {
                    for (tmp in tmp3) {
                        tmpEnabledPartitions.add(EnabledPartitionContainer(v, 2, tmp))
                    }
                }
            }
            enabledPartitions = tmpEnabledPartitions.toTypedArray()
        } else {
            enabledPartitions = arrayOf( //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), -1, 1), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), 1, 2), // TODO use reasonable partition counts ... this is just to verify during tests
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), 1, 4), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), 1, 8), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), 1, 16), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), 1, 32), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), 1, 64), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), 2, 2), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), 2, 4), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), 2, 8), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), 2, 16), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), 2, 32), //
                EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), 2, 64), //
            )
        }
        val dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        println("the activated Partitions are ::")
        for (p in enabledPartitions) {
            println("partition :: ${p.index.first()} ${p.column} ${p.partitionCount}")
            val name2 = StringBuilder(p.index.toString())
            if (p.column >= 0) {
                name2.insert(p.column, p.partitionCount)
                when {
                    p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.SPO) }, EIndexPattern.SPO))
                    p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.SOP) }, EIndexPattern.SOP))
                    p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.POS) }, EIndexPattern.POS))
                    p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.PSO) }, EIndexPattern.PSO))
                    p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.OSP) }, EIndexPattern.OSP))
                    p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ TripleStoreIndexIDTriple() }, p.column, p.partitionCount), { it.getData(EIndexPattern.OPS) }, EIndexPattern.OPS))
                    else -> throw Exception("")
                }
            } else {
                when {
                    p.index.contains(EIndexPattern.SPO) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.SPO) }, EIndexPattern.SPO))
                    p.index.contains(EIndexPattern.SOP) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.SOP) }, EIndexPattern.SOP))
                    p.index.contains(EIndexPattern.POS) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.POS) }, EIndexPattern.POS))
                    p.index.contains(EIndexPattern.PSO) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.PSO) }, EIndexPattern.PSO))
                    p.index.contains(EIndexPattern.OSP) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.OSP) }, EIndexPattern.OSP))
                    p.index.contains(EIndexPattern.OPS) -> dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(), { it.getData(EIndexPattern.OPS) }, EIndexPattern.OPS))
                    else -> throw Exception("")
                }
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
                    if (params == null) {
                        true
                    } else {
                        val p = params as TripleStoreFeatureParamsPartition
                        val c = p.getColumn()
                        c in 1..2
                    }
                }
            }
        }
    }
}
