package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class DateHelper {
    actual constructor() {}
    internal val time = js("new Date()")
    @JvmName("year") internal actual inline fun year(): Int = time.getFullYear()
    @JvmName("month") internal actual inline fun month(): Int = time.getMonth()
    @JvmName("day") internal actual inline fun day(): Int = time.getDay()
    @JvmName("hours") internal actual inline fun hours(): Int = time.getHours()
    @JvmName("minutes") internal actual inline fun minutes(): Int = time.getMinutes()
    @JvmName("seconds") internal actual inline fun seconds(): Int = time.getSeconds()
}
