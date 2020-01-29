package lupos.s00misc

import lupos.s02buildSyntaxTree.sparql1_1.*

fun XMLElement.Companion.parseFromASTNode(node: ASTNode): XMLElement {
    when {
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
    }
    throw Exception("parseFromASTNode for ${classNameToString(node)} undefined")
}
