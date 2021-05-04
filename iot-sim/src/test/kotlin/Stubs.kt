

object Stubs {

    fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation.getRandom()
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, address, null, null, IntArray(0))
    }

    fun createEmptyDevice(address: Int, linkTypes: IntArray): Device {
        val loc = GeoLocation.getRandom()
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, address, null, null, linkTypes)
    }

}