package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPModify(query: Query,
                @JvmField val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                @JvmField val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                child: OPBase) : LOPBase(query, EOperatorID.LOPModifyID, "LOPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames() = mutableListOf<String>("?boolean")
    override fun toXMLElement(): XMLElement {
Coverage.funStart(5183)
        val res = super.toXMLElement()
Coverage.statementStart(5184)
        val xmlI = XMLElement("insert")
Coverage.statementStart(5185)
        res.addContent(xmlI)
Coverage.statementStart(5186)
        for (e in insert) {
Coverage.forLoopStart(5187)
            xmlI.addContent(e.toXMLElement())
Coverage.statementStart(5188)
        }
Coverage.statementStart(5189)
        val xmlD = XMLElement("delete")
Coverage.statementStart(5190)
        res.addContent(xmlD)
Coverage.statementStart(5191)
        for (e in delete) {
Coverage.forLoopStart(5192)
            xmlD.addContent(e.toXMLElement())
Coverage.statementStart(5193)
        }
Coverage.statementStart(5194)
        return res
    }
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5195)
        if (other !is LOPModify) {
Coverage.ifStart(5196)
            return false
        }
Coverage.statementStart(5197)
        if (insert != other.insert) {
Coverage.ifStart(5198)
            return false
        }
Coverage.statementStart(5199)
        if (delete != other.delete) {
Coverage.ifStart(5200)
            return false
        }
Coverage.statementStart(5201)
        for (i in children.indices) {
Coverage.forLoopStart(5202)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5203)
                return false
            }
Coverage.statementStart(5204)
        }
Coverage.statementStart(5205)
        return true
    }
    override fun cloneOP() = LOPModify(query, insert, delete, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5206)
        var res = HistogramResult()
Coverage.statementStart(5207)
        res.values["?boolean"] = 1
Coverage.statementStart(5208)
        res.count = 1
Coverage.statementStart(5209)
        return res
    }
}
