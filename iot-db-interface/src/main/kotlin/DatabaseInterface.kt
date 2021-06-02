interface IDatabase {
    fun start(initialState: IDatabaseState)
    fun activate(state: IDatabaseState)
    fun deactivate(): IDatabaseState
    fun end()
    fun receiveQuery(from: Int, query: ByteArray)
    fun receive(pck: IDatabasePackage)
}

interface IRouter {
    fun send(to: Int, pck: IDatabasePackage)
    fun sendQueryResult(to: Int, result: ByteArray)
    fun getNextDBHopsFor(destinationAddresses: IntArray): IntArray
}

interface IDatabasePackage

interface IDatabaseState {
    val ownAddress: Int
    var allAddresses: IntArray
    val sender: IRouter
    val absolutePathToDataDirectory: String
}