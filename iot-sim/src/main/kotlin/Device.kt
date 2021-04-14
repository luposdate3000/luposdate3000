
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
    var dodagRoot = false

    var rank = notInitialized
        private set
    private var routingTable = RoutingTable(address)
    private var childNodes: MutableSet<Int> = mutableSetOf()
    var preferredParent = Parent()
        private set

    private var availableLinks: MutableMap<Int, Link> = HashMap()


    inner class Parent(var address: Int = notInitialized, var rank: Int = notInitialized)

    private fun getNetworkDelay(destinationAddress: Int): Long {
        return if (destinationAddress == address) {
            0
        } else {
            1 //berechne
        }
    }



    override fun startUpEntity() {

        sensor?.observe()
        if(dodagRoot) {
            rank = 0
            broadcastDIO()
        }
    }

    private fun initializeDODAGRoot() {

        val directAvailableAddresses = availableLinks.keys
        for (address in directAvailableAddresses)
            routingTable.update(address, address)
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

    private fun sendDAO(destinationAddress: Int, isChild: Boolean) {
        val dao = NetworkPackage.DAO(isChild)
        sendDODAGBuildingPackage(destinationAddress, dao)
    }

    private fun sendDODAGBuildingPackage(dest: Int, data: Any) {
        val pck = NetworkPackage(address, dest, data)
        val delay = getNetworkDelay(dest)
        sendEvent(Configuration.devices[dest], delay, pck)
    }

    fun sendNetworkPackage(src: Int, dest: Int, data: Any) {
        val pck = NetworkPackage(src, dest, data)
        val nextHop = routingTable.lookup(pck.destinationAddress)
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
        if (dao.isChild)
            childNodes.add(pck.sourceAddress)
        else
            childNodes.remove(pck.sourceAddress)

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