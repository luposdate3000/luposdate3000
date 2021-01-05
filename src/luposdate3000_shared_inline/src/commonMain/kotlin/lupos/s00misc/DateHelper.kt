package lupos.s00misc
import kotlin.jvm.JvmName
internal expect class DateHelper() {
     internal inline fun year(): Int
     internal inline fun month(): Int
     internal inline fun day(): Int
     internal inline fun hours(): Int
     internal inline fun minutes(): Int
     internal inline fun seconds(): Int
}
