package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPIri(var iri: String) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPIri")
        res.addAttribute("iri", "" + iri)
        return res
    }

    override fun valueToString() = "<" + iri + ">"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPIri)
            return false
        return iri == other.iri
    }
}
