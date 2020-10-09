package lupos.s00misc

class Partition {
    val data: Map<String, Int>
    val limit: Map<String, Int>

    companion object {
        var default_k = 12
        const val queue_size = 1000
        inline fun hashFunction(v: Int,k:Int): Int {
            if (v < 0) {
                return (-v) % k
            } else {
                return v % k
            }
        }
    }

    constructor() {
        data = mapOf<String, Int>()
        limit = mapOf<String, Int>()
    }

    constructor(parentPartition: Partition, variableName: String, partitionNumber: Int,partitionLimit:Int) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t.toMap()
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            t2[k] = v
        }
        t2[variableName] = partitionLimit
        limit = t2.toMap()
    }

    constructor(parentPartition: Partition, variableName: String) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t.toMap()
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            if (k != variableName) {
                t2[k] = v
            }
        }
        limit = t2.toMap()
    }

    override fun equals(other: Any?) = other is Partition && data == other.data &&limit==other.limit
    override fun hashCode() = data.hashCode()
}
