package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPSimpleLiteral(override var delimiter: String, override var content: String) : AOPConstantString() {
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPSimpleLiteral")
        res.addAttribute("delimiter", "" + delimiter)
        res.addAttribute("content", "" + content)
        return res
    }

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
