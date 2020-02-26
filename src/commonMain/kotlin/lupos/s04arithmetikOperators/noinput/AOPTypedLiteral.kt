package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPTypedLiteral : AOPConstantString {
    override val operatorID = EOperatorID.AOPTypedLiteralID
    override val classname = "AOPTypedLiteral"
    override val children: Array<OPBase> = arrayOf()
    override val delimiter: String
    override val content: String
    val type_iri: String

    constructor(delimiter: String, content: String, type_iri: String) : super() {
        this.delimiter = delimiter
        this.content = content
        this.type_iri = type_iri
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("delimiter", "" + delimiter).addAttribute("content", "" + content).addAttribute("type_iri", "" + type_iri)

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
