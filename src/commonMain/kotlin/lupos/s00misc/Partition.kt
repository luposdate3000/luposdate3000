package lupos.s00misc

class Partition {
    val data: Map<String, Int>

    constructor() {
        data = mapOf<String, Int>()
    }

    constructor(parentPartition: Partition, variableName: String, partitionNumber: Int) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            t[k] = v
        }
        t[variableName] = partitionNumber
        data = t.toMap()
    }

    constructor(parentPartition: Partition, variableName: String) {
        val t = mutableMapOf<String, Int>()
        for ((k, v) in parentPartition.data) {
            if (k != variableName) {
                t[k] = v
            }
        }
        data = t.toMap()
    }
}
