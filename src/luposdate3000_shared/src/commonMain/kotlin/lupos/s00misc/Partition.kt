package lupos.s00misc

class Partition {
    val data: MutableMap<String, Int>
    val limit: MutableMap<String, Int>

    companion object {
        val estimatedPartitions1 = mutableMapOf<String, MutableSet<Int>>()
        val estimatedPartitions2 = mutableMapOf<String, MutableSet<Int>>()
        var default_k: Int = 128
        const val queue_size: Int = 1000
        inline fun hashFunction(v: Int, k: Int): Int {
            return if (v < 0) {
                (-v) % k
            } else {
                v % k
            }
        }
    }

    constructor() {
        data = mutableMapOf()
        limit = mutableMapOf()
    }

    constructor(parentPartition: Partition, variableName: String, partitionNumber: Int, partitionLimit: Int) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            t2[k] = v
        }
        t2[variableName] = partitionLimit
        limit = t2
    }

    constructor(parentPartition: Partition, variableName: String) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t
        val t2 = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.limit) {
            if (k != variableName) {
                t2[k] = v
            }
        }
        limit = t2
    }

    override fun equals(other: Any?): Boolean = other is Partition && data == other.data && limit == other.limit
    override fun hashCode(): Int = data.hashCode()
    fun toXMLElement(): XMLElement {
        val res = XMLElement("Partition")//
        for ((k, v) in limit) {
            res.addContent(XMLElement("Limit").addAttribute("name", k).addAttribute("value", "$v"))
        }
        return res
    }
}
