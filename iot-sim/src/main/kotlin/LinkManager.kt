class LinkManager(val device: Device) {

    private var links: MutableMap<Int, Link> = HashMap(5)

    fun getDistanceInMeters(otherDevice: Device)
            = device.location.getDistanceInMeters(otherDevice.location)


    private fun getBestLinkTypeIndex(otherDevice: Device) : Int {
        val size = device.supportedLinkTypes.size.coerceAtMost(otherDevice.supportedLinkTypes.size)
        for (i in 0 until size) {
            if(device.supportedLinkTypes[i] == otherDevice.supportedLinkTypes[i]) {
                if(isReachableByLinkType(device.supportedLinkTypes[i], otherDevice)) {
                    return device.supportedLinkTypes[i]
                }
            }
        }
        return -1
    }


    private fun isReachableByLinkType(index: Int, otherDevice: Device): Boolean {
        val distance = getDistanceInMeters(otherDevice)
        val linkType = getLinkTypeByIndex(index)
        return distance <= linkType.rangeInMeters
    }

    fun getBestLink(otherDevice: Device): Link? {
        val linkIndex = getBestLinkTypeIndex(otherDevice)
        if(linkIndex == -1)
            return null

        val distance = getDistanceInMeters(otherDevice)
        val dataRate = getLinkTypeByIndex(linkIndex).dataRateInKbps
        return Link( distance, linkIndex, dataRate)
    }

    fun setLink(otherDevice: Device, dataRate: Int) {
        val distance = getDistanceInMeters(otherDevice)
        val link = Link(distance, -1, dataRate)
        setLink(otherDevice, link)
    }

    private fun setLink(otherDevice: Device, link: Link) {
        links[otherDevice.address] = link
        otherDevice.linkManager.links[device.address] = link
    }

    fun setLinkIfPossible(otherDevice: Device) {
        if (otherDevice == device)
            return

        if(hasLink(otherDevice))
            return

        val link = getBestLink(otherDevice) ?: return
        setLink(otherDevice, link)
    }

    fun getLink(otherDevice: Device): Link?
            = links[otherDevice.address]

    fun hasLink(otherDevice: Device)
            = null != getLink(otherDevice)

    fun getNumberOfLinks()
            = links.size

    fun getNeighbours()
        = links.keys

    companion object {
        var sortedLinkTypes: Array<LinkType> = emptyArray()
            set(value) {
                field = value
                field.sortByDescending { it.dataRateInKbps }
            }

        fun getLinkTypeByIndex(index: Int)
                = sortedLinkTypes[index]

        private fun getIndexByLinkType(linkType: LinkType)
                = sortedLinkTypes.indexOfFirst { linkType.name == it.name}

        fun getSortedLinkTypeIndices(list: List<LinkType>): IntArray {
            val result = IntArray(list.size)
            for((index, linkType) in list.withIndex()) {
                result[index] = getIndexByLinkType(linkType)
            }
            return result.sortedArray()
        }
    }
}