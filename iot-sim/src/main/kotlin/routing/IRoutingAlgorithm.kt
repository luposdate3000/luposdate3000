package routing

import NetworkPackage

interface IRoutingAlgorithm {
    fun startRouting()
    fun isControlPackage(pck: NetworkPackage): Boolean
    fun processControlPackage(pck: NetworkPackage)
    fun getNextHop(destinationAddress: Int): Int
    fun getNextDatabaseHop(destinationAddress: Int): Int
    fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}