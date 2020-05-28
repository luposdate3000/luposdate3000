package lupos.s04arithmetikOperators.singleinput
import kotlin.math.floor
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
class AOPBuildInCallFLOOR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallFLOORID, "AOPBuildInCallFLOOR", arrayOf(child)) {
    override fun toSparql() = "FLOOR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3009)
        if (other !is AOPBuildInCallFLOOR) {
Coverage.ifStart(3010)
            return false
        }
Coverage.statementStart(3011)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3012)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3013)
        return {
Coverage.statementStart(3014)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3015)
            val a = childA()
Coverage.statementStart(3016)
            try {
Coverage.statementStart(3017)
                if (a is ValueDouble) {
Coverage.ifStart(3018)
                    res = ValueDouble(floor(a.toDouble()))
Coverage.statementStart(3019)
                } else if (a is ValueDecimal) {
Coverage.ifStart(3020)
                    res = ValueDecimal(floor(a.toDouble()))
Coverage.statementStart(3021)
                } else if (a is ValueInteger) {
Coverage.ifStart(3022)
                    res = a
Coverage.statementStart(3023)
                }
Coverage.statementStart(3024)
            } catch (e: Throwable) {
Coverage.statementStart(3025)
            }
Coverage.statementStart(3026)
/*return*/res
        }
Coverage.statementStart(3027)
    }
    override fun cloneOP() = AOPBuildInCallFLOOR(query, children[0].cloneOP() as AOPBase)
}
