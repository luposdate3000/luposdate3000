package lupos.s04logicalOperators.singleinput

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.parseFromASTNode
import lupos.s04logicalOperators.singleinput.LOPBind
import lupos.s04logicalOperators.singleinput.LOPFilter
import lupos.s04logicalOperators.singleinput.LOPGroup
import lupos.s04logicalOperators.singleinput.LOPMakeBooleanResult
import lupos.s04logicalOperators.singleinput.LOPNOOP
import lupos.s04logicalOperators.singleinput.LOPSingleInputBase


class LOPModify() : LOPSingleInputBase() {
    var iri: String? = null
    val insert = mutableListOf<ASTNode>()
    val delete = mutableListOf<ASTNode>()

    constructor(child: OPBase) : this() {
        this.child = child
    }

    override fun getProvidedVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun getRequiredVariableNames(): List<String> {
        return mutableListOf<String>()
    }

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("LOPModify")
        res.addContent(XMLElement("where").addContent(child.toXMLElement()))
        val xmlI = XMLElement("insert")
        res.addContent(xmlI)
        for (e in insert)
            xmlI.addContent(XMLElement.parseFromASTNode(e))
        val xmlD = XMLElement("delete")
        res.addContent(xmlD)
        for (e in delete)
            xmlD.addContent(XMLElement.parseFromASTNode(e))
        return res
    }
}
