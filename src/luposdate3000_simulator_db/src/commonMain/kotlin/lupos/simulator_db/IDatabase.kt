package lupos.simulator_db

public interface IDatabase {
    public fun start(initialState: IDatabaseState)
    public fun activate()
    public fun deactivate()
    public fun end()
    public fun receiveQuery(sourceAddress: Int, query: ByteArray)
    public fun receive(pck: IDatabasePackage)
}
