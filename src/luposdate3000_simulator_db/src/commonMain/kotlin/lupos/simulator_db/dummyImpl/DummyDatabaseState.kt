package lupos.simulator_db.dummyImpl

import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IRouter

public class DummyDatabaseState(
    ownAddress: Int,
    allAddresses: IntArray,
    sender: IRouter,
    absolutePathToDataDirectory: String
) :
    DatabaseState(ownAddress, allAddresses, sender, absolutePathToDataDirectory) {

    public val queriesInProgress: MutableMap<Int, Query> = mutableMapOf()
    public var addressForQueryEndResult: Int = -1
    public lateinit var dataFile: String
}
