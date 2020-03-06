package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPDecimal(@JvmField var value: Double) : AOPConstant(), AOPNumeric {
    override val operatorID = EOperatorID.AOPDecimalID
    override val classname = "AOPDecimal"
    override val children: Array<OPBase> = arrayOf()


    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

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
