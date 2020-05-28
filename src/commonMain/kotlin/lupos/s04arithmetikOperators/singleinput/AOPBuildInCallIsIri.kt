package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallIsIri(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsIriID, "AOPBuildInCallIsIri", arrayOf(child)) {
    override fun toSparql() = "isIRI(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3064)
        if (other !is AOPBuildInCallIsIri) {
Coverage.ifStart(3065)
            return false
        }
Coverage.statementStart(3066)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3067)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3068)
        return {
Coverage.statementStart(3069)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3070)
            val a = childA()
Coverage.statementStart(3071)
            if (a !is ValueUndef && a !is ValueError) {
Coverage.ifStart(3072)
                res = ValueBoolean(a is ValueIri)
Coverage.statementStart(3073)
            }
Coverage.statementStart(3074)
/*return*/res
        }
Coverage.statementStart(3075)
    }
    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallIsIri(query, children[0].cloneOP() as AOPBase)
}
