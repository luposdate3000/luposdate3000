package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s00misc.MyMapStringIntPatriciaTrie
import lupos.s03resultRepresentation.MyListValue
class PatriciaTrie {
    var nextValue = 0
    val data = MyMapStringIntPatriciaTrie()
    fun insert(key: String) = data.getOrCreate(key, { nextValue++ })
    fun getDictionaryMapping(dictionary: ResultSetDictionary): MyListValue {
Coverage.funStart(1846)
        val res = MyListValue(nextValue) { ResultSetDictionary.undefValue }
Coverage.statementStart(1847)
        data.forEach { v, k ->
Coverage.statementStart(1848)
            res[k] = dictionary.createValue(ValueDefinition(v))
Coverage.statementStart(1849)
        }
Coverage.statementStart(1850)
        return res
    }
}
