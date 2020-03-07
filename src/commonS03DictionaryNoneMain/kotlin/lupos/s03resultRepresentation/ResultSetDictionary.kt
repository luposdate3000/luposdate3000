package lupos.s03resultRepresentation
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField


class ResultSetDictionary() {
    @JvmField
    val undefValue: Value = null

    fun createValue(value: String): Value = value
    fun getValue(value: Value): String? = value
}
