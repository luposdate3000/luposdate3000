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


class AOPBuildInCallIF(query: Query, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {

    override fun toSparql() = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIF)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultSet, resultChunk)
        val bVektor = (children[1] as AOPBase).calculate(resultSet, resultChunk)
        val cVektor = (children[2] as AOPBase).calculate(resultSet, resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            try {
                if (aVektor.data[i].toBoolean())
                    rVektor.data[i] = bVektor.data[i]
                else
                    rVektor.data[i] = cVektor.data[i]
            } catch (e: Throwable) {
            }
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
