package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPExpression
import lupos.s04ArithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.OPBase


class LOPValues(val variables: List<AOPVariable>, val values: List<LOPExpression>) : LOPBase() {
    override val children: Array<OPBase> = arrayOf()
    override fun getProvidedVariableNames(): List<String> {
        var res = mutableListOf<String>()
        for (v in variables)
            res.add(v.name)
        return res
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPValues")
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        for (v in values) {
            val it = v.child.children.iterator()
            for (v2 in variables)
                bindings.addContent(XMLElement("LocalBinding").addAttribute("name", v2.name).addContent(LOPExpression(it.next()).toXMLElement()))
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPValues)
            return false
        if (!variables.equals(other.variables))
            return false
        if (!values.equals(other.values))
            return false
        return true
    }
}
