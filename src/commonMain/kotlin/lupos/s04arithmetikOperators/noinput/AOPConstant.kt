package lupos.s04ArithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04ArithmetikOperators.*

class AOPConstant(val value: String) : AOPBase() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPConstant")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPConstant)
            return false
        return value == other.value
    }
}
