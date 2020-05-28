package lupos.s04arithmetikOperators.singleinput
import com.soywiz.krypto.sha256
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
class AOPBuildInCallSHA256(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSHA256ID, "AOPBuildInCallSHA256", arrayOf(child)) {
    override fun toSparql() = "SHA256(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3229)
        if (other !is AOPBuildInCallSHA256) {
Coverage.ifStart(3230)
            return false
        }
Coverage.statementStart(3231)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3232)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3233)
        return {
Coverage.statementStart(3234)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3235)
            val a = childA()
Coverage.statementStart(3236)
            if (a is ValueStringBase) {
Coverage.ifStart(3237)
                res = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().sha256().toHexString3())
Coverage.statementStart(3238)
            }
Coverage.statementStart(3239)
/*return*/res
        }
Coverage.statementStart(3240)
    }
    override fun cloneOP() = AOPBuildInCallSHA256(query, children[0].cloneOP() as AOPBase)
}
@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString3(): String {
Coverage.funStart(3241)
    val sb = StringBuilder()
Coverage.statementStart(3242)
    for (b in this) {
Coverage.forLoopStart(3243)
        val tmp = (b + 256) % 256
Coverage.statementStart(3244)
        if (tmp == 0) {
Coverage.ifStart(3245)
            sb.append("00")
Coverage.statementStart(3246)
        } else {
Coverage.ifStart(3247)
            sb.append(tmp.toString(16).padStart(2, '0'))
Coverage.statementStart(3248)
        }
Coverage.statementStart(3249)
    }
Coverage.statementStart(3250)
    return sb.toString()
}
