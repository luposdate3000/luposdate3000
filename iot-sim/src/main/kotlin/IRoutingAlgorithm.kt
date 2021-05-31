interface IRoutingAlgorithm {
    fun startRouting()
    fun isControlPackage(pck: NetworkPackage): Boolean
    fun isSelfEvent(marker: Any): Boolean
    fun processSelfEvent(marker: Any)
    fun processControlPackage(pck: NetworkPackage)
    fun getNextHop(destinationAddress: Int): Int
    fun getNextDatabaseHop(destinationAddress: Int): Int
    fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray
}