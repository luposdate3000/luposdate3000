import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool
import com.javadocmd.simplelatlng.util.LengthUnit


class Device(
    val powerSupply: PowerSupply,
    var location: GeoLocation,
    val name: String,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val protocols: Set<ProtocolType>
    ) : Entity()
{
    var networkPrefix = ""
    var isWSNGateway = false
    var rank = 0

    fun getNetworkDelay(destination: Device): Long {
        return if (destination == this) {
            0
        } else {
            1
        }
    }


    override fun startUpEntity() {
        if (isWSNGateway) {
            val addresses = Configuration.randNetAddresses[networkPrefix]

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

    private val comparator = compareByDescending<ProtocolType> { it.dataRateInKbps }
    private fun selectBestFitProtocol(protocols: Set<ProtocolType>, distance: Double): ProtocolType? {

        val sorted = protocols.asSequence().sortedWith(comparator)
        for (protocol in sorted) {
            if(distance <= protocol.rangeInMeters)
                return protocol
        }
        return null
    }

    fun selectBestLink(otherDevices: List<Device>): Link? {
        var bestFit: Link? = null
        for (device in otherDevices) {
            val protocolType = isLinkable(device)
            if(protocolType!= null) {
                val distance = getDistanceInMeters(device)
                if (bestFit == null) {
                    bestFit = Link(name, device.name, distance, protocolType)
                }
                else {
                    if(distance < bestFit.distanceInMeters) {
                        bestFit = Link(name, device.name, distance, protocolType)
                    }
                }
            }
        }
        return bestFit
    }

    fun isLinkable(otherDevice: Device): ProtocolType? {
        if (networkPrefix != otherDevice.networkPrefix) {
            return null
        }
        val commonProtocols = protocols.intersect(otherDevice.protocols)
        val distance = getDistanceInMeters(otherDevice)
        return selectBestFitProtocol(commonProtocols, distance)
    }
}