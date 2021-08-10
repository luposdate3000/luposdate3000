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
        private set

    internal var sortedLinkTypes: Array<LinkType> = emptyArray()
        set(value) {
            field = value
            field.sortByDescending { it.dataRateInKbps }
        }

    internal fun getSortedLinkTypeIndices(linkTypeNames: List<String>): IntArray {
        val result = IntArray(linkTypeNames.size) { -1 }
        for ((index, name) in linkTypeNames.withIndex()) {
            val indexOfFirst = this.sortedLinkTypes.indexOfFirst { it.name == name }
            require(indexOfFirst != -1) { "The LinkType with name '$name' does not exist in the LinkType configuration." }
            result[index] = indexOfFirst
        }
        result.sort()
        return result
    }

    internal fun createAvailableLinks(devices: MutableList<Device>) {
        for (one in devices)
            for (two in devices)
                if (!one.isStarNetworkChild && !two.isStarNetworkChild) {
                    linkIfPossible(one, two)
                }
    }

    internal fun linkIfPossible(one: Device, two: Device) {
        if (one == two) {
            return
        }

        if (one.linkManager.hasLink(two)) {
            return
        }

        if (two.linkManager.hasLink(one)) {
            throw IllegalStateException("$one has a link with $two but that is wrongly not the other way around")
        }

        val link = getBestLink(one, two) ?: return
        link(one, two, link)
    }

    internal fun getBestLink(one: Device, two: Device): Link? {
        val linkIndex = getBestLinkTypeIndex(one, two)
        if (linkIndex == -1) {
            return null
        }

        val distance = getDistanceInMeters(one, two)
        val dataRate = sortedLinkTypes[linkIndex].dataRateInKbps
        return Link(distance, linkIndex, dataRate)
    }

    internal fun getDistanceInMeters(one: Device, two: Device): Int =
        one.location.getDistanceInMeters(two.location)

    internal fun getBestLinkTypeIndex(one: Device, two: Device): Int {
        val oneIndices = one.linkManager.supportedLinkTypes
        val twoIndices = two.linkManager.supportedLinkTypes

        for (i in oneIndices)
            for (i2 in twoIndices)
                if (i == i2) {
                    if (isReachable(sortedLinkTypes[i], one, two)) {
                        return i
                    }
                }

        return -1
    }

    internal fun isReachable(linkType: LinkType, one: Device, two: Device): Boolean {
        val distance = getDistanceInMeters(one, two)
        return distance <= linkType.rangeInMeters
    }

    internal fun link(one: Device, two: Device, dataRate: Int) {
        val distance = getDistanceInMeters(one, two)
        val link = Link(distance, -1, dataRate)
        link(one, two, link)
    }

    internal fun link(one: Device, two: Device, link: Link) {
        require(one != two) { "Self link is not allowed" }
        one.linkManager.links[two.address] = link
        two.linkManager.links[one.address] = link
        numberOfLinks++
    }
}
