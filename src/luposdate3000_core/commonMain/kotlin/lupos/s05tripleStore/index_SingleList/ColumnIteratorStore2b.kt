package lupos.s05tripleStore.index_SingleList

import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator

class ColumnIteratorStore2b(@JvmField val values: MyListValue, start: Int) : ColumnIterator() {
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

    inline fun _close() {
        label = 0
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        if (label != 0) {
            var res: Value = values[index]
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
