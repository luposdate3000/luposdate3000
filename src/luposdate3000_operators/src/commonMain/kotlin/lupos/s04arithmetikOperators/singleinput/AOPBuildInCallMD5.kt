package lupos.s04arithmetikOperators.singleinput
import lupos.s00misc.Crypto
import lupos.s00misc.EOperatorIDExt
import lupos.s03resultRepresentation.ValueDefinition
import lupos.s03resultRepresentation.ValueError
import lupos.s03resultRepresentation.ValueSimpleLiteral
import lupos.s03resultRepresentation.ValueStringBase
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04logicalOperators.IOPBase
import lupos.s04logicalOperators.IQuery
import lupos.s04logicalOperators.iterator.IteratorBundle
@OptIn(ExperimentalStdlibApi::class)
public class AOPBuildInCallMD5 public constructor(query: IQuery, child: AOPBase) : AOPBase(query, EOperatorIDExt.AOPBuildInCallMD5ID, "AOPBuildInCallMD5", arrayOf(child)) {
    override fun toSparql(): String = "MD5(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean = other is AOPBuildInCallMD5 && children[0] == other.children[0]
    override fun evaluate(row: IteratorBundle): () -> ValueDefinition {
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
            var res: ValueDefinition = ValueError()
            val a = childA()
            if (a is ValueStringBase) {
                res = ValueSimpleLiteral(a.delimiter, Crypto.md5(a.content))
            }
            res
        }
    }
    override fun cloneOP(): IOPBase = AOPBuildInCallMD5(query, children[0].cloneOP() as AOPBase)
}
