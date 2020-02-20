package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s04arithmetikOperators.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.parseFromAOPBase


class LOPModify() : LOPBase() {
    override val children: Array<OPBase> = arrayOf(OPNothing())
    var iri: String? = null
    val insert = mutableListOf<AOPBase>()
    val delete = mutableListOf<AOPBase>()

    constructor(child: OPBase) : this() {
        children[0] = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModify")
        res.addContent(XMLElement("where").addContent(childrenToXML()))
        val xmlI = XMLElement("insert")
        res.addContent(xmlI)
        for (e in insert)
            xmlI.addContent(XMLElement.parseFromAOPBase(e))
        val xmlD = XMLElement("delete")
        res.addContent(xmlD)
        for (e in delete)
            xmlD.addContent(XMLElement.parseFromAOPBase(e))
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is LOPModify)
            return false
        if (iri != other.iri)
            return false
        if (!insert.equals(other.insert))
            return false
        if (!delete.equals(other.delete))
            return false
        for (i in children.indices) {
            if (!children[i].equals(other.children[i]))
                return false
        }
        return true
    }
}
