package lupos.s03buildOperatorGraph.singleinput
import lupos.s03buildOperatorGraph.OPBase
import lupos.s03buildOperatorGraph.parseFromASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s00misc.XMLElement

import lupos.s03buildOperatorGraph.singleinput.LOPBind
import lupos.s03buildOperatorGraph.singleinput.LOPFilter
import lupos.s03buildOperatorGraph.singleinput.LOPGroup
import lupos.s03buildOperatorGraph.singleinput.LOPMakeBooleanResult
import lupos.s03buildOperatorGraph.singleinput.LOPNOOP
import lupos.s03buildOperatorGraph.singleinput.LOPSingleInputBase


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
