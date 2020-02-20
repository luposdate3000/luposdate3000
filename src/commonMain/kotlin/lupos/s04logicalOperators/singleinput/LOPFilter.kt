package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04arithmetikOperators.*

class LOPFilter : LOPBase {
    override val children: Array<OPBase> = arrayOf(OPNothing(), OPNothing())
    override fun childrenToVerifyCount() = 1

    constructor(filter: AOPBase) : super() {
        children[1] = filter
    }

    constructor(filter: AOPBase, child: OPBase) : this(filter) {
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return children[0].getRequiredVariableNames() + children[1].getRequiredVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPFilter")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPFilter)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
