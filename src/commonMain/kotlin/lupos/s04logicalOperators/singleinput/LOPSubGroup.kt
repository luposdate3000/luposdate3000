package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPSubGroup() : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(child: OPBase) : this() {
        this.children[0] = child
    }

    override fun getProvidedVariableNames() = children[0].getProvidedVariableNames().distinct()

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPSubgroup")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPSubGroup)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
