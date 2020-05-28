package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class LOPServiceIRI(query: Query, @JvmField val name: String, @JvmField val silent: Boolean, child: OPBase) : LOPBase(query, EOperatorID.LOPServiceIRIID, "LOPServiceIRI", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent)
    override fun equals(other: Any?): Boolean {
Coverage.funStart(5251)
        if (other !is LOPServiceIRI) {
Coverage.ifStart(5252)
            return false
        }
Coverage.statementStart(5253)
        if (name != other.name) {
Coverage.ifStart(5254)
            return false
        }
Coverage.statementStart(5255)
        if (silent != other.silent) {
Coverage.ifStart(5256)
            return false
        }
Coverage.statementStart(5257)
        for (i in children.indices) {
Coverage.forLoopStart(5258)
            if (children[i] != other.children[i]) {
Coverage.ifStart(5259)
                return false
            }
Coverage.statementStart(5260)
        }
Coverage.statementStart(5261)
        return true
    }
    override fun cloneOP() = LOPServiceIRI(query, name, silent, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
Coverage.funStart(5262)
        return children[0].getHistogram()
    }
}
