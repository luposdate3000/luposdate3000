package lupos.iot_db_interface.dummyImpl
import lupos.iot_db_interface.IDatabaseState
import lupos.iot_db_interface.IRouter

public class DatabaseState(
    override val ownAddress: Int,
    override var allAddresses: IntArray,
    override val sender: IRouter,
    override val absolutePathToDataDirectory: String): IDatabaseState
{
    public val queriesInProgress: MutableMap<Int, Query> = mutableMapOf()
    public var addressForQueryEndResult: Int = -1
    public lateinit var dataFile: String
}
