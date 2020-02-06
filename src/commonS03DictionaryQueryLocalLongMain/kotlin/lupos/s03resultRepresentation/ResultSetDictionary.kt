package lupos.s03resultRepresentation

class ResultSetDictionary() {
    val mapSTL = mutableMapOf<String, Long>()
    val mapLTS = mutableListOf<String>()
    val undefValue = Long.MAX_VALUE
    fun createValue(value: String): Value {
        val o = mapSTL[value]
        if (o != null)
            return o
        val l = 0L + mapLTS.size
        mapSTL[value] = l
        mapLTS.add(value)
        return l
    }

    fun getValue(value: Value): String? {
        if (value == undefValue)
            return null
        return mapLTS[value.toInt()]
    }
}
