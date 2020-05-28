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
Coverage.funStart(4650)
        var res = super.toXMLElement()
Coverage.statementStart(4651)
        var x = XMLElement("columnProjectionOrders")
Coverage.statementStart(4652)
        res.addContent(x)
Coverage.statementStart(4653)
        for (cpos in columnProjectionOrder) {
Coverage.forLoopStart(4654)
            var y = XMLElement("columnProjectionOrder")
Coverage.statementStart(4655)
            x.addContent(y)
Coverage.statementStart(4656)
            for (cpo in cpos) {
Coverage.forLoopStart(4657)
                var z = XMLElement("columnProjectionOrderElement")
Coverage.statementStart(4658)
                y.addContent(z)
Coverage.statementStart(4659)
                z.addContent(cpo)
Coverage.statementStart(4660)
            }
Coverage.statementStart(4661)
        }
Coverage.statementStart(4662)
        return res
    }
    override fun calculateHistogram(): HistogramResult = throw Exception("unreachable")
    override fun equals(other: Any?): Boolean {
Coverage.funStart(4663)
        if (other !is OPBaseCompound) {
Coverage.ifStart(4664)
            return false
        }
Coverage.statementStart(4665)
        if (children.size != other.children.size) {
Coverage.ifStart(4666)
            return false
        }
Coverage.statementStart(4667)
        for (i in 0 until children.size) {
Coverage.forLoopStart(4668)
            if (children[i] != other.children[i]) {
Coverage.ifStart(4669)
                return false
            }
Coverage.statementStart(4670)
        }
Coverage.statementStart(4671)
        if (columnProjectionOrder.size != other.columnProjectionOrder.size) {
Coverage.ifStart(4672)
            return false
        }
Coverage.statementStart(4673)
        for (i in 0 until columnProjectionOrder.size) {
Coverage.forLoopStart(4674)
            if (columnProjectionOrder[i].size != other.columnProjectionOrder[i].size) {
Coverage.ifStart(4675)
                return false
            }
Coverage.statementStart(4676)
            for (j in 0 until columnProjectionOrder[i].size) {
Coverage.forLoopStart(4677)
                if (columnProjectionOrder[i][j] != other.columnProjectionOrder[i][j]) {
Coverage.ifStart(4678)
                    return false
                }
Coverage.statementStart(4679)
            }
Coverage.statementStart(4680)
        }
Coverage.statementStart(4681)
        return true
    }
    override fun toSparqlQuery() = toSparql()
    override fun toSparql(): String {
Coverage.funStart(4682)
        var res = StringBuilder()
Coverage.statementStart(4683)
        for (c in children) {
Coverage.forLoopStart(4684)
            res.append(c.toSparqlQuery() + "\n")
Coverage.statementStart(4685)
        }
Coverage.statementStart(4686)
        return res.toString()
    }
}
