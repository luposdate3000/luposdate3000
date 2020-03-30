package lupos.s04arithmetikOperators.singleinput
import kotlin.jvm.JvmField
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

impoert lupos.s04logicalOperators.iterator.*


class AOPBuildInCallSTRLEN(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRLENID, "AOPBuildInCallSTRLEN", arrayOf(child)) {
    override fun toSparql() = "STRLEN(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTRLEN)
            return false
        return children[0] == other.children[0]
    }

    override fun evaluate(row: ColumnIteratorRow): () -> ValueDefinition {
        
        val childA = (children[0] as AOPBase).evaluate(row)
        return {
var res = ValueError()
            val a = childA()
            if (a is ValueSimpleLiteral)
                res = ValueInteger(a.content.length)
            else if (a is ValueTypedLiteral)
                res = ValueInteger(a.content.length)
            else if (a is ValueLanguageTaggedLiteral)
                res = ValueInteger(a.content.length)
        res
        }
    }

    override fun cloneOP() = AOPBuildInCallSTRLEN(query, children[0].cloneOP() as AOPBase)
}
