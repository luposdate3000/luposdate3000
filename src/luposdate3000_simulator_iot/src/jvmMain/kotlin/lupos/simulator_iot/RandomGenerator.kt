package lupos.simulator_iot

import kotlin.random.Random

public object RandomGenerator {
    public var seed: Int = 1
        set(value) {
            field = value
            random = Random(value)
        }
    public var random: Random = Random(seed)
        private set

    public fun getDouble(minInclusive: Double, maxInclusive: Double): Double {
        if (minInclusive == maxInclusive) {
            return minInclusive
        }
        require(maxInclusive < Double.MAX_VALUE)
        val maxExclusive = maxInclusive + Double.MIN_VALUE
        return random.nextDouble(minInclusive, maxExclusive)
    }

    public fun getInt(minInclusive: Int, maxInclusive: Int): Int =
        getDouble(minInclusive.toDouble(), maxInclusive.toDouble()).toInt()

    public fun getBoolean(probabilityOfTrue: Float): Boolean =
        random.nextFloat() < probabilityOfTrue
}
