package lupos.s03resultRepresentation

import kotlin.jvm.JvmField
import kotlinx.coroutines.channels.Channel
import lupos.s00misc.CoroutinesHelper
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s01io.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s09physicalOperators.POPBase

class ValueComparatorDESC(val query: Query) : Comparator<Value> {
    override fun compare(aID: Value, bID: Value): Int {
        val a = query.dictionary.getValue(aID)
        val b = query.dictionary.getValue(bID)
        try {
            return b.compareTo(a)
        } catch (e: Throwable) {
            if (a is ValueUndef || a is ValueError) {
                return +1
            }
            if (b is ValueUndef || b is ValueError) {
                return -1
            }
            if (a is ValueBnode) {
                return +1
            }
            if (b is ValueBnode) {
                return -1
            }
            if (a is ValueIri) {
                return +1
            }
            if (b is ValueIri) {
                return -1
            }
            val sA = a.valueToString()!!
            val sB = b.valueToString()!!
            return sB.compareTo(sA)
        }
    }
}
