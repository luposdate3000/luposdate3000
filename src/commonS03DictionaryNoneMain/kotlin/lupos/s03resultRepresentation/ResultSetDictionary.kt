package lupos.s03resultRepresentation

class ResultSetDictionary() {
    fun createValue(value: String): Value = value
    fun getValue(value: Value): String? = value
}
