package lupos.s00misc
import kotlin.jvm.JvmName
internal actual object IntegerExt {
     internal actual inline fun numberOfLeadingZeros(value: Int): Int {
        return Integer.numberOfLeadingZeros(value)
    }
}
