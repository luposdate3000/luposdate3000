package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPSimpleLiteral : AOPConstantString {
    override val classname = "AOPSimpleLiteral"
    override val children: Array<OPBase> = arrayOf()
    override val delimiter: String
    override val content: String

    constructor(delimiter: String, content: String) : super() {
        this.delimiter = delimiter
        this.content = content
    }

    override fun toXMLElement() = super.toXMLElement().addAttribute("delimiter", delimiter).addAttribute("content", content)

    override fun valueToString() = delimiter + content + delimiter
    override fun equals(other: Any?): Boolean {
        if (other !is AOPSimpleLiteral)
            return false
        if (delimiter != other.delimiter)
            return false
        return content == other.content
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPSimpleLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPSimpleLiteral to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPSimpleLiteral to Boolean")
    }
}
