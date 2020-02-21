package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBooleanLiteral(var value: Boolean) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBooleanLiteral")
        res.addAttribute("value", "" + value)
        return res
    }

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBooleanLiteral)
            return false
        return value == other.value
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPBooleanLiteral to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPBooleanLiteral to Int")
    }

    override fun toBoolean(): Boolean = value
}
