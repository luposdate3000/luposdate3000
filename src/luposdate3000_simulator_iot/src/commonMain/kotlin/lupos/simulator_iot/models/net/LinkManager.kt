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
