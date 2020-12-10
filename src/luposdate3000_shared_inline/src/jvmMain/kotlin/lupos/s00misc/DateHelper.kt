package lupos.s00misc
import java.util.Calendar
internal actual class DateHelper actual constructor() {
    @JvmField
    internal val time = Calendar.getInstance()
    actual inline fun year(): Int = time.get(Calendar.YEAR)
    actual inline fun month(): Int = time.get(Calendar.MONTH)
    actual inline fun day(): Int = time.get(Calendar.DAY_OF_MONTH)
    actual inline fun hours(): Int = time.get(Calendar.HOUR)
    actual inline fun minutes(): Int = time.get(Calendar.MINUTE)
    actual inline fun seconds(): Int = time.get(Calendar.SECOND)
}
