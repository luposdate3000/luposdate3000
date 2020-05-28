package lupos.s04arithmetikOperators.noinput
import com.benasher44.uuid.uuid4
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
class AOPBuildInCallSTRUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {
    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(2666)
        if (other !is AOPBuildInCallSTRUUID) {
Coverage.ifStart(2667)
            return false
        }
Coverage.statementStart(2668)
        return true
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(2669)
        return {
Coverage.statementStart(2670)
            /*return*/ValueSimpleLiteral("\"", "" + uuid4())
        }
Coverage.statementStart(2671)
    }
    override fun cloneOP() = this
}
