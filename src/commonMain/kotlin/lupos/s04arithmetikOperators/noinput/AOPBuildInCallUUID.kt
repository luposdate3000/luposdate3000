package lupos.s04arithmetikOperators.noinput

import com.benasher44.uuid.uuid4
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallUUID : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallUUIDID
    override val classname = "AOPBuildInCallUUID"
    override val children: Array<OPBase> = arrayOf()


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallUUID)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        return resultFlow({ this }, { resultRow }, { resultSet }, {
            AOPIri("urn:uuid:" + uuid4())
        })
    }
override fun cloneOP()=this
}
