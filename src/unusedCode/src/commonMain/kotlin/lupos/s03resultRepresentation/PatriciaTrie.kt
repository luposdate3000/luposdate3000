package lupos.s03resultRepresentation
import lupos.s00misc.MyListInt
import lupos.s00misc.MyMapStringIntPatriciaTrie
public class PatriciaTrie {
    var nextValue = 0
    val data = MyMapStringIntPatriciaTrie()
    public fun insert(key: String) = data.getOrCreate(key, { nextValue++ })
    public fun getDictionaryMapping(dictionary: ResultSetDictionary): MyListInt {
        val res = MyListInt(nextValue) { ResultSetDictionary.undefValue }
        data.forEach { v, k ->
            res[k] = dictionary.createValue(ValueDefinition(v))
        }
        return res
    }
}
