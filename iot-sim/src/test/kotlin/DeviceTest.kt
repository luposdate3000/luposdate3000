import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeviceTest {


    @Test
    fun `two devices with within different networks are not linkable`() {
        val deviceOne: Device = Stubs.createEmptyDevice()
        deviceOne.networkPrefix = "A"
        val deviceTwo: Device = Stubs.createEmptyDevice()
        Assertions.assertNull(deviceOne.isLinkable(deviceTwo))
    }

    @Test
    fun `two devices with different protocols cannot link`() {
        val deviceOne: Device = Stubs.createEmptyDevice()
        val protocolType = ProtocolType("X")
        val deviceTwo: Device = Stubs.createEmptyDevice(mutableSetOf(protocolType))
        Assertions.assertNull(deviceOne.isLinkable(deviceTwo))
    }

    @Test
    fun `two devices are too far away to link`() {
        val protocolX = ProtocolType("X", 50, 7 )
        val deviceOne: Device = Stubs.createEmptyDevice(mutableSetOf(protocolX))
        deviceOne.location = LatLng.random()
        val deviceTwo: Device = Stubs.createEmptyDevice(mutableSetOf(protocolX))
        val distance = 51.0
        deviceTwo.location = deviceTwo.createNorthernLocation(deviceOne.location, distance)
        Assertions.assertNull(deviceOne.isLinkable(deviceTwo))
    }

    @Test
    fun `two devices link with most suitable protocol`() {
        val protocolX = ProtocolType("X", 50, 7 )
        val protocolY = ProtocolType("Y", 50, 8 )
        val protocolZ = ProtocolType("Z", 48, 9 )
        val protocolSet = mutableSetOf(protocolX, protocolY, protocolZ)
        val deviceOne: Device = Stubs.createEmptyDevice(protocolSet)
        val deviceTwo: Device = Stubs.createEmptyDevice(protocolSet)
        deviceOne.location = LatLng.random()
        val distance = 49.0
        deviceTwo.location = deviceTwo.createNorthernLocation(deviceOne.location, distance)
        val result = deviceOne.isLinkable(deviceTwo)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(protocolY.name, result!!.name)
    }


}