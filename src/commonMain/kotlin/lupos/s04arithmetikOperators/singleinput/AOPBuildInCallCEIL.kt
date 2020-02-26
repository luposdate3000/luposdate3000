package lupos.s04arithmetikOperators.singleinput

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
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s03resultRepresentation.ResultSetDictionary
import lupos.s03resultRepresentation.Variable
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.LOPBase
import lupos.s04logicalOperators.OPBase


class AOPBuildInCallCEIL(child: AOPBase) : AOPBase() {
    override val operatorID = EOperatorID.AOPBuildInCallCEILID
    override val classname = "AOPBuildInCallCEIL"
    override val children: Array<OPBase> = arrayOf(child)


    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallCEIL)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPDouble)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDouble(ceil(a.toDouble()))
            })
        if (a is AOPDecimal)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPDecimal(ceil(a.toDouble()))
            })
        if (a is AOPInteger)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                a
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall CEIL only works with numeric input")
        })
    }
}
