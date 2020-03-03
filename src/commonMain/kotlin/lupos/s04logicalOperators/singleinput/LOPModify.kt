package lupos.s04logicalOperators.singleinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.XMLElement
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.OPBase


class LOPModify(val insert: MutableList<LOPTriple> = mutableListOf<LOPTriple>(), val delete: MutableList<LOPTriple> = mutableListOf<LOPTriple>(), child: OPBase) : LOPBase() {
    override val operatorID = EOperatorID.LOPModifyID
    override val classname = "LOPModify"
    override val children: Array<OPBase> = arrayOf(child)

    override fun getProvidedVariableNames() = mutableListOf<String>()

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModify")
        res.addContent(XMLElement("where").addContent(childrenToXML()))
        val xmlI = XMLElement("insert")
        res.addContent(xmlI)
        for (e in insert)
            xmlI.addContent(e.toXMLElement())
        val xmlD = XMLElement("delete")
        res.addContent(xmlD)
        for (e in delete)
            xmlD.addContent(e.toXMLElement())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPModify)
            return false
        if (insert != other.insert)
            return false
        if (delete != other.delete)
            return false
        for (i in children.indices) {
            if (children[i] != other.children[i])
                return false
        }
        return true
    }

    override fun cloneOP() = LOPModify(insert, delete, children[0].cloneOP())
}
