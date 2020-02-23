package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPIri(var iri: String) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPIri")
        res.addAttribute("value", iri)
        return res
    }

    override fun valueToString() = "<" + iri + ">"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPIri)
            return false
        return iri == other.iri
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPIri to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPIri to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPIri to Boolean")
    }
}
