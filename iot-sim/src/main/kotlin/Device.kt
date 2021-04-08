
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val name: String,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val protocols: Set<LinkType>
    ) : Entity()
{
    private var availableLinks: ArrayList<Link> = ArrayList()

    class RouteRequest

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


    private fun selectBestFitProtocol(protocols: Set<LinkType>, distance: Double): LinkType? {
        val comparator = compareByDescending<LinkType> { it.dataRateInKbps }
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
//            val linkType = getLink(device)
//            if(linkType!= null) {
//                val distance = getDistanceInMeters(device)
//                if (bestFit == null) {
//                    bestFit = Link(name, device.name, distance, linkType)
//                }
//                else {
//                    if(distance < bestFit.distanceInMeters) {
//                        bestFit = Link(name, device.name, distance, linkType)
//                    }
//                }
//            }
//        }
//        return bestFit
//    }

    fun createLinkIfPossible(otherDevice: Device): Link? {
        if(otherDevice == this)
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