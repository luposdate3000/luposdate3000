package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator


class AOPBuildInCallUUID(query: Query) : AOPBase(query, EOperatorID.AOPBuildInCallUUIDID, "AOPBuildInCallUUID", arrayOf()) {

    override fun toSparql() = "UUID()"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUUID)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): ValueDefinition {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            ValueIri("urn:uuid:" + uuid4())
        })
    }

    override fun cloneOP() = this
}
