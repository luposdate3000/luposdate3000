package lupos.simulator_db

public interface IDatabase {
    public fun start(initialState: IDatabaseState)
    public fun activate(state: IDatabaseState)
    public fun deactivate(): IDatabaseState
    public fun end()
    public fun receiveQuery(sourceAddress: Int, query: ByteArray)
    public fun receive(pck: IDatabasePackage)
}

public interface IRouter {
    public fun send(destinationAddress: Int, pck: IDatabasePackage)
    public fun sendQueryResult(destinationAddress: Int, result: ByteArray)
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}

public interface IDatabasePackage

public interface IDatabaseState {
    public val ownAddress: Int
    public var allAddresses: IntArray
    public val sender: IRouter
    public val absolutePathToDataDirectory: String
}
