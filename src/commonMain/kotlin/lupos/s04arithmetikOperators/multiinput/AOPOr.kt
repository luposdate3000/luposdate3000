package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultChunk
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPOr(query: Query, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPOrID, "AOPOr", arrayOf(childA, childB)) {

    override fun toSparql() = "(" + children[0].toSparql() + " || " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPOr)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw()
        val aVektor = (children[0] as AOPBase).calculate(resultSet, resultChunk)
        val bVektor = (children[1] as AOPBase).calculate(resultSet, resultChunk)
        for (i in resultChunk.pos until resultChunk.size) {
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
                rVektor.data[i] = ValueBoolean(a.value || b.value)
            else if (a is ValueError && b is ValueBoolean && b.value == true)
                rVektor.data[i] = b
            else if (b is ValueError && a is ValueBoolean && a.value == true)
                rVektor.data[i] = a
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPOr(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
