package lupos.simulator_iot

import lupos.simulator_iot.geo.GeoLocation
import kotlin.random.Random
import kotlin.test.*

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
        RandomGenerator.seed = Random.nextInt()
        val center = GeoLocation(lat, lng)
        val createdLoc = GeoLocation.getRandomLocationInRadius(center, radius)
        val distance = center.getDistanceInMeters(createdLoc)
        assertTrue(distance <= radius)
    }
}
