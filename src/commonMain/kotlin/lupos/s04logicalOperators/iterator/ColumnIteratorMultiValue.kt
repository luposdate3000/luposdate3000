package lupos.s04logicalOperators.iterator
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.Value
object ColumnIteratorMultiValue {
    operator fun invoke(values: IntArray, size: Int) = ColumnIteratorMultiValue_3(values, size)
    operator fun invoke(values: MyListValue) = ColumnIteratorMultiValue_1(values)
    operator fun invoke(iterator: Iterator<Value>) = ColumnIteratorMultiValue_2(iterator)
}
class ColumnIteratorMultiValue_1(val values: MyListValue) : ColumnIterator() {
    var index = 0
    init {
Coverage.funStart(3833)
        next = {
Coverage.statementStart(3834)
            var res: Value?
Coverage.statementStart(3835)
            if (index == values.size) {
Coverage.ifStart(3836)
                res = null
Coverage.statementStart(3837)
            } else {
Coverage.ifStart(3838)
                res = values[index++]
Coverage.statementStart(3839)
            }
Coverage.statementStart(3840)
/*return*/res
        }
Coverage.statementStart(3841)
    }
}
class ColumnIteratorMultiValue_3(val values: IntArray, val size: Int) : ColumnIterator() {
    var index = 0
    init {
Coverage.funStart(3842)
        next = {
Coverage.statementStart(3843)
            var res: Value?
Coverage.statementStart(3844)
            if (index == size) {
Coverage.ifStart(3845)
                res = null
Coverage.statementStart(3846)
            } else {
Coverage.ifStart(3847)
                res = values[index++]
Coverage.statementStart(3848)
            }
Coverage.statementStart(3849)
/*return*/res
        }
Coverage.statementStart(3850)
    }
}
class ColumnIteratorMultiValue_2(val iterator: Iterator<Value>) : ColumnIterator() {
    init {
Coverage.funStart(3851)
        next = {
Coverage.statementStart(3852)
            var res: Value? = null
Coverage.statementStart(3853)
            if (iterator.hasNext()) {
Coverage.ifStart(3854)
                res = iterator.next()
Coverage.statementStart(3855)
            }
Coverage.statementStart(3856)
/*return*/res
        }
Coverage.statementStart(3857)
    }
}
