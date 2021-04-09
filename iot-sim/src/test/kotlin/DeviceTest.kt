import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeviceTest {


    @Test
    fun `two devices with different protocols cannot link`() {
        val deviceOne: Device = Stubs.createEmptyDevice()
        val linkType = LinkType("X")
        val deviceTwo: Device = Stubs.createEmptyDevice(mutableSetOf(linkType))
        Assertions.assertNull(deviceOne.createLinkIfPossible(deviceTwo))
    }

    @Test
    fun `two devices are too far away to link`() {
        val protocolX = LinkType("X", 50, 7 )
        val deviceOne: Device = Stubs.createEmptyDevice(mutableSetOf(protocolX))
        deviceOne.location = GeoLocation.getRandom()
        val deviceTwo: Device = Stubs.createEmptyDevice(mutableSetOf(protocolX))
        val distance = 51
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        Assertions.assertNull(deviceOne.createLinkIfPossible(deviceTwo))
    }

    @Test
    fun `two devices link with most suitable protocol`() {
        val protocolX = LinkType("X", 50, 7 )
        val protocolY = LinkType("Y", 50, 8 )
        val protocolZ = LinkType("Z", 48, 9 )
        val protocolSet = mutableSetOf(protocolX, protocolY, protocolZ)
        val deviceOne: Device = Stubs.createEmptyDevice(protocolSet)
        val deviceTwo: Device = Stubs.createEmptyDevice(protocolSet)
        deviceOne.location = GeoLocation.getRandom()
        val distance = 49
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        val result = deviceOne.createLinkIfPossible(deviceTwo)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(protocolY.name, result!!.linkType.name)
    }

/*    @Test
    fun `there is no best link`() {
        val protocolX = LinkType("X", 50, 7 )
        val protocolY = LinkType("Y", 42, 70 )
        val protocolSet = mutableSetOf(protocolX, protocolY)
        val selectingDevice: Device = Stubs.createEmptyDevice(protocolSet)
        selectingDevice.location = GeoLocation.getRandom()
        val device1: Device = Stubs.createEmptyDevice(protocolSet)
        device1.location = GeoLocation.createNorthernLocation(selectingDevice.location, 60.0)
        val list = arrayListOf(device1)
        Assertions.assertNull(selectingDevice.selectBestLink(list))
    }*/

//    @Test
//    fun `one device is close enough but not linkable`() {
//        val protocolX = LinkType("X", 50, 7 )
//        val protocolY = LinkType("Y", 42, 70 )
//        val protocolSet = mutableSetOf(protocolX, protocolY)
//        val selectingDevice: Device = Stubs.createEmptyDevice(protocolSet)
//        selectingDevice.location = GeoLocation.getRandom()
//
//        val device1: Device = Stubs.createEmptyDevice(protocolSet)
//        device1.location = GeoLocation.createNorthernLocation(selectingDevice.location, 53.0)
//        val device2: Device = Stubs.createEmptyDevice(mutableSetOf(LinkType("something else")))
//        device2.location = GeoLocation.createNorthernLocation(selectingDevice.location, 40.0)
//
//        val list = arrayListOf(device1, device2)
//        Assertions.assertNull(selectingDevice.selectBestLink(list))
//    }

//    @Test
//    fun `select the closest and linkable device`() {
//        val protocolX = LinkType("X", 50, 7 )
//        val protocolY = LinkType("Y", 42, 70 )
//        val protocolSet = mutableSetOf(protocolX, protocolY)
//        val selectingDevice: Device = Stubs.createEmptyDevice(protocolSet)
//        selectingDevice.location = GeoLocation.getRandom()
//
//        val device1: Device = Stubs.createEmptyDevice(protocolSet)
//        device1.location = GeoLocation.createNorthernLocation(selectingDevice.location, 4.0)
//        val device2: Device = Stubs.createEmptyDevice(mutableSetOf(LinkType("something else")))
//        device2.location = GeoLocation.createNorthernLocation(selectingDevice.location, 0.0)
//        val device3: Device = Stubs.createEmptyDevice(protocolSet)
//        device3.location = GeoLocation.createNorthernLocation(selectingDevice.location, 5.0)
//
//        val list = arrayListOf(device1, device2)
//        val link = selectingDevice.selectBestLink(list)
//        Assertions.assertNotNull(link)
//        Assertions.assertEquals(selectingDevice.name, link!!.srcAddress)
//        Assertions.assertEquals(device1.name, link.destAddress)
//        Assertions.assertEquals("Y", link.linkType.name)
//        Assertions.assertEquals(4.0, round(link.distanceInMeters))
//    }


}