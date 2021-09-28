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

package lupos.simulator_iot.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToLong
import kotlin.time.ExperimentalTime

public object TimeUtils {

    internal fun addNanoSeconds(instant: Instant, nanos: Long): Instant {
        return instant.plus(nanos, DateTimeUnit.NANOSECOND, TimeZone.UTC)
    }

    internal fun toISOString(instant: Instant): String = instant.toLocalDateTime(TimeZone.currentSystemDefault()).toString()

    @OptIn(ExperimentalTime::class)
    internal fun differenceInMillis(startInstant: Instant, endInstant: Instant): Long {
        val duration = endInstant - startInstant
        return duration.inWholeMilliseconds
    }

    internal fun differenceInSeconds(startInstant: Instant, endInstant: Instant): Double {
        val duration = differenceInMillis(startInstant, endInstant).toDouble()
        return duration / 1000
    }

    @OptIn(ExperimentalTime::class)
    internal fun differenceInNanoSec(startInstant: Instant, endInstant: Instant): Long {
        val duration = endInstant - startInstant
        return duration.inWholeNanoseconds
    }

    internal fun toNanoSec(seconds: Int): Long =
        seconds.toLong() * 1000 * 1000 * 1000

    internal fun toNanoSec(seconds: Double): Long =
        (seconds * 1000 * 1000 * 1000).roundToLong()

    internal fun stamp(): Instant =
        Clock.System.now()
}
