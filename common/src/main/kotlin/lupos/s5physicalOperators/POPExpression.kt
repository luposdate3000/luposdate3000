package lupos.s5physicalOperators

import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

enum class TmpResultType {
    RSBoolean, RSString, RSInteger
}

class POPExpression : OPBase {
    private val dataTypeInteger = "^^<http://www.w3.org/2001/XMLSchema#integer>"
    var child: ASTNode

    constructor(child: ASTNode) {
        this.child = child
    }

    private fun getResultType(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): TmpResultType {
        when (node) {
            is ASTOr -> return TmpResultType.RSBoolean
            is ASTAnd -> return TmpResultType.RSBoolean
            is ASTEQ -> return TmpResultType.RSBoolean
            is ASTGEQ -> return TmpResultType.RSBoolean
            is ASTLEQ -> return TmpResultType.RSBoolean
            is ASTGT -> return TmpResultType.RSBoolean
            is ASTLT -> return TmpResultType.RSBoolean
            is ASTInteger -> return TmpResultType.RSInteger
            is ASTAddition -> return TmpResultType.RSInteger
            is ASTMultiplication -> return TmpResultType.RSInteger
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                println("XXX ${tmp} -> ${tmp.endsWith(dataTypeInteger)} ${tmp.startsWith("<http")}")
                when {
                    tmp.endsWith(dataTypeInteger) -> return TmpResultType.RSInteger
                    tmp.startsWith("<http") -> return TmpResultType.RSString
                    else -> throw UnsupportedOperationException("${this::class.simpleName} getResultType ${node::class.simpleName} ${tmp}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} getResultType ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperInteger2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Int {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTAddition -> return a + b
                    is ASTMultiplication -> return a * b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger2 ${node::class.simpleName}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun evaluateHelperInteger(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Int {
        when (node) {
            is ASTInteger -> return node.value
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeInteger.length)
                return tmp2.toInt()
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperInteger2(resultSet, resultRow, node)
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperBoolean2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Boolean {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSBoolean && typeB == TmpResultType.RSBoolean -> {
                val a = evaluateHelperBoolean(resultSet, resultRow, left)
                val b = evaluateHelperBoolean(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> return a == b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }
            }
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> return a == b
                    is ASTGEQ -> return a >= b
                    is ASTLEQ -> return a <= b
                    is ASTGT -> return a > b
                    is ASTLT -> return a < b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }

            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun evaluateHelperBoolean(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Boolean {
        when (node) {
            is ASTOr -> {
                var res = false
                for (n in node.children) {
                    res = res || evaluateHelperBoolean(resultSet, resultRow, n)
                }
                return res
            }
            is ASTAnd -> {
                var res = true
                for (n in node.children) {
                    res = res && evaluateHelperBoolean(resultSet, resultRow, n)
                }
                return res
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperBoolean2(resultSet, resultRow, node)
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperString(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): String {
        when (getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSBoolean -> return "" + evaluateHelperBoolean(resultSet, resultRow, node)
            TmpResultType.RSInteger -> return "\"" + evaluateHelperInteger(resultSet, resultRow, node) + "\"" + dataTypeInteger
        }
        when (node) {
            is ASTLiteral -> return node.delimiter + node.content + node.delimiter
            is ASTVar -> return resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperString ${node::class.simpleName}")
        }
    }

    fun evaluate(resultSet: ResultSet, resultRow: ResultRow): String {
        return evaluateHelperString(resultSet, resultRow, child)
    }

    override fun toString(indentation: String): String {
        return "${indentation}${this::class.simpleName}\n${child.toString("${indentation}\t")}"
    }

}
