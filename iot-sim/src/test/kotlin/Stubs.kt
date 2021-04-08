

object Stubs {

    fun createEmptyDevice(): Device {
        val loc = GeoLocation(0.0, 0.0)
        val name = ""
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        val protocols = mutableSetOf<LinkType>()
        return Device(powerSupply, loc, name, app, sensor, protocols)
    }

    fun createEmptyDevice(protocols: Set<LinkType>): Device {
        val loc = GeoLocation(0.0, 0.0)
        val name = ""
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, name, app, sensor, protocols)
    }

}