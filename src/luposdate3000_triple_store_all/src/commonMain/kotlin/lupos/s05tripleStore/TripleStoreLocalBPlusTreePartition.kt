package lupos.s05tripleStore
import lupos.s00misc.ByteArrayHelper
import lupos.s00misc.EIndexPattern
import lupos.s00misc.ETripleIndexType
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s00misc.USE_PARTITIONS2
import lupos.s01io.BufferManagerExt
import lupos.s01io.BufferManager
import kotlin.jvm.JvmField
public class TripleStoreLocalBPlusTreePartition(name: String, store_root_page_id_: Int, store_root_page_init: Boolean) : TripleStoreLocalBase(name, store_root_page_id_) {
    @JvmField
public     val bufferManager :BufferManager= BufferManagerExt.getBuffermanager("stores")
    init {
        val rootPage = bufferManager.getPage(store_root_page_id)
        val dataDistinctList = mutableListOf<TripleStoreDistinctContainer>()
        println("the activated Partitions are ::")
        if (store_root_page_init) {
            var cnt = ByteArrayHelper.readInt4(rootPage, 0)
            var rootPageOffset = 4
            val tmpEnabledPartitions = mutableListOf<EnabledPartitionContainer>()
            val localindicees = mapOf(
                EIndexPattern.SPO to mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O),
                EIndexPattern.SOP to mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P),
                EIndexPattern.PSO to mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O),
                EIndexPattern.POS to mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S),
                EIndexPattern.OSP to mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P),
                EIndexPattern.OPS to mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S)
            )
            for (i in 0 until cnt) {
                val idx = EIndexPattern.values()[ByteArrayHelper.readInt4(rootPage, rootPageOffset)]
                val pageid2 = ByteArrayHelper.readInt4(rootPage, rootPageOffset + 4)
                val column = ByteArrayHelper.readInt4(rootPage, rootPageOffset + 8)
                val partitionCount = ByteArrayHelper.readInt4(rootPage, rootPageOffset + 12)
                val name2 = StringBuilder(idx.toString())
                println("partition :: $idx $column $partitionCount")
                val childPage = bufferManager.getPage(pageid2)
                val type = ETripleIndexType.values()[ByteArrayHelper.readInt4(childPage, 0)]
                val store = when (type) {
                    ETripleIndexType.ID_TRIPLE -> TripleStoreIndexIDTriple(pageid2, store_root_page_init)
                    ETripleIndexType.PARTITION -> TripleStoreIndexPartition({ i, k -> SanityCheck.checkUnreachable() }, column, partitionCount, pageid2, store_root_page_init)
                }
                bufferManager.releasePage(pageid2)
                if (column >= 0) {
                    name2.insert(column, partitionCount)
                    SanityCheck.check { store is TripleStoreIndexPartition }
                    dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), store, { it.getData(idx) }, idx))
                } else {
                    SanityCheck.check { store is TripleStoreIndexIDTriple }
                    dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), store, { it.getData(idx) }, idx))
                }
                rootPageOffset += 16
                tmpEnabledPartitions.add(EnabledPartitionContainer(localindicees[idx]!!, column, partitionCount))
            }
            enabledPartitions = tmpEnabledPartitions.toTypedArray()
        } else {
            if (!USE_PARTITIONS2) {
                enabledPartitions = arrayOf( //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.SPO, EIndexPattern.S_PO, EIndexPattern.SP_O), -1, 1), //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.SOP, EIndexPattern.S_OP, EIndexPattern.SO_P), -1, 1), //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.POS, EIndexPattern.P_OS, EIndexPattern.PO_S), -1, 1), //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.PSO, EIndexPattern.P_SO, EIndexPattern.PS_O), -1, 1), //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.OSP, EIndexPattern.O_SP, EIndexPattern.OS_P), -1, 1), //
                    EnabledPartitionContainer(mutableSetOf(EIndexPattern.OPS, EIndexPattern.O_PS, EIndexPattern.OP_S), -1, 1), //
                )
            } else if (Partition.estimatedPartitionsValid) {
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
            var rootPageOffset = 4
            ByteArrayHelper.writeInt4(rootPage, 0, enabledPartitions.size)
            for (p in enabledPartitions) {
                println("partition :: ${p.index.first()} ${p.column} ${p.partitionCount}")
                val idx = when {
                    p.index.contains(EIndexPattern.SPO) -> EIndexPattern.SPO
                    p.index.contains(EIndexPattern.SOP) -> EIndexPattern.SOP
                    p.index.contains(EIndexPattern.POS) -> EIndexPattern.POS
                    p.index.contains(EIndexPattern.PSO) -> EIndexPattern.PSO
                    p.index.contains(EIndexPattern.OSP) -> EIndexPattern.OSP
                    p.index.contains(EIndexPattern.OPS) -> EIndexPattern.OPS
                    else -> SanityCheck.checkUnreachable()
                }
                val name2 = StringBuilder(idx.toString())
                var pageid2 = -1
                bufferManager.createPage { p, pageid3 ->
                    pageid2 = pageid3
                }
                bufferManager.releasePage(pageid2)
                ByteArrayHelper.writeInt4(rootPage, rootPageOffset, idx.ordinal)
                ByteArrayHelper.writeInt4(rootPage, rootPageOffset + 4, pageid2)
                ByteArrayHelper.writeInt4(rootPage, rootPageOffset + 8, p.column)
                ByteArrayHelper.writeInt4(rootPage, rootPageOffset + 12, p.partitionCount)
                rootPageOffset += 16
                SanityCheck.check { rootPageOffset <= BufferManagerExt.getPageSize() }
                if (p.column >= 0) {
                    name2.insert(p.column, p.partitionCount)
                    dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexPartition({ i, k -> TripleStoreIndexIDTriple(i, k) }, p.column, p.partitionCount, pageid2, store_root_page_init), { it.getData(idx) }, idx))
                } else {
                    dataDistinctList.add(TripleStoreDistinctContainer(name2.toString(), TripleStoreIndexIDTriple(pageid2, store_root_page_init), { it.getData(idx) }, idx))
                }
            }
            bufferManager.flushPage(store_root_page_id)
        }
        dataDistinct = dataDistinctList.toTypedArray()
        pendingModificationsInsert = Array(dataDistinct.size) { mutableMapOf() }
        pendingModificationsRemove = Array(dataDistinct.size) { mutableMapOf() }
        bufferManager.releasePage(store_root_page_id)
    }
    override fun dropStore() {
        for (c in dataDistinct) {
            c.second.dropIndex()
        }
        bufferManager.getPage(store_root_page_id)
        bufferManager.deletePage(store_root_page_id)
    }
public    companion object {
        public fun providesFeature(feature: TripleStoreFeature, params: TripleStoreFeatureParams?): Boolean {
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
