package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueIri
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallUUIDID, "AOPBuildInCallUUID", arrayOf()) {
    override fun toSparql() = "UUID()"
    override fun equals(other: Any?) = other is AOPBuildInCallUUID
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        return {
            /*return*/ValueIri("urn:uuid:" + uuid4())
        }
/*Coverage Unreachable*/
    }

    override fun cloneOP() = this
}
