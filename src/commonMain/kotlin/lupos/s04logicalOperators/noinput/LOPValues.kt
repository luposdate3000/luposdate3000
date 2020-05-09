package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPValues(query: Query, @JvmField val variables: List<AOPVariable>, values: List<AOPValue>) : LOPBase(query, EOperatorID.LOPValuesID, "LOPValues", Array(values.size) { values[it] }, ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPValues").addAttribute("uuid", "" + uuid)
        val xmlvariables = XMLElement("LocalVariables")
        res.addContent(xmlvariables)
        val bindings = XMLElement("LocalBindings")
        res.addContent(bindings)
        for (v in variables) {
            xmlvariables.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        bindings.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPValues) {
            return false
        }
        if (variables != other.variables) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPValues(query, variables, List(children.size) { children[it].cloneOP() as AOPValue })
}
