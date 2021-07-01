package lupos.simulator_db

public class QueryPackage (val sourceAddress: Int,val query: ByteArray):IDatabasePackage{
override    public fun getPackageSizeInBytes(): Int{
return query.size+4
}
override    public fun getContentLogString(): String{
return "QueryPackage($sourceAddress,'${query.decodeToString()}')"
}
}
