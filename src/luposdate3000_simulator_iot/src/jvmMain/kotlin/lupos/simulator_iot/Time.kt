package lupos.simulator_iot

import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime

internal object Time {

    internal fun addMillis(instant: Instant, millis: Long): Instant = instant.plus(millis, DateTimeUnit.MILLISECOND, TimeZone.UTC)

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
    internal fun differenceInMicroSec(startInstant: Instant, endInstant: Instant): Long {
        val duration = endInstant - startInstant
        return duration.inWholeMicroseconds
    }

    internal fun toMillis(seconds: Int): Long
        = seconds.toLong() * 1000

    internal fun stamp(): Instant
        = Clock.System.now()
}
