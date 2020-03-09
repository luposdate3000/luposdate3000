package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.SanityCheck

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPDouble(query: Query, @JvmField var value: Double) : AOPConstant(query, EOperatorID.AOPDoubleID, "AOPDouble"), AOPNumeric {

    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#double>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPDouble)
            return false
        return value == other.value
    }

    override fun toDouble(): Double = value
    override fun toInt(): Int = value.toInt()
    override fun toBoolean() = value > 0 || value < 0
    override operator fun compareTo(other: AOPConstant): Int {
SanityCheck.check({other is AOPNumeric})
        if (other is AOPInteger)
            return value.compareTo(other.value)
        if (other is AOPDecimal)
            return value.compareTo(other.value)
        if (other is AOPDouble)
            return value.compareTo(other.value)
SanityCheck.checkUnreachable()
    }
}
