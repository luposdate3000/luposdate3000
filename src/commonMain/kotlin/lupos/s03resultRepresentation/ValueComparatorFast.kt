package lupos.s03resultRepresentation

import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueComparatorFast

class ValueComparatorFast() : Comparator<Value> {
    override fun compare(a: Value, b: Value): Int {
        return a.compareTo(b)
    }
}
