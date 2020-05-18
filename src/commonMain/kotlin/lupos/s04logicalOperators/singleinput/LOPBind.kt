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
        if (other !is LOPBind) {
            return false
        }
        if (name != other.name) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPBind(query, name, children[1].cloneOP() as AOPBase, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        var childHistogram = children[0].getHistogram()
        res.variableNames.addAll(childHistogram.variableNames)
        res.distinct.addAll(childHistogram.distinct)
        res.count = childHistogram.count
        var requiredVariables = getRequiredVariableNames()
        var distinct = 1
        for (r in requiredVariables) {
            for (i in 0 until childHistogram.variableNames.size) {
                if (childHistogram.variableNames[i] == r) {
                    distinct = distinct * childHistogram.distinct[i]
                    break
                }
            }
        }
        if (distinct > res.count) {
            distinct = res.count
        }
        res.variableNames.add(name.name)
        res.distinct.add(distinct)
        return res
    }
}
