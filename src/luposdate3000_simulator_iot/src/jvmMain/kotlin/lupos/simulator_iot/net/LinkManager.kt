package lupos.simulator_iot.net

import lupos.simulator_iot.Device
import lupos.simulator_iot.config.LinkType
import kotlin.math.roundToInt
import kotlin.math.roundToLong

internal class LinkManager(internal val device: Device) {

    private var links: MutableMap<Int, Link> = mutableMapOf()

    internal fun getTransmissionDelay(destinationAddress: Int, numberOfBytesToSend: Int): Long {
        val link = getLink(destinationAddress)
        requireNotNull(link) { "The device $destinationAddress is not reachable!" }
        val kiloBits = bytesToKBits(numberOfBytesToSend)
        val seconds =  kiloBits / link.dataRateInKbps.toDouble()
        val millis = seconds * 1000
        return millis.roundToLong()
    }

    private fun bytesToKBits(b: Int): Double
        // * 8 / 1000
        = b.toDouble() / 125

    internal fun getDistanceInMeters(otherDevice: Device): Int =
        device.location.getDistanceInMeters(otherDevice.location)

    private fun getBestLinkTypeIndex(otherDevice: Device): Int {
        val size = device.supportedLinkTypes.size.coerceAtMost(otherDevice.supportedLinkTypes.size)
        for (i in 0 until size)
            if (device.supportedLinkTypes[i] == otherDevice.supportedLinkTypes[i]) {
                if (isReachableByLinkType(device.supportedLinkTypes[i], otherDevice)) {
                    return device.supportedLinkTypes[i]
                }
            }
        return -1
    }

    private fun isReachableByLinkType(index: Int, otherDevice: Device): Boolean {
        val distance = getDistanceInMeters(otherDevice)
        val linkType = getLinkTypeByIndex(index)
        return distance <= linkType.rangeInMeters
    }

    internal fun getBestLink(otherDevice: Device): Link? {
        val linkIndex = getBestLinkTypeIndex(otherDevice)
        if (linkIndex == -1) {
            return null
        }

        val distance = getDistanceInMeters(otherDevice)
        val dataRate = getLinkTypeByIndex(linkIndex).dataRateInKbps
        return Link(distance, linkIndex, dataRate)
    }

    internal fun setLink(otherDevice: Device, dataRate: Int) {
        val distance = getDistanceInMeters(otherDevice)
        val link = Link(distance, -1, dataRate)
        setLink(otherDevice, link)
    }

    private fun setLink(otherDevice: Device, link: Link) {
        links[otherDevice.address] = link
        otherDevice.linkManager.links[device.address] = link
        linkCounter++
    }

    internal fun setLinkIfPossible(otherDevice: Device) {
        if (otherDevice == device) {
            return
        }

        if (hasLink(otherDevice)) {
            return
        }

        val link = getBestLink(otherDevice) ?: return
        setLink(otherDevice, link)
    }

    internal fun getLink(otherDeviceAddress: Int): Link? =
        links[otherDeviceAddress]

    internal fun getLink(otherDevice: Device): Link? =
        getLink(otherDevice.address)

    internal fun hasLink(otherDevice: Device): Boolean =
        null != getLink(otherDevice)

    internal fun getNumberOfLinks(): Int =
        links.size

    internal fun getNeighbours(): MutableSet<Int> =
        links.keys

    internal companion object {

        internal var linkCounter: Int = 0
            private set

        internal fun resetCounter() {
            linkCounter = 0
        }

        internal var sortedLinkTypes: Array<LinkType> = emptyArray()
            set(value) {
                field = value
                field.sortByDescending { it.dataRateInKbps }
            }

        internal fun getLinkTypeByIndex(index: Int): LinkType =
            sortedLinkTypes[index]

        private fun getIndexByLinkType(linkType: LinkType) =
            sortedLinkTypes.indexOfFirst { linkType.name == it.name }

        internal fun getSortedLinkTypeIndices(list: List<LinkType>): IntArray {
            val result = IntArray(list.size)
            for ((index, linkType) in list.withIndex()) {
                result[index] = getIndexByLinkType(linkType)
            }
            return result.sortedArray()
        }
    }
}
