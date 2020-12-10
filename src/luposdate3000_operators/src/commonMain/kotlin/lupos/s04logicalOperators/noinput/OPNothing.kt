package lupos.s04logicalOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import kotlin.jvm.JvmField

class OPNothing(query: IQuery, @JvmField val myProvidedVariableNames: List<String>) : LOPBase(query, EOperatorID.OPNothingID, "OPNothing", arrayOf(), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): List<String> = myProvidedVariableNames
    override fun toSparql(): String = "{}"
    override /*suspend*/ fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        for (v in myProvidedVariableNames) {
            res.addContent(XMLElement("v").addContent(v))
        }
        return res
    }

    override fun equals(other: Any?): Boolean = other is OPNothing
    override fun cloneOP(): IOPBase = this
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.count = 0
        for (v in myProvidedVariableNames) {
            res.values[v] = 0
        }
        return res
    }
}
