package lupos.s04logicalOperators.singleinput
import lupos.s04logicalOperators.IQuery
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPTriple
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.Query

class LOPModify(query: IQuery,
                @JvmField val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                @JvmField val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                child: IOPBase) : LOPBase(query, EOperatorID.LOPModifyID, "LOPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames() = mutableListOf<String>("?boolean")
    override suspend fun toXMLElement(): XMLElement {
        val res = super.toXMLElement()
        val xmlI = XMLElement("insert")
        res.addContent(xmlI)
        for (e in insert) {
            xmlI.addContent(e.toXMLElement())
        }
        val xmlD = XMLElement("delete")
        res.addContent(xmlD)
        for (e in delete) {
            xmlD.addContent(e.toXMLElement())
        }
        return res
    }

    override fun equals(other: Any?) = other is LOPModify && insert == other.insert && delete == other.delete && children[0] == other.children[0]
    override fun cloneOP() :IOPBase= LOPModify(query, insert, delete, children[0].cloneOP())
    suspend override fun calculateHistogram(): HistogramResult {
        var res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
