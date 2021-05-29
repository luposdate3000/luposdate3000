package dummyImpl

open class OperatorGraphPart {
    fun getUUID(): Int {
        return 0
    }
    fun evaluate(): ByteArray {
        return ByteArray(0)
    }
    fun canBeEvaluatedWithoutRemoteDependencies(): Boolean {
        return true
    }
    fun canBeEvaluatedWithTheseDependencies(l: List<OperatorGraphPart>): Boolean {
        return true
    }
    fun mergeAndGetDependencies(l: List<OperatorGraphPart>): List<OperatorGraphPart> {
        return listOf()
    }
    companion object {
        fun fromByteArray(b: ByteArray): List<OperatorGraphPart> {
            return listOf()
        }
        fun encodeToByteArray(l: List<OperatorGraphPart>): ByteArray {
            return ByteArray(0)
        }
    }
}