package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import kotlinx.coroutines.Job
import lupos.s00misc.Coverage
import lupos.s00misc.Lock
import lupos.s00misc.Partition
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s15tripleStoreDistributed.DistributedTripleStore

class Query(@JvmField val dictionary: ResultSetDictionary = ResultSetDictionary(), @JvmField val transactionID: Long = global_transactionID.next()) {
    class PartitionHelper() {
        var iterators: MutableMap<Partition, Array<IteratorBundle>>? = null
        var jobs: MutableMap<Partition, Job>? = null
        val lock = Lock()
    }

    suspend fun getPartitionHelper(uuid: Long): PartitionHelper {
        var res: PartitionHelper? = null
        partitionsLock.withWriteLockSuspend {
            res = partitions[uuid]
            if (res == null) {
                res = PartitionHelper()
                partitions[uuid] = res!!
            }
        }
        return res!!
    }

    @JvmField
    var _workingDirectory = ""
    var workingDirectory: String
        set(value) {
            if (value.endsWith("/")) {
                _workingDirectory = value
            } else {
                _workingDirectory = value + "/"
            }
        }
        get() = _workingDirectory

    @JvmField
    var commited = false

    @JvmField
    var dontCheckVariableExistence = false

    @JvmField
    var generatedNameCounter = 0

    @JvmField
    var generatedNameByBase = mutableMapOf<String, String>()

    @JvmField
    val partitions = mutableMapOf<Long, PartitionHelper>()

    @JvmField
    val partitionsLock = Lock()
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

    fun getUniqueVariableName() = "#+${generatedNameCounter++}"
    fun isGeneratedVariableName(name: String) = name.startsWith('#')

    companion object {
        private val global_transactionID = ThreadSafeUuid()
    }

    fun commit() {
        DistributedTripleStore.commit(this)
        commited = true
    }

    protected fun finalize() {
    }
}
