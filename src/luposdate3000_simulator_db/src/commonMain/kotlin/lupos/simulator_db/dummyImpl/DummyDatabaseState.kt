package lupos.simulator_db.dummyImpl

import lupos.simulator_db.DatabaseState
import lupos.simulator_db.IRouter
import lupos.visualize.distributed.database.VisualisationNetwork
public class DummyDatabaseState(
    visualisationNetwork: VisualisationNetwork,
    ownAddress: Int,
    allAddresses: IntArray,
    sender: IRouter,
    absolutePathToDataDirectory: String
) :
    DatabaseState(visualisationNetwork, ownAddress, allAddresses, sender, absolutePathToDataDirectory, true, null) {

    public val queriesInProgress: MutableMap<Int, Query> = mutableMapOf()
    public var addressForQueryEndResult: Int = -1
    public lateinit var dataFile: String
}
