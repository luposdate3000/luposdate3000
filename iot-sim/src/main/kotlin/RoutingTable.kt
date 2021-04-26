class RoutingTable(var defaultAddress: Int) {

    private var entries = IntArray(0)
    private val notInitialized = -1
    var entryCounter = 0
        private set


    private fun update(destinationAddress: Int, nextHopAddress: Int) {
        if(entries.isEmpty())
            entries = IntArray(Configuration.devices.size) { notInitialized }
        if(entries[destinationAddress] == notInitialized)
            entryCounter++
        entries[destinationAddress] = nextHopAddress
    }

    fun getNextHop(destinationAddress: Int)
        =   if(!hasDestination(destinationAddress))
                defaultAddress
            else
                entries[destinationAddress]

    private fun hasDestination(destinationAddress: Int)
        = destinationAddress <= entries.size-1 && entries[destinationAddress] != notInitialized

    fun removeDestinationsByHop(hop: Int) {
        for ((index, value) in entries.withIndex())
            if(value == hop) {
                entries[index] = notInitialized
                entryCounter--
            }
    }

    fun setDestinationsByHop(hop: Int, destinations: IntArray) {
        update(hop, hop)
        for (dest in destinations)
            update(dest, hop)
    }

    fun getDestinations(): IntArray {
        val destinations = IntArray(entryCounter)
        var destIndex = 0
        for ((index, value) in entries.withIndex())
            if(value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }


}