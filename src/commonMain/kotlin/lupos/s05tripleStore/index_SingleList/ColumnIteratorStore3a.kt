package lupos.s05tripleStore.index_SingleList

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04logicalOperators.iterator.ColumnIterator

class ColumnIteratorStore3a(@JvmField val values: MyListValue) : ColumnIterator() {
    @JvmField
    var counterPrimary: Int

    @JvmField
    var counterSecondary: Int

    @JvmField
    var counterTerniary: Int

    @JvmField
    var valueA = 0

    @JvmField
    var valueB = 0

    @JvmField
    var valueC = 0

    @JvmField
    val it = values.iterator()

    @JvmField
    var label = 1

    init {
        if (values.size > 4) {
            counterPrimary = it.next() - 1
            valueA = it.next()
            counterSecondary = it.next() - 1
            valueB = it.next()
            counterTerniary = it.next() - 1
        } else {
            counterPrimary = 0
            counterSecondary = 0
            counterTerniary = 0
            label = 0
        }
    }

    inline fun _close() {
        label = 0
    }

    override suspend fun close() {
        _close()
    }

    override suspend fun next(): Value {
        if (label == 1) {
            valueC = it.next()
            var res = valueA
            if (counterTerniary == 0) {
                if (counterSecondary == 0) {
                    if (counterPrimary == 0) {
                        _close()
                    } else {
                        counterPrimary--
                        valueA = it.next()
                        counterSecondary = it.next() - 1
                        valueB = it.next()
                        counterTerniary = it.next() - 1
                    }
                } else {
                    counterSecondary--
                    valueB = it.next()
                    counterTerniary = it.next() - 1
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
