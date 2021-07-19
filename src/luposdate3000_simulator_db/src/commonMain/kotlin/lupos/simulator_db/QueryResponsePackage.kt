package lupos.simulator_db

public class QueryResponsePackage(
    public val result: ByteArray,
    public val queryID: Int
) : IDatabasePackage {

    public override fun getPackageSizeInBytes(): Int {
        return result.size
    }
    public override fun getContentLogString(): String {
        return "QueryResponsePackage('${result.decodeToString()}')"
    }
}
