package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPSort(val asc: Boolean, var by: AOPVariable) : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(asc: Boolean, by: AOPVariable, child: OPBase) : this(asc, by) {
        this.children[0] = child
    }


    override fun getProvidedVariableNames(): List<String> {
        return children[0].getProvidedVariableNames()
    }

    override fun getRequiredVariableNames(): List<String> {
        return getProvidedVariableNames()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPSort")
        res.addAttribute("by", by.name)
        if (asc)
            res.addAttribute("order", "ASC")
        else
            res.addAttribute("order", "DESC")
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPSort)
            return false
        if (asc != other.asc)
            return false
        if (by != other.by)
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
