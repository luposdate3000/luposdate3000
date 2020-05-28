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
Coverage.funStart(5226)
        return MutableList(variables.size) { variables[it].name }.distinct()
    }
    override fun getRequiredVariableNames(): List<String> {
Coverage.funStart(5227)
        return MutableList(variables.size) { variables[it].name }.distinct()
    }
    override fun toXMLElement(): XMLElement {
Coverage.funStart(5228)
        val res = super.toXMLElement()
Coverage.statementStart(5229)
        val vars = XMLElement("LocalVariables")
Coverage.statementStart(5230)
        res.addContent(vars)
Coverage.statementStart(5231)
        for (v in variables) {
Coverage.forLoopStart(5232)
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
Coverage.statementStart(5233)
        }
Coverage.statementStart(5234)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5235)
        if (other !is LOPProjection) {
Coverage.ifStart(5236)
            return false
        }
Coverage.statementStart(5237)
        if (variables != other.variables) {
Coverage.ifStart(5238)
            return false
        }
Coverage.statementStart(5239)
        for (i in children.indices) {
Coverage.forLoopStart(5240)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5241)
                return false
            }
Coverage.statementStart(5242)
        }
Coverage.statementStart(5243)
        return true
    }
    override fun cloneOP() = LOPProjection(query, variables, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5244)
        var res = HistogramResult()
Coverage.statementStart(5245)
        var childHistogram = children[0].getHistogram()
Coverage.statementStart(5246)
        for (v in variables) {
Coverage.forLoopStart(5247)
            res.values[v.name] = childHistogram.values[v.name]!!
Coverage.statementStart(5248)
        }
Coverage.statementStart(5249)
        res.count = childHistogram.count
Coverage.statementStart(5250)
        return res
    }
}
