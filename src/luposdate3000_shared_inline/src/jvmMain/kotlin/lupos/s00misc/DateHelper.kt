package lupos.s00misc
import java.util.Calendar
import kotlin.jvm.JvmName
internal actual class DateHelper actual constructor() {
    @JvmField
    internal val time = Calendar.getInstance()
    @JvmName("year") internal actual inline fun year(): Int = time.get(Calendar.YEAR)
    @JvmName("month") internal actual inline fun month(): Int = time.get(Calendar.MONTH)
    @JvmName("day") internal actual inline fun day(): Int = time.get(Calendar.DAY_OF_MONTH)
    @JvmName("hours") internal actual inline fun hours(): Int = time.get(Calendar.HOUR)
    @JvmName("minutes") internal actual inline fun minutes(): Int = time.get(Calendar.MINUTE)
    @JvmName("seconds") internal actual inline fun seconds(): Int = time.get(Calendar.SECOND)
}
