package lupos.s00misc
import kotlin.jvm.JvmName
internal actual class DateHelper {
    actual constructor() {}
    @JvmName("year") internal actual inline fun year(): Int = throw NotImplementedException("DateHelper", "year not implemented")
    @JvmName("month") internal actual inline fun month(): Int = throw NotImplementedException("DateHelper", "month not implemented")
    @JvmName("day") internal actual inline fun day(): Int = throw NotImplementedException("DateHelper", "day not implemented")
    @JvmName("hours") internal actual inline fun hours(): Int = throw NotImplementedException("DateHelper", "hours not implemented")
    @JvmName("minutes") internal actual inline fun minutes(): Int = throw NotImplementedException("DateHelper", "minutes not implemented")
    @JvmName("seconds") internal actual inline fun seconds(): Int = throw NotImplementedException("DateHelper", "seconds not implemented")
}
