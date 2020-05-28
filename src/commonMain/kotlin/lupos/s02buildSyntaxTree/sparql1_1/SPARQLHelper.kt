package lupos.s02buildSyntaxTree.sparql1_1
import kotlin.jvm.JvmField
import lupos.s00misc.classNameToString
import lupos.s00misc.Coverage
import lupos.s00misc.ThreadSafeUuid
import lupos.s02buildSyntaxTree.LexerCharIterator
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
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
open abstract class ASTNode(@JvmField val children: Array<ASTNode>) {
    companion object {
        private val global_uuid = ThreadSafeUuid()
    }
    val uuid: Long = global_uuid.next()
    override fun toString(): String {
Coverage.funStart(1819)
        return toString("")
    }
    open fun toString(indentation: String): String {
Coverage.funStart(1820)
        var result = indentation + nodeToString() + "\r\n"
Coverage.statementStart(1821)
        result += toString(this.children, indentation + "  ")
Coverage.statementStart(1822)
        return result
    }
    fun toString(nodes: Array<out ASTNode>, indentation: String): String {
Coverage.funStart(1823)
        var result = ""
Coverage.statementStart(1824)
        for (i in 0..nodes.size - 1) {
Coverage.forLoopStart(1825)
            result += nodes[i].toString(indentation)
Coverage.statementStart(1826)
        }
Coverage.statementStart(1827)
        return result
    }
    open fun nodeToString(): String {
Coverage.funStart(1828)
        return classNameToString(this)
    }
    protected inline fun propertyToString(indentation2: String, indentation3: String, propertyname: String, property: Array<out ASTNode>): String {
Coverage.funStart(1829)
        var result = ""
Coverage.statementStart(1830)
        if (property.size > 0) {
Coverage.ifStart(1831)
            result += indentation2 + propertyname + ":\r\n"
Coverage.statementStart(1832)
            result += toString(property, indentation3)
Coverage.statementStart(1833)
        }
Coverage.statementStart(1834)
        return result
    }
    inline fun <T> getChildrensValues(visitor: Visitor<T>): List<T> {
Coverage.funStart(1835)
        return List<T>(children.size) { children[it].visit(visitor) }
    }
    open fun <T> visit(visitor: Visitor<T>): T {
Coverage.funStart(1836)
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
open abstract class ASTUnaryOperation(child: ASTNode) : ASTNode(arrayOf<ASTNode>(child))
open abstract class ASTUnaryOperationFixedName(child: ASTNode, @JvmField val name: String) : ASTNode(arrayOf<ASTNode>(child)) {
    override fun nodeToString(): String {
Coverage.funStart(1837)
        return name
    }
}
open abstract class ASTBinaryOperation(left: ASTNode, right: ASTNode) : ASTNode(arrayOf<ASTNode>(left, right))
open abstract class ASTBinaryOperationFixedName(left: ASTNode, right: ASTNode, @JvmField val name: String) : ASTNode(arrayOf<ASTNode>(left, right)) {
    override fun nodeToString(): String {
Coverage.funStart(1838)
        return name
    }
}
open abstract class ASTNaryOperation(children: Array<ASTNode>) : ASTNode(children)
open abstract class ASTNaryOperationFixedName(children: Array<ASTNode>, @JvmField val name: String) : ASTNode(children) {
    override fun nodeToString(): String {
Coverage.funStart(1839)
        return name
    }
}
open abstract class ASTLeafNode : ASTNode(arrayOf<ASTNode>())
fun parseSPARQL(toParse: String): ASTNode {
Coverage.funStart(1840)
    val lcit = LexerCharIterator(toParse)
Coverage.statementStart(1841)
    val tit = TokenIteratorSPARQLParser(lcit)
Coverage.statementStart(1842)
    val ltit = LookAheadTokenIterator(tit, 3)
Coverage.statementStart(1843)
    val parser = SPARQLParser(ltit)
Coverage.statementStart(1844)
    val node = parser.expr()
Coverage.statementStart(1845)
    return node
}
