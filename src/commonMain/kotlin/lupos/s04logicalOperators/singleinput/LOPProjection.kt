package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s00misc.BugException
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

    override fun equals(other: Any?) = other is LOPProjection && variables == other.variables && children[0] == other.children[0]
    override fun cloneOP() = LOPProjection(query, variables, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
try{
        for (v in variables) {
            res.values[v.name] = childHistogram.values[v.name]!!
        }
}catch(e:Throwable){
throw BugException(classname,"calculateHistogram column missing")
}
        res.count = childHistogram.count
        return res
    }
}
