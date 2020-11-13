package lupos.s04logicalOperators

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement

class OPBaseCompound(query: IQuery, children: Array<IOPBase>, val columnProjectionOrder: List<List<String>>) : OPBase(query, EOperatorID.OPCompoundID, "OPBaseCompound", children, ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override fun cloneOP(): IOPBase = OPBaseCompound(query, getChildren().map { it.cloneOP() }.toTypedArray(), columnProjectionOrder)
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val x = XMLElement("columnProjectionOrders")
        res.addContent(x)
        for (cpos in columnProjectionOrder) {
            val y = XMLElement("columnProjectionOrder")
            x.addContent(y)
            for (cpo in cpos) {
                val z = XMLElement("columnProjectionOrderElement")
                y.addContent(z)
                z.addContent(cpo)
            }
        }
        return res
    }

    override /*suspend*/ fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
    override fun equals(other: Any?): Boolean {
        if (other !is OPBaseCompound) {
            return false
        }
        if (getChildren().size != other.getChildren().size) {
            return false
        }
        for (i in getChildren().indices) {
            if (getChildren()[i] != other.getChildren()[i]) {
                return false
            }
        }
        if (columnProjectionOrder.size != other.columnProjectionOrder.size) {
            return false
        }
        for (i in columnProjectionOrder.indices) {
            if (columnProjectionOrder[i].size != other.columnProjectionOrder[i].size) {
                return false
            }
            for (j in columnProjectionOrder[i].indices) {
                if (columnProjectionOrder[i][j] != other.columnProjectionOrder[i][j]) {
                    return false
                }
            }
        }
        return true
    }

    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
        val res = StringBuilder()
        for (c in getChildren()) {
            res.append(c.toSparqlQuery() + "\n")
        }
        return res.toString()
    }
}
