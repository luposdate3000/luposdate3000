package lupos.simulator_iot.iot.net

import lupos.simulator_iot.iot.Device
import kotlin.math.roundToLong

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
        val millis = seconds * 1000
        return millis.roundToLong()
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
