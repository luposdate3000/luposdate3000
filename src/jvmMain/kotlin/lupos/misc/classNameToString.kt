package lupos.misc
import lupos.s00misc.*
import lupos.misc.*

fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null)
        return ""
    return res
}
