package lupos.s00misc
import kotlin.jvm.JvmName
internal expect object IntegerExt {
    @JvmName("numberOfLeadingZeros") internal inline fun numberOfLeadingZeros(value: Int): Int
}
