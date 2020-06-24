package lupos.s04logicalOperators.singleinput.modifiers

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPPrefix(query: Query, @JvmField val name: String, @JvmField val iri: String, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPPrefixID, "LOPPrefix", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("iri", iri)
    override fun equals(other: Any?) = other is LOPPrefix && name == other.name && iri == other.iri && children[0] == other.children[0]
    override fun cloneOP() = LOPPrefix(query, name, iri, children[0].cloneOP())
    override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
