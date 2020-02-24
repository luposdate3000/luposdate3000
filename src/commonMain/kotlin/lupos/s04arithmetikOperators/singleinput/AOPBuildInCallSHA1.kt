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


@UseExperimental(kotlin.ExperimentalStdlibApi::class)
class AOPBuildInCallSHA1(child: AOPBase) : AOPBase() {
    override val children: Array<OPBase> = arrayOf(child)

    override fun toXMLElement(): XMLElement {
        val res = XMLElement("AOPBuildInCallSHA1")
        res.addAttribute("uuid", "" + uuid)
        res.addContent(childrenToXML())
        return res
    }

    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSHA1)
            return false
        return children[0].equals(other.children[0])
    }

    override fun calculate(resultSet: ResultSet, resultRow: ResultRow): AOPConstant {
        val a = (children[0] as AOPBase).calculate(resultSet, resultRow)
        if (a is AOPConstantString)
            return resultFlow({ this }, { resultRow }, { resultSet }, {
                AOPSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha1().toHexString2())
            })
        throw resultFlow({ this }, { resultRow }, { resultSet }, {
            Exception("AOPBuiltInCall SHA1 only works with string input")
        })
    }
}

@UseExperimental(kotlin.ExperimentalStdlibApi::class)
fun ByteArray.toHexString2(): String {
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
