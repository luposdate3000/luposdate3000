package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPBind(query: Query, @JvmField val name: AOPVariable, expression: AOPBase, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPBindID, "LOPBind", arrayOf(child, expression), ESortPriority.BIND) {
    override fun childrenToVerifyCount(): Int = 1
    override fun getProvidedVariableNames() = (children[0].getProvidedVariableNames() + name.name).distinct().toMutableList()
    override fun getRequiredVariableNames(): List<String> = children[1].getRequiredVariableNamesRecoursive().distinct()
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name.name)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5060)
        if (other !is LOPBind) {
Coverage.ifStart(5061)
            return false
        }
Coverage.statementStart(5062)
        if (name != other.name) {
Coverage.ifStart(5063)
            return false
        }
Coverage.statementStart(5064)
        for (i in children.indices) {
Coverage.forLoopStart(5065)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5066)
                return false
            }
Coverage.statementStart(5067)
        }
Coverage.statementStart(5068)
        return true
    }
    override fun cloneOP() = LOPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5069)
        var res = HistogramResult()
Coverage.statementStart(5070)
        var childHistogram = children[0].getHistogram()
Coverage.statementStart(5071)
        var distinct = 1
Coverage.statementStart(5072)
        var requiredVariables = getRequiredVariableNames()
Coverage.statementStart(5073)
        childHistogram.values.forEach { k, v ->
Coverage.statementStart(5074)
            res.values[k] = v
Coverage.statementStart(5075)
            if (requiredVariables.contains(k)) {
Coverage.ifStart(5076)
                distinct *= v
Coverage.statementStart(5077)
            }
Coverage.statementStart(5078)
        }
Coverage.statementStart(5079)
        res.count = childHistogram.count
Coverage.statementStart(5080)
        if (distinct > res.count) {
Coverage.ifStart(5081)
            distinct = res.count
Coverage.statementStart(5082)
        }
Coverage.statementStart(5083)
        res.values[name.name] = distinct
Coverage.statementStart(5084)
        return res
    }
}
