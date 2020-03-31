package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s04logicalOperators.Query

fun Throwable.kotlinStacktrace() {
    this.printStackTrace()
}
