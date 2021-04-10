
class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val address: Int,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val protocols: Set<LinkType>
    ) : Entity()
{
    private var availableLinks: MutableMap<Int, Link> = HashMap()

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


    val comparator = compareByDescending<LinkType> { it.dataRateInKbps }

    private fun selectBestLinkType(linkTypes: Set<LinkType>, distance: Int): LinkType? {
        val sortedLinkTypes = linkTypes.asSequence().sortedWith(comparator)
        for (linkType in sortedLinkTypes) {
            if(distance <= linkType.rangeInMeters)
                return linkType
        }
        return null
    }

    fun getBestLinkIfPossible(otherDevice: Device): Link? {
        if (otherDevice == this)
            return null
        val commonLinkTypes = protocols.intersect(otherDevice.protocols)
        val distance = getDistanceInMeters(otherDevice)
        val linkType = selectBestLinkType(commonLinkTypes, distance) ?: return null

        val newLink = Link(otherDevice.address, distance, linkType)
        if(newLink == availableLinks[otherDevice.address])
            return null
        return newLink
    }

    fun addAvailableLink(otherDevice: Device) {
        val newLink = getBestLinkIfPossible(otherDevice) ?: return
        availableLinks[otherDevice.address] = newLink


/*        Probier mal mit String zuweisung,
        LinkTyp kann wieder verwendet werden,
        bei den device adressen kann ich vielleicht auch ein int verwenden.*/
        //nimm string für json, aber als id einfach int hochzählen und in arraylist speiucher

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

}