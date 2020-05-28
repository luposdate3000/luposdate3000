package lupos.s04arithmetikOperators.singleinput
import kotlin.math.ceil
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
class AOPBuildInCallCEIL(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallCEILID, "AOPBuildInCallCEIL", arrayOf(child)) {
    override fun toSparql() = "CEIL(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2951)
        if (other !is AOPBuildInCallCEIL) {
Coverage.ifStart(2952)
            return false
        }
Coverage.statementStart(2953)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2954)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(2955)
        return {
Coverage.statementStart(2956)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(2957)
            val a = childA()
Coverage.statementStart(2958)
            try {
Coverage.statementStart(2959)
                if (a is ValueDouble) {
Coverage.ifStart(2960)
                    res = ValueDouble(ceil(a.toDouble()))
Coverage.statementStart(2961)
                } else if (a is ValueDecimal) {
Coverage.ifStart(2962)
                    res = ValueDecimal(ceil(a.toDouble()))
Coverage.statementStart(2963)
                } else if (a is ValueInteger) {
Coverage.ifStart(2964)
                    res = a
Coverage.statementStart(2965)
                }
Coverage.statementStart(2966)
            } catch (e: Throwable) {
Coverage.statementStart(2967)
            }
Coverage.statementStart(2968)
/*return*/res
        }
Coverage.statementStart(2969)
    }
    override fun cloneOP() = AOPBuildInCallCEIL(query, children[0].cloneOP() as AOPBase)
}
