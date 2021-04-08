
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val name: String,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val protocols: Set<ProtocolType>
    ) : Entity()
{
    private var availableLinks: ArrayList<Link> = ArrayList()
    var networkPrefix = ""
    var isWSNGateway = false
    var rank = 0

    class RouteRequest

    fun getNetworkDelay(destination: Device): Long {
        return if (destination == this) {
            0
        } else {
            1
        }
    }

    override fun startUpEntity() {
        if (isWSNGateway) {
            //TODO GetAvailableLinks
            val addresses = Configuration.randNetAddresses[networkPrefix]
            if (addresses != null) {
                for (addr in addresses) {
                    val destEntity = Configuration.devices[addr]!!
/*                    val delay = getNetworkDelay(link)
                    sendEvent(destEntity)*/
                }

            }
        }
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


    private fun selectBestFitProtocol(protocols: Set<ProtocolType>, distance: Double): ProtocolType? {
        val comparator = compareByDescending<ProtocolType> { it.dataRateInKbps }
        val sorted = protocols.asSequence().sortedWith(comparator)
        for (protocol in sorted) {
            if(distance <= protocol.rangeInMeters)
                return protocol
        }
        return null
    }

//    fun selectBestLink(otherDevices: List<Device>): Link? {
//        var bestFit: Link? = null
//        for (device in otherDevices) {
//            val protocolType = getLink(device)
//            if(protocolType!= null) {
//                val distance = getDistanceInMeters(device)
//                if (bestFit == null) {
//                    bestFit = Link(name, device.name, distance, protocolType)
//                }
//                else {
//                    if(distance < bestFit.distanceInMeters) {
//                        bestFit = Link(name, device.name, distance, protocolType)
//                    }
//                }
//            }
//        }
//        return bestFit
//    }

    fun createLinkIfPossible(otherDevice: Device): Link? {

        if(otherDevice == this)
            return null

        if (networkPrefix != otherDevice.networkPrefix)
            return null

        val commonProtocols = protocols.intersect(otherDevice.protocols)
        val distance = getDistanceInMeters(otherDevice)
        val protocol = selectBestFitProtocol(commonProtocols, distance)
        if(protocol != null)
            return Link(name, otherDevice.name, distance, protocol)
        return null
    }

    fun addAvailableLink(otherDevice: Device) {
        val link = createLinkIfPossible(otherDevice)
        if(link != null)
            availableLinks.add(link)
    }


    fun getAvailableLink(otherDevice: Device): Link? {
        for (link in availableLinks)
            if(link.destAddress == otherDevice.name)
                return link
        return null
    }

    fun hasAvailAbleLink(otherDevice: Device)
        = null != getAvailableLink(otherDevice)

    fun numOfAvailAbleLinks()
        = availableLinks.size


}