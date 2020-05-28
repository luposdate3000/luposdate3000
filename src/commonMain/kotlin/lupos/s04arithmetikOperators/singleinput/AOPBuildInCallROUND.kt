package lupos.s04arithmetikOperators.singleinput
import kotlin.math.roundToInt
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
class AOPBuildInCallROUND(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallROUNDID, "AOPBuildInCallROUND", arrayOf(child)) {
    override fun toSparql() = "ROUND(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3176)
        if (other !is AOPBuildInCallROUND) {
Coverage.ifStart(3177)
            return false
        }
Coverage.statementStart(3178)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3179)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3180)
        return {
Coverage.statementStart(3181)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3182)
            val a = childA()
Coverage.statementStart(3183)
            try {
Coverage.statementStart(3184)
                if (a is ValueDouble) {
Coverage.ifStart(3185)
                    res = ValueDouble(a.toDouble().roundToInt().toDouble())
Coverage.statementStart(3186)
                } else if (a is ValueDecimal) {
Coverage.ifStart(3187)
                    res = ValueDecimal(a.toDouble().roundToInt().toDouble())
Coverage.statementStart(3188)
                } else if (a is ValueInteger) {
Coverage.ifStart(3189)
                    res = a
Coverage.statementStart(3190)
                }
Coverage.statementStart(3191)
            } catch (e: Throwable) {
Coverage.statementStart(3192)
            }
Coverage.statementStart(3193)
/*return*/res
        }
Coverage.statementStart(3194)
    }
    override fun cloneOP() = AOPBuildInCallROUND(query, children[0].cloneOP() as AOPBase)
}
