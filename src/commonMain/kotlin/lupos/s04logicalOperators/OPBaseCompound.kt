package lupos.s04logicalOperators

import kotlin.jvm.JvmField
import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.multiinput.LOPJoin
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter

class OPBaseCompound(query: Query, children: Array<OPBase>, val columnProjectionOrder: List<List<String>>) : OPBase(query, EOperatorID.OPCompoundID, "OPBaseCompound", children, ESortPriority.PREVENT_ANY) {
    override fun cloneOP() = OPBaseCompound(query, children.map { it.cloneOP() }.toTypedArray(), columnProjectionOrder)
    override fun toXMLElement(): XMLElement {
        var res = super.toXMLElement()
        var x = XMLElement("columnProjectionOrders")
        res.addContent(x)
        for (cpos in columnProjectionOrder) {
            var y = XMLElement("columnProjectionOrder")
            x.addContent(y)
            for (cpo in cpos) {
                var z = XMLElement("columnProjectionOrderElement")
                y.addContent(z)
                z.addContent(cpo)
            }
        }
        return res
    }
}
