package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.MyLock
import lupos.s00misc.ParallelJob
import lupos.s00misc.Partition
import lupos.s03resultRepresentation.IResultSetDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.IteratorBundle

class PartitionHelper() {
    var iterators: MutableMap<Partition, Array<IteratorBundle>>? = null
    internal var jobs: MutableMap<Partition, ParallelJob>? = null
    internal val lock = MyLock()
}

class Query(@JvmField val dictionary: ResultSetDictionary = ResultSetDictionary(), @JvmField val transactionID: Long = global_transactionID++) : IQuery {
    @JvmField
    var _workingDirectory = ""
    var filtersMovedUpFromOptionals = false

    @JvmField
    var commited = false

    @JvmField
    var dontCheckVariableExistence = false

    @JvmField
    var generatedNameCounter = 0

    @JvmField
    var generatedNameByBase = mutableMapOf<String, String>()

    @JvmField
    internal val partitions = mutableMapOf<Long, PartitionHelper>()

    @JvmField
    internal val partitionsLock = MyLock()
    override fun getTransactionID() = transactionID
    override fun getWorkingDirectory() = _workingDirectory
    override fun setWorkingDirectory(value: String) {
        if (value.endsWith("/")) {
            _workingDirectory = value
        } else {
            _workingDirectory = value + "/"
        }
    }

    override fun getDictionary(): IResultSetDictionary = dictionary
    override fun checkVariableExistence() = !dontCheckVariableExistence
    internal inline fun partitionsLockLock() {
        partitionsLock.lock()
    }

    internal inline fun partitionsLockUnLock() {
        partitionsLock.unlock()
    }

    override fun setCommited() {
        commited = true
    }

    override fun reset() {
        partitions.clear()
    }

    suspend fun getPartitionHelper(uuid: Long): PartitionHelper {
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
        if (tmp != null) {
            return tmp
        } else {
            val tmp2 = getUniqueVariableName()
            generatedNameByBase[name] = tmp2
            return tmp2
        }
    }

    inline fun getUniqueVariableName() = "#+${generatedNameCounter++}"
    inline fun isGeneratedVariableName(name: String) = name.startsWith('#')

    internal companion object {
        @JvmField
        internal var global_transactionID = 0L
    }
}
