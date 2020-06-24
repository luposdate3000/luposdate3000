package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallSTRUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?) = other is AOPBuildInCallSTRUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ValueSimpleLiteral("\"", "" + uuid4())
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = this
}
