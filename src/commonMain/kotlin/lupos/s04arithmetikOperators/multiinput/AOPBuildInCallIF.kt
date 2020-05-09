package lupos.s04arithmetikOperators.multiinput

import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallIF(query: Query, child: AOPBase, childA: AOPBase, childB: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallIFID, "AOPBuildInCallIF", arrayOf(child, childA, childB)) {
    override fun toSparql() = "IF(" + children[0].toSparql() + ", " + children[1].toSparql() + ", " + children[1].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallIF) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        val childB = (children[1] as AOPBase).evaluate(row)
        val childC = (children[2] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            try {
                if (childA().toBoolean()) {
                    res = childB()
                } else {
                    res = childC()
                }
            } catch (e: Throwable) {
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPBuildInCallIF(query, children[0].cloneOP() as AOPBase, children[1].cloneOP() as AOPBase, children[2].cloneOP() as AOPBase)
}
