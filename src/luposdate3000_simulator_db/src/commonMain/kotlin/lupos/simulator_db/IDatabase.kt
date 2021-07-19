package lupos.simulator_db

public interface IDatabase {
    public fun start(initialState: DatabaseState)
    public fun activate()
    public fun deactivate()
    public fun end()
    public fun receive(pck: IDatabasePackage)
}
