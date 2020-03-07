package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPDecimal(query:Query,@JvmField var value: Double) : AOPConstant(query,EOperatorID.AOPDecimalID,"AOPDecimal"), AOPNumeric {


    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPDecimal)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean() = value > 0 || value < 0
    override operator fun compareTo(other: AOPConstant): Int {
        require(other is AOPNumeric)
        if (other is AOPInteger)
            return value.compareTo(other.value)
        if (other is AOPDecimal)
            return value.compareTo(other.value)
        if (other is AOPDouble)
            return value.compareTo(other.value)
        throw Exception("unreachable")
    }
}
