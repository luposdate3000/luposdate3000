package lupos.s04arithmetikOperators.singleinput

import com.soywiz.krypto.sha1
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.ResultVektorRaw
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
import lupos.s04logicalOperators.ResultIterator

@UseExperimental(ExperimentalStdlibApi::class)
class AOPBuildInCallSHA1(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSHA1ID, "AOPBuildInCallSHA1", arrayOf(child)) {
    override fun toSparql() = "SHA1(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSHA1)
            return false
        return children[0] == other.children[0]
    }

    override fun calculate(resultChunk: ResultVektorRaw): ResultVektorRaw {
        val rVektor = ResultVektorRaw(resultChunk.availableRead())
        val aVektor = (children[0] as AOPBase).calculate(resultChunk)
        for (i in 0 until resultChunk.availableRead()) {
            val a = aVektor.data[i]
            if (a is ValueStringBase)
                rVektor.data[i] = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha1().toHexString2())
        }
        return rVektor
    }

    override fun cloneOP() = AOPBuildInCallSHA1(query, children[0].cloneOP() as AOPBase)
}

@UseExperimental(ExperimentalStdlibApi::class)
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
