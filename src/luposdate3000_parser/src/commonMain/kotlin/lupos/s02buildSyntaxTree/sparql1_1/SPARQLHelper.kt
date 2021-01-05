package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
public abstract class ASTNode(@JvmField public val children: Array<ASTNode>) {
internal     companion object {
        private var global_uuid = 0L
    }
public    val uuid: Long = global_uuid++
   public  override fun toString(): String {
        return toString("")
    }
public    open fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        result += toString(this.children, "$indentation  ")
        return result
    }
public    fun toString(nodes: Array<out ASTNode>, indentation: String): String {
        var result = ""
        for (element in nodes) {
            result += element.toString(indentation)
        }
        return result
    }
public    open fun nodeToString(): String {
        return "classname"
    }
    protected inline fun propertyToString(indentation2: String, indentation3: String, propertyname: String, property: Array<out ASTNode>): String {
        var result = ""
        if (property.isNotEmpty()) {
            result += "$indentation2$propertyname:\r\n"
            result += toString(property, indentation3)
        }
        return result
    }
    internal inline fun <T> getChildrensValues(visitor: Visitor<T>): List<T> {
        return List(children.size) { children[it].visit(visitor) }
    }
public    open fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf(child))
public abstract class ASTUnaryOperationFixedName(child: ASTNode, @JvmField public val name: String) : ASTNode(arrayOf(child)) {
    override fun nodeToString(): String {
        return name
    }
}
public abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, @JvmField public val name: String) : ASTNode(arrayOf(left, right)) {
    override fun nodeToString(): String {
        return name
    }
}
public abstract class ASTLeafNode : ASTNode(arrayOf())
