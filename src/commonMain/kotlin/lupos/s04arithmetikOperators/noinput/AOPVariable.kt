package lupos.s04ArithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04ArithmetikOperators.*

class AOPVariable(var name: String) : AOPBase() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>(name)
    }

    override fun toXMLElement(): XMLElement {
        return XMLElement("AOPVariable")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPVariable)
            return false
        if (name != other.name)
            return false
        return true
    }
}
