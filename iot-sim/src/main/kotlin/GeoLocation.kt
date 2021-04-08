import com.javadocmd.simplelatlng.LatLng
import com.javadocmd.simplelatlng.LatLngTool
import com.javadocmd.simplelatlng.util.LengthUnit
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

//adapter class for LatLng
class GeoLocation(var latitude: Double, var longitude: Double) {

    companion object {
        fun createNorthernLocation(start: GeoLocation, distanceInMeters: Double): GeoLocation {
            val loc = LatLngTool.travel(
                LatLng(start.latitude, start.longitude),
                LatLngTool.Bearing.NORTH,
                distanceInMeters,
                LengthUnit.METER)
            return GeoLocation(loc.latitude, loc.longitude)
        }

        //Adapted From: https://gis.stackexchange.com/questions/25877/generating-random-locations-nearby
        fun getRandomLocationInRadius(center: GeoLocation, radiusInMeters: Int): GeoLocation {
            val x0 = center.longitude
            val y0 = center.latitude

            // Convert radius from meters to degrees
            val radiusInDegrees = (radiusInMeters / 111000f).toDouble()
            val u = RandomGenerator.random.nextDouble()
            val v = RandomGenerator.random.nextDouble()
            val w = radiusInDegrees * sqrt(u)
            val t = 2 * Math.PI * v
            val x = w * cos(t)
            val y = w * sin(t)

            // Adjust the x-coordinate for the shrinking of the east-west distances
            val newX = x / cos(Math.toRadians(y0))
            val foundLongitude = newX + x0
            val foundLatitude = y + y0
            return GeoLocation(foundLatitude, foundLongitude)
        }

        fun getRandom(): GeoLocation {
            val loc = LatLng.random()
            return GeoLocation(loc.latitude, loc.longitude)
        }

    }

    fun getDistanceInMeters(other: GeoLocation): Int {
        val distance = LatLngTool.distance(
                            LatLng(latitude, longitude),
                            LatLng(other.latitude, other.longitude), LengthUnit.METER)
        return round(distance).toInt()
    }


    override fun equals(other: Any?): Boolean {
        if (other === this)
            return true

        if (other !is GeoLocation)
            return false

        return latitude == other.latitude && longitude == other.longitude
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        return result
    }

}