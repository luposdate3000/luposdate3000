package lupos.s00misc

import kotlin.jvm.JvmField
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

fun Throwable.kotlinStacktrace() {
    this.printStackTrace()
}
