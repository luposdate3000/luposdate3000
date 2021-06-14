package lupos.simulator_iot

import kotlinx.datetime.*
import kotlin.time.ExperimentalTime

internal object TimeUtils {


    internal fun addMillis(instant: Instant, millis: Long): Instant
        = instant.plus(millis, DateTimeUnit.MILLISECOND, TimeZone.UTC)


    internal fun toISOString(instant: Instant): String
        = instant.toLocalDateTime(TimeZone.currentSystemDefault()).toString()


    @OptIn(ExperimentalTime::class)
    internal fun differenceInMillis(startInstant: Instant, endInstant: Instant): Long {
        val duration = endInstant - startInstant
        return duration.inWholeMilliseconds
    }


}
