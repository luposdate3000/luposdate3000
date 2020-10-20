package lupos.s04logicalOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPEmptyRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class LOPServiceVAR(query: Query, @JvmField val name: String, @JvmField val silent: Boolean, constraint: OPBase, child: OPBase = OPEmptyRow(query)) : LOPBase(query, EOperatorID.LOPServiceVARID, "LOPServiceVAR", arrayOf(child, constraint), ESortPriority.PREVENT_ANY) {
    override suspend fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent).addContent(XMLElement("constraint").addContent(children[1].toXMLElement()))
    override fun equals(other: Any?) = other is LOPServiceVAR && name == other.name && silent == other.silent && children[0] == other.children[0] && children[1] == other.children[1]
    override fun cloneOP() = LOPServiceVAR(query, name, silent, children[1].cloneOP(), children[0].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
