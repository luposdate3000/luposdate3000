package lupos.s04logicalOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.HistogramResult
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.LOPTriple
import kotlin.jvm.JvmField
public class LOPModify public constructor(
    query: IQuery,
    @JvmField public val insert: MutableList<LOPTriple> = mutableListOf(),
    @JvmField public val delete: MutableList<LOPTriple> = mutableListOf(),
    child: IOPBase
) : LOPBase(query, EOperatorID.LOPModifyID, "LOPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames(): MutableList<String> = mutableListOf("?boolean")
    override /*suspend*/ fun toXMLElement(): XMLElement {
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
    override fun equals(other: Any?): Boolean = other is LOPModify && insert == other.insert && delete == other.delete && children[0] == other.children[0]
    override fun cloneOP(): IOPBase = LOPModify(query, insert, delete, children[0].cloneOP())
    override /*suspend*/ fun calculateHistogram(): HistogramResult {
        val res = HistogramResult()
        res.values["?boolean"] = 1
        res.count = 1
        return res
    }
}
