package lupos.simulator_db
import lupos.simulator_db.luposdate3000.MySimulatorConfig
import lupos.visualize.distributed.database.VisualisationNetwork
public abstract class DatabaseState(
    public val visualisationNetwork: VisualisationNetwork,
    public val ownAddress: Int,
    public var allAddresses: IntArray,
    public val sender: IRouter,
    public val absolutePathToDataDirectory: String,
    public val enableSharedMemoryDictionaryCheat: Boolean,
    public val dbConfig: MySimulatorConfig?,
)
