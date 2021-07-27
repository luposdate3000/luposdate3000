package lupos.simulator_iot.unit

import lupos.simulator_iot.RandomGenerator
import lupos.simulator_iot.models.geo.GeoLocation
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GeoLocationTest {

    @Test
    fun `create random location within radius`() {
        `create random location within radius`(53.83396283919661, 10.693484850649323, 40)
        `create random location within radius`(53.83396283919661, 10.693484850649323, 0)
        `create random location within radius`(53.83396283919661, 10.693484850649323, 1)
        `create random location within radius`(53.83396283919661, 10.693484850649323, 1000)
        `create random location within radius`(0.0, 0.0, 0)
    }

    private fun `create random location within radius`(lat: Double, lng: Double, radius: Int) {
        val center = GeoLocation(lat, lng)
        val randGenerator = RandomGenerator()
        for (i in 1 until 1000) {
            val createdLoc = GeoLocation.getRandomLocationInRadius(center, radius, randGenerator.random)
            val distance = center.getDistanceInMeters(createdLoc)
            assertTrue(distance <= radius)
        }
    }

    @Test
    fun getDistanceInMeters() {
        val location = GeoLocation(52.712294850491844, 9.960288525048137)

        val loc0m = GeoLocation.createNorthernLocation(location, 0)
        assertEquals(0, location.getDistanceInMeters(loc0m))

        val loc1m = GeoLocation.createNorthernLocation(location, 1)
        assertEquals(1, location.getDistanceInMeters(loc1m))

        val loc5m = GeoLocation.createNorthernLocation(location, 5)
        assertEquals(5, location.getDistanceInMeters(loc5m))

        val loc1000m = GeoLocation.createNorthernLocation(location, 1000)
        assertEquals(1000, location.getDistanceInMeters(loc1000m))
    }
}
