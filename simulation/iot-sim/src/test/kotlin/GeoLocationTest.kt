
import geo.GeoLocation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.random.Random

class GeoLocationTest {

    @ParameterizedTest
    @CsvSource(
        "53.83396283919661, 10.693484850649323, 40",
        "53.83396283919661, 10.693484850649323, 0",
        "53.83396283919661, 10.693484850649323, 1",
        "53.83396283919661, 10.693484850649323, 1000",
        "0.0, 0.0, 0",
    )
    fun `create random location within radius`(lat: Double, lng: Double, radius: Int) {
        RandomGenerator.seed = Random.nextInt()
        val center = GeoLocation(lat, lng)
        val createdLoc = GeoLocation.getRandomLocationInRadius(center, radius)
        val distance = center.getDistanceInMeters(createdLoc)
        Assertions.assertTrue(distance <= radius)
    }
}