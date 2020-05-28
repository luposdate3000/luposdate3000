package lupos.s04arithmetikOperators.singleinput
import kotlin.math.abs
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueDouble
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueInteger
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallABS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallABSID, "AOPBuildInCallABS", arrayOf(child)) {
    override fun toSparql() = "ABS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2919)
        if (other !is AOPBuildInCallABS) {
Coverage.ifStart(2920)
            return false
        }
Coverage.statementStart(2921)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2922)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2923)
        return {
Coverage.statementStart(2924)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2925)
            val a = childA()
Coverage.statementStart(2926)
            if (a is ValueDouble) {
Coverage.ifStart(2927)
                res = ValueDouble(abs(a.value))
Coverage.statementStart(2928)
            } else if (a is ValueDecimal) {
Coverage.ifStart(2929)
                res = ValueDecimal(abs(a.value))
Coverage.statementStart(2930)
            } else if (a is ValueInteger) {
Coverage.ifStart(2931)
                res = ValueInteger(abs(a.value))
Coverage.statementStart(2932)
            }
Coverage.statementStart(2933)
/*return*/res
        }
Coverage.statementStart(2934)
    }
    override fun cloneOP() = AOPBuildInCallABS(query, children[0].cloneOP() as AOPBase)
}
