package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPInteger(query:Query,@JvmField var value: Int) : AOPConstant(query,EOperatorID.AOPIntegerID,"AOPInteger"), AOPNumeric {


    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#integer>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPInteger)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value.toDouble()
    override fun toInt(): Int = value
    override fun toBoolean() = value != 0
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
