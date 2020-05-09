package lupos.s03resultRepresentation
import lupos.s00misc.*
import lupos.s00misc.Coverage


class PatriciaTrie {
    var nextValue = 0
    val data = MyMapStringIntPatriciaTrie()
    fun insert(key: String) = data.getOrCreate(key, { nextValue++ })
    fun getDictionaryMapping(dictionary: ResultSetDictionary): MyListValue {
        val res = MyListValue(nextValue) { ResultSetDictionary.undefValue }
        data.forEach { v, k ->
            res[k] = dictionary.createValue(ValueDefinition(v))
        }
        return res
    }
}
