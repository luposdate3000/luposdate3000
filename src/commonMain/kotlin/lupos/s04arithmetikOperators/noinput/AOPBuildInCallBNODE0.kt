package lupos.s04arithmetikOperators.noinput

import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s00misc.ThreadSafeUuid
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallBNODE0 : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallBNODE0ID
    override val classname = "AOPBuildInCallBNODE0"
    override val children: Array<OPBase> = arrayOf()

    companion object {
        val localbnode = ThreadSafeUuid()
    }


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallBNODE0)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPBnode("" + uuid + localbnode.next())
        })
    }
override fun cloneOP()=this
}
