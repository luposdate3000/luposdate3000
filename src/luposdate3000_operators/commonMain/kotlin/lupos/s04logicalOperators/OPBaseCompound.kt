package lupos.s04logicalOperators

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.SanityCheck
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult

class OPBaseCompound(query: Query, children: Array<OPBase>, val columnProjectionOrder: List<List<String>>) : OPBase(query, EOperatorID.OPCompoundID, "OPBaseCompound", children, ESortPriority.PREVENT_ANY) {
    override fun getPartitionCount(variable: String): Int = SanityCheck.checkUnreachable()
    override fun cloneOP() :IOPBase= OPBaseCompound(query, children.map { it.cloneOP() }.toTypedArray(), columnProjectionOrder)
    override suspend fun toXMLElement(): XMLElement {
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

    suspend override fun calculateHistogram(): HistogramResult = SanityCheck.checkUnreachable()
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
