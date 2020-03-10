package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import lupos.s04logicalOperators.Query


class ResultSetDictionary() {
    @JvmField
    val undefValue: Value = ValueUndef()

    fun createValue(value: ValueDefinition): Value = value
    fun getValue(value: Value): ValueDefinition = value
}
