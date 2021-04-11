
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    private var availableLinks: MutableMap<Int, Link> = HashMap()


    companion object {
        var sortedLinkTypes: MutableList<LinkType> = ArrayList()
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


    fun getNetworkDelay(destination: Device): Long {
        return if (destination == this) {
            0
        } else {
            1
        }
    }


    override fun startUpEntity() {

    }

    override fun processEvent(event: Event) {
        val pck = event.data as NetworkPackage
        if(this == pck.receiver) {
            // do something
            return
        }
        else {
            val delay = getNetworkDelay(pck.receiver)
            //sendEvent(destination, delay, pck )
        }

    }

    override fun shutDownEntity() {
    }






    fun getDistanceInMeters(otherDevice: Device)
        = location.getDistanceInMeters(otherDevice.location)


    private fun getBestLinkTypeIndex(otherDevice: Device) : Int {
        val size = supportedLinkTypes.size.coerceAtMost(otherDevice.supportedLinkTypes.size)
        for (i in 0 until size) {
            if(supportedLinkTypes[i] == otherDevice.supportedLinkTypes[i]) {
                if(isReachableByLinkType(supportedLinkTypes[i], otherDevice)) {
                    return supportedLinkTypes[i]
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
        if (otherDevice == this)
            return null
        val linkIndex = getBestLinkTypeIndex(otherDevice)
        if(linkIndex == -1)
            return null

        val distance = getDistanceInMeters(otherDevice)
        return Link( distance, linkIndex)
    }

    fun addAvailableLink(otherDevice: Device) {
        val link = getBestLink(otherDevice)
        if(link != null)
            availableLinks[otherDevice.address] = link
    }

    fun getAvailableLink(otherDevice: Device): Link?
        = availableLinks[otherDevice.address]

    fun hasAvailAbleLink(otherDevice: Device)
        = null != getAvailableLink(otherDevice)

    fun numOfAvailAbleLinks()
        = availableLinks.size

    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true

        if (other !is Device)
            return false

        return address == other.address
    }

    override fun hashCode(): Int {
        return address
    }

}