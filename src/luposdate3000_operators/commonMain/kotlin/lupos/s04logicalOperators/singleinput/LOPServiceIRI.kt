package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField

class LOPServiceIRI(query: IQuery, @JvmField val name: String, @JvmField val silent: Boolean, child: IOPBase) : LOPBase(query, EOperatorID.LOPServiceIRIID, "LOPServiceIRI", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override /*suspend*/ fun toXMLElement() = super.toXMLElement().addAttribute("name", name).addAttribute("silent", "" + silent)
    override fun equals(other: Any?) = other is LOPServiceIRI && name == other.name && silent == other.silent && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPServiceIRI(query, name, silent, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        return children[0].getHistogram()
    }
}
