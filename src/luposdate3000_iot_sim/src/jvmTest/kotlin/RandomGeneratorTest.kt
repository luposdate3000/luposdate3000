package lupos.iot_sim


import kotlin.test.*
import kotlin.random.Random

class RandomGeneratorTest {

    @Test
    fun `change seed`() {
        val firstSeed = 1
        RandomGenerator.seed = firstSeed
        val actualSeed = RandomGenerator.seed
        assertEquals(firstSeed, actualSeed)
    }

    @Test
    fun `random double is between min and max`() {
        `random double is between min and max`(0.0, 0.0)
        `random double is between min and max`(1.0, 1.0)
        `random double is between min and max`(4.43, 699.01)
    }

    private fun `random double is between min and max`(min: Double, max: Double) {
        RandomGenerator.seed = Random.nextInt()
        for(i in 1..30) {
            val actual = RandomGenerator.getDouble(min, max)
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
        val sequenceSize = 1000
        val firstSequence = DoubleArray(sequenceSize)
        val secondSequence = DoubleArray(sequenceSize)
        val maxRandom = Double.MIN_VALUE
        val minRandom = sequenceSize.toDouble()

        RandomGenerator.seed = seed
        for(i in 0 until sequenceSize)
            firstSequence[i] = RandomGenerator.getDouble(maxRandom, minRandom)

        RandomGenerator.seed = seed
        for(i in 0 until sequenceSize)
            secondSequence[i] = RandomGenerator.getDouble(maxRandom, minRandom)

        for(i in 0 until sequenceSize)
            assertEquals(firstSequence[i], secondSequence[i])
    }



    @Test
    fun `get true`() {
        RandomGenerator.seed = Random.nextInt()
        assertTrue(RandomGenerator.getBoolean(1.0F))
    }

    @Test
    fun `get false`() {
        RandomGenerator.seed = Random.nextInt()
        assertFalse(RandomGenerator.getBoolean(0.0F))
    }

}
