package lupos.s00misc
import lupos.s02buildSyntaxTree.sparql1_1.*
fun XMLElement.Companion.toASTNode(node:XMLElement): ASTNode {
when (node.tag) {
                "ASTVar" -> return ASTVar(node.attributes["name"]!!)
                "ASTAddition" -> return ASTAddition(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTAggregation"
                -> {
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

                "ASTDivision" -> return ASTDivision(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTEQ" -> return ASTEQ(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTFunctionCall" -> {
                    val childs = mutableListOf<ASTNode>()
                    node.childs.forEach {
                        childs.add(toASTNode(it))
                    }
                    return ASTFunctionCall(node.attributes["iri"]!!, node.attributes["distinct"]!!.toBoolean(), childs.toTypedArray())
                }
                "ASTGEQ" -> return ASTGEQ(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTGT" -> return ASTGT(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTInteger" -> return ASTInteger(node.attributes["value"]!!.toInt())
                "ASTIn" -> return ASTIn(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTIri" -> return ASTIri(node.attributes["iri"]!!)
                "ASTLEQ" -> return ASTLEQ(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTLT" -> return ASTLT(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTMultiplication" -> ASTMultiplication(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTNotIn" -> return ASTNotIn(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
                "ASTNot" -> return ASTNot(toASTNode(node.childs.first()!!))
                "ASTOr" -> {
                    val childs = mutableListOf<ASTNode>()
                    node.childs.forEach {
                        childs.add(toASTNode(it))
                    }
                    return ASTOr(childs.toTypedArray())
                }
"ASTBooleanLiteral"->return ASTBooleanLiteral(node.attributes["value"]!!.toBoolean())
"ASTDecimal"->return ASTDecimal(node.attributes["value"]!!)
"ASTNEQ"->return ASTNEQ(toASTNode(node["childA"]!!), toASTNode(node["childB"]!!))
"ASTSet"-> {
         val childs = mutableListOf<ASTNode>()
                    node.childs.forEach {
                        childs.add(toASTNode(it))
                    }
                    return ASTSet(childs.toTypedArray())
                }
"ASTSimpleLiteral"->return ASTSimpleLiteral(node.attributes["content"]!!,node.attributes["delimiter"]!!)
"ASTTriple"->return ASTTriple(toASTNode(node["childS"]!!), toASTNode(node["childP"]!!), toASTNode(node["childO"]!!))
"ASTTypedLiteral"->return ASTTypedLiteral(node.attributes["content"]!!,node.attributes["delimiter"]!!,node.attributes["type_iri"]!!)
"ASTFilter"->return ASTFilter(toASTNode(node.childs.first()!!))
"ASTLanguageTaggedLiteral"->return ASTLanguageTaggedLiteral(node.attributes["content"]!!,node.attributes["delimiter"]!!,node.attributes["language"]!!)
            }
            throw Exception("toASTNode for ${node.tag} undefined")
}
