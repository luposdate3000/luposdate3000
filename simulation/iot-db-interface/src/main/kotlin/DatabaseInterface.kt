interface IDatabase {
    fun start(initialState: IDatabaseState)
    fun activate(state: IDatabaseState)
    fun deactivate(): IDatabaseState
    fun end()
    fun receiveQuery(sourceAddress: Int, query: ByteArray)
    fun receive(pck: IDatabasePackage)
}

interface IRouter {
    fun send(destinationAddress: Int, pck: IDatabasePackage)
    fun sendQueryResult(destinationAddress: Int, result: ByteArray)
    fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}

interface IDatabasePackage

interface IDatabaseState {
    val ownAddress: Int
    var allAddresses: IntArray
    val sender: IRouter
    val absolutePathToDataDirectory: String
}