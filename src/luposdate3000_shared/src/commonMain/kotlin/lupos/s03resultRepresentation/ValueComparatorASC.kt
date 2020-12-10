package lupos.s03resultRepresentation

import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

class ValueComparatorASC(@JvmField val query: IQuery) : Comparator<Int> {
    override fun compare(a: Int, b: Int): Int {
        val a1 = query.getDictionary().getValue(a)
        val b1 = query.getDictionary().getValue(b)
        try {
            return a1.compareTo(b1)
        } catch (e: EvaluationException) {
            if (a1 is ValueUndef || a1 is ValueError) {
                return -1
            }
            if (b1 is ValueUndef || b1 is ValueError) {
                return +1
            }
            if (a1 is ValueBnode) {
                return -1
            }
            if (b1 is ValueBnode) {
                return +1
            }
            if (a1 is ValueIri) {
                return -1
            }
            if (b1 is ValueIri) {
                return +1
            }
            val sA = a1.valueToString()!!
            val sB = b1.valueToString()!!
            return sA.compareTo(sB)
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 45" }
            e.printStackTrace()
            return 0
        }
/*Coverage Unreachable*/
    }
}
