package lupos.s04arithmetikOperators.noinput
import lupos.s04logicalOperators.Query

import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallBNODE0(query:Query) : AOPBase(query,EOperatorID.AOPBuildInCallBNODE0ID,"AOPBuildInCallBNODE0",arrayOf()) {

    override fun toSparql() = "BNODE()"

    companion object {
        val localbnode = ThreadSafeUuid()
    }


    override fun equals(other: Any?): Boolean {
        return other is AOPBuildInCallBNODE0
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBnode(query,"" + uuid + localbnode.next())
        })
    }

    override fun cloneOP() = this
}
