package lupos.s03resultRepresentation

class ResultSetDictionary() {
    override val classname="ResultSetDictionary"
    val undefValue: Value = null
    fun createValue(value: String): Value = value
    fun getValue(value: Value): String? = value
}
