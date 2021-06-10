package lupos.simulator_iot.routing

public class RoutingTable(public var defaultAddress: Int, private val addressSpace: Int) {

    private var nextHops = IntArray(0)
    private var nextDatabaseHops = IntArray(0)
    private var hops: MutableSet<Int> = mutableSetOf()

    public var destinationCounter: Int = 0
        private set

    public companion object {
        public const val notInitialized: Int = -1
    }

    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int): Boolean {
        var updated = false
        initializeEntries()

        if (nextHops[destinationAddress] == notInitialized)
            destinationCounter++

        if (nextHops[destinationAddress] != nextHopAddress)
            updated = true

        nextHops[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)

        nextDatabaseHops[destinationAddress] = nextDatabaseHopAddress

        return updated
    }

    private fun initializeEntries() {
        if (nextHops.isEmpty())
            nextHops = IntArray(addressSpace) { notInitialized }

        if (nextDatabaseHops.isEmpty())
            nextDatabaseHops = IntArray(addressSpace) { notInitialized }
    }

    public fun getNextHop(destinationAddress: Int): Int =
        if (!hasDestination(destinationAddress))
            defaultAddress
        else
            nextHops[destinationAddress]

    public fun getNextDatabaseHop(destinationAddress: Int): Int =
        if (!hasDestination(destinationAddress))
            notInitialized
        else
            nextDatabaseHops[destinationAddress]

    public fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        val dbHops = IntArray(destinationAddresses.size) { -1 }
        for ((index, dest) in destinationAddresses.withIndex())
            dbHops[index] = getNextDatabaseHop(dest)

        return dbHops
    }

    private fun hasDestination(destinationAddress: Int) =
        destinationAddress <= nextHops.size - 1 && nextHops[destinationAddress] != notInitialized

    public fun removeDestinationsByHop(hop: Int): Boolean {
        var updated = false
        for ((index, value) in nextHops.withIndex())
            if (value == hop) {
                nextHops[index] = notInitialized
                nextDatabaseHops[index] = notInitialized
                destinationCounter--
                updated = true
            }
        hops.remove(hop)
        return updated
    }

    public fun setDestinationsByHop(hop: Int, destinations: IntArray, existingDatabaseHops: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, notInitialized)
        for ((index, dest) in destinations.withIndex()) {
            val flag = updateHop(dest, hop, existingDatabaseHops[index])
            updated = updated || flag
        }
        return updated
    }

    public fun setDestinationsByDatabaseHop(hop: Int, destinations: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, hop)
        for (dest in destinations) {
            val flag = updateHop(dest, hop, hop)
            updated = updated || flag
        }
        return updated
    }

    public fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in nextHops.withIndex())
            if (value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    public fun getHops(): Set<Int> = hops
}
