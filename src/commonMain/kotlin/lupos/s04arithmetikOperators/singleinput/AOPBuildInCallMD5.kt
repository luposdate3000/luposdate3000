package lupos.s04arithmetikOperators.singleinput
import com.soywiz.krypto.md5
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.ColumnIteratorRow
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query



@UseExperimental(ExperimentalStdlibApi::class)
class AOPBuildInCallMD5(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMD5ID, "AOPBuildInCallMD5", arrayOf(child)) {
    override fun toSparql() = "MD5(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallMD5) {
            return false
        }
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueStringBase) {
                res = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().md5().toHexString1())
            }
/*return*/res
        }
    }

    override fun cloneOP() = AOPBuildInCallMD5(query, children[0].cloneOP() as AOPBase)
}

@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString1(): String {
    val sb = StringBuilder()
    for (b in this) {
        val tmp = (b + 256) % 256
        if (tmp == 0) {
            sb.append("00")
        } else {
            sb.append(tmp.toString(16).padStart(2, '0'))
        }
    }
    return sb.toString()
}
