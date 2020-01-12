package lupos.s5physicalOperators

import kotlin.math.*
import lupos.s1buildSyntaxTree.sparql1_1.*
import lupos.s2buildOperatorGraph.OPBase
import lupos.s2buildOperatorGraph.data.LOPVariable
import lupos.s4resultRepresentation.ResultRow
import lupos.s4resultRepresentation.ResultSet
import lupos.s4resultRepresentation.Variable
import lupos.s5physicalOperators.POPBase

enum class TmpResultType {
    RSBoolean, RSString, RSInteger, RSDouble
}

class POPExpression : OPBase {
    private val dataTypeInteger = "^^<http://www.w3.org/2001/XMLSchema#integer>"
    private val dataTypeDouble = "^^<http://www.w3.org/2001/XMLSchema#decimal>"
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
            is ASTIri -> return TmpResultType.RSString
            is ASTInteger -> return TmpResultType.RSInteger
            is ASTAddition -> return TmpResultType.RSInteger
            is ASTMultiplication -> return TmpResultType.RSInteger
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                println("XXX ${tmp} -> ${tmp.endsWith(dataTypeInteger)} ${tmp.endsWith(dataTypeDouble)} ${tmp.startsWith("<http")}")
                when {
                    tmp.endsWith(dataTypeInteger) -> return TmpResultType.RSInteger
                    tmp.endsWith(dataTypeDouble) -> return TmpResultType.RSDouble
                    tmp.startsWith("<http") -> return TmpResultType.RSString
                    else -> {
                        println("guess result type to be TmpResultType.RSString (${tmp})")
                        return TmpResultType.RSString
                    }
                }
            }
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.BOUND -> return TmpResultType.RSBoolean
                    BuiltInFunctions.RAND -> return TmpResultType.RSInteger
                    BuiltInFunctions.ABS -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.CEIL -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.FLOOR -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.ROUND -> return getResultType(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.IF -> return TmpResultType.RSBoolean
                    BuiltInFunctions.sameTerm -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isIRI -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isURI -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isBLANK -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isLITERAL -> return TmpResultType.RSBoolean
                    BuiltInFunctions.isNUMERIC -> return TmpResultType.RSBoolean
                    BuiltInFunctions.Exists -> return TmpResultType.RSBoolean
                    BuiltInFunctions.NotExists -> return TmpResultType.RSBoolean
                    else -> return TmpResultType.RSString
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} getResultType ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperDouble2(resultSet: ResultSet, resultRow: ResultRow, node: ASTBinaryOperationFixedName): Double {
        val left = node.children[0]
        val right = node.children[1]
        val typeA = getResultType(resultSet, resultRow, left)
        val typeB = getResultType(resultSet, resultRow, right)
        when {
            typeA == TmpResultType.RSDouble && typeB == TmpResultType.RSDouble -> {
                val a = evaluateHelperDouble(resultSet, resultRow, left)
                val b = evaluateHelperDouble(resultSet, resultRow, right)
                when (node) {
                    is ASTAddition -> return a + b
                    is ASTMultiplication -> return a * b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble2 ${node::class.simpleName}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble2 ${node::class.simpleName} ${typeA} ${typeB}")
        }
    }

    private fun evaluateHelperDouble(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): Double {
        when (node) {
            is ASTVar -> {
                val tmp = resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
                val tmp2 = tmp.substring(1, tmp.length - 1 - dataTypeDouble.length)
                println("XXD1 " + tmp)
                println("XXD2 " + tmp2)
                return tmp2.toDouble()
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperDouble2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                when (node.function) {
                    BuiltInFunctions.ABS -> return abs(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.CEIL -> return ceil(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.FLOOR -> return floor(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    BuiltInFunctions.ROUND -> return round(evaluateHelperDouble(resultSet, resultRow, node.children[0]))
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble ${node::class.simpleName} ${node.function}")
                }
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperDouble ${node::class.simpleName}")
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
                println("XXI" + tmp2)
                return tmp2.toInt()
            }
            is ASTBinaryOperationFixedName -> return evaluateHelperInteger2(resultSet, resultRow, node)
            is ASTBuiltInCall -> {
                val typeA = getResultType(resultSet, resultRow, node.children[0])
                when (node.function) {
                    BuiltInFunctions.ABS -> return abs(evaluateHelperInteger(resultSet, resultRow, node.children[0])).toInt()
                    BuiltInFunctions.CEIL -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.FLOOR -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    BuiltInFunctions.ROUND -> return evaluateHelperInteger(resultSet, resultRow, node.children[0])
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperInteger ${node::class.simpleName} ${node.function}")
                }
            }
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
            typeA == TmpResultType.RSDouble && typeB == TmpResultType.RSInteger -> {
                val a = evaluateHelperDouble(resultSet, resultRow, left)
                val b = evaluateHelperInteger(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> return a == b.toDouble()
                    is ASTGEQ -> return a >= b
                    is ASTLEQ -> return a <= b
                    is ASTGT -> return a > b
                    is ASTLT -> return a < b
                    else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean2 ${node::class.simpleName}")
                }

            }
            typeA == TmpResultType.RSInteger && typeB == TmpResultType.RSDouble -> {
                val a = evaluateHelperInteger(resultSet, resultRow, left)
                val b = evaluateHelperDouble(resultSet, resultRow, right)
                when (node) {
                    is ASTEQ -> return a.toDouble() == b
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
            is ASTBuiltInCall -> {
                throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean ${node::class.simpleName} ${node.function}")
            }
            else -> throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperBoolean ${node::class.simpleName}")
        }
    }

    private fun evaluateHelperString(resultSet: ResultSet, resultRow: ResultRow, node: ASTNode): String {
        when (getResultType(resultSet, resultRow, node)) {
            TmpResultType.RSBoolean -> return "" + evaluateHelperBoolean(resultSet, resultRow, node)
            TmpResultType.RSInteger -> return "\"" + evaluateHelperInteger(resultSet, resultRow, node) + "\"" + dataTypeInteger
            TmpResultType.RSDouble -> {
                val res = evaluateHelperDouble(resultSet, resultRow, node).toString()
                when {
                    res.endsWith(".0") -> return "\"" + res.substring(0, res.length - 2) + "\"" + dataTypeDouble
                    res.contains('.') -> return "\"" + res + "\"" + dataTypeDouble
                    else -> return "\"" + res + ".0\"" + dataTypeDouble
                }
            }
        }
        when (node) {
            is ASTLiteral -> return node.delimiter + node.content + node.delimiter
            is ASTVar -> return resultSet.getValue(resultRow[resultSet.createVariable(node.name)])
            is ASTBuiltInCall -> {
                throw UnsupportedOperationException("${this::class.simpleName} evaluateHelperString ${node::class.simpleName} ${node.function}")
            }
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
