import com.javadocmd.simplelatlng.LatLng

object Stubs {

    fun createEmptyDevice(): Device {
        val loc = LatLng(0.0, 0.0)
        val name = ""
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        val protocols = mutableSetOf<ProtocolType>()
        return Device(powerSupply, loc, name, app, sensor, protocols)
    }

    fun createEmptyDevice(protocols: Set<ProtocolType>): Device {
        val loc = LatLng(0.0, 0.0)
        val name = ""
        val app = DatabaseApp()
        val sensor = null
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, name, app, sensor, protocols)
    }

}