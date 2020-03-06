package lupos.s00misc

import kotlin.jvm.JvmField


fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null)
        return ""
    return res
}
