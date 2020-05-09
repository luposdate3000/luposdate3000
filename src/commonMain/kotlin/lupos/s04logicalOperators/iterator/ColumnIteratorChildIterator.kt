package lupos.s04logicalOperators.iterator
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


class ColumnIteratorChildIterator() : ColumnIterator() {
    val childs = mutableListOf(ColumnIterator())
    var onNoMoreElements: suspend () -> Unit = ::_onNoMoreElements

    init {
        next = {
            var res: Value? = null
            while (childs.size > 0) {
                res = childs[0].next()
                if (res == null) {
                    childs.removeAt(0)
                } else {
                    break
                }
            }
            if (res == null) {
                onNoMoreElements()
                if (childs.size == 0) {
                    close()
                }
                res = next()
            }
            /*return*/res
        }
        close = {
            onNoMoreElements = ::_onNoMoreElements
            for (child in childs) {
                child.close()
            }
            _close()
        }
    }

    suspend fun _onNoMoreElements() {
        close()
    }
}
