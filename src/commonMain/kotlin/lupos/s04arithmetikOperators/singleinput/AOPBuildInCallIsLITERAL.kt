package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallIsLITERAL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsLITERALID, "AOPBuildInCallIsLITERAL", arrayOf(child)) {
    override fun toSparql() = "isLiteral(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3076)
        if (other !is AOPBuildInCallIsLITERAL) {
Coverage.ifStart(3077)
            return false
        }
Coverage.statementStart(3078)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3079)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3080)
        return {
Coverage.statementStart(3081)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3082)
            val a = childA()
Coverage.statementStart(3083)
            if (a !is ValueUndef && a !is ValueError) {
Coverage.ifStart(3084)
                res = ValueBoolean(a is ValueStringBase || a is ValueDouble || a is ValueBoolean || a is ValueInteger || a is ValueDecimal || a is ValueDateTime)
Coverage.statementStart(3085)
            }
Coverage.statementStart(3086)
/*return*/res
        }
Coverage.statementStart(3087)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallIsLITERAL(query, children[0].cloneOP() as AOPBase)
}
