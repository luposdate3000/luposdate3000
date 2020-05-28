package lupos.s04arithmetikOperators.singleinput
import com.soywiz.krypto.md5
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
class AOPBuildInCallMD5(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallMD5ID, "AOPBuildInCallMD5", arrayOf(child)) {
    override fun toSparql() = "MD5(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
Coverage.funStart(3130)
        if (other !is AOPBuildInCallMD5) {
Coverage.ifStart(3131)
            return false
        }
Coverage.statementStart(3132)
        return children[0] == other.children[0]
    }
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
Coverage.funStart(3133)
        val childA = (children[0] as AOPBase).evaluate(row)
Coverage.statementStart(3134)
        return {
Coverage.statementStart(3135)
            var res: ValueDefinition = ValueError()
Coverage.statementStart(3136)
            val a = childA()
Coverage.statementStart(3137)
            if (a is ValueStringBase) {
Coverage.ifStart(3138)
                res = ValueSimpleLiteral(a.delimiter, a.content.encodeToByteArray().md5().toHexString1())
Coverage.statementStart(3139)
            }
Coverage.statementStart(3140)
/*return*/res
        }
Coverage.statementStart(3141)
    }
    override fun cloneOP() = AOPBuildInCallMD5(query, children[0].cloneOP() as AOPBase)
}
@UseExperimental(ExperimentalStdlibApi::class)
fun ByteArray.toHexString1(): String {
Coverage.funStart(3142)
    val sb = StringBuilder()
Coverage.statementStart(3143)
    for (b in this) {
Coverage.forLoopStart(3144)
        val tmp = (b + 256) % 256
Coverage.statementStart(3145)
        if (tmp == 0) {
Coverage.ifStart(3146)
            sb.append("00")
Coverage.statementStart(3147)
        } else {
Coverage.ifStart(3148)
            sb.append(tmp.toString(16).padStart(2, '0'))
Coverage.statementStart(3149)
        }
Coverage.statementStart(3150)
    }
Coverage.statementStart(3151)
    return sb.toString()
}
