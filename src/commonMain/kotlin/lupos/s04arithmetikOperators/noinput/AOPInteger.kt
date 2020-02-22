package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPInteger(var value: Int) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()

    override fun toTestCaseInput() = "AOPInteger($value)"

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPInteger").addAttribute("value", "" + value)
    }

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPInteger)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value.toDouble()
    override fun toInt(): Int = value
    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPInteger to Boolean")
    }
}
