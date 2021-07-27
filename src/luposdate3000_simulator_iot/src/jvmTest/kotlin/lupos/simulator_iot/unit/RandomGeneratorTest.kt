package lupos.simulator_iot.unit

import lupos.simulator_iot.RandomGenerator
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RandomGeneratorTest {

    @Test
    fun `change seed`() {
        val randomGenerator = RandomGenerator()
        val firstSeed = 1
        randomGenerator.seed = firstSeed
        val actualSeed = randomGenerator.seed
        assertEquals(firstSeed, actualSeed)
    }

    @Test
    fun `random double is between min and max`() {
        `random double is between min and max`(0.0, 0.0)
        `random double is between min and max`(1.0, 1.0)
        `random double is between min and max`(4.43, 699.01)
    }

    private fun `random double is between min and max`(min: Double, max: Double) {
        val randomGenerator = RandomGenerator()
        randomGenerator.seed = Random.nextInt(50)
        for (i in 1..30) {
            val actual = randomGenerator.getDouble(min, max)
            assertTrue(actual >= min, "actual is $actual")
            assertTrue(actual <= max, "actual is $actual")
        }
    }

    @Test
    fun `same seed results in same random sequence`() {
        `same seed results in same random sequence`(1)
        `same seed results in same random sequence`(2)
        `same seed results in same random sequence`(53)
        `same seed results in same random sequence`(8)
    }

    private fun `same seed results in same random sequence`(seed: Int) {
        val randomGenerator = RandomGenerator()
        val sequenceSize = 1000
        val firstSequence = DoubleArray(sequenceSize)
        val secondSequence = DoubleArray(sequenceSize)
        val maxRandom = Double.MIN_VALUE
        val minRandom = sequenceSize.toDouble()

        randomGenerator.seed = seed
        for (i in 0 until sequenceSize)
            firstSequence[i] = randomGenerator.getDouble(maxRandom, minRandom)

        randomGenerator.seed = seed
        for (i in 0 until sequenceSize)
            secondSequence[i] = randomGenerator.getDouble(maxRandom, minRandom)

        for (i in 0 until sequenceSize)
            assertEquals(firstSequence[i], secondSequence[i])
    }

    @Test
    fun `get true`() {
        val randomGenerator = RandomGenerator()
        randomGenerator.seed = Random.nextInt(50)
        assertTrue(randomGenerator.getBoolean(1.0F))
    }

    @Test
    fun `get false`() {
        val randomGenerator = RandomGenerator()
        randomGenerator.seed = Random.nextInt(50)
        assertFalse(randomGenerator.getBoolean(0.0F))
    }
}
