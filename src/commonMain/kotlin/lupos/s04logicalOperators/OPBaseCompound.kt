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
import lupos.s04logicalOperators.HistogramResult
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

    override fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
    override fun equals(other: Any?): Boolean {
        if (other !is OPBaseCompound) {
            return false
        }
        if (children.size != other.children.size) {
            return false
        }
        for (i in 0 until children.size) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        if (columnProjectionOrder.size != other.columnProjectionOrder.size) {
            return false
        }
        for (i in 0 until columnProjectionOrder.size) {
            if (columnProjectionOrder[i].size != other.columnProjectionOrder[i].size) {
                return false
            }
            for (j in 0 until columnProjectionOrder[i].size) {
                if (columnProjectionOrder[i][j] != other.columnProjectionOrder[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        var res = StringBuilder()
        for (c in children) {
            res.append(c.toSparqlQuery() + "\n")
        }
        return res.toString()
    }
}
