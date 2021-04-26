
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val database: Database?,
    var sensor: Sensor?,
    val supportedLinkTypes: IntArray
    ) : Entity()
{
    private val notInitialized = Int.MAX_VALUE
    var root = false

    var rank = notInitialized
        private set

    var routingTable = RoutingTable(address)
        private set

    var preferredParent = Parent()
        private set

    private var availableLinks: MutableMap<Int, Link> = HashMap()


    inner class Parent(var address: Int = notInitialized, var rank: Int = notInitialized)

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1
        }
    }

    override fun startUpEntity() {

        sensor?.observe()
        if(root) {
            rank = 0
            broadcastDIO()
        }
    }

    override fun processEvent(event: Event) {
        val pck = event.data as NetworkPackage
        when(pck.data) {
            is NetworkPackage.ObservationEnd -> sensor!!.onObservationEnd()
            is NetworkPackage.ParkingObservation -> processParkingObservation(pck)
            is NetworkPackage.DIO -> processDIO(pck)
            is NetworkPackage.DAO -> processDAO(pck)
        }
    }

    override fun shutDownEntity() {
    }

    private fun broadcastDIO() {
        for (potentialChild in availableLinks.keys)
            sendDIO(potentialChild)
    }

    private fun sendDIO(destinationAddress: Int) {
        val dio = NetworkPackage.DIO(rank)
        sendDODAGBuildingPackage(destinationAddress, dio)
    }

    private fun sendDAO(destinationAddress: Int, isPath: Boolean) {
        val destinations = if(isPath) routingTable.getDestinations() else IntArray(0)
        val dao = NetworkPackage.DAO(isPath, destinations)
        sendDODAGBuildingPackage(destinationAddress, dao)
    }

    private fun sendDODAGBuildingPackage(dest: Int, data: Any) {
        val pck = NetworkPackage(address, dest, data)
        val delay = getNetworkDelay(dest)
        sendEvent(Configuration.devices[dest], delay, pck)
    }

    fun sendNetworkPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = routingTable.getNextHop(pck.destinationAddress)
        val delay = getNetworkDelay(nextHop)
        sendEvent(Configuration.devices[nextHop], delay, pck)
    }

    fun sendSelfPackage(delay: Long, data: Any) {
        val pck = NetworkPackage(address, address, data)
        sendEvent(this, delay, pck)
    }


    private fun processParkingObservation(pck: NetworkPackage) {
        if (pck.destinationAddress == address) {
            //store
        }
        else {
            sendNetworkPackage(pck.sourceAddress, pck.destinationAddress, pck.data)
        }
    }

    private fun processDIO(pck: NetworkPackage) {
        val dio = pck.data as NetworkPackage.DIO
        if (dio.rank >= rank)
            return

        if(isBetterParent(dio.rank))
            updateParent(Parent(pck.sourceAddress, dio.rank))

        objectiveFunction(dio)
        broadcastDIO()
    }

    private fun isBetterParent(rank: Int) = !hasParent() || rank < preferredParent.rank

    private fun updateParent(newParent: Parent) {

        if (hasParent())
            sendDAO(preferredParent.address, false)

        preferredParent = newParent
        sendDAO(preferredParent.address, true)
        routingTable.defaultAddress = preferredParent.address
    }


    private fun processDAO(pck: NetworkPackage) {
        val dao = pck.data as NetworkPackage.DAO
        if (dao.isPath)
            routingTable.setDestinationsByHop(pck.sourceAddress, dao.destinations)
        else
            routingTable.removeDestinationsByHop(pck.sourceAddress)

        if(hasParent())
            sendDAO(preferredParent.address, dao.isPath)
    }

    private fun objectiveFunction(dio: NetworkPackage.DIO) {
        rank = dio.rank + 1
    }


    fun hasParent()
        = preferredParent.address != notInitialized


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
        val linkIndex = getBestLinkTypeIndex(otherDevice)
        if(linkIndex == -1)
            return null

        val distance = getDistanceInMeters(otherDevice)
        val dataRate = getLinkTypeByIndex(linkIndex).dataRateInKbps
        return Link( distance, linkIndex, dataRate)
    }

    fun addLink(otherDevice: Device, dataRate: Int) {
        val distance = getDistanceInMeters(otherDevice)
        val link = Link(distance, -1, dataRate)
        addLink(otherDevice, link)
    }

    private fun addLink(otherDevice: Device, link: Link) {
        availableLinks[otherDevice.address] = link
        otherDevice.availableLinks[address] = link
    }

    fun addLinkIfPossible(otherDevice: Device) {
        if (otherDevice == this)
            return

        if(hasAvailAbleLink(otherDevice))
            return

        val link = getBestLink(otherDevice) ?: return
        addLink(otherDevice, link)
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