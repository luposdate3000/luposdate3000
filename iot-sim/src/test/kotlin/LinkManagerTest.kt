import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class LinkManagerTest {

    @Test
    fun cannotLinkWithItself() {
        val linkManager = LinkManager(1)
        val linkTypeX = LinkType("X", 50, 7 )
        Device.sortedLinkTypes = arrayListOf(linkTypeX)
        val device: Device = Stubs.createEmptyDevice(0, intArrayOf(0))
        linkManager.setLinkIfPossible(device.address, device.address)
        Assertions.assertNull(linkManager.getLink(device.address, device.address))
        Assertions.assertFalse(linkManager.hasLink(device.address, device.address))
    }

    @Test
    fun `get sorted LinkType Indices`() {
        val linkManager = LinkManager(1)
        val linkTypeW = LinkType("W", 51, 13 )
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        val linkTypes = arrayOf(linkTypeW, linkTypeX, linkTypeY, linkTypeZ)
        linkManager.sortedLinkTypes = linkTypes

        val expected1 = intArrayOf(1, 2)
        val actual1 = linkManager.getSortedLinkTypeIndices(arrayListOf(linkTypeY, linkTypeZ))
        val expected2 = intArrayOf(0, 3)
        val actual2 = linkManager.getSortedLinkTypeIndices(arrayListOf(linkTypeW, linkTypeX))

        Assertions.assertTrue(expected1.contentEquals(actual1))
        Assertions.assertTrue(expected2.contentEquals(actual2))
    }

    @Test
    fun `two devices with different linkTypes cannot link`() {
        val linkManager = LinkManager(200)
        val deviceOne: Device = Stubs.createEmptyDevice(1)
        val linkType = LinkType("X")
        linkManager.sortedLinkTypes = arrayOf(linkType)
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0))
        Assertions.assertNull(deviceOne.getBestLink(deviceTwo))
        Assertions.assertNull(linkManager.getBestLink(deviceOne, deviceTwo))
    }

    @Test
    fun `two devices are too far away to link`() {
        val linkManager = LinkManager(200)
        val linkTypeX = LinkType("X", 50, 7 )
        linkManager.sortedLinkTypes = arrayOf(linkTypeX)

        val deviceOne: Device = Stubs.createEmptyDevice(1, intArrayOf(0))
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0))
        val distance = 51
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, distance)
        Assertions.assertNull(linkManager.getBestLink(deviceOne, deviceTwo))
        Assertions.assertNull(linkManager.getBestLink(deviceTwo, deviceOne))
    }

    @Test
    fun `two devices link with most suitable linkType`() {
        val linkManager = LinkManager(200)
        val linkTypeX = LinkType("X", 50, 7 )
        val linkTypeY = LinkType("Y", 50, 8 )
        val linkTypeZ = LinkType("Z", 48, 9 )
        linkManager.sortedLinkTypes = arrayOf(linkTypeX, linkTypeY, linkTypeZ)
        val deviceOne: Device = Stubs.createEmptyDevice(1, intArrayOf(0, 1, 2))
        val deviceTwo: Device = Stubs.createEmptyDevice(2, intArrayOf(0, 1, 2))
        deviceTwo.location = GeoLocation.createNorthernLocation(deviceOne.location, 49)

        val actualLink1 = linkManager.getBestLink(deviceOne, deviceTwo)
        val actualLink2 = linkManager.getBestLink(deviceTwo, deviceOne)
        Assertions.assertNotNull(actualLink1)
        Assertions.assertNotNull(actualLink2)
        Assertions.assertEquals(actualLink1, actualLink2)
        Assertions.assertEquals(1, actualLink1!!.linkTypeIndex)
        Assertions.assertEquals("Y", linkManager.getLinkTypeByIndex(actualLink1.linkTypeIndex).name)
    }

    @Test
    fun `set links`() {
        val linkManager = LinkManager(5000)
        val deviceAddress1 = 501
        val deviceAddress2 = 4999
        val deviceAddress3 = 0
        linkManager.setLink(deviceAddress1, deviceAddress2, Link(0,0,0))
        linkManager.setLink(deviceAddress1, deviceAddress3, Link(0,0,0))

        Assertions.assertTrue(linkManager.hasLink(deviceAddress1, deviceAddress2))
        Assertions.assertTrue(linkManager.hasLink(deviceAddress2, deviceAddress1))
        Assertions.assertTrue(linkManager.hasLink(deviceAddress1, deviceAddress3))
        Assertions.assertTrue(linkManager.hasLink(deviceAddress3, deviceAddress1))
        Assertions.assertEquals(2, linkManager.numOfLinks(deviceAddress1))
        Assertions.assertEquals(1, linkManager.numOfLinks(deviceAddress2))
        Assertions.assertEquals(1, linkManager.numOfLinks(deviceAddress3))
    }
}