package lupos.s05tripleStore.index_SingleList
import lupos.s00misc.MyListInt
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s04logicalOperators.iterator.ColumnIterator
import kotlin.jvm.JvmField
public class ColumnIteratorStore2b(@JvmField public val values: MyListInt, start: Int) : ColumnIterator() {
    @JvmField
    var counterSecondary: Int
    @JvmField
    var counterTerniary: Int
    @JvmField
    var index = start + 3
    @JvmField
    var label = 1
    init {
        counterSecondary = values[index - 3] - 1
        counterTerniary = values[index - 1] - 1
    }
    internal inline fun _close() {
        label = 0
    }
    override suspend fun close() {
        _close()
    }
    override suspend fun next(): Int {
        if (label != 0) {
            var res: Int = values[index]
            index++
            if (counterTerniary == 0) {
                if (counterSecondary == 0) {
                    _close()
                } else {
                    counterSecondary--
                    counterTerniary = values[index + 1] - 1
                    index += 2
                }
            } else {
                counterTerniary--
            }
            return res
        } else {
            return ResultSetDictionary.nullValue
        }
    }
}
