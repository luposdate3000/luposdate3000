package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPProjection(query: Query, @JvmField val variables: MutableList<AOPVariable> = mutableListOf(), child: OPBase = OPNothing(query)) : LOPBase(query, EOperatorID.LOPProjectionID, "LOPProjection", arrayOf(child)) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
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
        if (variables != other.variables)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPProjection(query, variables, children[0].cloneOP())
}
