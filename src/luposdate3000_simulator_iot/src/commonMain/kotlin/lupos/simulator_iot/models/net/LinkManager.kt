package lupos.simulator_iot.models.net

import lupos.simulator_iot.models.Device
import lupos.simulator_iot.utils.TimeUtils

internal class LinkManager(
    internal val device: Device,
    internal val supportedLinkTypes: IntArray
) {

    internal var links: MutableMap<Int, Link> = mutableMapOf()

    internal fun getTransmissionDelay(destinationAddress: Int, numberOfBytesToSend: Int): Long {
        val link = links[destinationAddress]
        requireNotNull(link) { "The device $destinationAddress is not reachable!" }
        val kiloBits = bytesToKBits(numberOfBytesToSend)
        val seconds = kiloBits / link.dataRateInKbps.toDouble()
        return TimeUtils.toNanoSec(seconds)
    }

    private fun bytesToKBits(b: Int): Double =
        // * 8 / 1000
        b.toDouble() / 125

    internal fun getLink(otherDevice: Device): Link? =
        links[otherDevice.address]

    internal fun hasLink(otherDevice: Device): Boolean =
        null != getLink(otherDevice)

    internal fun getNeighbours(): MutableSet<Int> =
        links.keys
}
