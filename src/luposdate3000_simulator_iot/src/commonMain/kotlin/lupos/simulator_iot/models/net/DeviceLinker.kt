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
