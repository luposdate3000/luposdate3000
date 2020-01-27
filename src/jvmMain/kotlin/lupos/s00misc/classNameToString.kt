package lupos.misc
import lupos.s00misc.XMLElement

fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null)
        return ""
    return res
}
