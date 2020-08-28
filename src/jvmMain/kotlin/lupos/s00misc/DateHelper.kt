package lupos.s00misc

import java.util.Calendar
import kotlin.jvm.JvmField

class DateHelper() {
    //https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
    @JvmField
    val time = Calendar.getInstance()

    @JvmField
    val year = time.get(Calendar.YEAR)

    @JvmField
    val month = time.get(Calendar.MONTH)

    @JvmField
    val day = time.get(Calendar.DAY_OF_MONTH)

    @JvmField
    val hours = time.get(Calendar.HOUR)

    @JvmField
    val minutes = time.get(Calendar.MINUTE)

    @JvmField
    val seconds = time.get(Calendar.SECOND)

    companion object {
        inline fun markNow() = System.nanoTime()
        inline fun elapsedSeconds(marker: Long) = helperToSeconds(helperElapsed(marker))
        inline fun helperElapsed(marker: Long) = System.nanoTime() - marker
        inline fun helperAdd(time1: Long, time2: Long) = time1 + time2
        inline fun helperToSeconds(time: Long) = time.toDouble() / 1_000_000_000.0
    }
}
