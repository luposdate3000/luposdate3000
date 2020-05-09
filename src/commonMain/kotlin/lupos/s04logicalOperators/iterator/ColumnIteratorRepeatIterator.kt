package lupos.s04logicalOperators.iterator
import lupos.s00misc.*
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef


class ColumnIteratorRepeatIterator(val count: Int, val child: ColumnIterator) : ColumnIterator() {
    var index = 0
    var index2 = 0
    //TODO use pages instead
    val data = MyListValue()

    init {
        SanityCheck.check { count > 0 }
        next = {
            var res: Value?
            val tmp = child.next()
            if (tmp == null) {
                if (data.size == 0 || count == 1) {
                    next = {
                        /*return*/null
                    }
                } else {
                    index = 2
                    next = {
                        var res2: Value?
                        if (index2 < data.size) {
                            res2 = data[index2++]
                        } else {
                            if (index < count) {
                                index++
                                index2 = 0
                                res2 = data[index2++]
                            } else {
                                res2 = null
                            }
                        }
                        /*return*/res2
                    }
                }
                res = next()
            } else {
                data.add(tmp)
                res = tmp
            }
/*return*/res
        }
        close = {
            child.close()
            _close()
        }
    }
}
