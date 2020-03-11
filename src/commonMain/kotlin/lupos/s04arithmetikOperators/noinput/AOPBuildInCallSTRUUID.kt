package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPBuildInCallSTRUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallSTRUUIDID, "AOPBuildInCallSTRUUID", arrayOf()) {

    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRUUID)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            ValueSimpleLiteral("\"", "" + uuid4())
        })
    }

    override fun cloneOP() = this
}
