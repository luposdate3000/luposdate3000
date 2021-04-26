class LinkManager(val numberOfDevices: Int) {
    val links: Array<Array<Link?>> = Array(numberOfDevices) { arrayOfNulls(numberOfDevices) }

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

    private fun getBestLinkTypeIndex(deviceOne: Device, deviceTwo: Device) : Int {
        val deviceOneLinkTypes = deviceOne.supportedLinkTypes
        val deviceTwoLinkTypes = deviceTwo.supportedLinkTypes
        val smallestArraySize = deviceOneLinkTypes.size.coerceAtMost(deviceTwoLinkTypes.size)
        for (i in 0 until smallestArraySize) {
            if(deviceOneLinkTypes[i] == deviceTwoLinkTypes[i]) {
                if(isReachableByLinkType(deviceOneLinkTypes[i], deviceOne, deviceTwo)) {
                    return deviceOneLinkTypes[i]
                }
            }
        }
        return -1
    }

    private fun isReachableByLinkType(index: Int, deviceOne: Device, deviceTwo: Device): Boolean {
        val distance = deviceOne.getDistanceInMeters(deviceTwo)
        val linkType = getLinkTypeByIndex(index)
        return distance <= linkType.rangeInMeters
    }

    fun getBestLink(deviceOne: Device, deviceTwo: Device): Link? {
        val linkIndex = getBestLinkTypeIndex(deviceOne, deviceTwo)
        if(linkIndex == -1)
            return null

        val distance = deviceOne.getDistanceInMeters(deviceTwo)
        val dataRate = getLinkTypeByIndex(linkIndex).dataRateInKbps
        return Link( distance, linkIndex, dataRate)
    }

    fun setLink(deviceOne: Device, deviceTwo: Device, dataRate: Int) {
        val distance = deviceOne.getDistanceInMeters(deviceTwo)
        val link = Link(distance, -1, dataRate)
        setLink(deviceOne.address, deviceTwo.address, link)
    }

    fun setLink(deviceAddressOne: Int, deviceAddressTwo: Int, link: Link) {
        links[deviceAddressOne][deviceAddressTwo] = link
        links[deviceAddressTwo][deviceAddressOne] = link
    }

    fun setLinkIfPossible(deviceAddressOne: Int, deviceAddressTwo: Int) {
        if (deviceAddressOne == deviceAddressTwo)
            return

        if(hasLink(deviceAddressOne, deviceAddressTwo))
            return

        val deviceOne = Configuration.devices[deviceAddressOne]
        val deviceTwo = Configuration.devices[deviceAddressTwo]
        val link = getBestLink(deviceOne, deviceTwo) ?: return
        setLink(deviceAddressOne, deviceAddressTwo, link)
    }

    fun getLink(deviceAddressOne: Int, deviceAddressTwo: Int): Link?
            = links[deviceAddressOne][deviceAddressTwo]

    fun hasLink(deviceAddressOne: Int, deviceAddressTwo: Int)
            = null != getLink(deviceAddressOne, deviceAddressTwo)

    fun numOfLinks(deviceAddress: Int): Int {
        var num = 0
        for (link in links[deviceAddress]) {
            if (link != null)
                num++
        }
        return num
    }


}