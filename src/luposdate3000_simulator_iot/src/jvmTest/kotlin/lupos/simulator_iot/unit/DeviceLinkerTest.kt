/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot.unit
import lupos.parser.JsonParser
import lupos.parser.JsonParserObject
import lupos.simulator_iot.config.LinkType
import lupos.simulator_iot.models.Device
import lupos.simulator_iot.models.geo.GeoLocation
import lupos.simulator_iot.models.net.DeviceLinker
import lupos.simulator_iot.models.net.Link
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DeviceLinkerTest {

    @Test
    fun linkByLink() {
        val deviceLinker = DeviceLinker()
        val link = Link(123, 5, 9)
        val one = Stubs.createEmptyDevice(1)
        val two = Stubs.createEmptyDevice(2)
        deviceLinker.link(one, two, link)
        assertTrue(one.linkManager.hasLink(two))
        assertTrue(two.linkManager.hasLink(one))
        assertEquals(one.linkManager.getLink(two), two.linkManager.getLink(one))
        assertEquals(1, one.linkManager.links.size)
        assertEquals(1, two.linkManager.links.size)
    }

    @Test
    fun linkByDataRate() {
        val deviceLinker = DeviceLinker()
        val one = Stubs.createEmptyDevice(1)
        val two = Stubs.createEmptyDevice(2)
        deviceLinker.link(one, two, 231)
        assertEquals(one.linkManager.getLink(two), two.linkManager.getLink(one))
    }

    @Test
    fun isReachAble() {
        val one: Device = Stubs.createEmptyDevice(1)
        val two: Device = Stubs.createEmptyDevice(2)
        val distance = 51
        two.location = GeoLocation.createNorthernLocation(one.location, distance)
        val linkType = LinkType(JsonParser().stringToJson("{\"rangeInMeters\" : 52}")as JsonParserObject)
        val actual = DeviceLinker().isReachable(linkType, one, two)
        assertTrue(actual)
    }

    @Test
    fun isNotReachAble() {
        val one: Device = Stubs.createEmptyDevice(1)
        val two: Device = Stubs.createEmptyDevice(2)
        val distance = 51
        two.location = GeoLocation.createNorthernLocation(one.location, distance)
        val linkType = LinkType(JsonParser().stringToJson("{\"rangeInMeters\" : 50}")as JsonParserObject)
        val actual = DeviceLinker().isReachable(linkType, one, two)
        assertFalse(actual)
    }

    @Test
    fun cannotLinkWithItself() {
        val deviceLinker = DeviceLinker()
        val device: Device = Stubs.createEmptyDevice(0, intArrayOf(0))
        deviceLinker.linkIfPossible(device, device)
        assertFalse(device.linkManager.hasLink(device))
    }

    @Test
    fun sortLinkTypesByDataRate() {
        val deviceLinker = DeviceLinker()
        val linkTypeW = LinkType(JsonParser().stringToJson(" {\"name\": \"W\", \"rangeInMeters\":51,\"dataRateInKbps\": 13}")as JsonParserObject)
        val linkTypeX = LinkType(JsonParser().stringToJson(" {\"name\": \"X\", \"rangeInMeters\":50,\"dataRateInKbps\": 20}")as JsonParserObject)
        val linkTypeY = LinkType(JsonParser().stringToJson(" {\"name\": \"Y\", \"rangeInMeters\":50,\"dataRateInKbps\": 1}")as JsonParserObject)
        val linkTypeZ = LinkType(JsonParser().stringToJson(" {\"name\": \"Z\", \"rangeInMeters\":48,\"dataRateInKbps\": 40}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkTypeW, linkTypeX, linkTypeY, linkTypeZ)
        assertEquals(linkTypeZ, deviceLinker.sortedLinkTypes[0])
        assertEquals(linkTypeX, deviceLinker.sortedLinkTypes[1])
        assertEquals(linkTypeW, deviceLinker.sortedLinkTypes[2])
        assertEquals(linkTypeY, deviceLinker.sortedLinkTypes[3])
    }

    @Test
    fun getSortedLinkTypeIndices() {
        val deviceLinker = DeviceLinker()
        val linkTypeW = LinkType(JsonParser().stringToJson(" {\"name\": \"W\", \"rangeInMeters\":51,\"dataRateInKbps\": 13}")as JsonParserObject)
        val linkTypeX = LinkType(JsonParser().stringToJson(" {\"name\": \"X\", \"rangeInMeters\":50,\"dataRateInKbps\": 20}")as JsonParserObject)
        val linkTypeY = LinkType(JsonParser().stringToJson(" {\"name\": \"Y\", \"rangeInMeters\":50,\"dataRateInKbps\": 1}")as JsonParserObject)
        val linkTypeZ = LinkType(JsonParser().stringToJson(" {\"name\": \"Z\", \"rangeInMeters\":48,\"dataRateInKbps\": 40}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkTypeW, linkTypeX, linkTypeY, linkTypeZ)
        val actual1 = deviceLinker.getSortedLinkTypeIndices(listOf("W"))
        val actual2 = deviceLinker.getSortedLinkTypeIndices(listOf("W", "Z"))
        val actual3 = deviceLinker.getSortedLinkTypeIndices(listOf("Z", "Y", "X"))

        assertContentEquals(actual1, intArrayOf(2))
        assertContentEquals(actual2, intArrayOf(0, 2))
        assertContentEquals(actual3, intArrayOf(0, 1, 3))
    }

    @Test
    fun deviceWithoutLinkTypeCanNotLink() {
        val deviceLinker = DeviceLinker()
        val linkType = LinkType(JsonParser().stringToJson(" {\"name\": \"X\"}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkType)
        val one: Device = Stubs.createEmptyDevice(1)
        val two: Device = Stubs.createEmptyDevice(2, intArrayOf(0))

        assertEquals(-1, deviceLinker.getBestLinkTypeIndex(one, two))
        assertNull(deviceLinker.getBestLink(one, two))
        deviceLinker.linkIfPossible(one, two)
        assertFalse(one.linkManager.hasLink(two))
    }

    @Test
    fun tooFarAwayToLink() {
        val deviceLinker = DeviceLinker()
        val linkTypeX = LinkType(JsonParser().stringToJson(" {\"name\": \"X\", \"rangeInMeters\":50,\"dataRateInKbps\": 7}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkTypeX)
        val one: Device = Stubs.createEmptyDevice(1, intArrayOf(0))
        val two: Device = Stubs.createEmptyDevice(2, intArrayOf(0))
        val distance = 51
        two.location = GeoLocation.createNorthernLocation(one.location, distance)

        assertEquals(-1, deviceLinker.getBestLinkTypeIndex(one, two))
        assertNull(deviceLinker.getBestLink(one, two))
        deviceLinker.linkIfPossible(one, two)
        assertFalse(one.linkManager.hasLink(two))
    }

    @Test
    fun `two devices link with most suitable linkType`() {
        val deviceLinker = DeviceLinker()
        val linkTypeX = LinkType(JsonParser().stringToJson(" {\"name\": \"X\", \"rangeInMeters\":50,\"dataRateInKbps\": 7}")as JsonParserObject)
        val linkTypeY = LinkType(JsonParser().stringToJson(" {\"name\": \"Y\", \"rangeInMeters\":50,\"dataRateInKbps\": 8}")as JsonParserObject)
        val linkTypeZ = LinkType(JsonParser().stringToJson(" {\"name\": \"Z\", \"rangeInMeters\":48,\"dataRateInKbps\": 9}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkTypeX, linkTypeY, linkTypeZ)
        val one: Device = Stubs.createEmptyDevice(1, intArrayOf(0, 1, 2))
        val two: Device = Stubs.createEmptyDevice(2, intArrayOf(0, 1, 2))
        two.location = GeoLocation.createNorthernLocation(one.location, 49)
        deviceLinker.linkIfPossible(one, two)

        assertEquals(8, one.linkManager.getLink(two)!!.dataRateInKbps)
    }

    @Test
    fun `two devices link with the only possible linkType`() {
        val deviceLinker = DeviceLinker()
        val linkTypeX = LinkType(JsonParser().stringToJson(" {\"name\": \"X\", \"rangeInMeters\":50,\"dataRateInKbps\": 20}")as JsonParserObject)
        val linkTypeY = LinkType(JsonParser().stringToJson(" {\"name\": \"Y\", \"rangeInMeters\":50,\"dataRateInKbps\": 8}")as JsonParserObject)
        val linkTypeZ = LinkType(JsonParser().stringToJson(" {\"name\": \"Z\", \"rangeInMeters\":50,\"dataRateInKbps\": 400}")as JsonParserObject)
        deviceLinker.sortedLinkTypes = arrayOf(linkTypeX, linkTypeY, linkTypeZ)

        val oneSupportedLTypes = deviceLinker.getSortedLinkTypeIndices(listOf("X", "Y"))
        val one: Device = Stubs.createEmptyDevice(1, oneSupportedLTypes)
        val twoSupportedLTypes = deviceLinker.getSortedLinkTypeIndices(listOf("Y"))
        val two: Device = Stubs.createEmptyDevice(2, twoSupportedLTypes)
        two.location = GeoLocation.createNorthernLocation(one.location, 49)
        deviceLinker.linkIfPossible(one, two)

        assertTrue(one.linkManager.hasLink(two))
        val actualLink1 = one.linkManager.getLink(two)!!
        val actualLink2 = two.linkManager.getLink(one)!!
        assertEquals(actualLink1, actualLink2)
        assertEquals(8, actualLink1.dataRateInKbps)
    }
}
