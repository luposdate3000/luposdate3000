package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class DateHelper() {
    @JvmName("year") internal inline fun year(): Int
    @JvmName("month") internal inline fun month(): Int
    @JvmName("day") internal inline fun day(): Int
    @JvmName("hours") internal inline fun hours(): Int
    @JvmName("minutes") internal inline fun minutes(): Int
    @JvmName("seconds") internal inline fun seconds(): Int
}
