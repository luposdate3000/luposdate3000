

class RoutingTable(var defaultAddress: Int, private val addressSpace: Int) {

    private var nextHops = IntArray(0)
    private var nextDatabaseHops = IntArray(0)
    private var hops: MutableSet<Int> = mutableSetOf()

    var destinationCounter = 0
        private set

    companion object {
        const val notInitialized = -1
    }


    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int) : Boolean {
        var updated = false
        initializeEntries()

        if(nextHops[destinationAddress] == notInitialized)
            destinationCounter++

        if(nextHops[destinationAddress] != nextHopAddress)
            updated = true

        nextHops[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)

        nextDatabaseHops[destinationAddress] = nextDatabaseHopAddress

        return updated
    }

    private fun initializeEntries() {
        if(nextHops.isEmpty())
            nextHops = IntArray(addressSpace) { notInitialized }

        if(nextDatabaseHops.isEmpty())
            nextDatabaseHops = IntArray(addressSpace) { notInitialized }
    }


    fun getNextHop(destinationAddress: Int)
        =   if(!hasDestination(destinationAddress))
                defaultAddress
            else
                nextHops[destinationAddress]


    fun getNextDatabaseHop(destinationAddress: Int)
        =   if(!hasDestination(destinationAddress))
                notInitialized
            else
                nextDatabaseHops[destinationAddress]


    fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        val dbHops = IntArray(destinationAddresses.size) {-1}
        for ((index, dest) in destinationAddresses.withIndex())
            dbHops[index] = getNextDatabaseHop(dest)

        return dbHops
    }


    private fun hasDestination(destinationAddress: Int)
        = destinationAddress <= nextHops.size-1 && nextHops[destinationAddress] != notInitialized

    fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        for ((index, value) in nextHops.withIndex())
            if(value == hop) {
                nextHops[index] = notInitialized
                nextDatabaseHops[index] = notInitialized
                destinationCounter--
                updated = true
            }
        hops.remove(hop)
        return updated
    }

    fun setDestinationsByHop(hop: Int, destinations: IntArray, existingDatabaseHops: IntArray) : Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, notInitialized)
        for ((index, dest) in destinations.withIndex()) {
            val flag = updateHop(dest, hop, existingDatabaseHops[index])
            updated = updated || flag
        }
        return updated
    }

    fun setDestinationsByDatabaseHop(hop: Int, destinations: IntArray) : Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, hop)
        for (dest in destinations) {
            val flag = updateHop(dest, hop, hop)
            updated = updated || flag
        }
        return updated
    }


    fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in nextHops.withIndex())
            if(value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    fun getHops(): Set<Int> = hops



}