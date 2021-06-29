package lupos.simulator_db

public interface IDatabase {
    public fun start(initialState: IDatabaseState)
    public fun activate()
    public fun deactivate()
    public fun end()
    public fun receiveQuery(sourceAddress: Int, query: ByteArray)
    public fun receive(pck: IDatabasePackage)
}

public interface IRouter {
    public fun send(destinationAddress: Int, pck: IDatabasePackage)
    public fun sendQueryResult(destinationAddress: Int, result: ByteArray)
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}

public interface IDatabasePackage {
    public fun getPackageSizeInBytes(): Int
    public fun getContentLogString(): String
}

public interface IDatabaseState {
    public val ownAddress: Int
    public var allAddresses: IntArray
    public val sender: IRouter
    public val absolutePathToDataDirectory: String
}
