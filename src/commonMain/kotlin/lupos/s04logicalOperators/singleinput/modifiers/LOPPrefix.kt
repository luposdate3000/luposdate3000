package lupos.s04logicalOperators.singleinput.modifiers
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPPrefix(query: Query, @JvmField val name: String, @JvmField val iri: String, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPPrefixID, "LOPPrefix", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("iri", iri)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5367)
        if (other !is LOPPrefix) {
Coverage.ifStart(5368)
            return false
        }
Coverage.statementStart(5369)
        if (name != other.name) {
Coverage.ifStart(5370)
            return false
        }
Coverage.statementStart(5371)
        if (iri != other.iri) {
Coverage.ifStart(5372)
            return false
        }
Coverage.statementStart(5373)
        for (i in children.indices) {
Coverage.forLoopStart(5374)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5375)
                return false
            }
Coverage.statementStart(5376)
        }
Coverage.statementStart(5377)
        return true
    }
    override fun cloneOP() = LOPPrefix(query, name, iri, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5378)
        return children[0].getHistogram()
    }
}
