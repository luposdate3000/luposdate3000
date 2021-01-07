package lupos.s02buildSyntaxTree.sparql1_1
import lupos.s02buildSyntaxTree.LookAheadTokenIterator
import lupos.s02buildSyntaxTree.ParseError
import lupos.s02buildSyntaxTree.Token
import lupos.s02buildSyntaxTree.UnexpectedToken
import kotlin.jvm.JvmField
public interface Visitor<T> {
    public fun visit(node: ASTNode, childrenValues: List<T>): T
    public fun visit(node: ASTBase, childrenValues: List<T>): T
    public fun visit(node: ASTPrefix, childrenValues: List<T>): T
    public fun visit(node: ASTQuery, childrenValues: List<T>): T
    public fun visit(node: ASTValues, childrenValues: List<T>): T
    public fun visit(node: ASTValue, childrenValues: List<T>): T
    public fun visit(node: ASTUndef, childrenValues: List<T>): T
    public fun visit(node: ASTQueryBaseClass, childrenValues: List<T>): T
    public fun visit(node: ASTSelectQuery, childrenValues: List<T>): T
    public fun visit(node: ASTSubSelectQuery, childrenValues: List<T>): T
    public fun visit(node: ASTConstructQuery, childrenValues: List<T>): T
    public fun visit(node: ASTDescribeQuery, childrenValues: List<T>): T
    public fun visit(node: ASTAskQuery, childrenValues: List<T>): T
    public fun visit(node: ASTAs, childrenValues: List<T>): T
    public fun visit(node: ASTDatasetClause, childrenValues: List<T>): T
    public fun visit(node: ASTDefaultGraph, childrenValues: List<T>): T
    public fun visit(node: ASTNamedGraph, childrenValues: List<T>): T
    public fun visit(node: ASTOrderCondition, childrenValues: List<T>): T
    public fun visit(node: ASTVar, childrenValues: List<T>): T
    public fun visit(node: ASTRDFTerm, childrenValues: List<T>): T
    public fun visit(node: ASTLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTSimpleLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTTypedLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTLanguageTaggedLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTIri, childrenValues: List<T>): T
    public fun visit(node: ASTBlankNode, childrenValues: List<T>): T
    public fun visit(node: ASTBooleanLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTNumericLiteral, childrenValues: List<T>): T
    public fun visit(node: ASTInteger, childrenValues: List<T>): T
    public fun visit(node: ASTDouble, childrenValues: List<T>): T
    public fun visit(node: ASTDecimal, childrenValues: List<T>): T
    public fun visit(node: ASTFunctionCall, childrenValues: List<T>): T
    public fun visit(node: ASTTriple, childrenValues: List<T>): T
    public fun visit(node: ASTGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTIriGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTNamedIriGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTDefaultGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTNamedGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTAllGraphRef, childrenValues: List<T>): T
    public fun visit(node: ASTLoad, childrenValues: List<T>): T
    public fun visit(node: ASTGrapOperation, childrenValues: List<T>): T
    public fun visit(node: ASTClear, childrenValues: List<T>): T
    public fun visit(node: ASTDrop, childrenValues: List<T>): T
    public fun visit(node: ASTCreate, childrenValues: List<T>): T
    public fun visit(node: ASTUpdateGrapOperation, childrenValues: List<T>): T
    public fun visit(node: ASTAdd, childrenValues: List<T>): T
    public fun visit(node: ASTMove, childrenValues: List<T>): T
    public fun visit(node: ASTCopy, childrenValues: List<T>): T
    public fun visit(node: ASTGraph, childrenValues: List<T>): T
    public fun visit(node: ASTService, childrenValues: List<T>): T
    public fun visit(node: ASTModify, childrenValues: List<T>): T
    public fun visit(node: ASTDeleteData, childrenValues: List<T>): T
    public fun visit(node: ASTDeleteWhere, childrenValues: List<T>): T
    public fun visit(node: ASTInsertData, childrenValues: List<T>): T
    public fun visit(node: ASTModifyWithWhere, childrenValues: List<T>): T
    public fun visit(node: ASTPathAlternatives, childrenValues: List<T>): T
    public fun visit(node: ASTPathSequence, childrenValues: List<T>): T
    public fun visit(node: ASTPathInverse, childrenValues: List<T>): T
    public fun visit(node: ASTPathArbitraryOccurrences, childrenValues: List<T>): T
    public fun visit(node: ASTPathOptionalOccurrence, childrenValues: List<T>): T
    public fun visit(node: ASTPathArbitraryOccurrencesNotZero, childrenValues: List<T>): T
    public fun visit(node: ASTPathNegatedPropertySet, childrenValues: List<T>): T
    public fun visit(node: ASTOptional, childrenValues: List<T>): T
    public fun visit(node: ASTMinusGroup, childrenValues: List<T>): T
    public fun visit(node: ASTUnion, childrenValues: List<T>): T
    public fun visit(node: ASTGroup, childrenValues: List<T>): T
    public fun visit(node: ASTFilter, childrenValues: List<T>): T
    public fun visit(node: ASTSet, childrenValues: List<T>): T
    public fun visit(node: ASTOr, childrenValues: List<T>): T
    public fun visit(node: ASTAnd, childrenValues: List<T>): T
    public fun visit(node: ASTEQ, childrenValues: List<T>): T
    public fun visit(node: ASTNEQ, childrenValues: List<T>): T
    public fun visit(node: ASTLEQ, childrenValues: List<T>): T
    public fun visit(node: ASTGEQ, childrenValues: List<T>): T
    public fun visit(node: ASTLT, childrenValues: List<T>): T
    public fun visit(node: ASTGT, childrenValues: List<T>): T
    public fun visit(node: ASTIn, childrenValues: List<T>): T
    public fun visit(node: ASTNotIn, childrenValues: List<T>): T
    public fun visit(node: ASTAddition, childrenValues: List<T>): T
    public fun visit(node: ASTSubtraction, childrenValues: List<T>): T
    public fun visit(node: ASTMultiplication, childrenValues: List<T>): T
    public fun visit(node: ASTDivision, childrenValues: List<T>): T
    public fun visit(node: ASTNot, childrenValues: List<T>): T
    public fun visit(node: ASTPlus, childrenValues: List<T>): T
    public fun visit(node: ASTMinus, childrenValues: List<T>): T
    public fun visit(node: ASTBuiltInCall, childrenValues: List<T>): T
    public fun visit(node: ASTAggregation, childrenValues: List<T>): T
    public fun visit(node: ASTGroupConcat, childrenValues: List<T>): T
}
public class ASTBase(@JvmField public val iri: String) : ASTLeafNode() {
    public override fun nodeToString(): String = "BASE <$iri>"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPrefix(@JvmField public val name: String, @JvmField public val iri: String) : ASTLeafNode() {
    public override fun nodeToString(): String = "PREFIX $name: <$iri>"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTQuery(children: Array<ASTNode>) : ASTNode(children) {
    public constructor(children: List<ASTNode>) : this(children.toTypedArray())
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTValues(@JvmField public val variables: Array<ASTVar>, children: Array<ASTNode>) : ASTNode(children) {
    public constructor(variable: ASTVar, children: List<ASTNode>) : this(arrayOf(variable), children.toTypedArray())
    public constructor(variables: List<ASTVar>, children: List<ASTNode>) : this(variables.toTypedArray(), children.toTypedArray())
    public override fun nodeToString(): String = "Values for variables: " + variables.joinToString(separator = ", ") { it.name }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTValue(children: Array<ASTNode>) : ASTNode(children) {
    public constructor(children: List<ASTNode>) : this(children.toTypedArray())
    public constructor(child: ASTNode) : this(arrayOf(child))
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTUndef : ASTLeafNode() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTQueryBaseClass : ASTLeafNode() {
    @JvmField
    public var datasets: Array<ASTDatasetClause> = arrayOf()
    public fun existsDatasets(): Boolean = datasets.isNotEmpty()
    @JvmField
    public var where: Array<ASTNode> = arrayOf()
    @JvmField
    public var groupBy: Array<ASTNode> = arrayOf()
    public fun existsGroupBy(): Boolean = groupBy.isNotEmpty()
    @JvmField
    public var having: Array<ASTNode> = arrayOf()
    public fun existsHaving(): Boolean = having.isNotEmpty()
    @JvmField
    public var orderBy: Array<ASTNode> = arrayOf()
    public fun existsOrderBy(): Boolean = orderBy.isNotEmpty()
    @JvmField
    public var limit: Int = -1
    public fun existsLimit(): Boolean = limit >= 0
    @JvmField
    public var offset: Int = 0
    public fun existsOffset(): Boolean = offset > 0
    public override fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        val indentation2 = "$indentation  "
        val indentation3 = "$indentation2  "
        result += propertyToString(indentation2, indentation3, "Datasets", this.datasets)
        result += propertyToString(indentation2, indentation3, "Where", this.where)
        result += propertyToString(indentation2, indentation3, "Group By", this.groupBy)
        result += propertyToString(indentation2, indentation3, "Having", this.having)
        result += propertyToString(indentation2, indentation3, "Order By", this.orderBy)
        if (existsLimit()) {
            result += indentation2 + "LIMIT: " + limit + "\r\n"
        }
        if (existsOffset()) {
            result += indentation2 + "OFFSET: " + offset + "\r\n"
        }
        return result
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTSelectQuery(@JvmField public val distinct: Boolean, @JvmField public val reduced: Boolean, @JvmField public val select: Array<ASTNode>) : ASTQueryBaseClass() {
    public fun selectAll(): Boolean = (select.isEmpty())
    public override fun nodeToString(): String = "ASTSelectQuery" + innerNodeToString()
    @Suppress("NOTHING_TO_INLINE") protected inline fun innerNodeToString(): String = (if (distinct) " DISTINCT" else "") + (if (reduced) " REDUCED " else "") + (if (selectAll()) " *" else "")
    public override fun toString(indentation: String): String = super.toString(indentation) + propertyToString("$indentation  ", "$indentation    ", "Select", this.select)
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTSubSelectQuery(distinct: Boolean, reduced: Boolean, select: Array<ASTNode>) : ASTSelectQuery(distinct, reduced, select) {
    @JvmField
    public var values: ASTValues? = null
    public fun existsValues(): Boolean = (values != null)
    public override fun nodeToString(): String = "ASTSubSelectQuery" + innerNodeToString()
    public override fun toString(indentation: String): String = super.toString(indentation) + (if (this.values == null) "" else this.values?.toString("$indentation  "))
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTConstructQuery(@JvmField public val template: Array<ASTNode>) : ASTQueryBaseClass() {
    public override fun toString(indentation: String): String = super.toString(indentation) + propertyToString("$indentation  ", "$indentation    ", "Template", this.template)
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDescribeQuery(@JvmField public val select: Array<ASTNode>) : ASTQueryBaseClass() {
    @Suppress("NOTHING_TO_INLINE") private inline fun selectAll(): Boolean {
        return select.isEmpty()
    }
    public override fun nodeToString(): String = "ASTSelectQuery" + (if (selectAll()) " *" else "")
    public override fun toString(indentation: String): String = super.toString(indentation) + propertyToString("$indentation  ", "$indentation    ", "Select", this.select)
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAskQuery : ASTQueryBaseClass() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAs(@JvmField public val expression: ASTNode, @JvmField public val variable: ASTVar) : ASTLeafNode() {
    public override fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        val indentation2 = "$indentation  "
        val indentation3 = "$indentation2  "
        result += indentation2 + "Bind:\r\n"
        result += expression.toString(indentation3)
        result += indentation2 + "As:\r\n"
        result += variable.toString(indentation3)
        return result
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTDatasetClause(@JvmField public val source_iri: String) : ASTLeafNode() {
    public constructor(iri: ASTIri) : this(iri.iri)
    public override fun nodeToString(): String = super.nodeToString() + " <" + source_iri + ">"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDefaultGraph(source_iri: ASTIri) : ASTDatasetClause(source_iri) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNamedGraph(source_iri: ASTIri) : ASTDatasetClause(source_iri) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTOrderCondition(@JvmField public val asc: Boolean, child: ASTNode) : ASTUnaryOperation(child) {
    public override fun toString(indentation: String): String = indentation + nodeToString() + " " + (if (asc) "ASC" else "DESC") + "\r\n" + children[0].toString("$indentation  ")
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTVar(@JvmField public val name: String) : ASTLeafNode() {
    public override fun nodeToString(): String = super.nodeToString() + " " + name
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTRDFTerm : ASTLeafNode() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTLiteral(@JvmField public val content: String, @JvmField public val delimiter: String) : ASTRDFTerm() {
    public override fun nodeToString(): String = delimiter + content + delimiter
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTSimpleLiteral(content: String, delimiter: String) : ASTLiteral(content, delimiter) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTTypedLiteral(content: String, delimiter: String, @JvmField public val type_iri: String) : ASTLiteral(content, delimiter) {
    public override fun nodeToString(): String = super.nodeToString() + "^^<" + type_iri + ">"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTLanguageTaggedLiteral(content: String, delimiter: String, @JvmField public val language: String) : ASTLiteral(content, delimiter) {
    public override fun nodeToString(): String = super.nodeToString() + "@" + language
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTIri(@JvmField public val iri: String) : ASTRDFTerm() {
    public override fun nodeToString(): String = "<$iri>"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTBlankNode(@JvmField public val name: String) : ASTRDFTerm() {
    public constructor() : this(getNewName())
    public override fun nodeToString(): String = "_:$name"
    private companion object {
        var label_index = 0L
        fun getNewName(): String {
            return "_" + label_index++
        }
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTBooleanLiteral(@JvmField public val value: Boolean) : ASTRDFTerm() {
    public override fun nodeToString(): String = if (value) "true" else "false"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTNumericLiteral : ASTRDFTerm() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTInteger(@JvmField public val value: Int) : ASTNumericLiteral() {
    public constructor(image: String) : this(image.toInt())
    public override fun nodeToString(): String = "" + value
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDouble(@JvmField public val image: String) : ASTNumericLiteral() {
    public fun toDouble(): Double = image.toDouble()
    public override fun nodeToString(): String = image
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDecimal(@JvmField public val image: String) : ASTNumericLiteral() {
    public fun toDouble(): Double = image.toDouble()
    public override fun nodeToString(): String = image
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTFunctionCall(@JvmField public val iri: String, @JvmField public val distinct: Boolean, arguments: Array<ASTNode>) : ASTNode(arguments) {
    public override fun nodeToString(): String = "ASTFunctionCall <" + iri + ">" + (if (distinct) "DISTINCT" else "")
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTTriple(s: ASTNode, p: ASTNode, o: ASTNode) : ASTNode(arrayOf(s, p, o)) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTGraphRef : ASTLeafNode() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTIriGraphRef(@JvmField public val iri: String) : ASTGraphRef() {
    public override fun nodeToString(): String = super.nodeToString() + " <" + iri + ">"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNamedIriGraphRef(@JvmField public val iri: String) : ASTGraphRef() {
    public override fun nodeToString(): String = super.nodeToString() + " <" + iri + ">"
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDefaultGraphRef : ASTGraphRef() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNamedGraphRef : ASTGraphRef() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAllGraphRef : ASTGraphRef() {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTLoad(@JvmField public val silent: Boolean, @JvmField public val iri: String, @JvmField public val into: ASTGraphRef?) : ASTLeafNode() {
    public override fun nodeToString(): String = super.nodeToString() + (if (silent) " SILENT" else "") + " <" + iri + ">"
    public override fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        if (into != null) {
            result += indentation + "  INTO:\r\n" + into.toString("$indentation    ")
        }
        return result
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTGrapOperation(@JvmField public val silent: Boolean, @JvmField public val graphref: ASTGraphRef) : ASTLeafNode() {
    public override fun nodeToString(): String = super.nodeToString() + (if (silent) " SILENT" else "")
    public override fun toString(indentation: String): String = indentation + nodeToString() + "\r\n" + graphref.toString("$indentation  ")
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTClear(silent: Boolean, graphref: ASTGraphRef) : ASTGrapOperation(silent, graphref) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDrop(silent: Boolean, graphref: ASTGraphRef) : ASTGrapOperation(silent, graphref) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTCreate(silent: Boolean, graphref: ASTGraphRef) : ASTGrapOperation(silent, graphref) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTUpdateGrapOperation(@JvmField public val silent: Boolean, @JvmField public val fromGraph: ASTGraphRef, @JvmField public val toGraph: ASTGraphRef) : ASTLeafNode() {
    public override fun nodeToString(): String = super.nodeToString() + (if (silent) " SILENT" else "")
    public override fun toString(indentation: String): String {
        var result = indentation + nodeToString() + "\r\n"
        val indentation2 = "$indentation  "
        val indentation3 = "$indentation2  "
        result += indentation2 + "FROM:\r\n" + fromGraph.toString(indentation3)
        result += indentation2 + "TO:\r\n" + toGraph.toString(indentation3)
        return result
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAdd(silent: Boolean, fromGraph: ASTGraphRef, toGraph: ASTGraphRef) : ASTUpdateGrapOperation(silent, fromGraph, toGraph) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTMove(silent: Boolean, fromGraph: ASTGraphRef, toGraph: ASTGraphRef) : ASTUpdateGrapOperation(silent, fromGraph, toGraph) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTCopy(silent: Boolean, fromGraph: ASTGraphRef, toGraph: ASTGraphRef) : ASTUpdateGrapOperation(silent, fromGraph, toGraph) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTGraph(@JvmField public val iriOrVar: ASTNode, constraint: Array<ASTNode>) : ASTNode(constraint) {
    public override fun nodeToString(): String = super.nodeToString() + " " + iriOrVar.nodeToString()
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTService(@JvmField public val silent: Boolean, @JvmField public val iriOrVar: ASTNode, constraint: Array<ASTNode>) : ASTNode(constraint) {
    public override fun nodeToString(): String = super.nodeToString() + (if (silent) "SILENT" else "") + " " + iriOrVar.nodeToString()
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTModify(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDeleteData(data: Array<ASTNode>) : ASTModify(data) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDeleteWhere(pattern: Array<ASTNode>) : ASTModify(pattern) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTInsertData(data: Array<ASTNode>) : ASTModify(data) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTModifyWithWhere(@JvmField public val iri: String?, @JvmField public val delete: Array<ASTNode>, @JvmField public val insert: Array<ASTNode>, @JvmField public val using: Array<ASTGraphRef>, where: Array<ASTNode>) : ASTModify(where) {
    public override fun toString(indentation: String): String {
        var result = indentation + nodeToString() + (if (iri != null) " <$iri>" else "") + "\r\n"
        val indentation2 = "$indentation  "
        val indentation3 = "$indentation2  "
        result += propertyToString(indentation2, indentation3, "Delete", this.delete)
        result += propertyToString(indentation2, indentation3, "Insert", this.insert)
        result += propertyToString(indentation2, indentation3, "Using", this.using)
        result += propertyToString(indentation2, indentation3, "WHERE", this.children)
        return result
    }
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathAlternatives(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathSequence(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathInverse(child: ASTNode) : ASTUnaryOperation(child) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathArbitraryOccurrences(child: ASTNode) : ASTUnaryOperation(child) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathOptionalOccurrence(child: ASTNode) : ASTUnaryOperation(child) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathArbitraryOccurrencesNotZero(child: ASTNode) : ASTUnaryOperation(child) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPathNegatedPropertySet(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTOptional(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTMinusGroup(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTUnion(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTGroup(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTFilter(child: ASTNode) : ASTUnaryOperation(child) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTSet(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTOr(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAnd(children: Array<ASTNode>) : ASTNode(children) {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTEQ(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "=") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNEQ(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "!=") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTLEQ(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "<=") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTGEQ(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, ">=") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTLT(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "<") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTGT(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, ">") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTIn(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "In") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNotIn(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "Not In") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTAddition(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "+") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTSubtraction(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "-") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTMultiplication(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "*") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTDivision(left: ASTNode, right: ASTNode) : ASTBinaryOperationFixedName(left, right, "/") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTNot(child: ASTNode) : ASTUnaryOperationFixedName(child, "!") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTPlus(child: ASTNode) : ASTUnaryOperationFixedName(child, "+") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTMinus(child: ASTNode) : ASTUnaryOperationFixedName(child, "-") {
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTBuiltInCall(@JvmField public val function: BuiltInFunctions, children: Array<ASTNode>) : ASTNode(children) {
    public constructor(function: BuiltInFunctions) : this(function, arrayOf<ASTNode>())
    public constructor(function: BuiltInFunctions, parameter: ASTNode) : this(function, arrayOf<ASTNode>(parameter))
    public constructor(function: BuiltInFunctions, first_parameter: ASTNode, second_parameter: ASTNode) : this(function, arrayOf<ASTNode>(first_parameter, second_parameter))
    public constructor(function: BuiltInFunctions, first_parameter: ASTNode, second_parameter: ASTNode, third_parameter: ASTNode) : this(function, arrayOf<ASTNode>(first_parameter, second_parameter, third_parameter))
    public override fun nodeToString(): String = function.name
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public open class ASTAggregation(@JvmField public val type: Aggregation, @JvmField public val distinct: Boolean, children: Array<ASTNode>) : ASTNode(children) {
    public constructor(type: Aggregation, distinct: Boolean, child: ASTNode) : this(type, distinct, arrayOf<ASTNode>(child))
    public override fun nodeToString(): String = type.name + (if (distinct) " DISTINCT" else "")
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class ASTGroupConcat(distinct: Boolean, child: ASTNode, @JvmField public val separator: String) : ASTAggregation(Aggregation.GROUP_CONCAT, distinct, child) {
    public override fun nodeToString(): String = super.nodeToString() + " separator=\"" + separator + "\""
    public override fun <T> visit(visitor: Visitor<T>): T {
        return visitor.visit(this, this.getChildrensValues(visitor))
    }
}
public class SPARQLParser(@JvmField public val ltit: LookAheadTokenIterator) {
    // for storing the prefixes...
    private val prefixes = mutableMapOf<String, String>()
    // some constants used for typed literals
    private val rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#"
    private val nil = rdf + "nil"
    private val first = rdf + "first"
    private val rest = rdf + "rest"
    private val type = rdf + "type"
    private val ASTNil = ASTIri(nil)
    private val ASTFirst = ASTIri(first)
    private val ASTRest = ASTIri(rest)
    private val ASTType = ASTIri(type)
    public fun expr(): ASTNode {
        val result = QueryOrUpdate()
        val token: Token = ltit.nextToken()
        if (token !is EOF) {
            throw UnexpectedToken(token, arrayOf("EOF"), ltit)
        }
        return result
    }
    private fun QueryOrUpdate(): ASTQuery {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val t1 = ltit.lookahead()
        if (t1.image == "BASE" || t1.image == "PREFIX") {
            val list = Prologue()
            collect.addAll(list)
        }
        val t7 = ltit.lookahead()
        if (t7.image == "SELECT" || t7.image == "CONSTRUCT" || t7.image == "DESCRIBE" || t7.image == "ASK" || t7.image == "LOAD" || t7.image == "CLEAR" || t7.image == "DROP" || t7.image == "ADD" || t7.image == "MOVE" || t7.image == "COPY" || t7.image == "CREATE" || t7.image == "WITH" || t7.image == "DELETE" || t7.image == "INSERT") {
            val t6 = ltit.lookahead()
            when (t6.image) {
                "SELECT", "CONSTRUCT", "DESCRIBE", "ASK" -> {
                    val t2 = ltit.lookahead()
                    when (t2.image) {
                        "SELECT" -> {
                            val select = SelectQuery()
                            collect.add(select)
                        }
                        "CONSTRUCT" -> {
                            val construct = ConstructQuery()
                            collect.add(construct)
                        }
                        "DESCRIBE" -> {
                            val describe = DescribeQuery()
                            collect.add(describe)
                        }
                        "ASK" -> {
                            val ask = AskQuery()
                            collect.add(ask)
                        }
                        else -> {
                            throw UnexpectedToken(t2, arrayOf("SELECT", "CONSTRUCT", "DESCRIBE", "ASK"), ltit)
                        }
                    }
                    val t3 = ltit.lookahead()
                    if (t3.image == "VALUES") {
                        val value = ValuesClause()
                        collect.add(value)
                    }
                }
                "LOAD", "CLEAR", "DROP", "ADD", "MOVE", "COPY", "CREATE", "WITH", "DELETE", "INSERT" -> {
                    val update = Update1()
                    collect.add(update)
                    var t5 = ltit.lookahead()
                    while (t5.image == ";") {
                        token = ltit.nextToken()
                        if (token.image != ";") {
                            throw UnexpectedToken(token, arrayOf(";"), ltit)
                        }
                        val t4 = ltit.lookahead()
                        if (t4.image == "LOAD" || t4.image == "CLEAR" || t4.image == "DROP" || t4.image == "ADD" || t4.image == "MOVE" || t4.image == "COPY" || t4.image == "CREATE" || t4.image == "WITH" || t4.image == "DELETE" || t4.image == "INSERT") {
                            val update2 = Update1()
                            collect.add(update2)
                        }
                        t5 = ltit.lookahead()
                    }
                }
                else -> {
                    throw UnexpectedToken(t6, arrayOf("SELECT", "CONSTRUCT", "DESCRIBE", "ASK", "LOAD", "CLEAR", "DROP", "ADD", "MOVE", "COPY", "CREATE", "WITH", "DELETE", "INSERT"), ltit)
                }
            }
        }
        return ASTQuery(collect)
    }
    public fun Query(): ASTQuery {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val t8 = ltit.lookahead()
        if (t8.image == "BASE" || t8.image == "PREFIX") {
            val list = Prologue()
            collect.addAll(list)
        }
        val t9 = ltit.lookahead()
        when (t9.image) {
            "SELECT" -> {
                val select = SelectQuery()
                collect.add(select)
            }
            "CONSTRUCT" -> {
                val construct = ConstructQuery()
                collect.add(construct)
            }
            "DESCRIBE" -> {
                val describe = DescribeQuery()
                collect.add(describe)
            }
            "ASK" -> {
                val ask = AskQuery()
                collect.add(ask)
            }
            else -> {
                throw UnexpectedToken(t9, arrayOf("SELECT", "CONSTRUCT", "DESCRIBE", "ASK"), ltit)
            }
        }
        val t10 = ltit.lookahead()
        if (t10.image == "VALUES") {
            val value = ValuesClause()
            collect.add(value)
        }
        return ASTQuery(collect)
    }
    private fun Prologue(): MutableList<ASTNode> {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        do {
            val t11 = ltit.lookahead()
            when (t11.image) {
                "BASE" -> {
                    val base = BaseDecl()
                    collect.add(base)
                }
                "PREFIX" -> {
                    val prefix = PrefixDecl()
                    collect.add(prefix)
                }
                else -> {
                    throw UnexpectedToken(t11, arrayOf("BASE", "PREFIX"), ltit)
                }
            }
            val t12 = ltit.lookahead()
        } while (t12.image == "BASE" || t12.image == "PREFIX")
        return collect
    }
    private fun BaseDecl(): ASTBase {
        var token: Token = ltit.nextToken()
        if (token.image != "BASE") {
            throw UnexpectedToken(token, arrayOf("BASE"), ltit)
        }
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[""] = token.content; return ASTBase(token.content)
    }
    private fun PrefixDecl(): ASTPrefix {
        var token: Token = ltit.nextToken()
        if (token.image != "PREFIX") {
            throw UnexpectedToken(token, arrayOf("PREFIX"), ltit)
        }
        token = ltit.nextToken()
        if (token !is PNAME_NS) {
            throw UnexpectedToken(token, arrayOf("PNAME_NS"), ltit)
        }
        val key = token.beforeColon
        token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        prefixes[key] = token.content; return ASTPrefix(key, token.content)
    }
    private fun SelectQuery(): ASTSelectQuery {
        var token: Token
        val select = SelectClause()
        val collect: MutableList<ASTDatasetClause> = mutableListOf()
        var t13 = ltit.lookahead()
        while (t13.image == "FROM") {
            val dataset = DatasetClause()
            collect.add(dataset)
            t13 = ltit.lookahead()
        }
        select.datasets = collect.toTypedArray()
        WhereClause(select)
        SolutionModifier(select)
        return select
    }
    private fun SubSelect(): ASTSelectQuery {
        var token: Token
        val select = SubSelectClause()
        WhereClause(select)
        SolutionModifier(select)
        val t14 = ltit.lookahead()
        if (t14.image == "VALUES") {
            val values = ValuesClause()
            select.values = values
        }
        return select
    }
    private fun SelectClause(): ASTSelectQuery {
        var token: Token
        var distinct = false
        var reduced = false
        token = ltit.nextToken()
        if (token.image != "SELECT") {
            throw UnexpectedToken(token, arrayOf("SELECT"), ltit)
        }
        val t16 = ltit.lookahead()
        if (t16.image == "DISTINCT" || t16.image == "REDUCED") {
            val t15 = ltit.lookahead()
            when (t15.image) {
                "DISTINCT" -> {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                "REDUCED" -> {
                    token = ltit.nextToken()
                    if (token.image != "REDUCED") {
                        throw UnexpectedToken(token, arrayOf("REDUCED"), ltit)
                    }
                    reduced = true
                }
                else -> {
                    throw UnexpectedToken(t15, arrayOf("DISTINCT", "REDUCED"), ltit)
                }
            }
        }
        val collect: MutableList<ASTNode> = mutableListOf()
        val t19 = ltit.lookahead()
        when {
            t19 is VAR || t19.image == "(" -> {
                do {
                    val t17 = ltit.lookahead()
                    when {
                        t17 is VAR -> {
                            val variable = Var()
                            collect.add(variable)
                        }
                        t17.image == "(" -> {
                            val asTerm = As()
                            collect.add(asTerm)
                        }
                        else -> {
                            throw UnexpectedToken(t17, arrayOf("VAR", "("), ltit)
                        }
                    }
                    val t18 = ltit.lookahead()
                } while (t18 is VAR || t18.image == "(")
            }
            t19.image == "*" -> {
                token = ltit.nextToken()
                if (token.image != "*") {
                    throw UnexpectedToken(token, arrayOf("*"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t19, arrayOf("VAR", "(", "*"), ltit)
            }
        }
        return ASTSelectQuery(distinct, reduced, collect.toTypedArray())
    }
    private fun SubSelectClause(): ASTSubSelectQuery {
        var token: Token
        var distinct = false
        var reduced = false
        token = ltit.nextToken()
        if (token.image != "SELECT") {
            throw UnexpectedToken(token, arrayOf("SELECT"), ltit)
        }
        val t21 = ltit.lookahead()
        if (t21.image == "DISTINCT" || t21.image == "REDUCED") {
            val t20 = ltit.lookahead()
            when (t20.image) {
                "DISTINCT" -> {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                "REDUCED" -> {
                    token = ltit.nextToken()
                    if (token.image != "REDUCED") {
                        throw UnexpectedToken(token, arrayOf("REDUCED"), ltit)
                    }
                    reduced = true
                }
                else -> {
                    throw UnexpectedToken(t20, arrayOf("DISTINCT", "REDUCED"), ltit)
                }
            }
        }
        val collect: MutableList<ASTNode> = mutableListOf()
        val t24 = ltit.lookahead()
        when {
            t24 is VAR || t24.image == "(" -> {
                do {
                    val t22 = ltit.lookahead()
                    when {
                        t22 is VAR -> {
                            val variable = Var()
                            collect.add(variable)
                        }
                        t22.image == "(" -> {
                            val asTerm = As()
                            collect.add(asTerm)
                        }
                        else -> {
                            throw UnexpectedToken(t22, arrayOf("VAR", "("), ltit)
                        }
                    }
                    val t23 = ltit.lookahead()
                } while (t23 is VAR || t23.image == "(")
            }
            t24.image == "*" -> {
                token = ltit.nextToken()
                if (token.image != "*") {
                    throw UnexpectedToken(token, arrayOf("*"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t24, arrayOf("VAR", "(", "*"), ltit)
            }
        }
        return ASTSubSelectQuery(distinct, reduced, collect.toTypedArray())
    }
    private fun As(): ASTAs {
        var token: Token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val expr = Expression()
        token = ltit.nextToken()
        if (token.image != "AS") {
            throw UnexpectedToken(token, arrayOf("AS"), ltit)
        }
        val variable = Var()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTAs(expr, variable)
    }
    private fun ConstructQuery(): ASTConstructQuery {
        val construct: ASTConstructQuery
        val collect: MutableList<ASTDatasetClause> = mutableListOf()
        val token: Token = ltit.nextToken()
        if (token.image != "CONSTRUCT") {
            throw UnexpectedToken(token, arrayOf("CONSTRUCT"), ltit)
        }
        val t27 = ltit.lookahead()
        when (t27.image) {
            "{" -> {
                val template = ConstructTemplate()
                var t25 = ltit.lookahead()
                while (t25.image == "FROM") {
                    val dataset = DatasetClause()
                    collect.add(dataset)
                    t25 = ltit.lookahead()
                }
                construct = ASTConstructQuery(template)
                construct.datasets = collect.toTypedArray()
                WhereClause(construct)
            }
            "WHERE", "FROM" -> {
                var t26 = ltit.lookahead()
                while (t26.image == "FROM") {
                    val dataset = DatasetClause()
                    collect.add(dataset)
                    t26 = ltit.lookahead()
                }
                val templateAndWhere = ConstructQueryWherePart()
                construct = ASTConstructQuery(templateAndWhere)
                construct.where = templateAndWhere
                construct.datasets = collect.toTypedArray()
            }
            else -> {
                throw UnexpectedToken(t27, arrayOf("{", "WHERE", "FROM"), ltit)
            }
        }
        SolutionModifier(construct)
        return construct
    }
    private fun ConstructQueryWherePart(): Array<ASTNode> {
        var result = arrayOf<ASTNode>()
        var token: Token = ltit.nextToken()
        if (token.image != "WHERE") {
            throw UnexpectedToken(token, arrayOf("WHERE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val t28 = ltit.lookahead()
        if (t28.image == "(" || t28.image == "[" || t28 is VAR || t28 is IRI || t28 is PNAME_LN || t28 is PNAME_NS || t28 is STRING || t28 is INTEGER || t28 is DECIMAL || t28 is DOUBLE || t28.image == "+" || t28.image == "-" || t28.image == "TRUE" || t28.image == "FALSE" || t28 is BNODE || t28 is ANON_BNODE || t28 is NIL) {
            result = TriplesTemplate()
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return result
    }
    private fun DescribeQuery(): ASTDescribeQuery {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "DESCRIBE") {
            throw UnexpectedToken(token, arrayOf("DESCRIBE"), ltit)
        }
        val vois: MutableList<ASTNode> = mutableListOf()
        val t30 = ltit.lookahead()
        when {
            t30 is VAR || t30 is IRI || t30 is PNAME_LN || t30 is PNAME_NS -> {
                do {
                    val voi = VarOrIRIref()
                    vois.add(voi)
                    val t29 = ltit.lookahead()
                } while (t29 is VAR || t29 is IRI || t29 is PNAME_LN || t29 is PNAME_NS)
            }
            t30.image == "*" -> {
                token = ltit.nextToken()
                if (token.image != "*") {
                    throw UnexpectedToken(token, arrayOf("*"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t30, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS", "*"), ltit)
            }
        }
        val describe = ASTDescribeQuery(vois.toTypedArray())
        val collect: MutableList<ASTDatasetClause> = mutableListOf()
        var t31 = ltit.lookahead()
        while (t31.image == "FROM") {
            val dataset = DatasetClause()
            collect.add(dataset)
            t31 = ltit.lookahead()
        }
        describe.datasets = collect.toTypedArray()
        val t32 = ltit.lookahead()
        if (t32.image == "{" || t32.image == "WHERE") {
            WhereClause(describe)
        }
        SolutionModifier(describe)
        return describe
    }
    private fun AskQuery(): ASTAskQuery {
        val ask = ASTAskQuery()
        val token: Token = ltit.nextToken()
        if (token.image != "ASK") {
            throw UnexpectedToken(token, arrayOf("ASK"), ltit)
        }
        val collect: MutableList<ASTDatasetClause> = mutableListOf()
        var t33 = ltit.lookahead()
        while (t33.image == "FROM") {
            val dataset = DatasetClause()
            collect.add(dataset)
            t33 = ltit.lookahead()
        }
        ask.datasets = collect.toTypedArray()
        WhereClause(ask)
        SolutionModifier(ask)
        return ask
    }
    private fun DatasetClause(): ASTDatasetClause {
        val token: Token = ltit.nextToken()
        if (token.image != "FROM") {
            throw UnexpectedToken(token, arrayOf("FROM"), ltit)
        }
        val result: ASTDatasetClause
        val t34 = ltit.lookahead()
        result = when {
            t34 is IRI || t34 is PNAME_LN || t34 is PNAME_NS -> {
                DefaultGraphClause()
            }
            t34.image == "NAMED" -> {
                NamedGraphClause()
            }
            else -> {
                throw UnexpectedToken(t34, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "NAMED"), ltit)
            }
        }
        return result
    }
    private fun DefaultGraphClause(): ASTDefaultGraph {
        var token: Token
        val iri = SourceSelector()
        return ASTDefaultGraph(iri)
    }
    private fun NamedGraphClause(): ASTNamedGraph {
        val token: Token = ltit.nextToken()
        if (token.image != "NAMED") {
            throw UnexpectedToken(token, arrayOf("NAMED"), ltit)
        }
        val iri = SourceSelector()
        return ASTNamedGraph(iri)
    }
    private fun SourceSelector(): ASTIri {
        var token: Token
        return IRIref()
    }
    private fun WhereClause(query: ASTQueryBaseClass) {
        val token: Token
        val t35 = ltit.lookahead()
        if (t35.image == "WHERE") {
            token = ltit.nextToken()
            if (token.image != "WHERE") {
                throw UnexpectedToken(token, arrayOf("WHERE"), ltit)
            }
        }
        val where = GroupGraphPattern()
        query.where = where
    }
    private fun SolutionModifier(query: ASTQueryBaseClass) {
        var token: Token
        val t36 = ltit.lookahead()
        if (t36.image == "GROUP") {
            GroupClause(query)
        }
        val t37 = ltit.lookahead()
        if (t37.image == "HAVING") {
            HavingClause(query)
        }
        val t38 = ltit.lookahead()
        if (t38.image == "ORDER") {
            OrderClause(query)
        }
        val t39 = ltit.lookahead()
        if (t39.image == "LIMIT" || t39.image == "OFFSET") {
            LimitOffsetClauses(query)
        }
    }
    private fun GroupClause(query: ASTQueryBaseClass) {
        val collect: MutableList<ASTNode> = mutableListOf()
        var token: Token = ltit.nextToken()
        if (token.image != "GROUP") {
            throw UnexpectedToken(token, arrayOf("GROUP"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "BY") {
            throw UnexpectedToken(token, arrayOf("BY"), ltit)
        }
        do {
            val cond = GroupCondition()
            collect.add(cond)
            val t40 = ltit.lookahead()
        } while (t40.image == "STR" || t40.image == "LANG" || t40.image == "LANGMATCHES" || t40.image == "DATATYPE" || t40.image == "BOUND" || t40.image == "IRI" || t40.image == "URI" || t40.image == "BNODE" || t40.image == "RAND" || t40.image == "ABS" || t40.image == "CEIL" || t40.image == "FLOOR" || t40.image == "ROUND" || t40.image == "CONCAT" || t40.image == "SUBSTR" || t40.image == "STRLEN" || t40.image == "REPLACE" || t40.image == "UCASE" || t40.image == "LCASE" || t40.image == "ENCODE_FOR_URI" || t40.image == "CONTAINS" || t40.image == "STRSTARTS" || t40.image == "STRENDS" || t40.image == "STRBEFORE" || t40.image == "STRAFTER" || t40.image == "YEAR" || t40.image == "MONTH" || t40.image == "DAY" || t40.image == "HOURS" || t40.image == "MINUTES" || t40.image == "SECONDS" || t40.image == "TIMEZONE" || t40.image == "TZ" || t40.image == "NOW" || t40.image == "UUID" || t40.image == "STRUUID" || t40.image == "MD5" || t40.image == "SHA1" || t40.image == "SHA256" || t40.image == "SHA384" || t40.image == "SHA512" || t40.image == "COALESCE" || t40.image == "IF" || t40.image == "STRLANG" || t40.image == "STRDT" || t40.image == "SAMETERM" || t40.image == "ISIRI" || t40.image == "ISURI" || t40.image == "ISBLANK" || t40.image == "ISLITERAL" || t40.image == "ISNUMERIC" || t40.image == "REGEX" || t40.image == "EXISTS" || t40.image == "NOT" || t40 is IRI || t40 is PNAME_LN || t40 is PNAME_NS || t40.image == "(" || t40 is VAR)
        query.groupBy = collect.toTypedArray()
    }
    private fun GroupCondition(): ASTNode {
        var token: Token
        var result: ASTNode
        val t42 = ltit.lookahead()
        when {
            t42.image == "STR" || t42.image == "LANG" || t42.image == "LANGMATCHES" || t42.image == "DATATYPE" || t42.image == "BOUND" || t42.image == "IRI" || t42.image == "URI" || t42.image == "BNODE" || t42.image == "RAND" || t42.image == "ABS" || t42.image == "CEIL" || t42.image == "FLOOR" || t42.image == "ROUND" || t42.image == "CONCAT" || t42.image == "SUBSTR" || t42.image == "STRLEN" || t42.image == "REPLACE" || t42.image == "UCASE" || t42.image == "LCASE" || t42.image == "ENCODE_FOR_URI" || t42.image == "CONTAINS" || t42.image == "STRSTARTS" || t42.image == "STRENDS" || t42.image == "STRBEFORE" || t42.image == "STRAFTER" || t42.image == "YEAR" || t42.image == "MONTH" || t42.image == "DAY" || t42.image == "HOURS" || t42.image == "MINUTES" || t42.image == "SECONDS" || t42.image == "TIMEZONE" || t42.image == "TZ" || t42.image == "NOW" || t42.image == "UUID" || t42.image == "STRUUID" || t42.image == "MD5" || t42.image == "SHA1" || t42.image == "SHA256" || t42.image == "SHA384" || t42.image == "SHA512" || t42.image == "COALESCE" || t42.image == "IF" || t42.image == "STRLANG" || t42.image == "STRDT" || t42.image == "SAMETERM" || t42.image == "ISIRI" || t42.image == "ISURI" || t42.image == "ISBLANK" || t42.image == "ISLITERAL" || t42.image == "ISNUMERIC" || t42.image == "REGEX" || t42.image == "EXISTS" || t42.image == "NOT" -> {
                result = BuiltInCall()
            }
            t42 is IRI || t42 is PNAME_LN || t42 is PNAME_NS -> {
                result = FunctionCall()
            }
            t42.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                result = Expression()
                val t41 = ltit.lookahead()
                if (t41.image == "AS") {
                    token = ltit.nextToken()
                    if (token.image != "AS") {
                        throw UnexpectedToken(token, arrayOf("AS"), ltit)
                    }
                    val variable = Var()
                    result = ASTAs(result, variable)
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            t42 is VAR -> {
                result = Var()
            }
            else -> {
                throw UnexpectedToken(t42, arrayOf("STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "(", "VAR"), ltit)
            }
        }
        return result
    }
    private fun HavingClause(query: ASTQueryBaseClass) {
        val collect: MutableList<ASTNode> = mutableListOf()
        val token: Token = ltit.nextToken()
        if (token.image != "HAVING") {
            throw UnexpectedToken(token, arrayOf("HAVING"), ltit)
        }
        do {
            val having = HavingCondition()
            collect.add(having)
            val t43 = ltit.lookahead()
        } while (t43.image == "(" || t43.image == "STR" || t43.image == "LANG" || t43.image == "LANGMATCHES" || t43.image == "DATATYPE" || t43.image == "BOUND" || t43.image == "IRI" || t43.image == "URI" || t43.image == "BNODE" || t43.image == "RAND" || t43.image == "ABS" || t43.image == "CEIL" || t43.image == "FLOOR" || t43.image == "ROUND" || t43.image == "CONCAT" || t43.image == "SUBSTR" || t43.image == "STRLEN" || t43.image == "REPLACE" || t43.image == "UCASE" || t43.image == "LCASE" || t43.image == "ENCODE_FOR_URI" || t43.image == "CONTAINS" || t43.image == "STRSTARTS" || t43.image == "STRENDS" || t43.image == "STRBEFORE" || t43.image == "STRAFTER" || t43.image == "YEAR" || t43.image == "MONTH" || t43.image == "DAY" || t43.image == "HOURS" || t43.image == "MINUTES" || t43.image == "SECONDS" || t43.image == "TIMEZONE" || t43.image == "TZ" || t43.image == "NOW" || t43.image == "UUID" || t43.image == "STRUUID" || t43.image == "MD5" || t43.image == "SHA1" || t43.image == "SHA256" || t43.image == "SHA384" || t43.image == "SHA512" || t43.image == "COALESCE" || t43.image == "IF" || t43.image == "STRLANG" || t43.image == "STRDT" || t43.image == "SAMETERM" || t43.image == "ISIRI" || t43.image == "ISURI" || t43.image == "ISBLANK" || t43.image == "ISLITERAL" || t43.image == "ISNUMERIC" || t43.image == "REGEX" || t43.image == "EXISTS" || t43.image == "NOT" || t43 is IRI || t43 is PNAME_LN || t43 is PNAME_NS)
        query.having = collect.toTypedArray()
    }
    private fun HavingCondition(): ASTNode {
        var token: Token
        return Constraint()
    }
    private fun OrderClause(query: ASTQueryBaseClass) {
        val collect: MutableList<ASTNode> = mutableListOf()
        var token: Token = ltit.nextToken()
        if (token.image != "ORDER") {
            throw UnexpectedToken(token, arrayOf("ORDER"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "BY") {
            throw UnexpectedToken(token, arrayOf("BY"), ltit)
        }
        do {
            val order = OrderCondition()
            collect.add(order)
            val t44 = ltit.lookahead()
        } while (t44.image == "ASC" || t44.image == "DESC" || t44.image == "(" || t44.image == "STR" || t44.image == "LANG" || t44.image == "LANGMATCHES" || t44.image == "DATATYPE" || t44.image == "BOUND" || t44.image == "IRI" || t44.image == "URI" || t44.image == "BNODE" || t44.image == "RAND" || t44.image == "ABS" || t44.image == "CEIL" || t44.image == "FLOOR" || t44.image == "ROUND" || t44.image == "CONCAT" || t44.image == "SUBSTR" || t44.image == "STRLEN" || t44.image == "REPLACE" || t44.image == "UCASE" || t44.image == "LCASE" || t44.image == "ENCODE_FOR_URI" || t44.image == "CONTAINS" || t44.image == "STRSTARTS" || t44.image == "STRENDS" || t44.image == "STRBEFORE" || t44.image == "STRAFTER" || t44.image == "YEAR" || t44.image == "MONTH" || t44.image == "DAY" || t44.image == "HOURS" || t44.image == "MINUTES" || t44.image == "SECONDS" || t44.image == "TIMEZONE" || t44.image == "TZ" || t44.image == "NOW" || t44.image == "UUID" || t44.image == "STRUUID" || t44.image == "MD5" || t44.image == "SHA1" || t44.image == "SHA256" || t44.image == "SHA384" || t44.image == "SHA512" || t44.image == "COALESCE" || t44.image == "IF" || t44.image == "STRLANG" || t44.image == "STRDT" || t44.image == "SAMETERM" || t44.image == "ISIRI" || t44.image == "ISURI" || t44.image == "ISBLANK" || t44.image == "ISLITERAL" || t44.image == "ISNUMERIC" || t44.image == "REGEX" || t44.image == "EXISTS" || t44.image == "NOT" || t44 is IRI || t44 is PNAME_LN || t44 is PNAME_NS || t44 is VAR)
        query.orderBy = collect.toTypedArray()
    }
    private fun OrderCondition(): ASTOrderCondition {
        val token: Token
        val result: ASTOrderCondition
        val t47 = ltit.lookahead()
        when {
            t47.image == "ASC" || t47.image == "DESC" -> {
                val asc: Boolean
                val t45 = ltit.lookahead()
                when (t45.image) {
                    "ASC" -> {
                        token = ltit.nextToken()
                        if (token.image != "ASC") {
                            throw UnexpectedToken(token, arrayOf("ASC"), ltit)
                        }
                        asc = true
                    }
                    "DESC" -> {
                        token = ltit.nextToken()
                        if (token.image != "DESC") {
                            throw UnexpectedToken(token, arrayOf("DESC"), ltit)
                        }
                        asc = false
                    }
                    else -> {
                        throw UnexpectedToken(t45, arrayOf("ASC", "DESC"), ltit)
                    }
                }
                val child = BrackettedExpression()
                return ASTOrderCondition(asc, child)
            }
            t47.image == "(" || t47.image == "STR" || t47.image == "LANG" || t47.image == "LANGMATCHES" || t47.image == "DATATYPE" || t47.image == "BOUND" || t47.image == "IRI" || t47.image == "URI" || t47.image == "BNODE" || t47.image == "RAND" || t47.image == "ABS" || t47.image == "CEIL" || t47.image == "FLOOR" || t47.image == "ROUND" || t47.image == "CONCAT" || t47.image == "SUBSTR" || t47.image == "STRLEN" || t47.image == "REPLACE" || t47.image == "UCASE" || t47.image == "LCASE" || t47.image == "ENCODE_FOR_URI" || t47.image == "CONTAINS" || t47.image == "STRSTARTS" || t47.image == "STRENDS" || t47.image == "STRBEFORE" || t47.image == "STRAFTER" || t47.image == "YEAR" || t47.image == "MONTH" || t47.image == "DAY" || t47.image == "HOURS" || t47.image == "MINUTES" || t47.image == "SECONDS" || t47.image == "TIMEZONE" || t47.image == "TZ" || t47.image == "NOW" || t47.image == "UUID" || t47.image == "STRUUID" || t47.image == "MD5" || t47.image == "SHA1" || t47.image == "SHA256" || t47.image == "SHA384" || t47.image == "SHA512" || t47.image == "COALESCE" || t47.image == "IF" || t47.image == "STRLANG" || t47.image == "STRDT" || t47.image == "SAMETERM" || t47.image == "ISIRI" || t47.image == "ISURI" || t47.image == "ISBLANK" || t47.image == "ISLITERAL" || t47.image == "ISNUMERIC" || t47.image == "REGEX" || t47.image == "EXISTS" || t47.image == "NOT" || t47 is IRI || t47 is PNAME_LN || t47 is PNAME_NS || t47 is VAR -> {
                val t46 = ltit.lookahead()
                return when {
                    t46.image == "(" || t46.image == "STR" || t46.image == "LANG" || t46.image == "LANGMATCHES" || t46.image == "DATATYPE" || t46.image == "BOUND" || t46.image == "IRI" || t46.image == "URI" || t46.image == "BNODE" || t46.image == "RAND" || t46.image == "ABS" || t46.image == "CEIL" || t46.image == "FLOOR" || t46.image == "ROUND" || t46.image == "CONCAT" || t46.image == "SUBSTR" || t46.image == "STRLEN" || t46.image == "REPLACE" || t46.image == "UCASE" || t46.image == "LCASE" || t46.image == "ENCODE_FOR_URI" || t46.image == "CONTAINS" || t46.image == "STRSTARTS" || t46.image == "STRENDS" || t46.image == "STRBEFORE" || t46.image == "STRAFTER" || t46.image == "YEAR" || t46.image == "MONTH" || t46.image == "DAY" || t46.image == "HOURS" || t46.image == "MINUTES" || t46.image == "SECONDS" || t46.image == "TIMEZONE" || t46.image == "TZ" || t46.image == "NOW" || t46.image == "UUID" || t46.image == "STRUUID" || t46.image == "MD5" || t46.image == "SHA1" || t46.image == "SHA256" || t46.image == "SHA384" || t46.image == "SHA512" || t46.image == "COALESCE" || t46.image == "IF" || t46.image == "STRLANG" || t46.image == "STRDT" || t46.image == "SAMETERM" || t46.image == "ISIRI" || t46.image == "ISURI" || t46.image == "ISBLANK" || t46.image == "ISLITERAL" || t46.image == "ISNUMERIC" || t46.image == "REGEX" || t46.image == "EXISTS" || t46.image == "NOT" || t46 is IRI || t46 is PNAME_LN || t46 is PNAME_NS -> {
                        val constr = Constraint()
                        ASTOrderCondition(true, constr)
                    }
                    t46 is VAR -> {
                        val variable = Var()
                        ASTOrderCondition(true, variable)
                    }
                    else -> {
                        throw UnexpectedToken(t46, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "VAR"), ltit)
                    }
                }
            }
            else -> {
                throw UnexpectedToken(t47, arrayOf("ASC", "DESC", "(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "VAR"), ltit)
            }
        }
    }
    private fun LimitOffsetClauses(query: ASTQueryBaseClass) {
        var token: Token
        val t50 = ltit.lookahead()
        when (t50.image) {
            "LIMIT" -> {
                LimitClause(query)
                val t48 = ltit.lookahead()
                if (t48.image == "OFFSET") {
                    OffsetClause(query)
                }
            }
            "OFFSET" -> {
                OffsetClause(query)
                val t49 = ltit.lookahead()
                if (t49.image == "LIMIT") {
                    LimitClause(query)
                }
            }
            else -> {
                throw UnexpectedToken(t50, arrayOf("LIMIT", "OFFSET"), ltit)
            }
        }
    }
    private fun LimitClause(query: ASTQueryBaseClass) {
        var token: Token = ltit.nextToken()
        if (token.image != "LIMIT") {
            throw UnexpectedToken(token, arrayOf("LIMIT"), ltit)
        }
        token = ltit.nextToken()
        if (token !is INTEGER) {
            throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
        }
        query.limit = token.toInt()
    }
    private fun OffsetClause(query: ASTQueryBaseClass) {
        var token: Token = ltit.nextToken()
        if (token.image != "OFFSET") {
            throw UnexpectedToken(token, arrayOf("OFFSET"), ltit)
        }
        token = ltit.nextToken()
        if (token !is INTEGER) {
            throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
        }
        query.offset = token.toInt()
    }
    private fun ValuesClause(): ASTValues {
        var token: Token
        return InlineData()
    }
    private fun InlineData(): ASTValues {
        val token: Token = ltit.nextToken()
        if (token.image != "VALUES") {
            throw UnexpectedToken(token, arrayOf("VALUES"), ltit)
        }
        return DataBlock()
    }
    private fun DataBlock(): ASTValues {
        var token: Token
        val result: ASTValues
        val t51 = ltit.lookahead()
        result = when {
            t51 is VAR -> {
                InlineDataOneVar()
            }
            t51 is NIL || t51.image == "(" -> {
                InlineDataFull()
            }
            else -> {
                throw UnexpectedToken(t51, arrayOf("VAR", "NIL", "("), ltit)
            }
        }
        return result
    }
    private fun InlineDataOneVar(): ASTValues {
        val variable = Var()
        var token: Token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val collect: MutableList<ASTNode> = mutableListOf()
        var t52 = ltit.lookahead()
        while (t52 is IRI || t52 is PNAME_LN || t52 is PNAME_NS || t52 is STRING || t52 is INTEGER || t52 is DECIMAL || t52 is DOUBLE || t52.image == "+" || t52.image == "-" || t52.image == "TRUE" || t52.image == "FALSE" || t52.image == "UNDEF") {
            val value = DataBlockValueOneVar()
            collect.add(value)
            t52 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return ASTValues(variable, collect)
    }
    private fun DataBlockValueOneVar(): ASTValue {
        var token: Token
        val result = DataBlockValue()
        return ASTValue(result)
    }
    private fun InlineDataFull(): ASTValues {
        var token: Token
        val variables: MutableList<ASTVar> = mutableListOf()
        val collect: MutableList<ASTNode> = mutableListOf()
        val t54 = ltit.lookahead()
        when {
            t54 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
            }
            t54.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                var t53 = ltit.lookahead()
                while (t53 is VAR) {
                    val variable = Var()
                    variables.add(variable)
                    t53 = ltit.lookahead()
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t54, arrayOf("NIL", "("), ltit)
            }
        }
        token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        var t55 = ltit.lookahead()
        while (t55 is NIL || t55.image == "(") {
            val data = sequenceOfDataBlockValues()
            collect.add(data)
            t55 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return ASTValues(variables, collect)
    }
    private fun sequenceOfDataBlockValues(): ASTValue {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val t57 = ltit.lookahead()
        when {
            t57 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
            }
            t57.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                var t56 = ltit.lookahead()
                while (t56 is IRI || t56 is PNAME_LN || t56 is PNAME_NS || t56 is STRING || t56 is INTEGER || t56 is DECIMAL || t56 is DOUBLE || t56.image == "+" || t56.image == "-" || t56.image == "TRUE" || t56.image == "FALSE" || t56.image == "UNDEF") {
                    val elem = DataBlockValue()
                    collect.add(elem)
                    t56 = ltit.lookahead()
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t57, arrayOf("NIL", "("), ltit)
            }
        }
        return ASTValue(collect)
    }
    private fun DataBlockValue(): ASTNode {
        val token: Token
        val result: ASTNode
        val t58 = ltit.lookahead()
        when {
            t58 is IRI || t58 is PNAME_LN || t58 is PNAME_NS -> {
                result = IRIref()
            }
            t58 is STRING -> {
                result = RDFLiteral()
            }
            t58 is INTEGER || t58 is DECIMAL || t58 is DOUBLE || t58.image == "+" || t58.image == "-" -> {
                result = NumericLiteral()
            }
            t58.image == "TRUE" || t58.image == "FALSE" -> {
                result = BooleanLiteral()
            }
            t58.image == "UNDEF" -> {
                token = ltit.nextToken()
                if (token.image != "UNDEF") {
                    throw UnexpectedToken(token, arrayOf("UNDEF"), ltit)
                }
                result = ASTUndef()
            }
            else -> {
                throw UnexpectedToken(t58, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "UNDEF"), ltit)
            }
        }
        return result
    }
    private fun Update1(): ASTNode {
        var token: Token
        val result: ASTNode
        val t62 = ltit.lookahead()
        when (t62.image) {
            "LOAD" -> {
                result = Load()
            }
            "CLEAR" -> {
                result = Clear()
            }
            "DROP" -> {
                result = Drop()
            }
            "ADD" -> {
                result = Add()
            }
            "MOVE" -> {
                result = Move()
            }
            "COPY" -> {
                result = Copy()
            }
            "CREATE" -> {
                result = Create()
            }
            "WITH", "DELETE", "INSERT" -> {
                result = Modify()
            }
            else -> {
                throw UnexpectedToken(t62, arrayOf("LOAD", "CLEAR", "DROP", "ADD", "MOVE", "COPY", "CREATE", "WITH", "DELETE", "INSERT"), ltit)
            }
        }
        return result
    }
    private fun Load(): ASTLoad {
        var token: Token
        var silent = false
        val into: ASTGraphRef? = null
        token = ltit.nextToken()
        if (token.image != "LOAD") {
            throw UnexpectedToken(token, arrayOf("LOAD"), ltit)
        }
        val t63 = ltit.lookahead()
        if (t63.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val iri = IRIref()
        val t64 = ltit.lookahead()
        if (t64.image == "INTO") {
            token = ltit.nextToken()
            if (token.image != "INTO") {
                throw UnexpectedToken(token, arrayOf("INTO"), ltit)
            }
            GraphRef()
        }
        return ASTLoad(silent, iri.iri, into)
    }
    private fun Clear(): ASTClear {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "CLEAR") {
            throw UnexpectedToken(token, arrayOf("CLEAR"), ltit)
        }
        val t65 = ltit.lookahead()
        if (t65.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val graphref = GraphRefAll()
        return ASTClear(silent, graphref)
    }
    private fun Drop(): ASTDrop {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "DROP") {
            throw UnexpectedToken(token, arrayOf("DROP"), ltit)
        }
        val t66 = ltit.lookahead()
        if (t66.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val graphref = GraphRefAll()
        return ASTDrop(silent, graphref)
    }
    private fun Create(): ASTCreate {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "CREATE") {
            throw UnexpectedToken(token, arrayOf("CREATE"), ltit)
        }
        val t67 = ltit.lookahead()
        if (t67.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val graphref = GraphRefAll()
        return ASTCreate(silent, graphref)
    }
    private fun Add(): ASTAdd {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "ADD") {
            throw UnexpectedToken(token, arrayOf("ADD"), ltit)
        }
        val t68 = ltit.lookahead()
        if (t68.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val fromGraph = GraphOrDefault()
        token = ltit.nextToken()
        if (token.image != "TO") {
            throw UnexpectedToken(token, arrayOf("TO"), ltit)
        }
        val toGraph = GraphOrDefault()
        return ASTAdd(silent, fromGraph, toGraph)
    }
    private fun Move(): ASTMove {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "MOVE") {
            throw UnexpectedToken(token, arrayOf("MOVE"), ltit)
        }
        val t69 = ltit.lookahead()
        if (t69.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val fromGraph = GraphOrDefault()
        token = ltit.nextToken()
        if (token.image != "TO") {
            throw UnexpectedToken(token, arrayOf("TO"), ltit)
        }
        val toGraph = GraphOrDefault()
        return ASTMove(silent, fromGraph, toGraph)
    }
    private fun Copy(): ASTCopy {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "COPY") {
            throw UnexpectedToken(token, arrayOf("COPY"), ltit)
        }
        val t70 = ltit.lookahead()
        if (t70.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val fromGraph = GraphOrDefault()
        token = ltit.nextToken()
        if (token.image != "TO") {
            throw UnexpectedToken(token, arrayOf("TO"), ltit)
        }
        val toGraph = GraphOrDefault()
        return ASTCopy(silent, fromGraph, toGraph)
    }
    private fun Modify(): ASTModify {
        var token: Token
        var iri: ASTIri? = null
        var delete: Array<ASTNode>? = null
        var insert: Array<ASTNode>? = null
        val t76 = ltit.lookahead()
        when (t76.image) {
            "WITH" -> {
                token = ltit.nextToken()
                if (token.image != "WITH") {
                    throw UnexpectedToken(token, arrayOf("WITH"), ltit)
                }
                iri = IRIref()
                val t72 = ltit.lookahead()
                when (t72.image) {
                    "DELETE" -> {
                        delete = DeleteClause()
                        val t71 = ltit.lookahead()
                        if (t71.image == "INSERT") {
                            insert = InsertClause()
                        }
                    }
                    "INSERT" -> {
                        insert = InsertClause()
                    }
                    else -> {
                        throw UnexpectedToken(t72, arrayOf("DELETE", "INSERT"), ltit)
                    }
                }
            }
            "DELETE" -> {
                token = ltit.nextToken()
                if (token.image != "DELETE") {
                    throw UnexpectedToken(token, arrayOf("DELETE"), ltit)
                }
                val t73 = ltit.lookahead()
                when (t73.image) {
                    "DATA" -> {
                        token = ltit.nextToken()
                        if (token.image != "DATA") {
                            throw UnexpectedToken(token, arrayOf("DATA"), ltit)
                        }
                        val data = QuadData()
                        return ASTDeleteData(data)
                    }
                    "WHERE" -> {
                        token = ltit.nextToken()
                        if (token.image != "WHERE") {
                            throw UnexpectedToken(token, arrayOf("WHERE"), ltit)
                        }
                        val pattern = QuadPattern()
                        return ASTDeleteWhere(pattern)
                    }
                    "{" -> {
                        delete = QuadPattern()
                    }
                    else -> {
                        throw UnexpectedToken(t73, arrayOf("DATA", "WHERE", "{"), ltit)
                    }
                }
                val t74 = ltit.lookahead()
                if (t74.image == "INSERT") {
                    insert = InsertClause()
                }
            }
            "INSERT" -> {
                token = ltit.nextToken()
                if (token.image != "INSERT") {
                    throw UnexpectedToken(token, arrayOf("INSERT"), ltit)
                }
                val t75 = ltit.lookahead()
                when (t75.image) {
                    "DATA" -> {
                        token = ltit.nextToken()
                        if (token.image != "DATA") {
                            throw UnexpectedToken(token, arrayOf("DATA"), ltit)
                        }
                        val data = QuadData()
                        return ASTInsertData(data)
                    }
                    "{" -> {
                        insert = QuadPattern()
                    }
                    else -> {
                        throw UnexpectedToken(t75, arrayOf("DATA", "{"), ltit)
                    }
                }
            }
            else -> {
                throw UnexpectedToken(t76, arrayOf("WITH", "DELETE", "INSERT"), ltit)
            }
        }
        val collect = mutableListOf<ASTGraphRef>()
        var t77 = ltit.lookahead()
        while (t77.image == "USING") {
            val ref = UsingClause()
            collect.add(ref)
            t77 = ltit.lookahead()
        }
        token = ltit.nextToken()
        if (token.image != "WHERE") {
            throw UnexpectedToken(token, arrayOf("WHERE"), ltit)
        }
        val where = GroupGraphPattern()
        return ASTModifyWithWhere(
            iri?.iri, delete ?: arrayOf(),
            insert
                ?: arrayOf(),
            collect.toTypedArray(), where
        )
    }
    private fun DeleteClause(): Array<ASTNode> {
        val token: Token = ltit.nextToken()
        if (token.image != "DELETE") {
            throw UnexpectedToken(token, arrayOf("DELETE"), ltit)
        }
        return QuadPattern()
    }
    private fun InsertClause(): Array<ASTNode> {
        val token: Token = ltit.nextToken()
        if (token.image != "INSERT") {
            throw UnexpectedToken(token, arrayOf("INSERT"), ltit)
        }
        return QuadPattern()
    }
    private fun UsingClause(): ASTGraphRef {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "USING") {
            throw UnexpectedToken(token, arrayOf("USING"), ltit)
        }
        val t78 = ltit.lookahead()
        when {
            t78.image == "NAMED" -> {
                token = ltit.nextToken()
                if (token.image != "NAMED") {
                    throw UnexpectedToken(token, arrayOf("NAMED"), ltit)
                }
                val iri = IRIref()
                return ASTNamedIriGraphRef(iri.iri)
            }
            t78 is IRI || t78 is PNAME_LN || t78 is PNAME_NS -> {
                val iri = IRIref()
                return ASTIriGraphRef(iri.iri)
            }
            else -> {
                throw UnexpectedToken(t78, arrayOf("NAMED", "IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
    }
    private fun GraphOrDefault(): ASTGraphRef {
        val token: Token
        val t80 = ltit.lookahead()
        when {
            t80.image == "DEFAULT" -> {
                token = ltit.nextToken()
                if (token.image != "DEFAULT") {
                    throw UnexpectedToken(token, arrayOf("DEFAULT"), ltit)
                }
                return ASTDefaultGraphRef()
            }
            t80 is IRI || t80 is PNAME_LN || t80 is PNAME_NS || t80.image == "GRAPH" -> {
                val t79 = ltit.lookahead()
                if (t79.image == "GRAPH") {
                    token = ltit.nextToken()
                    if (token.image != "GRAPH") {
                        throw UnexpectedToken(token, arrayOf("GRAPH"), ltit)
                    }
                }
                val iri = IRIref()
                return ASTIriGraphRef(iri.iri)
            }
            else -> {
                throw UnexpectedToken(t80, arrayOf("DEFAULT", "IRI", "PNAME_LN", "PNAME_NS", "GRAPH"), ltit)
            }
        }
    }
    private fun GraphRef(): ASTIriGraphRef {
        val token: Token = ltit.nextToken()
        if (token.image != "GRAPH") {
            throw UnexpectedToken(token, arrayOf("GRAPH"), ltit)
        }
        val iri = IRIref()
        return ASTIriGraphRef(iri.iri)
    }
    private fun GraphRefAll(): ASTGraphRef {
        val token: Token
        val t81 = ltit.lookahead()
        when (t81.image) {
            "GRAPH" -> {
                return GraphRef()
            }
            "DEFAULT" -> {
                token = ltit.nextToken()
                if (token.image != "DEFAULT") {
                    throw UnexpectedToken(token, arrayOf("DEFAULT"), ltit)
                }
                return ASTDefaultGraphRef()
            }
            "NAMED" -> {
                token = ltit.nextToken()
                if (token.image != "NAMED") {
                    throw UnexpectedToken(token, arrayOf("NAMED"), ltit)
                }
                return ASTNamedGraphRef()
            }
            "ALL" -> {
                token = ltit.nextToken()
                if (token.image != "ALL") {
                    throw UnexpectedToken(token, arrayOf("ALL"), ltit)
                }
                return ASTAllGraphRef()
            }
            else -> {
                throw UnexpectedToken(t81, arrayOf("GRAPH", "DEFAULT", "NAMED", "ALL"), ltit)
            }
        }
    }
    private fun QuadPattern(): Array<ASTNode> {
        var token: Token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val result = Quads()
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return result
    }
    private fun QuadData(): Array<ASTNode> {
        var token: Token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val result = Quads()
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return result
    }
    private fun Quads(): Array<ASTNode> {
        var token: Token
        val collect = mutableListOf<ASTNode>()
        val t82 = ltit.lookahead()
        if (t82.image == "(" || t82.image == "[" || t82 is VAR || t82 is IRI || t82 is PNAME_LN || t82 is PNAME_NS || t82 is STRING || t82 is INTEGER || t82 is DECIMAL || t82 is DOUBLE || t82.image == "+" || t82.image == "-" || t82.image == "TRUE" || t82.image == "FALSE" || t82 is BNODE || t82 is ANON_BNODE || t82 is NIL) {
            val triples = TriplesTemplate()
            collect.addAll(triples)
        }
        var t85 = ltit.lookahead()
        while (t85.image == "GRAPH") {
            val graph = QuadsNotTriples()
            val t83 = ltit.lookahead()
            if (t83.image == ".") {
                token = ltit.nextToken()
                if (token.image != ".") {
                    throw UnexpectedToken(token, arrayOf("."), ltit)
                }
            }
            collect.add(graph)
            val t84 = ltit.lookahead()
            if (t84.image == "(" || t84.image == "[" || t84 is VAR || t84 is IRI || t84 is PNAME_LN || t84 is PNAME_NS || t84 is STRING || t84 is INTEGER || t84 is DECIMAL || t84 is DOUBLE || t84.image == "+" || t84.image == "-" || t84.image == "TRUE" || t84.image == "FALSE" || t84 is BNODE || t84 is ANON_BNODE || t84 is NIL) {
                val triples2 = TriplesTemplate()
                collect.addAll(triples2)
            }
            t85 = ltit.lookahead()
        }
        return collect.toTypedArray()
    }
    private fun QuadsNotTriples(): ASTGraph {
        var graphconstraint = arrayOf<ASTNode>()
        var token: Token = ltit.nextToken()
        if (token.image != "GRAPH") {
            throw UnexpectedToken(token, arrayOf("GRAPH"), ltit)
        }
        val varoriri = VarOrIRIref()
        token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val t86 = ltit.lookahead()
        if (t86.image == "(" || t86.image == "[" || t86 is VAR || t86 is IRI || t86 is PNAME_LN || t86 is PNAME_NS || t86 is STRING || t86 is INTEGER || t86 is DECIMAL || t86 is DOUBLE || t86.image == "+" || t86.image == "-" || t86.image == "TRUE" || t86.image == "FALSE" || t86 is BNODE || t86 is ANON_BNODE || t86 is NIL) {
            graphconstraint = TriplesTemplate()
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return ASTGraph(varoriri, graphconstraint)
    }
    private fun TriplesTemplate(): Array<ASTNode> {
        var token: Token
        val result = mutableListOf<ASTNode>()
        TriplesSameSubject(result)
        var t88 = ltit.lookahead()
        while (t88.image == ".") {
            token = ltit.nextToken()
            if (token.image != ".") {
                throw UnexpectedToken(token, arrayOf("."), ltit)
            }
            val t87 = ltit.lookahead()
            if (t87.image == "(" || t87.image == "[" || t87 is VAR || t87 is IRI || t87 is PNAME_LN || t87 is PNAME_NS || t87 is STRING || t87 is INTEGER || t87 is DECIMAL || t87 is DOUBLE || t87.image == "+" || t87.image == "-" || t87.image == "TRUE" || t87.image == "FALSE" || t87 is BNODE || t87 is ANON_BNODE || t87 is NIL) {
                TriplesSameSubject(result)
            }
            t88 = ltit.lookahead()
        }
        return result.toTypedArray()
    }
    private fun GroupGraphPattern(): Array<ASTNode> {
        val result = mutableListOf<ASTNode>()
        var token: Token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val t90 = ltit.lookahead()
        if (t90.image == "SELECT" || t90.image == "(" || t90.image == "[" || t90 is VAR || t90 is IRI || t90 is PNAME_LN || t90 is PNAME_NS || t90 is STRING || t90 is INTEGER || t90 is DECIMAL || t90 is DOUBLE || t90.image == "+" || t90.image == "-" || t90.image == "TRUE" || t90.image == "FALSE" || t90 is BNODE || t90 is ANON_BNODE || t90 is NIL || t90.image == "{" || t90.image == "OPTIONAL" || t90.image == "MINUS" || t90.image == "GRAPH" || t90.image == "SERVICE" || t90.image == "FILTER" || t90.image == "BIND" || t90.image == "VALUES") {
            val t89 = ltit.lookahead()
            when {
                t89.image == "SELECT" -> {
                    val select = SubSelect()
                    result.add(select)
                }
                t89.image == "(" || t89.image == "[" || t89 is VAR || t89 is IRI || t89 is PNAME_LN || t89 is PNAME_NS || t89 is STRING || t89 is INTEGER || t89 is DECIMAL || t89 is DOUBLE || t89.image == "+" || t89.image == "-" || t89.image == "TRUE" || t89.image == "FALSE" || t89 is BNODE || t89 is ANON_BNODE || t89 is NIL || t89.image == "{" || t89.image == "OPTIONAL" || t89.image == "MINUS" || t89.image == "GRAPH" || t89.image == "SERVICE" || t89.image == "FILTER" || t89.image == "BIND" || t89.image == "VALUES" -> {
                    val group = GroupGraphPatternSub()
                    result.addAll(group)
                }
                else -> {
                    throw UnexpectedToken(t89, arrayOf("SELECT", "(", "[", "VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL", "{", "OPTIONAL", "MINUS", "GRAPH", "SERVICE", "FILTER", "BIND", "VALUES"), ltit)
                }
            }
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return result.toTypedArray()
    }
    private fun GroupGraphPatternSub(): Array<ASTNode> {
        var token: Token
        val result = mutableListOf<ASTNode>()
        val t91 = ltit.lookahead()
        if (t91.image == "(" || t91.image == "[" || t91 is VAR || t91 is IRI || t91 is PNAME_LN || t91 is PNAME_NS || t91 is STRING || t91 is INTEGER || t91 is DECIMAL || t91 is DOUBLE || t91.image == "+" || t91.image == "-" || t91.image == "TRUE" || t91.image == "FALSE" || t91 is BNODE || t91 is ANON_BNODE || t91 is NIL) {
            TriplesBlock(result)
        }
        var t94 = ltit.lookahead()
        while (t94.image == "{" || t94.image == "OPTIONAL" || t94.image == "MINUS" || t94.image == "GRAPH" || t94.image == "SERVICE" || t94.image == "FILTER" || t94.image == "BIND" || t94.image == "VALUES") {
            val no_pattern = GraphPatternNotTriples()
            result.add(no_pattern)
            val t92 = ltit.lookahead()
            if (t92.image == ".") {
                token = ltit.nextToken()
                if (token.image != ".") {
                    throw UnexpectedToken(token, arrayOf("."), ltit)
                }
            }
            val t93 = ltit.lookahead()
            if (t93.image == "(" || t93.image == "[" || t93 is VAR || t93 is IRI || t93 is PNAME_LN || t93 is PNAME_NS || t93 is STRING || t93 is INTEGER || t93 is DECIMAL || t93 is DOUBLE || t93.image == "+" || t93.image == "-" || t93.image == "TRUE" || t93.image == "FALSE" || t93 is BNODE || t93 is ANON_BNODE || t93 is NIL) {
                TriplesBlock(result)
            }
            t94 = ltit.lookahead()
        }
        return result.toTypedArray()
    }
    private fun TriplesBlock(patterns: MutableList<ASTNode>) {
        val token: Token
        TriplesSameSubjectPath(patterns)
        val t96 = ltit.lookahead()
        if (t96.image == ".") {
            token = ltit.nextToken()
            if (token.image != ".") {
                throw UnexpectedToken(token, arrayOf("."), ltit)
            }
            val t95 = ltit.lookahead()
            if (t95.image == "(" || t95.image == "[" || t95 is VAR || t95 is IRI || t95 is PNAME_LN || t95 is PNAME_NS || t95 is STRING || t95 is INTEGER || t95 is DECIMAL || t95 is DOUBLE || t95.image == "+" || t95.image == "-" || t95.image == "TRUE" || t95.image == "FALSE" || t95 is BNODE || t95 is ANON_BNODE || t95 is NIL) {
                TriplesBlock(patterns)
            }
        }
    }
    private fun GraphPatternNotTriples(): ASTNode {
        var token: Token
        val result: ASTNode
        val t97 = ltit.lookahead()
        when (t97.image) {
            "{" -> {
                result = GroupOrUnionGraphPattern()
            }
            "OPTIONAL" -> {
                result = OptionalGraphPattern()
            }
            "MINUS" -> {
                result = MinusGraphPattern()
            }
            "GRAPH" -> {
                result = GraphGraphPattern()
            }
            "SERVICE" -> {
                result = ServiceGraphPattern()
            }
            "FILTER" -> {
                result = Filter()
            }
            "BIND" -> {
                result = Bind()
            }
            "VALUES" -> {
                result = InlineData()
            }
            else -> {
                throw UnexpectedToken(t97, arrayOf("{", "OPTIONAL", "MINUS", "GRAPH", "SERVICE", "FILTER", "BIND", "VALUES"), ltit)
            }
        }
        return result
    }
    private fun OptionalGraphPattern(): ASTOptional {
        val token: Token = ltit.nextToken()
        if (token.image != "OPTIONAL") {
            throw UnexpectedToken(token, arrayOf("OPTIONAL"), ltit)
        }
        val group = GroupGraphPattern()
        return ASTOptional(group)
    }
    private fun GraphGraphPattern(): ASTGraph {
        val token: Token = ltit.nextToken()
        if (token.image != "GRAPH") {
            throw UnexpectedToken(token, arrayOf("GRAPH"), ltit)
        }
        val varoriri = VarOrIRIref()
        val constraint = GroupGraphPattern()
        return ASTGraph(varoriri, constraint)
    }
    private fun ServiceGraphPattern(): ASTService {
        var token: Token
        var silent = false
        token = ltit.nextToken()
        if (token.image != "SERVICE") {
            throw UnexpectedToken(token, arrayOf("SERVICE"), ltit)
        }
        val t98 = ltit.lookahead()
        if (t98.image == "SILENT") {
            token = ltit.nextToken()
            if (token.image != "SILENT") {
                throw UnexpectedToken(token, arrayOf("SILENT"), ltit)
            }
            silent = true
        }
        val varOrIri = VarOrIRIref()
        val constraint = GroupGraphPattern()
        return ASTService(silent, varOrIri, constraint)
    }
    private fun Bind(): ASTAs {
        var token: Token = ltit.nextToken()
        if (token.image != "BIND") {
            throw UnexpectedToken(token, arrayOf("BIND"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val expr = Expression()
        token = ltit.nextToken()
        if (token.image != "AS") {
            throw UnexpectedToken(token, arrayOf("AS"), ltit)
        }
        val variable = Var()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTAs(expr, variable)
    }
    private fun MinusGraphPattern(): ASTMinusGroup {
        val token: Token = ltit.nextToken()
        if (token.image != "MINUS") {
            throw UnexpectedToken(token, arrayOf("MINUS"), ltit)
        }
        val group = GroupGraphPattern()
        return ASTMinusGroup(group)
    }
    private fun GroupOrUnionGraphPattern(): ASTNode {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val first = GroupGraphPattern()
        if (first.size == 1) collect.add(first[0]); else {
            collect.add(ASTGroup(first))
        }
        var t99 = ltit.lookahead()
        while (t99.image == "UNION") {
            token = ltit.nextToken()
            if (token.image != "UNION") {
                throw UnexpectedToken(token, arrayOf("UNION"), ltit)
            }
            val second = GroupGraphPattern()
            if (second.size == 1) collect.add(second[0]); else {
                collect.add(ASTGroup(second))
            }
            t99 = ltit.lookahead()
        }
        return if (collect.size > 1) {
            ASTUnion(collect.toTypedArray())
        } else {
            collect[0]
        }
    }
    private fun Filter(): ASTFilter {
        val token: Token = ltit.nextToken()
        if (token.image != "FILTER") {
            throw UnexpectedToken(token, arrayOf("FILTER"), ltit)
        }
        val result = Constraint()
        return ASTFilter(result)
    }
    private fun Constraint(): ASTNode {
        var token: Token
        val result: ASTNode
        val t100 = ltit.lookahead()
        result = when {
            t100.image == "(" -> {
                BrackettedExpression()
            }
            t100.image == "STR" || t100.image == "LANG" || t100.image == "LANGMATCHES" || t100.image == "DATATYPE" || t100.image == "BOUND" || t100.image == "IRI" || t100.image == "URI" || t100.image == "BNODE" || t100.image == "RAND" || t100.image == "ABS" || t100.image == "CEIL" || t100.image == "FLOOR" || t100.image == "ROUND" || t100.image == "CONCAT" || t100.image == "SUBSTR" || t100.image == "STRLEN" || t100.image == "REPLACE" || t100.image == "UCASE" || t100.image == "LCASE" || t100.image == "ENCODE_FOR_URI" || t100.image == "CONTAINS" || t100.image == "STRSTARTS" || t100.image == "STRENDS" || t100.image == "STRBEFORE" || t100.image == "STRAFTER" || t100.image == "YEAR" || t100.image == "MONTH" || t100.image == "DAY" || t100.image == "HOURS" || t100.image == "MINUTES" || t100.image == "SECONDS" || t100.image == "TIMEZONE" || t100.image == "TZ" || t100.image == "NOW" || t100.image == "UUID" || t100.image == "STRUUID" || t100.image == "MD5" || t100.image == "SHA1" || t100.image == "SHA256" || t100.image == "SHA384" || t100.image == "SHA512" || t100.image == "COALESCE" || t100.image == "IF" || t100.image == "STRLANG" || t100.image == "STRDT" || t100.image == "SAMETERM" || t100.image == "ISIRI" || t100.image == "ISURI" || t100.image == "ISBLANK" || t100.image == "ISLITERAL" || t100.image == "ISNUMERIC" || t100.image == "REGEX" || t100.image == "EXISTS" || t100.image == "NOT" -> {
                BuiltInCall()
            }
            t100 is IRI || t100 is PNAME_LN || t100 is PNAME_NS -> {
                FunctionCall()
            }
            else -> {
                throw UnexpectedToken(t100, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        return result
    }
    private fun FunctionCall(): ASTFunctionCall {
        var token: Token
        val iri = IRIref()
        return ArgList(iri.iri)
    }
    private fun ArgList(iri: String): ASTFunctionCall {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        var distinct = false
        val t103 = ltit.lookahead()
        when {
            t103 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
            }
            t103.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t101 = ltit.lookahead()
                if (t101.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                collect.add(expr)
                var t102 = ltit.lookahead()
                while (t102.image == ",") {
                    token = ltit.nextToken()
                    if (token.image != ",") {
                        throw UnexpectedToken(token, arrayOf(","), ltit)
                    }
                    val expr2 = Expression()
                    collect.add(expr2)
                    t102 = ltit.lookahead()
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t103, arrayOf("NIL", "("), ltit)
            }
        }
        return ASTFunctionCall(iri, distinct, collect.toTypedArray())
    }
    private fun ExpressionList(): Array<ASTNode> {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val t105 = ltit.lookahead()
        when {
            t105 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
            }
            t105.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val first = Expression()
                collect.add(first)
                var t104 = ltit.lookahead()
                while (t104.image == ",") {
                    token = ltit.nextToken()
                    if (token.image != ",") {
                        throw UnexpectedToken(token, arrayOf(","), ltit)
                    }
                    val second = Expression()
                    collect.add(second)
                    t104 = ltit.lookahead()
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t105, arrayOf("NIL", "("), ltit)
            }
        }
        return collect.toTypedArray()
    }
    private fun ConstructTemplate(): Array<ASTNode> {
        val result = mutableListOf<ASTNode>()
        var token: Token = ltit.nextToken()
        if (token.image != "{") {
            throw UnexpectedToken(token, arrayOf("{"), ltit)
        }
        val t106 = ltit.lookahead()
        if (t106.image == "(" || t106.image == "[" || t106 is VAR || t106 is IRI || t106 is PNAME_LN || t106 is PNAME_NS || t106 is STRING || t106 is INTEGER || t106 is DECIMAL || t106 is DOUBLE || t106.image == "+" || t106.image == "-" || t106.image == "TRUE" || t106.image == "FALSE" || t106 is BNODE || t106 is ANON_BNODE || t106 is NIL) {
            ConstructTriples(result)
        }
        token = ltit.nextToken()
        if (token.image != "}") {
            throw UnexpectedToken(token, arrayOf("}"), ltit)
        }
        return result.toTypedArray()
    }
    private fun ConstructTriples(result: MutableList<ASTNode>) {
        val token: Token
        TriplesSameSubject(result)
        val t108 = ltit.lookahead()
        if (t108.image == ".") {
            token = ltit.nextToken()
            if (token.image != ".") {
                throw UnexpectedToken(token, arrayOf("."), ltit)
            }
            val t107 = ltit.lookahead()
            if (t107.image == "(" || t107.image == "[" || t107 is VAR || t107 is IRI || t107 is PNAME_LN || t107 is PNAME_NS || t107 is STRING || t107 is INTEGER || t107 is DECIMAL || t107 is DOUBLE || t107.image == "+" || t107.image == "-" || t107.image == "TRUE" || t107.image == "FALSE" || t107 is BNODE || t107 is ANON_BNODE || t107 is NIL) {
                ConstructTriples(result)
            }
        }
    }
    private fun TriplesSameSubject(result: MutableList<ASTNode>) {
        var token: Token
        val t109 = ltit.lookahead()
        when {
            t109.image == "(" || t109.image == "[" -> {
                val subject = TriplesNode(result)
                PropertyList(subject, result)
            }
            t109 is VAR || t109 is IRI || t109 is PNAME_LN || t109 is PNAME_NS || t109 is STRING || t109 is INTEGER || t109 is DECIMAL || t109 is DOUBLE || t109.image == "+" || t109.image == "-" || t109.image == "TRUE" || t109.image == "FALSE" || t109 is BNODE || t109 is ANON_BNODE || t109 is NIL -> {
                val subject = VarOrTerm()
                PropertyListNotEmpty(subject, result)
            }
            else -> {
                throw UnexpectedToken(t109, arrayOf("(", "[", "VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL"), ltit)
            }
        }
    }
    private fun PropertyListNotEmpty(subject: ASTNode, result: MutableList<ASTNode>) {
        var token: Token
        val predicate = Verb()
        ObjectList(subject, predicate, result)
        var t111 = ltit.lookahead()
        while (t111.image == ";") {
            token = ltit.nextToken()
            if (token.image != ";") {
                throw UnexpectedToken(token, arrayOf(";"), ltit)
            }
            val t110 = ltit.lookahead()
            if (t110 is VAR || t110 is IRI || t110 is PNAME_LN || t110 is PNAME_NS || t110.image == "A") {
                val predicate2 = Verb()
                ObjectList(subject, predicate2, result)
            }
            t111 = ltit.lookahead()
        }
    }
    private fun PropertyList(subject: ASTNode, result: MutableList<ASTNode>) {
        var token: Token
        val t112 = ltit.lookahead()
        if (t112 is VAR || t112 is IRI || t112 is PNAME_LN || t112 is PNAME_NS || t112.image == "A") {
            PropertyListNotEmpty(subject, result)
        }
    }
    private fun ObjectList(subject: ASTNode, predicate: ASTNode, result: MutableList<ASTNode>) {
        var token: Token
        val o = Object(result)
        result.add(ASTTriple(subject, predicate, o))
        var t113 = ltit.lookahead()
        while (t113.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val o2 = Object(result)
            result.add(ASTTriple(subject, predicate, o2))
            t113 = ltit.lookahead()
        }
    }
    private fun Object(result: MutableList<ASTNode>): ASTNode {
        var token: Token
        return GraphNode(result)
    }
    private fun Verb(): ASTNode {
        val token: Token
        val t114 = ltit.lookahead()
        return when {
            t114 is VAR || t114 is IRI || t114 is PNAME_LN || t114 is PNAME_NS -> {
                VarOrIRIref()
            }
            t114.image == "A" -> {
                token = ltit.nextToken()
                if (token.image != "A") {
                    throw UnexpectedToken(token, arrayOf("A"), ltit)
                }
                ASTType
            }
            else -> {
                throw UnexpectedToken(t114, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS", "A"), ltit)
            }
        }
    }
    private fun TriplesSameSubjectPath(patterns: MutableList<ASTNode>) {
        var token: Token
        val t115 = ltit.lookahead()
        when {
            t115.image == "(" || t115.image == "[" -> {
                val s = TriplesNodePath(patterns)
                PropertyListPath(s, patterns)
            }
            t115 is VAR || t115 is IRI || t115 is PNAME_LN || t115 is PNAME_NS || t115 is STRING || t115 is INTEGER || t115 is DECIMAL || t115 is DOUBLE || t115.image == "+" || t115.image == "-" || t115.image == "TRUE" || t115.image == "FALSE" || t115 is BNODE || t115 is ANON_BNODE || t115 is NIL -> {
                val vot = VarOrTerm()
                PropertyListNotEmptyPath(vot, patterns)
            }
            else -> {
                throw UnexpectedToken(t115, arrayOf("(", "[", "VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL"), ltit)
            }
        }
    }
    private fun TriplesNodePath(patterns: MutableList<ASTNode>): ASTNode {
        var token: Token
        val result: ASTNode
        val t116 = ltit.lookahead()
        result = when (t116.image) {
            "(" -> {
                CollectionPath(patterns)
            }
            "[" -> {
                BlankNodePropertyListPath(patterns)
            }
            else -> {
                throw UnexpectedToken(t116, arrayOf("(", "["), ltit)
            }
        }
        return result
    }
    private fun BlankNodePropertyListPath(result: MutableList<ASTNode>): ASTBlankNode {
        val b = ASTBlankNode()
        var token: Token = ltit.nextToken()
        if (token.image != "[") {
            throw UnexpectedToken(token, arrayOf("["), ltit)
        }
        PropertyListNotEmptyPath(b, result)
        token = ltit.nextToken()
        if (token.image != "]") {
            throw UnexpectedToken(token, arrayOf("]"), ltit)
        }
        return b
    }
    private fun CollectionPath(result: MutableList<ASTNode>): ASTBlankNode {
        val subject = ASTBlankNode()
        var current = subject
        var token: Token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val gn = GraphNodePath(result)
        result.add(ASTTriple(current, ASTFirst, gn))
        var t117 = ltit.lookahead()
        while (t117 is VAR || t117 is IRI || t117 is PNAME_LN || t117 is PNAME_NS || t117 is STRING || t117 is INTEGER || t117 is DECIMAL || t117 is DOUBLE || t117.image == "+" || t117.image == "-" || t117.image == "TRUE" || t117.image == "FALSE" || t117 is BNODE || t117 is ANON_BNODE || t117 is NIL || t117.image == "(" || t117.image == "[") {
            val gn2 = GraphNodePath(result)
            val next = ASTBlankNode()
            result.add(ASTTriple(current, ASTRest, next))
            result.add(ASTTriple(next, ASTFirst, gn2))
            current = next
            t117 = ltit.lookahead()
        }
        result.add(ASTTriple(current, ASTRest, ASTNil))
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return subject
    }
    private fun GraphNodePath(result: MutableList<ASTNode>): ASTNode {
        var token: Token
        val t118 = ltit.lookahead()
        return when {
            t118 is VAR || t118 is IRI || t118 is PNAME_LN || t118 is PNAME_NS || t118 is STRING || t118 is INTEGER || t118 is DECIMAL || t118 is DOUBLE || t118.image == "+" || t118.image == "-" || t118.image == "TRUE" || t118.image == "FALSE" || t118 is BNODE || t118 is ANON_BNODE || t118 is NIL -> {
                VarOrTerm()
            }
            t118.image == "(" || t118.image == "[" -> {
                TriplesNodePath(result)
            }
            else -> {
                throw UnexpectedToken(t118, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL", "(", "["), ltit)
            }
        }
    }
    private fun PropertyListNotEmptyPath(s: ASTNode, patterns: MutableList<ASTNode>) {
        var token: Token
        val p: ASTNode
        val t119 = ltit.lookahead()
        p = when {
            t119 is IRI || t119 is PNAME_LN || t119 is PNAME_NS || t119.image == "A" || t119.image == "!" || t119.image == "(" || t119.image == "^" -> {
                VerbPath()
            }
            t119 is VAR -> {
                VerbSimple()
            }
            else -> {
                throw UnexpectedToken(t119, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "!", "(", "^", "VAR"), ltit)
            }
        }
        ObjectListPath(s, p, patterns)
        var t122 = ltit.lookahead()
        while (t122.image == ";") {
            token = ltit.nextToken()
            if (token.image != ";") {
                throw UnexpectedToken(token, arrayOf(";"), ltit)
            }
            val t121 = ltit.lookahead()
            if (t121 is IRI || t121 is PNAME_LN || t121 is PNAME_NS || t121.image == "A" || t121.image == "!" || t121.image == "(" || t121.image == "^" || t121 is VAR) {
                val p2: ASTNode
                val t120 = ltit.lookahead()
                p2 = when {
                    t120 is IRI || t120 is PNAME_LN || t120 is PNAME_NS || t120.image == "A" || t120.image == "!" || t120.image == "(" || t120.image == "^" -> {
                        VerbPath()
                    }
                    t120 is VAR -> {
                        VerbSimple()
                    }
                    else -> {
                        throw UnexpectedToken(t120, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "!", "(", "^", "VAR"), ltit)
                    }
                }
                ObjectList(s, p2, patterns)
            }
            t122 = ltit.lookahead()
        }
    }
    private fun ObjectListPath(subject: ASTNode, predicate: ASTNode, result: MutableList<ASTNode>) {
        var token: Token
        val o = GraphNodePath(result)
        result.add(ASTTriple(subject, predicate, o))
        var t123 = ltit.lookahead()
        while (t123.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val o2 = GraphNodePath(result)
            result.add(ASTTriple(subject, predicate, o2))
            t123 = ltit.lookahead()
        }
    }
    private fun PropertyListPath(s: ASTNode, patterns: MutableList<ASTNode>) {
        var token: Token
        val t124 = ltit.lookahead()
        if (t124 is VAR || t124 is IRI || t124 is PNAME_LN || t124 is PNAME_NS || t124.image == "A") {
            PropertyListNotEmpty(s, patterns)
        }
    }
    private fun VerbPath(): ASTNode {
        var token: Token
        return Path()
    }
    private fun VerbSimple(): ASTNode {
        var token: Token
        return Var()
    }
    private fun Path(): ASTNode {
        var token: Token
        return PathAlternative()
    }
    private fun PathAlternative(): ASTNode {
        var token: Token
        val first = PathSequence()
        val collect: MutableList<ASTNode> = mutableListOf(first)
        var t125 = ltit.lookahead()
        while (t125.image == "|") {
            token = ltit.nextToken()
            if (token.image != "|") {
                throw UnexpectedToken(token, arrayOf("|"), ltit)
            }
            val second = PathSequence()
            collect.add(second)
            t125 = ltit.lookahead()
        }
        return if (collect.size > 1) {
            ASTPathAlternatives(collect.toTypedArray())
        } else {
            first
        }
    }
    private fun PathSequence(): ASTNode {
        var token: Token
        val first = PathEltOrInverse()
        val collect: MutableList<ASTNode> = mutableListOf(first)
        var t126 = ltit.lookahead()
        while (t126.image == "/") {
            token = ltit.nextToken()
            if (token.image != "/") {
                throw UnexpectedToken(token, arrayOf("/"), ltit)
            }
            val second = PathEltOrInverse()
            collect.add(second)
            t126 = ltit.lookahead()
        }
        return if (collect.size > 1) {
            ASTPathSequence(collect.toTypedArray())
        } else {
            first
        }
    }
    private fun PathElt(): ASTNode {
        var token: Token
        var result = PathPrimary()
        val t127 = ltit.lookahead()
        if (t127.image == "*" || t127.image == "?" || t127.image == "+") {
            result = PathMod(result)
        }
        return result
    }
    private fun PathEltOrInverse(): ASTNode {
        val token: Token
        val t128 = ltit.lookahead()
        when {
            t128 is IRI || t128 is PNAME_LN || t128 is PNAME_NS || t128.image == "A" || t128.image == "!" || t128.image == "(" -> {
                return PathElt()
            }
            t128.image == "^" -> {
                token = ltit.nextToken()
                if (token.image != "^") {
                    throw UnexpectedToken(token, arrayOf("^"), ltit)
                }
                val inner = PathElt()
                return ASTPathInverse(inner)
            }
            else -> {
                throw UnexpectedToken(t128, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "!", "(", "^"), ltit)
            }
        }
    }
    private fun PathMod(toModify: ASTNode): ASTNode {
        val token: Token
        val t129 = ltit.lookahead()
        when (t129.image) {
            "*" -> {
                token = ltit.nextToken()
                if (token.image != "*") {
                    throw UnexpectedToken(token, arrayOf("*"), ltit)
                }
                return ASTPathArbitraryOccurrences(toModify)
            }
            "?" -> {
                token = ltit.nextToken()
                if (token.image != "?") {
                    throw UnexpectedToken(token, arrayOf("?"), ltit)
                }
                return ASTPathOptionalOccurrence(toModify)
            }
            "+" -> {
                token = ltit.nextToken()
                if (token.image != "+") {
                    throw UnexpectedToken(token, arrayOf("+"), ltit)
                }
                return ASTPathArbitraryOccurrencesNotZero(toModify)
            }
            else -> {
                throw UnexpectedToken(t129, arrayOf("*", "?", "+"), ltit)
            }
        }
    }
    private fun PathPrimary(): ASTNode {
        var token: Token
        val t130 = ltit.lookahead()
        when {
            t130 is IRI || t130 is PNAME_LN || t130 is PNAME_NS -> {
                return IRIref()
            }
            t130.image == "A" -> {
                token = ltit.nextToken()
                if (token.image != "A") {
                    throw UnexpectedToken(token, arrayOf("A"), ltit)
                }
                return ASTType
            }
            t130.image == "!" -> {
                token = ltit.nextToken()
                if (token.image != "!") {
                    throw UnexpectedToken(token, arrayOf("!"), ltit)
                }
                return PathNegatedPropertySet()
            }
            t130.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val path = Path()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return path
            }
            else -> {
                throw UnexpectedToken(t130, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "!", "("), ltit)
            }
        }
    }
    private fun PathNegatedPropertySet(): ASTPathNegatedPropertySet {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val t133 = ltit.lookahead()
        when {
            t133 is IRI || t133 is PNAME_LN || t133 is PNAME_NS || t133.image == "A" || t133.image == "^" -> {
                val one = PathOneInPropertySet()
                collect.add(one)
            }
            t133.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t132 = ltit.lookahead()
                if (t132 is IRI || t132 is PNAME_LN || t132 is PNAME_NS || t132.image == "A" || t132.image == "^") {
                    val first = PathOneInPropertySet()
                    collect.add(first)
                    var t131 = ltit.lookahead()
                    while (t131.image == "|") {
                        token = ltit.nextToken()
                        if (token.image != "|") {
                            throw UnexpectedToken(token, arrayOf("|"), ltit)
                        }
                        val second = PathOneInPropertySet()
                        collect.add(second)
                        t131 = ltit.lookahead()
                    }
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
            }
            else -> {
                throw UnexpectedToken(t133, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "^", "("), ltit)
            }
        }
        return ASTPathNegatedPropertySet(collect.toTypedArray())
    }
    private fun PathOneInPropertySet(): ASTNode {
        var token: Token
        val t135 = ltit.lookahead()
        when {
            t135 is IRI || t135 is PNAME_LN || t135 is PNAME_NS -> {
                return IRIref()
            }
            t135.image == "A" -> {
                token = ltit.nextToken()
                if (token.image != "A") {
                    throw UnexpectedToken(token, arrayOf("A"), ltit)
                }
                return ASTType
            }
            t135.image == "^" -> {
                token = ltit.nextToken()
                if (token.image != "^") {
                    throw UnexpectedToken(token, arrayOf("^"), ltit)
                }
                val inner: ASTNode
                val t134 = ltit.lookahead()
                when {
                    t134 is IRI || t134 is PNAME_LN || t134 is PNAME_NS -> {
                        inner = IRIref()
                    }
                    t134.image == "A" -> {
                        token = ltit.nextToken()
                        if (token.image != "A") {
                            throw UnexpectedToken(token, arrayOf("A"), ltit)
                        }
                        inner = ASTType
                    }
                    else -> {
                        throw UnexpectedToken(t134, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A"), ltit)
                    }
                }
                return ASTPathInverse(inner)
            }
            else -> {
                throw UnexpectedToken(t135, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "A", "^"), ltit)
            }
        }
    }
    private fun TriplesNode(result: MutableList<ASTNode>): ASTNode {
        var token: Token
        val t136 = ltit.lookahead()
        return when (t136.image) {
            "(" -> {
                val c = Collection(result)
                c
            }
            "[" -> {
                BlankNodePropertyList(result)
            }
            else -> {
                throw UnexpectedToken(t136, arrayOf("(", "["), ltit)
            }
        }
    }
    private fun BlankNodePropertyList(result: MutableList<ASTNode>): ASTBlankNode {
        val b = ASTBlankNode()
        var token: Token = ltit.nextToken()
        if (token.image != "[") {
            throw UnexpectedToken(token, arrayOf("["), ltit)
        }
        PropertyListNotEmpty(b, result)
        token = ltit.nextToken()
        if (token.image != "]") {
            throw UnexpectedToken(token, arrayOf("]"), ltit)
        }
        return b
    }
    private fun Collection(result: MutableList<ASTNode>): ASTBlankNode {
        val subject = ASTBlankNode()
        var current = subject
        var token: Token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val gn = GraphNode(result)
        result.add(ASTTriple(current, ASTFirst, gn))
        var t137 = ltit.lookahead()
        while (t137 is VAR || t137 is IRI || t137 is PNAME_LN || t137 is PNAME_NS || t137 is STRING || t137 is INTEGER || t137 is DECIMAL || t137 is DOUBLE || t137.image == "+" || t137.image == "-" || t137.image == "TRUE" || t137.image == "FALSE" || t137 is BNODE || t137 is ANON_BNODE || t137 is NIL || t137.image == "(" || t137.image == "[") {
            val gn2 = GraphNode(result)
            val next = ASTBlankNode()
            result.add(ASTTriple(current, ASTRest, next))
            result.add(ASTTriple(next, ASTFirst, gn2))
            current = next
            t137 = ltit.lookahead()
        }
        result.add(ASTTriple(current, ASTRest, ASTNil))
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return subject
    }
    private fun GraphNode(result: MutableList<ASTNode>): ASTNode {
        var token: Token
        val t138 = ltit.lookahead()
        return when {
            t138 is VAR || t138 is IRI || t138 is PNAME_LN || t138 is PNAME_NS || t138 is STRING || t138 is INTEGER || t138 is DECIMAL || t138 is DOUBLE || t138.image == "+" || t138.image == "-" || t138.image == "TRUE" || t138.image == "FALSE" || t138 is BNODE || t138 is ANON_BNODE || t138 is NIL -> {
                VarOrTerm()
            }
            t138.image == "(" || t138.image == "[" -> {
                TriplesNode(result)
            }
            else -> {
                throw UnexpectedToken(t138, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL", "(", "["), ltit)
            }
        }
    }
    private fun VarOrTerm(): ASTNode {
        var token: Token
        val result: ASTNode
        val t139 = ltit.lookahead()
        result = when {
            t139 is VAR -> {
                Var()
            }
            t139 is IRI || t139 is PNAME_LN || t139 is PNAME_NS || t139 is STRING || t139 is INTEGER || t139 is DECIMAL || t139 is DOUBLE || t139.image == "+" || t139.image == "-" || t139.image == "TRUE" || t139.image == "FALSE" || t139 is BNODE || t139 is ANON_BNODE || t139 is NIL -> {
                GraphTerm()
            }
            else -> {
                throw UnexpectedToken(t139, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL"), ltit)
            }
        }
        return result
    }
    private fun VarOrIRIref(): ASTNode {
        var token: Token
        val result: ASTNode
        val t140 = ltit.lookahead()
        result = when (t140) {
            is VAR -> {
                Var()
            }
            is IRI, is PNAME_LN, is PNAME_NS -> {
                IRIref()
            }
            else -> {
                throw UnexpectedToken(t140, arrayOf("VAR", "IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        return result
    }
    private fun Var(): ASTVar {
        val token: Token = ltit.nextToken()
        if (token !is VAR) {
            throw UnexpectedToken(token, arrayOf("VAR"), ltit)
        }
        return ASTVar(token.identifier)
    }
    private fun GraphTerm(): ASTNode {
        val token: Token
        val result: ASTNode
        val t141 = ltit.lookahead()
        when {
            t141 is IRI || t141 is PNAME_LN || t141 is PNAME_NS -> {
                result = IRIref()
            }
            t141 is STRING -> {
                result = RDFLiteral()
            }
            t141 is INTEGER || t141 is DECIMAL || t141 is DOUBLE || t141.image == "+" || t141.image == "-" -> {
                result = NumericLiteral()
            }
            t141.image == "TRUE" || t141.image == "FALSE" -> {
                result = BooleanLiteral()
            }
            t141 is BNODE || t141 is ANON_BNODE -> {
                result = BlankNode()
            }
            t141 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
                result = ASTIri(nil)
            }
            else -> {
                throw UnexpectedToken(t141, arrayOf("IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "BNODE", "ANON_BNODE", "NIL"), ltit)
            }
        }
        return result
    }
    private fun Expression(): ASTNode {
        var token: Token
        return ConditionalOrExpression()
    }
    private fun ConditionalOrExpression(): ASTNode {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val first = ConditionalAndExpression()
        collect.add(first)
        var t142 = ltit.lookahead()
        while (t142.image == "||") {
            token = ltit.nextToken()
            if (token.image != "||") {
                throw UnexpectedToken(token, arrayOf("||"), ltit)
            }
            val second = ConditionalAndExpression()
            collect.add(second)
            t142 = ltit.lookahead()
        }
        return if (collect.size == 1) {
            first
        } else {
            ASTOr(collect.toTypedArray())
        }
    }
    private fun ConditionalAndExpression(): ASTNode {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        val first = ValueLogical()
        collect.add(first)
        var t143 = ltit.lookahead()
        while (t143.image == "&&") {
            token = ltit.nextToken()
            if (token.image != "&&") {
                throw UnexpectedToken(token, arrayOf("&&"), ltit)
            }
            val second = ValueLogical()
            collect.add(second)
            t143 = ltit.lookahead()
        }
        return if (collect.size == 1) {
            first
        } else {
            ASTAnd(collect.toTypedArray())
        }
    }
    private fun ValueLogical(): ASTNode {
        var token: Token
        return RelationalExpression()
    }
    private fun RelationalExpression(): ASTNode {
        var token: Token
        val left = NumericExpression()
        val t145 = ltit.lookahead()
        if (t145.image == "=" || t145.image == "!=" || t145.image == "<" || t145.image == ">" || t145.image == "<=" || t145.image == ">=" || t145.image == "IN" || t145.image == "NOT") {
            val t144 = ltit.lookahead()
            when (t144.image) {
                "=" -> {
                    token = ltit.nextToken()
                    if (token.image != "=") {
                        throw UnexpectedToken(token, arrayOf("="), ltit)
                    }
                    val right = NumericExpression()
                    return ASTEQ(left, right)
                }
                "!=" -> {
                    token = ltit.nextToken()
                    if (token.image != "!=") {
                        throw UnexpectedToken(token, arrayOf("!="), ltit)
                    }
                    val right = NumericExpression()
                    return ASTNEQ(left, right)
                }
                "<" -> {
                    token = ltit.nextToken()
                    if (token.image != "<") {
                        throw UnexpectedToken(token, arrayOf("<"), ltit)
                    }
                    val right = NumericExpression()
                    return ASTLT(left, right)
                }
                ">" -> {
                    token = ltit.nextToken()
                    if (token.image != ">") {
                        throw UnexpectedToken(token, arrayOf(">"), ltit)
                    }
                    val right = NumericExpression()
                    return ASTGT(left, right)
                }
                "<=" -> {
                    token = ltit.nextToken()
                    if (token.image != "<=") {
                        throw UnexpectedToken(token, arrayOf("<="), ltit)
                    }
                    val right = NumericExpression()
                    return ASTLEQ(left, right)
                }
                ">=" -> {
                    token = ltit.nextToken()
                    if (token.image != ">=") {
                        throw UnexpectedToken(token, arrayOf(">="), ltit)
                    }
                    val right = NumericExpression()
                    return ASTGEQ(left, right)
                }
                "IN" -> {
                    token = ltit.nextToken()
                    if (token.image != "IN") {
                        throw UnexpectedToken(token, arrayOf("IN"), ltit)
                    }
                    val right = ExpressionList()
                    return ASTIn(left, ASTSet(right))
                }
                "NOT" -> {
                    token = ltit.nextToken()
                    if (token.image != "NOT") {
                        throw UnexpectedToken(token, arrayOf("NOT"), ltit)
                    }
                    token = ltit.nextToken()
                    if (token.image != "IN") {
                        throw UnexpectedToken(token, arrayOf("IN"), ltit)
                    }
                    val right = ExpressionList()
                    return ASTNotIn(left, ASTSet(right))
                }
                else -> {
                    throw UnexpectedToken(t144, arrayOf("=", "!=", "<", ">", "<=", ">=", "IN", "NOT"), ltit)
                }
            }
        }
        return left
    }
    private fun NumericExpression(): ASTNode {
        var token: Token
        return AdditiveExpression()
    }
    private fun AdditiveExpression(): ASTNode {
        var token: Token
        var current = MultiplicativeExpression()
        var t147 = ltit.lookahead()
        while (t147.image == "+" || t147.image == "-") {
            val t146 = ltit.lookahead()
            when (t146.image) {
                "+" -> {
                    token = ltit.nextToken()
                    if (token.image != "+") {
                        throw UnexpectedToken(token, arrayOf("+"), ltit)
                    }
                    val right = MultiplicativeExpression()
                    current = ASTAddition(current, right)
                }
                "-" -> {
                    token = ltit.nextToken()
                    if (token.image != "-") {
                        throw UnexpectedToken(token, arrayOf("-"), ltit)
                    }
                    val right = MultiplicativeExpression()
                    current = ASTSubtraction(current, right)
                }
                else -> {
                    throw UnexpectedToken(t146, arrayOf("+", "-"), ltit)
                }
            }
            t147 = ltit.lookahead()
        }
        return current
    }
    private fun MultiplicativeExpression(): ASTNode {
        var token: Token
        var current = UnaryExpression()
        var t149 = ltit.lookahead()
        while (t149.image == "*" || t149.image == "/") {
            val t148 = ltit.lookahead()
            when (t148.image) {
                "*" -> {
                    token = ltit.nextToken()
                    if (token.image != "*") {
                        throw UnexpectedToken(token, arrayOf("*"), ltit)
                    }
                    val right = UnaryExpression()
                    current = ASTMultiplication(current, right)
                }
                "/" -> {
                    token = ltit.nextToken()
                    if (token.image != "/") {
                        throw UnexpectedToken(token, arrayOf("/"), ltit)
                    }
                    val right = UnaryExpression()
                    current = ASTDivision(current, right)
                }
                else -> {
                    throw UnexpectedToken(t148, arrayOf("*", "/"), ltit)
                }
            }
            t149 = ltit.lookahead()
        }
        return current
    }
    private fun UnaryExpression(): ASTNode {
        var token: Token
        val t152 = ltit.lookahead()
        when {
            t152.image == "!" -> {
                token = ltit.nextToken()
                if (token.image != "!") {
                    throw UnexpectedToken(token, arrayOf("!"), ltit)
                }
                val not = PrimaryExpression()
                return ASTNot(not)
            }
            t152.image == "+" -> {
                token = ltit.nextToken()
                if (token.image != "+") {
                    throw UnexpectedToken(token, arrayOf("+"), ltit)
                }
                val t150 = ltit.lookahead()
                return when {
                    t150.image == "(" || t150.image == "STR" || t150.image == "LANG" || t150.image == "LANGMATCHES" || t150.image == "DATATYPE" || t150.image == "BOUND" || t150.image == "IRI" || t150.image == "URI" || t150.image == "BNODE" || t150.image == "RAND" || t150.image == "ABS" || t150.image == "CEIL" || t150.image == "FLOOR" || t150.image == "ROUND" || t150.image == "CONCAT" || t150.image == "SUBSTR" || t150.image == "STRLEN" || t150.image == "REPLACE" || t150.image == "UCASE" || t150.image == "LCASE" || t150.image == "ENCODE_FOR_URI" || t150.image == "CONTAINS" || t150.image == "STRSTARTS" || t150.image == "STRENDS" || t150.image == "STRBEFORE" || t150.image == "STRAFTER" || t150.image == "YEAR" || t150.image == "MONTH" || t150.image == "DAY" || t150.image == "HOURS" || t150.image == "MINUTES" || t150.image == "SECONDS" || t150.image == "TIMEZONE" || t150.image == "TZ" || t150.image == "NOW" || t150.image == "UUID" || t150.image == "STRUUID" || t150.image == "MD5" || t150.image == "SHA1" || t150.image == "SHA256" || t150.image == "SHA384" || t150.image == "SHA512" || t150.image == "COALESCE" || t150.image == "IF" || t150.image == "STRLANG" || t150.image == "STRDT" || t150.image == "SAMETERM" || t150.image == "ISIRI" || t150.image == "ISURI" || t150.image == "ISBLANK" || t150.image == "ISLITERAL" || t150.image == "ISNUMERIC" || t150.image == "REGEX" || t150.image == "EXISTS" || t150.image == "NOT" || t150 is IRI || t150 is PNAME_LN || t150 is PNAME_NS || t150 is STRING || t150.image == "TRUE" || t150.image == "FALSE" || t150 is VAR || t150.image == "COUNT" || t150.image == "SUM" || t150.image == "MIN" || t150.image == "MAX" || t150.image == "AVG" || t150.image == "SAMPLE" || t150.image == "GROUP_CONCAT" -> {
                        val inner = PrimaryExpressionWithoutNumericLiteral()
                        ASTPlus(inner)
                    }
                    t150 is INTEGER || t150 is DECIMAL || t150 is DOUBLE -> {
                        val result = NumericLiteralUnsigned()
                        result
                    }
                    else -> {
                        throw UnexpectedToken(t150, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT", "INTEGER", "DECIMAL", "DOUBLE"), ltit)
                    }
                }
            }
            t152.image == "-" -> {
                token = ltit.nextToken()
                if (token.image != "-") {
                    throw UnexpectedToken(token, arrayOf("-"), ltit)
                }
                val t151 = ltit.lookahead()
                when {
                    t151.image == "(" || t151.image == "STR" || t151.image == "LANG" || t151.image == "LANGMATCHES" || t151.image == "DATATYPE" || t151.image == "BOUND" || t151.image == "IRI" || t151.image == "URI" || t151.image == "BNODE" || t151.image == "RAND" || t151.image == "ABS" || t151.image == "CEIL" || t151.image == "FLOOR" || t151.image == "ROUND" || t151.image == "CONCAT" || t151.image == "SUBSTR" || t151.image == "STRLEN" || t151.image == "REPLACE" || t151.image == "UCASE" || t151.image == "LCASE" || t151.image == "ENCODE_FOR_URI" || t151.image == "CONTAINS" || t151.image == "STRSTARTS" || t151.image == "STRENDS" || t151.image == "STRBEFORE" || t151.image == "STRAFTER" || t151.image == "YEAR" || t151.image == "MONTH" || t151.image == "DAY" || t151.image == "HOURS" || t151.image == "MINUTES" || t151.image == "SECONDS" || t151.image == "TIMEZONE" || t151.image == "TZ" || t151.image == "NOW" || t151.image == "UUID" || t151.image == "STRUUID" || t151.image == "MD5" || t151.image == "SHA1" || t151.image == "SHA256" || t151.image == "SHA384" || t151.image == "SHA512" || t151.image == "COALESCE" || t151.image == "IF" || t151.image == "STRLANG" || t151.image == "STRDT" || t151.image == "SAMETERM" || t151.image == "ISIRI" || t151.image == "ISURI" || t151.image == "ISBLANK" || t151.image == "ISLITERAL" || t151.image == "ISNUMERIC" || t151.image == "REGEX" || t151.image == "EXISTS" || t151.image == "NOT" || t151 is IRI || t151 is PNAME_LN || t151 is PNAME_NS || t151 is STRING || t151.image == "TRUE" || t151.image == "FALSE" || t151 is VAR || t151.image == "COUNT" || t151.image == "SUM" || t151.image == "MIN" || t151.image == "MAX" || t151.image == "AVG" || t151.image == "SAMPLE" || t151.image == "GROUP_CONCAT" -> {
                        val inner = PrimaryExpressionWithoutNumericLiteral()
                        return ASTMinus(inner)
                    }
                    t151 is INTEGER -> {
                        token = ltit.nextToken()
                        if (token !is INTEGER) {
                            throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                        }
                        return ASTInteger("-" + token.image)
                    }
                    t151 is DECIMAL -> {
                        token = ltit.nextToken()
                        if (token !is DECIMAL) {
                            throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                        }
                        return ASTDouble("-" + token.image)
                    }
                    t151 is DOUBLE -> {
                        token = ltit.nextToken()
                        if (token !is DOUBLE) {
                            throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                        }
                        return ASTDouble("-" + token.image)
                    }
                    else -> {
                        throw UnexpectedToken(t151, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT", "INTEGER", "DECIMAL", "DOUBLE"), ltit)
                    }
                }
            }
            t152.image == "(" || t152.image == "STR" || t152.image == "LANG" || t152.image == "LANGMATCHES" || t152.image == "DATATYPE" || t152.image == "BOUND" || t152.image == "IRI" || t152.image == "URI" || t152.image == "BNODE" || t152.image == "RAND" || t152.image == "ABS" || t152.image == "CEIL" || t152.image == "FLOOR" || t152.image == "ROUND" || t152.image == "CONCAT" || t152.image == "SUBSTR" || t152.image == "STRLEN" || t152.image == "REPLACE" || t152.image == "UCASE" || t152.image == "LCASE" || t152.image == "ENCODE_FOR_URI" || t152.image == "CONTAINS" || t152.image == "STRSTARTS" || t152.image == "STRENDS" || t152.image == "STRBEFORE" || t152.image == "STRAFTER" || t152.image == "YEAR" || t152.image == "MONTH" || t152.image == "DAY" || t152.image == "HOURS" || t152.image == "MINUTES" || t152.image == "SECONDS" || t152.image == "TIMEZONE" || t152.image == "TZ" || t152.image == "NOW" || t152.image == "UUID" || t152.image == "STRUUID" || t152.image == "MD5" || t152.image == "SHA1" || t152.image == "SHA256" || t152.image == "SHA384" || t152.image == "SHA512" || t152.image == "COALESCE" || t152.image == "IF" || t152.image == "STRLANG" || t152.image == "STRDT" || t152.image == "SAMETERM" || t152.image == "ISIRI" || t152.image == "ISURI" || t152.image == "ISBLANK" || t152.image == "ISLITERAL" || t152.image == "ISNUMERIC" || t152.image == "REGEX" || t152.image == "EXISTS" || t152.image == "NOT" || t152 is IRI || t152 is PNAME_LN || t152 is PNAME_NS || t152 is STRING || t152.image == "TRUE" || t152.image == "FALSE" || t152 is VAR || t152.image == "COUNT" || t152.image == "SUM" || t152.image == "MIN" || t152.image == "MAX" || t152.image == "AVG" || t152.image == "SAMPLE" || t152.image == "GROUP_CONCAT" -> {
                return PrimaryExpressionWithoutNumericLiteral()
            }
            t152 is INTEGER || t152 is DECIMAL || t152 is DOUBLE -> {
                return NumericLiteralUnsigned()
            }
            else -> {
                throw UnexpectedToken(t152, arrayOf("!", "+", "-", "(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT", "INTEGER", "DECIMAL", "DOUBLE"), ltit)
            }
        }
    }
    private fun PrimaryExpressionWithoutNumericLiteral(): ASTNode {
        var token: Token
        val result: ASTNode
        val t153 = ltit.lookahead()
        when {
            t153.image == "(" -> {
                result = BrackettedExpression()
            }
            t153.image == "STR" || t153.image == "LANG" || t153.image == "LANGMATCHES" || t153.image == "DATATYPE" || t153.image == "BOUND" || t153.image == "IRI" || t153.image == "URI" || t153.image == "BNODE" || t153.image == "RAND" || t153.image == "ABS" || t153.image == "CEIL" || t153.image == "FLOOR" || t153.image == "ROUND" || t153.image == "CONCAT" || t153.image == "SUBSTR" || t153.image == "STRLEN" || t153.image == "REPLACE" || t153.image == "UCASE" || t153.image == "LCASE" || t153.image == "ENCODE_FOR_URI" || t153.image == "CONTAINS" || t153.image == "STRSTARTS" || t153.image == "STRENDS" || t153.image == "STRBEFORE" || t153.image == "STRAFTER" || t153.image == "YEAR" || t153.image == "MONTH" || t153.image == "DAY" || t153.image == "HOURS" || t153.image == "MINUTES" || t153.image == "SECONDS" || t153.image == "TIMEZONE" || t153.image == "TZ" || t153.image == "NOW" || t153.image == "UUID" || t153.image == "STRUUID" || t153.image == "MD5" || t153.image == "SHA1" || t153.image == "SHA256" || t153.image == "SHA384" || t153.image == "SHA512" || t153.image == "COALESCE" || t153.image == "IF" || t153.image == "STRLANG" || t153.image == "STRDT" || t153.image == "SAMETERM" || t153.image == "ISIRI" || t153.image == "ISURI" || t153.image == "ISBLANK" || t153.image == "ISLITERAL" || t153.image == "ISNUMERIC" || t153.image == "REGEX" || t153.image == "EXISTS" || t153.image == "NOT" -> {
                result = BuiltInCall()
            }
            t153 is IRI || t153 is PNAME_LN || t153 is PNAME_NS -> {
                result = IRIrefOrFunction()
            }
            t153 is STRING -> {
                result = RDFLiteral()
            }
            t153.image == "TRUE" || t153.image == "FALSE" -> {
                result = BooleanLiteral()
            }
            t153 is VAR -> {
                result = Var()
            }
            t153.image == "COUNT" || t153.image == "SUM" || t153.image == "MIN" || t153.image == "MAX" || t153.image == "AVG" || t153.image == "SAMPLE" || t153.image == "GROUP_CONCAT" -> {
                result = Aggregate()
            }
            else -> {
                throw UnexpectedToken(t153, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT"), ltit)
            }
        }
        return result
    }
    private fun PrimaryExpression(): ASTNode {
        var token: Token
        val result: ASTNode
        val t154 = ltit.lookahead()
        when {
            t154.image == "(" -> {
                result = BrackettedExpression()
            }
            t154.image == "STR" || t154.image == "LANG" || t154.image == "LANGMATCHES" || t154.image == "DATATYPE" || t154.image == "BOUND" || t154.image == "IRI" || t154.image == "URI" || t154.image == "BNODE" || t154.image == "RAND" || t154.image == "ABS" || t154.image == "CEIL" || t154.image == "FLOOR" || t154.image == "ROUND" || t154.image == "CONCAT" || t154.image == "SUBSTR" || t154.image == "STRLEN" || t154.image == "REPLACE" || t154.image == "UCASE" || t154.image == "LCASE" || t154.image == "ENCODE_FOR_URI" || t154.image == "CONTAINS" || t154.image == "STRSTARTS" || t154.image == "STRENDS" || t154.image == "STRBEFORE" || t154.image == "STRAFTER" || t154.image == "YEAR" || t154.image == "MONTH" || t154.image == "DAY" || t154.image == "HOURS" || t154.image == "MINUTES" || t154.image == "SECONDS" || t154.image == "TIMEZONE" || t154.image == "TZ" || t154.image == "NOW" || t154.image == "UUID" || t154.image == "STRUUID" || t154.image == "MD5" || t154.image == "SHA1" || t154.image == "SHA256" || t154.image == "SHA384" || t154.image == "SHA512" || t154.image == "COALESCE" || t154.image == "IF" || t154.image == "STRLANG" || t154.image == "STRDT" || t154.image == "SAMETERM" || t154.image == "ISIRI" || t154.image == "ISURI" || t154.image == "ISBLANK" || t154.image == "ISLITERAL" || t154.image == "ISNUMERIC" || t154.image == "REGEX" || t154.image == "EXISTS" || t154.image == "NOT" -> {
                result = BuiltInCall()
            }
            t154 is IRI || t154 is PNAME_LN || t154 is PNAME_NS -> {
                result = IRIrefOrFunction()
            }
            t154 is STRING -> {
                result = RDFLiteral()
            }
            t154 is INTEGER || t154 is DECIMAL || t154 is DOUBLE || t154.image == "+" || t154.image == "-" -> {
                result = NumericLiteral()
            }
            t154.image == "TRUE" || t154.image == "FALSE" -> {
                result = BooleanLiteral()
            }
            t154 is VAR -> {
                result = Var()
            }
            t154.image == "COUNT" || t154.image == "SUM" || t154.image == "MIN" || t154.image == "MAX" || t154.image == "AVG" || t154.image == "SAMPLE" || t154.image == "GROUP_CONCAT" -> {
                result = Aggregate()
            }
            else -> {
                throw UnexpectedToken(t154, arrayOf("(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "INTEGER", "DECIMAL", "DOUBLE", "+", "-", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT"), ltit)
            }
        }
        return result
    }
    private fun BrackettedExpression(): ASTNode {
        var token: Token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val result = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return result
    }
    private fun BuiltInCall(): ASTBuiltInCall {
        var token: Token
        val result: ASTBuiltInCall
        val t155 = ltit.lookahead()
        when (t155.image) {
            "STR" -> {
                result = STR()
            }
            "LANG" -> {
                result = LANG()
            }
            "LANGMATCHES" -> {
                result = LANGMATCHES()
            }
            "DATATYPE" -> {
                result = DATATYPE()
            }
            "BOUND" -> {
                result = BOUND()
            }
            "IRI" -> {
                result = IRIFunc()
            }
            "URI" -> {
                result = URIFunc()
            }
            "BNODE" -> {
                result = BNODE()
            }
            "RAND" -> {
                result = RAND()
            }
            "ABS" -> {
                result = ABS()
            }
            "CEIL" -> {
                result = CEIL()
            }
            "FLOOR" -> {
                result = FLOOR()
            }
            "ROUND" -> {
                result = ROUND()
            }
            "CONCAT" -> {
                result = CONCAT()
            }
            "SUBSTR" -> {
                result = SubstringExpression()
            }
            "STRLEN" -> {
                result = STRLEN()
            }
            "REPLACE" -> {
                result = StrReplaceExpression()
            }
            "UCASE" -> {
                result = UCASE()
            }
            "LCASE" -> {
                result = LCASE()
            }
            "ENCODE_FOR_URI" -> {
                result = ENCODE_FOR_URI()
            }
            "CONTAINS" -> {
                result = CONTAINS()
            }
            "STRSTARTS" -> {
                result = STRSTARTS()
            }
            "STRENDS" -> {
                result = STRENDS()
            }
            "STRBEFORE" -> {
                result = STRBEFORE()
            }
            "STRAFTER" -> {
                result = STRAFTER()
            }
            "YEAR" -> {
                result = YEAR()
            }
            "MONTH" -> {
                result = MONTH()
            }
            "DAY" -> {
                result = DAY()
            }
            "HOURS" -> {
                result = HOURS()
            }
            "MINUTES" -> {
                result = MINUTES()
            }
            "SECONDS" -> {
                result = SECONDS()
            }
            "TIMEZONE" -> {
                result = TIMEZONE()
            }
            "TZ" -> {
                result = TZ()
            }
            "NOW" -> {
                result = NOW()
            }
            "UUID" -> {
                result = UUID()
            }
            "STRUUID" -> {
                result = STRUUID()
            }
            "MD5" -> {
                result = MD5()
            }
            "SHA1" -> {
                result = SHA1()
            }
            "SHA256" -> {
                result = SHA256()
            }
            "SHA384" -> {
                result = SHA384()
            }
            "SHA512" -> {
                result = SHA512()
            }
            "COALESCE" -> {
                result = COALESCE()
            }
            "IF" -> {
                result = IF()
            }
            "STRLANG" -> {
                result = STRLANG()
            }
            "STRDT" -> {
                result = STRDT()
            }
            "SAMETERM" -> {
                result = sameTerm()
            }
            "ISIRI" -> {
                result = isIRI()
            }
            "ISURI" -> {
                result = isURI()
            }
            "ISBLANK" -> {
                result = isBLANK()
            }
            "ISLITERAL" -> {
                result = isLITERAL()
            }
            "ISNUMERIC" -> {
                result = isNUMERIC()
            }
            "REGEX" -> {
                result = RegexExpression()
            }
            "EXISTS" -> {
                result = ExistsFunc()
            }
            "NOT" -> {
                result = NotExistsFunc()
            }
            else -> {
                throw UnexpectedToken(t155, arrayOf("STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT"), ltit)
            }
        }
        return result
    }
    private fun STR(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STR") {
            throw UnexpectedToken(token, arrayOf("STR"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STR, param)
    }
    private fun LANG(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "LANG") {
            throw UnexpectedToken(token, arrayOf("LANG"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.LANG, param)
    }
    private fun LANGMATCHES(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "LANGMATCHES") {
            throw UnexpectedToken(token, arrayOf("LANGMATCHES"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.LANGMATCHES, param1, param2)
    }
    private fun DATATYPE(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "DATATYPE") {
            throw UnexpectedToken(token, arrayOf("DATATYPE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.DATATYPE, param)
    }
    private fun BOUND(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "BOUND") {
            throw UnexpectedToken(token, arrayOf("BOUND"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Var()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.BOUND, param)
    }
    private fun IRIFunc(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "IRI") {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.IRI, param)
    }
    private fun URIFunc(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "URI") {
            throw UnexpectedToken(token, arrayOf("URI"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.URI, param)
    }
    private fun BNODE(): ASTBuiltInCall {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "BNODE") {
            throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
        }
        val t156 = ltit.lookahead()
        when {
            t156.image == "(" -> {
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val param = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTBuiltInCall(BuiltInFunctions.BNODE, param)
            }
            t156 is NIL -> {
                token = ltit.nextToken()
                if (token !is NIL) {
                    throw UnexpectedToken(token, arrayOf("NIL"), ltit)
                }
                return ASTBuiltInCall(BuiltInFunctions.BNODE)
            }
            else -> {
                throw UnexpectedToken(t156, arrayOf("(", "NIL"), ltit)
            }
        }
    }
    private fun RAND(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "RAND") {
            throw UnexpectedToken(token, arrayOf("RAND"), ltit)
        }
        token = ltit.nextToken()
        if (token !is NIL) {
            throw UnexpectedToken(token, arrayOf("NIL"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.RAND)
    }
    private fun ABS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ABS") {
            throw UnexpectedToken(token, arrayOf("ABS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.ABS, param)
    }
    private fun CEIL(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "CEIL") {
            throw UnexpectedToken(token, arrayOf("CEIL"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.CEIL, param)
    }
    private fun FLOOR(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "FLOOR") {
            throw UnexpectedToken(token, arrayOf("FLOOR"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.FLOOR, param)
    }
    private fun ROUND(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ROUND") {
            throw UnexpectedToken(token, arrayOf("ROUND"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.ROUND, param)
    }
    private fun CONCAT(): ASTBuiltInCall {
        val token: Token = ltit.nextToken()
        if (token.image != "CONCAT") {
            throw UnexpectedToken(token, arrayOf("CONCAT"), ltit)
        }
        val params = ExpressionList()
        return ASTBuiltInCall(BuiltInFunctions.CONCAT, params)
    }
    private fun STRLEN(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRLEN") {
            throw UnexpectedToken(token, arrayOf("STRLEN"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRLEN, param)
    }
    private fun UCASE(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "UCASE") {
            throw UnexpectedToken(token, arrayOf("UCASE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.UCASE, param)
    }
    private fun LCASE(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "LCASE") {
            throw UnexpectedToken(token, arrayOf("LCASE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.LCASE, param)
    }
    private fun ENCODE_FOR_URI(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ENCODE_FOR_URI") {
            throw UnexpectedToken(token, arrayOf("ENCODE_FOR_URI"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.ENCODE_FOR_URI, param)
    }
    private fun CONTAINS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "CONTAINS") {
            throw UnexpectedToken(token, arrayOf("CONTAINS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.CONTAINS, param1, param2)
    }
    private fun STRSTARTS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRSTARTS") {
            throw UnexpectedToken(token, arrayOf("STRSTARTS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRSTARTS, param1, param2)
    }
    private fun STRENDS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRENDS") {
            throw UnexpectedToken(token, arrayOf("STRENDS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRENDS, param1, param2)
    }
    private fun STRBEFORE(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRBEFORE") {
            throw UnexpectedToken(token, arrayOf("STRBEFORE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRBEFORE, param1, param2)
    }
    private fun STRAFTER(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRAFTER") {
            throw UnexpectedToken(token, arrayOf("STRAFTER"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRAFTER, param1, param2)
    }
    public fun YEAR(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "YEAR") {
            throw UnexpectedToken(token, arrayOf("YEAR"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.YEAR, param)
    }
    public fun MONTH(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "MONTH") {
            throw UnexpectedToken(token, arrayOf("MONTH"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.MONTH, param)
    }
    private fun DAY(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "DAY") {
            throw UnexpectedToken(token, arrayOf("DAY"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.DAY, param)
    }
    private fun HOURS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "HOURS") {
            throw UnexpectedToken(token, arrayOf("HOURS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.HOURS, param)
    }
    private fun MINUTES(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "MINUTES") {
            throw UnexpectedToken(token, arrayOf("MINUTES"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.MINUTES, param)
    }
    private fun SECONDS(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SECONDS") {
            throw UnexpectedToken(token, arrayOf("SECONDS"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SECONDS, param)
    }
    private fun TIMEZONE(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "TIMEZONE") {
            throw UnexpectedToken(token, arrayOf("TIMEZONE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.TIMEZONE, param)
    }
    private fun TZ(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "TZ") {
            throw UnexpectedToken(token, arrayOf("TZ"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.TZ, param)
    }
    private fun NOW(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "NOW") {
            throw UnexpectedToken(token, arrayOf("NOW"), ltit)
        }
        token = ltit.nextToken()
        if (token !is NIL) {
            throw UnexpectedToken(token, arrayOf("NIL"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.NOW)
    }
    private fun UUID(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "UUID") {
            throw UnexpectedToken(token, arrayOf("UUID"), ltit)
        }
        token = ltit.nextToken()
        if (token !is NIL) {
            throw UnexpectedToken(token, arrayOf("NIL"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.UUID)
    }
    private fun STRUUID(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRUUID") {
            throw UnexpectedToken(token, arrayOf("STRUUID"), ltit)
        }
        token = ltit.nextToken()
        if (token !is NIL) {
            throw UnexpectedToken(token, arrayOf("NIL"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRUUID)
    }
    private fun MD5(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "MD5") {
            throw UnexpectedToken(token, arrayOf("MD5"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.MD5, param)
    }
    private fun SHA1(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SHA1") {
            throw UnexpectedToken(token, arrayOf("SHA1"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SHA1, param)
    }
    private fun SHA256(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SHA256") {
            throw UnexpectedToken(token, arrayOf("SHA256"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SHA256, param)
    }
    private fun SHA384(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SHA384") {
            throw UnexpectedToken(token, arrayOf("SHA384"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SHA384, param)
    }
    private fun SHA512(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SHA512") {
            throw UnexpectedToken(token, arrayOf("SHA512"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SHA512, param)
    }
    private fun COALESCE(): ASTBuiltInCall {
        val token: Token = ltit.nextToken()
        if (token.image != "COALESCE") {
            throw UnexpectedToken(token, arrayOf("COALESCE"), ltit)
        }
        val params = ExpressionList()
        return ASTBuiltInCall(BuiltInFunctions.COALESCE, params)
    }
    private fun IF(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "IF") {
            throw UnexpectedToken(token, arrayOf("IF"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param3 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.IF, param1, param2, param3)
    }
    private fun STRLANG(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRLANG") {
            throw UnexpectedToken(token, arrayOf("STRLANG"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRLANG, param1, param2)
    }
    private fun STRDT(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "STRDT") {
            throw UnexpectedToken(token, arrayOf("STRDT"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.STRDT, param1, param2)
    }
    private fun sameTerm(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "SAMETERM") {
            throw UnexpectedToken(token, arrayOf("SAMETERM"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.sameTerm, param1, param2)
    }
    private fun isIRI(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ISIRI") {
            throw UnexpectedToken(token, arrayOf("ISIRI"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.isIRI, param)
    }
    private fun isURI(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ISURI") {
            throw UnexpectedToken(token, arrayOf("ISURI"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.isURI, param)
    }
    private fun isBLANK(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ISBLANK") {
            throw UnexpectedToken(token, arrayOf("ISBLANK"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.isBLANK, param)
    }
    private fun isLITERAL(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ISLITERAL") {
            throw UnexpectedToken(token, arrayOf("ISLITERAL"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.isLITERAL, param)
    }
    private fun isNUMERIC(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "ISNUMERIC") {
            throw UnexpectedToken(token, arrayOf("ISNUMERIC"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param = Expression()
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.isNUMERIC, param)
    }
    private fun RegexExpression(): ASTBuiltInCall {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        token = ltit.nextToken()
        if (token.image != "REGEX") {
            throw UnexpectedToken(token, arrayOf("REGEX"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        collect.add(param1)
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        collect.add(param2)
        val t157 = ltit.lookahead()
        if (t157.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val param3 = Expression()
            collect.add(param3)
        }
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.RegexExpression, collect.toTypedArray())
    }
    private fun SubstringExpression(): ASTBuiltInCall {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        token = ltit.nextToken()
        if (token.image != "SUBSTR") {
            throw UnexpectedToken(token, arrayOf("SUBSTR"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        collect.add(param1)
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        collect.add(param2)
        val t158 = ltit.lookahead()
        if (t158.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val param3 = Expression()
            collect.add(param3)
        }
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.SubstringExpression, collect.toTypedArray())
    }
    private fun StrReplaceExpression(): ASTBuiltInCall {
        var token: Token
        val collect: MutableList<ASTNode> = mutableListOf()
        token = ltit.nextToken()
        if (token.image != "REPLACE") {
            throw UnexpectedToken(token, arrayOf("REPLACE"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "(") {
            throw UnexpectedToken(token, arrayOf("("), ltit)
        }
        val param1 = Expression()
        collect.add(param1)
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param2 = Expression()
        collect.add(param2)
        token = ltit.nextToken()
        if (token.image != ",") {
            throw UnexpectedToken(token, arrayOf(","), ltit)
        }
        val param3 = Expression()
        collect.add(param3)
        val t159 = ltit.lookahead()
        if (t159.image == ",") {
            token = ltit.nextToken()
            if (token.image != ",") {
                throw UnexpectedToken(token, arrayOf(","), ltit)
            }
            val param4 = Expression()
            collect.add(param4)
        }
        token = ltit.nextToken()
        if (token.image != ")") {
            throw UnexpectedToken(token, arrayOf(")"), ltit)
        }
        return ASTBuiltInCall(BuiltInFunctions.StrReplaceExpression, collect.toTypedArray())
    }
    private fun ExistsFunc(): ASTBuiltInCall {
        val token: Token = ltit.nextToken()
        if (token.image != "EXISTS") {
            throw UnexpectedToken(token, arrayOf("EXISTS"), ltit)
        }
        val params = GroupGraphPattern()
        return ASTBuiltInCall(BuiltInFunctions.Exists, params)
    }
    private fun NotExistsFunc(): ASTBuiltInCall {
        var token: Token = ltit.nextToken()
        if (token.image != "NOT") {
            throw UnexpectedToken(token, arrayOf("NOT"), ltit)
        }
        token = ltit.nextToken()
        if (token.image != "EXISTS") {
            throw UnexpectedToken(token, arrayOf("EXISTS"), ltit)
        }
        val params = GroupGraphPattern()
        return ASTBuiltInCall(BuiltInFunctions.NotExists, params)
    }
    private fun Aggregate(): ASTAggregation {
        var token: Token
        var distinct = false
        val t169 = ltit.lookahead()
        when (t169.image) {
            "COUNT" -> {
                token = ltit.nextToken()
                if (token.image != "COUNT") {
                    throw UnexpectedToken(token, arrayOf("COUNT"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t160 = ltit.lookahead()
                if (t160.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val children: Array<ASTNode>
                val t161 = ltit.lookahead()
                when {
                    t161.image == "*" -> {
                        token = ltit.nextToken()
                        if (token.image != "*") {
                            throw UnexpectedToken(token, arrayOf("*"), ltit)
                        }
                        children = arrayOf()
                    }
                    t161.image == "!" || t161.image == "+" || t161.image == "-" || t161.image == "(" || t161.image == "STR" || t161.image == "LANG" || t161.image == "LANGMATCHES" || t161.image == "DATATYPE" || t161.image == "BOUND" || t161.image == "IRI" || t161.image == "URI" || t161.image == "BNODE" || t161.image == "RAND" || t161.image == "ABS" || t161.image == "CEIL" || t161.image == "FLOOR" || t161.image == "ROUND" || t161.image == "CONCAT" || t161.image == "SUBSTR" || t161.image == "STRLEN" || t161.image == "REPLACE" || t161.image == "UCASE" || t161.image == "LCASE" || t161.image == "ENCODE_FOR_URI" || t161.image == "CONTAINS" || t161.image == "STRSTARTS" || t161.image == "STRENDS" || t161.image == "STRBEFORE" || t161.image == "STRAFTER" || t161.image == "YEAR" || t161.image == "MONTH" || t161.image == "DAY" || t161.image == "HOURS" || t161.image == "MINUTES" || t161.image == "SECONDS" || t161.image == "TIMEZONE" || t161.image == "TZ" || t161.image == "NOW" || t161.image == "UUID" || t161.image == "STRUUID" || t161.image == "MD5" || t161.image == "SHA1" || t161.image == "SHA256" || t161.image == "SHA384" || t161.image == "SHA512" || t161.image == "COALESCE" || t161.image == "IF" || t161.image == "STRLANG" || t161.image == "STRDT" || t161.image == "SAMETERM" || t161.image == "ISIRI" || t161.image == "ISURI" || t161.image == "ISBLANK" || t161.image == "ISLITERAL" || t161.image == "ISNUMERIC" || t161.image == "REGEX" || t161.image == "EXISTS" || t161.image == "NOT" || t161 is IRI || t161 is PNAME_LN || t161 is PNAME_NS || t161 is STRING || t161.image == "TRUE" || t161.image == "FALSE" || t161 is VAR || t161.image == "COUNT" || t161.image == "SUM" || t161.image == "MIN" || t161.image == "MAX" || t161.image == "AVG" || t161.image == "SAMPLE" || t161.image == "GROUP_CONCAT" || t161 is INTEGER || t161 is DECIMAL || t161 is DOUBLE -> {
                        val expr = Expression()
                        children = arrayOf(expr)
                    }
                    else -> {
                        throw UnexpectedToken(t161, arrayOf("*", "!", "+", "-", "(", "STR", "LANG", "LANGMATCHES", "DATATYPE", "BOUND", "IRI", "URI", "BNODE", "RAND", "ABS", "CEIL", "FLOOR", "ROUND", "CONCAT", "SUBSTR", "STRLEN", "REPLACE", "UCASE", "LCASE", "ENCODE_FOR_URI", "CONTAINS", "STRSTARTS", "STRENDS", "STRBEFORE", "STRAFTER", "YEAR", "MONTH", "DAY", "HOURS", "MINUTES", "SECONDS", "TIMEZONE", "TZ", "NOW", "UUID", "STRUUID", "MD5", "SHA1", "SHA256", "SHA384", "SHA512", "COALESCE", "IF", "STRLANG", "STRDT", "SAMETERM", "ISIRI", "ISURI", "ISBLANK", "ISLITERAL", "ISNUMERIC", "REGEX", "EXISTS", "NOT", "IRI", "PNAME_LN", "PNAME_NS", "STRING", "TRUE", "FALSE", "VAR", "COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT", "INTEGER", "DECIMAL", "DOUBLE"), ltit)
                    }
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.COUNT, distinct, children)
            }
            "SUM" -> {
                token = ltit.nextToken()
                if (token.image != "SUM") {
                    throw UnexpectedToken(token, arrayOf("SUM"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t162 = ltit.lookahead()
                if (t162.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.SUM, distinct, expr)
            }
            "MIN" -> {
                token = ltit.nextToken()
                if (token.image != "MIN") {
                    throw UnexpectedToken(token, arrayOf("MIN"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t163 = ltit.lookahead()
                if (t163.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.MIN, distinct, expr)
            }
            "MAX" -> {
                token = ltit.nextToken()
                if (token.image != "MAX") {
                    throw UnexpectedToken(token, arrayOf("MAX"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t164 = ltit.lookahead()
                if (t164.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.MAX, distinct, expr)
            }
            "AVG" -> {
                token = ltit.nextToken()
                if (token.image != "AVG") {
                    throw UnexpectedToken(token, arrayOf("AVG"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t165 = ltit.lookahead()
                if (t165.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.AVG, distinct, expr)
            }
            "SAMPLE" -> {
                token = ltit.nextToken()
                if (token.image != "SAMPLE") {
                    throw UnexpectedToken(token, arrayOf("SAMPLE"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t166 = ltit.lookahead()
                if (t166.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTAggregation(Aggregation.SAMPLE, distinct, expr)
            }
            "GROUP_CONCAT" -> {
                token = ltit.nextToken()
                if (token.image != "GROUP_CONCAT") {
                    throw UnexpectedToken(token, arrayOf("GROUP_CONCAT"), ltit)
                }
                token = ltit.nextToken()
                if (token.image != "(") {
                    throw UnexpectedToken(token, arrayOf("("), ltit)
                }
                val t167 = ltit.lookahead()
                if (t167.image == "DISTINCT") {
                    token = ltit.nextToken()
                    if (token.image != "DISTINCT") {
                        throw UnexpectedToken(token, arrayOf("DISTINCT"), ltit)
                    }
                    distinct = true
                }
                val expr = Expression()
                var separator = " "
                val t168 = ltit.lookahead()
                if (t168.image == ";") {
                    token = ltit.nextToken()
                    if (token.image != ";") {
                        throw UnexpectedToken(token, arrayOf(";"), ltit)
                    }
                    token = ltit.nextToken()
                    if (token.image != "SEPARATOR") {
                        throw UnexpectedToken(token, arrayOf("SEPARATOR"), ltit)
                    }
                    token = ltit.nextToken()
                    if (token.image != "=") {
                        throw UnexpectedToken(token, arrayOf("="), ltit)
                    }
                    token = ltit.nextToken()
                    if (token !is STRING) {
                        throw UnexpectedToken(token, arrayOf("STRING"), ltit)
                    }
                    separator = token.content
                }
                token = ltit.nextToken()
                if (token.image != ")") {
                    throw UnexpectedToken(token, arrayOf(")"), ltit)
                }
                return ASTGroupConcat(distinct, expr, separator)
            }
            else -> {
                throw UnexpectedToken(t169, arrayOf("COUNT", "SUM", "MIN", "MAX", "AVG", "SAMPLE", "GROUP_CONCAT"), ltit)
            }
        }
    }
    private fun IRIrefOrFunction(): ASTNode {
        var token: Token
        val iri = IRIref()
        val t170 = ltit.lookahead()
        if (t170 is NIL || t170.image == "(") {
            return ArgList(iri.iri)
        }
        return iri
    }
    private fun RDFLiteral(): ASTLiteral {
        var token: Token
        token = ltit.nextToken()
        if (token !is STRING) {
            throw UnexpectedToken(token, arrayOf("STRING"), ltit)
        }
        val s = token
        val t172 = ltit.lookahead()
        if (t172 is LANGTAG || t172.image == "^^") {
            val t171 = ltit.lookahead()
            when {
                t171 is LANGTAG -> {
                    token = ltit.nextToken()
                    if (token !is LANGTAG) {
                        throw UnexpectedToken(token, arrayOf("LANGTAG"), ltit)
                    }
                    return ASTLanguageTaggedLiteral(s.content, s.leftBrace, token.language)
                }
                t171.image == "^^" -> {
                    token = ltit.nextToken()
                    if (token.image != "^^") {
                        throw UnexpectedToken(token, arrayOf("^^"), ltit)
                    }
                    val iri = IRIref()
                    return ASTTypedLiteral(s.content, s.leftBrace, iri.iri)
                }
                else -> {
                    throw UnexpectedToken(t171, arrayOf("LANGTAG", "^^"), ltit)
                }
            }
        }
        return ASTSimpleLiteral(s.content, s.leftBrace)
    }
    private fun NumericLiteral(): ASTNumericLiteral {
        var token: Token
        val result: ASTNumericLiteral
        val t173 = ltit.lookahead()
        result = when {
            t173 is INTEGER || t173 is DECIMAL || t173 is DOUBLE -> {
                NumericLiteralUnsigned()
            }
            t173.image == "+" -> {
                NumericLiteralPositive()
            }
            t173.image == "-" -> {
                NumericLiteralNegative()
            }
            else -> {
                throw UnexpectedToken(t173, arrayOf("INTEGER", "DECIMAL", "DOUBLE", "+", "-"), ltit)
            }
        }
        return result
    }
    private fun NumericLiteralUnsigned(): ASTNumericLiteral {
        val token: Token
        when (val t174 = ltit.lookahead()) {
            is INTEGER -> {
                token = ltit.nextToken()
                if (token !is INTEGER) {
                    throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                }
                return ASTInteger(token.image)
            }
            is DECIMAL -> {
                token = ltit.nextToken()
                if (token !is DECIMAL) {
                    throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                }
                return ASTDecimal(token.image)
            }
            is DOUBLE -> {
                token = ltit.nextToken()
                if (token !is DOUBLE) {
                    throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                }
                return ASTDouble(token.image)
            }
            else -> {
                throw UnexpectedToken(t174, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), ltit)
            }
        }
    }
    private fun NumericLiteralPositive(): ASTNumericLiteral {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "+") {
            throw UnexpectedToken(token, arrayOf("+"), ltit)
        }
        when (val t175 = ltit.lookahead()) {
            is INTEGER -> {
                token = ltit.nextToken()
                if (token !is INTEGER) {
                    throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                }
                return ASTInteger(token.image)
            }
            is DECIMAL -> {
                token = ltit.nextToken()
                if (token !is DECIMAL) {
                    throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                }
                return ASTDecimal(token.image)
            }
            is DOUBLE -> {
                token = ltit.nextToken()
                if (token !is DOUBLE) {
                    throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                }
                return ASTDouble(token.image)
            }
            else -> {
                throw UnexpectedToken(t175, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), ltit)
            }
        }
    }
    private fun NumericLiteralNegative(): ASTNumericLiteral {
        var token: Token
        token = ltit.nextToken()
        if (token.image != "-") {
            throw UnexpectedToken(token, arrayOf("-"), ltit)
        }
        when (val t176 = ltit.lookahead()) {
            is INTEGER -> {
                token = ltit.nextToken()
                if (token !is INTEGER) {
                    throw UnexpectedToken(token, arrayOf("INTEGER"), ltit)
                }
                return ASTInteger("-" + token.image)
            }
            is DECIMAL -> {
                token = ltit.nextToken()
                if (token !is DECIMAL) {
                    throw UnexpectedToken(token, arrayOf("DECIMAL"), ltit)
                }
                return ASTDecimal("-" + token.image)
            }
            is DOUBLE -> {
                token = ltit.nextToken()
                if (token !is DOUBLE) {
                    throw UnexpectedToken(token, arrayOf("DOUBLE"), ltit)
                }
                return ASTDouble("-" + token.image)
            }
            else -> {
                throw UnexpectedToken(t176, arrayOf("INTEGER", "DECIMAL", "DOUBLE"), ltit)
            }
        }
    }
    private fun BooleanLiteral(): ASTBooleanLiteral {
        val token: Token
        val t177 = ltit.lookahead()
        when (t177.image) {
            "TRUE" -> {
                token = ltit.nextToken()
                if (token.image != "TRUE") {
                    throw UnexpectedToken(token, arrayOf("TRUE"), ltit)
                }
                return ASTBooleanLiteral(true)
            }
            "FALSE" -> {
                token = ltit.nextToken()
                if (token.image != "FALSE") {
                    throw UnexpectedToken(token, arrayOf("FALSE"), ltit)
                }
                return ASTBooleanLiteral(false)
            }
            else -> {
                throw UnexpectedToken(t177, arrayOf("TRUE", "FALSE"), ltit)
            }
        }
    }
    private fun IRIref(): ASTIri {
        var token: Token
        val result: ASTIri
        val t178 = ltit.lookahead()
        result = when (t178) {
            is IRI -> {
                QuotedIriRef()
            }
            is PNAME_LN, is PNAME_NS -> {
                PrefixedName()
            }
            else -> {
                throw UnexpectedToken(t178, arrayOf("IRI", "PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        return result
    }
    private fun QuotedIriRef(): ASTIri {
        val token: Token = ltit.nextToken()
        if (token !is IRI) {
            throw UnexpectedToken(token, arrayOf("IRI"), ltit)
        }
        return ASTIri(token.content)
    }
    private fun PrefixedName(): ASTIri {
        val token: Token
        val prefix: String
        val postfix: String
        val iriToken: Token
        when (val t179 = ltit.lookahead()) {
            is PNAME_LN -> {
                token = ltit.nextToken()
                if (token !is PNAME_LN) {
                    throw UnexpectedToken(token, arrayOf("PNAME_LN"), ltit)
                }
                iriToken = token; prefix = token.beforeColon; postfix = token.afterColon
            }
            is PNAME_NS -> {
                token = ltit.nextToken()
                if (token !is PNAME_NS) {
                    throw UnexpectedToken(token, arrayOf("PNAME_NS"), ltit)
                }
                iriToken = token; prefix = token.beforeColon; postfix = ""
            }
            else -> {
                throw UnexpectedToken(t179, arrayOf("PNAME_LN", "PNAME_NS"), ltit)
            }
        }
        val alias = prefixes[prefix]
        if (alias == null) {
            throw ParseError("Undefined Prefix $prefix", iriToken, ltit)
        } else {
            return ASTIri(alias + postfix)
        }
    }
    private fun BlankNode(): ASTBlankNode {
        val token: Token
        val result: ASTBlankNode
        when (val t180 = ltit.lookahead()) {
            is BNODE -> {
                token = ltit.nextToken()
                if (token !is BNODE) {
                    throw UnexpectedToken(token, arrayOf("BNODE"), ltit)
                }
                return ASTBlankNode(token.name)
            }
            is ANON_BNODE -> {
                token = ltit.nextToken()
                if (token !is ANON_BNODE) {
                    throw UnexpectedToken(token, arrayOf("ANON_BNODE"), ltit)
                }
                return ASTBlankNode()
            }
            else -> {
                throw UnexpectedToken(t180, arrayOf("BNODE", "ANON_BNODE"), ltit)
            }
        }
    }
}
