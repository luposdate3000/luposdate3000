package lupos.simulator_iot.models.routing

import lupos.simulator_iot.models.net.NetworkPackage

public interface IRoutingProtocol {
    public var isRoot: Boolean
    public fun startRouting()
    public fun isControlPackage(pck: NetworkPackage): Boolean
    public fun processControlPackage(pck: NetworkPackage)
    public fun getNextHop(destinationAddress: Int): Int
    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}
