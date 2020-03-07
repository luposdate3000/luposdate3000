package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query


class ResultSetDictionary() {
    @JvmField
    val undefValue: Value = null

    fun createValue(value: String): Value = value
    fun getValue(value: Value): String? = value
}
