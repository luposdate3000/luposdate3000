package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s04logicalOperators.OPBase


class AOPIri(query:Query,@JvmField var iri: String) : AOPConstant(query,EOperatorID.AOPIriID,"AOPIri") {

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
