package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.MyListInt
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator
import kotlin.jvm.JvmField
public class ColumnIteratorStore1(@JvmField public val values: MyListInt, start: Int) : ColumnIterator() {
    @JvmField
    var index = start + 1
    @JvmField
    val end = start + values[start] + 1
    override suspend fun close() {
    }
    override suspend fun next(): Int {
        if (index == end) {
            return ResultSetDictionary.nullValue
        } else {
            return values[index++]
        }
    }
}
