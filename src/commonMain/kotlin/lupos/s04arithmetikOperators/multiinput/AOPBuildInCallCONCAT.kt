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


class AOPBuildInCallCONCAT(query: Query, child: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCONCATID, "AOPBuildInCallCONCAT", arrayOf(child, childB)) {

    override fun toSparql() = "CONCAT(" + children[0].toSparql() + ", " + children[1].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCONCAT)
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
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            val b = bVektor.data[i]
            if (a is ValueLanguageTaggedLiteral && b is ValueLanguageTaggedLiteral && a.language == b.language)
                rVektor.data[i] = ValueLanguageTaggedLiteral(a.delimiter, a.content + b.content, a.language)
            else if (a is ValueTypedLiteral && b is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string" && a.type_iri == b.type_iri)
                rVektor.data[i] = ValueTypedLiteral.create(a.delimiter, a.content + b.content, a.type_iri)
            else if (a is ValueStringBase && b is ValueStringBase)
                rVektor.data[i] = ValueSimpleLiteral(a.delimiter, a.content + b.content)
        }
        return resultFlow({ this }, { resultChunk }, { resultSet }, { rVektor })
    }

    override fun cloneOP() = AOPBuildInCallCONCAT(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase)
}
