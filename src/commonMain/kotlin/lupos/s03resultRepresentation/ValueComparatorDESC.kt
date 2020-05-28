package lupos.s03resultRepresentation
import lupos.s00misc.Coverage
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04logicalOperators.Query
class ValueComparatorDESC(val query: Query) : Comparator<Value> {
    override fun compare(aID: Value, bID: Value): Int {
Coverage.funStart(1871)
        val a = query.dictionary.getValue(aID)
Coverage.statementStart(1872)
        val b = query.dictionary.getValue(bID)
Coverage.statementStart(1873)
        try {
Coverage.statementStart(1874)
            return b.compareTo(a)
        } catch (e: Throwable) {
Coverage.statementStart(1875)
            if (a is ValueUndef || a is ValueError) {
Coverage.ifStart(1876)
                return +1
            }
Coverage.statementStart(1877)
            if (b is ValueUndef || b is ValueError) {
Coverage.ifStart(1878)
                return -1
            }
Coverage.statementStart(1879)
            if (a is ValueBnode) {
Coverage.ifStart(1880)
                return +1
            }
Coverage.statementStart(1881)
            if (b is ValueBnode) {
Coverage.ifStart(1882)
                return -1
            }
Coverage.statementStart(1883)
            if (a is ValueIri) {
Coverage.ifStart(1884)
                return +1
            }
Coverage.statementStart(1885)
            if (b is ValueIri) {
Coverage.ifStart(1886)
                return -1
            }
Coverage.statementStart(1887)
            val sA = a.valueToString()!!
Coverage.statementStart(1888)
            val sB = b.valueToString()!!
Coverage.statementStart(1889)
            return sB.compareTo(sA)
        }
Coverage.statementStart(1890)
    }
}
