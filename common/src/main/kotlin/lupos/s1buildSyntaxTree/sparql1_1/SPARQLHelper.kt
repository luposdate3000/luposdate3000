package lupos.s1buildSyntaxTree.sparql1_1

import lupos.s1buildSyntaxTree.LexerCharIterator
import lupos.s1buildSyntaxTree.LookAheadTokenIterator

enum class BuiltInFunctions {
    STR,
    LANG,
    LANGMATCHES,
    DATATYPE,
    BOUND,
    IRI,
    URI,
    BNODE,
    RAND,
    ABS,
    CEIL,
    FLOOR,
    ROUND,
    CONCAT,
    SubstringExpression,
    STRLEN,
    StrReplaceExpression,
    UCASE,
    LCASE,
    ENCODE_FOR_URI,
    CONTAINS,
    STRSTARTS,
    STRENDS,
    STRBEFORE,
    STRAFTER,
    YEAR,
    MONTH,
    DAY,
    HOURS,
    MINUTES,
    SECONDS,
    TIMEZONE,
    TZ,
    NOW,
    UUID,
    STRUUID,
    MD5,
    SHA1,
    SHA256,
    SHA384,
    SHA512,
    COALESCE,
    IF,
    STRLANG,
    STRDT,
    sameTerm,
    isIRI,
    isURI,
    isBLANK,
    isLITERAL,
    isNUMERIC,
    RegexExpression,
    Exists,
    NotExists
}

enum class Aggregation {
    COUNT, SUM, MIN, MAX, AVG, SAMPLE, GROUP_CONCAT
}

open abstract class ASTNode(val children: Array<ASTNode>) {
    override fun toString(): String {
        return toString("")
    }

    open fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        result += toString(this.children, indentation + "  ")
        return result
    }

    fun toString(nodes: Array<out ASTNode>, indentation: String): String {
        var result = ""
        for (i in 0..nodes.size - 1) {
            result += nodes[i].toString(indentation)
        }
        return result
    }

    open fun nodeToString(): String {
        return this::class.simpleName ?: ""
    }

    protected inline fun propertyToString(indentation2: String, indentation3: String, propertyname: String, property: Array<out ASTNode>): String {
        var result = ""
        if (property.size > 0) {
            result += indentation2 + propertyname + ":\r\n"
            result += toString(property, indentation3)
        }
        return result
    }

    inline fun <T> getChildrensValues(visitor: Visitor<T>): List<T> {
        return List<T>(children.size) { children[it].visit(visitor) }
    }

    open fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}

open abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf<ASTNode>(child))
open abstract class ASTUnaryOperationFixedName(child: ASTNode, val name: String) : ASTNode(arrayOf<ASTNode>(child)) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTBinaryOperation(left: ASTNode, right: ASTNode) : ASTNode(arrayOf<ASTNode>(left, right))
open abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, val name: String) : ASTNode(arrayOf<ASTNode>(left, right)) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTNaryOperation(children: Array<ASTNode>) : ASTNode(children)
open abstract class ASTNaryOperationFixedName(children: Array<ASTNode>, val name: String) : ASTNode(children) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTLeafNode : ASTNode(arrayOf<ASTNode>())

fun parseSPARQL(toParse: String): ASTNode {
    val lcit = LexerCharIterator(toParse)
    val tit = TokenIteratorSPARQLParser(lcit)
    val ltit = LookAheadTokenIterator(tit, 3)
    val parser = SPARQLParser(ltit)
    val node = parser.expr()
    return node
}