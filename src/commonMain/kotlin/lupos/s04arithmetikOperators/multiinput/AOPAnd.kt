package lupos.s04arithmetikOperators.multiinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
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

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        var a: Any
        var b: Any
        try {
            a = ValueBoolean((children[0] as AOPBase).calculate(resultSet, resultRow).toBoolean())
        } catch (e: Throwable) {
            a = e
        }
        try {
            b = ValueBoolean((children[1] as AOPBase).calculate(resultSet, resultRow).toBoolean())
        } catch (e: Throwable) {
            b = e
        }
        if (a is ValueBoolean && b is ValueBoolean)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                ValueBoolean(a.value && b.value)
            })
        if (a is Throwable) {
            if (b is ValueBoolean && b.value == false)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    b
                })
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                a
            })
        }
        if (b is Throwable) {
            if (a is ValueBoolean && a.value == false)
                return resultFlow({ this }, { resultRow }, { resultSet }, {
                    a
                })
            throw resultFlow({ this }, { resultRow }, { resultSet }, {
                b
            })
        }
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPAnd only works with boolean input")
        })
    }

    override fun cloneOP() = AOPAnd(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
