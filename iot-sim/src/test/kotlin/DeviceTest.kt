import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeviceTest {


    @Test
    fun `two devices with different linkTypes cannot link`() {
        val deviceOne: Device = Stubs.createEmptyDevice(1)
        val linkType = LinkType("X")
        val deviceTwo: Device = Stubs.createEmptyDevice(2, mutableSetOf(linkType))
        Assertions.assertNull(deviceOne.getBestLinkIfPossible(deviceTwo))
    }

    @Test
    fun `two devices are too far away to link`() {
        val linkTypeX = LinkType("X", 50, 7 )
        val deviceOne: Device = Stubs.createEmptyDevice(1, mutableSetOf(linkTypeX))
        deviceOne.location = GeoLocation.getRandom()
        val deviceTwo: Device = Stubs.createEmptyDevice(2,mutableSetOf(linkTypeX))
        val distance = 51
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        Assertions.assertNull(deviceOne.getBestLinkIfPossible(deviceTwo))
    }

    @Test
    fun `two devices link with most suitable linkType`() {
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        val linkTypeSet = mutableSetOf(linkTypeX, linkTypeY, linkTypeZ)
        val deviceOne: Device = Stubs.createEmptyDevice(1,linkTypeSet)
        val deviceTwo: Device = Stubs.createEmptyDevice(2,linkTypeSet)
        deviceOne.location = GeoLocation.getRandom()
        val distance = 49
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        val result = deviceOne.getBestLinkIfPossible(deviceTwo)
        Assertions.assertNotNull(result)
        Assertions.assertEquals(linkTypeY.name, result!!.linkType.name)
    }

    @Test
    fun cannotLinkWithItself() {
        val linkTypeX = LinkType("X", 50, 7 )
        val device: Device = Stubs.createEmptyDevice(1,mutableSetOf(linkTypeX))
        device.addAvailableLink(device)
        Assertions.assertNull(device.getAvailableLink(device))
        Assertions.assertFalse(device.hasAvailAbleLink(device))
        Assertions.assertNull(device.getBestLinkIfPossible(device))
    }


//    @Test
//    fun `one device is close enough but not linkable`() {
//        val linkTypeX = LinkType("X", 50, 7 )
//        val linkTypeY = LinkType("Y", 42, 70 )
//        val linkTypeSet = mutableSetOf(linkTypeX, linkTypeY)
//        val selectingDevice: Device = Stubs.createEmptyDevice(linkTypeSet)
//        selectingDevice.location = GeoLocation.getRandom()
//
//        val device1: Device = Stubs.createEmptyDevice(linkTypeSet)
//        device1.location = GeoLocation.createNorthernLocation(selectingDevice.location, 53)
//        val device2: Device = Stubs.createEmptyDevice(mutableSetOf(LinkType("something else")))
//        device2.location = GeoLocation.createNorthernLocation(selectingDevice.location, 40)
//
//        val list = arrayListOf(device1, device2)
//        Assertions.assertNull(selectingDevice.selectBestLink(list))
//    }

//    @Test
//    fun `select the closest and linkable device`() {
//        val linkTypeX = LinkType("X", 50, 7 )
//        val linkTypeY = LinkType("Y", 42, 70 )
//        val linkTypeSet = mutableSetOf(linkTypeX, linkTypeY)
//        val selectingDevice: Device = Stubs.createEmptyDevice(linkTypeSet)
//        selectingDevice.location = GeoLocation.getRandom()
//
//        val device1: Device = Stubs.createEmptyDevice(linkTypeSet)
//        device1.location = GeoLocation.createNorthernLocation(selectingDevice.location, 4.0)
//        val device2: Device = Stubs.createEmptyDevice(mutableSetOf(LinkType("something else")))
//        device2.location = GeoLocation.createNorthernLocation(selectingDevice.location, 0.0)
//        val device3: Device = Stubs.createEmptyDevice(linkTypeSet)
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