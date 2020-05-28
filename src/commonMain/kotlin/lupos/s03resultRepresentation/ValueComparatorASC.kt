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
Coverage.funStart(1851)
        val a = query.dictionary.getValue(aID)
Coverage.statementStart(1852)
        val b = query.dictionary.getValue(bID)
Coverage.statementStart(1853)
        try {
Coverage.statementStart(1854)
            return a.compareTo(b)
        } catch (e: Throwable) {
Coverage.statementStart(1855)
            if (a is ValueUndef || a is ValueError) {
Coverage.ifStart(1856)
                return -1
            }
Coverage.statementStart(1857)
            if (b is ValueUndef || b is ValueError) {
Coverage.ifStart(1858)
                return +1
            }
Coverage.statementStart(1859)
            if (a is ValueBnode) {
Coverage.ifStart(1860)
                return -1
            }
Coverage.statementStart(1861)
            if (b is ValueBnode) {
Coverage.ifStart(1862)
                return +1
            }
Coverage.statementStart(1863)
            if (a is ValueIri) {
Coverage.ifStart(1864)
                return -1
            }
Coverage.statementStart(1865)
            if (b is ValueIri) {
Coverage.ifStart(1866)
                return +1
            }
Coverage.statementStart(1867)
            val sA = a.valueToString()!!
Coverage.statementStart(1868)
            val sB = b.valueToString()!!
Coverage.statementStart(1869)
            return sA.compareTo(sB)
        }
Coverage.statementStart(1870)
    }
}
