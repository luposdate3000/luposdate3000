package lupos.s04logicalOperators.multiinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.*


class LOPMinus : LOPBase {
    override val classname="LOPMinus"
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())

    constructor(first: OPBase, second: OPBase) : super() {
        children[0] = first
        children[1] = second
    }

    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() + children[1].getProvidedVariableNames()).distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames() + children[1].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMinus")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPMinus)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
