package lupos.s04logicalOperators

import lupos.s00misc.classNameToString
import lupos.s00misc.XMLElement
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
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.noinput.OPNothing
import lupos.s04logicalOperators.OPBase


fun XMLElement.Companion.parseFromASTNode(node: ASTNode): XMLElement {
    when {
        node is ASTUndef -> return XMLElement("ASTUndef")
        node is ASTVar -> return XMLElement("ASTVar").addAttribute("name", node.name)
        node is ASTAddition -> return XMLElement("ASTAddition").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTAggregation -> {
            val res = XMLElement("ASTAggregation").addAttribute("type", "" + node.type).addAttribute("distinct", "" + node.distinct)
            node.children.forEach() {
                res.addContent(parseFromASTNode(it))
            }
            return res
        }
        node is ASTAnd -> {
            val res = XMLElement("ASTAnd")
            node.children.forEach() {
                res.addContent(parseFromASTNode(it))
            }
            return res
        }
        node is ASTBuiltInCall -> {
            val res = XMLElement("ASTBuiltInCall").addAttribute("function", "" + node.function)
            node.children.forEach() {
                res.addContent(parseFromASTNode(it))
            }
            return res
        }
        node is ASTDivision -> return XMLElement("ASTDivision").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTEQ -> return XMLElement("ASTEQ").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTFunctionCall -> {
            val res = XMLElement("ASTFunctionCall").addAttribute("iri", node.iri).addAttribute("distinct", "" + node.distinct)
            return res
        }
        node is ASTGEQ -> return XMLElement("ASTGEQ").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTGT -> return XMLElement("ASTGT").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTInteger -> return XMLElement("ASTInteger").addAttribute("value", "" + node.value)
        node is ASTIn -> return XMLElement("ASTIn").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTIri -> return XMLElement("ASTIri").addAttribute("iri", node.iri)
        node is ASTLEQ -> return XMLElement("ASTLEQ").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTLT -> return XMLElement("ASTLT").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTMultiplication -> return XMLElement("ASTMultiplication").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTNotIn -> return XMLElement("ASTNotIn").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTNot -> return XMLElement("ASTNot").addContent(parseFromASTNode(node.children[0]))
        node is ASTOr -> {
            val res = XMLElement("ASTOr")
            node.children.forEach() {
                res.addContent(parseFromASTNode(it))
            }
            return res
        }
        node is ASTBooleanLiteral -> return XMLElement("ASTBooleanLiteral").addAttribute("value", "" + node.value)
        node is ASTDecimal -> return XMLElement("ASTDecimal").addAttribute("value", "" + node.image)
        node is ASTNEQ -> return XMLElement("ASTNEQ").addContent(XMLElement("childA").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childB").addContent(parseFromASTNode(node.children[1])))
        node is ASTSet -> {
            val res = XMLElement("ASTSet")
            node.children.forEach() {
                res.addContent(parseFromASTNode(it))
            }
            return res
        }
        node is ASTSimpleLiteral -> return XMLElement("ASTSimpleLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter)
        node is ASTTriple -> return XMLElement("ASTTriple").addContent(XMLElement("childS").addContent(parseFromASTNode(node.children[0]))).addContent(XMLElement("childP").addContent(parseFromASTNode(node.children[1]))).addContent(XMLElement("childO").addContent(parseFromASTNode(node.children[2])))
        node is ASTTypedLiteral -> return XMLElement("ASTTypedLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter).addAttribute("type_iri", node.type_iri)
        node is ASTFilter -> return XMLElement("ASTFilter").addContent(parseFromASTNode(node.children[0]))
        node is ASTLanguageTaggedLiteral -> return XMLElement("ASTLanguageTaggedLiteral").addAttribute("content", node.content).addAttribute("delimiter", node.delimiter).addAttribute("language", node.language)
        node is ASTGraph -> {
            val res = XMLElement("ASTGraph")
            res.addContent(XMLElement("iriOrVar").addContent(XMLElement.parseFromASTNode(node.iriOrVar)))
            val tmp = XMLElement("constraints")
            res.addContent(tmp)
            for (c in node.children) {
                tmp.addContent(XMLElement.parseFromASTNode(c))
            }
            return res
        }
    }
    throw Exception("XMLElement.Companion.parseFromASTNode undefined :: ${classNameToString(node)}")
}
