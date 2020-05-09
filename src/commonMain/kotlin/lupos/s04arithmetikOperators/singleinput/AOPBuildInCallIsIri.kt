package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBoolean
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueIri
import lupos.s03resultRepresentation.ValueUndef
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query



class AOPBuildInCallIsIri(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIsIriID, "AOPBuildInCallIsIri", arrayOf(child)) {
    override fun toSparql() = "isIRI(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIsIri) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a !is ValueUndef && a !is ValueError) {
                res = ValueBoolean(a is ValueIri)
            }
/*return*/res
        }
    }

    override fun enforcesBooleanOrError() = true
    override fun cloneOP() = AOPBuildInCallIsIri(query, children[0].cloneOP() as AOPBase)
}
