/*
 * This file is part of the Luposdate3000 distribution (https://github.com/luposdate3000/luposdate3000).
 * Copyright (c) 2020-2021, Institute of Information Systems (Benjamin Warnke and contributors of LUPOSDATE3000), University of Luebeck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package lupos.simulator_iot

import lupos.shared.SanityCheck
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
        SanityCheck.check(
            { /*SOURCE_FILE_START*/"/src/luposdate3000/src/luposdate3000_simulator_iot/src/commonMain/kotlin/lupos/simulator_iot/RandomGenerator.kt:35"/*SOURCE_FILE_END*/ },
            { maxInclusive < Double.MAX_VALUE },
        )
        val maxExclusive = maxInclusive + Double.MIN_VALUE
        return random.nextDouble(minInclusive, maxExclusive)
    }

    internal fun getInt(minInclusive: Int, maxInclusive: Int): Int =
        getDouble(minInclusive.toDouble(), maxInclusive.toDouble()).toInt()

    internal fun getBoolean(probabilityOfTrue: Float): Boolean =
        random.nextFloat() < probabilityOfTrue
}
