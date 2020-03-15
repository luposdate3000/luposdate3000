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


class AOPBuildInCallSTRSTARTS(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRSTARTSID, "AOPBuildInCallSTRSTARTS", arrayOf(child, childB)) {
    override fun toSparql() = "STRSTARTS(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRSTARTS)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw()
        val aVektor = (children[0] as AOPBase).calculate(resultSet, resultChunk)
        val bVektor = (children[1] as AOPBase).calculate(resultSet, resultChunk)
        for (i in resultChunk.pos until resultChunk.size) {
            val a = aVektor.data[i]
            val b = bVektor.data[i]
            if (a is ValueStringBase && b is ValueSimpleLiteral)
                rVektor.data[i] = ValueBoolean(a.content.startsWith(b.content))
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPBuildInCallSTRSTARTS(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
