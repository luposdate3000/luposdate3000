package lupos.simulator_db

public class QueryResponsePackage (val result: ByteArray):IDatabasePackage{
override    public fun getPackageSizeInBytes(): Int{
return result.size
}
override    public fun getContentLogString(): String{
return "QueryResponsePackage('${result.decodeToString()}')"
}
}
