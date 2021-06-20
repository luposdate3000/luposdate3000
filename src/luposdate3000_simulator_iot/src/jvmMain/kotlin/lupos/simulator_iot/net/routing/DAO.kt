package lupos.simulator_iot.net.routing

import lupos.simulator_iot.net.IPayload

public class DAO(
    public val isPath: Boolean,
    public val destinations: IntArray,
    public val hopHasDatabase: Boolean,
    public val existingDatabaseHops: IntArray
): IPayload {
    override fun getSizeInBytes(): Int {
        val ipv6InBytes = 16
        val destinationsSize = destinations.size * ipv6InBytes
        val existingDatabaseHopsSize = existingDatabaseHops.size * ipv6InBytes
        return destinationsSize + existingDatabaseHopsSize
    }

}
