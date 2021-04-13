

object Stubs {

    fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation.getRandom()
        val app = Database()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, address, app, sensor, IntArray(0))
    }

    fun createEmptyDevice(address: Int, linkTypes: IntArray): Device {
        val loc = GeoLocation.getRandom()
        val app = Database()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, address, app, sensor, linkTypes)
    }

}