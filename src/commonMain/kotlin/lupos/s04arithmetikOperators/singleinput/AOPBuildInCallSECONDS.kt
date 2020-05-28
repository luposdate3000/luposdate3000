package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDateTime
import lupos.s03resultRepresentation.ValueDecimal
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSECONDS(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSECONDSID, "AOPBuildInCallSECONDS", arrayOf(child)) {
    override fun toSparql() = "SECONDS(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3195)
        if (other !is AOPBuildInCallSECONDS) {
Coverage.ifStart(3196)
            return false
        }
Coverage.statementStart(3197)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3198)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3199)
        return {
Coverage.statementStart(3200)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3201)
            val a = childA()
Coverage.statementStart(3202)
            if (a is ValueDateTime) {
Coverage.ifStart(3203)
                res = ValueDecimal(0.0 + a.seconds)
Coverage.statementStart(3204)
            }
Coverage.statementStart(3205)
/*return*/res
        }
Coverage.statementStart(3206)
    }
    override fun cloneOP() = AOPBuildInCallSECONDS(query, children[0].cloneOP() as AOPBase)
}
