package lupos.s04logicalOperators.noinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class OPNothing(query: Query, val myProvidedVariableNames: List<String>) : LOPBase(query, EOperatorID.OPNothingID, "OPNothing", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames() = myProvidedVariableNames
    override fun toSparql() = "{}"
    override fun toXMLElement(): XMLElement {
Coverage.funStart(4636)
        var res = super.toXMLElement()
Coverage.statementStart(4637)
        for (v in myProvidedVariableNames) {
Coverage.forLoopStart(4638)
            res.addContent(XMLElement("v").addContent(v))
Coverage.statementStart(4639)
        }
Coverage.statementStart(4640)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4641)
        if (other !is OPNothing) {
Coverage.ifStart(4642)
            return false
        }
Coverage.statementStart(4643)
        return true
    }
    override fun cloneOP() = this
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(4644)
        var res = HistogramResult()
Coverage.statementStart(4645)
        res.count = 0
Coverage.statementStart(4646)
        for (v in myProvidedVariableNames) {
Coverage.forLoopStart(4647)
            res.values[v] = 0
Coverage.statementStart(4648)
        }
Coverage.statementStart(4649)
        return res
    }
}
