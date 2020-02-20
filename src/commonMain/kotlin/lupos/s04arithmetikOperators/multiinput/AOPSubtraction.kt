package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPSubtraction(childA: AOPBase, childB: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(childA, childB)
    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPSubtraction")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPSubtraction)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
