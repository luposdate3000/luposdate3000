package lupos.s08logicalOptimisation
import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOptimizerID
import lupos.s00misc.ExecuteOptimizer
import lupos.s03resultRepresentation.MyListValue
import lupos.s03resultRepresentation.nodeGlobalDictionary
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueComparatorASC
import lupos.s03resultRepresentation.ValueComparatorDESC
import lupos.s03resultRepresentation.ValueComparatorFast
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueLanguageTaggedLiteral
import lupos.s03resultRepresentation.ValueNumeric
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueTypedLiteral
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallBNODE0
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallSTRUUID
import lupos.s04arithmetikOperators.noinput.AOPBuildInCallUUID
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPValue
import lupos.s04arithmetikOperators.noinput.AOPVariable
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s08logicalOptimisation.OptimizerBase


class LogicalOptimizerArithmetic(query: Query) : OptimizerBase(query, EOptimizerID.LogicalOptimizerArithmeticID) {
    override val classname = "LogicalOptimizerArithmetic"
    fun hasAggregation(node: OPBase): Boolean {
        for (n in node.children) {
            if (hasAggregation(n)) {
                return true
            }
        }
        return node is AOPAggregationBase
    }

    override fun optimize(node: OPBase, parent: OPBase?, onChange: () -> Unit) = ExecuteOptimizer.invoke({ this }, { node }, {
        var res = node
        if (node is AOPBase && node !is AOPValue) {
            if (node.children.size > 0 && node.getRequiredVariableNamesRecoursive().size == 0 && !hasAggregation(node)) {
                val outMap = mutableMapOf<String, ColumnIterator>()
                println("evaluating constant ::")
                var value = node.evaluate(ColumnIteratorRow(outMap))()
                println("evaluating constant :: A $value")
                if (value is ValueError) {
                    value = ValueUndef()
                }
                println("evaluating constant :: B $value")
                res = AOPConstant(query, value)
                println("evaluating constant :: C $res")
                onChange()
            }
        }
/*return*/res
    })
}
