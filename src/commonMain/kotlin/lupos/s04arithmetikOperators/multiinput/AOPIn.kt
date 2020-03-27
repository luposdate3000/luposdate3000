package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPIn(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPInID, "AOPIn", arrayOf(childA, childB)) {
    override fun toSparql() = "( " + children[0].toSparql() + " IN " + children[1].toSparql() + " )"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPIn)
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
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            val b = (children[1] as AOPBase)
            var found = false
            try {
                if (b is AOPSet) {
                    for (c in b.children) {
                        val tmp = (c as AOPBase).calculate(resultChunk)
                        if (tmp.data[0] == a) {
                            found = true
                            break
                        }
                    }
                }
                rVektor.data[i] = ValueBoolean(found)
            } catch (e: Throwable) {
            }
        }
        return rVektor
    }

    override fun cloneOP() = AOPIn(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
