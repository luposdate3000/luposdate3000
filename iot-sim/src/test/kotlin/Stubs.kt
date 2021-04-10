

object Stubs {

    fun createEmptyDevice(address: Int): Device {
        val loc = GeoLocation(0.0, 0.0)
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        val protocols = mutableSetOf<LinkType>()
        return Device(powerSupply, loc, address, app, sensor, protocols)
    }

    fun createEmptyDevice(address: Int, protocols: Set<LinkType>): Device {
        val loc = GeoLocation(0.0, 0.0)
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, address, app, sensor, protocols)
    }

}