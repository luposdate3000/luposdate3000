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

class AOPBuildInCallIRI(query: Query, child: AOPBase, @JvmField var prefix: String = "") : AOPBase(query, EOperatorID.AOPBuildInCallIRIID, "AOPBuildInCallIRI", arrayOf(child)) {
    override fun toSparql() = "IRI(" + children[0].toSparql() + ")"
    override fun applyPrefix(prefix: String, iri: String) {
        if (prefix == "")
            this.prefix = iri
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("prefix", prefix)
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIRI)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            if (a is ValueIri)
                rVektor.data[i] = a
            else if (a is ValueSimpleLiteral || a is ValueTypedLiteral && a.type_iri == "http://www.w3.org/2001/XMLSchema#string") {
                val b = a as ValueStringBase
                if (prefix != "" && !prefix.endsWith("/"))
                    rVektor.data[i] = ValueIri(prefix + "/" + b.content)
                else
                    rVektor.data[i] = ValueIri(prefix + b.content)
            }
        }
        return rVektor
    }

    override fun cloneOP() = AOPBuildInCallIRI(query, children[0].cloneOP() as AOPBase, prefix)
}
