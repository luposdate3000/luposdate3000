package lupos.s03resultRepresentation
import lupos.s00misc.EOperatorID



class ResultSetDictionary() {
    val undefValue: Value = null
    fun createValue(value: String): Value = value
    fun getValue(value: Value): String? = value
}
