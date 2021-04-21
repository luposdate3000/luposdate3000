class RoutingTable(var defaultAddress: Int) {

    private val routingTable: MutableMap<Int, Int> = HashMap()

    private fun update(destinationAddress: Int, nextHopAddress: Int) {
        routingTable[destinationAddress] = nextHopAddress
    }

    fun getNextHop(destinationAddress: Int): Int
        = routingTable[destinationAddress]?: defaultAddress

    fun size() = routingTable.size

    fun removeDestinationsByHop(hop: Int) {
        for ((key, value) in routingTable)
            if(value == hop)
                routingTable.remove(key)
    }

    fun setDestinationsByHop(hop: Int, destinations: Set<Int>) {
        update(hop, hop)
        for (dest in destinations)
            update(dest, hop)
    }

    fun getDestinations()
        = routingTable.keys

}