package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

class AOPBuildInCallDATATYPE(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallDATATYPEID, "AOPBuildInCallDATATYPE", arrayOf(child)) {
    override fun toSparql() = "DATATYPE(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallDATATYPE)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            when (a) {
                is ValueSimpleLiteral -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#string")
                is ValueLanguageTaggedLiteral -> rVektor.data[i] = ValueIri("http://www.w3.org/1999/02/22-rdf-syntax-ns#langString")
                is ValueTypedLiteral -> rVektor.data[i] = ValueIri(a.type_iri)
                is ValueBoolean -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#boolean")
                is ValueDateTime -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#dateTime")
                is ValueDecimal -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#decimal")
                is ValueDouble -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#double")
                is ValueInteger -> rVektor.data[i] = ValueIri("http://www.w3.org/2001/XMLSchema#integer")
            }
        }
        return rVektor
    }

    override fun cloneOP() = AOPBuildInCallDATATYPE(query, children[0].cloneOP() as AOPBase)
}
