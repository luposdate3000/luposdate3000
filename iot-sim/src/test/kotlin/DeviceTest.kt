import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DeviceTest {


    @Test
    fun `get sorted LinkType Indices`() {
        val linkTypeW = LinkType("W", 51, 13 )
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        val linkTypes = arrayListOf(linkTypeW, linkTypeX, linkTypeY, linkTypeZ)
        Device.sortedLinkTypes = linkTypes

        val expected1 = intArrayOf(1, 2)
        val actual1 = Device.getSortedLinkTypeIndices(arrayListOf(linkTypeY, linkTypeZ))
        val expected2 = intArrayOf(0, 3)
        val actual2 = Device.getSortedLinkTypeIndices(arrayListOf(linkTypeW, linkTypeX))

        Assertions.assertTrue(expected1.contentEquals(actual1))
        Assertions.assertTrue(expected2.contentEquals(actual2))
    }

    @Test
    fun `two devices with different linkTypes cannot link`() {
        val deviceOne: Device = Stubs.createEmptyDevice(1)
        val linkType = LinkType("X")
        Device.sortedLinkTypes = arrayListOf(linkType)
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0))
        Assertions.assertNull(deviceOne.getBestLink(deviceTwo))
    }

    @Test
    fun `two devices are too far away to link`() {
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypes = arrayListOf(linkTypeX)
        Device.sortedLinkTypes = linkTypes

        val deviceOne: Device = Stubs.createEmptyDevice(1, intArrayOf(0))
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0))
        val distance = 51
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        Assertions.assertNull(deviceOne.getBestLink(deviceTwo))
    }


    @Test
    fun `two devices link with most suitable linkType`() {
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        val linkTypes = arrayListOf(linkTypeX, linkTypeY, linkTypeZ)
        Device.sortedLinkTypes = linkTypes
        val deviceOne: Device = Stubs.createEmptyDevice(1, intArrayOf(0, 1, 2))
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0, 1, 2))
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, 49)

        Assertions.assertNotNull(deviceOne.getBestLink(deviceTwo))
        Assertions.assertEquals(1, deviceOne.getBestLink(deviceTwo)!!.linkTypeIndex)
    }

    @Test
    fun cannotLinkWithItself() {
        val linkTypeX = LinkType("X", 50, 7 )
        Device.sortedLinkTypes = arrayListOf(linkTypeX)
        val device: Device = Stubs.createEmptyDevice(1, intArrayOf(0))
        device.addAvailableLink(device)
        Assertions.assertNull(device.getAvailableLink(device))
        Assertions.assertFalse(device.hasAvailAbleLink(device))
        Assertions.assertNull(device.getBestLink(device))
    }

}