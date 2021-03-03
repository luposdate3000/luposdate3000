import com.javadocmd.simplelatlng.LatLng
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

object RandomGenerator {
    var seed: Int = 1
        set(value) {
            field = value
            random = Random(value)
        }
    var random: Random = Random(seed)
        private set

    fun getDouble(minInclusive: Double, maxInclusive: Double): Double {
        if(minInclusive == maxInclusive)
            return minInclusive
        require(maxInclusive < Double.MAX_VALUE)
        val maxExclusive = maxInclusive + Double.MIN_VALUE
        return random.nextDouble(minInclusive, maxExclusive)
    }

    //Adapted From: https://gis.stackexchange.com/questions/25877/generating-random-locations-nearby
    fun getLatLngInRadius(center: LatLng, radiusInMeters: Int): LatLng {
        val x0 = center.longitude
        val y0 = center.latitude

        // Convert radius from meters to degrees
        val radiusInDegrees = (radiusInMeters / 111000f).toDouble()
        val u = random.nextDouble()
        val v = random.nextDouble()
        val w = radiusInDegrees * sqrt(u)
        val t = 2 * Math.PI * v
        val x = w * cos(t)
        val y = w * sin(t)

        // Adjust the x-coordinate for the shrinking of the east-west distances
        val newX = x / cos(Math.toRadians(y0))
        val foundLongitude = newX + x0
        val foundLatitude = y + y0
        return LatLng(foundLatitude, foundLongitude)
    }

}