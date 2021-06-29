package lupos.simulator_db

public interface IDatabaseState {
    public val ownAddress: Int
    public var allAddresses: IntArray
    public val sender: IRouter
    public val absolutePathToDataDirectory: String
}
