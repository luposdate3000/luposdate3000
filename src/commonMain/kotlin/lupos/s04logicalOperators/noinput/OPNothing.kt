package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class OPNothing() : LOPBase() {
    override val classname="OPNothing"
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames() = mutableListOf<String>()

    override fun getRequiredVariableNames() = mutableListOf<String>()

    override fun toXMLElement(): XMLElement {
        return XMLElement("OPNothing")
    }

    override fun equals(other: Any?): Boolean {
        if (other !is OPNothing)
            return false
        return true
    }
}
