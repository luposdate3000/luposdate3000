package lupos.s00misc

import lupos.s00misc.EOperatorID

fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null)
        return ""
    return res
}
