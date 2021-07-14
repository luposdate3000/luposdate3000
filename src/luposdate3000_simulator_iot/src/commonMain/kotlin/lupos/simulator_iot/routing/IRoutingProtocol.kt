package lupos.simulator_iot.routing

import lupos.simulator_iot.net.NetworkPackage

internal interface IRoutingProtocol {
    var isRoot: Boolean
    fun startRouting()
    fun isControlPackage(pck: NetworkPackage): Boolean
    fun processControlPackage(pck: NetworkPackage)
    fun getNextHop(destinationAddress: Int): Int
    fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}
