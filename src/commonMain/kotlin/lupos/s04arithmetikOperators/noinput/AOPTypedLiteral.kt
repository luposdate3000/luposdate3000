package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPTypedLiteral(override var delimiter: String, override var content: String, var type_iri: String) : AOPConstantString() {
    override val children: Array<OPBase> = arrayOf()

    override fun toTestCaseInput(): String {
        if (delimiter == "\"")
            return "AOPTypedLiteral(\"\\\"\",\"${content.replace("\"", "\\\"")}\",\"${type_iri.replace("\"", "\\\"")}\")"
        return "AOPTypedLiteral(\"$delimiter\",\"${content.replace("\"", "\\\"")}\",\"${type_iri.replace("\"", "\\\"")}\")"
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

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPTypedLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPTypedLiteral to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPTypedLiteral to Boolean")
    }
}
