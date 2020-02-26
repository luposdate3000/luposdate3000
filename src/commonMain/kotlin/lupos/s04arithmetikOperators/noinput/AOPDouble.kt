package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPDouble(var value: Double) : AOPConstant() {
    override val operatorID = EOperatorID.AOPDoubleID
    override val classname = "AOPDouble"
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPDouble)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPDouble to Boolean")
    }
}
