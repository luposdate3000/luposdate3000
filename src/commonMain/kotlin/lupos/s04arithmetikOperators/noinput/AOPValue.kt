package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPValue(childs: List<AOPConstant>) : AOPBase() {
    override val children: Array<OPBase> = Array(childs.size) { childs[it] }
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPValue")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPValue)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
