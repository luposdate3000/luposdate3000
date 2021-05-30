

class RoutingTable(var defaultAddress: Int, private val addressSpace: Int) {

    private var entries = IntArray(0)
    private val notInitialized = -1
    private var hops: MutableSet<Int> = mutableSetOf()

    var destinationCounter = 0
        private set


    private fun updateHop(destinationAddress: Int, nextHopAddress: Int) : Boolean {
        var updated = false
        if(entries.isEmpty())
            entries = IntArray(addressSpace) { notInitialized }

        if(entries[destinationAddress] == notInitialized)
            destinationCounter++

        if(entries[destinationAddress] != nextHopAddress)
            updated = true

        entries[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)
        return updated
    }

    private fun updateDBHop(destinationAddress: Int, nextDBHopAddress: Int) : Boolean {
        var updated = false
        //TODO nehme zweites Array für db hops
        //Dafür reicht ein boolean array.
        //schicke bei DAO ein Flag für DB mit
        return updated
    }

    fun getNextHop(destinationAddress: Int)
        =   if(!hasDestination(destinationAddress))
                defaultAddress
            else
                entries[destinationAddress]

    private fun hasDestination(destinationAddress: Int)
        = destinationAddress <= entries.size-1 && entries[destinationAddress] != notInitialized

    fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        for ((index, value) in entries.withIndex())
            if(value == hop) {
                entries[index] = notInitialized
                destinationCounter--
                updated = true
            }
        hops.remove(hop)
        return updated
    }

    fun setDestinationsByHop(hop: Int, destinations: IntArray) : Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop)
        for (dest in destinations) {
            val tmp = updateHop(dest, hop)
            if (!updated)
                updated = tmp
        }
        return updated
    }

    fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in entries.withIndex())
            if(value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    fun getHops(): Set<Int> = hops



}