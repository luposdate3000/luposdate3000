package lupos.s04logicalOperators.noinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class OPNothing(query: Query, @JvmField val myProvidedVariableNames: List<String>) : LOPBase(query, EOperatorID.OPNothingID, "OPNothing", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames() = myProvidedVariableNames
    override fun toSparql() = "{}"
    override suspend fun toXMLElement(): XMLElement {
        var res = super.toXMLElement()
        for (v in myProvidedVariableNames) {
            res.addContent(XMLElement("v").addContent(v))
        }
        return res
    }

    override fun equals(other: Any?) = other is OPNothing
    override fun cloneOP() = this
    suspend override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        res.count = 0
        for (v in myProvidedVariableNames) {
            res.values[v] = 0
        }
        return res
    }
}
