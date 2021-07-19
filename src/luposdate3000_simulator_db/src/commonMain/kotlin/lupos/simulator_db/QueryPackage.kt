package lupos.simulator_db

public class QueryPackage(
    public val sourceAddress: Int,
    public val query: ByteArray,
    public val queryID: Int = -1
) : IDatabasePackage {

    public override fun getPackageSizeInBytes(): Int {
        return query.size + 4
    }
    public override fun getContentLogString(): String {
        return "QueryPackage($sourceAddress,'${query.decodeToString()}')"
    }
}
