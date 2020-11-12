package lupos.s04logicalOperators.singleinput.modifiers

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import kotlin.jvm.JvmField

class LOPPrefix(query: IQuery, @JvmField val name: String, @JvmField val iri: String, child: IOPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPPrefixID, "LOPPrefix", arrayOf(child), ESortPriority.SAME_AS_CHILD) {
    override /*suspend*/ fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("iri", iri)
    override fun equals(other: Any?) = other is LOPPrefix && name == other.name && iri == other.iri && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPPrefix(query, name, iri, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
