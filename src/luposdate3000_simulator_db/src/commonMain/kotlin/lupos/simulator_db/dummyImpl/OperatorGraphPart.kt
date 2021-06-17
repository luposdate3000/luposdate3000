package lupos.simulator_db.dummyImpl

public open class OperatorGraphPart {
    public fun getUUID(): Int {
        return 0
    }

    public fun evaluate(): ByteArray {
        return ByteArray(0)
    }

    public fun canBeEvaluatedWithoutRemoteDependencies(): Boolean {
        return true
    }

    public fun canBeEvaluatedWithTheseDependencies(l: List<OperatorGraphPart>): Boolean {
        return true
    }

    public fun mergeAndGetDependencies(l: List<OperatorGraphPart>): List<OperatorGraphPart> {
        return listOf()
    }

    public companion object {
        public fun fromByteArray(b: ByteArray): List<OperatorGraphPart> {
            return listOf()
        }

        public fun encodeToByteArray(l: List<OperatorGraphPart>): ByteArray {
            return ByteArray(0) // DB can filter here to reduce network-amount
        }
    }
}
