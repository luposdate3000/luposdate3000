package lupos.s03resultRepresentation

import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04logicalOperators.Query

class ValueComparatorASC(val query: Query) : Comparator<Value> {
    override fun compare(aID: Value, bID: Value): Int {
        val a = query.dictionary.getValue(aID)
        val b = query.dictionary.getValue(bID)
        try {
println("ValueComparatorASC compareing")
            return a.compareTo(b)
        } catch (e: Throwable) {
            if (a is ValueUndef || a is ValueError) {
                return -1
            }
            if (b is ValueUndef || b is ValueError) {
                return +1
            }
            if (a is ValueBnode) {
                return -1
            }
            if (b is ValueBnode) {
                return +1
            }
            if (a is ValueIri) {
                return -1
            }
            if (b is ValueIri) {
                return +1
            }
            val sA = a.valueToString()!!
            val sB = b.valueToString()!!
            return sA.compareTo(sB)
        }
    }
}
