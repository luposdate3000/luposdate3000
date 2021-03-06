package lupos.simulator_db

public class QueryPackage(
    public val sourceAddress: Int,
    public val query: ByteArray,
) : IDatabasePackage {
    public val queryID: Int = idCounter++
    private companion object {
        private var idCounter: Int = 0
    }
    public override fun getPackageSizeInBytes(): Int {
        return query.size + 4
    }
    public override fun getContentLogString(): String {
        return "QueryPackage($sourceAddress,'${query.decodeToString()}')"
    }
}
