package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPTypedLiteral : AOPConstantString {
    override val children: Array<OPBase> = arrayOf()
    override val delimiter: String
    override val content: String
    val type_iri: String

    constructor(delimiter: String, content: String, type_iri: String) : super() {
        this.delimiter = delimiter
        this.content = content
        this.type_iri = type_iri
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
