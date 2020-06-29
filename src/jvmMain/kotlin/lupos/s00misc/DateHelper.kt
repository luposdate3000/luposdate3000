package lupos.s00misc

import java.util.Calendar
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage

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
}
