package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueBnode
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallBNODE0(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallBNODE0ID, "AOPBuildInCallBNODE0", arrayOf()) {
    override fun toSparql() = "BNODE()"

    companion object {
        val localbnode = ThreadSafeUuid()
    }

    override fun equals(other: Any?) = other is AOPBuildInCallBNODE0
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ValueBnode("" + uuid + localbnode.next())
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = this
}
