package lupos.simulator_iot

import kotlin.random.Random

internal class RandomGenerator {
    internal var seed: Int = 1
        set(value) {
            field = value
            random = Random(value)
        }
    internal var random: Random = Random(seed)
        private set

    internal fun getDouble(minInclusive: Double, maxInclusive: Double): Double {
        if (minInclusive == maxInclusive) {
            return minInclusive
        }
        require(maxInclusive < Double.MAX_VALUE)
        val maxExclusive = maxInclusive + Double.MIN_VALUE
        return random.nextDouble(minInclusive, maxExclusive)
    }

    internal fun getInt(minInclusive: Int, maxInclusive: Int): Int =
        getDouble(minInclusive.toDouble(), maxInclusive.toDouble()).toInt()

    internal fun getBoolean(probabilityOfTrue: Float): Boolean =
        random.nextFloat() < probabilityOfTrue
}
