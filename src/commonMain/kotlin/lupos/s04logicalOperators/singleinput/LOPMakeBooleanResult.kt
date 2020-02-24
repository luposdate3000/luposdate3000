package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPMakeBooleanResult() : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(child: OPBase) : this() {
        children[0] = child
    }

    override fun getProvidedVariableNames() = mutableListOf("?boolean")

    override fun getRequiredVariableNames(): List<String> {
        return listOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPMakeBooleanResult")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPMakeBooleanResult)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
