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


class AOPBuildInCallUCASE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallUCASEID, "AOPBuildInCallUCASE", arrayOf(child)) {

    override fun toSparql() = "UCASE(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUCASE)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultChunk: ResultChunk): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultSet, resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            if (a is ValueLanguageTaggedLiteral)
                rVektor.data[i] = ValueLanguageTaggedLiteral(a.delimiter, a.content.toUpperCase(), a.language)
            else if (a is ValueTypedLiteral)
                rVektor.data[i] = ValueTypedLiteral.create(a.delimiter, a.content.toUpperCase(), a.type_iri)
            else if (a is ValueSimpleLiteral)
                rVektor.data[i] = ValueSimpleLiteral(a.delimiter, a.content.toUpperCase())
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPBuildInCallUCASE(query, children[0].cloneOP() as AOPBase)
}
