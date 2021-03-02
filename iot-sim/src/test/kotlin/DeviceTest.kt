import AppEntity
import Device
import PowerSupply
import Sensor
import com.javadocmd.simplelatlng.LatLng
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeviceTest {

    @Test
    fun `new device with default variables`() {
        val loc = LatLng(0.0, 0.0)
        val name = ""
        val app = AppEntity()
        val sensors = ArrayList<Sensor>()
        val powerSupply = PowerSupply(-1.0)
        val device = Device(powerSupply, loc, name, app, sensors)
        Assertions.assertNotNull(device.networkCard)

    }

}