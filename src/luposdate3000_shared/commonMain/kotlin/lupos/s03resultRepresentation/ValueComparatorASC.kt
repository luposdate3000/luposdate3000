package lupos.s03resultRepresentation

import lupos.s00misc.EvaluationException
import lupos.s00misc.SanityCheck
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField

class ValueComparatorASC(@JvmField val query: IQuery) : Comparator<Int> {
    override fun compare(aID: Int, bID: Int): Int {
        val a = query.getDictionary().getValue(aID)
        val b = query.getDictionary().getValue(bID)
        try {
            return a.compareTo(b)
        } catch (e: EvaluationException) {
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
        } catch (e: Throwable) {
            SanityCheck.println { "TODO exception 45" }
            e.printStackTrace()
            return 0
        }
/*Coverage Unreachable*/
    }
}
