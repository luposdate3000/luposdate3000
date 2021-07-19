package lupos.simulator_iot.models.geo

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

// adapter class for LatLng
public class GeoLocation(internal var latitude: Double, internal var longitude: Double) {

    internal companion object {

        private fun travel(start: GeoLocation, distanceInMeters: Int, direction: Double): GeoLocation {
            val loc = LatLngTool.travel(
                LatLng(start.latitude, start.longitude),
                direction,
                distanceInMeters.toDouble(),
                LengthUnit.METER
            )
            return GeoLocation(loc.getLatitude(), loc.getLongitude())
        }

        internal fun createNorthernLocation(start: GeoLocation, distanceInMeters: Int): GeoLocation =
            travel(start, distanceInMeters, LatLngTool.Bearing.NORTH)

        internal fun createSouthernLocation(start: GeoLocation, distanceInMeters: Int): GeoLocation =
            travel(start, distanceInMeters, LatLngTool.Bearing.SOUTH)

        internal fun createEasternLocation(start: GeoLocation, distanceInMeters: Int): GeoLocation =
            travel(start, distanceInMeters, LatLngTool.Bearing.EAST)

        // Adapted From: https://gis.stackexchange.com/questions/25877/generating-random-locations-nearby
        internal fun getRandomLocationInRadius(center: GeoLocation, radiusInMeters: Int, random: Random): GeoLocation {
            val x0 = center.longitude
            val y0 = center.latitude

            // Convert radius from meters to degrees
            val radiusInDegrees = (radiusInMeters / 111000f).toDouble()
            val u = random.nextDouble()
            val v = random.nextDouble()
            val w = radiusInDegrees * sqrt(u)
            val t = 2 * PI * v
            val x = w * cos(t)
            val y = w * sin(t)

            // Adjust the x-coordinate for the shrinking of the east-west distances
            val newX = x / cos(y0 / 180.0 * PI)
            val foundLongitude = newX + x0
            val foundLatitude = y + y0

            // Check the result for possible rounding errors
            val result = GeoLocation(foundLatitude, foundLongitude)
            if (result.getDistanceInMeters(center) > radiusInMeters) {
                return getRandomLocationInRadius(center, radiusInMeters, random)
            }
            return result
        }

        internal fun getRandom(random: Random): GeoLocation {
            val loc = LatLng.random(random)
            return GeoLocation(loc.getLatitude(), loc.getLongitude())
        }
    }

    internal fun getDistanceInMeters(other: GeoLocation): Int {
        val distance = LatLngTool.distance(
            LatLng(latitude, longitude),
            LatLng(other.latitude, other.longitude),
            LengthUnit.METER
        )
        return round(distance).toInt()
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }

        if (other !is GeoLocation) {
            return false
        }

        return latitude == other.latitude && longitude == other.longitude
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }
}
