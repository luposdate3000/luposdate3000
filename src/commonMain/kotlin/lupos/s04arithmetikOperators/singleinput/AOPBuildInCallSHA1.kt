package lupos.s04arithmetikOperators.singleinput
import com.soywiz.krypto.sha1
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.Value
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.iterator.ColumnIterator
import lupos.s04logicalOperators.iterator.IteratorBundle
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query
@UseExperimental(ExperimentalStdlibApi::class)
class AOPBuildInCallSHA1(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSHA1ID, "AOPBuildInCallSHA1", arrayOf(child)) {
    override fun toSparql() = "SHA1(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3207)
        if (other !is AOPBuildInCallSHA1) {
Coverage.ifStart(3208)
            return false
        }
Coverage.statementStart(3209)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3210)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3211)
        return {
Coverage.statementStart(3212)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3213)
            val a = childA()
Coverage.statementStart(3214)
            if (a is ValueStringBase) {
Coverage.ifStart(3215)
                res = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha1().toHexString2())
Coverage.statementStart(3216)
            }
Coverage.statementStart(3217)
/*return*/res
        }
Coverage.statementStart(3218)
    }
    override fun cloneOP() = AOPBuildInCallSHA1(query, children[0].cloneOP() as AOPBase)
}
@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString2(): String {
Coverage.funStart(3219)
    val sb = StringBuilder()
Coverage.statementStart(3220)
    for (b in this) {
Coverage.forLoopStart(3221)
        val tmp = (b + 256) % 256
Coverage.statementStart(3222)
        if (tmp == 0) {
Coverage.ifStart(3223)
            sb.append("00")
Coverage.statementStart(3224)
        } else {
Coverage.ifStart(3225)
            sb.append(tmp.toString(16).padStart(2, '0'))
Coverage.statementStart(3226)
        }
Coverage.statementStart(3227)
    }
Coverage.statementStart(3228)
    return sb.toString()
}
