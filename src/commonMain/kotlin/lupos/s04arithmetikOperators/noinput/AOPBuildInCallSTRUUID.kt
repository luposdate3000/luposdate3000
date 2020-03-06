package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallSTRUUID : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallSTRUUIDID
    override val classname = "AOPBuildInCallSTRUUID"
    override val children: Array<OPBase> = arrayOf()

    override fun toSparql() = "STRUUID()"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRUUID)
            return false
        return true
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPSimpleLiteral("\"", "" + uuid4())
        })
    }

    override fun cloneOP() = this
}
