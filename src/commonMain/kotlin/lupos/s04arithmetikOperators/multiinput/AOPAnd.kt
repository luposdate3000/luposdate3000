package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPAnd(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPAndID, "AOPAnd", arrayOf(childA, childB)) {
    override fun toSparql() = "(" + children[0].toSparql() + " && " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPAnd)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultChunk)
        val bVektor = (children[1] as AOPBase).calculate(resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            var a: ValueDefinition
            var b: ValueDefinition
            try {
                a = ValueBoolean(aVektor.data[i].toBoolean())
            } catch (e: Throwable) {
                a = ValueError()
            }
            try {
                b = ValueBoolean(bVektor.data[i].toBoolean())
            } catch (e: Throwable) {
                b = ValueError()
            }
            if (a is ValueBoolean && b is ValueBoolean)
                rVektor.data[i] = ValueBoolean(a.value && b.value)
            else if (a is ValueError && b is ValueBoolean && b.value == false)
                rVektor.data[i] = b
            else if (b is ValueError && a is ValueBoolean && a.value == false)
                rVektor.data[i] = a
        }
        return rVektor
    }

    override fun cloneOP() = AOPAnd(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
