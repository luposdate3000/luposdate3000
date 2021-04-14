class RoutingTable(var defaultAddress: Int) {

    private val routingTable: MutableMap<Int, Int> = HashMap()

    fun update(destinationAddress: Int, nextHopAddress: Int) {
        routingTable[destinationAddress] = nextHopAddress
    }

    fun lookup(destinationAddress: Int): Int
        = routingTable[destinationAddress]?: defaultAddress

}