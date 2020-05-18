package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPProjection(query: Query, @JvmField val variables: MutableList<AOPVariable> = mutableListOf(), child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPProjectionID, "LOPProjection", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun getRequiredVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }

    override fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables) {
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPProjection) {
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

    override fun cloneOP() = LOPProjection(query, variables, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        for (i in 0 until childHistogram.variableNames.size) {
            if (variables.contains(childHistogram.variableNames[i])) {
                res.variableNames.add(childHistogram.variableNames[i])
                res.distinct.add(childHistogram.distinct[i])
            }
        }
        res.count = childHistogram.count
        return res
    }
}
