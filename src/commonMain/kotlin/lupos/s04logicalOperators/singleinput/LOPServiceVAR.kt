package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPServiceVAR(query: Query, @JvmField val name: String, @JvmField val silent: Boolean, constraint: OPBase, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPServiceVARID, "LOPServiceVAR", arrayOf(child, constraint), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent).addContent(XMLElement("constraint").addContent(children[1].toXMLElement()))
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5263)
        if (other !is LOPServiceVAR) {
Coverage.ifStart(5264)
            return false
        }
Coverage.statementStart(5265)
        if (name != other.name) {
Coverage.ifStart(5266)
            return false
        }
Coverage.statementStart(5267)
        if (silent != other.silent) {
Coverage.ifStart(5268)
            return false
        }
Coverage.statementStart(5269)
        for (i in children.indices) {
Coverage.forLoopStart(5270)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5271)
                return false
            }
Coverage.statementStart(5272)
        }
Coverage.statementStart(5273)
        return true
    }
    override fun cloneOP() = LOPServiceVAR(query, name, silent, children[1].cloneOP(), children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5274)
        return children[0].getHistogram()
    }
}
