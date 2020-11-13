package lupos.s02buildSyntaxTree.sparql1_1

import kotlin.jvm.JvmField
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator

open abstract class ASTNode(@JvmField val children: Array<ASTNode>) {
    companion object {
        private var global_uuid = 0L
    }

    val uuid: Long = global_uuid++
    override fun toString(): String {
        return toString("")
    }

    open fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        result += toString(this.children, "$indentation  ")
        return result
    }

    fun toString(nodes: Array<out ASTNode>, indentation: String): String {
        var result = ""
        for (i in 0 until nodes.size) {
            result += nodes[i].toString(indentation)
        }
        return result
    }

    open fun nodeToString(): String {
        return "classname"
    }

    protected /*inline*/ fun propertyToString(indentation2: String, indentation3: String, propertyname: String, property: Array<out ASTNode>): String {
        var result = ""
        if (property.isNotEmpty()) {
            result += "$indentation2$propertyname:\r\n"
            result += toString(property, indentation3)
        }
        return result
    }

    /*inline*/ fun <T> getChildrensValues(visitor: Visitor<T>): List<T> {
        return List(children.size) { children[it].visit(visitor) }
    }

    open fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}

open abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf(child))
open abstract class ASTUnaryOperationFixedName(child: ASTNode, @JvmField val name: String) : ASTNode(arrayOf(child)) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTBinaryOperation(left: ASTNode, right: ASTNode) : ASTNode(arrayOf(left, right))
open abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, @JvmField val name: String) : ASTNode(arrayOf(left, right)) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTNaryOperation(children: Array<ASTNode>) : ASTNode(children)
open abstract class ASTNaryOperationFixedName(children: Array<ASTNode>, @JvmField val name: String) : ASTNode(children) {
    override fun nodeToString(): String {
        return name
    }
}

open abstract class ASTLeafNode : ASTNode(arrayOf())

fun parseSPARQL(toParse: String): ASTNode {
    val lcit = LexerCharIterator(toParse)
    val tit = TokenIteratorSPARQLParser(lcit)
    val ltit = LookAheadTokenIterator(tit, 3)
    val parser = SPARQLParser(ltit)
    return parser.expr()
}
