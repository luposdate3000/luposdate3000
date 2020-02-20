package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPTypedLiteral(var delimiter: String, var content: String, var type_iri: String) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPTypedLiteral")
        res.addAttribute("delimiter", "" + delimiter)
        res.addAttribute("content", "" + content)
        res.addAttribute("type_iri", "" + type_iri)
        return res
    }

    override fun valueToString() = delimiter + content + delimiter + "^^<" + type_iri + ">"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPTypedLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        if (type_iri != other.type_iri)
            return false
        return content == other.content
    }
}
