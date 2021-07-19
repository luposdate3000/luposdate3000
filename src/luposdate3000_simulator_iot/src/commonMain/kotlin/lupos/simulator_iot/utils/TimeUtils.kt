package lupos.simulator_iot.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.math.roundToLong
import kotlin.time.ExperimentalTime

internal object TimeUtils {

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
