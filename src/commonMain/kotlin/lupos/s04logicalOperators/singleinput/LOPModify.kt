package lupos.s04logicalOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ESortPriority
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query


class LOPModify(query: Query,
                @JvmField val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                @JvmField val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>(),
                child: OPBase) : LOPBase(query, EOperatorID.LOPModifyID, "LOPModify", arrayOf(child), ESortPriority.PREVENT_ANY) {
    override fun getProvidedVariableNames() = mutableListOf<String>()
    override fun toXMLElement(): XMLElement {
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

    override fun equals(other: Any?): Boolean {
        if (other !is LOPModify) {
            return false
        }
        if (insert != other.insert) {
            return false
        }
        if (delete != other.delete) {
            return false
        }
        for (i in children.indices) {
            if (children[i] != other.children[i]) {
                return false
            }
        }
        return true
    }

    override fun cloneOP() = LOPModify(query, insert, delete, children[0].cloneOP())
}
