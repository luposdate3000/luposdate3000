
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val database: Database?,
    var sensor: Sensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    var dodagRoot = false
    var rank = 0

    private var childNodes: MutableList<Int> = ArrayList()
    private var parentNodes: MutableList<Int> = ArrayList()
    private var preferredParent: Int = 0

    private var availableLinks: MutableMap<Int, Link> = HashMap()


    fun getNetworkDelay(destination: Device): Long {
        return if (destination == this) {
            0
        } else {
            1
        }
    }


    override fun startUpEntity() {
        sensor?.observe()

        if(dodagRoot) {
            childNodes.addAll(availableLinks.keys)
            sendDIOPackage()


        }
    }


    override fun processEvent(event: Event) {
        //wenn DIO, dann berechne Rang
        //wähle Eltern, sende DAO ob Eltern Eltern bleiben oder nicht.
        val pck = event.data as NetworkPackage

    }

    override fun shutDownEntity() {
    }

    private fun sendDIOPackage() {
        for (child in childNodes) {
            val dio = NetworkPackage.DIO(rank)
            sendPackage(address, child, dio)
        }
    }

    fun sendSelfPackage(data: Any) {
        val pck = NetworkPackage(address, address, data)
        sendEvent(this, 0, pck)
    }

    fun sendPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = this //TODO use Routing table
        val delay = getNetworkDelay(nextHop)
        sendEvent(nextHop, delay, pck)
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
        if(link != null) {
            availableLinks[otherDevice.address] = link
            otherDevice.availableLinks[address] = link
        }

    }

    fun getAvailableLink(otherDevice: Device): Link?
        = availableLinks[otherDevice.address]

    fun hasAvailAbleLink(otherDevice: Device)
        = null != getAvailableLink(otherDevice)

    fun numOfAvailAbleLinks()
        = availableLinks.size

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

    interface Sensor {
        var dataSinkAddress: Int
        fun observe()
        fun onObservationEnd()
    }

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