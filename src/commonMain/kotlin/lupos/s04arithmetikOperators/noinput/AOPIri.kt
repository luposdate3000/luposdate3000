package lupos.s04arithmetikOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPIri(@JvmField var iri: String) : AOPConstant() {
    override val operatorID = EOperatorID.AOPIriID
    override val classname = "AOPIri"
    override val children: Array<OPBase> = arrayOf()

    override fun toXMLElement() = super.toXMLElement().addAttribute("value", "" + iri)

    override fun valueToString() = "<" + iri + ">"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPIri)
            return false
        return iri == other.iri
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPIri to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPIri to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPIri to Boolean")
    }
}
