import com.javadocmd.simplelatlng.LatLng
import iot.AppEntity
import iot.Device
import iot.PowerSupply
import iot.Sensor

object Stubs {

    fun createEmptyDevice(): Device {
        val loc = LatLng(0.0, 0.0)
        val name = ""
        val app = AppEntity()
        val sensors = ArrayList<Sensor>()
        val powerSupply = PowerSupply(-1.0)
        return Device(powerSupply, loc, name, app, sensors)
    }
}