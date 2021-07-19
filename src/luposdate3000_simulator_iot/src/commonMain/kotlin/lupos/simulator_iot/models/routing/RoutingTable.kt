package lupos.simulator_iot.models.routing

internal class RoutingTable(
    private val ownAddress: Int,
    private val addressSpace: Int,
    private val hasDatabase: Boolean
) {

    private var nextHops = IntArray(0)
    private var nextDatabaseHops = IntArray(0)
    private var hops: MutableSet<Int> = mutableSetOf()

    internal var destinationCounter: Int = 0
        private set

    internal companion object {
        internal const val notInitialized: Int = -1
    }

    internal var fallbackHop = ownAddress

    private fun updateHop(destinationAddress: Int, nextHopAddress: Int, nextDatabaseHopAddress: Int): Boolean {
        var updated = false
        initializeEntries()

        if (nextHops[destinationAddress] == notInitialized) {
            destinationCounter++
        }

        if (nextHops[destinationAddress] != nextHopAddress) {
            updated = true
        }

        nextHops[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)

        nextDatabaseHops[destinationAddress] = nextDatabaseHopAddress

        return updated
    }

    private fun initializeEntries() {
        if (nextHops.isEmpty()) {
            nextHops = IntArray(addressSpace) { notInitialized }
        }

        if (nextDatabaseHops.isEmpty()) {
            nextDatabaseHops = IntArray(addressSpace) { notInitialized }
        }
    }

    internal fun getNextHop(destinationAddress: Int): Int =
        if (!hasDestination(destinationAddress)) {
            fallbackHop
        } else {
            nextHops[destinationAddress]
        }

    internal fun getNextDatabaseHop(destinationAddress: Int): Int =
        if (!hasDestination(destinationAddress)) {
            getOwnAddressIfItHasDatabase()
        } else {
            val hop = nextDatabaseHops[destinationAddress]
            if (hop != notInitialized) hop else getOwnAddressIfItHasDatabase()
        }

    private fun getOwnAddressIfItHasDatabase(): Int =
        if (hasDatabase) ownAddress else notInitialized

    internal fun getNextDatabaseHops(destinationAddresses: IntArray): IntArray {
        val dbHops = IntArray(destinationAddresses.size) { -1 }
        for ((index, dest) in destinationAddresses.withIndex())
            dbHops[index] = getNextDatabaseHop(dest)

        return dbHops
    }

    private fun hasDestination(destinationAddress: Int) =
        destinationAddress <= nextHops.size - 1 && nextHops[destinationAddress] != notInitialized

    internal fun removeDestinationsByHop(hop: Int): Boolean {
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

    internal fun setDestinationsByHop(hop: Int, destinations: IntArray, existingDatabaseHops: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, notInitialized)
        for ((index, dest) in destinations.withIndex()) {
            val flag = updateHop(dest, hop, existingDatabaseHops[index])
            updated = updated || flag
        }
        return updated
    }

    internal fun setDestinationsByDatabaseHop(hop: Int, destinations: IntArray): Boolean {
        var updated: Boolean
        updated = updateHop(hop, hop, hop)
        for (dest in destinations) {
            val flag = updateHop(dest, hop, hop)
            updated = updated || flag
        }
        return updated
    }

    internal fun getDestinations(): IntArray {
        val destinations = IntArray(destinationCounter)
        var destIndex = 0
        for ((index, value) in nextHops.withIndex())
            if (value != notInitialized) {
                destinations[destIndex] = index
                destIndex++
            }
        return destinations
    }

    internal fun getHops(): Set<Int> = hops
}
