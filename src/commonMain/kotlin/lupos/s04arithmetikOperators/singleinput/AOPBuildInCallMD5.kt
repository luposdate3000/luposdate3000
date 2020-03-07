package lupos.s04arithmetikOperators.singleinput
import lupos.s04logicalOperators.Query

import com.soywiz.krypto.md5
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s00misc.resultFlow
import lupos.s03resultRepresentation.ResultRow
import lupos.s03resultRepresentation.ResultSet
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.AOPConstant
import lupos.s04arithmetikOperators.noinput.AOPConstantString
import lupos.s04arithmetikOperators.noinput.AOPSimpleLiteral
import lupos.s04logicalOperators.OPBase


@UseExperimental(ExperimentalStdlibApi::class)
class AOPBuildInCallMD5(query:Query,child: AOPBase) : AOPBase(query,EOperatorID.AOPBuildInCallMD5ID,"AOPBuildInCallMD5",arrayOf(child)) {

    override fun toSparql() = "MD5(" + children[0].toSparql() + ")"

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallMD5)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPSimpleLiteral(query,a.delimiter, a.content.encodeToByteArray().md5().toHexString1())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall MD5 only works with string input")
        })
    }

    override fun cloneOP() = AOPBuildInCallMD5(query,children[0].cloneOP() as AOPBase)
}

@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString1(): String {
    val sb = StringBuilder()
    for (b in this) {
        val tmp = (b + 256) % 256
        if (tmp == 0)
            sb.append("00")
        else
            sb.append(tmp.toString(16).padStart(2, '0'))
    }
    return sb.toString()
}
