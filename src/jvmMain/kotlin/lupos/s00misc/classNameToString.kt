package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

fun classNameToString(c: Any): String {
    val res = c::class.simpleName
    if (res == null) {
        return ""
    }
    return res
}
