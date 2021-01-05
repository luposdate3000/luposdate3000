package lupos.s00misc
import java.util.Calendar
import kotlin.jvm.JvmName
internal actual class DateHelper actual constructor() {
    @JvmField
    internal val time = Calendar.getInstance()
     internal actual inline fun year(): Int = time.get(Calendar.YEAR)
     internal actual inline fun month(): Int = time.get(Calendar.MONTH)
     internal actual inline fun day(): Int = time.get(Calendar.DAY_OF_MONTH)
     internal actual inline fun hours(): Int = time.get(Calendar.HOUR)
     internal actual inline fun minutes(): Int = time.get(Calendar.MINUTE)
     internal actual inline fun seconds(): Int = time.get(Calendar.SECOND)
}
