package rand

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import simulation.Simulation

class RandomValuesTest {

    @Test
    fun `change seed`() {
        val firstSeed = 1
        RandomValues.seed = firstSeed
        val actualSeed = RandomValues.seed
        Assertions.assertEquals(firstSeed, actualSeed)
    }

    @ParameterizedTest
    @CsvSource(
        "0.0, 0.0",
        "1.0, 1.0",
        "4.43, 699.01",
    )
    fun `random double is between min and max`(min: Double, max: Double) {
        for(i in 1..30) {
            val actual = RandomValues.getDouble(min, max)
            Assertions.assertTrue(actual >= min, "actual is $actual")
            Assertions.assertTrue(actual <= max, "actual is $actual")
        }
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 53, 8])
    fun `same seed results in same random sequence`(seed: Int) {
        val sequenceSize = 1000
        val firstSequence = DoubleArray(sequenceSize)
        val secondSequence = DoubleArray(sequenceSize)
        val maxRandom = Double.MIN_VALUE
        val minRandom = sequenceSize.toDouble()

        RandomValues.seed = seed
        for(i in 0 until sequenceSize) {
            firstSequence[i] = RandomValues.getDouble(maxRandom, minRandom)
        }

        RandomValues.seed = seed
        for(i in 0 until sequenceSize) {
            secondSequence[i] = RandomValues.getDouble(maxRandom, minRandom)
        }

        for(i in 0 until sequenceSize) {
            Assertions.assertEquals(firstSequence[i], secondSequence[i])
        }
    }


}