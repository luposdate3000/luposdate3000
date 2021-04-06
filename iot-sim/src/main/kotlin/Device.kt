import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool
import com.javadocmd.simplelatlng.util.LengthUnit


class Device(
    val powerSupply: PowerSupply,
    var location: LatLng,
    val name: String,
    val application: Entity?,
    var sensor: ParkingSensor?,
    val protocols: Set<ProtocolType>
    )
{
    var networkPrefix = ""
    val networkCard = NetworkCard(this)

    fun createNorthernLocation(startLoc: LatLng, distanceInMeters: Double): LatLng
        = LatLngTool.travel(
        startLoc,
        LatLngTool.Bearing.NORTH,
        distanceInMeters,
        LengthUnit.METER)

    private fun getDistanceInMeters(loc1: LatLng, loc2: LatLng)
        = LatLngTool.distance(loc1, loc2, LengthUnit.METER)

    fun getDistanceInMeters(otherDevice: Device)
        = getDistanceInMeters(location, otherDevice.location)

    private val comparator = compareByDescending<ProtocolType> { it.dataRateInKbps }
    private fun chooseBestFitProtocol(protocols: Set<ProtocolType>, distance: Double): ProtocolType? {

        val sorted = protocols.asSequence().sortedWith(comparator)
        for (protocol in sorted) {
            if(distance <= protocol.rangeInMeters)
                return protocol
        }
        return null
    }

    fun isLinkable(otherDevice: Device): ProtocolType? {
        if (networkPrefix != otherDevice.networkPrefix) {
            return null
        }
        val commonProtocols = protocols.intersect(otherDevice.protocols)
        val distance = getDistanceInMeters(otherDevice)
        return chooseBestFitProtocol(commonProtocols, distance)
    }
}