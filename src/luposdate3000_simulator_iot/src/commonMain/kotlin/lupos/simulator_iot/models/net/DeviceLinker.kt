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

import lupos.simulator_iot.config.LinkType
import lupos.simulator_iot.models.Device

internal class DeviceLinker {
    internal var numberOfLinks = 0
    private var sortedLinkTypes: Array<LinkType> = emptyArray()

    internal fun getLinkByName(name: String): LinkType = sortedLinkTypes.filter { it.name == name }.first()
    internal fun getSortedLinkTypeIndices(linkTypeNames: List<String>): IntArray = linkTypeNames.map { getLinkByName(it) }.map { sortedLinkTypes.indexOf(it) }.sorted().toIntArray()

    internal fun setLinkTypes(types: Array<LinkType>) {
        sortedLinkTypes = types.sortedByDescending { it.dataRateInKbps }.toTypedArray()
    }

    internal fun createAvailableLinks(devices: MutableList<Device>) {
        for (one in devices) {
            for (two in devices) {
                if (!one.isStarNetworkChild && !two.isStarNetworkChild) {
                    linkIfPossible(one, two)
                }
            }
        }
    }

    internal fun linkIfPossible(one: Device, two: Device) {
        if (one != two && !one.linkManager.hasLink(two)) {
            val distance = getDistanceInMeters(one, two)
            val oneIndices = one.linkManager.supportedLinkTypes
            val twoIndices = two.linkManager.supportedLinkTypes
            loop@ for (i in oneIndices) {
                for (i2 in twoIndices) {
                    if (i == i2) {
                        if (distance <= sortedLinkTypes[i].rangeInMeters) {
                            link(one, two, Link(distance, i, sortedLinkTypes[i].dataRateInKbps))
                            return
                        }
                    }
                }
            }
        }
    }

    internal fun getDistanceInMeters(one: Device, two: Device): Double = one.location.getDistanceInMeters(two.location)

    internal fun link(one: Device, two: Device, dataRate: Int) {
        val distance = getDistanceInMeters(one, two)
        link(one, two, Link(distance, -1, dataRate))
    }

    internal fun link(one: Device, two: Device, link: Link) {
        one.linkManager.links[two.address] = link
        two.linkManager.links[one.address] = link
        numberOfLinks++
    }
}
