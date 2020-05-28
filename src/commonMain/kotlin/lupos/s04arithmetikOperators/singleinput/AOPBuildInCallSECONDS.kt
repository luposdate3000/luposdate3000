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
        if (other !is AOPBuildInCallSECONDS) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueDateTime) {
                res = ValueDecimal(0.0 + a.seconds)
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPBuildInCallSECONDS(query, children[0].cloneOP() as AOPBase)
}
