package lupos.iot_sim
import lupos.iot_sim.config.LinkType

public class LinkManager(public val device: Device) {

    private var links: MutableMap<Int, Link> = HashMap(5)


    public fun getDistanceInMeters(otherDevice: Device): Int
            = device.location.getDistanceInMeters(otherDevice.location)

    private fun getBestLinkTypeIndex(otherDevice: Device) : Int {
        val size = device.supportedLinkTypes.size.coerceAtMost(otherDevice.supportedLinkTypes.size)
        for (i in 0 until size)
            if(device.supportedLinkTypes[i] == otherDevice.supportedLinkTypes[i])
                if(isReachableByLinkType(device.supportedLinkTypes[i], otherDevice))
                    return device.supportedLinkTypes[i]
        return -1
    }


    private fun isReachableByLinkType(index: Int, otherDevice: Device): Boolean {
        val distance = getDistanceInMeters(otherDevice)
        val linkType = getLinkTypeByIndex(index)
        return distance <= linkType.rangeInMeters
    }

    public fun getBestLink(otherDevice: Device): Link? {
        val linkIndex = getBestLinkTypeIndex(otherDevice)
        if(linkIndex == -1)
            return null

        val distance = getDistanceInMeters(otherDevice)
        val dataRate = getLinkTypeByIndex(linkIndex).dataRateInKbps
        return Link( distance, linkIndex, dataRate)
    }

    public fun setLink(otherDevice: Device, dataRate: Int) {
        val distance = getDistanceInMeters(otherDevice)
        val link = Link(distance, -1, dataRate)
        setLink(otherDevice, link)
    }

    private fun setLink(otherDevice: Device, link: Link) {
        links[otherDevice.address] = link
        otherDevice.linkManager.links[device.address] = link
        linkCounter++
    }

    public fun setLinkIfPossible(otherDevice: Device) {
        if (otherDevice == device)
            return

        if(hasLink(otherDevice))
            return

        val link = getBestLink(otherDevice) ?: return
        setLink(otherDevice, link)
    }

    public fun getLink(otherDeviceAddress: Int): Link?
        = links[otherDeviceAddress]

    public fun getLink(otherDevice: Device): Link?
            = getLink(otherDevice.address)

    public fun hasLink(otherDevice: Device): Boolean
            = null != getLink(otherDevice)

    public fun getNumberOfLinks():Int
            = links.size

    public fun getNeighbours(): MutableSet<Int>
        = links.keys

    public companion object {

        public var linkCounter: Int = 0
            private set

        public fun resetCounter() {
            linkCounter = 0
        }

        public var sortedLinkTypes: Array<LinkType> = emptyArray()
            set(value) {
                field = value
                field.sortByDescending { it.dataRateInKbps }
            }

        public fun getLinkTypeByIndex(index: Int): LinkType
                = sortedLinkTypes[index]

        private fun getIndexByLinkType(linkType: LinkType)
                = sortedLinkTypes.indexOfFirst { linkType.name == it.name}

        public fun getSortedLinkTypeIndices(list: List<LinkType>): IntArray {
            val result = IntArray(list.size)
            for((index, linkType) in list.withIndex()) {
                result[index] = getIndexByLinkType(linkType)
            }
            return result.sortedArray()
        }
    }
}
