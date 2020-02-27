package lupos.s04arithmetikOperators.noinput
import lupos.s00misc.*
import com.benasher44.uuid.uuid4
import com.soywiz.krypto.md5
import com.soywiz.krypto.sha1
import com.soywiz.krypto.sha256
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt
import lupos.s00misc.classNameToString
import lupos.s00misc.EOperatorID
import lupos.s00misc.ThreadSafeUuid
import lupos.s00misc.XMLElement
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONCAT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallCONTAINS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallIF
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallLANGMATCHES
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRDT
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRENDS
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRLANG
import lupos.s04arithmetikOperators.multiinput.AOPBuildInCallSTRSTARTS
import lupos.s04arithmetikOperators.noinput.AOPBnode
import lupos.s04arithmetikOperators.noinput.AOPConstant

import lupos.s04arithmetikOperators.singleinput.AOPAggregation
import lupos.s04arithmetikOperators.singleinput.AOPFunctionCall
import lupos.s04arithmetikOperators.singleinput.AOPNot
import lupos.s04logicalOperators.LOPBase
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
}
