package lupos.simulator_db

public abstract class DatabaseState(
    public val ownAddress: Int,
    public var allAddresses: IntArray,
    public val sender: IRouter,
    public val absolutePathToDataDirectory: String
)
