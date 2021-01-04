package lupos.s04logicalOperators
import lupos.s00misc.MyLock
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s00misc.SanityCheck
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s09physicalOperators.partition.POPChangePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPMergePartition
import lupos.s09physicalOperators.partition.POPMergePartitionCount
import lupos.s09physicalOperators.partition.POPMergePartitionOrderedByIntId
import lupos.s09physicalOperators.partition.POPSplitPartition
import lupos.s09physicalOperators.partition.POPSplitPartitionFromStore
import kotlin.jvm.JvmField
class PartitionHelper {
    var iterators: MutableMap<Partition, Array<IteratorBundle>>? = null
    internal var jobs: MutableMap<Partition, ParallelJob>? = null
    internal val lock = MyLock()
}
class Query(@JvmField val dictionary: ResultSetDictionary = ResultSetDictionary(), @JvmField val transactionID: Long = global_transactionID++) : IQuery {
    @JvmField
    var _workingDirectory: String = ""
    @JvmField
    var filtersMovedUpFromOptionals: Boolean = false
    @JvmField
    var commited: Boolean = false
    @JvmField
    var dontCheckVariableExistence: Boolean = false
    @JvmField
    var generatedNameCounter: Int = 0
    @JvmField
    var generatedNameByBase: MutableMap<String, String> = mutableMapOf()
    @JvmField
    internal val partitions = mutableMapOf<Long, PartitionHelper>()
    @JvmField
    internal val partitionsLock = MyLock()
    @JvmField
    val partitionOperators: MutableMap<Int, MutableSet<Long>> = mutableMapOf()
    @JvmField
    val partitionOperatorCount: MutableMap<Int, Int> = mutableMapOf()
    fun getNextPartitionOperatorID(): Int {
        var res = 0
        while (partitionOperators[res] != null) {
            res++
        }
        return res
    }
    fun addPartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp == null) {
            partitionOperators[id] = mutableSetOf(uuid)
        } else {
            SanityCheck.check { !tmp.contains(uuid) }
            tmp.add(uuid)
        }
    }
    fun removePartitionOperator(uuid: Long, id: Int) {
        val tmp = partitionOperators[id]
        if (tmp != null) {
            SanityCheck.check { tmp.contains(uuid) }
            tmp.remove(uuid)
            if (tmp.size == 0) {
                partitionOperators.remove(id)
            }
        }
    }
    private fun changeID(root: IOPBase, list: Set<Long>, idFrom: Int, idTo: Int) {
        if (list.contains(root.getUUID())) {
            when (root) {
                is POPMergePartitionCount -> root.partitionID = idTo
                is POPMergePartition -> root.partitionID = idTo
                is POPMergePartitionOrderedByIntId -> root.partitionID = idTo
                is POPSplitPartitionFromStore -> root.partitionID = idTo
                is POPSplitPartition -> root.partitionID = idTo
                is POPChangePartitionOrderedByIntId -> {
                    if (root.partitionIDFrom == idFrom) {
                        root.partitionIDFrom = idTo
                    } else {
                        SanityCheck.check { root.partitionIDTo == idFrom }
                        root.partitionIDTo = idTo
                    }
                }
                else -> {
                    SanityCheck.checkUnreachable()
                }
            }
        }
        for (c in root.getChildren()) {
            changeID(c, list, idFrom, idTo)
        }
    }
    fun mergePartitionOperator(id1: Int, id2: Int, root: IOPBase): Int {
        partitionOperators[id1]!!.addAll(partitionOperators[id2]!!)
        changeID(root, partitionOperators[id2]!!, id2, id1)
        partitionOperators.remove(id2)
        return id1
    }
    override fun setWorkingDirectory(value: String) {
        _workingDirectory = if (value.endsWith("/")) {
            value
        } else {
            "$value/"
        }
    }
    override fun getTransactionID(): Long = transactionID
    override fun getWorkingDirectory(): String = _workingDirectory
    override fun getDictionary(): IResultSetDictionary = dictionary
    override fun checkVariableExistence(): Boolean = !dontCheckVariableExistence
    override fun setCommited() {
        commited = true
    }
    override fun reset() {
        partitions.clear()
    }
    inline fun getUniqueVariableName(): String = "#+${generatedNameCounter++}"
    inline fun isGeneratedVariableName(name: String): Boolean = name.startsWith('#')
    /*suspend*/ fun getPartitionHelper(uuid: Long): PartitionHelper {
        var res: PartitionHelper? = null
        partitionsLock.withLock {
            res = partitions[uuid]
            if (res == null) {
                res = PartitionHelper()
                partitions[uuid] = res!!
            }
        }
        return res!!
    }
    fun getUniqueVariableName(name: String): String {
        val tmp = generatedNameByBase[name]
        return if (tmp != null) {
            tmp
        } else {
            val tmp2 = getUniqueVariableName()
            generatedNameByBase[name] = tmp2
            tmp2
        }
    }
    internal companion object {
        @JvmField
        internal var global_transactionID = 0L
    }
}
