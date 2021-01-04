package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField
class LOPProjection(query: IQuery, @JvmField val variables: MutableList<AOPVariable> = mutableListOf(), child: IOPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPProjectionID, "LOPProjection", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun getProvidedVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }
    override fun getRequiredVariableNames(): List<String> {
        return MutableList(variables.size) { variables[it].name }.distinct()
    }
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val vars = XMLElement("LocalVariables")
        res.addContent(vars)
        for (v in variables) {
            vars.addContent(XMLElement("LocalVariable").addAttribute("name", v.name))
        }
        return res
    }
    override fun equals(other: Any?): Boolean = other is LOPProjection && variables == other.variables && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPProjection(query, variables, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        val childHistogram = children[0].getHistogram()
        for (v in variables) {
            val w = childHistogram.values[v.name]
            if (w == null) {
                res.values[v.name] = 1
            } else {
                res.values[v.name] = w
            }
        }
        res.count = childHistogram.count
        return res
    }
}
