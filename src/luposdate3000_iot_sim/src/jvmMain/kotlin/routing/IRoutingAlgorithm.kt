package lupos.iot_sim.routing

import lupos.iot_sim.NetworkPackage

public interface IRoutingAlgorithm {
    public var isRoot: Boolean
    public fun startRouting()
    public fun isControlPackage(pck: NetworkPackage): Boolean
    public fun processControlPackage(pck: NetworkPackage)
    public fun getNextHop(destinationAddress: Int): Int
    public fun getNextDatabaseHop(destinationAddress: Int): Int
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}
