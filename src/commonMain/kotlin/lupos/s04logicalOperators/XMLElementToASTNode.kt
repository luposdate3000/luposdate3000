package lupos.s04logicalOperators

import lupos.s00misc.XMLElement
import lupos.s02buildSyntaxTree.sparql1_1.Aggregation
import lupos.s02buildSyntaxTree.sparql1_1.ASTAddition
import lupos.s02buildSyntaxTree.sparql1_1.ASTAggregation
import lupos.s02buildSyntaxTree.sparql1_1.ASTAnd
import lupos.s02buildSyntaxTree.sparql1_1.ASTBooleanLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTBuiltInCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTDecimal
import lupos.s02buildSyntaxTree.sparql1_1.ASTDivision
import lupos.s02buildSyntaxTree.sparql1_1.ASTEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTFilter
import lupos.s02buildSyntaxTree.sparql1_1.ASTFunctionCall
import lupos.s02buildSyntaxTree.sparql1_1.ASTGEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTGraph
import lupos.s02buildSyntaxTree.sparql1_1.ASTGT
import lupos.s02buildSyntaxTree.sparql1_1.ASTIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTInteger
import lupos.s02buildSyntaxTree.sparql1_1.ASTIri
import lupos.s02buildSyntaxTree.sparql1_1.ASTLanguageTaggedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTLEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTLT
import lupos.s02buildSyntaxTree.sparql1_1.ASTMultiplication
import lupos.s02buildSyntaxTree.sparql1_1.ASTNEQ
import lupos.s02buildSyntaxTree.sparql1_1.ASTNode
import lupos.s02buildSyntaxTree.sparql1_1.ASTNot
import lupos.s02buildSyntaxTree.sparql1_1.ASTNotIn
import lupos.s02buildSyntaxTree.sparql1_1.ASTOr
import lupos.s02buildSyntaxTree.sparql1_1.ASTSet
import lupos.s02buildSyntaxTree.sparql1_1.ASTSimpleLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTTriple
import lupos.s02buildSyntaxTree.sparql1_1.ASTTypedLiteral
import lupos.s02buildSyntaxTree.sparql1_1.ASTUndef
import lupos.s02buildSyntaxTree.sparql1_1.ASTVar
import lupos.s02buildSyntaxTree.sparql1_1.BuiltInFunctions
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


fun XMLElement.Companion.toASTNode(node: XMLElement): ASTNode {
    when (node.tag) {
        "ASTUndef" -> return ASTUndef()
        "ASTVar" -> return ASTVar(node.attributes["name"]!!)
        "ASTAddition" -> return ASTAddition(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTAggregation" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTAggregation(Aggregation.valueOf(node.attributes["type"]!!), node.attributes["distinct"]!!.toBoolean(), childs.toTypedArray())
        }
        "ASTAnd" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTAnd(childs.toTypedArray())
        }
        "ASTBuiltInCall" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTBuiltInCall(BuiltInFunctions.valueOf(node.attributes["function"]!!), childs.toTypedArray())
        }

        "ASTDivision" -> return ASTDivision(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTEQ" -> return ASTEQ(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTFunctionCall" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTFunctionCall(node.attributes["iri"]!!, node.attributes["distinct"]!!.toBoolean(), childs.toTypedArray())
        }
        "ASTGEQ" -> return ASTGEQ(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTGT" -> return ASTGT(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTInteger" -> return ASTInteger(node.attributes["value"]!!.toInt())
        "ASTIn" -> return ASTIn(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTIri" -> return ASTIri(node.attributes["iri"]!!)
        "ASTLEQ" -> return ASTLEQ(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTLT" -> return ASTLT(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTMultiplication" -> return ASTMultiplication(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTNotIn" -> return ASTNotIn(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTNot" -> return ASTNot(toASTNode(node.childs.first()!!))
        "ASTOr" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTOr(childs.toTypedArray())
        }
        "ASTBooleanLiteral" -> return ASTBooleanLiteral(node.attributes["value"]!!.toBoolean())
        "ASTDecimal" -> return ASTDecimal(node.attributes["value"]!!)
        "ASTNEQ" -> return ASTNEQ(toASTNode(node["childA"]!!.childs.first()!!), toASTNode(node["childB"]!!.childs.first()!!))
        "ASTSet" -> {
            val childs = mutableListOf<ASTNode>()
            node.childs.forEach {
                childs.add(toASTNode(it))
            }
            return ASTSet(childs.toTypedArray())
        }
        "ASTSimpleLiteral" -> return ASTSimpleLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!)
        "ASTTriple" -> return ASTTriple(toASTNode(node["childS"]!!), toASTNode(node["childP"]!!), toASTNode(node["childO"]!!))
        "ASTTypedLiteral" -> return ASTTypedLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!, node.attributes["type_iri"]!!)
        "ASTFilter" -> return ASTFilter(toASTNode(node.childs.first()!!))
        "ASTLanguageTaggedLiteral" -> return ASTLanguageTaggedLiteral(node.attributes["content"]!!, node.attributes["delimiter"]!!, node.attributes["language"]!!)
        "ASTGraph" -> {
            val constraint = mutableListOf<ASTNode>()
            val iriOrVar = XMLElement.toASTNode(node["iriOrVar"]!!.childs.first())
            for (c in node["constraints"]!!.childs)
                constraint.add(XMLElement.toASTNode(c))
            val res = ASTGraph(iriOrVar, constraint.toTypedArray())
            return res
        }
    }
    throw Exception("XMLElement.Companion.toASTNode undefined :: ${node.tag}")
}
