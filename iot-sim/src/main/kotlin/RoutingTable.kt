import java.util.*

class RoutingTable(var defaultAddress: Int) {

    private var entries = IntArray(0)
    private val notInitialized = -1
    private var hops = TreeSet<Int>()

    var destinationCounter = 0
        private set


    private fun update(destinationAddress: Int, nextHopAddress: Int) : Boolean {
        var updated = false
        if(entries.isEmpty())
            entries = IntArray(Configuration.devices.size) { notInitialized }

        if(entries[destinationAddress] == notInitialized)
            destinationCounter++

        if(entries[destinationAddress] != nextHopAddress)
            updated = true

        entries[destinationAddress] = nextHopAddress
        hops.add(nextHopAddress)
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
        updated = update(hop, hop)
        for (dest in destinations) {
            val tmp = update(dest, hop)
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