package lupos.s04arithmetikOperators.singleinput

import kotlin.jvm.JvmField
import lupos.s00misc.Coverage
import lupos.s00misc.EOperatorID
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.AOPBase
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04logicalOperators.iterator.*
import lupos.s04logicalOperators.OPBase
import lupos.s04logicalOperators.Query

class AOPBuildInCallSTR(query: Query, child: AOPBase) : AOPBase(query, EOperatorID.AOPBuildInCallSTRID, "AOPBuildInCallSTR", arrayOf(child)) {
    override fun toSparql() = "STR(" + children[0].toSparql() + ")"
    override fun equals(other: Any?): Boolean {
        if (other !is AOPBuildInCallSTR) {
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
                res = ValueSimpleLiteral(a.delimiter, a.content)
            } else if (a is ValueIri) {
                res = ValueSimpleLiteral("\"", a.iri)
            } else if (a !is ValueBnode && a !is ValueUndef) {
                val tmp = a.valueToString()!!
                res = ValueSimpleLiteral("" + tmp.get(0), tmp.substring(1, tmp.lastIndexOf(tmp.get(0))))
            }
            res
        }
    }

    override fun cloneOP() = AOPBuildInCallSTR(query, children[0].cloneOP() as AOPBase)
}
