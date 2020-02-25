package lupos.s04logicalOperators.noinput

import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class LOPValues(val variables: List<AOPVariable>, values: List<AOPValue>) : LOPBase() {
    override val classname = "LOPValues"
    override val children: Array<OPBase> = Array(values.size) { values[it] }

    override fun getProvidedVariableNames(): List<String> {
        val res = MutableList(variables.size) { variables[it].name }.distinct()
        println("($classname)($uuid)getProvidedVariableNames $res")
        return res
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPValues")
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables)
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        bindings.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPValues)
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
