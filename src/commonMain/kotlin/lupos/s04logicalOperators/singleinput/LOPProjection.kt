package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


class LOPProjection(val variables: MutableList<AOPVariable> = mutableListOf()) : LOPBase() {
    override val operatorID = EOperatorID.LOPProjectionID
    override val classname = "LOPProjection"
    override val children: Array<OPBase> = arrayOf(OPNothing())

    constructor(variables: MutableList<AOPVariable> = mutableListOf(), child: OPBase) : this(variables) {
        this.children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        val res = MutableList(variables.size) { variables[it].name }.distinct()
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        val res = MutableList(variables.size) { variables[it].name }.distinct()
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPProjection")
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables)
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPProjection)
            return false
        if (!variables.equals(other.variables))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
