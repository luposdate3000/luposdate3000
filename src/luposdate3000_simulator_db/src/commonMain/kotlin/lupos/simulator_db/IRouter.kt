package lupos.simulator_db

public interface IRouter {
    public fun send(destinationAddress: Int, pck: IDatabasePackage)
    public fun sendQueryResult(destinationAddress: Int, result: ByteArray)
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}
