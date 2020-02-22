package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBnode(var value: String) : AOPConstant() {
    override val children: Array<OPBase> = arrayOf()

    override fun toTestCaseInput() = "AOPBnode(\"${value.replace("\"", "\\\"")}\")"

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBnode")
        res.addAttribute("value", "" + value)
        return res
    }

    override fun valueToString() = "_:" + value
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBnode)
            return false
        return value == other.value
    }

    override fun toDouble(): Double {
        throw Exception("cannot cast AOPBnode to Double")
    }

    override fun toInt(): Int {
        throw Exception("cannot cast AOPBnode to Int")
    }

    override fun toBoolean(): Boolean {
        throw Exception("cannot cast AOPBnode to Boolean")
    }
}
