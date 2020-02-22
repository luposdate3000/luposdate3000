package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPDecimal(var value: Double) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()


    override fun toTestCaseInput() = "AOPDecimal($value)"

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPDecimal")
        res.addAttribute("value", "" + value)
        return res
    }

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPDecimal)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPDecimal to Boolean")
    }
}
