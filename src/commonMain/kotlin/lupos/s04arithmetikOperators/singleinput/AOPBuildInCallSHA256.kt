package lupos.s04arithmetikOperators.singleinput

import com.soywiz.krypto.sha256
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

@UseExperimental(ExperimentalStdlibApi::class)
class AOPBuildInCallSHA256(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSHA256ID, "AOPBuildInCallSHA256", arrayOf(child)) {
    override fun toSparql() = "SHA256(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSHA256)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueStringBase)
                res = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha256().toHexString3())
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallSHA256(query, children[0].cloneOP() as AOPBase)
}

@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString3(): String {
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
