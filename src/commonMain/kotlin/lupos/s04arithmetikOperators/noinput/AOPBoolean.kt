package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class AOPBoolean(query: Query, @JvmField var value: Boolean) : AOPConstant(query, EOperatorID.AOPBooleanID, "AOPBoolean"), AOPXPathCompareable {

    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + value)

    override fun valueToString() = "\"" + value + "\"^^<http://www.w3.org/2001/XMLSchema#boolean>"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBoolean)
            return false
        return value == other.value
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPBoolean to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPBoolean to Int")
    }

    override fun toBoolean(): Boolean = value

    override operator fun compareTo(other: AOPConstant): Int {
        require(other is AOPBoolean)
        if (value == other.value)
            return 0
        if (value && !other.value)
            return 1
        return -1
    }
}
