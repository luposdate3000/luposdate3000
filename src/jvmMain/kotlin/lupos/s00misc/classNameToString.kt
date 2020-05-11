package lupos.s00misc

import lupos.s00misc.Coverage

fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null) {
        return ""
    }
    return res
}
