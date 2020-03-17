package lupos.s04arithmetikOperators.singleinput

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


class AOPBuildInCallSTR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child)) {

    override fun toSparql() = "STR(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTR)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw()
        val aVektor = (children[0] as AOPBase).calculate(resultSet, resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            if (a is ValueStringBase)
                rVektor.data[i] = ValueSimpleLiteral(a.delimiter, a.content)
            else if (a is ValueIri)
                rVektor.data[i] = ValueSimpleLiteral("\"", a.iri)
            else if (a !is ValueBnode && a !is ValueUndef) {
                val tmp = a.valueToString()!!
                rVektor.data[i] = ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.lastIndexOf(tmp.get(0))))
            }
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
}
