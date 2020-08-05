package lupos.s00misc
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.CoroutineScope
class Partition {
    val data: Map<String, Int>
var scope:CoroutineScope

    companion object {
        var k = 12
        val queue_size = 1000
        inline fun hashFunction(v: Int): Int {
            if (v < 0) {
                return (-v) % k
            } else {
                return v % k
            }
        }
    }

    constructor() {
        data = mapOf<String, Int>()
	scope=GlobalScope
    }

    constructor(parentPartition: Partition, variableName: String, partitionNumber: Int,scope:CoroutineScope) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t.toMap()
this.scope=scope
    }

    constructor(parentPartition: Partition, variableName: String,scope:CoroutineScope) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t.toMap()
this.scope=scope
    }

    override fun equals(other: Any?) = other is Partition && data == other.data
    override fun hashCode() = data.hashCode()
}
